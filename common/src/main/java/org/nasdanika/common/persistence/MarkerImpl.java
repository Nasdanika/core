package org.nasdanika.common.persistence;

import org.nasdanika.common.Util;
import org.yaml.snakeyaml.error.Mark;

/**
 * Implementation of {@link Marker} with equals() and hashCode() 
 * @author Pavel Vlasov
 *
 */
public class MarkerImpl implements Marker {

	private int column;
	private int line;
	private String location;

	public MarkerImpl(String location, int line, int column) {
		this.location = location;
		this.line = line;
		this.column = column;
	}
	
	public MarkerImpl(String location, Mark mark) {
		this(location, mark == null ? 0 : mark.getLine() + 1, mark == null ? 0 : mark.getColumn() + 1);
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
		return obj instanceof Marker && ((Marker) obj).getLine() == getLine() && ((Marker) obj).getColumn() == getColumn() && equal(((Marker) obj).getLocation(), location) ;
	}
	
	private boolean equal(String a, String b) {
		return a == null ? b == null : a.equals(b);
	}

	@Override
	public int hashCode() {
		return getLine() * 31 + getColumn();
	}
	
	@Override
	public String toString() {
		return (Util.isBlank(location) ? "" : location) + " " + getLine()+":"+getColumn();
	}

	@Override
	public String getLocation() {
		return this.location;
	}
			
}
