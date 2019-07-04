package org.nasdanika.common.persistence;

import org.yaml.snakeyaml.error.Mark;

/**
 * Implementation of {@link Marker} with equals() and hashCode() 
 * @author Pavel Vlasov
 *
 */
public class MarkerImpl implements Marker {

	private int column;
	private int line;

	public MarkerImpl(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	public MarkerImpl(Mark mark) {
		this(mark.getLine() + 1, mark.getColumn() + 1);
	}

	@Override
	public int getLine() {
		return line;
	}

	@Override
	public int getColumn() {
		return column;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Marker && ((Marker) obj).getLine() == getLine() && ((Marker) obj).getColumn() == getColumn();
	}
	
	@Override
	public int hashCode() {
		return getLine() * 31 + getColumn();
	}
	
	@Override
	public String toString() {
		return getLine()+":"+getColumn();
	}
			
}
