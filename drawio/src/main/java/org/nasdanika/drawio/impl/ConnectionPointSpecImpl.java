package org.nasdanika.drawio.impl;

import java.util.Objects;

import org.nasdanika.drawio.ConnectionPointSpec;

/**
 * {@link ConnectionPointSpec} value object with hashCode and equals
 */
class ConnectionPointSpecImpl implements ConnectionPointSpec {
	
	private double x;
	private double y;
	private double dx;
	private double dy;
	private boolean perimeter;
	
	public ConnectionPointSpecImpl() {
		
	}
	
	public ConnectionPointSpecImpl(ConnectionPointSpec spec) {
		setSpec(spec);
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setX(double x) {
		this.x = x;		
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public double getDx() {
		return dx;
	}

	@Override
	public double getDy() {
		return dy;
	}

	@Override
	public void setDx(double dx) {
		this.dx = dx;
	}

	@Override
	public void setDy(double dy) {
		this.dy = dy;
	}

	@Override
	public boolean isPerimeter() {
		return perimeter;
	}

	@Override
	public void setPerimeter(boolean perimeter) {
		this.perimeter = perimeter;		
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, dx, y, dy, perimeter);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConnectionPointSpecImpl other = (ConnectionPointSpecImpl) obj;
		return specEquals(other);
	}		

}
