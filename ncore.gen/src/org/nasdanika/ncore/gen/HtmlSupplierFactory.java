package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.ncore.Html;

public class HtmlSupplierFactory extends SupplierSupplierFactory<Html> {
	
	public HtmlSupplierFactory(Html target) {
		super(target);
	}
	
	@Override
	public Supplier<Object> create(Context context) throws Exception {
		return new Supplier<Object>() {

			@Override
			public Object execute(ProgressMonitor progressMonitor) throws Exception {
				return target.isInterpolate() ? context.interpolate(target.getContent()) : target.getContent();
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