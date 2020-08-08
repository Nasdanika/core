package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.ncore.Service;

public class ServiceSupplierFactory<T extends Service> implements SupplierFactory<Object> {
	
	protected T target;

	public ServiceSupplierFactory(T target) {
		this.target = target;
	}

	@Override
	public Supplier<Object> create(Context context) throws Exception {
		return new Supplier<Object>() {

			@Override
			public Object execute(ProgressMonitor progressMonitor) throws Exception {
				ClassLoader classLoader = context.get(ClassLoader.class);
				if (classLoader == null) {
					classLoader = getClass().getClassLoader();
				}
				return context.get(classLoader.loadClass(target.getType()));
			}

			@Override
			public double size() {
				return 1;
			}

			@Override
			public String name() {
				return target.getTitle();
			}
			
		};
	}
	
}