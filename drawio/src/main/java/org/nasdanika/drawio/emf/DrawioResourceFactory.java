package org.nasdanika.drawio.emf;

import java.util.function.Function;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.persistence.Marker;

public class DrawioResourceFactory extends ResourceFactoryImpl {
		
	@Override
	public Resource createResource(URI uri) {
		return new DrawioResource(uri) {

			@Override
			protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
				return DrawioResourceFactory.this.getMarkerFactory();
			}

			@Override
			protected ModelFactory getFactory() {
				return DrawioResourceFactory.this.getFactory();
			}
			
		};
	}

	protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
		return null;
	}

	protected ModelFactory getFactory() {
		return org.nasdanika.drawio.model.ModelFactory.eINSTANCE;
	}
	
}
