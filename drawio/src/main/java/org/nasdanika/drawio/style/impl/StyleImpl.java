package org.nasdanika.drawio.style.impl;


import org.nasdanika.common.DelimitedStringMap;
import org.nasdanika.drawio.style.Style;

public abstract class StyleImpl extends DelimitedStringMap implements Style {
	
    private static final String OPACITY = "opacity";
    private static final String SHADOW = "shadow";
    private static final String ROUNDED = "rounded";	

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
		}
		remove(ROUNDED);
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
		}
		remove(SHADOW);
		return this;
	}

	@Override
	public boolean shadow() {
        return "1".equals(get(SHADOW));
	}
	
}
