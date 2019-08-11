package org.nasdanika.help.markdown;

/**
 * Utility class
 * @author Pavel
 *
 */
public class DocUtil {
	
	private DocUtil() {
		// Singleton
	}
	
	public static boolean isBlank(String text) {
		return text == null || text.trim().length() == 0;
	}
	
	public static String unescape(String str) {
		StringBuilder sb = new StringBuilder();
		boolean inEscape = false;
		for (int i=0; i<str.length(); ++i) {
			if (inEscape) {
				inEscape = false;
				sb.append(str.charAt(i));
			} else {
				if (str.charAt(i) == '\\') {
					inEscape = true;
				} else {
					sb.append(str.charAt(i));
				}
			}				
		}
		return sb.toString();
	}
	
	/**
	 * Index of taking escaping into account.
	 * @param str
	 * @param from
	 * @param ch
	 * @return
	 */
	public static int indexOf(String str, int from, char ch) {
		boolean inEscape = false;
		for (int i=from; i<str.length(); ++i) {
			if (inEscape) {
				inEscape = false;
			} else {
				if (str.charAt(i)=='\\') {
					inEscape = true;
				} else {
					if (str.charAt(i)==ch) {
						return i;
					}
				}
			}
		}
		return -1;
	}
	

}
