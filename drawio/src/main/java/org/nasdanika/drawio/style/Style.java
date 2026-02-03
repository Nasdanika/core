package org.nasdanika.drawio.style;

import java.util.Map;

/**
 * Base style interface extending Map<String,String>.
 */
public interface Style extends Map<String,String> {
	
    String opacity();

    Style opacity(String value);
    
    Style opacity(int value);

    Style rounded(boolean value);
	
    boolean rounded();

    Style shadow(boolean value);
	
    boolean shadow();

}
