package org.nasdanika.graph.processor.activation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

public class Activation implements Enrollment, AutoCloseable {
	
	private Collection<Enrollment> enrollments = new ArrayList<>();

	public Activation(Collection<ActivationParticipant> participants) {
		participants.forEach(p -> enrollments.add(p.enroll(this)));
	}

	@Override
	public void close() {
		enrollments.forEach(e -> e.close());		
	}

	@Override
	public void submit(Consumer<Runnable> taskConsumer) {
		enrollments.forEach(e -> e.submit(taskConsumer));		
	}

	@Override
	public void error(Exception ex) {
		enrollments.forEach(e -> e.error(ex));		
	}

}
