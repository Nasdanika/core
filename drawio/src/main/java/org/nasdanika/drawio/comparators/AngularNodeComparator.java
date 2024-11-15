package org.nasdanika.drawio.comparators;

import java.util.Comparator;
import java.util.Objects;

import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Point;
import org.nasdanika.drawio.Rectangle;

/**
 * Sorts {@link Node}s by the angle of a line between the node center and node's logical parent center.  
 * @author Pavel
 *
 */
public class AngularNodeComparator implements Comparator<Node> {

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

	private Node parent;
	private Double baseAngle;
	
	/**
	 * 
	 * @param parent Parent node
	 * @param clockwise If true, the comparison is clockwise. I.e. if the base angle is 12 o'clock then a node at 2 o'clock would be greater than a node at 1 o'clock.
	 * @param baseAngle Base angle in degrees, defaults to 90 if null.
	 */
	public AngularNodeComparator(Node parent, Double baseAngle) {
		this.parent = parent;
		this.baseAngle = baseAngle == null ? Math.PI / 2.0 : baseAngle;
	}

	@Override
	public int compare(Node o1, Node o2) {
		if (Objects.equals(o1, o2)) {
			return 0;
		}
				
		if (o1 == null) {
			return 1;
		}
		
		if (o2 == null) {
			return -1;
		}
				
		if (Objects.equals(o1.getModel().getPage(), parent.getModel().getPage()) && Objects.equals(o2.getModel().getPage(), parent.getModel().getPage())) {		
			double angle1 = angle(parent, o1);
			double angle2 = angle(parent, o2);
			if (angle1 == angle2) {
				return 0;
			}
			double delta1 = angle1 - baseAngle;
			if (delta1 < 0) {
				delta1 += 2.0 * Math.PI;
			}
			double delta2 = angle2 - baseAngle;
			if (delta2 < 0) {
				delta2 += 2.0 * Math.PI;
			}
			return delta1 < delta2 ? -1 : 1;
		}
		throw new IllegalArgumentException("Nodes belong to different pages");
	}

}
