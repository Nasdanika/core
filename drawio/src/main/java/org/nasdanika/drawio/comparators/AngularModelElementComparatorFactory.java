package org.nasdanika.drawio.comparators;

import java.util.Objects;

import org.nasdanika.common.Util;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.ElementComparator;
import org.nasdanika.drawio.ElementComparator.Factory;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Point;
import org.nasdanika.drawio.Rectangle;

public class AngularModelElementComparatorFactory implements Factory {
	
	public static final String COUNTERCLOCKWISE = "counterclockwise";
	public static final String CLOCKWISE = "clockwise";

	public static Rectangle getAbsoluteGeometry(Node node) {
		ModelElement parent = node.getParent();
		if (parent instanceof Node) {
			Rectangle parentAbsoluteGeometry = getAbsoluteGeometry((Node) parent);
			Rectangle nodeGeometry = node.getGeometry();
			if (nodeGeometry == null) {
				return parentAbsoluteGeometry;
			}
			if (parentAbsoluteGeometry == null) {
				return nodeGeometry;
			}
			return new Rectangle() {

				@Override
				public org.w3c.dom.Element getElement() {
					throw new UnsupportedOperationException();
				}

				@Override
				public double getX() {
					return parentAbsoluteGeometry.getX() + nodeGeometry.getX();
				}

				@Override
				public double getY() {
					return parentAbsoluteGeometry.getY() + nodeGeometry.getY();
				}

				@Override
				public void setX(double x) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void setY(double y) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void setLocation(double x, double y) {
					throw new UnsupportedOperationException();
				}

				@Override
				public double getWidth() {
					return nodeGeometry.getWidth();
				}

				@Override
				public double getHeight() {
					return nodeGeometry.getHeight();
				}

				@Override
				public void setWidth(double width) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void setHeight(double height) {
					throw new UnsupportedOperationException();
				}

				@Override
				public void setBounds(double x, double y, double width, double height) {
					throw new UnsupportedOperationException();
				}
				
				@Override
				public String toString() {
					return super.toString() + "[" + getX() + ", " + getY() + ", " + getWidth() + ", " + getHeight() + "]";
				}
				
			};
		}
		return node.getGeometry();
	}
	
	public static Point getCenter(Rectangle rectangle) {
		return new Point() {

			@Override
			public org.w3c.dom.Element getElement() {
				throw new UnsupportedOperationException();
			}

			@Override
			public double getX() {
				return rectangle.getX() + rectangle.getWidth() / 2.0;
			}

			@Override
			public double getY() {
				return rectangle.getY() + rectangle.getHeight() / 2.0;
			}

			@Override
			public void setX(double x) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void setY(double y) {
				throw new UnsupportedOperationException();
			}

			@Override
			public void setLocation(double x, double y) {
				throw new UnsupportedOperationException();
			}
			
			@Override
			public String toString() {
				return super.toString() + "[" + getX() + ", " + getY() + "]";
			}
			
		};
	}
	
	/**
	 * @param from
	 * @param to
	 * @return An angle for a line from the parent's center to child's center. NaN if the angle cannot be computed for some reason.
	 */
	public static double angle(Node from, Node to) {
		if (from == null || to == null) {
			return Double.NaN;
		}
		Rectangle parentGeometry = getAbsoluteGeometry(from);
		if (parentGeometry == null) {
			return Double.NaN;
		}		
		Point parentCenter = getCenter(parentGeometry);
		
		Rectangle childGeometry = getAbsoluteGeometry(to);
		if (childGeometry == null) {
			return Double.NaN;
		}
		Point childCenter = getCenter(childGeometry);		
		double deltaX = childCenter.getX() - parentCenter.getX();
		double deltaY = childCenter.getY() - parentCenter.getY();
		if (Math.abs(deltaX) <= Double.MIN_VALUE) {
			return Math.PI * (deltaY > 0 ? 1.5 : 0.5);
		}
		if (Math.abs(deltaY) <= Double.MIN_VALUE) {
			return deltaX < 0 ? Math.PI : 0;
		}		
		double ret = Math.atan(-deltaY / deltaX);
		if (deltaX < 0) {
			ret += Math.PI;
		}
		return ret;
	}

	@Override
	public ElementComparator create(String type, String config, Element parent) {
		if (parent instanceof Node) {
			return new ElementComparator() {
				
				@Override
				public int compare(Element o1, Element o2) {				
					if (Objects.equals(o1, o2)) {
						return 0;
					}
					
					if (o1 instanceof Node && o2 instanceof Node) {
						double angle1 = angle((Node) parent, (Node) o1);
						double angle2 = angle((Node) parent, (Node) o2);
						if (angle1 == angle2) {
							return 0;
						}
						double baseAngle = Util.isBlank(config) ? Math.PI / 2.0 : Math.toRadians(Double.parseDouble(config));
						double delta1 = angle1 - baseAngle;
						if (delta1 < 0) {
							delta1 += 2.0 * Math.PI;
						}
						double delta2 = angle2 - baseAngle;
						if (delta2 < 0) {
							delta2 += 2.0 * Math.PI;
						}
						int cmp = delta1 < delta2 ? -1 : 1;
						return CLOCKWISE.equals(type) ? -cmp : cmp;
					}
					
					return o1.hashCode() - o2.hashCode();
				}
				
			};
		} 
		return null;
	}

	@Override
	public boolean isForType(String type) {
		return CLOCKWISE.equals(type) || COUNTERCLOCKWISE.equals(type);
	}

}
