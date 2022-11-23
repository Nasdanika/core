package org.nasdanika.drawio;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.graph.Connection;
import org.nasdanika.graph.processor.AbstractEObjectFactory;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Creates {@link EObject}s from graph elements and wires them together using diagram element properties.
 * @author Pavel
 *
 */
public abstract class AbstractDrawioEObjectFactory<T extends EObject> extends AbstractEObjectFactory<T> {
	
	
	protected String getChildReferencePropertyName() {
		return "child-reference";
	}
	
	protected String getParentReferencePropertyName() {
		return "parent-reference";
	}
	
	protected String getPropertyValue(org.nasdanika.graph.Element element, String propertyName) {
		if (!org.nasdanika.common.Util.isBlank(propertyName) && element instanceof ModelElement) {
			return ((ModelElement) element).getProperty(propertyName);
		}
		return null;
	}

	@Override
	protected EReference getChildReference(ProcessorConfig<T> config, T semanticElement, ProcessorInfo<T> parentProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EReference getParentReference(ProcessorConfig<T> config, T semanticElement, ProcessorInfo<T> parentProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EReference getSourceReference(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> sourceProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EReference getTargetReference(ConnectionProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, ProcessorInfo<T> targetProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EReference getIncomingReference(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> incomingProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected EReference getOutgoingReference(NodeProcessorConfig<T, ProcessorInfo<T>, ProcessorInfo<T>> config, T semanticElement, Connection connection, ProcessorInfo<T> outgoingProcessorInfo) {
		// TODO Auto-generated method stub
		return null;
	}

}
