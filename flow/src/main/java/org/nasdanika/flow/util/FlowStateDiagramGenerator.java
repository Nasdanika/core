package org.nasdanika.flow.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import org.nasdanika.flow.Participant;
import org.nasdanika.flow.PseudoState;
import org.nasdanika.flow.Transition;

public class FlowStateDiagramGenerator {
	
	DiagramFactory diagramFactory = DiagramFactory.eINSTANCE;

	public void generateFlowDiagram(Flow flow, Diagram diagram) {
		Map<FlowElement<?>,DiagramElement> semanticMap = new HashMap<>();
		diagram.getElements().addAll(createDiagramElements(flow.getElements().values(), semanticMap, null));
		
		for (FlowElement<?> fe: semanticMap.keySet()) {
			wire(fe, semanticMap);
		}
		
		// Auto start/end
	}
	
	// TODO - single method taking context and depth into account.
	public void generateContextDiagram(FlowElement<?> flowElement, Diagram diagram) {
		Collection<FlowElement<?>> diagramElements = new HashSet<>();
		collectRelatedElements(flowElement, diagramElements);
		
		Map<FlowElement<?>,DiagramElement> semanticMap = new HashMap<>();
		diagram.getElements().addAll(createDiagramElements(diagramElements, semanticMap, flowElement));
		
		for (FlowElement<?> fe: diagramElements) {
			wire(fe, semanticMap);
		}
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
			
//			if (flowElement instanceof Flow && ((Flow) flowElement).isPartition()) {
//				for (FlowElement<?> subElement: ((Flow) flowElement).getElements().values()) {
//					collectRelatedElements(subElement, accumulator);
//				}
//			}
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
				ret.getElements().addAll(createDiagramElements(((Flow) flowElement).getElements().values(), semanticMap, contextElement));
			}
		}
		EList<String> modifiers = flowElement.getModifiers();
		if (modifiers.contains("final")) {
			ret.setBold(true);
		} else if (modifiers.contains("abstract")) {
			ret.setDashed(true);
		}
		
		if (modifiers.contains("optional")) {
			ret.setBorder("grey");
		}
		
		if (contextElement == null || flowElement == contextElement) {
			if (Util.isBlank(ret.getColor())) {
				ret.setColor("FEFECE"); 				
			}
		} else {
			ret.setColor("DDDDDD");
		}
		
		return ret;
	}

	/**
	 * Creates diagram for flow elements potentially grouping them.
	 * @param flowElements 
	 * @param semanticMap
	 * @param contextElement
	 * @return
	 */
	protected List<DiagramElement> createDiagramElements(
			Collection<FlowElement<?>> flowElements,
			Map<FlowElement<?>, DiagramElement> semanticMap, 
			FlowElement<?> contextElement) {		
		List<DiagramElement> ret = new ArrayList<>();
		if (isGroupByParticipant()) {
			java.util.function.Function<FlowElement<?>, Participant> keyExtractor = fe -> {
				EList<Participant> participants = fe.getParticipants();
				return participants.isEmpty() ? null : participants.get(0);
			};
			for (Entry<Participant, List<FlowElement<?>>> ge: Util.groupBy(flowElements, keyExtractor).entrySet()) {
				if (ge.getKey() == null) {
					for (FlowElement<?> subElement: ge.getValue()) {
						ret.add(createDiagramElement(subElement, semanticMap, contextElement));
					}							
				} else {
					ret.add(createParticipantGroup(ge.getKey(), ge.getValue(), semanticMap, contextElement));
				}
			}
		} else {				
			for (FlowElement<?> subElement: flowElements) {
				ret.add(createDiagramElement(subElement, semanticMap, contextElement));
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
	
	protected boolean isGroupByParticipant() {
		return true;
	}

	protected DiagramElement createParticipantGroup(
			Participant participant, 
			Collection<FlowElement<?>> groupElements, 
			Map<FlowElement<?>, DiagramElement> semanticMap, 
			FlowElement<?> contextElement) {
		
		org.nasdanika.diagram.DiagramElement ret = diagramFactory.createDiagramElement();
		ret.setColor("E0E0FF");
		
		ret.setText(participant.getName());
		ret.setType("state");
		ret.setLocation(getParticipantLocation(participant));
		ret.setTooltip(getParticipantTooltip(participant));
		
		
		for (FlowElement<?> groupElement: groupElements) {
			DiagramElement diagramElement = createDiagramElement(groupElement, semanticMap, contextElement);
			if (diagramElement != null) {
				ret.getElements().add(diagramElement);
			}
		}
		
		return ret;		
	}
	
	protected String getParticipantLocation(Participant participant) {
		return null;
	}
	
	protected String getParticipantTooltip(Participant participant) {
		return null;
	}
	
}
