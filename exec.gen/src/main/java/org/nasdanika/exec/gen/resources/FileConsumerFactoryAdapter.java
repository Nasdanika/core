package org.nasdanika.exec.gen.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.CancellationException;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.resources.File;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.nasdanika.resources.BinaryEntity;
import org.nasdanika.resources.BinaryEntityContainer;
import org.nasdanika.resources.Merger;

public class FileConsumerFactoryAdapter extends ResourceConsumerFactoryAdapter<File> {

	public FileConsumerFactoryAdapter(File file) {
		super(file);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == ConsumerFactory.class;
	}
	
	protected SupplierFactory<InputStream> getContentsFactory() {
		ListCompoundSupplierFactory<InputStream> contentsFactory = new ListCompoundSupplierFactory<>("Contents");
		for (EObject ce: ((File) getTarget()).getContents()) {
			contentsFactory.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(ce, InputStream.class), "Cannot adapt to SupplierFactory: " + ce));
		}
		return contentsFactory.then(Util.JOIN_STREAMS_FACTORY);
	}
		
	private ConsumerFactory<Supplier.FunctionResult<BinaryEntityContainer, InputStream>> createFileFactory() {
		File semanticElement = (File) getTarget();
		
		return context -> new Consumer<Supplier.FunctionResult<BinaryEntityContainer, InputStream>>() {

			private String finalName = finalName(context.interpolateToString(semanticElement.getName()));
		
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Create file " + finalName;
			}
	
			@Override
			public void execute(Supplier.FunctionResult<BinaryEntityContainer, InputStream> input, ProgressMonitor progressMonitor) {
				if (!progressMonitor.isCancelled()) {
					switch (semanticElement.getReconcileAction()) {
					case CANCEL:
						if (input.argument().find(finalName, progressMonitor) != null) {
							throw new CancellationException("Cancelling generation - resource '" + finalName + "' already exists in " + input.argument());
						}
					case OVERWRITE: {
						BinaryEntity file = Objects.requireNonNull(input.argument().get(finalName, progressMonitor), "Cannot create file " + finalName + " in " + input.argument());
						if (input.result() != null) {
							file.setState(input.result(), progressMonitor);
						}
						break;
					}
					case KEEP: {
						if (input.argument().find(finalName, progressMonitor) == null) {
							BinaryEntity file = Objects.requireNonNull(input.argument().get(finalName, progressMonitor), "Cannot create file " + finalName + " in " + input.argument());
							if (input.result() != null) {
								file.setState(input.result(), progressMonitor);
							}
						}
						break;
					}
					case APPEND: {
						BinaryEntity file = Objects.requireNonNull(input.argument().get(finalName, progressMonitor), "Cannot create file " + finalName + " in " + input.argument());
						if (input.result() != null) {
							try {
								file.setState(file.exists(progressMonitor) ? Util.join(file.getState(progressMonitor), input.result()) : input.result(), progressMonitor);
							} catch (IOException e) {
								throw new ExecutionException(e, this);
							}
						}
						break;
					}							
					case MERGE: {
						BinaryEntity file = Objects.requireNonNull(input.argument().get(finalName, progressMonitor), "Cannot create file " + finalName + " in " + input.argument());
						if (input.result() != null) {
							if (file.exists(progressMonitor)) {
								file.setState(merge(context, file, file.getState(progressMonitor), input.result(), progressMonitor), progressMonitor);
							} else {
								file.setState(input.result(), progressMonitor);
							}
						}
						break;
					}							
					}
				}
			}
		
		};
	}

	@Override
	public Consumer<BinaryEntityContainer> create(Context iContext) {
		FunctionFactory<BinaryEntityContainer, Supplier.FunctionResult<BinaryEntityContainer, InputStream>> contentFunctionFactory = getContentsFactory().asFunctionFactory();
		return contentFunctionFactory.then(createFileFactory()).create(iContext);
	}
	
	protected InputStream merge(Context context, BinaryEntity entity, InputStream oldContent, InputStream newContent, ProgressMonitor progressMonitor) {
		File semanticElement = (File) getTarget();
		EObject semanticMerger = semanticElement.getMerger();
		Merger merger = semanticMerger == null ? getNativeMerger(context) : Objects.requireNonNull(EObjectAdaptable.adaptTo(semanticMerger, Merger.class));
		if (merger == null) {			
			throw new ConfigurationException("No merger for reconcile action MERGE", EObjectAdaptable.adaptTo(semanticElement, Marked.class));
		}
		return merger.merge(context, entity, oldContent, newContent, progressMonitor);
	}
	
	protected Merger getNativeMerger(Context context) {
		return null;
	}	
	
}
