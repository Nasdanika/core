package org.nasdanika.drawio.style.impl;

import org.nasdanika.drawio.style.LineStyle;

public abstract class LineStyleImpl extends StyleImpl implements LineStyle {

    private static final String DASHED = "dashed";
	private static final String STROKE_WIDTH = "strokeWidth";
	private static final String STROKE_COLOR = "strokeColor";

    @Override
    public String color() {
        return get(STROKE_COLOR);
    }

    @Override
    public LineStyle color(String value) {
        put(STROKE_COLOR, value);
        return this;
    }

    @Override
    public String width() {
        return get(STROKE_WIDTH);
    }

    @Override
    public LineStyle width(String value) {
        put(STROKE_WIDTH, value);
        return this;        
    }

    @Override
    public String dashed() {
        return get(DASHED);
    }

    @Override
    public LineStyle dashed(String value) {
        put(DASHED, value);
        return this;
    }

}
