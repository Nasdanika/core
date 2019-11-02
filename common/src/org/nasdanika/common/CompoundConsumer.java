package org.nasdanika.common;

import java.util.List;

public class CompoundConsumer<T> extends CompoundFunction<T,Void,Void> {

	@Override
	protected Void combine(List<Void> results, Context context, ProgressMonitor progressMonitor) {
		return null;
	}


}
