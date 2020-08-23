package org.nasdanika.exec.content;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.ZipOutputStream;

import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ObjectLoader;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.common.resources.EphemeralBinaryEntityContainer;
import org.nasdanika.exec.Loader;

public class ZipArchive implements SupplierFactory<InputStream>, Marked { 
	
	private ConsumerFactory<BinaryEntityContainer> consumer;
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}

	public ZipArchive(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		consumer = Loader.asConsumerFactory(loader.load(config, base, progressMonitor), marker);
	}	
	
	private FunctionFactory<BinaryEntityContainer,InputStream> streamFactory = context -> new Function<BinaryEntityContainer, InputStream>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Zip Archive";
		}

		@Override
		public InputStream execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (ZipOutputStream zos = new ZipOutputStream(baos)) {
				container.store(zos, null, progressMonitor);
			}
			baos.close();
			return new ByteArrayInputStream(baos.toByteArray());
		}
		
	};

	@Override
	public Supplier<InputStream> create(Context iContext) throws Exception {		
		SupplierFactory<BinaryEntityContainer> containerFactory = new SupplierFactory<BinaryEntityContainer>() {

			@Override
			public Supplier<BinaryEntityContainer> create(Context context) throws Exception {
				return Supplier.from(new EphemeralBinaryEntityContainer(), "Constructor");
			}
			
		};
		
		return containerFactory.then(consumer.asFunctionFactory()).then(streamFactory).create(iContext);
	}
	

}
