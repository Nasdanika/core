package org.nasdanika.common;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.nasdanika.common.ProgressMonitor.Status;

/**
 * Wraps a {@link Command} into a {@link Callable}. This class doesn't split the monitor for itself. The monitor is closed upon completion of the execution of the command. 
 * Executes the work only once inside a lock and caches the result. If the monitor is cancelled when ``call()`` is invoked then the command is not executed
 * and ``call()`` throws {@link CancellationException}. 
 * If a thread attempts to acquire a lock while the command's ``execute()`` is being executed by another thread,
 * the calling thread is blocked until the execution completes and both the threads receive the result or 
 * thrown exception. In the case of cached exception it is wrapped into {@link ExecutionException} in 
 * order to provide access to both the stack traces - the one of the current thread and the other of the thread
 * which called ``execute()`` in the first place. 
 * 
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public class CommandCallable<T> implements Callable<T> {
	
	Lock lock = new ReentrantLock();

	private T result;
	private Exception exception;
	volatile boolean done;
	private ProgressMonitor monitor;

	private Command<T> command;
	
	public CommandCallable(Command<T> command, ProgressMonitor monitor) {
		this.command = command;		
		this.monitor = monitor;
	}

	@Override
	public T call() throws Exception {
		lock.lock();
		try {
			if (done) {
				if (exception != null) {
					throw new ExecutionException(exception);
				}
				return result;
			}

			if (monitor.isCancelled()) {
				monitor.worked(Status.CANCEL, 0, "Cancelled");
				exception = new CancellationException();
				throw exception;
			}
			
			try {
				result = command.execute(monitor);
				monitor.worked(0, "Completed", result);
				return result;
			} catch (Exception e) {
				exception = e;
				monitor.worked(Status.ERROR, 0, "Failed", e);
				throw e;
			} finally {
				done = true;
				monitor.close();
			}
		} finally {
			lock.unlock();
		}
	}

}
