package org.nasdanika.common.resources;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

public class FileSystemFile extends FileSystemResource implements File<InputStream> {

	public FileSystemFile(java.io.File file) {
		super(file);
	}

	@Override
	public InputStream getContents(ProgressMonitor monitor) {
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
	public void setContents(InputStream contents, ProgressMonitor monitor) {
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
	public void appendContents(InputStream contents, ProgressMonitor monitor) {
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

}
