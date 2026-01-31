package org.nasdanika.drawio.style;

/**
 * Common arrow shapes used by Draw.io for connection ends.
 */
public enum Arrow {
	
    NONE("none"),
    CLASSIC("classic"),
    BLOCK("block"),
    OPEN("open"),
    DIAMOND("diamond"),
    OVAL("oval");

    private final String value;

    Arrow(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Arrow fromString(String v) {
        if (v == null) return NONE;
        for (Arrow a : values()) {
            if (a.value.equalsIgnoreCase(v) || a.name().equalsIgnoreCase(v)) {
                return a;
            }
        }
        return NONE;
    }
}
