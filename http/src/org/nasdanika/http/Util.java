package org.nasdanika.http;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

public final class Util {
	
	public static final String JSON_CONTENT_TYPE = "application/json";
	public static final String PATH_VARIABLE_ROUTE_URL = "routeURL";
	public static final String PATH_VARIABLE_ROUTE_PATH = "routePath";
	public static final String PATH_VARIABLE_PATH_INFO = "pathInfo";	
	
	private Util() {
		// Singleton
	}

	public static boolean isBlank(String str) {
		return str == null || str.trim().isEmpty();
	}
	
	private static final String DIGEST_ALGORITHM = "SHA-256";
	private static final String UTF_8 = "UTF-8";	
	
	/**
	 * Computes SHA-256 hash for a password with an optional seed.
	 * @param seed Seed, can be null.
	 * @param password Password.
	 * @return Password hash.
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] passwordHash(byte[] seed, String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance(DIGEST_ALGORITHM);
		if (seed != null) {
			md.update(seed);
		}
		md.update(password.getBytes(UTF_8));
		return md.digest();
	}
		
	/**
	 * Builds url including scheme and port.
	 * @param req
	 * @return
	 */
	public static StringBuilder buildUrl(HttpServletRequest req) {
		StringBuilder urlBuilder = new StringBuilder(req.getScheme())
		        .append("://")
		        .append(req.getServerName());

		int port = req.getServerPort();
		if ((req.getScheme().equals("http") && port != 80) || (req.getScheme().equals("https") && port != 443)) {
			urlBuilder.append(':').append(port);
		}
		urlBuilder.append(req.getContextPath()).append(req.getServletPath());			
		
		return urlBuilder;
	}
	

}
