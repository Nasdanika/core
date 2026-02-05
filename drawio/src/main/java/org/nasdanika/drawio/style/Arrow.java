package org.nasdanika.drawio.style;

/**
 * Common arrow shapes used by Draw.io for connection ends.
 */
public enum Arrow {
		
	/** Standard triangular arrow */
	CLASSIC("classic"),

	/** Thin variant of classic arrow */
	CLASSIC_THIN("classicThin"),

	/** Filled block/triangle arrow */
	BLOCK("block"),

	/** Thin block arrow */
	BLOCK_THIN("blockThin"),

	/** Open/unfilled arrow */
	OPEN("open"),

	/** Thin open arrow */
	OPEN_THIN("openThin"),

	/** Circular/oval arrow marker */
	OVAL("oval"),

	/** Diamond-shaped arrow marker */
	DIAMOND("diamond"),

	/** Thin diamond arrow */
	DIAMOND_THIN("diamondThin"),

	/** Dash/line endpoint */
	DASH("dash"),

	/** No arrow marker */
	NONE("none"),

	/** Entity relationship: one */
	E_RONE("ERone"),

	/** Entity relationship: mandatory one */
	E_RMAND_ONE("ERmandOne"),

	/** Entity relationship: many */
	E_RMANY("ERmany"),

	/** Entity relationship: one to many */
	E_RONE_TO_MANY("ERoneToMany"),

	/** Entity relationship: zero to one */
	E_RZERO_TO_ONE("ERzeroToOne"),

	/** Entity relationship: zero to many */
	E_RZERO_TO_MANY("ERzeroToMany");

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
