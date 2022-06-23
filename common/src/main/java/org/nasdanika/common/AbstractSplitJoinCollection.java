package org.nasdanika.common;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Collection which splits the underlying state S into a sequence of chunks C which then get converted to element type E.
 * The class itself has no state - it always retrieves state, splits, performs an operation and then joins and sets state in case of mutations.
 * For this reason there is no way to update an element. For sets you may add it again, for lists set at index. 
 * @author Pavel
 *
 * @param <S> State type
 * @param <C> Chunk type
 * @param <E> Element type
 */
public abstract class AbstractSplitJoinCollection<S,C,E> extends AbstractCollection<E> {
	
	/**
	 * @return State to be split into chunks.
	 */
	protected abstract S getState();
	
	/**
	 * Sets state composed from chunks created from elements.
	 * @param state
	 */
	protected abstract void setState(S state);
	
	/**
	 * Splits state into an array of chunks
	 * @param state
	 * @return
	 */
	protected abstract List<C> split(S state);
	
	/**
	 * Joins an array of chunks into a state.
	 * @param chunks
	 * @return
	 */
	protected abstract S join(List<C> chunks);
	
	/**
	 * Loads a chunk - converts it to element.
	 * @param chunk
	 * @return
	 */
	protected abstract E load(C chunk);
	
	/**
	 * Stores an element - converts it into a chunk  
	 * @param element
	 * @return
	 */
	protected abstract C store(E element);

	/**
	 * The iterator maintains array index - this is the only state it maintains. 
	 * For each operation state is retrieved, split, chunks loaded, ... 
	 */
	@Override
	public Iterator<E> iterator() {
		return new Iterator<E>() {
			
			// Points to the position of the current elements.
			// Initial value is before the first element.
			private int idx = -1;

			@Override
			public boolean hasNext() {
				S state = getState();
				return split(state).size() > idx + 1;
			}

			@Override
			public E next() {
				return load(split(getState()).get(++idx));
			}
			
			@Override
			public void remove() {
				List<C> chunks = new ArrayList<>(split(getState())); // In case split returns unmodifiable list.
				chunks.remove(idx--);
				setState(join(chunks));
			}
		};
	}

	@Override
	public int size() {
		return split(getState()).size();
	}
	
	@Override
	public void clear() {
		setState(join(Collections.emptyList()));
	}
	
	@Override
	public boolean add(E e) {
		ArrayList<C> chunks = new ArrayList<>(split(getState()));
		chunks.add(store(e));
		setState(join(chunks));
		return true;
	}

}
