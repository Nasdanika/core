package org.nasdanika.telemetry;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetAddress;

import org.nasdanika.common.Util;

import io.opentelemetry.api.trace.SpanBuilder;

public final class TelemetryUtil {
	
	private static RuntimeMXBean RUNTIME_MX_BEAN = ManagementFactory.getRuntimeMXBean();
	private static final String USER_NAME = System.getProperty("user.name");
	private static String HOST_NAME;
	private static String HOST_ADDRESS;
	
	static {
		try {
			InetAddress localHost = InetAddress.getLocalHost();
			HOST_NAME = localHost.getHostName();
			HOST_ADDRESS = localHost.getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static SpanBuilder buildSpan(SpanBuilder builder) {
		if (!Util.isBlank(USER_NAME)) {
			builder.setAttribute("user.name", USER_NAME);
		}
		
        builder.setAttribute("java.vm.name", RUNTIME_MX_BEAN.getVmName());
        builder.setAttribute("java.vm.vendor", RUNTIME_MX_BEAN.getVmVendor());
        builder.setAttribute("java.vm.version", RUNTIME_MX_BEAN.getVmVersion());
        builder.setAttribute("java.vm.pid", RUNTIME_MX_BEAN.getPid());
		
		if (!Util.isBlank(HOST_NAME)) {
			builder.setAttribute("host.name", HOST_NAME);				
		}
		if (!Util.isBlank(HOST_ADDRESS)) {
			builder.setAttribute("host.address", HOST_ADDRESS);				
		}			
		return builder;
	}
		
	private TelemetryUtil() {
		// Utility class
	}

}
