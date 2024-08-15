package org.nasdanika.ncore.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.ncore.Reference;

/**
 * {@link TreeIterator} which iterates over {@link Reference} targets
 * and prevents infinite loops by maintaining a stack of objects being iterated over via target dereferencing.
 * @param <E>
 */
public class DereferencingTreeIterator implements TreeIterator<EObject> {
			
	private Stack<Map.Entry<Object, TreeIterator<EObject>>> stack = new Stack<>();
	private Predicate<? super EObject> refTargetPredicate;
	
	public DereferencingTreeIterator(EObject obj) {
		this(obj, true, null);
	}
	
	public DereferencingTreeIterator(
			Resource resource, 
			boolean includeRoot,
			Predicate<? super EObject> refTargetPredicate) {
		for (EObject obj: resource.getContents()) {
			stack.push(Map.entry(obj, createTreeIterator(obj, includeRoot)));
		}
		this.refTargetPredicate = refTargetPredicate;
	}
	
	public DereferencingTreeIterator(
			Iterable<EObject> objs, 
			boolean includeRoot,
			Predicate<? super EObject> refTargetPredicate) {
		for (EObject obj: objs) {
			stack.push(Map.entry(obj, createTreeIterator(obj, includeRoot)));
		}
		this.refTargetPredicate = refTargetPredicate;
	}
		
	public DereferencingTreeIterator(
			EObject obj, 
			boolean includeRoot,
			Predicate<? super EObject> refTargetPredicate) {
		stack.push(Map.entry(obj, createTreeIterator(obj, includeRoot)));
		this.refTargetPredicate = refTargetPredicate;
	}
	
	
	@Override
	public boolean hasNext() {
		return stack
				.stream()
				.map(Map.Entry::getValue)
				.filter(Iterator::hasNext)
				.findAny()
				.isPresent();
	}

	@Override
	public EObject next() {
		while (!stack.isEmpty()) {
			TreeIterator<EObject> topIterator = stack.peek().getValue();
			if (topIterator.hasNext()) {
				EObject next = topIterator.next();
				if (next instanceof Reference) {
					Object target = ((Reference<?>) next).getTarget();
					if (target instanceof EObject) {
						boolean targetInStack = stack
							.stream()
							.filter(e -> e.getKey() == target)
							.findAny()
							.isPresent();
								
						EObject eTarget = (EObject) target;
						if (!targetInStack && (refTargetPredicate == null || refTargetPredicate.test(eTarget))) {
							stack.push(Map.entry(eTarget, createTreeIterator(eTarget, true)));
						}
					}
				}
				
				return next;
			}
			stack.pop();
		}
		throw new IllegalStateException("No next");
	}

	@Override
	public void prune() {
		stack.peek().getValue().prune();		
	}
	
	@SuppressWarnings("serial")
	protected TreeIterator<EObject> createTreeIterator(EObject eObj, boolean includeRoot) {
		return new AbstractTreeIterator<EObject>(eObj, includeRoot) {

			@Override
			protected Iterator<? extends EObject> getChildren(Object object) {
				return ((EObject) object).eContents().iterator();
			}
			
		};				
	}
	
}
