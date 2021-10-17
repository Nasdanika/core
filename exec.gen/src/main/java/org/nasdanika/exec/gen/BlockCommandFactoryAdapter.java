package org.nasdanika.exec.gen;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.nasdanika.common.Command;
import org.nasdanika.common.CommandFactory;
import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reference;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Block;

public class BlockCommandFactoryAdapter extends BlockExecutionParticipantAdapter implements CommandFactory {
	
	public BlockCommandFactoryAdapter(Block block) {
		super(block);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
		
	private static class BlockCommand extends BlockExecutionParticipant<Command> implements Command {

		private Reference<Exception> eRef;

		BlockCommand(
				Command tryParticipant,
				Command catchParticipant,
				Command finallyParticipant,
				Reference<Exception> eRef) {
			super(tryParticipant, catchParticipant, finallyParticipant);
			this.eRef = eRef;
		}

		@Override
		public void execute(ProgressMonitor progressMonitor) throws Exception {
			try {
				tryParticipant.splitAndExecute(progressMonitor);
			} catch (Exception e) {
				tryParticipant.splitAndRollback(progressMonitor);
				eRef.set(e);
				if (catchParticipant == null) {
					throw e;
				}
				catchParticipant.splitAndExecute(progressMonitor);
			} finally {
				if (finallyParticipant != null) {
					finallyParticipant.splitAndExecute(progressMonitor);
				}
			}			
		}
		
	}	
	
	@Override
	public Command create(Context context) throws Exception {
		Reference<Exception> eRef = new Reference<Exception>();
		Block target = (Block) getTarget();
		
		List<CommandFactory> tryBlock = target.getTry().stream().map(e -> Objects.requireNonNull(EObjectAdaptable.adaptTo(e, CommandFactory.class), "Cannot adapt to CommandFactory: " + e)).collect(Collectors.toList());
		Command tryCommand = Util.asCommandFactory(tryBlock).create(context);
		
		Command catchCommand = null; 		
		if (!target.getCatch().isEmpty()) {
			List<CommandFactory> catchBlock = target.getCatch().stream().map(e -> Objects.requireNonNull(EObjectAdaptable.adaptTo(e, CommandFactory.class), "Cannot adapt to CommandFactory: " + e)).collect(Collectors.toList());
			catchCommand = Util.asCommandFactory(catchBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}
		
		Command finallyCommand = null;
		if (!target.getFinally().isEmpty()) {
			List<CommandFactory> finallyBlock = target.getFinally().stream().map(e -> Objects.requireNonNull(EObjectAdaptable.adaptTo(e, CommandFactory.class), "Cannot adapt to CommandFactory: " + e)).collect(Collectors.toList());
			finallyCommand = Util.asCommandFactory(finallyBlock).contextify(ctx -> createCatchContextSupplier(ctx, eRef)).create(context);
		}		
		
		return new BlockCommand(tryCommand, catchCommand, finallyCommand, eRef);		
	}
	

}
