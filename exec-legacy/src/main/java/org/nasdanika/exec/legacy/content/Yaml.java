package org.nasdanika.exec.legacy.content;

import java.io.InputStream;
import java.net.URL;

import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;

/**
 * Encodes contained content as YAML.  
 * @author Pavel
 *
 */
public class Yaml extends Encoder {

	public Yaml(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
	}

	public Yaml(Marker marker, SupplierFactory<Object> dataFactory) {
		super(marker, dataFactory);
	}

	@Override
	protected FunctionFactory<Object, InputStream> getEncoderFactory() {
		return ctx -> new Function<Object, InputStream>() {

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return "Encoder";
			}

			@Override
			public InputStream execute(Object data, ProgressMonitor progressMonitor) throws Exception {
				DumperOptions dumperOptions = new DumperOptions();
				dumperOptions.setDefaultFlowStyle(FlowStyle.BLOCK);
				dumperOptions.setIndent(4);
				org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml(dumperOptions);
				return Util.toStream(ctx, yaml.dump(data));				
			}
			
		};
	}

}


