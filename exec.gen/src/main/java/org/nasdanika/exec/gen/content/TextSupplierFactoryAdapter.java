package org.nasdanika.exec.gen.content;

import java.io.InputStream;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.exec.content.Text;

public class TextSupplierFactoryAdapter extends AdapterImpl implements SupplierFactory<InputStream> {

	public TextSupplierFactoryAdapter(Text text) {
		setTarget(text);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}

	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		return new Supplier<InputStream>() {
			
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Text";
			}
	
			@Override
			public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
				Text target = (Text) getTarget();
				String text = target.isInterpolate() ? context.interpolateToString(target.getContent()) : target.getContent();
				return Util.toStream(context, text);
			}
		
		};
		
	}

}
