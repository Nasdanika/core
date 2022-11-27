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
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Util {
	
	private Util() {}
			
	/**
	 * Lays out the diagram so nodes do not overlap.
	 * @param root
	 * @param gridSize
	 */
	public static void layout(Root root, int gridSize) {
		List<Node> topLevelNodes = root.getLayers().stream().flatMap(layer -> layer.getElements().stream()).filter(Node.class::isInstance).map(Node.class::cast).collect(Collectors.toList());
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
				int[] eAffinity = affinity(e.getKey(), els.stream().map(Map.Entry::getKey).collect(Collectors.toList()));
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
	private static int[] affinity(ModelElement element, Collection<ModelElement> elements) {
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
		List<Node> children = node.getElements().stream().filter(Node.class::isInstance).map(Node.class::cast).collect(Collectors.toList());
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
	public static <T> BiFunction<Element, Map<Element, T>, T> withLinkedPages(BiFunction<? super Element, Map<? extends Element, T>, T> visitor, ConnectionBase connectionBase) {
		return new BiFunction<Element, Map<Element, T>, T>() {

			@Override
			public T apply(Element element, Map<Element, T> childResults) {				
				if (element instanceof ModelElement) {
					Page linkedPage = ((ModelElement) element).getLinkedPage();
					if (linkedPage != null) {
						Map<Element, T> cr = new LinkedHashMap<>();
						if (childResults != null) {
							cr.putAll(childResults);
						}
						T linkedPageResult = linkedPage.accept(this, connectionBase);
						if (linkedPageResult != null) {
							cr.put(linkedPage, linkedPageResult);
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
	 * @return Visitor which passes itself to linked pages and adds linked pages' result to child results.
	 */
	public static <T> Consumer<Element> withLinkedPages(Consumer<Element> visitor, ConnectionBase connectionBase) {
		return new Consumer<Element>() {

			@Override
			public void accept(Element element) {				
				if (element instanceof ModelElement) {
					Page linkedPage = ((ModelElement) element).getLinkedPage();
					if (linkedPage != null) {
						linkedPage.accept(this, connectionBase);
					}
				}
				visitor.accept(element);
			}
			
		};
	}
	
//	/**
//	 * Creates an element wrapper which substitutes accept()'s visitor with a visitor which also traverses linked pages.
//	 * @param element
//	 * @return
//	 */
//	public static org.nasdanika.graph.Element withLinkedPages(org.nasdanika.graph.Element element) {
//		
//	}

}
