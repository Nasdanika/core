package org.nasdanika.echarts;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.function.Consumer;

import org.icepear.echarts.Chart;
import org.icepear.echarts.render.Engine;
import org.json.JSONObject;
import org.nasdanika.common.Util;


public abstract class AbstractSericesChart<E,X,Y,S, C extends Chart<?,?>> implements SeriesChart<E,X,Y,S> {
	
	public final static String CHART_TEMPLATE = 
		"""
		<html>
			<head>
			    <title>${title}</title>
			    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootswatch/4.5.2/cerulean/bootstrap.min.css" id="nsd-bootstrap-theme-stylesheet">
			    <meta charset="utf-8">
			    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
			    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
			    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
			    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.min.js"></script>
			    <meta charset="utf-8">
			    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/Nasdanika-Models/html-app@master/gen/web-resources/css/app.css">
			    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
			    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/jstree@3.3.16/dist/themes/default/style.min.css">
			    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/github-markdown-css@5.5.0/github-markdown.min.css">
			    <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.9.0/build/styles/default.min.css">
			    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-vue@2.23.0/dist/bootstrap-vue.css">
			    <script src="https://cdn.jsdelivr.net/gh/Nasdanika-Models/html-app@master/gen/web-resources/js/common.js"></script>
			    <script src="https://cdn.jsdelivr.net/gh/Nasdanika-Models/html-app@master/gen/web-resources/js/dark-head.js"></script>
			    <script src="https://cdn.jsdelivr.net/npm/jstree@3.3.16/dist/jstree.min.js"></script>
			    <script src="https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.9.0/build/highlight.min.js"></script>
			    <script src="https://cdn.jsdelivr.net/npm/vue@2.7.16/dist/vue.min.js"></script>
			    <script src="https://cdn.jsdelivr.net/npm/bootstrap-vue@2.23.0/dist/bootstrap-vue.min.js"></script>
			    <script src="https://cdn.jsdelivr.net/gh/Nasdanika-Models/html-app@master/gen/web-resources/js/components/table.js"></script>
			    <script src="https://cdn.jsdelivr.net/npm/mermaid/dist/mermaid.min.js"></script>
			    <script src="https://cdn.jsdelivr.net/gh/Nasdanika-Models/ecore@master/graph/web-resources/components/table.js"></script>
			    <script src="https://cdnjs.cloudflare.com/ajax/libs/echarts/5.4.3/echarts.min.js"></script><!-- Global site tag (gtag.js) - Google Analytics -->
			</head>			
			<body>
				<H1>${title}</H1>
				<div class="container-fluid">
					<div id="chart-container-${chartContainerId}" class="row" style="height:80vh;width:100%">
					</div>
				</div>
				<script type="text/javascript">
					$(document).ready(function() {
						var dom = document.getElementById("chart-container-${chartContainerId}");
						var myChart = echarts.init(dom, null, {
							render: "canvas",
							useDirtyRect: false
						});		
						var option = ${chart};
						myChart.setOption(option);
						window.addEventListener("resize", myChart.resize);
					});		
				</script>
			</body>
		</html>
		""";	
	
	protected abstract C generateChart();
	
	@Override
	public void write(File output, String title, boolean open) throws IOException {
		write(output, title, open, null, null);
		
	}

	public String generateChartJSON(Consumer<C> chartConfigurator, Consumer<JSONObject> optionConfigurator) {
		Engine engine = new Engine();
		
		C chart = generateChart();
		if (chartConfigurator != null) {
			chartConfigurator.accept(chart);
		}
		
		String chartJSON = engine.renderJsonOption(chart);
		if (optionConfigurator != null) {
			JSONObject jChart = new JSONObject(chartJSON);
			optionConfigurator.accept(jChart);
			chartJSON = jChart.toString(2);
		}
		return chartJSON;
	}	
	
	public void write(
			File output, 
			String title, 
			boolean open,
			Consumer<C> chartConfigurator,
			Consumer<JSONObject> optionConfigurator) throws IOException {
		
		write(output, title, open, chartConfigurator, optionConfigurator, CHART_TEMPLATE);
	}
	
	public void write(
			File output, 
			String title, 
			boolean open,
			Consumer<C> chartConfigurator,
			Consumer<JSONObject> optionConfigurator,
			String chartTemplate) throws IOException {
	
		String chartJSON = generateChartJSON(chartConfigurator, optionConfigurator);
		
		Map<String,String> tokens = Map.of(
			"chart", chartJSON,
			"chartContainerId", "chart-container",
			"title", title
		);		
						
		String chartHTML = Util.interpolate(chartTemplate, tokens::get);
		
		output.getParentFile().mkdirs();
		Files.writeString(output.toPath(), chartHTML);
		if (open) {
			Desktop.getDesktop().browse(output.toURI());
		}
		
	}

}
