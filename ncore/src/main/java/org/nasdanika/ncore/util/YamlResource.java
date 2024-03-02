package org.nasdanika.ncore.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.persistence.Marked;
import org.nasdanika.persistence.Marker;
import org.nasdanika.persistence.MarkingYamlConstructor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.Mark;
import org.yaml.snakeyaml.error.MarkedYAMLException;
import org.yaml.snakeyaml.error.YAMLException;

public abstract class YamlResource  extends ResourceImpl {

	protected YamlResource(URI uri) {
		super(uri);
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		Yaml yaml = MarkingYamlConstructor.createMarkingYaml(getURI() == null ? null : getURI().toString());
		try {
			Z: for (Object yamlContent: yaml.loadAll(inputStream)) {
				for (YamlHandler handler: getHandlers()) {
					Optional<EObject> copt = handler.load(this, yamlContent);
					if (copt.isPresent()) {
						getContents().add(copt.get());
						continue Z;
					}
				}
				getErrors().add(new Diagnostic() {
					
					@Override
					public String getMessage() {
						return "No handler to load " + yamlContent;
					}
					
					@Override
					public String getLocation() {
						if (yamlContent instanceof Marked) {
							for (Marker marker: ((Marked) yamlContent).getMarkers()) {
								return marker.getLocation() + " " + marker.getPosition();
							}
						}
						return getURI() == null ? null : getURI().toString();
					}
					
					@Override
					public int getLine() {
						return 0;
					}
					
					@Override
					public int getColumn() {
						return 0;
					}
					
				});				
			}
		} catch (YAMLException e) {
			getErrors().add(new Diagnostic() {
				
				@Override
				public String getMessage() {
					return e.toString();
				}
				
				@Override
				public String getLocation() {
					return getURI() == null ? null : getURI().toString();
				}
				
				@Override
				public int getLine() {
					if (e instanceof MarkedYAMLException) {
						MarkedYAMLException me = (MarkedYAMLException) e;
						Mark problemMark = me.getProblemMark();
						if (problemMark != null) {
							return problemMark.getLine();
						}
					}
					return 0;
				}
				
				@Override
				public int getColumn() {
					if (e instanceof MarkedYAMLException) {
						MarkedYAMLException me = (MarkedYAMLException) e;
						Mark problemMark = me.getProblemMark();
						if (problemMark != null) {
							return problemMark.getLine();
						}
					}
					return 0;
				}
			});
		}
	}
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK); dumperOptions.setIndent(4);
		List<Object> toDump = new ArrayList<>();
		Z: for (EObject eObj: getContents()) {
			for (YamlHandler handler: getHandlers()) {
				Optional<Object> oopt = handler.save(this, eObj);
				if (oopt.isPresent()) {
					toDump.add(oopt.get());
					continue Z;
				}
			}
			getErrors().add(new Diagnostic() {
				
				@Override
				public String getMessage() {
					return "No handler to save " + eObj;
				}
				
				@Override
				public String getLocation() {
					if (eObj instanceof Marked) {
						for (Marker marker: ((Marked) eObj).getMarkers()) {
							return marker.getLocation() + " " + marker.getPosition();
						}
					}
					return getURI() == null ? null : getURI().toString();
				}
				
				@Override
				public int getLine() {
					return 0;
				}
				
				@Override
				public int getColumn() {
					return 0;
				}
				
			});				
		}
		
		try (Writer writer = new OutputStreamWriter(outputStream)) {
			new Yaml(dumperOptions).dumpAll(toDump.iterator(), writer);
		}
	}
	
	protected abstract Iterable<YamlHandler> getHandlers();
	
}
