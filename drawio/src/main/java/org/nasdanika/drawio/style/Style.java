package org.nasdanika.drawio.style;

import java.util.Map;

/**
 * Base style interface extending Map<String,String>.
 */
public interface Style extends Map<String,String> {
	
    String opacity();

    Style opacity(String opacity);
    
    Style opacity(int opacity);

    Style rounded(boolean rounded);
	
    boolean rounded();

    Style shadow(boolean shadow);
	
    boolean shadow();
    
    Style enumerate(boolean enumerate);
	
    boolean enumerate();
    
    String enumerateValue();

    Style enumerateValue(String enumerateValue);

}
