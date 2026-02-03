package org.nasdanika.drawio.style;

/**
 * Line-related styling (stroke) used for borders and edges.
 */
public interface LineStyle extends Style {
	
    String color();

    LineStyle color(String value);

    String width();

    LineStyle width(String value);

    String dashed();

    LineStyle dashed(String value);
    
}
