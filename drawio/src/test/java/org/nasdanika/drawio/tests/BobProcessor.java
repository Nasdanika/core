package org.nasdanika.drawio.tests;

import static org.junit.Assert.assertNotNull;

import java.util.function.Function;

import org.nasdanika.graph.processor.ElementProcessorInfo;
import org.nasdanika.graph.processor.IncomingHandler;
import org.nasdanika.graph.processor.RegistryEntry;

public class BobProcessor implements Runnable {
	
//	@IncomingHandler("source.label == 'Alice'")
//	public Function<String,String> aliceInboundHandler(Connection connection) { 
//		return request -> {
//			return request + System.lineSeparator() + " [Bob] Hello, my name is Bob! What is yours?";
//		};
//	} 
	
	@RegistryEntry("label == 'Library'")
	private Function<String,String> library;
	
	private ElementProcessorInfo<Function<String,String>> libraryInfo;
	
	@RegistryEntry(value = "label == 'Library'", info = true)
	public void setLibrary(ElementProcessorInfo<Function<String,String>> libraryInfo) {
		this.libraryInfo = libraryInfo;
	};
	
	@IncomingHandler("source.label == 'Alice'")
	private Function<String,String> aliceInboundHandler = request -> {
		System.out.println("Request: " + request);
		System.out.println("Request: " + library.apply(request));
		System.out.println(libraryInfo.getProcessor().apply("Hello!"));		
		return request + System.lineSeparator() + "[Bob] Hello, my name is Bob! What is yours?";
	};

	@Override
	public void run() {
		assertNotNull(library);
		// TODO Assertions here
		
	}

}
