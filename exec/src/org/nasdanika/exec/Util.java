package org.nasdanika.exec;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;

public class Util {
		
	public static InputStream join(InputStream... streams) throws IOException {
		if (streams.length == 1) {
			return streams[0];
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for (InputStream in: streams) {
			if (in != null) {
				try (BufferedInputStream bin = new BufferedInputStream(in)) {
					int b;
					while ((b = bin.read()) != -1) {
						baos.write(b);
					}						
				}
			}
		}
		baos.close();
		return new ByteArrayInputStream(baos.toByteArray());
	}
	
	public static InputStream toStream(Context context, String text) throws IOException {
		Charset charset = context.get(Charset.class, StandardCharsets.UTF_8);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		if (text != null) {
			try (OutputStreamWriter writer = new OutputStreamWriter(baos, charset)) {
				writer.write(text);
			}
		}
		baos.close();
		return new ByteArrayInputStream(baos.toByteArray());
	}	
	
	public static String toString(Context context, InputStream in) throws IOException {
		if (in == null) {
			return "";
		}
		StringWriter sw = new StringWriter();
		Charset charset = context.get(Charset.class, StandardCharsets.UTF_8);
		try (Reader reader = new InputStreamReader(new BufferedInputStream(in), charset)) {
			int ch;
			while ((ch = reader.read()) != -1) {
				sw.write(ch);
			}
		}
		sw.close();
		return sw.toString();
	}	
	
	public static InputStream filter(Context context, InputStream in, java.util.function.Function<String,String> filter) throws IOException {
		return toStream(context, filter.apply(toString(context, in)));
	}	
		
	public static Function<List<InputStream>, InputStream> JOIN_STREAMS = new Function<List<InputStream>, InputStream>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return "Joining content";
		}

		@Override
		public InputStream execute(List<InputStream> content, ProgressMonitor progressMonitor) throws Exception {
			return join(content.toArray(new InputStream[content.size()]));
		}
		
	};
	
	public static FunctionFactory<List<InputStream>, InputStream> JOIN_STREAMS_FACTORY = new FunctionFactory<List<InputStream>, InputStream>() {

		@Override
		public Function<List<InputStream>, InputStream> create(Context context) throws Exception {
			return JOIN_STREAMS;
		}
		
	};
	
}
