package org.nasdanika.drawio;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.jgrapht.alg.drawing.FRLayoutAlgorithm2D;
import org.jgrapht.alg.drawing.LayoutAlgorithm2D;
import org.jgrapht.alg.drawing.model.Box2D;
import org.jgrapht.alg.drawing.model.LayoutModel2D;
import org.jgrapht.alg.drawing.model.MapLayoutModel2D;
import org.jgrapht.alg.drawing.model.Point2D;
import org.jgrapht.alg.drawing.model.Points;
import org.jgrapht.graph.DefaultUndirectedGraph;
import org.nasdanika.common.DelimitedStringMap;

public final class Util {
	
	private Util() {}
			
	/**
	 * Lays out the diagram so nodes do not overlap.
	 * @param root
	 * @param gridSize
	 */
	public static void layout(Root root, int gridSize) {
		List<Node> topLevelNodes = root.getLayers().stream().flatMap(layer -> layer.getElements().stream()).filter(Node.class::isInstance).map(Node.class::cast).toList();
		layout(topLevelNodes, new Point(gridSize, gridSize), down -> createOffsetGenerator(gridSize, down));
	}		
	
	/**
	 * Computes sizes and positions of a collection of elements so they don't overlap
	 * @param elements
	 * @return
	 */
	public static Map<Node, Rectangle> layout(
			Collection<Node> nodes, 
			Point offset,
			Function<Boolean, Supplier<Point>> offsetGeneratorProvider /*,			
			BiConsumer<Node, Rectangle> geometryConsumer */) {
		
		Map<Node, Rectangle> ret = new HashMap<>();
		for (Node node: nodes) {
			ret.put(node, createElementRectangle(node, offset, offsetGeneratorProvider /*, geometryConsumer */));
		}
		
		// Sort
		List<Map.Entry<Node, Rectangle>> remaining = new ArrayList<>(ret.entrySet());
		List<Map.Entry<Node, Rectangle>> sorted = new ArrayList<>();
		Map<Rectangle,Boolean> downMap = new IdentityHashMap<>();
		while (!remaining.isEmpty()) {
			Entry<Node, Rectangle> candidate = null;
			int candidateAffinity = 0;
			boolean isCandidateDown = false;
			for (Entry<Node, Rectangle> e: remaining) {
				Collection<Map.Entry<Node, Rectangle>> els;
				if (sorted.isEmpty()) {
					els = new ArrayList<>(remaining);
					els.remove(e);
				} else {
					els = sorted;
				}
				int[] eAffinity = affinity(e.getKey(), els.stream().map(Map.Entry::getKey).toList());
				int teAffinity = eAffinity[0] + eAffinity[1];
				if (candidate == null || (candidateAffinity < teAffinity)) {
					candidate = e;
					candidateAffinity = teAffinity;
					isCandidateDown = eAffinity[1] > eAffinity[0];
				}		
			}
			sorted.add(candidate);
			downMap.put(candidate.getValue(), isCandidateDown);
			remaining.remove(candidate);
		}				
		
		position(sorted, new HashMap<>(), offset, offsetGeneratorProvider, downMap::get);
		
		double minX = Integer.MAX_VALUE;
		double minY = Integer.MAX_VALUE;
		for (Rectangle rectangle: ret.values()) {			
			minX = Math.min(rectangle.getX(), minX);
			minY = Math.min(rectangle.getY(), minY);
		}
		
		for (Rectangle rectangle: ret.values()) {
			rectangle.translate((int) (-minX + offset.getX()), (int) (-minY + offset.getY()));
		}	
		
		for (Entry<Node, Rectangle> e: ret.entrySet()) {
			Rectangle rectangle = e.getValue();
			e.getKey().getGeometry().setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}	
		
		return ret;
	}
	
	private static boolean isSameOrAncestor(ModelElement child, ModelElement ancestor) {
		if (child == null) {
			return false;
		}
		if (child.equals(ancestor)) {
			return true;
		}
		return isSameOrAncestor(child.getParent(), ancestor);
	}
	
	/**
	 * Computes affinity of a given diagram element to a collection of other elements.
	 * Affinity is a two-elements array with the first element containing a total number of 
	 * connections from the first argument element to the second argument elements recursively including child elements on both sides.
	 * The second array element contains a total number of connections from the second argument elements to the first argument element also recursively
	 * including child elements on both sides. Affinity is used to layout elements in a top-down   
	 * @param element
	 * @param elements
	 * @return
	 */
	private static int[] affinity(ModelElement element, Collection<? extends ModelElement> elements) {
		int[] outgoing = { 0 };
		element.accept(e -> {
			if (e instanceof Connection) {
				Node target = ((Connection) e).getTarget();
				for (ModelElement oe: elements) {
					if (isSameOrAncestor(target, oe)) {
						++outgoing[0];
					}
				}
			}
		}, ConnectionBase.SOURCE);
		
		int[] inbound = { 0 };
		element.accept(e -> {
			if (e instanceof Connection) {
				Node source = ((Connection) e).getSource();
				for (ModelElement oe: elements) {
					if (isSameOrAncestor(source, oe)) {
						++inbound[0];
					}
				}
			}
		}, ConnectionBase.TARGET);
		
		return new int[] { outgoing[0], inbound[0] };
	}
	
	/**
	 * Sorts rectangles entry set by size and affinity to positioned offset rectangles.
	 * Then positions the first element so it doesn't overlap with the offset rectangles. 
	 * Then passes the remaining elements for positioning.
	 * @param rectangles
	 * @param offsetRectangles
	 */
	private static void position(
			List<Map.Entry<Node, Rectangle>> rectangles, 
			Map<Node, Rectangle> offsetRectangles,
			Point offset,
			Function<Boolean, Supplier<Point>> offsetGeneratorProvider,
			Predicate<Rectangle> downPredicate) {
		if (rectangles.isEmpty()) {
			return;
		}
		
		Entry<Node, Rectangle> firstEntry = rectangles.get(0);
		Rectangle firstRectangle = firstEntry.getValue();
		position(firstRectangle, offsetRectangles.values(), offsetGeneratorProvider, downPredicate.test(firstRectangle)); 
		Rectangle offsetRectangle = new Rectangle(firstRectangle);
		offsetRectangle.translate((int) -offset.getX(), (int) - offset.getY());
		offsetRectangle.grow( 2 * (int) offset.getX(), 2 * (int) offset.getY());
		offsetRectangles.put(firstEntry.getKey(), offsetRectangle);			
		
		position(rectangles.subList(1, rectangles.size()), offsetRectangles, offset, offsetGeneratorProvider, downPredicate);
	}

	/**
	 * Positions the rectangle so it doesn't overlap with existing rectangles.
	 * @param element
	 * @param geometry
	 * @param rectangle
	 * @param rectangles
	 */
	private static void position(
			Rectangle rectangle, 
			Collection<Rectangle> offsetRectangles,
			Function<Boolean, Supplier<Point>> offsetGeneratorProvider,
			boolean down) {
		
		if (offsetRectangles.isEmpty()) {
			return;
		}
		
		// Start at the center of offset rectangles union
		Rectangle union = null;
		for (Rectangle offsetRectangle: offsetRectangles) {
			if (union == null) {
				union = new Rectangle(offsetRectangle);
			} else {
				union.add(offsetRectangle); 
			}
		}
		double unionCenter = (union.getX() + union.getWidth())/2;
		double unionMiddle = (union.getY() + union.getHeight())/2;

		double rectangleCenter = (rectangle.getX() + rectangle.getWidth())/2;
		double rectangleMiddle = (rectangle.getY() + rectangle.getHeight())/2;
		
		rectangle.translate((int) (unionCenter - rectangleCenter), (int) (unionMiddle - rectangleMiddle));
	
		Supplier<Point> offsetGenerator = offsetGeneratorProvider.apply(down);
		Z: for (Point offset = offsetGenerator.get(); offset != null; offset = offsetGenerator.get()) {
			rectangle.translate((int) offset.getX(), (int) offset.getY());
			for (Rectangle offsetRectangle: offsetRectangles) {
				if (!rectangle.intersection(offsetRectangle).isEmpty()) {
					continue Z;
				}
			}
			break; // No intersection.
		}
	}
	
	/**
	 * @param gridSize difference x, y, or both, between offset points in pixels,
	 * @param down Direction of traversing.  
	 * @return generator of a sequence of points which are offset from the initial point for positioning of a rectangle. 
	 * This implementation returns a sequence of offsets which form concentric half-circles up or down.
	 */
	public static Supplier<Point> createOffsetGenerator(int gridSize, boolean down) {
		return new Supplier<Point>() {

			private Point lastPosition = new Point(0, 0);
			private Set<Point> visited = new HashSet<>();

			@Override
			public Point get() {
				if (visited.add(lastPosition)) {
					return lastPosition;
				}
				List<Point> newPositions = new ArrayList<>();					
				newPositions.add(new Point(lastPosition.x + gridSize, lastPosition.y));					
				newPositions.add(new Point(lastPosition.x, lastPosition.y + gridSize));					
				newPositions.add(new Point(lastPosition.x + gridSize, lastPosition.y + gridSize));					
				newPositions.add(new Point(lastPosition.x + gridSize, lastPosition.y - gridSize));					
				newPositions.add(new Point(lastPosition.x - gridSize, lastPosition.y));					
				newPositions.add(new Point(lastPosition.x, lastPosition.y - gridSize));					
				newPositions.add(new Point(lastPosition.x - gridSize, lastPosition.y - gridSize));
				newPositions.add(new Point(lastPosition.x - gridSize, lastPosition.y + gridSize));
				newPositions.sort((a, b) -> a.x*a.x + a.y*a.y - b.x*b.x - b.y*b.y);
				for (Point newPosition: newPositions) {
					if ((down && newPosition.y >= 0 || !down && newPosition.y <=0) && visited.add(newPosition)) {
						Point offset = new Point(newPosition.x - lastPosition.x, newPosition.y - lastPosition.y);
						lastPosition = newPosition;
						return offset;
					}
				}
				throw new IllegalStateException("Nowhere to move");
			}
			
		};
	}

	/**
	 * Creates a rectangle for the diagram element and its children at position 0, 0.
	 * @param element
	 * @param offset
	 * @return
	 */
	private static Rectangle createElementRectangle(
			Node node, 
			Point offset, 
			Function<Boolean, Supplier<Point>> offsetGeneratorProvider) {
		
		org.nasdanika.drawio.Rectangle nodeGeometry = node.getGeometry();
		Rectangle rectangle = new Rectangle(0, 0, (int) nodeGeometry.getWidth(), (int) nodeGeometry.getHeight());
		List<Node> children = node.getElements().stream().filter(Node.class::isInstance).map(Node.class::cast).toList();
		if (!children.isEmpty()) {
			Point childOffset = new Point((int) offset.getX(), (int) offset.getY() + 30);
			Map<Node, Rectangle> childGeometry = layout(children, childOffset, offsetGeneratorProvider);
			for (ModelElement child: children) {
				Rectangle childRectangle = childGeometry.get(child);
				rectangle.add(childRectangle);
			}
			rectangle.grow((int) offset.getX(), (int) offset.getY());
		}
		return rectangle;
	}
	
	// Mappers
	
	private static Collection<? extends Element> getChildren(Element element) {
		if (element instanceof Document) {
			return ((Document) element).getPages();
		}
		if (element instanceof Page) {
			return Collections.singleton(((Page) element).getModel());
		}
		if (element instanceof Model) {
			return Collections.singleton(((Model) element).getRoot());
		}
		if (element instanceof Root) {
			return ((Root) element).getLayers();
		}
		if (element instanceof Layer) {
			return ((Layer) element).getElements();
		}
		return Collections.emptySet();
	}
	
	
	public static Function<Element, Stream<? extends Element>> childrenMapper(Predicate<Element> predicate) {
		return new Function<Element, Stream<? extends Element>>() {
						
			@Override
			public Stream<? extends Element> apply(Element element) {
				Stream<? extends Element> ret = getChildren(element).stream();
				return predicate == null ? ret : ret.filter(predicate);
			}
			
		};
	}
	
	public static Function<Element, Stream<? extends Element>> childrenRecursiveMapper() {
		return new Function<Element, Stream<? extends Element>>() {
			
			@Override
			public Stream<? extends Element> apply(Element element) {
				return getChildren(element).stream().flatMap(childrenRecursiveMapper());
			}
			
		};
		
	}
	
	public static Stream<Element> childrenStream(Element element, Predicate<Element> predicate) {
		return Stream.of(element).flatMap(childrenMapper(predicate));
	}
	
	/**
	 * @param <T>
	 * @param visitor
	 * @param connectionBase Connection base for visiting linked pages.
	 * @return Visitor which passes itself to linked pages and adds linked pages' result to child results.
	 */
	public static <T> BiFunction<Element, Map<Element, T>, T> withLinkTargets(BiFunction<? super Element, Map<? extends Element, T>, T> visitor, ConnectionBase connectionBase) {
		return new BiFunction<Element, Map<Element, T>, T>() {
			
			Map<LinkTarget, T> results = new HashMap<>();

			@Override
			public T apply(Element element, Map<Element, T> childResults) {				
				if (element instanceof ModelElement) {
					LinkTarget linkTarget = ((ModelElement) element).getLinkTarget();
					if (linkTarget != null) {
						Map<Element, T> cr = new LinkedHashMap<>();
						if (childResults != null) {
							cr.putAll(childResults);
						}
						T linkTargetResult;
						if (results.containsKey(linkTarget)) {
							linkTargetResult = results.get(linkTarget);
						} else {
							linkTargetResult = linkTarget.accept(this, connectionBase);
						}
						if (linkTargetResult != null) {
							cr.put(linkTarget, linkTargetResult);
						}
						return visitor.apply(element, cr);
					}
				}
				return visitor.apply(element, childResults);
			}
			
		};
	}
	
	/**
	 * @param <T>
	 * @param visitor
	 * @param connectionBase Connection base for visiting linked pages.
	 * @return Visitor which passes itself to linked pages.
	 */
	public static <T> Consumer<Element> withLinkTargets(Consumer<Element> visitor, ConnectionBase connectionBase) {
		return new Consumer<Element>() {
			
			Collection<LinkTarget> visited = new HashSet<>();

			@Override
			public void accept(Element element) {				
				if (element instanceof ModelElement) {
					LinkTarget linkTarget = ((ModelElement) element).getLinkTarget();
					if (linkTarget != null && visited.add(linkTarget)) { // No double-visiting
						linkTarget.accept(this, connectionBase);
					}
				}
				visitor.accept(element);
			}
			
		};
	}
	
	
	/**
	 * @param <T>
	 * @param visitor
	 * @param connectionBase Connection base for visiting linked pages.
	 * @return Visitor which passes itself to connected elements (incoming and outgoing connnections for nodes and source and target nodes for connections) and linked pages and adds linked pages' result to child results.
	 */
	public static <T> BiFunction<Element, Map<Element, T>, T> traverser(BiFunction<? super Element, Map<? extends Element, T>, T> visitor, ConnectionBase connectionBase) {
		return new BiFunction<Element, Map<Element, T>, T>() {
			
			Map<Element, T> results = new HashMap<>();

			@Override
			public T apply(Element element, Map<Element, T> childResults) {
				if (results.containsKey(element)) {
					return results.get(element);
				}
				
				if (element instanceof Node) {
					Node node = (Node) element;
					for (Connection oc: node.getOutgoingConnections()) {
						if (!results.containsKey(oc)) {
							results.put(oc, oc.accept(this, connectionBase));
						}
					}
					for (Connection ic: node.getIncomingConnections()) {
						if (!results.containsKey(ic)) {
							results.put(ic, ic.accept(this, connectionBase));
						}
					}
				}
				if (element instanceof Connection) {
					Connection connection = (Connection) element;
					Node source = connection.getTarget();
					if (source != null && !results.containsKey(source)) {
						results.put(source, source.accept(this, connectionBase));
					}
					Node target = connection.getTarget();
					if (target != null && !results.containsKey(target)) {
						results.put(target, target.accept(this, connectionBase));
					}
				}
				
				if (element instanceof ModelElement) {
					ModelElement modelElement = (ModelElement) element;
					ModelElement parent = modelElement.getParent();
					if (parent != null && !results.containsKey(parent)) {
						results.put(parent, parent.accept(this, connectionBase));						
					}
					
					LinkTarget linkTarget = modelElement.getLinkTarget();
					if (linkTarget != null) {
						Map<Element, T> cr = new LinkedHashMap<>();
						if (childResults != null) {
							cr.putAll(childResults);
						}
						T linkTargetResult;
						if (results.containsKey(linkTarget)) {
							linkTargetResult = results.get(linkTarget);
						} else {
							linkTargetResult = linkTarget.accept(this, connectionBase);
						}
						if (linkTargetResult != null) {
							cr.put(linkTarget, linkTargetResult);
						}
						return visitor.apply(element, cr);
					}
				}								
				
				return visitor.apply(element, childResults);
			}
			
		};
	}
	
	/**
	 * @param <T>
	 * @param visitor
	 * @param connectionBase Connection base for visiting linked pages.
	 * @return Visitor which passes itself to connected elements (incoming and outgoing connnections for nodes and source and target nodes for connections) and linked pages and adds linked pages' result to child results.
	 */
	public static Consumer<Element> traverser(Consumer<Element> visitor, ConnectionBase connectionBase) {
		return new Consumer<Element>() {
			
			Collection<Element> visited = new HashSet<>();

			@Override
			public void accept(Element element) {
				if (element != null && visited.add(element)) {
					if (element instanceof Node) {
						Node node = (Node) element;
						for (Connection oc: node.getOutgoingConnections()) {
							oc.accept(this, connectionBase);
						}
						for (Connection ic: node.getIncomingConnections()) {
							ic.accept(this, connectionBase);
						}
					}
					if (element instanceof Connection) {
						Connection connection = (Connection) element;
						Node source = connection.getSource();
						if (source != null) {
							source.accept(this, connectionBase);
						}
						Node target = connection.getTarget();
						if (target != null) {
							target.accept(this, connectionBase);
						}
					}
					
					if (element instanceof ModelElement) {
						ModelElement modelElement = (ModelElement) element;
						ModelElement parent = modelElement.getParent();
						if (parent != null) {
							parent.accept(this, connectionBase);						
						}
						
						LinkTarget linkTarget = modelElement.getLinkTarget();
						if (linkTarget != null && visited.add(linkTarget)) { // No double-visiting
							linkTarget.accept(this, connectionBase);
						}
					}
					visitor.accept(element);
				}
			}
			
		};
	}
	
	/**
	 * Lays out using JGraphT algorithms
	 * @param elements
	 * @return
	 */
	public static void layout(
			Collection<Node> nodes, 
			LayoutAlgorithm2D<Node, Connection> layout,
			LayoutModel2D<Node> layoutModel) {
		// Using JGraphT for force layout
		DefaultUndirectedGraph<Node, Connection> dGraph = new DefaultUndirectedGraph<>(Connection.class);
		
		// Populating
		for (Node node: nodes) {
			dGraph.addVertex(node);
		}	
		
		for (Node node: nodes) {
			for (Connection connection: node.getOutgoingConnections()) {
				if (dGraph.getEdge(connection.getTarget(), node) == null) { // Not yet connected, connect
					dGraph.addEdge(node, connection.getTarget(), connection);
				}
			}
		}		
		
		layout.layout(dGraph, layoutModel);
		layoutModel.forEach(ne -> {
			Node node = ne.getKey();
			Point2D point = ne.getValue();
			org.nasdanika.drawio.Rectangle geometry = node.getGeometry();
			geometry.setX(point.getX());
			geometry.setY(point.getY());
		});
		
	}	
	
	/**
	 * Uses JGraphT {@link FRLayoutAlgorithm2D} to force layout the graph.
	 * @param graph
	 */
	public static void forceLayout(
			Root root, 
			double layoutWidth, 
			double layoutHeight) {
		
		List<Node> topLevelNodes = root.getLayers().stream().flatMap(layer -> layer.getElements().stream()).filter(Node.class::isInstance).map(Node.class::cast).toList();
		
		FRLayoutAlgorithm2D<Node, Connection> forceLayout = new FRLayoutAlgorithm2D<>() {
			
			@Override
			protected java.util.Map<Node,Point2D> calculateRepulsiveForces(org.jgrapht.Graph<Node, Connection> graph, org.jgrapht.alg.drawing.model.LayoutModel2D<Node> model) {
		        Point2D drawableArea = Point2D.of(
		        		model.getDrawableArea().getWidth(), 
		        		model.getDrawableArea().getHeight());
		        
		        double daLen = Points.length(drawableArea);
			
				Map<Node, Point2D> disp = super.calculateRepulsiveForces(graph, model);
				for (Node v : graph.vertexSet()) {
					Point2D vPos = model.get(v);
		            Point2D vDisp = Point2D.of(0d, 0d);
		            
		            int intersections = 0;
					for (Node u : graph.vertexSet()) {
						if (v.equals(u)) {
							continue;
						}
						Point2D uPos = model.get(u);
						if (intersects(v, vPos, u, uPos)) {
							++intersections;
							Point2D delta = Points.subtract(vPos, uPos);
							if (comparator.compare(vPos.getX(), uPos.getX()) == 0 && comparator.compare(vPos.getY(), uPos.getY()) == 0) {
								// At the same position - using hash code to position one of them under another
								delta = Point2D.of(0, v.hashCode() > u.hashCode() ? 10 : -10);
							}
							double deltaLength = Points.length(delta);	
							Point2D dispContribution = Points.scalarMultiply(delta, 1.0 / deltaLength); // Normalization
							vDisp = Points.add(vDisp, dispContribution);	
						}
					}
	
					if (intersections > 0) {						
						if (comparator.compare(vDisp.getX(), 0.0) == 0 && comparator.compare(vDisp.getY(), 0.0) == 0) {
							// Sum of the intersection forces is zero - do nothing.
							continue;
						}
						
						vDisp = Points.scalarMultiply(vDisp, daLen / Points.length(vDisp)); // Shall beat any attractive force
						disp.put(v, Points.add(vDisp, disp.get(v)));
					}
				}
				return disp;
			};
			
		};
		MapLayoutModel2D<Node> model = new MapLayoutModel2D<Node>(new Box2D(layoutWidth, layoutHeight));
		layout(topLevelNodes, forceLayout, model);
	}
	
	private static boolean intersects(Node v, Point2D vPos, Node u, Point2D uPos) {
		double gutter = 30.0;
		// Adapted from awt rectangle
        org.nasdanika.drawio.Rectangle vGeometry = v.getGeometry();
		double vw = vGeometry.getWidth() + gutter;
        double vh = vGeometry.getHeight() + gutter;
        org.nasdanika.drawio.Rectangle uGeometry = u.getGeometry();
		double uw = uGeometry.getWidth() + gutter;
        double uh = uGeometry.getHeight() + gutter;
        if (uw <= 0 || uh <= 0 || vw <= 0 || vh <= 0) {
            return false;
        }
        double vx = vPos.getX();
        double vy = vPos.getY();
        double ux = uPos.getX();
        double uy = uPos.getY();
        uw += ux;
        uh += uy;
        vw += vx;
        vh += vy;
        return ((uw < ux || uw > vx) &&
                (uh < uy || uh > vy) &&
                (vw < vx || vw > ux) &&
                (vh < vy || vh > uy));
    }	
	
	/**
	 * Loads style map from a string
	 * @return
	 */
	public static Map<String, String> loadStyle(String styleStr) {
		DelimitedStringMap styleMap = new DelimitedStringMap(";", "=") {

			@Override
			protected String getState() {
				return styleStr;
			}

			@Override
			protected void setState(String state) {
				throw new UnsupportedOperationException();
			}
			
		};
		return Collections.unmodifiableMap(styleMap);
	}

}
