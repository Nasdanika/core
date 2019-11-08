package org.nasdanika.common;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class MapWork<K,T,E> extends FilterWork<T> implements Work<T> {
	
	private Map<Integer,K> keyMap = new HashMap<>();
	private AtomicInteger counter = new AtomicInteger();

	public MapWork(String name, Executor executor) {
		super(null);
		target = new CompoundWork<T, E>(name, executor) {

			@Override
			protected T combine(List<E> results, ProgressMonitor progressMonitor) throws Exception {
				Map<K,E> mappedResults = new HashMap<>();
				int counter = 0;
				for (E result: results) {
					K key = keyMap.get(counter++);
					if (key != null) {
						mappedResults.put(key, result);
					}
				}
				return MapWork.this.combine(mappedResults, progressMonitor);
			}
		};
	}
	
	/**
	 * Adds a child work
	 * @param child
	 * @return {@link CommandCallable} wrapping the work's execute() method.
	 */
	@SuppressWarnings("unchecked")
	public Callable<E> add(K key, Work<E> child) {
		keyMap.put(counter.incrementAndGet(), key);
		return ((CompoundWork<T,E>) target).add(child);
	}
	
	protected abstract T combine(Map<K,E> results, ProgressMonitor progressMonitor) throws Exception;

}
