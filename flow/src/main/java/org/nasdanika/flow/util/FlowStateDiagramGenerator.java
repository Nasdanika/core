package org.nasdanika.flow.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramFactory;
import org.nasdanika.diagram.Link;
import org.nasdanika.diagram.Note;
import org.nasdanika.flow.Call;
import org.nasdanika.flow.Choice;
import org.nasdanika.flow.EntryPoint;
import org.nasdanika.flow.ExitPoint;
import org.nasdanika.flow.ExpansionInput;
import org.nasdanika.flow.ExpansionOutput;
import org.nasdanika.flow.Flow;
import org.nasdanika.flow.FlowElement;
import org.nasdanika.flow.Fork;
import org.nasdanika.flow.InputPin;
import org.nasdanika.flow.Join;
import org.nasdanika.flow.OutputPin;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Transition;

public class FlowStateDiagramGenerator {
	
	DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;

	public Diagram generateFlowDiagram(Flow flow) {
		Diagram ret = createDiagram(flow);
		Map<FlowElement<?>,DiagramElement> semanticMap = new HashMap<>();
		for (FlowElement<?> fe: flow.getElements().values()) {
			DiagramElement diagramElement = createDiagramElement(fe, semanticMap, null);
			if (diagramElement != null) {
				ret.getElements().add(diagramElement);
			}
		}
		
		for (FlowElement<?> fe: semanticMap.keySet()) {
			wire(fe, semanticMap);
		}
		
		// Auto start/end
		
		return ret;
	}
	
	public Diagram generateContextDiagram(FlowElement<?> flowElement) {
		Collection<FlowElement<?>> diagramElements = new HashSet<>();
		collectRelatedElements(flowElement, diagramElements);
		
		Diagram ret = createDiagram(flowElement);
		Map<FlowElement<?>,DiagramElement> semanticMap = new HashMap<>();
		for (FlowElement<?> fe: diagramElements) {			
			DiagramElement diagramElement = createDiagramElement(fe, semanticMap, flowElement);
			if (diagramElement != null) {
				ret.getElements().add(diagramElement);
			}
		}
		
		for (FlowElement<?> fe: diagramElements) {
			wire(fe, semanticMap);
		}
		
		return ret;
	}

	private void collectRelatedElements(FlowElement<?> flowElement, Collection<FlowElement<?>> accumulator) { 
		if (flowElement != null && accumulator.add(flowElement)) {
			for (Transition input: flowElement.getInputs()) {
				FlowElement<?> target = (FlowElement<?>) input.eContainer().eContainer(); 
				if (target instanceof PseudoState) {
					collectRelatedElements(target, accumulator);
				} else {					
					accumulator.add(target);
				}
			}

			for (Transition output: flowElement.getOutputs().values()) {
				FlowElement<?> target = output.getTarget(); 
				if (target instanceof PseudoState) {
					collectRelatedElements(target, accumulator);
				} else if (target != null) {
					accumulator.add(target);
				}
			}

			for (Call invocation: flowElement.getInvocations()) {
				FlowElement<?> target = (FlowElement<?>) invocation.eContainer().eContainer(); 
				if (target instanceof PseudoState) {
					collectRelatedElements(target, accumulator);
				} else if (target != null) {
					accumulator.add(target);
				}
			}

			for (Call call: flowElement.getCalls().values()) {
				FlowElement<?> target = call.getTarget(); 
				if (target instanceof PseudoState) {
					collectRelatedElements(target, accumulator);
				} else if (target != null) {
					accumulator.add(target);
				}
			}
		}
	}
	
	protected DiagramElement createDiagramElement(FlowElement<?> flowElement, Map<FlowElement<?>, DiagramElement> semanticMap, FlowElement<?> contextElement) {
		
		if (flowElement instanceof org.nasdanika.flow.Start) {
			org.nasdanika.diagram.Start ret = diagramFactory.createStart();
			semanticMap.put(flowElement, ret);
			return ret;
		}
		
		if (flowElement instanceof org.nasdanika.flow.End) {
			org.nasdanika.diagram.End ret = diagramFactory.createEnd();
			semanticMap.put(flowElement, ret);
			return ret;
		}
		
		org.nasdanika.diagram.DiagramElement ret = diagramFactory.createDiagramElement();
		semanticMap.put(flowElement, ret);
		ret.setType("state");
		
		if (flowElement instanceof PseudoState) {
			if (flowElement instanceof Choice) {
				ret.setStereotype("choice");
			} else if (flowElement instanceof EntryPoint) { 
				ret.setStereotype("entryPoint");
			} else if (flowElement instanceof ExitPoint) { 
				ret.setStereotype("exitPoint");
			} else if (flowElement instanceof ExpansionInput) { 
				ret.setStereotype("expansionInput");
			} else if (flowElement instanceof ExpansionOutput) { 
				ret.setStereotype("expansioinOutput");
			} else if (flowElement instanceof Fork) { 
				ret.setStereotype("fork");
			} else if (flowElement instanceof InputPin) { 
				ret.setStereotype("inputPin");
			} else if (flowElement instanceof Join) { 
				ret.setStereotype("join");
			} else if (flowElement instanceof OutputPin) { 
				ret.setStereotype("outputPin");				
			}
			String name = flowElement.getName();
			if (!Util.isBlank(name)) {
				Note nameNote = diagramFactory.createNote();
				nameNote.setText(name);
				ret.getNotes().add(nameNote);				
			}
		} else {
			ret.setText(flowElement.getName());
			ret.setLocation(getFlowElementLocation(flowElement));
			ret.setTooltip(getFlowElementTooltip(flowElement));
			
			if (flowElement instanceof Flow && ((Flow) flowElement).isPartition()) {
				EList<DiagramElement> elements = ret.getElements();
				for (FlowElement<?> subElement: ((Flow) flowElement).getElements().values()) {
					elements.add(createDiagramElement(subElement, semanticMap, contextElement));
				}
			}
		}
		EList<String> modifiers = flowElement.getModifiers();
		if (modifiers.contains("final")) {
			ret.setBold(true);
		} else if (modifiers.contains("abstract")) {
			ret.setDashed(true);
		}
		
		if (contextElement == null) {
			if (modifiers.contains("optional")) {
				ret.setColor("grey");
			}
		} else {
			if (flowElement != contextElement) {
				ret.setColor("DDDDDD");
			}
		}
		return ret;
	}
	
	protected String getFlowElementLocation(FlowElement<?> flowElement) {
		return null;
	}
	
	protected String getFlowElementTooltip(FlowElement<?> flowElement) {
		return null;
	}
	
	protected void wire(FlowElement<?> value, Map<FlowElement<?>, DiagramElement> semanticMap) {
		EList<Connection> connections = semanticMap.get(value).getConnections();
		
		// Transitions
		for (Transition output: value.getOutputs().values()) {
			DiagramElement connectionTarget = semanticMap.get((FlowElement<?>) output.getTarget());
			if (connectionTarget != null) {
				Connection transition = diagramFactory.createConnection();
				connections.add(transition);
				transition.setTarget(connectionTarget);
				transition.setType("-->");
				String name = output.getName();
				if (!Util.isBlank(name)) {
					Link link = diagramFactory.createLink();
					link.setText(name);
					link.setLocation(getTransitionLocation(output));
					link.setTooltip(getTransitionTooltip(output));
					transition.getDescription().add(link);
				}
			}
		}
		
		// Calls
		for (Call call: value.getCalls().values()) {
			DiagramElement connectionTarget = semanticMap.get((FlowElement<?>) call.getTarget());
			if (connectionTarget != null) {
				Connection connection = diagramFactory.createConnection();
				connections.add(connection);
				connection.setTarget(connectionTarget);
				connection.setType("-left${style}->");
				connection.setColor("black");
				String name = call.getName();
				if (!Util.isBlank(name)) {
					Link link = diagramFactory.createLink();
					link.setText(name);
					link.setLocation(getCallLocation(call));
					link.setTooltip(getCallTooltip(call));
					connection.getDescription().add(link);
				}
			}
		}
	}
		
	protected String getTransitionLocation(Transition transition) {
		return null;
	}
	
	protected String getTransitionTooltip(Transition transition) {
		return null;
	}
	
	protected String getCallLocation(Call call) {
		return null;
	}
	
	protected String getCallTooltip(Call call) {
		return null;
	}
	
	protected Diagram createDiagram(FlowElement<?> flowElement) {
		return diagramFactory.createDiagram();
//		diagram.setHideEmptyDescription(true);
	}

}
