package org.nasdanika.exec.legacy.java;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.nasdanika.common.Consumer;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.persistence.Marker;
import org.nasdanika.common.persistence.ObjectLoader;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.exec.legacy.resources.Container;
import org.nasdanika.exec.legacy.resources.ReconcileAction;

public class Package extends Container {

	public Package(ObjectLoader loader, Object config, URL base, ProgressMonitor progressMonitor, Marker marker) throws Exception {
		super(loader, config, base, progressMonitor, marker);
	}
	
	public Package(Marker marker, String name, ReconcileAction reconcileAction) {
		super(marker, name, reconcileAction);
	}

	/**
	 * Serivce interface to pass through generation contexts.
	 * @author Pavel
	 *
	 */
	public interface PackageInfo {
		
		String getName();
		
		List<String> getTypes();
		
	}
	
	@Override
	public Consumer<BinaryEntityContainer> create(Context context) throws Exception {
		PackageInfo parentInfo = context.get(PackageInfo.class);
		String packageName = context.interpolateToString(name);
		String fullyQualifiedName = parentInfo == null ? packageName : parentInfo.getName() + "." + packageName;
		
		PackageInfo info = new PackageInfo() {
			
			private List<String> types = new ArrayList<>();
			
			@Override
			public List<String> getTypes() {
				return types;
			}
			
			@Override
			public String getName() {
				return fullyQualifiedName;
			}
			
		};
		
		Context ctx = Context.singleton(PackageInfo.class, info).compose(context);
		return super.create(ctx);
	}
	
	@Override
	protected String finalName(String name) {
		return name.replace('.',  '/');
	}
	
}
