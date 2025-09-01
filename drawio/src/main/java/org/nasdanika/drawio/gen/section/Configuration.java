package org.nasdanika.drawio.gen.section;

import java.util.Comparator;
import java.util.function.BiConsumer;

import org.eclipse.emf.common.util.URI;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.comparators.CartesianNodeComparator.Direction;
import org.nasdanika.drawio.comparators.FlowNodeComparator;
import org.nasdanika.drawio.comparators.LabelModelElementComparator;
import org.nasdanika.drawio.comparators.CartesianNodeComparator;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorInfo;

/**
 * Base class for processor configuration/customization classes.
 */
public class Configuration  {
	
	public static final String DOC_FORMAT_PROPERTY = "doc-format";
	public static final String DOC_REF_PROPERTY = "doc-ref";
	public static final String DOCUMENTATION_PROPERTY = "documentation";
	public static final String TITLE_PROPERTY = "title";
	public static final String PARENT_PROPERTY = "parent";
	
	public static final String SOURCE_KEY = "source";
	public static final String TARGET_KEY = "target";
	
	public static final String ICON_PROPERTY = "icon";
	
	public String getParentProperty() {
		return PARENT_PROPERTY;
	}	
	
	public String getTitleProperty() {
		return TITLE_PROPERTY;
	}	
		
	public String getDocumentationProperty() {
		return DOCUMENTATION_PROPERTY;
	}	
		
	public String getDocRefProperty() {
		return DOC_REF_PROPERTY;
	}	
	
	public String getDocFormatProperty() {
		return DOC_FORMAT_PROPERTY; 
	}		
	
	public String getSourceKey() {
		return SOURCE_KEY; 
	}		
	
	public String getTargetKey() {
		return TARGET_KEY; 
	}
	
	public String getIconProperty() {
		return ICON_PROPERTY;
	}	
	
	
	/**
	 * Base URI for resolving documentation instead of the
	 * document URI. Resolved relative to the document URI.
	 * If null, document URI is used.
	 * @return
	 */
	public URI getRefBaseURI(URI docURI) {
		return docURI;
	}	
	
	/**
	 * Filters processor. This implementation returns the processor AS-IS.
	 * @param <T>
	 * @param config
	 * @param processor
	 * @param infoProvider
	 * @param progressMonitor
	 */
	public <T> T filter(
			ProcessorConfig config, 
			T processor, 
			BiConsumer<Element,BiConsumer<ProcessorInfo<Object>,ProgressMonitor>> infoProvider,
			ProgressMonitor progressMonitor) {
		
		return processor;
	}
	
	public Comparator<Node> getPageNodesComparator() {
		return new FlowNodeComparator(
				null, 
				new CartesianNodeComparator(
						Direction.downRight, 
						new LabelModelElementComparator()));
	}

}
