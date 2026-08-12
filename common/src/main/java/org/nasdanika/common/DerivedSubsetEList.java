package org.nasdanika.common;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Function;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.AbstractSequentialInternalEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * A modifiable, derived view of a single many-valued source feature, intended for
 * implementing derived subset features such as {@code Family.daughters} backed by
 * {@code Family.children}. Loosely modeled on
 * {@code org.eclipse.uml2.common.util.DerivedSubsetEObjectEList} but self-contained
 * (depends only on org.eclipse.emf.common and org.eclipse.emf.ecore).
 * <p>
 * Inclusion and (optional) value mapping are defined by {@link #asDerived(Object)};
 * mutations are mapped back by {@link #asSource(Object)} and written through to the
 * source feature's list, so the backing feature's own semantics (containment,
 * opposites, notifications) apply. In addition, mutations performed through this
 * list dispatch notifications against the <em>derived</em> feature with view-relative
 * indices, guarded by {@code owner.eNotificationRequired()}.
 * <p>
 * <b>Identity:</b> {@link #asDerived(Object)} should be identity-preserving
 * (filter + downcast). If it manufactures wrapper objects, they must be identity-stable
 * per source element, or {@code indexOf}/{@code remove(Object)}/adapter-based code will
 * misbehave.
 * <p>
 * <b>Concurrency/staleness:</b> like the UML2 implementation, this is a live view.
 * Structural changes made directly to the backing list between iterator operations are
 * not tracked by this list's modCount and may produce surprising iteration results.
 * <p>
 * <b>Not thread-safe</b> (like all of EMF).
 *
 * @param <E> the derived element type
 */
public class DerivedSubsetEList<E> extends AbstractSequentialInternalEList<E>
		implements InternalEList.Unsettable<E>, EStructuralFeature.Setting {

	protected final InternalEObject owner;

	/** Feature ID of the derived feature (for notifications and {@link #getEStructuralFeature()}). */
	protected final int featureID;

	/** Feature ID of the backing many-valued feature. */
	protected final int sourceFeatureID;

	protected final Class<E> dataClass;

	// Optional functional configuration; when null, the protected methods' defaults apply.
	protected final Function<Object, Optional<E>> derivedMapper;
	protected final Function<E, Object> sourceMapper;

	/**
	 * Type-filtered subset: an element of the source list is part of the view iff it is
	 * an instance of {@code dataClass}.
	 */
	public DerivedSubsetEList(Class<E> dataClass, InternalEObject owner, int featureID, int sourceFeatureID) {
		this(dataClass, owner, featureID, sourceFeatureID, null, null);
	}

	/**
	 * Functional variant: {@code derivedMapper} returns the view value or
	 * {@link Optional#empty()} for exclusion; {@code sourceMapper} maps a view value to
	 * the source element to store, throwing {@link IllegalArgumentException} if
	 * preconditions fail. Either may be null to fall back to the type-based defaults.
	 */
	public DerivedSubsetEList(
			Class<E> dataClass,
			InternalEObject owner,
			int featureID,
			int sourceFeatureID,
			Function<Object, Optional<E>> derivedMapper,
			Function<E, Object> sourceMapper) {

		this.dataClass = dataClass;
		this.owner = owner;
		this.featureID = featureID;
		this.sourceFeatureID = sourceFeatureID;
		this.derivedMapper = derivedMapper;
		this.sourceMapper = sourceMapper;

		EStructuralFeature sourceFeature = owner.eClass().getEStructuralFeature(sourceFeatureID);
		if (sourceFeature == null || !sourceFeature.isMany()) {
			throw new IllegalArgumentException("Source feature must be many-valued: " + sourceFeature);
		}
	}

	// --- Mapping hooks -----------------------------------------------------------------

	/**
	 * Maps a source element to its view value, or {@link Optional#empty()} if the element
	 * is not part of the view. Default: type filter on {@code dataClass}.
	 */
	@SuppressWarnings("unchecked")
	protected Optional<E> asDerived(Object sourceElement) {
		if (derivedMapper != null) {
			return derivedMapper.apply(sourceElement);
		}
		return dataClass.isInstance(sourceElement) ? Optional.of((E) sourceElement) : Optional.empty();
	}

	/**
	 * Maps a view value to the source element to be stored in the backing list.
	 * Default: identity. Implementations should throw {@link IllegalArgumentException}
	 * if the value cannot be a member of this subset; storing a value that
	 * {@link #asDerived(Object)} would exclude makes it silently invisible.
	 */
	protected Object asSource(E derivedElement) {
		if (sourceMapper != null) {
			return sourceMapper.apply(derivedElement);
		}
		return derivedElement;
	}

	// --- Backing feature access --------------------------------------------------------

	protected boolean resolve() {
		EStructuralFeature feature = getEStructuralFeature();
		return feature instanceof EReference && ((EReference) feature).isResolveProxies();
	}

	@SuppressWarnings("unchecked")
	protected List<Object> sourceList(boolean resolve) {
		Object value = owner.eGet(sourceFeatureID, resolve, true);
		return resolve
			? (List<Object>) value
			: ((InternalEList<Object>) value).basicList();
	}

	// --- List contract -----------------------------------------------------------------

	@Override
	public int size() {
		int result = 0;
		for (Object sourceElement : sourceList(resolve())) {
			if (asDerived(sourceElement).isPresent()) {
				++result;
			}
		}
		return result;
	}

	@Override
	public boolean isEmpty() {
		for (Object sourceElement : sourceList(resolve())) {
			if (asDerived(sourceElement).isPresent()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return new DerivedListIterator(index, resolve(), true);
	}

	@Override
	public ListIterator<E> basicListIterator(int index) {
		return new DerivedListIterator(index, false, false);
	}

	@Override
	public List<E> basicList() {
		return new AbstractSequentialInternalEList<E>() {
			@Override
			public ListIterator<E> listIterator(int index) {
				return basicListIterator(index);
			}

			@Override
			public int size() {
				return DerivedSubsetEList.this.size();
			}
		};
	}

	/**
	 * Bidirectional filtered/mapped list iterator over the live backing list, with
	 * write-through mutation. Positions:
	 * <ul>
	 * <li>{@code backingCursor} — index into the backing list at which the scan for
	 * {@code next()} starts; also the insertion point for {@code add}.</li>
	 * <li>{@code viewCursor} — index in the view of the element {@code next()} would
	 * return.</li>
	 * </ul>
	 */
	protected class DerivedListIterator implements ListIterator<E> {

		protected final boolean resolveElements;
		protected final boolean mutable;

		protected int backingCursor;
		protected int viewCursor;

		/** Backing index of the element last returned by next()/previous(), or -1. */
		protected int lastBackingIndex = -1;

		/** +1 if last returned via next(), -1 via previous(), 0 if none. */
		protected int lastDirection = 0;

		protected int expectedModCount = modCount;

		protected DerivedListIterator(int index, boolean resolveElements, boolean mutable) {
			this.resolveElements = resolveElements;
			this.mutable = mutable;
			if (index < 0) {
				throw new IndexOutOfBoundsException("index=" + index);
			}
			// Advance to the requested view index.
			List<Object> backing = backing();
			int seen = 0;
			int i = 0;
			for (int backingSize = backing.size(); i < backingSize && seen < index; ++i) {
				if (asDerived(backing.get(i)).isPresent()) {
					++seen;
				}
			}
			if (seen < index) {
				throw new IndexOutOfBoundsException("index=" + index + ", size=" + seen);
			}
			this.backingCursor = i;
			this.viewCursor = index;
		}

		protected List<Object> backing() {
			return sourceList(resolveElements);
		}

		protected void checkModCount() {
			if (expectedModCount != modCount) {
				throw new java.util.ConcurrentModificationException();
			}
		}

		@Override
		public boolean hasNext() {
			List<Object> backing = backing();
			for (int i = backingCursor, backingSize = backing.size(); i < backingSize; ++i) {
				if (asDerived(backing.get(i)).isPresent()) {
					return true;
				}
			}
			return false;
		}

		@Override
		public E next() {
			checkModCount();
			List<Object> backing = backing();
			for (int i = backingCursor, backingSize = backing.size(); i < backingSize; ++i) {
				Optional<E> derived = asDerived(backing.get(i));
				if (derived.isPresent()) {
					lastBackingIndex = i;
					lastDirection = 1;
					backingCursor = i + 1;
					++viewCursor;
					return derived.get();
				}
			}
			throw new NoSuchElementException();
		}

		@Override
		public boolean hasPrevious() {
			List<Object> backing = backing();
			for (int i = backingCursor - 1; i >= 0; --i) {
				if (asDerived(backing.get(i)).isPresent()) {
					return true;
				}
			}
			return false;
		}

		@Override
		public E previous() {
			checkModCount();
			List<Object> backing = backing();
			for (int i = backingCursor - 1; i >= 0; --i) {
				Optional<E> derived = asDerived(backing.get(i));
				if (derived.isPresent()) {
					lastBackingIndex = i;
					lastDirection = -1;
					backingCursor = i;
					--viewCursor;
					return derived.get();
				}
			}
			throw new NoSuchElementException();
		}

		@Override
		public int nextIndex() {
			return viewCursor;
		}

		@Override
		public int previousIndex() {
			return viewCursor - 1;
		}

		@Override
		public void remove() {
			checkModCount();
			checkMutable();
			if (lastDirection == 0) {
				throw new IllegalStateException();
			}
			Object removed = mutableBacking().remove(lastBackingIndex);
			int removedViewIndex;
			if (lastDirection > 0) {
				--backingCursor;
				--viewCursor;
				removedViewIndex = viewCursor;
			} else {
				removedViewIndex = viewCursor;
			}
			lastDirection = 0;
			lastBackingIndex = -1;
			++modCount;
			++expectedModCount;
			dispatchNotification(Notification.REMOVE, asDerived(removed).orElse(null), null, removedViewIndex);
		}

		@Override
		public void set(E object) {
			checkModCount();
			checkMutable();
			if (lastDirection == 0) {
				throw new IllegalStateException();
			}
			Object replaced = mutableBacking().set(lastBackingIndex, asSource(object));
			int viewIndex = lastDirection > 0 ? viewCursor - 1 : viewCursor;
			++modCount;
			++expectedModCount;
			dispatchNotification(Notification.SET, asDerived(replaced).orElse(null), object, viewIndex);
		}

		@Override
		public void add(E object) {
			checkModCount();
			checkMutable();
			mutableBacking().add(backingCursor, asSource(object));
			++backingCursor;
			int addedViewIndex = viewCursor;
			++viewCursor;
			lastDirection = 0;
			lastBackingIndex = -1;
			++modCount;
			++expectedModCount;
			dispatchNotification(Notification.ADD, null, object, addedViewIndex);
		}

		protected void checkMutable() {
			if (!mutable) {
				throw new UnsupportedOperationException("Basic (non-resolving) view is not modifiable");
			}
		}

		/**
		 * Mutations always go through the resolved list returned by
		 * {@code eGet(sourceFeatureID, true, true)} so the backing feature's full
		 * semantics (containment, opposites, its own notifications) apply.
		 */
		protected List<Object> mutableBacking() {
			return sourceList(true);
		}
	}

	protected void dispatchNotification(int eventType, Object oldValue, Object newValue, int viewIndex) {
		if (owner.eNotificationRequired()) {
			owner.eNotify(new ENotificationImpl(owner, eventType, featureID, oldValue, newValue, viewIndex));
		}
	}

	// --- EStructuralFeature.Setting / Unsettable ---------------------------------------

	@Override
	public EObject getEObject() {
		return owner;
	}

	@Override
	public EStructuralFeature getEStructuralFeature() {
		return owner.eClass().getEStructuralFeature(featureID);
	}

	@Override
	public Object get(boolean resolve) {
		return this;
	}

	@Override
	public void set(Object newValue) {
		clear();
		@SuppressWarnings("unchecked")
		List<E> newValues = (List<E>) newValue;
		addAll(newValues);
	}

	@Override
	public boolean isSet() {
		return !isEmpty();
	}

	@Override
	public void unset() {
		clear();
	}

	// --- Object ------------------------------------------------------------------------

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("[");
		for (ListIterator<E> i = listIterator(0); i.hasNext();) {
			result.append(i.next());
			if (i.hasNext()) {
				result.append(", ");
			}
		}
		return result.append(']').toString();
	}
}
