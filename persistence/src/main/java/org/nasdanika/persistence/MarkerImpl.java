package org.nasdanika.persistence;

import java.util.Objects;

import org.yaml.snakeyaml.error.Mark;

/**
 * Implementation of {@link Marker} with equals() and hashCode() 
 * @author Pavel Vlasov
 *
 */
public class MarkerImpl implements Marker {

	private String location;
	private String position;
	
	public MarkerImpl(String location, String position) {
		this.location = location;
		this.position = position;
	}	
	
	private static String asPositon(int line, int column) {
		if (line == 0) {
			if (column == 0) {
				return null;
			}
			return ":" + column;
		}
		
		if (column == 0) {
			return String.valueOf(line);
		}
		
		return line + ":" + column;
	}

	public MarkerImpl(String location, int line, int column) {
		this(location, asPositon(line, column));
	}
	
	public MarkerImpl(String location, Mark mark) {
		this(location, mark == null ? 0 : mark.getLine() + 1, mark == null ? 0 : mark.getColumn() + 1);
	}
	
	@Override
	public String getPosition() {
		return position;
	}
	
	@Override
	public String toString() {
		return Marker.toString(this);
	}

	@Override
	public String getLocation() {
		return this.location;
	}

	@Override
	public int hashCode() {
		return Objects.hash(location, position);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarkerImpl other = (MarkerImpl) obj;
		return Objects.equals(location, other.location) && Objects.equals(position, other.position);
	}
	
}
