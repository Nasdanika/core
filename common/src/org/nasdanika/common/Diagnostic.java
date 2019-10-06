package org.nasdanika.common;

import java.util.List;

public interface Diagnostic {
	
	Status getStatus();
	
	String getMessage();
	
	List<Object> getData();
	
	List<Diagnostic> getChildren();

}
