package org.nasdanika.exec.gen;

import java.util.List;
import java.util.Objects;

import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reference;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Block;
import org.nasdanika.persistence.Util;
import org.nasdanika.resources.BinaryEntityContainer;

public class BlockConsumerFactoryAdapter extends BlockExecutionParticipantAdapter implements ConsumerFactory<BinaryEntityContainer> {
	
	public BlockConsumerFactoryAdapter(Block block) {
		super(block);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	private static class BlockConsumer extends BlockExecutionParticipant<Consumer<BinaryEntityContainer>> implements Consumer<BinaryEntityContainer> {

		private Reference<Exception> eRef;

		BlockConsumer(
				Consumer<BinaryEntityContainer> tryParticipant,
				Consumer<BinaryEntityContainer> catchParticipant,
				Consumer<BinaryEntityContainer> finallyParticipant,
				Reference<Exception> eRef) {
			super(tryParticipant, catchParticipant, finallyParticipant);
			this.eRef = eRef;
		}

		@Override
		public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) {
			try {
				tryParticipant.splitAndExecute(container, progressMonitor);
			} catch (Exception e) {
				tryParticipant.splitAndRollback(progressMonitor);
				eRef.set(e);
				if (catchParticipant == null) {
					throw e;
				}
				catchParticipant.splitAndExecute(container, progressMonitor);
			} finally {
				if (finallyParticipant != null) {
					finallyParticipant.splitAndExecute(container, progressMonitor);
				}
			}			
		}
		
	}	
	
	@Override
	public Consumer<BinaryEntityContainer> create(Context context) {
		Reference<Exception> eRef = new Reference<Exception>();
		Block target = (Block) getTarget();
		
		List<ConsumerFactory<BinaryEntityContainer>> tryBlock = target.getTry().stream().map(e -> Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(e, BinaryEntityContainer.class), "Cannot adapt to ConsumerFactory: " + e)).toList();
		Consumer<BinaryEntityContainer>  tryConsumer = Util.asConsumerFactory(tryBlock).create(context);
		
		Consumer<BinaryEntityContainer> catchConsumer = null; 		
		if (!target.getCatch().isEmpty()) {
			List<ConsumerFactory<BinaryEntityContainer>> catchBlock = target.getCatch().stream().map(e -> Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(e, BinaryEntityContainer.class), "Cannot adapt to ConsumerFactory: " + e)).toList();
			catchConsumer = Util.asConsumerFactory(catchBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}
		
		Consumer<BinaryEntityContainer>  finallyConsumer = null;
		if (!target.getFinally().isEmpty()) {
			List<ConsumerFactory<BinaryEntityContainer>> finallyBlock = target.getFinally().stream().map(e -> Objects.requireNonNull(EObjectAdaptable.adaptToConsumerFactory(e, BinaryEntityContainer.class), "Cannot adapt to ConsumerFactory: " + e)).toList();
			finallyConsumer = Util.asConsumerFactory(finallyBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}		
		
		return new BlockConsumer(tryConsumer, catchConsumer, finallyConsumer, eRef);		
	}	

}
