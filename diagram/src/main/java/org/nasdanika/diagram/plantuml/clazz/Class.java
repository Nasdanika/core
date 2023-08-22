package org.nasdanika.diagram.plantuml.clazz;

public class Class extends Type {
	
	public Class(String name) {
		super(name);
	}

	private boolean isAbstract;

	public boolean isAbstract() {
		return isAbstract;
	}

	public void setAbstract(boolean isAbstract) {
		this.isAbstract = isAbstract;
	}
	
	@Override
	protected String getType() {
		return isAbstract() ? "abstract class" : "class";
	}

}
