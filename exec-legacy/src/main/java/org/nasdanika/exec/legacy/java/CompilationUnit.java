package org.nasdanika.exec.legacy.java;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ListCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.Merger;
import org.nasdanika.exec.legacy.java.Package.PackageInfo;
import org.nasdanika.exec.legacy.resources.File;
import org.nasdanika.exec.legacy.resources.ReconcileAction;

public class CompilationUnit extends File {

	static final String IMPORTS_KEY = "imports";
	private static final String FORMAT_KEY = "format";

	protected List<String> imports = new ArrayList<>();
	protected boolean format = true;
	
	@SuppressWarnings("unchecked")
	public CompilationUnit(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
		Map<String, Object> configMap = (Map<String, Object>) config;
		Util.loadMultiString(configMap, IMPORTS_KEY, imports::add);
		if (configMap.containsKey(FORMAT_KEY)) {
			format = Boolean.TRUE.equals(configMap.get(FORMAT_KEY));
		}
	}

	public CompilationUnit(Marker marker, 
			String name, 
			ReconcileAction reconcileAction, 
			SupplierFactory<InputStream> contents, 
			Merger merger,
			List<String> imports,
			boolean format) {
		
		super(marker, name, reconcileAction, contents, merger);
		if (imports != null) {
			this.imports.addAll(imports);		
		}
		this.format = format;
	}
	
	@Override
	protected Collection<String> getSupportedKeys() {
		Collection<String> supportedKeys = super.getSupportedKeys();
		supportedKeys.add(IMPORTS_KEY);
		supportedKeys.add(FORMAT_KEY);
		return supportedKeys;
	}
		
	@Override
	protected Merger getNativeMerger(Context context) {
		return JavaMerger.INSTANCE;
	}
	
	/**
	 * Formats compilation unit using JDT {@link CodeFormatter}.
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String format(String content) throws Exception {
		CodeFormatter formatter = ToolFactory.createCodeFormatter(null); // possibly TODO - options from the configuration.
		if (formatter != null) {
			TextEdit formatted = formatter.format(
					CodeFormatter.K_COMPILATION_UNIT, 
					content, 
					0,
					content.length(),
					0, 
					System.lineSeparator());
			
			IDocument document = new Document(content);
			formatted.apply(document);
			return document.get();
		}
		return content;
	}
	
	private static final String JAVA_EXTENSION = ".java";
	
	@Override
	protected String finalName(String name) {
		return name.endsWith(JAVA_EXTENSION) ? name : name + JAVA_EXTENSION;
	}
	
	@Override
	protected SupplierFactory<InputStream> getContents() {
		SupplierFactory<InputStream> headerFactory = new SupplierFactory<InputStream>() {

			@Override
			public Supplier<InputStream> create(Context context) throws Exception {
				return new Supplier<InputStream>() {

					@Override
					public double size() {
						return 1;
					}

					@Override
					public String name() {
						return "Package and imports declarations";
					}

					@Override
					public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
						StringBuilder contentBuilder = new StringBuilder();
						contentBuilder
							.append("package ")
							.append(context.get(PackageInfo.class).getName())
							.append(";")
							.append(System.lineSeparator())
							.append(System.lineSeparator());
						
						String lastFirstPackageSegment = null;
						for (String ie: context.get(ImportManager.class).getImports()) {
							int dotIdx = ie.indexOf('.');
							String fps = ie.substring(0, dotIdx);
							if (lastFirstPackageSegment != null && !lastFirstPackageSegment.equals(fps)) {
								contentBuilder.append(System.lineSeparator());
							}
							contentBuilder.append("import ").append(ie).append(";").append(System.lineSeparator());
						}
						contentBuilder.append(System.lineSeparator());
						
						return Util.toStream(context, contentBuilder.toString());
					}
					
				};
			}
			
		};
		
		ListCompoundSupplierFactory<InputStream> bodyAndHeaderFactory = new ListCompoundSupplierFactory<InputStream>("Body and Header", super.getContents(), headerFactory);
		
		FunctionFactory<InputStream, InputStream> formatFactory = new FunctionFactory<InputStream, InputStream>() {

			@Override
			public Function<InputStream, InputStream> create(Context context) throws Exception {
				return new Function<InputStream, InputStream>() {

					@Override
					public double size() {
						return 1;
					}

					@Override
					public String name() {
						return "Compilation unit formatter";
					}

					@Override
					public InputStream execute(InputStream content, ProgressMonitor progressMonitor) throws Exception {
						return Util.toStream(context, format(Util.toString(context, content)));
					}
				};
			}
			
		};
		
		FunctionFactory<List<InputStream>, InputStream> reverseJoinFactory = context -> new Function<List<InputStream>,InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Reverse join";
			}

			@Override
			public InputStream execute(List<InputStream> inputs, ProgressMonitor progressMonitor) throws Exception {
				return Util.join(inputs.get(1), inputs.get(0));
			}
			
		};
		
		return bodyAndHeaderFactory.then(reverseJoinFactory).then(formatFactory);
	}
	
	@Override
	public Consumer<BinaryEntityContainer> create(Context context) throws Exception {
		PackageInfo packageInfo = context.get(PackageInfo.class);
		List<String> pTypesObj = packageInfo.getTypes(); // List of short names of types defined in the containing package. TODO - fill out by types.
		Set<String> implicitImports = new HashSet<>();
		for (Object pType: (Iterable<?>) pTypesObj) {
			implicitImports.add(packageInfo.getName()+"."+pType);
		}

		ImportManager importManager = new SimpleImportManager(implicitImports);
		Member.flatten(context, imports).forEach(importManager::addImport);
		
		Context ctx = Context.singleton("import", importManager)
				.compose(Context.singleton(ImportManager.class, importManager))
				.compose(context);
		
		return super.create(ctx);
	}	

}
