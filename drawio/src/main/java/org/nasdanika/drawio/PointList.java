package org.nasdanika.drawio;

import java.util.List;

public interface PointList extends List<Point> {
	
	default Point add(double x, double y) {
		return add(size(), x, y);
	}
	
	default Point add(int index, double x, double y) {
		Point point = add(index);
		point.setLocation(x, y);
		return point;
	}
	
	Point add(int index);
	
	default Point add() {
		return add(size());
	}

}
