package org.nasdanika.common._legacy;

import java.util.List;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;

public class CompoundConsumer<T> extends CompoundFunction<T,Void,Void> {

	@Override
	protected Void combine(List<Void> results, Context context, ProgressMonitor progressMonitor) {
		return null;
	}


}
