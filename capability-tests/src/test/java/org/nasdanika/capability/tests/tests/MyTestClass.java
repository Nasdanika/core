package org.nasdanika.capability.tests.tests;

public class MyTestClass {
	
	private byte[] data;

	public MyTestClass(byte[] data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return super.toString() + " " + new String(data);
	}
	
	public static byte[] factory(byte[] data) {
		return data;
	}

}
