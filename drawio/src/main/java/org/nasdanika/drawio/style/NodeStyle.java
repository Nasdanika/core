package org.nasdanika.drawio.style;

/**
 * Style for node (vertex) shapes.
 */
public interface NodeStyle extends LineStyle {
	
    String backgroundColor();

    NodeStyle backgroundColor(String value);
        
    @Override
    NodeStyle color(String value);

    @Override
    NodeStyle width(String value);

    @Override
    NodeStyle dashed(String value);    
    
}
