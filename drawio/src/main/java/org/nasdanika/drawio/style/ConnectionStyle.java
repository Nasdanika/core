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

    ConnectionStyle startArrowEnum(Arrow arrow);

    Arrow endArrowEnum();

    ConnectionStyle endArrowEnum(Arrow arrow);
    
    @Override
    ConnectionStyle color(String value);

    @Override
    ConnectionStyle width(String value);

    @Override
    ConnectionStyle dashed(String value);
    
}
