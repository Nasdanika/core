package org.nasdanika.drawio.style.impl;

import org.nasdanika.common.DelimitedStringMap;
import org.nasdanika.drawio.style.Style;

public abstract class StyleImpl extends DelimitedStringMap implements Style {

	protected StyleImpl() {
		super(";", "=");
	}
	
}
