package org.nasdanika.drawio.impl;

import java.util.Map;

import org.nasdanika.common.Util;
import org.nasdanika.drawio.ConnectionPoint;
import org.w3c.dom.Element;

class ConnectionPointImpl implements ConnectionPoint {
	
	private static final String X_STYLE_KEY = "X";
	private static final String DX_STYLE_KEY = "Dx";
	private static final String Y_STYLE_KEY = "Y";
	private static final String DY_STYLE_KEY = "Dy";	
	
	private Map<String, String> style;
	private String prefix;

	ConnectionPointImpl(String prefix) {
		this.style = connectionImpl.getStyle();
		this.prefix = prefix;
	}

	@Override
	public Element getElement() {
		return null; // No backing element
	}

	@Override
	public double getX() {
		String xstr = style.get(prefix + X_STYLE_KEY);
		return Util.isBlank(xstr) ? Double.NaN : Double.parseDouble(xstr);
	}

	@Override
	public double getY() {
		String ystr = style.get(prefix + Y_STYLE_KEY);
		return Util.isBlank(ystr) ? Double.NaN : Double.parseDouble(ystr);
	}

	@Override
	public void setX(double x) {
		if (Double.isNaN(x)) {
			style.remove(prefix + X_STYLE_KEY);
			style.remove(prefix + DX_STYLE_KEY);
		} else {
			style.put(prefix + X_STYLE_KEY, String.valueOf(x));
			if (!style.containsKey(DX_STYLE_KEY)) {
				setDx(0);
			}
		}			
	}

	@Override
	public void setY(double y) {
		if (Double.isNaN(y)) {
			style.remove(prefix + Y_STYLE_KEY);
			style.remove(prefix + DY_STYLE_KEY);
		} else {
			style.put(prefix + Y_STYLE_KEY, String.valueOf(y));
			if (!style.containsKey(DY_STYLE_KEY)) {
				setDy(0);
			}
		}			
	}

	@Override
	public double getDx() {
		String dxstr = style.get(prefix + DX_STYLE_KEY);
		return Util.isBlank(dxstr) ? Double.NaN : Double.parseDouble(dxstr);
	}

	@Override
	public double getDy() {
		String dystr = style.get(prefix + DY_STYLE_KEY);
		return Util.isBlank(dystr) ? Double.NaN : Double.parseDouble(dystr);
	}

	@Override
	public void setDx(double dx) {
		style.put(prefix + DX_STYLE_KEY, String.valueOf(dx));
	}

	@Override
	public void setDy(double dy) {
		style.put(prefix + DY_STYLE_KEY, String.valueOf(dy));
	}
	
}