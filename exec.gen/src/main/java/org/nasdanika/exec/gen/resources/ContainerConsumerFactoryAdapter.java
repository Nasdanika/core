package org.nasdanika.exec.gen.resources;

import java.util.Objects;
import java.util.concurrent.CancellationException;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.CollectionCompoundConsumerFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.FilterConsumer;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.resources.Container;
import org.nasdanika.resources.BinaryEntityContainer;

public class ContainerConsumerFactoryAdapter extends ResourceConsumerFactoryAdapter<Container> {

	public ContainerConsumerFactoryAdapter(Container container) {
		super(container);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == ConsumerFactory.class;
	}	

	@Override
	public Consumer<BinaryEntityContainer> create(Context context) {
		return createContainerFactory().then(createContentsFactory()).create(context);
	}

	protected FunctionFactory<BinaryEntityContainer, BinaryEntityContainer> createContainerFactory() {
		Container semanticElement = (Container) getTarget();
		
		return context -> new Function<BinaryEntityContainer, BinaryEntityContainer>() {
			
			private String finalName = finalName(context.interpolateToString(semanticElement.getName()));

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Create container " + finalName;
			}

			@Override
			public BinaryEntityContainer execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) {
				if (progressMonitor.isCancelled()) {
					return null;
				}
				
				BinaryEntityContainer ret = Objects.requireNonNull(container.getContainer(finalName, progressMonitor), "Cannot create container " + finalName + " in " + container);
				switch (semanticElement.getReconcileAction()) {
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
	}

	protected ConsumerFactory<BinaryEntityContainer> createContentsFactory() {
		Container container = (Container) getTarget();
		
		CollectionCompoundConsumerFactory<BinaryEntityContainer> contentsFactory = new CollectionCompoundConsumerFactory<>("Contents");
		for (EObject ce: container.getContents()) {
			contentsFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(ce, BinaryEntityContainer.class), "Cannot adapt to ConsumerFactory"));
		}
		
		return new ConsumerFactory<BinaryEntityContainer>() {		
		
			@Override
			public Consumer<BinaryEntityContainer> create(Context context) {
				Consumer<BinaryEntityContainer> contentsConsumer = contentsFactory.create(context);
				
				return new FilterConsumer<BinaryEntityContainer>(contentsConsumer) {
					
					@Override
					public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) {
						if (container != null) {
							contentsConsumer.execute(container, progressMonitor);
						}						
					}
					
				};
			}
			
		};
	}

}
