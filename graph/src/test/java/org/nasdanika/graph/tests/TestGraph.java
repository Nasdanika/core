package org.nasdanika.graph.tests;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EcorePackage;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.graph.emf.EObjectNode;
import org.nasdanika.graph.emf.Util;
import org.nasdanika.graph.processor.ProcessorInfo;


public class TestGraph {
		
	@Test
	public void testECoreGraph() {
		List<EObjectNode> nodes = Util.load(Collections.singleton(EcorePackage.eINSTANCE));
		
		GraphProcessorFactory processorFactory = new GraphProcessorFactory();
		ProgressMonitor progressMonitor = new NullProgressMonitor(); // new PrintStreamProgressMonitor();
		Registry registry = processorFactory.createProcessors(nodes, progressMonitor);
		
		List<JSONObject> jNodes = new ArrayList<>();
		List<JSONObject> jLinks = new ArrayList<>();		
		
		registry
			.infoMap()
			.values()
			.stream()
			.map(ProcessorInfo::getProcessor)			
			.filter(Objects::nonNull)
			.map(Supplier::get)
			.forEach(r -> {
				jNodes.add(r.node());
				jLinks.addAll(r.links());
			});
		
		JSONObject result = new JSONObject();
		result.put("nodes", jNodes);
		result.put("links", jLinks);
		System.out.println(result.toString(4));
	}
	
}
