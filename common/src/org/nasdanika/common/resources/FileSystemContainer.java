package org.nasdanika.common.resources;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

public class FileSystemContainer extends FileSystemResource implements Container<InputStream> {

	public FileSystemContainer(java.io.File file) {
		super(file);
	}

	protected Resource<InputStream> getChild(String name) {
		for (Resource<InputStream> child: getChildren()) {
			if (name.equals(child.getName())) {
				return child;
			}
		}
		return null;
	}

	@Override
	public Resource<InputStream> find(String path) {
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return getChild(path); 
		}
		Resource<InputStream> child = getChild(path.substring(0, sPos));
		if (child instanceof Container) {
			return ((Container<InputStream>) child).find(path.substring(sPos+1));
		}
		return null;
	}

	@Override
	public File<InputStream> getFile(String path) {
		Resource<InputStream> existing = find(path);
		if (existing instanceof File) {
			return (File<InputStream>) existing;
		}
		if (existing instanceof Container) {
			// container - can't have a file with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return new FileSystemFile(new java.io.File(file, path));
		}
		
		Container<InputStream> container = getContainer(path.substring(0, sPos));
		return container == null ? null : container.getFile(path.substring(sPos + 1));
	}

	@Override
	public Container<InputStream> getContainer(String path) {
		Resource<InputStream> existing = find(path);
		if (existing instanceof Container) {
			return (Container<InputStream>) existing;
		}
		if (existing instanceof File) {
			// file - can't have a container with the same name.
			return null;
		}
		int sPos = path.indexOf(SEPARATOR);
		if (sPos == -1) {
			return new FileSystemContainer(new java.io.File(file, path));
		}
		
		Container<InputStream> container = getContainer(path.substring(0, sPos));
		return container == null ? null : container.getContainer(path.substring(sPos + 1));
	}

	@Override
	public Collection<Resource<InputStream>> getChildren() {
		Collection<Resource<InputStream>> ret = new ArrayList<>();
		for (java.io.File child: file.listFiles()) {
			if (child.isFile()) {
				ret.add(new FileSystemFile(child));
			} else if (child.isDirectory()) {
				ret.add(new FileSystemContainer(child));
			}
		}
		return ret;
	}

}
