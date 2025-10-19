package org.nasdanika.echarts;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import org.icepear.echarts.Chart;
import org.icepear.echarts.render.Engine;
import org.nasdanika.common.Util;

public abstract class AbstractChartGenerator<C extends Chart<?,?>> {
	
	final static String CHART_TEMPLATE = 
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
							option.tooltip = {};
							option.series[0].tooltip = {
								formatter: function(arg) { 
									return arg.value ? arg.value.description : null; 
								}
							};
							myChart.setOption(option);
							myChart.on("dblclick", function(params) {
								if (params.value) {
									if (params.value.link) {
										window.open(params.value.link, "_self");
									} else if (params.value.externalLink) {
										window.open(params.value.externalLink);
									}
								}
							});
							window.addEventListener("resize", myChart.resize);
						});		
					</script>
				</body>
			</html>
			""";	
	
	protected abstract C generateChart();	

	public String generateChartJSON() {
		Engine engine = new Engine();		
		C chart = generateChart();				
		return engine.renderJsonOption(chart);
		
// Example of option refinement		
//		JSONObject jChart = new JSONObject(chartJSON);
//		...manipulate here...
//		return jChart.toString(2);
	}	
	
	public void write(File output, String title, boolean open) throws IOException {		
		write(output, title, open, CHART_TEMPLATE);
	}
	
	public void write(File output, String title, boolean open, String chartTemplate) throws IOException {	
		String chartJSON = generateChartJSON();
		
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
