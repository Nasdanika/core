package org.nasdanika.exec.gen;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reference;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Block;

public class BlockSupplierFactoryAdapter extends BlockExecutionParticipantAdapter implements SupplierFactory<InputStream> {
	
	public BlockSupplierFactoryAdapter(Block block) {
		super(block);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	private static class BlockSupplier extends BlockExecutionParticipant<Supplier<InputStream>> implements Supplier<InputStream> {
	
		private Reference<Exception> eRef;
	
		BlockSupplier(
				Supplier<InputStream> tryParticipant,
				Supplier<InputStream> catchParticipant,
				Supplier<InputStream> finallyParticipant,
				Reference<Exception> eRef) {
			super(tryParticipant, catchParticipant, finallyParticipant);
			this.eRef = eRef;
		}
	
		private InputStream executeTryCatch(ProgressMonitor progressMonitor) throws Exception {
			try {
				return tryParticipant.splitAndExecute(progressMonitor);
			} catch (Exception e) {
				tryParticipant.splitAndRollback(progressMonitor);
				eRef.set(e);
				if (catchParticipant == null) {
					throw e;
				}
				return catchParticipant.splitAndExecute(progressMonitor);
			}			
		}
	
		@Override
		public InputStream execute(ProgressMonitor progressMonitor) throws Exception {
			if (finallyParticipant == null) {
				return executeTryCatch(progressMonitor);
			} 
			
			InputStream tcResult = null;
			Exception ex = null;
			InputStream finallyResult = null;
			try {
				tcResult = executeTryCatch(progressMonitor);
			} catch (Exception e) {
				ex = e;
			} finally {
				finallyResult = finallyParticipant.splitAndExecute(progressMonitor);
			}
			if (ex == null) {
				return Util.join(tcResult, finallyResult);
			}
			throw ex;			
		}
		
	}	
	
	@SuppressWarnings("unchecked")
	@Override
	public Supplier<InputStream> create(Context context) throws Exception {
		Reference<Exception> eRef = new Reference<Exception>();
		Block target = (Block) getTarget();
		
		Collection<SupplierFactory<InputStream>> tryBlock = new ArrayList<>();
		for (EObject tbe: target.getTry()) {
			SupplierFactory<InputStream> tbesf = EObjectAdaptable.adaptTo(tbe, SupplierFactory.class);
			if (tbesf != null) {
				tryBlock.add(tbesf);
			}
		}
		Supplier<InputStream>  trySupplier = Util.asInputStreamSupplierFactory(tryBlock).create(context);
		
		Supplier<InputStream> catchSupplier = null; 		
		if (!target.getCatch().isEmpty()) {
			List<SupplierFactory<InputStream>> catchBlock = target.getCatch().stream().map(e -> EObjectAdaptable.adaptTo(e, SupplierFactory.class)).filter(Objects::nonNull).collect(Collectors.toList());
			catchSupplier = Util.asInputStreamSupplierFactory(catchBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}
		
		Supplier<InputStream>  finallySupplier = null;
		if (!target.getFinally().isEmpty()) {
			List<SupplierFactory<InputStream>> finallyBlock = target.getFinally().stream().map(e -> EObjectAdaptable.adaptTo(e, SupplierFactory.class)).filter(Objects::nonNull).collect(Collectors.toList());
			finallySupplier = Util.asInputStreamSupplierFactory(finallyBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}		
		
		return new BlockSupplier(trySupplier, catchSupplier, finallySupplier, eRef);		
	}	

}
