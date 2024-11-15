package org.nasdanika.drawio.model.comparators;

import java.util.Comparator;
import java.util.Objects;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.drawio.model.Geometry;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.drawio.model.Node;
import org.nasdanika.drawio.model.Point;

/**
 * Sorts {@link Node}s by the angle of a line between the node center and node's logical parent center.  
 * @author Pavel
 *
 */
public class AngularNodeComparator implements Comparator<Node> {

	public static Geometry getAbsoluteGeometry(Node node) {
		EObject parent = node.eContainer();
		if (parent instanceof Node) {
			Geometry parentAbsoluteGeometry = getAbsoluteGeometry((Node) parent);
			Geometry nodeGeometry = node.getGeometry();
			if (nodeGeometry == null) {
				return parentAbsoluteGeometry;
			}
			if (parentAbsoluteGeometry == null) {
				return nodeGeometry;
			}
			Geometry absoluteGeometry = ModelFactory.eINSTANCE.createGeometry();
			absoluteGeometry.setX(parentAbsoluteGeometry.getX() + nodeGeometry.getX());
			absoluteGeometry.setY(parentAbsoluteGeometry.getY() + nodeGeometry.getY());
			absoluteGeometry.setWidth(nodeGeometry.getWidth());
			absoluteGeometry.setHeight(nodeGeometry.getHeight());
		}
		return node.getGeometry();
	}
	
	public static Point getCenter(Geometry geometry) {
		Point ret = ModelFactory.eINSTANCE.createPoint();
		ret.setX(geometry.getX() + geometry.getWidth() / 2.0);
		ret.setY(geometry.getY() + geometry.getHeight() / 2.0);
		return ret;
	}
	
	/**
	 * @param from
	 * @param to
	 * @return An angle for a line from the parent's center to child's center. NaN if the angle cannot be computed for some reason.
	 */
	public static double angle(Node from, Node to) {
		if (from == null || to == null || !Objects.equals(from.getPage(), to.getPage())) {
			return Double.NaN;
		}

		Geometry parentGeometry = getAbsoluteGeometry(from);
		if (parentGeometry == null) {
			return Double.NaN;
		}		
		Point parentCenter = getCenter(parentGeometry);
		
		Geometry childGeometry = getAbsoluteGeometry(to);
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
				
		if (Objects.equals(o1.getPage(), parent.getPage()) && Objects.equals(o2.getPage(), parent.getPage())) {		
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
