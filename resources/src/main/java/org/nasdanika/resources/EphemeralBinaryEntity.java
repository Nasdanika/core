package org.nasdanika.resources;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;

public abstract class EphemeralBinaryEntity implements BinaryEntity {

	protected byte[] state;

	@Override
	public InputStream getState(ProgressMonitor monitor) {
		return state == null ? null : new ByteArrayInputStream(state);
	}

	@Override
	public void setState(InputStream state, ProgressMonitor monitor) {
		if (state == null) {
			this.state = null;
		} else {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				try (BufferedInputStream bis = new BufferedInputStream(state)) {
					int b;
					while ((b = bis.read()) != -1) {
						baos.write(b);
					}
				}
				baos.close();
				this.state = baos.toByteArray();
			} catch (IOException e) {
				throw new NasdanikaException(e);
			}
		}
	}
	
	@Override
	public void appendState(InputStream state, ProgressMonitor monitor) {
		if (state != null) {
			if (this.state == null) {
				setState(state, monitor);
			} else {
				try {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();					
					try (InputStream bis = new ByteArrayInputStream(this.state)) {
						int b;
						while ((b = bis.read()) != -1) {
							baos.write(b);
						}
					}
					try (InputStream bis = new BufferedInputStream(state)) {
						int b;
						while ((b = bis.read()) != -1) {
							baos.write(b);
						}
					}
					baos.close();
					this.state = baos.toByteArray();
				} catch (IOException e) {
					throw new NasdanikaException(e);
				}
			}
		}
	}	

	@Override
	public boolean canRead() {
		return true;
	}

	@Override
	public boolean canWrite() {
		return true;
	}
	
	@Override
	public boolean exists(ProgressMonitor monitor) {
		return state != null;
	}
	
	@Override
	public void delete(ProgressMonitor monitor) {
		setState(null, monitor);	
	}	

	@Override
	public String toString() {
		return getClass().getName()+"("+getPath()+")";
	}
	
	@Override
	public long size(ProgressMonitor monitor) {
		return state == null ? 0 : state.length;
	}

}
