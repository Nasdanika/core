package org.nasdanika.core.tests.exec;

import java.net.URL;
import java.util.function.Consumer;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;

/**
 * Creates a consumer invoked on Git push doing some processing and storing results of the processing in a 
 * context property of type {@link Consumer}. Property name is passed in configuration.
 * @author Pavel
 *
 */
public class OnPushConsumerFactory implements ConsumerFactory<BinaryEntityContainer>, Marked {
	
	private Marker marker;
	private String consumerProperty;

	public OnPushConsumerFactory(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		if (config instanceof String) {
			consumerProperty = (String) config;
		}
	}	
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	@Override
	public org.nasdanika.common.Consumer<BinaryEntityContainer> create(Context context) throws Exception {
		return new org.nasdanika.common.Consumer<BinaryEntityContainer>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "On push";
			}

			@Override
			public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
				if (consumerProperty != null) {
					@SuppressWarnings("unchecked")
					Consumer<Object> resultConsumer = context.get(consumerProperty, Consumer.class);
					if (resultConsumer != null) {
						resultConsumer.accept("*** On push: " + container);
					}
				}
			}
			
		};
	}
	
}
