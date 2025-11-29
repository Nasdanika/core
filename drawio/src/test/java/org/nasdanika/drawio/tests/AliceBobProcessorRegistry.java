package org.nasdanika.drawio.tests;

import java.util.function.Function;

import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.graph.processor.RegistryEntry;
import org.nasdanika.graph.processor.RegistryEntry.Type;

public class AliceBobProcessorRegistry {
	
	@RegistryEntry("label == 'Alice'")
	public AliceProcessor aliceProcessor;
	
	
	@RegistryEntry("label == 'Bob'")
	public ProcessorInfo<Function<String,String>,Function<String,String>,String,BobProcessor> bobInfo;	
	
	@RegistryEntry("label == 'Bob''s House'")
	public void setBobHouseProcessor(Runnable bobHouse) {
		bobHouse.run();
//		throw new NasdanikaException("Not so fast!");
	}
		
	@RegistryEntry(
			value = "label == 'Bob'", 
			type = Type.ENDPOINT,
			clientKey = "'registry-endpoint'")
	public void setBobEndpoint(Object endpoint) {
		System.out.println("Bob endpoint: " + endpoint);
	}
		
	@RegistryEntry(
			value = "label == 'Bob'", 
			type = Type.ENDPOINT,
			clientKey = "'registry-endpoint-2'")
	public void setBobEndpoint(String clientKey, Object endpoint) {
		System.out.println("Bob endpoint: " + clientKey + " " + endpoint);
	}
		
	@RegistryEntry(
			value = "label == 'Bob'", 
			type = Type.HANDLER,
			clientKey = "'registry-handler'")
	public Function<String,String> getBobHandler() {
		System.out.println("Bob handler");
		return str -> "{registry Bob handler} " + str;
	}
			
	@RegistryEntry(
			value = "label == 'Bob'", 
			type = Type.HANDLER,
			clientKey = "'registry-handler-2'")
	public Function<String,String> getBobHandler(String clientKey) {
		System.out.println("Bob handler for " + clientKey);
		return str -> "{registry Bob handler %s} ".formatted(clientKey) + str;
	}
	
	@RegistryEntry(
			value = "label == 'Bob'", 
			type = Type.HANDLER,
			proxy = Function.class,
			clientKey = "'registry-proxy-handler'")
	public String bobProxyHandler(String arg) {
		System.out.println("Bob proxy handler");
		return "{registry Bob proxy handler} " + arg;
	}

}
