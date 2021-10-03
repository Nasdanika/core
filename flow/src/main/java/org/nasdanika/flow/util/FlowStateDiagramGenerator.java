package org.nasdanika.flow.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.EList;
import org.nasdanika.common.Util;
import org.nasdanika.diagram.Connection;
import org.nasdanika.diagram.Diagram;
import org.nasdanika.diagram.DiagramElement;
import org.nasdanika.diagram.DiagramFactory;
import org.nasdanika.diagram.Link;
import org.nasdanika.flow.Activity;
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
		for (Entry<String, FlowElement<?>> fe: flow.getElements()) {
			DiagramElement diagramElement = createDiagramElement(fe.getKey(), fe.getValue(), semanticMap);
			if (diagramElement != null) {
				ret.getElements().add(diagramElement);
			}
		}
		
		for (Entry<String, FlowElement<?>> fe: flow.getElements()) {
			wire(fe.getKey(), fe.getValue(), semanticMap);
		}
		
		// Auto start/end
		
		return ret;
	}
	
	public Diagram generateActivityContextDiagram(Activity<?> activity) {
//		Diagram ret = createDiagram(activity);
		throw new UnsupportedOperationException();
//		return ret;
	}
	
	protected DiagramElement createDiagramElement(
			String key, 
			FlowElement<?> value, 
			Map<FlowElement<?>, DiagramElement> semanticMap) {
		
		if (value instanceof org.nasdanika.flow.Start) {
			org.nasdanika.diagram.Start ret = diagramFactory.createStart();
			semanticMap.put(value, ret);
			return ret;
		}
		
		if (value instanceof org.nasdanika.flow.End) {
			org.nasdanika.diagram.End ret = diagramFactory.createEnd();
			semanticMap.put(value, ret);
			return ret;
		}
		
		org.nasdanika.diagram.DiagramElement ret = diagramFactory.createDiagramElement();
		semanticMap.put(value, ret);
		ret.setType("state");
		
		if (value instanceof PseudoState) {
			if (value instanceof Choice) {
				ret.setStereotype("choice");
			} else if (value instanceof EntryPoint) { 
				ret.setStereotype("entryPoint");
			} else if (value instanceof ExitPoint) { 
				ret.setStereotype("exitPoint");
			} else if (value instanceof ExpansionInput) { 
				ret.setStereotype("expansionInput");
			} else if (value instanceof ExpansionOutput) { 
				ret.setStereotype("expansioinOutput");
			} else if (value instanceof Fork) { 
				ret.setStereotype("fork");
			} else if (value instanceof InputPin) { 
				ret.setStereotype("inputPin");
			} else if (value instanceof Join) { 
				ret.setStereotype("join");
			} else if (value instanceof OutputPin) { 
				ret.setStereotype("outputPin");				
			}
		} else {
			ret.setText(value.getName());
			ret.setLocation(getFlowElementLocation(key, value));
			ret.setTooltip(getFlowElementTooltip(key, value));
		}
		EList<String> modifiers = value.getModifiers();
		if (modifiers.contains("final")) {
			ret.setBold(true);
		} else if (modifiers.contains("abstract")) {
			ret.setDashed(true);
		}
		if (modifiers.contains("optional")) {
			ret.setColor("grey");
		}
		return ret;
	}
	
	protected String getFlowElementLocation(String key, FlowElement<?> flowElement) {
		return null;
	}
	
	protected String getFlowElementTooltip(String key, FlowElement<?> flowElement) {
		return null;
	}
	
	protected void wire(String key, FlowElement<?> value, Map<FlowElement<?>, DiagramElement> semanticMap) {
		// Transitions
		for (Transition output: value.getOutputs().values()) {
			Connection transition = diagramFactory.createConnection();
			semanticMap.get(value).getConnections().add(transition);
			transition.setTarget(semanticMap.get(output.getTarget()));
			transition.setType("-->");
			String name = output.getName();
			if (!Util.isBlank(name)) {
				Link link = diagramFactory.createLink();
				link.setText(name);
				link.setLocation(getTransitionLocation(key, output));
				link.setTooltip(getTransitionTooltip(key, output));
				transition.getDescription().add(link);
			}
		}
		
		// Calls
		for (Call call: value.getCalls().values()) {
			Connection connection = diagramFactory.createConnection();
			semanticMap.get(value).getConnections().add(connection);
			connection.setTarget(semanticMap.get(call.getTarget()));
			connection.setType("-left${style}->");
			connection.setColor("black");
			String name = call.getName();
			if (!Util.isBlank(name)) {
				Link link = diagramFactory.createLink();
				link.setText(name);
				link.setLocation(getCallLocation(key, call));
				link.setTooltip(getCallTooltip(key, call));
				connection.getDescription().add(link);
			}
		}
	}
		
	protected String getTransitionLocation(String key, Transition transition) {
		return null;
	}
	
	protected String getTransitionTooltip(String key, Transition transition) {
		return null;
	}
	
	protected String getCallLocation(String key, Call call) {
		return null;
	}
	
	protected String getCallTooltip(String key, Call call) {
		return null;
	}
	

	protected Diagram createDiagram(Activity<?> activity) {
		return diagramFactory.createDiagram();
//		diagram.setHideEmptyDescription(true);
	}

}
