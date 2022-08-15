package org.nasdanika.drawio.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.function.Function;
import java.util.function.Supplier;

import org.nasdanika.drawio.Connection;
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
	private Supplier<Function<String,String>> librarySupplier;
	
	private Supplier<ElementProcessorInfo<Function<String,String>>> librarySupplierInfo;
	
	@RegistryEntry(value = "label == 'Library'", info = true)
	public void setLibrarySupplier(Supplier<ElementProcessorInfo<Function<String,String>>> librarySupplierInfo) {
		this.librarySupplierInfo = librarySupplierInfo;
	};
	
	@IncomingHandler("source.label == 'Alice'")
	private Function<String,String> aliceInboundHandler = request -> {
		System.out.println("Request: " + request);
		System.out.println("Request: " + librarySupplier.get().apply(request));
		System.out.println(librarySupplierInfo.get().getProcessor().apply("Hello!"));		
		return request + System.lineSeparator() + "[Bob] Hello, my name is Bob! What is yours?";
	};

	@Override
	public void run() {
		assertNotNull(librarySupplier.get());
		// TODO Assertions here
		
	}

}
