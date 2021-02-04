package org.nasdanika.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.ObjectLoader;

/**
 * Loads EMF classes from YAML using {@link ObjectLoader}s. 
 * Load-only, saving is not supported. 
 * The idea of this resource is to load models from manually created YAML files or YAML files generated 
 * by some other means. 
 * @author Pavel
 *
 */
public class YamlResourceImpl extends ResourceImpl {
	
	private ObjectLoader loader;
	private ProgressMonitor progressMonitor;

	public YamlResourceImpl(URI uri, ObjectLoader loader, ProgressMonitor progressMonitor) {
		super(uri);
		this.loader = loader;
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
				getContents().add((EObject) data);
			}
		} catch (RuntimeException | IOException e) {
			throw e;
		} catch (Exception e) {
			throw new NasdanikaException(e);
		}
	}
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		throw new UnsupportedOperationException("Saving is not supported");
	}

}