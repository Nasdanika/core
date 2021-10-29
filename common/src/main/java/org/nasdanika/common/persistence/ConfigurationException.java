package org.nasdanika.common.persistence;

import java.util.Stack;

import org.nasdanika.common.NasdanikaException;

/**
 * Exception to indicate error in configuration resources.
 * @author Pavel
 *
 */
@SuppressWarnings("serial")
public class ConfigurationException extends NasdanikaException {
	
	private Marker marker;

	public ConfigurationException(String message, Marker marker) {
		super(marker == null ? message : message + " at " + Marker.toString(marker));
		this.marker = marker;
	}

	public ConfigurationException(String message, Throwable cause, Marker marker) {
		super(marker == null ? message : message + " at " + Marker.toString(marker), cause);
		this.marker = marker;
	}

	public ConfigurationException(String message, Marked marked) {
		this(message, marked == null ? null : marked.getMarker());
	}

	public ConfigurationException(String message, Throwable cause, Marked marked) {
		this(message, cause, marked == null ? null : marked.getMarker());
	}
	
	public Marker getMarker() {
		return marker;
	}
	
	private static ThreadLocal<Stack<Marker>> threadMarkerStack = new ThreadLocal<Stack<Marker>>() {

		protected java.util.Stack<Marker> initialValue() {
			return new Stack<>();
		};
		
	};
	
	public static void pushThreadMarker(Marker marker) {
		threadMarkerStack.get().push(marker);
	}
	
	public static void popThreadMarker() {
		threadMarkerStack.get().pop();
	}	
	
	public static void pushThreadMarked(Marked marked) {
		pushThreadMarker(marked == null ? null : marked.getMarker());
	}
	
	public static Marker peekThreadMarker() {
		Stack<Marker> stack = threadMarkerStack.get();
		return stack.isEmpty() ? null : stack.peek(); 
	}

	public ConfigurationException(String message) {
		this(message, peekThreadMarker());
	}

	public ConfigurationException(String message, Throwable cause) {
		this(message, cause, peekThreadMarker());
	}

}
