package org.nasdanika.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Base64;
import java.util.concurrent.TimeoutException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.nasdanika.repository.AuthenticationException;
import org.nasdanika.repository.AuthorizationException;
import org.nasdanika.repository.Command;
import org.nasdanika.repository.Executor;
import org.nasdanika.repository.Repository;

@SuppressWarnings("serial")
/**
 * Repository servlet wraps request/response into a command which is executed against a resource in the repository.
 * @author Pavel
 *
 */
public abstract class RepositoryServlet extends HttpServlet {
	
	
//	public static final MimetypesFileTypeMap MIME_TYPES_MAP = new MimetypesFileTypeMap(TransactionServlet.class.getResourceAsStream("mime.types"));	
	
	protected static final String EXECUTOR_KEY = "org.nasdanika.http:executor";
	
	public static final String AUTHORIZATION_BEARER = "Bearer ";
	
	private static final String AUTHORIZATION_BASIC = "Basic ";
		
    private static final String HEADER_IFMODSINCE = "If-Modified-Since";
    private static final String HEADER_LASTMOD = "Last-Modified";		
		
	protected boolean jsonPrettyPrint = true;
	
	protected abstract Repository getRepository(HttpServletRequest req);

	protected abstract Command<Result> createCommand(HttpServletRequest req, HttpServletResponse res);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try (Executor executor = getExecutor(req)) {
			long lastModified = executor.getTimestamp();
			if (RequestMethod.valueOf(req.getMethod()) == RequestMethod.GET && lastModified != -1) {
			    long ifModifiedSince = req.getDateHeader(HEADER_IFMODSINCE);
			    if (ifModifiedSince > 0 && lastModified > 0) {
			        if (lastModified - ifModifiedSince < 1000) { // Seconds precision.
			        	resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
			        	return;
			        }
			    }
			}
			
			String pathInfo = req.getPathInfo();
			if (pathInfo == null) {
				pathInfo = "/";
			}
			
			if ("/login".equals(pathInfo)) {
				// TODO - invalidate session, take credentials from the request, authenticate, store 
				// executor in the session, redirect to /index.html
			} else if ("/logout".equals(pathInfo)) {
				// Invalidate session, close executor (or session listener to close?). redirect to /index.html
			} else {
				Result result = executor.execute(createCommand(req, resp), pathInfo);					
				if (result == Result.FORBIDDEN && !executor.isAuthenticated()) {
					if (redirectToLogin(executor, req, resp)) {
						result = null;
					} else {
						result = Result.UNAUTHORIZED;
					}
				}
				if (result != null) {
					String contentType = result.getContentType();
					if (contentType != null) {
						resp.setContentType(contentType);
					}
					resultToResponse(result.getValue(), resp);
				}
				lastModified = executor.getTimestamp();
				if (RequestMethod.valueOf(req.getMethod()) == RequestMethod.GET && lastModified != -1 && !resp.containsHeader(HEADER_LASTMOD)) {
			        resp.setDateHeader(HEADER_LASTMOD, lastModified);        	
				}				
			}				
		} catch (TimeoutException e) {
			resp.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, e.getMessage());								
		} catch (AuthorizationException | AuthenticationException e) {
			log("Authentication exception: " + e, e);
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.toString());			
		} catch (Exception e) {
			log(e.toString(), e);
			if (e instanceof ServletException) {
				throw (ServletException) e;
			}
			if (e instanceof IOException) {
				throw (IOException) e;
			}
			throw new ServletException(e);
		}
	}

	protected void resultToResponse(Object result, HttpServletResponse resp) throws Exception {
		if (result instanceof ProcessingError) {
			ProcessingError processingError = (ProcessingError) result;
			if (processingError.getMessage()==null) {
				resp.sendError(processingError.getCode());
			} else {
				resp.sendError(processingError.getCode(), processingError.getMessage());
			}
//		} else if (result instanceof Producer) {
//			resultToResponse(((Producer) result).produce(4), resp);
		} else if (result instanceof JSONArray) {
			resp.setContentType("application/json");
			if (jsonPrettyPrint) {
				resp.getWriter().write(((JSONArray) result).toString(4));
			} else {
				((JSONArray) result).write(resp.getWriter());
			}
		} else if (result instanceof JSONObject) {
			resp.setContentType("application/json");
			if (jsonPrettyPrint) {
				resp.getWriter().write(((JSONObject) result).toString(4));
			} else {
				((JSONObject) result).write(resp.getWriter());
			}
		} else if (result instanceof String) {
			resp.getWriter().write((String) result);
		} else if (result instanceof char[]) {
			resp.getWriter().write((char[]) result);
		} else if (result instanceof byte[]) {
			resp.getOutputStream().write((byte[]) result);
		} else if (result instanceof Reader) {
			try {
				Writer writer = resp.getWriter();
				char[] cbuf = new char[4096];
				int l;
				while ((l=((Reader) result).read(cbuf))!=-1) {
					writer.write(cbuf, 0, l);
				}
			} finally {
				((Reader) result).close();
			}
		} else if (result instanceof InputStream) {
			try (InputStream in = (InputStream) result; OutputStream out = resp.getOutputStream()) {
				byte[] buf = new byte[4096];
				int l;
				while ((l=((InputStream) result).read(buf))!=-1) {
					out.write(buf, 0, l);
				}
			}
		} else if (result instanceof URL) {
			resultToResponse(((URL) result).openStream(), resp);
		} else if (result!=null) {
			resp.getWriter().write(result.toString());
		}
	}	
	
	/**
	 * Redirects unauthorized request to the login page.
	 * @param executor
	 * @param asyncContext
	 * @return true if redirected.
	 */
	protected boolean redirectToLogin(Executor executor, HttpServletRequest req, HttpServletResponse resp) {
		return false;
	}
	
	/**
	 * Request header containing forwarded user name.
	 * @return
	 */
	protected String getForwardedUserHeader() {
		return "X-Forwarded-User";
	}
	
	/**
	 * Authorizes address for forwarding user name. This method authorizes only the localhost.
	 * @param remoteAddress
	 * @return
	 */
	protected boolean authorizeForwardingHost(String remoteAddress) {
		try {
			return InetAddress.getLocalHost().equals(InetAddress.getByName(remoteAddress));
		} catch (UnknownHostException e) {
			log("Cannot obtain localhost or invalid remote address", e);
			return false;
		}
	}
	
	/**
	 * Caches return value of doGetExecutor in the request for optimization.
	 * @param req
	 * @return
	 * @throws AuthenticationException 
	 * @throws AuthorizationException 
	 */
	protected Executor getExecutor(HttpServletRequest req) throws AuthenticationException, AuthorizationException {
		// Caching in the request for optimization.
		Executor ret = (Executor) req.getAttribute(EXECUTOR_KEY);
		if (ret == null) {
			ret = doGetExecutor(req);
			req.setAttribute(EXECUTOR_KEY, ret);
		}
		
		return ret;
	}
	
	/**
	 * Repository executor associated with a request.
	 * @param req
	 * @return
	 * @throws org.nasdanika.repository.AuthenticationException 
	 * @throws AuthorizationException 
	 */
	protected Executor doGetExecutor(HttpServletRequest req) throws AuthenticationException, AuthorizationException {				
		// Remote user
		String forwardedUserHeader = getForwardedUserHeader();
		if (forwardedUserHeader != null) {
			String principalName = req.getHeader(forwardedUserHeader);
			if (principalName != null) {
				String remoteAddr = req.getRemoteAddr();
				if (authorizeForwardingHost(remoteAddr)) {
					return getRepository(req).createExecutor(principalName, false);
				}
				
				throw new AuthorizationException("Unauthorized forwarding host: "+remoteAddr);
			}
		}		
		
		// Auth header is present
		String authHeader = req.getHeader("Authorization");
		if (authHeader != null) {
			if (authHeader.startsWith(AUTHORIZATION_BEARER)) {		
				String token = authHeader.substring(AUTHORIZATION_BEARER.length());
				return getRepository(req).createExecutor(token,  true);				
			} 
			
			if (authHeader.startsWith(AUTHORIZATION_BASIC)) {
				// Basic authorization
				String decoded = new String(Base64.getDecoder().decode(authHeader.substring(AUTHORIZATION_BASIC.length())));
				int idx = decoded.indexOf(":");
				if (idx == -1) {
					throw new AuthenticationException("Invalid credentials format: "+decoded);
				}
				return getRepository(req).createExecutor(decoded.substring(0, idx), decoded.substring(idx+1));
			}
		}
		
		// Principals are present in the session.
		HttpSession session = req.getSession(false);
		if (session != null) {
			Executor ret = (Executor) session.getAttribute(EXECUTOR_KEY);
			if (ret != null) {
				return ret;
			}
		}
		
		// Unauthenticated executor		
		return getRepository(req).createExecutor();
	}
	
//	protected abstract Key getJwtTokenIssuerKey(HttpServletRequest req, String key);
//	
//	protected SigningKeyResolver getJwtTokenSignatureKeyResolver(HttpServletRequest req) {
//		return new SigningKeyResolver() {
//			
//			@Override
//			public Key resolveSigningKey(@SuppressWarnings("rawtypes") JwsHeader header, String plainText) {
//				throw new UnsupportedOperationException();
//			}
//			
//			@Override
//			public Key resolveSigningKey(@SuppressWarnings("rawtypes") JwsHeader header, Claims claims) {
//				return getJwtTokenIssuerKey(req, claims.getIssuer());
//			}
//		};
//	}
//	
//	/**
//	 * Builds token subject. This creates a JWT token subject. 
//	 * Override to provide support of user forwarding. 
//	 * @param forwardedUser
//	 * @return
//	 */
//	protected Transaction openJwtTransaction(HttpServletRequest req, String token) {
//		SigningKeyResolver signingKeyResolver = getJwtTokenSignatureKeyResolver(req);
//		if (signingKeyResolver == null) {
//			throw new AuthenticationException("No signing key resolver");
//		}
//		try {
//			JwtParser parser = Jwts.parser()
//					.setSigningKeyResolver(getJwtTokenSignatureKeyResolver(req))
//					.setAllowedClockSkewSeconds(60 * 5); // 5 minutes.
//			validateTokenRequirements(parser);
//			
//			Jws<Claims> jws = parser.parseClaimsJws(token);			
//			return openTransaction(req, jws);
//		} catch (JwtException jwtException) {
//			throw new AuthenticationException(jwtException);
//		}
//	};
//	
//	/**
//	 * Creates JWT subject. This implementation uses subject field as forwarded user field to create a transaction.
//	 * @param req
//	 * @param transaction
//	 * @param jws
//	 * @return
//	 */
//	protected Transaction openTransaction(HttpServletRequest req, Jws<Claims> jws) {
//		String subject = jws.getBody().getSubject();
//		return Util.isBlank(subject) ? null : openTransaction(req, subject);
//	}
	
//	/**
//	 * This method requires not before and expiration. 
//	 * Override to implement additional checks. 
//	 * @param parser
//	 */
//	protected void validateTokenRequirements(JwtParser parser) {
//		Date now = new Date();
//		parser.requireNotBefore(now).requireExpiration(now);
//	}
		
	
//	/**
//	 * Creates unauthenticated principal (guest).
//	 * @param req
//	 * @param transaction
//	 * @return
//	 */
//	protected abstract Transaction openUnauthenticatedTransaction(HttpServletRequest req);
	
//	protected static class CacheKey {
//		String action;
//		String qualifier;
//		
//		CacheKey(String action, String qualifier) {
//			super();
//			this.action = action;
//			this.qualifier = qualifier;
//		}
//		@Override
//		public int hashCode() {
//			final int prime = 31;
//			int result = 1;
//			result = prime * result + ((action == null) ? 0 : action.hashCode());
//			result = prime * result + ((qualifier == null) ? 0 : qualifier.hashCode());
//			return result;
//		}
//		@Override
//		public boolean equals(Object obj) {
//			if (this == obj)
//				return true;
//			if (obj == null)
//				return false;
//			if (getClass() != obj.getClass())
//				return false;
//			CacheKey other = (CacheKey) obj;
//			if (action == null) {
//				if (other.action != null)
//					return false;
//			} else if (!action.equals(other.action))
//				return false;
//			if (qualifier == null) {
//				if (other.qualifier != null)
//					return false;
//			} else if (!qualifier.equals(other.qualifier))
//				return false;
//			return true;
//		}		
//		
//	}
//	
//	/**
//	 * Creates access controller which calls authorize() and caches permission check results with empty contexts.
//	 * @param req
//	 * @param transaction
//	 * @param target
//	 * @param context
//	 * @return
//	 */
//	protected AccessController createAccessController(HttpServletRequest req, Transaction transaction, EObject target) {
//		return new AccessController() {
//						
//			private Map<CacheKey, Boolean> cache = new ConcurrentHashMap<>();
//			
//			@Override
//			public boolean hasPermission(String action, String qualifier, Map<?,?> context) {
//				if (context.isEmpty()) {
//					Function<? super CacheKey, ? extends Boolean> cf = key -> authorize(req, transaction, target, key.action, key.qualifier, context);
//					return cache.computeIfAbsent(new CacheKey(action, qualifier), cf);
//				}
//				return authorize(req, transaction, target, action, qualifier, context);
//			}
//			
//			/**
//			 * Checks request authorization cache first, calls doAuthorize if there is no entry in the cache.
//			 * @param req
//			 * @param transaction
//			 * @param target
//			 * @param action
//			 * @param qualifier
//			 * @param context
//			 * @return
//			 */
//			private boolean authorize(
//					HttpServletRequest req, 
//					Transaction transaction, 
//					EObject target, 
//					String action, 
//					String qualifier, 
//					Map<?,?> context) {
//				
//				return getPrincipal(req, transaction).authorize(target, action, qualifier, context) == AccessDecision.ALLOW;
//			} 	
//			
//		};
//	}
	
//	{
//		org.nasdanika.http.Repository.Result findResult = transaction.find(pathInfo);
//		if (findResult == null) {
//			resp.sendError(HttpServletResponse.SC_NOT_FOUND);						
//		} else {						
//			Processor processor = EObjectAdaptable.adaptTo(findResult.getObject(), Processor.class);
//			if (processor == null) {
//				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//			} else {
//				int objectPathLength = findResult.getObjectPath().length();
//				HttpServletRequestWrapper wReq = new HttpServletRequestWrapper(req) {
//					
//					@Override
//					public String getPathInfo() {
//						return super.getPathInfo().substring(objectPathLength);
//					}
//					
//					@Override
//					public String getServletPath() {
//						return super.getServletPath()+super.getPathInfo().substring(0, objectPathLength);
//					}
//					
//				};
//				
//				Map<String,String> pathVariables = new HashMap<>();
//				pathVariables.put(Util.PATH_VARIABLE_PATH_INFO, wReq.getPathInfo());
//				pathVariables.put(Util.PATH_VARIABLE_ROUTE_PATH, wReq.getContextPath()+wReq.getServletPath());
//				
//				pathVariables.put(Util.PATH_VARIABLE_ROUTE_URL, Util.buildUrl(wReq).toString()); 
//				
//				result = processor.process(wReq, resp, pathVariables::get);
//				// TODO - Transaction listener, onCommit(Transaction) onRollback(Transaction), addListener 
////					if (result instanceof CDOTransactionHandlerBase) {
////						transaction.addTransactionHandler((CDOTransactionHandlerBase) result);
////					}
//			}
//		}
//		if (result != null && result.isRollback()) {
//			transaction.rollback();
//		} else {
//			transaction.commit();
//		}
//		
//		// Unauthenticated principal - return authorized or redirect to auth url
//		if (result == Result.FORBIDDEN && !transaction.isAuthenticated()) {
//			String au = transaction.getAuthenticationUrl();
//			if (Util.isBlank(au)) {
//				resp.sendRedirect(au);
//				result = null;
//			} else {
//				result = Result.UNAUTHORIZED;
//			}
//		}
//		if (result != null) {
//			String contentType = result.getContentType();
//			if (contentType != null) {
//				resp.setContentType(contentType);
//			}
//			resultToResponse(result.getValue(), resp);
//		}
//	}
//} catch (Exception e) {
//	log(e.toString(), e);
//	transaction.rollback();
//	if (e instanceof ServletException) {
//		throw (ServletException) e;
//	}
//	if (e instanceof IOException) {
//		throw (IOException) e;
//	}
//	throw new ServletException(e);
//} finally {
//	long lastModified = transaction.timestamp();
//	if (RequestMethod.valueOf(req.getMethod()) == RequestMethod.GET && lastModified != -1 && !resp.containsHeader(HEADER_LASTMOD)) {
//        resp.setDateHeader(HEADER_LASTMOD, lastModified);        	
//	}
//}
//} catch (AuthenticationException e) {
//log("Authentication exception: " + e, e);
//resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.toString());			
//} catch (Exception e) {
//log(e.toString(), e);
//if (e instanceof ServletException) {
//	throw (ServletException) e;
//}
//if (e instanceof IOException) {
//	throw (IOException) e;
//}
//throw new ServletException(e);
//}
//
//if (result != null) {
//try {
//	result.close();
//} catch (Exception e) {
//	log("Result closing failed: "+e.toString(), e);
//}
//}
//	
//	}
		
}



//org.nasdanika.http.Repository.Result findResult = transaction.find(pathInfo);
//if (findResult == null) {
//	resp.sendError(HttpServletResponse.SC_NOT_FOUND);						
//} else {						
//	Processor processor = EObjectAdaptable.adaptTo(findResult.getObject(), Processor.class);
//	if (processor == null) {
//		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
//	} else {
//		int objectPathLength = findResult.getObjectPath().length();
//		HttpServletRequestWrapper wReq = new HttpServletRequestWrapper(req) {
//			
//			@Override
//			public String getPathInfo() {
//				return super.getPathInfo().substring(objectPathLength);
//			}
//			
//			@Override
//			public String getServletPath() {
//				return super.getServletPath()+super.getPathInfo().substring(0, objectPathLength);
//			}
//			
//		};
//		
//		Map<String,String> pathVariables = new HashMap<>();
//		pathVariables.put(Util.PATH_VARIABLE_PATH_INFO, wReq.getPathInfo());
//		pathVariables.put(Util.PATH_VARIABLE_ROUTE_PATH, wReq.getContextPath()+wReq.getServletPath());
//		
//		pathVariables.put(Util.PATH_VARIABLE_ROUTE_URL, Util.buildUrl(wReq).toString()); 
//		
//		result = processor.process(wReq, resp, pathVariables::get);
//		// TODO - Transaction listener, onCommit(Transaction) onRollback(Transaction), addListener 
////			if (result instanceof CDOTransactionHandlerBase) {
////				transaction.addTransactionHandler((CDOTransactionHandlerBase) result);
////			}
//	}
//}
