package org.nasdanika.capability;

/**
 * Capability provider which can determine that it is "better" (faster, cheaper, ...) than another provider and shall be used instead of the other provider
 * given a choice. 
 * @param <T>
 */
public interface SupercedingCapabilityProvider<T> extends CapabilityProvider<T> {
	
	boolean supercedes(CapabilityProvider<?> other);

}
