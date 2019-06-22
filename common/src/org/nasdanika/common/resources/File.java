package org.nasdanika.common.resources;

import java.util.function.BiFunction;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import org.nasdanika.common.ProgressMonitor;

/**
 * File is a storage of contents.
 * @author Pavel
 *
 * @param <T> Content type
 */
public interface File<T> extends Resource<T> {
	
	/**
	 * @param monitor
	 * @return File contents. 
	 * @throws IllegalStateException if a file does not exist or cannot be read.
	 */
	T getContents(ProgressMonitor monitor);
		
	/**
	 * 
	 * @param type Content type
	 * @param monitor
	 * @return File contents converted to the the requested content type. This implementation does not perform any conversion and
	 * returns contents AS-IS if it is already of the requested type or throws an {@link UnsupportedOperationException} otherwise.
	 * @throws IllegalStateException if a file does not exist, cannot be read, or conversion from T to V is not supported.
	 */
	@SuppressWarnings("unchecked")
	default <V> V getContents(Class<V> type, ProgressMonitor monitor) {
		T contents = getContents(monitor);
		if (type.isInstance(contents)) {
			return (V) contents;
		}
		throw new UnsupportedOperationException("Unsupported content type: "+type);
	}	
	
	/**
	 * Sets file contents. If the file does not exist this call creates a file and intermediary containers as needed.
	 * @param contents File contents. 
	 * @param monitor
	 * @throws IllegalStateException if canWrite() returns false.
	 */
	void setContents(T contents, ProgressMonitor monitor);
	
	/**
	 * Appends contents if the file exists and calls setContents if it doesn't.
	 * @param contents
	 * @param monitor
	 * @throws IllegalStateException if canWrite() returns false.
	 */
	void appendContents(T contents, ProgressMonitor monitor);

	/**
	 * @return true if file can be read. Some containers may be write-only, e.g. a container backed by a {@link ZipOutputStream} would only support writing resource to it, but not reading them.
	 */
	boolean canRead();
	
	/**
	 * @return true if file contents can be changed. Some containers may be read-only, e.g. a container backed by a {@link ZipFile} would only support reading resources from it.
	 */
	boolean canWrite();
	
	/**
	 * Copies file content
	 * @param container target container, may be the same container.
	 * @param path Path in the target container.
	 * @param monitor Progress monitor.
	 */
	@Override
	default void copy(Container<? super T> container, String path, ProgressMonitor monitor) {	
		if (exists()) {
			try (ProgressMonitor readMonitor = monitor.split("Reading "+getPath(), 100, this); ProgressMonitor writeMonitor = monitor.split("Writing "+path, 100, container, path)) {
				container.getFile(path).setContents(getContents(readMonitor), monitor);			
			}
		}
	}
	
	/**
	 * Moves contents. This implementation implements move as copy and delete.
	 * @param container target container, may be the same container.
	 * @param path Path in the target container.
	 * @param monitor Progress monitor.
	 */
	@Override
	default void move(Container<? super T> container, String path, ProgressMonitor monitor) {
		if (exists()) {
			try (
					ProgressMonitor readMonitor = monitor.split("Reading "+getPath(), 100, this); 
					ProgressMonitor writeMonitor = monitor.split("Writing "+getPath(), 100, container, path);
					ProgressMonitor deleteMonitor = monitor.split("Delete "+getPath(), 100, this)) {
				container.getFile(path).setContents(getContents(readMonitor), monitor);			
				delete(deleteMonitor);
			}
		}
	}
	
	/**
	 * Adapts to a different content type.
	 * @param <V>
	 * @param decoder Decodes T to V.
	 * @param encoder Encodes V to T.
	 * @return
	 */
	default <V> File<V> adapt(BiFunction<File<T>, T, V> decoder, BiFunction<File<T>, V, T> encoder) {
		return new File<V>() {

			@Override
			public String getName() {
				return File.this.getName();
			}

			@Override
			public boolean exists() {
				return File.this.exists();
			}

			@Override
			public Container<V> getParent() {
				Container<T> parent = File.this.getParent();
				return parent == null ? null : parent.adapt(decoder, encoder);
			}

			@Override
			public void delete(ProgressMonitor monitor) {
				File.this.delete(monitor);
			}

			@Override
			public String getPath() {
				return File.this.getPath();
			}

			@Override
			public V getContents(ProgressMonitor monitor) {
				return decoder.apply(File.this, File.this.getContents(monitor));
			}

			@Override
			public void setContents(V contents, ProgressMonitor monitor) {
				File.this.setContents(encoder.apply(File.this, contents), monitor);
			}

			@Override
			public void appendContents(V contents, ProgressMonitor monitor) {
				File.this.appendContents(encoder.apply(File.this, contents), monitor);
			}

			@Override
			public boolean canRead() {
				return decoder != null && File.this.canRead();
			}

			@Override
			public boolean canWrite() {
				return encoder != null && File.this.canWrite();
			}
			
		};
	}

}
