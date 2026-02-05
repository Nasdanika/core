package org.nasdanika.drawio.style.impl;


import org.nasdanika.common.DelimitedStringMap;
import org.nasdanika.drawio.style.Style;

public abstract class StyleImpl extends DelimitedStringMap implements Style {
	
    private static final String OPACITY = "opacity";
    private static final String SHADOW = "shadow";
    private static final String ROUNDED = "rounded";	
    private static final String ENUMERATE = "enumerate";	
    private static final String ENUMERATE_VALUE = "enumerateValue";	

	protected StyleImpl() {
		super(";", "=");
	}

	@Override
	public String opacity() {
		return get(OPACITY);
	}

	@Override
	public Style opacity(String opacity) {
		put(OPACITY, opacity);
		return this;
	}

	@Override
	public Style opacity(int opacity) {
		return opacity(String.valueOf(opacity));
	}

	@Override
	public Style rounded(boolean rounded) {
		if (rounded) {
			put(ROUNDED, "1");
		} else {
			remove(ROUNDED);
		}
		return this;
	}

	@Override
	public boolean rounded() {
        return "1".equals(get(ROUNDED));
	}

	@Override
	public Style shadow(boolean shadow) {
		if (shadow) {
			put(SHADOW, "1");
		} else {
			remove(SHADOW);
		}
		return this;
	}

	@Override
	public boolean shadow() {
        return "1".equals(get(SHADOW));
	}

	@Override
	public String enumerateValue() {
		return get(ENUMERATE_VALUE);
	}

	@Override
	public Style enumerateValue(String enumerateValue) {
		put(ENUMERATE_VALUE, enumerateValue);
		return this;
	}

	@Override
	public Style enumerate(boolean enumerate) {
		if (enumerate) {
			put(ENUMERATE, "1");
		} else {
			remove(ENUMERATE);
		}
		return this;
	}

	@Override
	public boolean enumerate() {
        return "1".equals(get(ENUMERATE));
	}
	
}
