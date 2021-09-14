package org.nasdanika.exec.gen.content;

import java.io.InputStream;
import java.util.Objects;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.content.Filter;

public abstract class FilterSupplierFactoryAdapter<T extends Filter> extends AdapterImpl implements SupplierFactory<InputStream> {
	
	protected FilterSupplierFactoryAdapter(T filter) {
		setTarget(filter);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getTarget() {
		return (T) super.getTarget();
	}
	
	private FunctionFactory<InputStream,InputStream> filterFactory = ctx -> new Function<InputStream, InputStream>() {

		@Override
		public double size() {
			return 1;
		}

		@Override
		public String name() {
			return FilterSupplierFactoryAdapter.this.getClass().getName();
		}

		@Override
		public InputStream execute(InputStream input, ProgressMonitor progressMonitor) throws Exception {
			return Util.filter(ctx, input, str -> FilterSupplierFactoryAdapter.this.filter(ctx, str));
		}
		
	};
		
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		EObject source = ((Filter) getTarget()).getSource();
		SupplierFactory<InputStream> ssf = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(source, InputStream.class), "Cannot adapt to SupplierFactory: " + source);
		return ssf.then(filterFactory).create(context);
	}
	
	protected abstract String filter(Context context, String input); 	

}
