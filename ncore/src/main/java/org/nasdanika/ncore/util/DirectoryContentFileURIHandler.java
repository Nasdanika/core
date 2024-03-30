package org.nasdanika.ncore.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.FileURIHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.Directory;
import org.nasdanika.ncore.NcoreFactory;

/**
 * URI handler which supports directory listing - it creates a list of references to root objects of supported files and saves it to output stream to be loaded by XMIResourceFactoryImpl. 
 */
public class DirectoryContentFileURIHandler extends FileURIHandlerImpl {
	
	@Override
	public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {		
		if (uri.isFile()) {
			boolean isDirectory = false;			
			if (Util.isBlank(uri.lastSegment())) {
				isDirectory = true;
				uri = uri.trimSegments(1);
			}
			String fileString = uri.toFileString();
			File file = new File(fileString);			
			if (isDirectory || file.isDirectory()) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				XMIResourceImpl resource = new XMIResourceImpl(uri);
				try (baos) {	
					Directory directory = NcoreFactory.eINSTANCE.createDirectory();
					directory.setName(file.getName());
					directory.setLastModified(new Date(file.lastModified()));
					resource.getContents().add(directory);				
					File[] children = file.listFiles();
					if (children != null) {
						for (File child: children) {
							org.nasdanika.ncore.File childFile = child.isDirectory() ? NcoreFactory.eINSTANCE.createDirectory() : NcoreFactory.eINSTANCE.createFile();
							childFile.setName(child.getName());
							childFile.setLastModified(new Date(child.lastModified()));
							childFile.setLength(child.length());
							directory.getTreeItems().add(childFile);
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
