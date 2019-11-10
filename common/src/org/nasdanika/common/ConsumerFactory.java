package org.nasdanika.common;

/**
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface ConsumerFactory<T> extends ExecutionParticipantFactory<Consumer<T>> {
		
	Consumer<T> create(Context context) throws Exception;
	
}
