package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface CommandFactory extends ExecutionParticipantFactory<Command> {
		
	/**
	 * @param contextSupplierFactory Factory which creates a context to be passed to this factory to create supplier.
	 * @return Command factory which creates a context using the context supplier factory and then uses that context and this factory to create a command.
	 */
	default CommandFactory contextify(SupplierFactory<Context> contextSupplierFactory) {
		return new CommandFactory() {

			@Override
			public Command create(Context context) {
				return new ContextifiedExecutionParticipant.ContextifiedCommand(contextSupplierFactory.create(context), CommandFactory.this);
			}
			
		};
	}
	
	
}
