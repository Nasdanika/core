package org.nasdanika.common;

import org.apache.commons.lang3.StringUtils;

public class Util {

	private Util() {
		// Singleton
	}
	
	/**
	 * Converts name to label text by breaking the name by camel case, capitalizing the first word and lowsercasing the rest.
	 * @return
	 */
	public static String nameToLabel(String name) {
		String[] cca = StringUtils.splitByCharacterTypeCamelCase(name);
		cca[0] = StringUtils.capitalize(cca[0]);
		for (int i=1; i<cca.length; ++i) {
			cca[i] = cca[i].toLowerCase();
		}
		return StringUtils.join(cca, " ");
	}
	
	public static boolean isBlank(String str) {
		return str == null || str.trim().length() == 0;
	}
	
	/**
	 * Validates that URL is a valid relative file name/path - a very basic check.
	 * @param url
	 * @return
	 */
	public static boolean isValidAndRelative(String url) {
		String interpolated = Context.wrap(k -> "something").interpolate(url); // interpolates tokens if any with valid file part name.
		return !interpolated.contains("://") && !interpolated.startsWith("/") && !interpolated.startsWith("./");
	}	
	
}
