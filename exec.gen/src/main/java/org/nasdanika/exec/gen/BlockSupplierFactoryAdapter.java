package org.nasdanika.exec.gen;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.Context;
import org.nasdanika.common.ExecutionException;
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
	
		private InputStream executeTryCatch(ProgressMonitor progressMonitor) {
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
		public InputStream execute(ProgressMonitor progressMonitor) {
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
				try {
					return Util.join(tcResult, finallyResult);
				} catch (IOException e) {
					throw new ExecutionException(e, this);
				}
			}
			throw new ExecutionException(ex, this);			
		}
		
	}	
	
	@Override
	public Supplier<InputStream> create(Context context) {
		Reference<Exception> eRef = new Reference<Exception>();
		Block target = (Block) getTarget();
		
		Collection<SupplierFactory<InputStream>> tryBlock = new ArrayList<>();
		for (EObject tbe: target.getTry()) {
			tryBlock.add(Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(tbe, InputStream.class), "Cannot adapt to SupplierFactory: " + tbe));
		}
		Supplier<InputStream>  trySupplier = Util.asInputStreamSupplierFactory(tryBlock).create(context);
		
		Supplier<InputStream> catchSupplier = null; 		
		if (!target.getCatch().isEmpty()) {
			List<SupplierFactory<InputStream>> catchBlock = target.getCatch().stream().map(e -> Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(e, InputStream.class), "Cannot adapt to SupplierFactory: " + e)).toList();
			catchSupplier = Util.asInputStreamSupplierFactory(catchBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}
		
		Supplier<InputStream>  finallySupplier = null;
		if (!target.getFinally().isEmpty()) {
			List<SupplierFactory<InputStream>> finallyBlock = target.getFinally().stream().map(e -> Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(e, InputStream.class), "Cannot adapt to SupplierFactory: " + e)).toList();
			finallySupplier = Util.asInputStreamSupplierFactory(finallyBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}		
		
		return new BlockSupplier(trySupplier, catchSupplier, finallySupplier, eRef);		
	}	

}
