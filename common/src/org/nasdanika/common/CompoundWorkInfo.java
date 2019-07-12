package org.nasdanika.common;

import java.util.List;

/**
 * Information about compound work, a WBS (Work breakdown structure).  
 * @author Pavel
 *
 */
public interface CompoundWorkInfo extends WorkInfo {
	
	List<WorkInfo> getChildren();

}
