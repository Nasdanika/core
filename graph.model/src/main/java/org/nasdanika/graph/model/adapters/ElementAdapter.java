package org.nasdanika.graph.model.adapters;

import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.graph.Element;

public interface ElementAdapter<T extends EObject> extends Element, Supplier<T> {

}
