package org.nasdanika.exec.resources;

import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CancellationException;

import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.exec.Loader;

public class Container extends Resource {
	
	private ConsumerFactory<BinaryEntityContainer> contents;
	
	@SuppressWarnings("unchecked")
	public Container(ObjectLoader loader, String type, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, type, config, base, progressMonitor, marker);
		Map<String,Object> configMap = (Map<String,Object>) config;
		if (configMap.containsKey(CONTENTS_KEY)) {
			contents = Loader.asConsumerFactory(loader.load(configMap.get(CONTENTS_KEY), base, progressMonitor));
		}
	}
	
	private FunctionFactory<BinaryEntityContainer, BinaryEntityContainer> containerFactory = context -> new Function<BinaryEntityContainer, BinaryEntityContainer>() {
		
		private String finalName = finalName(context.interpolateToString(Container.this.name));

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Create container " + finalName;
		}

		@Override
		public BinaryEntityContainer execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
			BinaryEntityContainer ret = Objects.requireNonNull(container.getContainer(finalName, progressMonitor), "Cannot create container " + finalName + " in " + container);
			switch (reconcileAction) {
			case OVERWRITE:
				if (ret.exists(progressMonitor)) {
					ret.delete(progressMonitor);
				}
			case MERGE:
			case APPEND:
				return ret;
			case CANCEL:
				if (ret.exists(progressMonitor)) {
					throw new CancellationException("Cancelling generation - container '" + finalName + "' already exists in " + container);
				}
				return ret;
			case KEEP:
				if (ret.exists(progressMonitor)) {
					return null; // Indicates that elements factory shall not do anything.
				}
				return ret;
			}
			return ret;
		}
		
	};
	
	private ConsumerFactory<BinaryEntityContainer> conditionalFactory = new ConsumerFactory<BinaryEntityContainer>() {

		@Override
		public Consumer<BinaryEntityContainer> create(Context context) throws Exception {
			Consumer<BinaryEntityContainer> contentsConsumer = contents.create(context);
			
			return new Consumer<BinaryEntityContainer>() {
				
				@Override
				public double size() {
					return contentsConsumer.size();
				}
				
				@Override
				public String name() {
					return contentsConsumer.name();
				}
				
				@Override
				public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
					if (container != null) {
						contentsConsumer.execute(container, progressMonitor);
					}						
				}
			};
		}
		
	};	
	
	@Override
	public Consumer<BinaryEntityContainer> create(Context iContext) throws Exception {		
		return containerFactory.then(conditionalFactory).create(iContext);
	}
	

}
