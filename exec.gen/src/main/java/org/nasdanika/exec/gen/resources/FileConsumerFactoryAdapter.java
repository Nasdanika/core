package org.nasdanika.exec.gen.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.concurrent.CancellationException;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.Merger;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.resources.File;

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
		
	private ConsumerFactory<BiSupplier<BinaryEntityContainer, InputStream>> createFileFactory() {
		File semanticElement = (File) getTarget();
		
		return context -> new Consumer<BiSupplier<BinaryEntityContainer, InputStream>>() {

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
			public void execute(BiSupplier<BinaryEntityContainer, InputStream> input, ProgressMonitor progressMonitor) {
				if (!progressMonitor.isCancelled()) {
					switch (semanticElement.getReconcileAction()) {
					case CANCEL:
						if (input.getFirst().find(finalName, progressMonitor) != null) {
							throw new CancellationException("Cancelling generation - resource '" + finalName + "' already exists in " + input.getFirst());
						}
					case OVERWRITE: {
						BinaryEntity file = Objects.requireNonNull(input.getFirst().get(finalName, progressMonitor), "Cannot create file " + finalName + " in " + input.getFirst());
						if (input.getSecond() != null) {
							file.setState(input.getSecond(), progressMonitor);
						}
						break;
					}
					case KEEP: {
						if (input.getFirst().find(finalName, progressMonitor) == null) {
							BinaryEntity file = Objects.requireNonNull(input.getFirst().get(finalName, progressMonitor), "Cannot create file " + finalName + " in " + input.getFirst());
							if (input.getSecond() != null) {
								file.setState(input.getSecond(), progressMonitor);
							}
						}
						break;
					}
					case APPEND: {
						BinaryEntity file = Objects.requireNonNull(input.getFirst().get(finalName, progressMonitor), "Cannot create file " + finalName + " in " + input.getFirst());
						if (input.getSecond() != null) {
							try {
								file.setState(file.exists(progressMonitor) ? Util.join(file.getState(progressMonitor), input.getSecond()) : input.getSecond(), progressMonitor);
							} catch (IOException e) {
								throw new ExecutionException(e, this);
							}
						}
						break;
					}							
					case MERGE: {
						BinaryEntity file = Objects.requireNonNull(input.getFirst().get(finalName, progressMonitor), "Cannot create file " + finalName + " in " + input.getFirst());
						if (input.getSecond() != null) {
							if (file.exists(progressMonitor)) {
								file.setState(merge(context, file, file.getState(progressMonitor), input.getSecond(), progressMonitor), progressMonitor);
							} else {
								file.setState(input.getSecond(), progressMonitor);
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
		FunctionFactory<BinaryEntityContainer, BiSupplier<BinaryEntityContainer, InputStream>> contentFunctionFactory = getContentsFactory().asFunctionFactory();
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
