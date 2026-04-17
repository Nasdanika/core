package org.nasdanika.drawio.style;

/**
 * Jump style.
 */
public enum Jump {
		
	NONE(null),

	ARC("arc"),

	GAP("gap"),

	SHARP("sharp"),

	LINE("line");

    private final String value;

    Jump(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Jump fromString(String v) {
        if (v == null) return NONE;
        for (Jump a : values()) {
            if (a.value.equalsIgnoreCase(v) || a.name().equalsIgnoreCase(v)) {
                return a;
            }
        }
        return NONE;
    }
}
