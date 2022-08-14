package org.nasdanika.persistence;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import org.nasdanika.common.NasdanikaException;

/**
 * Exception to indicate error in configuration resources.
 * @author Pavel
 *
 */
@SuppressWarnings("serial")
public class ConfigurationException extends NasdanikaException {
	
	private List<? extends Marker> markers;
	
	public ConfigurationException(String message, Marker marker) {
		this(message, marker == null ? null : Collections.singletonList(marker));
	}

	public ConfigurationException(String message, Throwable cause, Marker marker) {
		this(message, cause, marker == null ? null : Collections.singletonList(marker));
	}	

	public ConfigurationException(String message, List<? extends Marker> markers) {
		super(markers == null || markers.isEmpty() ? message : message + " at " + markers.stream().map(m -> Marker.toString(m)).collect(Collectors.joining(", ")));
		this.markers = markers;
	}

	public ConfigurationException(String message, Throwable cause, List<? extends Marker> markers) {
		super(markers == null || markers.isEmpty() ? message : message + " at " + markers.stream().map(m -> Marker.toString(m)).collect(Collectors.joining(", ")));
		this.markers = markers;
	}

	public ConfigurationException(String message, Marked marked) {
		this(message, marked == null ? null : marked.getMarkers());
	}

	public ConfigurationException(String message, Throwable cause, Marked marked) {
		this(message, cause, marked == null ? null : marked.getMarkers());
	}
	
	public List<? extends Marker> getMarkers() {
		return markers;
	}
	
	private static ThreadLocal<Stack<List<? extends Marker>>> threadMarkerStack = new ThreadLocal<Stack<List<? extends Marker>>>() {

		protected java.util.Stack<List<? extends Marker>> initialValue() {
			return new Stack<>();
		};
		
	};
	
	public static void pushThreadMarker(List<? extends Marker> markers) {
		threadMarkerStack.get().push(markers);
	}
	
	public static void popThreadMarker() {
		threadMarkerStack.get().pop();
	}	
	
	public static void pushThreadMarked(Marked marked) {
		pushThreadMarker(marked == null ? null : marked.getMarkers());
	}
	
	public static List<? extends Marker> peekThreadMarker() {
		Stack<List<? extends Marker>> stack = threadMarkerStack.get();
		return stack.isEmpty() ? null : stack.peek(); 
	}

	public ConfigurationException(String message) {
		this(message, peekThreadMarker());
	}

	public ConfigurationException(String message, Throwable cause) {
		this(message, cause, peekThreadMarker());
	}

}
