package org.nasdanika.exec.gen;

import java.util.concurrent.CancellationException;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.nasdanika.common.BasicDiagnostic;
import org.nasdanika.common.Context;
import org.nasdanika.common.Diagnostic;
import org.nasdanika.common.ExecutionParticipant;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Reference;
import org.nasdanika.common.Status;
import org.nasdanika.common.Supplier;
import org.nasdanika.exec.Block;

/**
 * Base class for Block adapters.
 * @author Pavel
 *
 */
public class BlockExecutionParticipantAdapter extends AdapterImpl {
	
	public BlockExecutionParticipantAdapter(Block block) {
		setTarget(block);
	}
	
	public static final String ERROR_KEY = "error";
	public static final String ERROR_TYPE_KEY = "error/type";
	public static final String ERROR_MESSAGE_KEY = "error/message";	
	
	/**
	 * Base class for command, consumer, and supplier participants.
	 * @author Pavel
	 *
	 * @param <E>
	 */
	protected static abstract class BlockExecutionParticipant<E extends ExecutionParticipant> implements ExecutionParticipant {
		
		protected E tryParticipant;
		protected E catchParticipant;
		protected E finallyParticipant;
		
		BlockExecutionParticipant(E tryParticipant, E catchParticipant, E finallyParticipant) {
			this.tryParticipant = tryParticipant;
			this.catchParticipant = catchParticipant;
			this.finallyParticipant = finallyParticipant;
		}

		@Override
		public String name() {
			return "Block";
		}	
		
		@Override
		public double size() {
			double ret = tryParticipant.size();
			if (catchParticipant != null) {
				ret += catchParticipant.size();
			}
			if (finallyParticipant != null) {
				ret += finallyParticipant.size();
			}
			return ret;
		}

		@Override
		public Diagnostic diagnose(ProgressMonitor progressMonitor) {		
			if (progressMonitor.isCancelled()) {
				progressMonitor.worked(1, "Cancelled");
				return new BasicDiagnostic(Status.CANCEL, "Progress monitor is cancelled", this);
			}
			BasicDiagnostic ret = new BasicDiagnostic(Status.SUCCESS, name());
			progressMonitor.setWorkRemaining(size());
			ret.add(tryParticipant.splitAndDiagnose(progressMonitor));
			if (catchParticipant != null) {
				ret.add(catchParticipant.splitAndDiagnose(progressMonitor));
			}
			if (finallyParticipant != null) {
				ret.add(finallyParticipant.splitAndDiagnose(progressMonitor));
			}
			return ret;
		}
		
		@Override
		public void commit(ProgressMonitor progressMonitor) throws Exception {
			if (progressMonitor.isCancelled()) {
				progressMonitor.worked(1, "Cancelled");
				throw new CancellationException();
			}
			progressMonitor.setWorkRemaining(size());
			tryParticipant.splitAndCommit(progressMonitor);
			if (catchParticipant != null) {
				catchParticipant.splitAndCommit(progressMonitor);
			}
			if (finallyParticipant != null) {
				finallyParticipant.splitAndCommit(progressMonitor);
			}
		}
		
		@Override
		public boolean rollback(ProgressMonitor progressMonitor) throws Exception {
			if (progressMonitor.isCancelled()) {
				progressMonitor.worked(1, "Cancelled");
				throw new CancellationException();
			}
			progressMonitor.setWorkRemaining(size());
			boolean result = true;
			if (finallyParticipant != null) {
				result = finallyParticipant.splitAndRollback(progressMonitor) && result;
			}
			if (catchParticipant != null) {
				result = catchParticipant.splitAndRollback(progressMonitor) && result;
			}
			result = tryParticipant.splitAndRollback(progressMonitor) && result;
			return result;
		}
		
		@Override
		public void close() throws Exception {
			tryParticipant.close();
			if (catchParticipant != null) {
				catchParticipant.close();
			}
			if (finallyParticipant != null) {
				finallyParticipant.close();
			}
		}
		
		@Override
		public String toString() {
			return super.toString() + " " + name() + " " + size();
		}

	}
		
	/**
	 * Supplier of context with {@link Exception} "service" indicating that the catch block shall be executed. 
	 * @param context
	 * @param eRef
	 * @return
	 */
	protected org.nasdanika.common.Supplier<Context> createCatchContextSupplier(Context context, Reference<Exception> eRef) {
		return new Supplier<Context>() {
	
			@Override
			public double size() {
				return 1;
			}
	
			@Override
			public String name() {
				return "Injecting error info";
			}
	
			@Override
			public Context execute(ProgressMonitor progressMonitor) throws Exception {
				return new Context() {
	
					@Override
					public Object get(String key) {
						Exception exception = eRef.get();
	
						if (ERROR_KEY.equals(key)) {
							return exception;
						}
						if (exception != null) {
							if (ERROR_TYPE_KEY.equals(key)) {
								return exception.getClass().getName();
							}
							if (ERROR_MESSAGE_KEY.equals(key)) {
								return exception.getMessage();
							}
						}
						
						return null;
					}
	
					@SuppressWarnings("unchecked")
					@Override
					public <T> T get(Class<T> type) {
						return type == Exception.class ? (T) eRef.get() : null;
					}
					
				}.compose(context);
				
			}
			
		};
		
	}

}
