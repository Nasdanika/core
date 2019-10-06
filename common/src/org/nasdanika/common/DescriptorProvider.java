package org.nasdanika.common;

/**
 * Provider of a descriptor backed by a data source.
 * @author Pavel
 *
 * @param <D>
 * @param <S>
 */
public interface DescriptorProvider<D extends Descriptor, S> {

	D getDescriptor(S dataSource);
	
}
