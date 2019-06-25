package org.nasdanika.common;

import java.io.PrintStream;

/**
 * Progress monitor reporting to a {@link PrintStream}, e.g. ``System.out``.
 * This monitor indents sub-tasks and worked messages. It can be thought of as a hierarchical logger.
 * @author Pavel
 *
 */
public class PrintStreamProgressMonitor implements ProgressMonitor {

	private PrintStream out;
	private boolean closeStream;
	private String indent;
	private int indentIncrement;
	private boolean cancelled;
	
	/**
	 * Constructs a progress monitor for a given print stream.
	 * @param out
	 * @param indent Indent in spaces for this monitor.
	 * @param indentIncrement Increment to add to the indent of this monitor when constructing a sub-monitor.
	 * @param closeStream if true the monitor closes the stream in its close() method.
	 */
	public PrintStreamProgressMonitor(PrintStream out, int indent, int indentIncrement, boolean closeStream) {
		this.out = out;
		StringBuilder indentBuilder = new StringBuilder();
		for (int i = 0; i < indent; ++i) {
			indentBuilder.append(' ');
		}
		this.indent = indentBuilder.toString();
		this.closeStream = closeStream;
	}
	
	/**
	 * Constructs a progres monitor outputting to System.out with indent 0, indentIncrement 2 and not closing the stream.
	 */
	public PrintStreamProgressMonitor() {
		this(System.out, 0, 2, false);
	}
	
	@Override
	public void close() {
		if (closeStream) {
			out.close();
		}
	}

	@Override
	public void cancel() {
		cancelled = true;
	}

	@Override
	public boolean isCancelled() {
		return cancelled;
	}

	@Override
	public ProgressMonitor split(String taskName, long ticks, Object... details) {
		out.println(indent+"  "+taskName+" ("+ticks+")");
		for (Object d: details) {
			out.println(formatDetail(d, indent + "    "));			
		}
		return new PrintStreamProgressMonitor(out, indent.length()+indentIncrement, indentIncrement, false) {
			
			@Override
			public boolean isCancelled() {
				return PrintStreamProgressMonitor.this.isCancelled();
			}
			
			@Override
			public void cancel() {
				PrintStreamProgressMonitor.this.cancel();
			}
			
		};
	}
	
	/**
	 * Formats detail element for output. This implementation concatenates the indent with the detail.
	 * @param detail
	 * @param indent
	 * @return
	 */
	protected String formatDetail(Object detail, String indent) {
		return indent + detail;
	}

	@Override
	public void worked(long work, String progressMessage) {
		out.println(indent+"  ["+work+"] "+progressMessage);
	}

//	@Override
//	public void setWorkRemaining(int ticks) {
//		// NOP
//	}

}
