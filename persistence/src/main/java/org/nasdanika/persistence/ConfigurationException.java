package org.nasdanika.persistence;

import java.util.Collection;
import java.util.Collections;
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
	
	private Collection<? extends Marker> markers;
	
	public ConfigurationException(String message, Marker marker) {
		this(message, marker == null ? null : Collections.singletonList(marker));
	}

	public ConfigurationException(Throwable cause, Marker marker) {
		this(cause, marker == null ? null : Collections.singletonList(marker));
	}	

	public ConfigurationException(String message, Throwable cause, Marker marker) {
		this(message, cause, marker == null ? null : Collections.singletonList(marker));
	}	

	public ConfigurationException(String message, Collection<? extends Marker> markers) {
		super(markers == null || markers.isEmpty() ? message : message + " at " + markers.stream().map(m -> Marker.toString(m)).collect(Collectors.joining(", ")));
		this.markers = markers;
	}

	public ConfigurationException(Throwable cause, Collection<? extends Marker> markers) {
		super(markers == null || markers.isEmpty() ? cause.toString() : cause.toString() + " at " + markers.stream().map(m -> Marker.toString(m)).collect(Collectors.joining(", ")), cause);
		this.markers = markers;
	}

	public ConfigurationException(String message, Throwable cause, Collection<? extends Marker> markers) {
		super(markers == null || markers.isEmpty() ? message : message + " at " + markers.stream().map(m -> Marker.toString(m)).collect(Collectors.joining(", ")), cause);
		this.markers = markers;
	}

	public ConfigurationException(String message, Marked marked) {
		this(message, marked == null ? null : marked.getMarkers());
	}

	public ConfigurationException(Throwable cause, Marked marked) {
		this(cause, marked == null ? null : marked.getMarkers());
	}

	public ConfigurationException(String message, Throwable cause, Marked marked) {
		this(message, cause, marked == null ? null : marked.getMarkers());
	}
	
	public Collection<? extends Marker> getMarkers() {
		return markers;
	}
	
	private static ThreadLocal<Stack<Collection<? extends Marker>>> threadMarkerStack = new ThreadLocal<Stack<Collection<? extends Marker>>>() {

		protected java.util.Stack<Collection<? extends Marker>> initialValue() {
			return new Stack<>();
		};
		
	};
	
	public static void pushThreadMarker(Collection<? extends Marker> markers) {
		threadMarkerStack.get().push(markers);
	}
	
	public static void popThreadMarker() {
		threadMarkerStack.get().pop();
	}	
	
	public static void pushThreadMarked(Marked marked) {
		pushThreadMarker(marked == null ? null : marked.getMarkers());
	}
	
	public static Collection<? extends Marker> peekThreadMarker() {
		Stack<Collection<? extends Marker>> stack = threadMarkerStack.get();
		return stack.isEmpty() ? null : stack.peek(); 
	}

	public ConfigurationException(String message) {
		this(message, peekThreadMarker());
	}

	public ConfigurationException(Throwable cause) {
		this(cause, peekThreadMarker());
	}

	public ConfigurationException(String message, Throwable cause) {
		this(message, cause, peekThreadMarker());
	}

}
