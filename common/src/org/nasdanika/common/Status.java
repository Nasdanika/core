package org.nasdanika.common;

public enum Status {
	
	INFO,
	SUCCESS,
	WARNING,
	
	/**
	 * Depending on a situation it might be possible to proceed with errors, e.g. to report them.   
	 */
	ERROR,
	
	/**
	 * This status is more severe than error and generally means that no further processing is possible. FAIL vs ERROR is similar to {@link Error} vs {@link Exception}.
	 * A reason to use FAIL instead of throwing an exception is that an exception would report the first problem, while with FAIL multiple reasons for being unable to proceed might be collected. 
	 */
	FAIL,
	
	CANCEL

}
