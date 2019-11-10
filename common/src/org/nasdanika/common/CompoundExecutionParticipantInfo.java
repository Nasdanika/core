package org.nasdanika.common;

import java.util.List;

/**
 * Information about compound work, a WBS (Supplier breakdown structure).  
 * @author Pavel
 *
 */
public interface CompoundExecutionParticipantInfo extends ExecutionParticipantInfo {
	
	List<ExecutionParticipantInfo> getChildren();

}
