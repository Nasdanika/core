package org.nasdanika.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.BinaryOperator;

/**
 * Aggregates (reduces) element results using a {@link BinaryOperator}
 * @param <E>
 */
public class ReducingListCompoundSupplier<E> extends ListCompoundExecutionParticipant<Supplier<E>> implements Supplier<E>  {

	private BinaryOperator<E> accumulator;

	public ReducingListCompoundSupplier(
			String name, 
			BinaryOperator<E> accumulator, 
			Collection<Supplier<E>> suppliers) {
		super(name);
		for (Supplier<E> supplier: suppliers) {
			add(supplier);
		}
		this.accumulator = accumulator;
	}
	
	@SafeVarargs
	public ReducingListCompoundSupplier(String name, BinaryOperator<E> accumulator, Supplier<E>... suppliers) {
		this(name, accumulator, Arrays.asList(suppliers));
	}

	@Override
	public E execute(ProgressMonitor progressMonitor) {
		progressMonitor.setWorkRemaining(size());
		return getElements()
			.stream()
			.filter(Objects::nonNull)
			.map(e -> e.splitAndExecute(progressMonitor))
			.reduce(accumulator)
			.orElse(null);
	}	

}
