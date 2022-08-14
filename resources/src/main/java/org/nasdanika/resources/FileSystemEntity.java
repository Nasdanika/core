package org.nasdanika.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

/**
 * Wrapper for a file system file.
 * @author Pavel
 *
 */
public class FileSystemEntity extends FileSystemResource implements BinaryEntity {

	public FileSystemEntity(java.io.File file) {
		super(file);
	}

	@Override
	public InputStream getState(ProgressMonitor monitor) {
		if (!file.isFile()) {
			throw new UnsupportedOperationException("Not a file: "+file.getAbsolutePath());
		}
		if (!canRead()) {
			throw new UnsupportedOperationException("Cannot read: "+file.getAbsolutePath());			
		}
		try {
			return new FileInputStream(file);
		} catch (FileNotFoundException e) {
			throw new NasdanikaException(e);
		}
	}

	@Override
	public void setState(InputStream contents, ProgressMonitor monitor) {
		java.io.File parent = file.getParentFile();
		if (!parent.exists() && !parent.mkdirs()) {
			throw new NasdanikaException("Directory does not exist and could not be created: "+parent.getAbsolutePath());
		}
		long total = 0;
		try (BufferedInputStream bin = new BufferedInputStream(contents); BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(file))) {
			int b;
			while ((b = bin.read()) != -1) {
				bout.write(b);
				++total;
				// Progress reporting at a regular interval?
			}
		} catch (IOException e) {
			monitor.worked(100, "Error setting contents: "+e+", stored "+total+" bytes");
			throw new NasdanikaException("Could not set contents of "+file.getAbsolutePath(), e);
		}
		monitor.worked(100, "Stored "+total+" bytes");
	}

	@Override
	public void appendState(InputStream contents, ProgressMonitor monitor) {
		long total = 0;
		try (BufferedInputStream bin = new BufferedInputStream(contents); BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(file, true))) {
			int b;
			while ((b = bin.read()) != -1) {
				bout.write(b);
				++total;
				// Progress reporting at a regular interval?
			}
		} catch (IOException e) {
			monitor.worked(100, "Error appending contents: "+e+", appended "+total+" bytes");
			throw new NasdanikaException("Could not append contents to "+file.getAbsolutePath(), e);
		}
		monitor.worked(100, "Appended "+total+" bytes");
	}

	@Override
	public boolean canRead() {
		return file.canRead();
	}

	@Override
	public boolean canWrite() {
		return file.canWrite();
	}

	@Override
	public long size(ProgressMonitor monitor) {
		return file.isFile() ? file.length() : 0;
	}

	// TODO - digest.
	
}
