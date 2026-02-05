package org.nasdanika.drawio.style;

/**
 * Style for connections (edges). Extends {@link LineStyle}.
 */
public interface ConnectionStyle extends LineStyle {
		
    boolean startFill();

    ConnectionStyle startFill(boolean startFill);
	
    boolean endFill();

    ConnectionStyle endFill(boolean endFill);	
	
    String startArrow();

    ConnectionStyle startArrow(String arrow);

    String endArrow();

    ConnectionStyle endArrow(String arrow);

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
    
    @Override
    ConnectionStyle enumerate(boolean enumerate);
	
    @Override
    ConnectionStyle enumerateValue(String enumerateValue);    
    
}
