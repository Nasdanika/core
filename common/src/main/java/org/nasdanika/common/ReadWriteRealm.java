package org.nasdanika.common;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiFunction;

/**
 * This realm uses a {@link ReadWriteLock} to protect command execution.
 */
public class ReadWriteRealm implements Realm {
	
	protected ReadWriteLock lock = createLock();
	
	protected ReadWriteLock createLock() {
		return new ReentrantReadWriteLock();
	}	

	@Override
	public <T, R> R read(T element, BiFunction<Realm, T, R> command) {
		Lock readLock = lock.readLock();
		readLock.lock();
		try {
			return command.apply(this, element);
		} finally {
			readLock.unlock();
		}
	}

	@Override
	public <T, R> R write(T element, BiFunction<Realm, T, R> command) {
		Lock writeLock = lock.writeLock();
		try {
			return command.apply(this, element);
		} finally {
			writeLock.unlock();
		}
	}

}
