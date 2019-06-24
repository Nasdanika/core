package org.nasdanika.common;

/**
 * ResourceLocator implementation.
 * @author Pavel
 *
 */
public class SimpleMutableResourceLocator extends SimpleMutableContext implements ResourceLocator {

	public SimpleMutableResourceLocator() {
		super();
	}

	public SimpleMutableResourceLocator(Context chain) {
		super(chain);
	}

}
