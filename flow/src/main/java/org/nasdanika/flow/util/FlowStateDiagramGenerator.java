package org.nasdanika.flow.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
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

	public void generateDiagram(Flow flow, Diagram diagram) {
		generateDiagram(flow.getElements().values(), diagram, null);
	}
	
	public void generateDiagram(FlowElement<?> flowElement, Diagram diagram) {
		generateDiagram(Collections.singleton(flowElement), diagram, flowElement);
	}
	
	public void generateDiagram(Collection<FlowElement<?>> flowElements, Diagram diagram, FlowElement<?> contextElement) {
		// Collecting all elements to be included
		Map<FlowElement<?>,Integer> semanticElements = new HashMap<>();
		for (FlowElement<?> flowElement: flowElements) {
			collectRelatedElements(flowElement, semanticElements, diagram.getContext(), Direction.BOTH);
		}
		
		// Selecting top-level elements
		Collection<FlowElement<?>> topLevelElements = new HashSet<>();
		semanticElements.keySet().stream()
			.filter(se -> !EcoreUtil.isAncestor(topLevelElements, se))
			.forEach(se -> {
				topLevelElements.removeIf(topLevelElement -> EcoreUtil.isAncestor(se, topLevelElement));
				topLevelElements.add(se);
			});
		
		Map<FlowElement<?>,DiagramElement> semanticMap = new HashMap<>();
		diagram.getElements().addAll(createDiagramElements(topLevelElements, semanticMap, contextElement, diagram.getDepth()));
		
		for (FlowElement<?> se: topLevelElements) {
			wire(se, semanticMap);
		}
	}
	
	private enum Direction { IN, OUT, BOTH }

	/**
	 * Collects related elements
	 * @param semanticElement Semantic element
	 * @param accumulator Accumulator of related elements
	 * @param depth Collection depth. 
	 * @param in If true, collect in the direction of related inputs/invocations, otherwise in the direction of outputs/calls.
	 */
	private void collectRelatedElements(FlowElement<?> semanticElement, Map<FlowElement<?>,Integer> accumulator, int depth, Direction direction) { 
		if (semanticElement == null) {
			return;
		}
		Integer sDepth = accumulator.get(semanticElement);
		if (sDepth != null && (sDepth < 0 || depth <= sDepth)) {
			return; // Do not traverse if existing depth is negative or depth is less than already traversed depth.
		}
		accumulator.put(semanticElement, depth);
		
		if (depth != 0) {
			if (direction == Direction.BOTH || direction == Direction.IN) {
				for (Transition input: semanticElement.getInputs()) {
					FlowElement<?> source = (FlowElement<?>) input.eContainer().eContainer(); 
					boolean isPseudoSource = source instanceof PseudoState;
					collectRelatedElements(source, accumulator, isPseudoSource ? depth : depth - 1, isPseudoSource ? Direction.IN : Direction.BOTH);
				}
	
				for (Call invocation: semanticElement.getInvocations()) {
					FlowElement<?> source = (FlowElement<?>) invocation.eContainer().eContainer(); 
					boolean isPseudoSource = source instanceof PseudoState;
					collectRelatedElements(source, accumulator, isPseudoSource ? depth : depth - 1, isPseudoSource ? Direction.IN : Direction.BOTH);
				}
			}
			
			if (direction == Direction.BOTH || direction == Direction.OUT) {
				for (Transition output: semanticElement.getOutputs().values()) {
					FlowElement<?> target = output.getTarget(); 
					boolean isPseudoTarget = target instanceof PseudoState;
					collectRelatedElements(target, accumulator, isPseudoTarget ? depth : depth - 1, isPseudoTarget ? Direction.OUT : Direction.BOTH);
				}
	
				for (Call call: semanticElement.getCalls().values()) {
					FlowElement<?> target = call.getTarget(); 
					boolean isPseudoTarget = target instanceof PseudoState;
					collectRelatedElements(target, accumulator, isPseudoTarget ? depth : depth - 1, isPseudoTarget ? Direction.OUT : Direction.BOTH);
				}
			}
		}
		
		if (semanticElement instanceof Flow) {
			for (FlowElement<?> subElement: ((Flow) semanticElement).getElements().values()) {
				collectRelatedElements(subElement, accumulator, depth, Direction.BOTH);
			}
		}
	}
	
	protected DiagramElement createDiagramElement(
			FlowElement<?> semanticElement, 
			Map<FlowElement<?>, DiagramElement> semanticMap, 
			FlowElement<?> contextElement,
			int depth) {
		
		if (semanticElement instanceof org.nasdanika.flow.Start) {
			org.nasdanika.diagram.Start ret = diagramFactory.createStart();
			semanticMap.put(semanticElement, ret);
			return ret;
		}
		
		if (semanticElement instanceof org.nasdanika.flow.End) {
			org.nasdanika.diagram.End ret = diagramFactory.createEnd();
			semanticMap.put(semanticElement, ret);
			return ret;
		}
		
		org.nasdanika.diagram.DiagramElement ret = diagramFactory.createDiagramElement();
		semanticMap.put(semanticElement, ret);
		ret.setType("state");
		
		if (semanticElement instanceof PseudoState) {
			if (semanticElement instanceof Choice) {
				ret.setStereotype("choice");
			} else if (semanticElement instanceof EntryPoint) { 
				ret.setStereotype("entryPoint");
			} else if (semanticElement instanceof ExitPoint) { 
				ret.setStereotype("exitPoint");
			} else if (semanticElement instanceof ExpansionInput) { 
				ret.setStereotype("expansionInput");
			} else if (semanticElement instanceof ExpansionOutput) { 
				ret.setStereotype("expansioinOutput");
			} else if (semanticElement instanceof Fork) { 
				ret.setStereotype("fork");
			} else if (semanticElement instanceof InputPin) { 
				ret.setStereotype("inputPin");
			} else if (semanticElement instanceof Join) { 
				ret.setStereotype("join");
			} else if (semanticElement instanceof OutputPin) { 
				ret.setStereotype("outputPin");				
			}
			String name = semanticElement.getName();
			if (!Util.isBlank(name)) {
				Note nameNote = diagramFactory.createNote();
				nameNote.setText(name);
				ret.getNotes().add(nameNote);				
			}
		} else {
			ret.setText(semanticElement.getName());
			ret.setLocation(getFlowElementLocation(semanticElement));
			ret.setTooltip(getFlowElementTooltip(semanticElement));
			
			if (depth != 0 && semanticElement instanceof Flow && ((Flow) semanticElement).isPartition()) {
				ret.getElements().addAll(createDiagramElements(((Flow) semanticElement).getElements().values(), semanticMap, contextElement, depth - 1));
			}
		}
		EList<String> modifiers = semanticElement.getModifiers();
		if (modifiers.contains("final")) {
			ret.setBold(true);
		} else if (modifiers.contains("abstract")) {
			ret.setDashed(true);
		}
		
		if (modifiers.contains("optional")) {
			ret.setBorder("grey");
		}
		
		if (contextElement == null || semanticElement == contextElement || EcoreUtil.isAncestor(contextElement, semanticElement)) {
			if (Util.isBlank(ret.getColor())) {
				ret.setColor("FEFECE"); 				
			}
		} else {
			ret.setColor("DDDDDD");
		}
		
		ArtifactComponentDiagramGenerator.setProperties(semanticElement, ret);
		return ret;
	}

	/**
	 * Creates diagram for flow elements potentially grouping them.
	 * @param semanticElements 
	 * @param semanticMap
	 * @param contextElement
	 * @return
	 */
	protected List<DiagramElement> createDiagramElements(
			Collection<FlowElement<?>> semanticElements,
			Map<FlowElement<?>, DiagramElement> semanticMap, 
			FlowElement<?> contextElement,
			int depth) {		
		List<DiagramElement> ret = new ArrayList<>();
		if (isGroupByParticipant()) {
			java.util.function.Function<FlowElement<?>, Participant> keyExtractor = fe -> {
				EList<Participant> participants = fe.getParticipants();
				return participants.isEmpty() ? null : participants.get(0);
			};
			for (Entry<Participant, List<FlowElement<?>>> ge: Util.groupBy(semanticElements, keyExtractor).entrySet()) {
				if (ge.getKey() == null) {
					for (FlowElement<?> subElement: ge.getValue()) {
						ret.add(createDiagramElement(subElement, semanticMap, contextElement, depth));
					}							
				} else {
					ret.add(createParticipantGroup(ge.getKey(), ge.getValue(), semanticMap, contextElement, depth));
				}
			}
		} else {				
			for (FlowElement<?> subElement: semanticElements) {
				ret.add(createDiagramElement(subElement, semanticMap, contextElement, depth));
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
	
	/**
	 * Collects outputs from the argument and unmapped children.
	 * @param semanticElement
	 * @param semanticMap
	 * @return
	 */
	protected Collection<Transition> collectAllOutputs(FlowElement<?> semanticElement, Map<FlowElement<?>, DiagramElement> semanticMap) {
		Collection<Transition> ret = new ArrayList<>();
		ret.addAll(semanticElement.getOutputs().values());
		if (semanticElement instanceof Flow) {
			for (FlowElement<?> child: ((Flow) semanticElement).getElements().values()) {
				if (!semanticMap.containsKey(child)) {
					ret.addAll(collectAllOutputs(child, semanticMap));
				}
			}
		}
		return ret;
	}	
	
	/**
	 * Collects outputs from the argument and unmapped children.
	 * @param semanticElement
	 * @param semanticMap
	 * @return
	 */
	protected Collection<Call> collectAllCalls(FlowElement<?> semanticElement, Map<FlowElement<?>, DiagramElement> semanticMap) {
		Collection<Call> ret = new ArrayList<>();
		ret.addAll(semanticElement.getCalls().values());
		if (semanticElement instanceof Flow) {
			for (FlowElement<?> child: ((Flow) semanticElement).getElements().values()) {
				if (!semanticMap.containsKey(child)) {
					ret.addAll(collectAllCalls(child, semanticMap));
				}
			}
		}
		return ret;
	}	
	
	protected void wire(FlowElement<?> semanticElement, Map<FlowElement<?>, DiagramElement> semanticMap) {
		EList<Connection> connections = semanticMap.get(semanticElement).getConnections();
		
		Function<Transition,FlowElement<?>> groupByMappedTarget = transition -> {
			FlowElement<?> target = transition.getTarget(); 
			while (!semanticMap.containsKey(target)) {
				if (target.eIsProxy()) {
					throw new ConfigurationException("Target is an unresolved proxy: " + target, semanticElement);
				}
				EObject eContainer = target.eContainer();
				if (eContainer == null) {
					throw new NullPointerException("Transition target does not have super-container, cannot group: " + transition);
				}
				EObject superContainer = eContainer.eContainer();
				if (superContainer instanceof FlowElement<?>) {
					target = (FlowElement<?>) superContainer;
				} else {
					return null;
				}
			}
			return target;		
		};
		
		// Transitions
		for (Entry<FlowElement<?>, List<Transition>> outputEntry: Util.groupBy(collectAllOutputs(semanticElement, semanticMap), groupByMappedTarget).entrySet()) {			
			FlowElement<?> target = outputEntry.getKey();
			if (target != null && target != semanticElement) {
				DiagramElement connectionTarget = semanticMap.get(target);
				if (connectionTarget != null) {
					List<Transition> transitions = outputEntry.getValue();
					Connection connection;
					if (transitions.size() == 1) {
						// Single relationship
						Transition output = transitions.get(0);
						connection = diagramFactory.createConnection();
						String name = output.getName();
						if (!Util.isBlank(name)) {
							Link link = diagramFactory.createLink();
							link.setText(name);
							link.setLocation(getTransitionLocation(output));
							link.setTooltip(getTransitionTooltip(output));
							connection.getDescription().add(link);
						}
					} else {
						// Grouped relationship
						connection = createGroupConnection(semanticElement, target, transitions);
					}
					connection.setType("-->");
					connections.add(connection);
					connection.setTarget(connectionTarget);
				}
			}
		}
		
		// Calls
		for (Entry<FlowElement<?>, List<Call>> callEntry: Util.groupBy(collectAllCalls(semanticElement, semanticMap), groupByMappedTarget).entrySet()) {			
			FlowElement<?> target = callEntry.getKey();
			if (target != null && target != semanticElement) {
				DiagramElement connectionTarget = semanticMap.get(target);
				if (connectionTarget != null) {
					List<Call> calls = callEntry.getValue();
					Connection connection;
					if (calls.size() == 1) {
						// Single relationship
						Call call= calls.get(0);						
						connection = diagramFactory.createConnection();
						String name = call.getName();
						if (!Util.isBlank(name)) {
							Link link = diagramFactory.createLink();
							link.setText(name);
							link.setLocation(getCallLocation(call));
							link.setTooltip(getCallTooltip(call));
							connection.getDescription().add(link);
						}
					} else {
						// Grouped relationship
						connection = createGroupConnection(semanticElement, target, calls);
					}
					connections.add(connection);
					connection.setTarget(connectionTarget);
					connection.setType("-right${style}->");
					connection.setColor("black");
				}
			}
		}

		// Flow elements
		if (semanticElement instanceof Flow) {
			for (FlowElement<?> child: ((Flow) semanticElement).getElements().values()) {
				if (semanticMap.containsKey(child)) {
					wire(child, semanticMap);
				}
			}
		}
	}
	
	/**
	 * Creates a connection between to elements from a group of relationships. 
	 * @param source
	 * @param target
	 * @param relationships
	 * @return
	 */
	protected Connection createGroupConnection(FlowElement<?> source, FlowElement<?> target, List<? extends Transition> transitions) {
		Connection connection = diagramFactory.createConnection();
		connection.setThickness(3);
		// ports # and o to indicate connections to internal things?
		return connection;
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
			FlowElement<?> contextElement,
			int depth) {
		
		org.nasdanika.diagram.DiagramElement ret = diagramFactory.createDiagramElement();
		ret.setColor("E0E0FF");
		
		ret.setText(participant.getName());
		ret.setType("state");
		ret.setLocation(getParticipantLocation(participant));
		ret.setTooltip(getParticipantTooltip(participant));
		
		
		for (FlowElement<?> groupElement: groupElements) {
			DiagramElement diagramElement = createDiagramElement(groupElement, semanticMap, contextElement, depth);
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
