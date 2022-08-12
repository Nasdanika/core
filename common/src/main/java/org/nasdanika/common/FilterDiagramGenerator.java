package org.nasdanika.common;

public class FilterDiagramGenerator implements DiagramGenerator {
	
	private DiagramGenerator chain;

	public FilterDiagramGenerator(DiagramGenerator chain) {
		this.chain = chain;
	}

	@Override
	public boolean isSupported(String dialect) {
		return chain.isSupported(dialect);
	}

	@Override
	public String generateDiagram(String spec, String dialect) {
		return chain.generateDiagram(spec, dialect);
	}

}
