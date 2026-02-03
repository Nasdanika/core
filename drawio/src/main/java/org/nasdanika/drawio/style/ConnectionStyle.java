package org.nasdanika.drawio.style;

/**
 * Style for connections (edges). Extends {@link LineStyle}.
 */
public interface ConnectionStyle extends LineStyle {
	
    String startArrow();

    ConnectionStyle startArrow(String value);

    String endArrow();

    ConnectionStyle endArrow(String value);

    Arrow startArrowEnum();

    ConnectionStyle startArrow(Arrow arrow);

    Arrow endArrowEnum();

    ConnectionStyle endArrowEnum(Arrow arrow);
    
    /**
     * Style of the edge, e.g. orthogonalEdgeStyle.
     */
    String edgeStyle();

    ConnectionStyle edgeStyle(String value);
    
    @Override
    ConnectionStyle color(String value);

    @Override
    ConnectionStyle width(String value);

    @Override
    ConnectionStyle dashed(String value);
    
    @Override
    ConnectionStyle opacity(String value);
    
    @Override
    ConnectionStyle opacity(int value);

    @Override
    ConnectionStyle rounded(boolean value);
	
    @Override
    ConnectionStyle shadow(boolean value);    
    
}
