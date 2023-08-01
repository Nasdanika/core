package org.nasdanika.graph.emf;

import java.util.List;

import org.eclipse.emf.ecore.EOperation;

public record EOperationConnectionQualifier(EOperation operation, List<Object> arguments, int index) {

}
