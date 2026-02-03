package org.nasdanika.drawio.style.impl;

import org.nasdanika.drawio.style.NodeStyle;

public abstract class NodeStyleImpl extends LineStyleImpl implements NodeStyle {

    private static final String FILL_COLOR = "fillColor";
    private static final String SHAPE = "shape";
    private static final String CONTAINER = "container";
    private static final String COLLAPSIBLE = "collapsible";
    private static final String VERTICAL_ALIGN = "verticalAlign";
    private static final String ALIGN = "align";
    private static final String FONT_SIZE = "fontSize";
    private static final String FONT_COLOR = "fontColor";
    private static final String FONT_STYLE = "fontStyle";
    private static final String LABEL_BG_COLOR = "labelBackgroundColor";
    private static final String LABEL_BORDER_COLOR = "labelBorderColor";
    private static final String ROTATION = "rotation";

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

    @Override
    public String shape() {
        return get(SHAPE);
    }

    @Override
    public NodeStyle shape(String value) {
        put(SHAPE, value);
        return this;
    }

    @Override
    public String verticalAlign() {
        return get(VERTICAL_ALIGN);
    }

    @Override
    public NodeStyle verticalAlign(String value) {
        put(VERTICAL_ALIGN, value);
        return this;
    }

    @Override
    public String align() {
        return get(ALIGN);
    }

    @Override
    public NodeStyle align(String value) {
        put(ALIGN, value);
        return this;
    }

    @Override
    public String fontSize() {
        return get(FONT_SIZE);
    }

    @Override
    public NodeStyle fontSize(String value) {
        put(FONT_SIZE, value);
        return this;
    }

    @Override
    public String fontColor() {
        return get(FONT_COLOR);
    }

    @Override
    public NodeStyle fontColor(String value) {
        put(FONT_COLOR, value);
        return this;
    }

    @Override
    public String fontStyle() {
        return get(FONT_STYLE);
    }

    @Override
    public NodeStyle fontStyle(String value) {
        put(FONT_STYLE, value);
        return this;
    }

    @Override
    public String labelBackgroundColor() {
        return get(LABEL_BG_COLOR);
    }

    @Override
    public NodeStyle labelBackgroundColor(String value) {
        put(LABEL_BG_COLOR, value);
        return this;
    }

    @Override
    public String labelBorderColor() {
        return get(LABEL_BORDER_COLOR);
    }

    @Override
    public NodeStyle labelBorderColor(String value) {
        put(LABEL_BORDER_COLOR, value);
        return this;
    }

    @Override
    public String rotation() {
        return get(ROTATION);
    }

    @Override
    public NodeStyle rotation(String value) {
        put(ROTATION, value);
        return this;
    }
        
    @Override
    public NodeStyle opacity(int opacity) {
    	return (NodeStyle) super.opacity(opacity);
    }
    
    @Override
    public NodeStyle opacity(String value) {
    	return (NodeStyle) super.opacity(value);
    }
    
    @Override
    public NodeStyle rounded(boolean rounded) {
    	return (NodeStyle) super.rounded(rounded);
    }
    
    @Override
    public NodeStyle shadow(boolean shadow) {
    	return (NodeStyle) super.shadow(shadow);
    }
    
    @Override
    public boolean collapsible() {
        return "1".equals(get(COLLAPSIBLE));
    }
    
    @Override
    public NodeStyle collapsible(boolean collapsible) {
		if (collapsible) {
			put(COLLAPSIBLE, "1");
		}
		remove(COLLAPSIBLE);
		return this;
    }
    
    @Override
    public boolean container() {
        return "1".equals(get(CONTAINER));
    }
    
    @Override
    public NodeStyle container(boolean container) {
		if (container) {
			put(CONTAINER, "1");
		}
		remove(CONTAINER);
		return this;
    }
    
    
}
