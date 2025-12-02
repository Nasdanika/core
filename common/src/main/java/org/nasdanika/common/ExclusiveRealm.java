package org.nasdanika.common;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.BiFunction;

/**
 * This realm uses a lock to protect command execution.
 */
public class ExclusiveRealm implements Realm {
	
	protected Lock lock = createLock();
	
	protected Lock createLock() {
		return new ReentrantLock();
	}	

	@Override
	public <T, R> R read(T element, BiFunction<Realm, T, R> command) {
		lock.lock();
		try {
			return command.apply(this, element);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public <T, R> R write(T element, BiFunction<Realm, T, R> command) {
		lock.lock();
		try {
			return command.apply(this, element);
		} finally {
			lock.unlock();
		}
	}

}
