package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.graph.processor.SourceEndpoint;
import org.nasdanika.graph.processor.SourceHandler;
import org.nasdanika.graph.processor.TargetEndpoint;
import org.nasdanika.graph.processor.TargetHandler;

public class AliceBobConnectionProcessor {
	
	@SourceEndpoint
	public Function<String,String> sourceEndpoint;
	
	@TargetEndpoint
	public Function<String,String> targetEndpoint;
	
	@SourceHandler
	public Function<String,String> sourceHandler = request -> ">> " + targetEndpoint.apply(request);
	
	@TargetHandler
	public Function<String,String> targetHandler = response -> "<< " + sourceEndpoint.apply(response);	
	
}
