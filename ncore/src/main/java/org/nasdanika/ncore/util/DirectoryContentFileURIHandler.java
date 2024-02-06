package org.nasdanika.ncore.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.FileURIHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.NcoreFactory;
import org.nasdanika.ncore.Reference;

/**
 * URI handler which supports directory listing - it creates a list of references to root objects of supported files and saves it to output stream to be loaded by XMIResourceFactoryImpl. 
 */
public class DirectoryContentFileURIHandler extends FileURIHandlerImpl {
	
	private Predicate<URI> filePredicate;
	private boolean rootProxies;

	public DirectoryContentFileURIHandler(Predicate<URI> filePredicate, boolean rootProxies) {
		this.filePredicate = filePredicate;
		this.rootProxies = rootProxies;
	}

	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
		if (uri.isFile()) {
			String fileString = uri.toFileString();
			File file = new File(fileString);
			URI base = uri.appendSegment("");
			if (file.isDirectory()) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				XMIResourceImpl resource = new XMIResourceImpl(uri);
				try (baos) {					
					List list = NcoreFactory.eINSTANCE.createList();
					resource.getContents().add(list);				
					for (String child: file.list()) {						
						URI childURI = URI.createURI(child).resolve(base);
						if (filePredicate == null || filePredicate.test(childURI)) {
							if (rootProxies) {
								Reference<EObject> ref = NcoreFactory.eINSTANCE.createReference();
								EObject refTarget = EcoreFactory.eINSTANCE.createEObject();
								((EObjectImpl) refTarget).eSetProxyURI(childURI.appendFragment("/"));
								ref.setTarget(refTarget);
								list.getValue().add(ref);
							} else {
								org.nasdanika.ncore.String string = NcoreFactory.eINSTANCE.createString();
								string.setValue(child);
								list.getValue().add(string);								
							}
						}
					}
				} 
				
				resource.save(baos, options);
				return new ByteArrayInputStream(baos.toByteArray());
			}
		}
		return super.createInputStream(uri, options);
	}

}
