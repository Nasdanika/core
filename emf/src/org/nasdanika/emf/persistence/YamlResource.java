package org.nasdanika.emf.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.Context;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.ConfigurationException;
import org.nasdanika.common.persistence.MarkerImpl;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.persistence.Storable;
import org.nasdanika.emf.EObjectAdaptable;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.error.MarkedYAMLException;
import org.yaml.snakeyaml.Yaml;

/**
 * Loads EMF classes from YAML using {@link ObjectLoader}s. 
 * Load-only, saving is not supported. 
 * The idea of this resource is to load models from manually created YAML files or YAML files generated 
 * by some other means. 
 * @author Pavel
 *
 */
public class YamlResource extends ResourceImpl {
	
	private ObjectLoader loader;
	private ProgressMonitor progressMonitor;
	private Context context;

	public YamlResource(URI uri, ObjectLoader loader, Context context, ProgressMonitor progressMonitor) {
		super(uri);
		this.loader = loader;
		this.context = context;
		this.progressMonitor = progressMonitor;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			Object data = loader.loadYaml(inputStream, new URL(getURI().toString()), progressMonitor);
			if (data instanceof Collection) {
				getContents().addAll((Collection<EObject>) data);
			} else {
				if (data instanceof SupplierFactory) {
					EObject eObject = Util.callSupplier(((SupplierFactory<EObject>) data).create(context), progressMonitor);
					getContents().add(eObject);
				} else if (data instanceof EObject) {
					getContents().add((EObject) data);
				} else {
					throw new IOException("Not an instance of EObject: " + data);
				}
			}
		} catch (MarkedYAMLException e) {
			throw new ConfigurationException(e.getMessage(), e, new MarkerImpl(getURI().toString(), e.getProblemMark()));
		} catch (RuntimeException | IOException e) {
			throw e;
		} catch (Exception e) {
			throw new NasdanikaException(e);
		}
	}
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		List<Object> data = new ArrayList<>();
		for (EObject e: getContents()) {
			Storable storable = EObjectAdaptable.adaptTo(e, Storable.class);
			if (storable == null) {
				throw new IOException("Cannot adapt " + e + " to " + Storable.class.getName());
			}
			try {
				data.add(storable.store(new URL(getURI().toString()), progressMonitor));
			} catch (RuntimeException | IOException ex) {
				throw ex;
			} catch (Exception ex) {
				throw new NasdanikaException(ex);
			}
		}
		
		DumperOptions dumperOptions = new DumperOptions();
		dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
		dumperOptions.setIndent(4);
		Yaml yaml = new Yaml(dumperOptions);
		yaml.dump(data.size() == 1 ? data.get(0) : data, new OutputStreamWriter(outputStream));		
	}

	/**
	 * If fragment is null returns the first content element.
	 */
	@Override
	public EObject getEObject(String uriFragment) {
		if (uriFragment == null) {
			return getContents().get(0);
		}
		return super.getEObject(uriFragment);
	}

}