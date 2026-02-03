package org.nasdanika.drawio.style.impl;

import org.nasdanika.drawio.style.Arrow;
import org.nasdanika.drawio.style.ConnectionStyle;

public abstract class ConnectionStyleImpl extends LineStyleImpl implements ConnectionStyle {

    private static final String END_ARROW = "endArrow";
	private static final String START_ARROW = "startArrow";
    private static final String EDGE_STYLE = "edgeStyle";

	@Override
    public String startArrow() {
        return get(START_ARROW);
    }

    @Override
    public ConnectionStyle startArrow(String value) {
        put(START_ARROW, value);
        return this;
    }

    @Override
    public String endArrow() {
        return get(END_ARROW);
    }

    @Override
    public ConnectionStyle endArrow(String value) {
        put(END_ARROW, value);
        return this;
    }

    @Override
    public Arrow startArrowEnum() {
        return Arrow.fromString(startArrow());
    }

    @Override
    public ConnectionStyle startArrow(Arrow arrow) {
        startArrow(arrow == null ? null : arrow.value());
        return this;
    }

    @Override
    public Arrow endArrowEnum() {
        return Arrow.fromString(endArrow());
    }

    @Override
    public ConnectionStyle endArrowEnum(Arrow arrow) {
        endArrow(arrow == null ? null : arrow.value());
        return this;
    }
    
    @Override
    public String edgeStyle() {
        return get(EDGE_STYLE);
    }

    @Override
    public ConnectionStyle edgeStyle(String value) {
        put(EDGE_STYLE, value);
        return this;
    }
    
    @Override
    public ConnectionStyle color(String value) {
    	return (ConnectionStyle) super.color(value);
    }

    @Override
    public ConnectionStyle width(String value) {
    	return (ConnectionStyle) super.width(value);
    }

    @Override
    public ConnectionStyle dashed(String value) {
    	return (ConnectionStyle) super.dashed(value);
    }
    
    @Override
    public ConnectionStyle opacity(int opacity) {
    	return (ConnectionStyle) super.opacity(opacity);
    }
    
    @Override
    public ConnectionStyle opacity(String value) {
    	return (ConnectionStyle) super.opacity(value);
    }
    
    @Override
    public ConnectionStyle rounded(boolean rounded) {
    	return (ConnectionStyle) super.rounded(rounded);
    }
    
    @Override
    public ConnectionStyle shadow(boolean shadow) {
    	return (ConnectionStyle) super.shadow(shadow);
    }
    
}
