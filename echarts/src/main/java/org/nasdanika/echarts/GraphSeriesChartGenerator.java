package org.nasdanika.echarts;

import org.icepear.echarts.Graph;
import org.icepear.echarts.charts.graph.GraphEdgeLineStyle;
import org.icepear.echarts.charts.graph.GraphEmphasis;
import org.icepear.echarts.charts.graph.GraphSeries;
import org.icepear.echarts.components.series.SeriesLabel;

public class GraphSeriesChartGenerator extends AbstractChartGenerator<Graph> {
		
	/**
	 * Graph series with some default configuration but no data. 
	 * Override to add data. 
	 * @return
	 */
	protected GraphSeries generateGraphSeries() {
		return new org.icepear.echarts.charts.graph.GraphSeries()
				.setSymbolSize(24)
				.setDraggable(true)				
				.setLayout("none")
		        .setLabel(new SeriesLabel().setShow(true).setPosition("right"))
		        .setLineStyle(new GraphEdgeLineStyle().setColor("source").setCurveness(0))
		        .setRoam(true)
		        .setEdgeSymbol(new String[] { "none", "arrow" })
		        .setEmphasis(new GraphEmphasis().setFocus("adjacency")); 		
	}

	@Override
	protected Graph generateChart() {
    	return new org.icepear.echarts.Graph()
                .setLegend()
                .addSeries(generateGraphSeries());
	}	

}
