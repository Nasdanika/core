package org.nasdanika.capability.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Factory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;

public class GzipBinaryResourceFactoryCapabilityFactory extends ResourceFactoryCapabilityFactory {

	public static final String GZIP_BINARY_RESOURCE_EXTENSION = "egz";

	@Override
	protected Factory getResourceFactory(ResourceSet resourceSet) {
		return new Resource.Factory() {

			@Override
			public Resource createResource(URI uri) {
				return new BinaryResourceImpl(uri) {
					
					@Override
					protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
						try (InputStream gzIn = new GZIPInputStream(inputStream)) {
							super.doLoad(gzIn, options);
						}
					}
					
					@Override
					protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
						try (OutputStream gzOut = new GZIPOutputStream(outputStream)) {
							super.doSave(gzOut, options);
						}
					}
					
				};
			}
			
		};
	}
	
	@Override
	protected String getExtension() {
		return GZIP_BINARY_RESOURCE_EXTENSION;
	}

}
