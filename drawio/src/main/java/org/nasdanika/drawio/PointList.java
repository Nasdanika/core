package org.nasdanika.drawio;

import java.util.List;

public interface PointList extends List<Point> {
	
	default Point add(double x, double y) {
		return add(size(), x, y);
	}
	
	Point add(int index, double x, double y);

}
