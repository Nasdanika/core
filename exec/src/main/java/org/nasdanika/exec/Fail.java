package org.nasdanika.exec;

import java.io.InputStream;
import java.net.URL;

import org.nasdanika.common.Adaptable;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.NasdanikaException;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marked;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.resources.BinaryEntityContainer;

/**
 * Fails execution by throwing an exception with supplied message.
 * @author Pavel
 *
 */
public class Fail implements Adaptable, Marked {
		
	protected SupplierFactory<InputStream> message;
	private Marker marker;
	
	@Override
	public Marker getMarker() {
		return marker;
	}
	
	protected Fail(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		this.marker = marker;
		message = Util.asInputStreamSupplierFactory(loader.load(config, base, progressMonitor));
	}
	
	protected Fail(Marker marker, SupplierFactory<InputStream> message) {
		this.marker = marker;
		this.message = message;
	}
		
	private Consumer<InputStream> createConsumer(Context context) throws Exception {
		return new Consumer<InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Fail";
			}

			@Override
			public void execute(InputStream msg, ProgressMonitor progressMonitor) throws Exception {
				throw new NasdanikaException(Util.toString(context, msg));
			}
			
		};
	}
		
	private Consumer<BiSupplier<BinaryEntityContainer, InputStream>> createBiConsumer(Context context) throws Exception {
		return new Consumer<BiSupplier<BinaryEntityContainer, InputStream>>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Fail";
			}
	
			@Override
			public void execute(BiSupplier<BinaryEntityContainer, InputStream> input, ProgressMonitor progressMonitor) throws Exception {
				throw new NasdanikaException(Util.toString(context, input.getSecond()));
			}
			
		};
	}	
	
	private Function<InputStream,InputStream> createFunction(Context context) throws Exception {
		return new Function<InputStream,InputStream>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Fail";
			}
	
			@Override
			public InputStream execute(InputStream msg, ProgressMonitor progressMonitor) throws Exception {
				throw new NasdanikaException(Util.toString(context, msg));
			}
			
		};
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T adaptTo(Class<T> type) {		
		if (type == CommandFactory.class) {
			return (T) message.then((ConsumerFactory<InputStream>) this::createConsumer);															
		}
		
		if (type == ConsumerFactory.class) {
			return (T) message.<BinaryEntityContainer>asFunctionFactory().then((ConsumerFactory<BiSupplier<BinaryEntityContainer, InputStream>>) this::createBiConsumer);													
		}
		
		if (type == SupplierFactory.class) {
			return (T) message.then((FunctionFactory<InputStream,InputStream>) this::createFunction);															
		}
		
		return Adaptable.super.adaptTo(type);
	}		

}
