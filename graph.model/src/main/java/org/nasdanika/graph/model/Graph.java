/**
 */
package org.nasdanika.graph.model;

import java.util.Collections;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.graph.model.adapters.ElementAdapter;
import org.nasdanika.graph.model.adapters.GraphAdapterFactory;
import org.nasdanika.graph.model.util.ReflectiveProcessorFactory;
import org.nasdanika.graph.processor.NopEndpointProcessorConfigFactory;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.function.BiFunctionProcessorFactory;
import org.nasdanika.graph.processor.function.ReflectiveBiFunctionProcessorFactoryProvider;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Graph</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Graph is a container of graph elements uniquely identified by a string identifier. This allows to implement graph inheritance - deriving a graph from another graph by removing, adding and replacing graph elements similar to how inheritance works in object-oriented languages.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.graph.model.Graph#getElements <em>Elements</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.graph.model.ModelPackage#getGraph()
 * @model
 * @generated
 */
public interface Graph<E extends GraphElement> extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Graph elements uniquely identified by a string identifier. This allows to implement graph inheritance - deriving a graph from another graph by removing, adding and replacing graph elements similar to how inheritance works in object-oriented languages.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see org.nasdanika.graph.model.ModelPackage#getGraph_Elements()
	 * @model containment="true" keys="id"
	 * @generated
	 */
	EList<E> getElements();
	
	/**
	 * Creates processors for this flow and its child elements and returns processor info for interacting with the flow
	 * @param context
	 * @param progressMonitor
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>> createProcessor(Object requirement, Context context, ProgressMonitor progressMonitor) {				
		// Creating adapters
		GraphAdapterFactory graphAdapterFactory = new GraphAdapterFactory();  
		Transformer<EObject,ElementAdapter<?>> graphFactory = new Transformer<>(graphAdapterFactory); 
		Map<EObject, ElementAdapter<?>> registry = graphFactory.transform(Collections.singleton(this), false, progressMonitor);
		
		// Configs and processors
		NopEndpointProcessorConfigFactory<Function<Object,Object>> processorConfigFactory = new NopEndpointProcessorConfigFactory<>() {
			
			protected boolean isPassThrough(org.nasdanika.graph.Connection connection) {
				return false;
			};
			
		};
		
		Transformer<org.nasdanika.graph.Element, ProcessorConfig> transformer = new Transformer<>(processorConfigFactory);
		Map<org.nasdanika.graph.Element, ProcessorConfig> configs = transformer.transform(registry.values(), false, progressMonitor);

		ReflectiveProcessorFactory reflectiveTarget = new ReflectiveProcessorFactory(requirement, context);
		ReflectiveBiFunctionProcessorFactoryProvider<Object, Object, Object, Object> processorFactoryProvider = new ReflectiveBiFunctionProcessorFactoryProvider<>(reflectiveTarget);
		BiFunctionProcessorFactory<Object, Object, Object, Object> processorFactory = processorFactoryProvider.getFactory();
		Map<org.nasdanika.graph.Element, ProcessorInfo<BiFunction<Object, ProgressMonitor, Object>>> processors = processorFactory.createProcessors(configs.values(), true, progressMonitor);

		return processors
				.entrySet()
				.stream()
				.filter(e -> ((Supplier<EObject>) e.getKey()).get() == this)
				.map(Map.Entry::getValue)
				.findAny()
				.get();
	}
	

} // Graph
