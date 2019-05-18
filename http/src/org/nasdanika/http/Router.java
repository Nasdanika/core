package org.nasdanika.http;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * Dispatches request processing to matching target.
 * 
 * @author Pavel Vlasov
 *
 */
public abstract class Router implements Processor {
	
	/**
	 * Target to route request to.
	 * @author Pavel
	 *
	 */
	public interface Target extends Processor {
		/**
		 * Supported HTTP methods. 
		 * @return
		 */
		RequestMethod[] getRequestMethods();
		
		/**
		 * Pattern to match path. Pattern is used for matching if getPath() returns null.
		 * @return
		 */
		Pattern getPattern();
		
		/**
		 * Target path. Takes precedence over pattern. May contain path parameter specs, e.g. <code>{something}</code>
		 * @return
		 */
		String getPath();
		
		/**
		 * Response content type produced by the method. Used for route matching and for setting response content type if not yet set by the target.
		 * If this method returns null then the response content type is implied from the path's extension.
		 * @return
		 */
		String getProduces();
		
		/**
		 * Content types which this method can consume. Used for matching the target to request. Empty array matches any content type.
		 * for <code>CREATE_WEB_SOCKET</code> {@link RequestMethod} this attribute matches sub-protocols.
		 * @return
		 */
		String[] getConsumes();
				
		/**
		 * Authorization action. If not set a standard action corresponding to request method is used:
		 * 
		 * * GET, OPTIONS, TRACE - read
		 * * POST - create
		 * * DELETE - delete
		 * * PUT, PATCH - update
		 * 
		 * @return
		 */
		String getAction();
		
		/**
		 * Authorization qualifier. 
		 * @return
		 */
		String getQualifier();
				
	}
	
	protected abstract List<Target> getTargets(HttpServletRequest request) throws Exception;
	
	protected abstract boolean authorize(HttpServletRequest request, String action, String qualifier);

	@Override
	public Result process(HttpServletRequest request, HttpServletResponse response,	Function<String, String> pathVariables) throws Exception {
		String[] path = request.getPathInfo().split("/"); 
		
		W: for (Target target: getTargets(request)) {
			RequestMethod[] requestMethods = target.getRequestMethods();
			if (requestMethods.length>0) {
				boolean methodMatch = false;
				for (RequestMethod method: requestMethods) {
					if (RequestMethod.valueOf(request.getMethod()).equals(method)) {
						methodMatch = true;
						break;
					}
				}
				if (!methodMatch) {
					continue;
				}
			}
			
			String produces = target.getProduces();
			if (!matchProduces(request, produces)) {
				continue;
			}
			
			if (!matchConsumes(request, target.getConsumes())) {
				continue;
			}
			
			String action = target.getAction();
			if (Util.isBlank(action)) {
				// Mapping request methods to standard actions
				switch (RequestMethod.valueOf(request.getMethod())) {
				case DELETE:
					action = "delete";
					break;
				case GET:
				case OPTIONS:
				case TRACE:
					action = "read";
					break;
				case POST:
					action = "create";
					break;
				case PUT:
				case PATCH:
					action = "update";
					break;
				default:
					action = request.getMethod();
					break;						
				}
			}			
						
			String targetPath = target.getPath();
			if (!Util.isBlank(targetPath)) {
				String[] splitRoutePath = targetPath.split("/");
				if (targetPath.endsWith("/")) {
					if (splitRoutePath.length>=path.length) {
						continue;
					}
				} else {
					if (splitRoutePath.length!=path.length) {
						continue;
					}
				}
				
				String qualifier = target.getQualifier();
				Map<String,String> pathParameters = new HashMap<>();
				for (int i=0; i<splitRoutePath.length; ++i) {
					boolean isPathParameter = splitRoutePath[i].startsWith("{") && splitRoutePath[i].endsWith("}");
					if (isPathParameter) {
						pathParameters.put(splitRoutePath[i].substring(1, splitRoutePath[i].length() - 1), path[i]);
						// Replacing path tokens in the qualifier
						if (qualifier != null) {
							for (int idx = qualifier.indexOf(splitRoutePath[i]); idx != -1; idx = qualifier.indexOf(splitRoutePath[i], idx + splitRoutePath[i].length())) {
								qualifier = qualifier.substring(0, idx) + path[i] + qualifier.substring(idx + splitRoutePath[i].length());
							}
						}								
					} else if (!splitRoutePath[i].equals(path[i])) {
						continue W;
					}
				}
								
				if (authorize(request, action, qualifier)) {
					if (response!=null && response.getContentType()==null && !Util.isBlank(produces)) {
						response.setContentType(produces);
					}							
					String matchedPath = String.join("/", Arrays.copyOfRange(path, 0, splitRoutePath.length));
					String pathInfo = String.join("/", Arrays.copyOfRange(path, splitRoutePath.length, path.length));
					
					pathParameters.put(Util.PATH_VARIABLE_PATH_INFO, pathInfo);
					pathParameters.put(Util.PATH_VARIABLE_ROUTE_PATH, pathVariables.apply(Util.PATH_VARIABLE_ROUTE_PATH) + matchedPath);
					pathParameters.put(Util.PATH_VARIABLE_ROUTE_URL, pathVariables.apply(Util.PATH_VARIABLE_ROUTE_URL) + matchedPath);

					HttpServletRequestWrapper wReq = new HttpServletRequestWrapper(request) {
						
						@Override
						public String getPathInfo() {
							return targetPath.endsWith("/") ? "/" + pathInfo : pathInfo;
						}
						
						@Override
						public String getServletPath() {
							return super.getServletPath()+matchedPath;
						}
						
					};
					
					return target.process(wReq, response, var -> pathParameters.computeIfAbsent(var, pathVariables));
				}
				
				return Result.FORBIDDEN;
			} else if (path.length > 1) {										
				Pattern pattern = target.getPattern();
				if (pattern!=null && pattern.matcher(request.getPathInfo()).matches()) {
					if (authorize(request, action, target.getQualifier())) {
						if (response!=null && response.getContentType()==null && !Util.isBlank(produces)) {
							response.setContentType(produces);
						}							
						return target.process(request, response, pathVariables);
					}
					
					return Result.FORBIDDEN;
				}				
			}						
		}		
		return Result.NOT_FOUND;
	}
	
	
	public static boolean matchConsumes(HttpServletRequest request, String[] consumes) {
		if (consumes==null || consumes.length==0) {
			return true;
		}		
		
		String contentType = request.getContentType();
		if (Util.isBlank(contentType)) {
			return false;
		}
		int idx = contentType.indexOf(";");
		if (idx != -1) {
			contentType = contentType.substring(0, idx).trim();
		}
		for (String consumesEntry: consumes) {
			String contentTypeLowerCase = contentType.trim().toLowerCase();
			String ceLowerCase = consumesEntry.trim().toLowerCase();
			if (ceLowerCase.equals("*/*") || ceLowerCase.equals(contentTypeLowerCase)) {
				return true;
			}
			if (consumesEntry.endsWith("/*") && contentTypeLowerCase.startsWith(ceLowerCase.substring(0, ceLowerCase.length()-1))) {
				return true;				 
			}
		}					
		
		return false;
	}

	public static boolean matchProduces(HttpServletRequest request, String produces) {
		if (Util.isBlank(produces)) {
			return true;
		}
		String accept = request.getHeader("Accept");
		if (Util.isBlank(accept)) {
			return true;
		}
		String plc = produces.toLowerCase().trim();
		for (String acceptEntry: accept.split(",")) {
			String acceptEntryLowerCase = acceptEntry.trim().toLowerCase();
			int idx = acceptEntryLowerCase.indexOf(";");
			if (idx!=-1) {
				acceptEntryLowerCase = acceptEntryLowerCase.substring(0, idx).trim();
			}
			// Ignoring q and level for now or forever
			if (acceptEntryLowerCase.equals("*/*") || acceptEntryLowerCase.equals(plc)) {
				return true;
			}
			
			if (acceptEntryLowerCase.endsWith("/*") && plc.startsWith(acceptEntryLowerCase.substring(0, acceptEntryLowerCase.length()-1))) {
				return true;
			}
		}					
			
		return false;
	}	
	
}
