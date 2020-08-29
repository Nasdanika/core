package org.nasdanika.exec.resources;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntity;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.exec.Loader;

public class ZipResourceCollection extends ResourceCollection {
	
	private Collection<SupplierFactory<InputStream>> contents = new ArrayList<>();

	@SuppressWarnings("unchecked")
	public ZipResourceCollection(ObjectLoader loader, Object config, URL base,	ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		Map<String,Object> configMap = (Map<String,Object>) config;
		Object contentsVal = configMap.get(CONTENTS_KEY);
		if (contentsVal instanceof Collection) {
			int idx = 0;
			for (Object ce: (Collection<?>) contentsVal) {
				contents.add(Loader.asSupplierFactory(loader.load(ce, base, progressMonitor), Util.getMarker((Collection<?>) contentsVal, idx++)));
			}
		} else {
			contents.add(Loader.asSupplierFactory(loader.load(contentsVal, base, progressMonitor), Util.getMarker(configMap, CONTENTS_KEY)));
		} 
	}	
	
	public ZipResourceCollection(
			Marker marker, 
			ReconcileAction reconcileAction, 
			String path, 
			String prefix,
			Collection<String> includes, 
			Collection<String> excludes, 
			Collection<String> interpolationIncludes,
			Collection<String> interpolationExcludes, 
			Collection<SupplierFactory<InputStream>> contents) {
		super(marker, reconcileAction, path, prefix, includes, excludes, interpolationIncludes, interpolationExcludes);
		this.contents.addAll(contents);
	}

	private ConsumerFactory<BiSupplier<BinaryEntityContainer, List<InputStream>>> extractFactory = context -> new Consumer<BiSupplier<BinaryEntityContainer,List<InputStream>>>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Extracting zip archive";
		}

		@Override
		public void execute(BiSupplier<BinaryEntityContainer, List<InputStream>> inputs, ProgressMonitor progressMonitor) throws Exception {
			for (InputStream in: inputs.getSecond()) {
				extract(context, in, inputs.getFirst(), progressMonitor);
			}
		}
	};

	@Override
	public Consumer<BinaryEntityContainer> create(Context iContext) throws Exception {
		ListCompoundSupplierFactory<InputStream> contentFactory = new ListCompoundSupplierFactory<>("Contents");
		for (SupplierFactory<InputStream> ce: contents) {
			contentFactory.add(ce);
		}
		
		FunctionFactory<BinaryEntityContainer, BiSupplier<BinaryEntityContainer, List<InputStream>>> contentFunctionFactory = contentFactory.asFunctionFactory();
		return contentFunctionFactory.then(extractFactory).create(iContext);
	}
	
	protected void extract(Context context, InputStream in, BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
		String path = context.interpolateToString(this.path); 
		String prefix = context.interpolateToString(this.prefix); 
		try (ZipInputStream zipInputStream = new ZipInputStream(in)) {
			ZipEntry zipEntry;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) {
				String entryName = zipEntry.getName();
				if (include(entryName) && (Util.isBlank(path) || entryName.startsWith(path.trim()))) {
					try (ProgressMonitor entryMonitor = progressMonitor.split("Entry "+zipEntry.getName(), 1, zipEntry)) {
						String entryRelativePath = Util.isBlank(path) ? entryName : entryName.substring(path.trim().length());
						String targetPath = prefix == null || prefix.trim().length() == 0 ? entryRelativePath : prefix.trim()+entryRelativePath;										
						if (entryName.endsWith("/")) {
							if (container.getContainer(targetPath.substring(0, targetPath.length() - 1), entryMonitor.split("Getting container "+targetPath, 1)) == null) {
								throw new IOException("Container with path "+targetPath+" cannot be created");
							}
						} else {
							BinaryEntity entity = container.get(targetPath, progressMonitor.split("Getting target entity "+targetPath, 1));
							if (entity == null) {
								throw new IllegalArgumentException("Cannot obtain entity at "+targetPath);
							}
							
							
							// Overriding close because set state closes the source stream when it is drained.
							InputStream entryStream = new FilterInputStream(zipInputStream) { 

								public void close() {
									
								}
								
							};
							
							if (entity.exists(progressMonitor.split("Checking existence of "+targetPath, 1))) {
								switch (reconcileAction) {
								case APPEND:
									entity.appendState(interpolate(context, entryRelativePath, entryStream), progressMonitor.split("Appending state", 1, entity));
									break;
								case MERGE:
//									String mergerClass = getMerger();
//									Merger<InputStream> merger;
//									if (mergerClass == null || mergerClass.trim().length() == 0) {
//										throw new IllegalStateException("Merger is not set");
//									} else {
//										merger = (Merger<InputStream>) instantiate(context, mergerClass, getMergerArguments());
//									}
//									InputStream oldContent = entity.getState(progressMonitor.split("Getting state", 1, entity));
//									InputStream mergedContents = merger.merge(context, entity, oldContent, interpolate(context, entryRelativePath, entryStream, progressMonitor), progressMonitor.split("Merging", 1, entity));
//									entity.setState(mergedContents, progressMonitor.split("Setting state", 1, entity));
//									break;
									throw new UnsupportedOperationException("Merging is not yet supported");
								case CANCEL:
									throw new CancellationException("Operation cancelled - entity already exists: "+targetPath);
								case KEEP:
									// Take no action
									break;
								case OVERWRITE:
									entity.setState(interpolate(context, entryRelativePath, entryStream), progressMonitor.split("Setting state", 1, entity));
									break;
								default:
									throw new IllegalStateException("Unsupported reconcile action: "+reconcileAction);
								}
							} else {
								entity.setState(interpolate(context, entryRelativePath, entryStream), progressMonitor.split("Setting state", 1, entity));					
							}
						}
						zipInputStream.closeEntry();
					}
				}
			}						
		}
	}
	
}
