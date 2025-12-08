package org.nasdanika.drawio.comparators;

import java.util.Comparator;
import java.util.Objects;

import org.nasdanika.drawio.ModelElement;

/**
 * Compares {@link ModelElement}s by their position in the parent.
 * @author Pavel
 *
 */
public class PositionModelElementComparator implements Comparator<ModelElement<?>> {
		

	@Override
	public int compare(ModelElement<?> o1, ModelElement<?> o2) {
		if (Objects.equals(o1, o2)) {
			return 0;
		}
		
		if (o1 == null) {
			return 1;
		}
		
		if (o2 == null) {
			return -1;
		}
		
		return o1.getPosition() - o2.getPosition();
	}

}
