package org.nasdanika.exec.java;

public enum JdkLevel {
	
	JDK_4("1.4"),
	JDK_5("1.5"),
	JDK_6("1.6"),
	JDK_7("1.7"),
	JDK_8("1.8"),
	JDK_9("9"),
	JDK_10("10"),
	JDK_11("11"),
	JDK_12("12"),
	JDK_13("13");

	public final String literal;
	
	private JdkLevel(String literal) {
		this.literal = literal;
	}
	
	public static JdkLevel fromLiteral(String literal) {
		for (JdkLevel level: JdkLevel.values()) {
			if (level.literal.equals(literal)) {
				return level;
			}
		}
		return null;
	}
}
