package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface CommandFactory extends ExecutionParticipantFactory<Command> {
		
	Command create(Context context) throws Exception;
	
}
