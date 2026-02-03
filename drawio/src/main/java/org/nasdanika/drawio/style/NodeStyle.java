package org.nasdanika.drawio.style;

/**
 * Style for node (vertex) shapes.
 */
public interface NodeStyle extends LineStyle {
	
    String backgroundColor();

    NodeStyle backgroundColor(String value);
    
    String shape();

    NodeStyle shape(String value);

    String verticalAlign();

    NodeStyle verticalAlign(String value);

    String align();

    NodeStyle align(String value);

    String fontSize();

    NodeStyle fontSize(String value);

    String fontColor();

    NodeStyle fontColor(String value);

    String fontStyle();

    NodeStyle fontStyle(String value);

    String labelBackgroundColor();

    NodeStyle labelBackgroundColor(String value);

    String labelBorderColor();

    NodeStyle labelBorderColor(String value);

    String rotation();

    NodeStyle rotation(String value);
        
    @Override
    NodeStyle color(String value);

    @Override
    NodeStyle width(String value);

    @Override
    NodeStyle dashed(String value);    
        
    @Override
    NodeStyle opacity(String value);
    
    @Override
    NodeStyle opacity(int value);

    @Override
    NodeStyle rounded(boolean value);
	
    @Override
    NodeStyle shadow(boolean value);
    
    NodeStyle container(boolean container);
    
    boolean container();
    
    NodeStyle collapsible(boolean collapsible);
    
    boolean collapsible();
    
}
