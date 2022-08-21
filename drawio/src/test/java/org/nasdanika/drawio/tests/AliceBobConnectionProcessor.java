package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.graph.processor.SourceEndpoint;
import org.nasdanika.graph.processor.SourceHandler;
import org.nasdanika.graph.processor.TargetEndpoint;
import org.nasdanika.graph.processor.TargetHandler;

public class AliceBobConnectionProcessor {
	
	@SourceEndpoint
	Function<String,String> sourceEndpoint;
	
	@TargetEndpoint
	Function<String,String> targetEndpoint;
	
	@SourceHandler
	Function<String,String> sourceHandler = request -> {
		return ">> " + targetEndpoint.apply(request);
	};
	
	@TargetHandler
	Function<String,String> targetHandler = response -> {
		return "<< " + sourceEndpoint.apply(response);	
	};
	
}
