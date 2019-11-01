package org.nasdanika.common;

/**
 * Consumer creates a work factory which in turn creates work. The work may be applied to the argument and does not
 * return result. This interface is similar to {@link java.util.function.Consumer}, but allows to perform execution in
 * a {@link Context} with progress reporting and ability to cancel via {@link ProgressMonitor}.
 * @author Pavel Vlasov
 *
 * @param <T>
 */
public interface Consumer<T> extends Function<T,Void> {

}
