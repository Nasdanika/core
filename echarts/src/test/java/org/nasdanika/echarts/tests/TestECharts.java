package org.nasdanika.echarts.tests;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;

import org.icepear.echarts.Option;
import org.icepear.echarts.charts.line.LineEmphasis;
import org.icepear.echarts.components.dataZoom.DataZoom;
import org.icepear.echarts.components.marker.MarkArea;
import org.icepear.echarts.components.marker.MarkArea2DDataItem;
import org.icepear.echarts.components.marker.MarkArea2DDataItemDim;
import org.icepear.echarts.origin.component.marker.MarkAreaDataItemOption;
import org.junit.jupiter.api.Test;
import org.nasdanika.echarts.LineSeriesChart;
import org.nasdanika.echarts.LineSeriesChart.LineSeriesBuilder;


public class TestECharts {
		
	@Test
	public void testLineSeriesChart() throws IOException {
		LineSeriesChart<Map.Entry<String,Number>, String, Number> lineSeriesChart = new LineSeriesChart<>((a,b) -> a.compareTo(b), Function.identity());		
		
		LineEmphasis emphasis = new LineEmphasis();
		emphasis.setFocus("series");
		
		LineSeriesBuilder<Entry<String, Number>> builder = lineSeriesChart.createSeries("Test", Map.Entry::getKey, Map.Entry::getValue);
		builder.addElement(Map.entry("Monday", 1));
		builder.addElement(Map.entry("Tuesday", 2));
		builder.addElement(Map.entry("Wednesday", 3));
		builder.addElement(Map.entry("Thursday", 8));
		builder.addElement(Map.entry("Friday", 11));
		builder.lineSeries().setEmphasis(emphasis);
		
		MarkArea2DDataItemDim startPoint = new MarkArea2DDataItemDim();
		startPoint.setName("Area");
		startPoint.setXAxis("Monday");
		
		MarkArea2DDataItem markAreaItem = new MarkArea2DDataItem();
		markAreaItem.setStartPoint(startPoint);

		MarkArea2DDataItemDim endPoint = new MarkArea2DDataItemDim();
		endPoint.setXAxis("Tuesday");
		markAreaItem.setEndPoint(endPoint);
		
		MarkArea markArea = new MarkArea();
		markArea.setData(new MarkAreaDataItemOption[] { markAreaItem });
		
		builder.lineSeries().setMarkArea(markArea);
		
		LineSeriesBuilder<Entry<String, Number>> builder2 = lineSeriesChart.createSeries("Test 2", Map.Entry::getKey, Map.Entry::getValue);
		builder2.addElement(Map.entry("Monday", 2));
		builder2.addElement(Map.entry("Tuesday", 3));
		builder2.addElement(Map.entry("Wednesday", 5));
		builder2.addElement(Map.entry("Thursday", 6));
		builder2.addElement(Map.entry("Friday", 7));
		builder2.lineSeries().setEmphasis(emphasis);
		
		
		lineSeriesChart.write(
				new File("target/line.html"), 
				"Test", 
				true,
				chart -> {
					Option option = chart.getOption();
					DataZoom dataZoom = new DataZoom();
					dataZoom.setType("inside");
					option.setDataZoom(dataZoom);
				},
				null);
	}
	
}
