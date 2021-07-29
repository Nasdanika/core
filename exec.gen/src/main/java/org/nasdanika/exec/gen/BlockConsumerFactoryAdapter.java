package org.nasdanika.exec.gen;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.nasdanika.common.Consumer;
import org.nasdanika.common.ConsumerFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reference;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.common.resources.BinaryEntityContainer;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Block;

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
		public void execute(BinaryEntityContainer container, ProgressMonitor progressMonitor) throws Exception {
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
	public Consumer<BinaryEntityContainer> create(Context context) throws Exception {
		Reference<Exception> eRef = new Reference<Exception>();
		Block target = (Block) getTarget();
		
		List<ConsumerFactory<BinaryEntityContainer>> tryBlock = target.getTry().stream().map(e -> EObjectAdaptable.adaptToConsumerFactory(e, BinaryEntityContainer.class)).filter(Objects::nonNull).collect(Collectors.toList());
		Consumer<BinaryEntityContainer>  tryConsumer = Util.asConsumerFactory(tryBlock).create(context);
		
		Consumer<BinaryEntityContainer> catchConsumer = null; 		
		if (!target.getCatch().isEmpty()) {
			List<ConsumerFactory<BinaryEntityContainer>> catchBlock = target.getCatch().stream().map(e -> EObjectAdaptable.adaptToConsumerFactory(e, BinaryEntityContainer.class)).filter(Objects::nonNull).collect(Collectors.toList());
			catchConsumer = Util.asConsumerFactory(catchBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}
		
		Consumer<BinaryEntityContainer>  finallyConsumer = null;
		if (!target.getFinally().isEmpty()) {
			List<ConsumerFactory<BinaryEntityContainer>> finallyBlock = target.getFinally().stream().map(e -> EObjectAdaptable.adaptToConsumerFactory(e, BinaryEntityContainer.class)).filter(Objects::nonNull).collect(Collectors.toList());
			finallyConsumer = Util.asConsumerFactory(finallyBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}		
		
		return new BlockConsumer(tryConsumer, catchConsumer, finallyConsumer, eRef);		
	}	

}
