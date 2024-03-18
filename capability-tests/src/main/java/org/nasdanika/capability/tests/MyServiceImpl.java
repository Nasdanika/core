package org.nasdanika.capability.tests;

public class MyServiceImpl implements MyService {

	@Override
	public int count(String str) {
		return str.length();
	}

}
