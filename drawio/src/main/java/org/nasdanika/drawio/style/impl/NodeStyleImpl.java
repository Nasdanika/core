package org.nasdanika.drawio.style.impl;

import org.nasdanika.drawio.style.NodeStyle;

public abstract class NodeStyleImpl extends LineStyleImpl implements NodeStyle {

    private static final String FILL_COLOR = "fillColor";

    @Override
    public String backgroundColor() {
        return get(FILL_COLOR);
    }

    @Override
    public NodeStyle backgroundColor(String value) {
        put(FILL_COLOR, value);
        return this;
    }    
    
    @Override
    public NodeStyle color(String value) {
    	return (NodeStyle) super.color(value);
    }

    @Override
    public NodeStyle width(String value) {
    	return (NodeStyle) super.width(value);
    }

    @Override
    public NodeStyle dashed(String value) {
    	return (NodeStyle) super.dashed(value);
    }    
    
}
