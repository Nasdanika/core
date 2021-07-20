package org.nasdanika.exec.tests;

import java.util.function.BiConsumer;

import org.nasdanika.common.MarkdownHelper;

/**
 * For testing purposes, need a public constructor.
 * @author Pavel
 *
 */
public class CustomMarkdownHelper extends MarkdownHelper implements BiConsumer<String, Object> {
	
	public CustomMarkdownHelper() {
		super();
	}

	@Override
	public void accept(String key, Object value) {
		System.out.println(key + " : " + value);		
	}
	
}
