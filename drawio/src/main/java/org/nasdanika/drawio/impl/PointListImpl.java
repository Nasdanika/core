package org.nasdanika.drawio.impl;

import java.util.AbstractList;

import org.nasdanika.drawio.Point;
import org.nasdanika.drawio.PointList;

class PointListImpl extends AbstractList<Point> implements PointList {
	
	private ConnectionImpl connection;

	PointListImpl(ConnectionImpl connection) {
		this.connection = connection;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point addPoint(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

}
