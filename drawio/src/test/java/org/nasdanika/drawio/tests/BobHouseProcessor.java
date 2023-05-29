package org.nasdanika.drawio.tests;

import org.nasdanika.graph.processor.ChildProcessor;

public class BobHouseProcessor implements Runnable {

	@ChildProcessor("label == 'Bob'")
	public BobProcessor bobProcessor;
	
	
	@Override
	public void run() {
		System.out.println("Bob: " + bobProcessor);
	}

}
