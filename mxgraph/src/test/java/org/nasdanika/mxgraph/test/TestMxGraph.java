package org.nasdanika.mxgraph.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import org.junit.Test;
import org.nasdanika.common.DefaultConverter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.mxgraph.io.mxCodec;
import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.mxIGraphLayout;
import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxICell;
import com.mxgraph.model.mxIGraphModel;
import com.mxgraph.util.mxDomUtils;
import com.mxgraph.util.mxXmlUtils;
import com.mxgraph.view.mxGraph;

/**
 * Tests of agile flows.
 * @author Pavel
 *
 */
public class TestMxGraph {
	
	private Charset charset = StandardCharsets.UTF_8;
	private final String AWS_DIAGRAM_ENCODED = "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";
	private final String AWS_DIAGRAM_DECODED = "<mxGraphModel dx=\"1086\" dy=\"1633\" grid=\"1\" gridSize=\"10\" guides=\"1\" tooltips=\"1\" connect=\"1\" arrows=\"1\" fold=\"1\" page=\"1\" pageScale=\"1\" pageWidth=\"1169\" pageHeight=\"827\" math=\"0\" shadow=\"0\"><root><mxCell id=\"0\"/><mxCell id=\"1\" parent=\"0\"/><UserObject label=\"Shared Services Account\" link=\"children/shared-services-account/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-176\"><mxCell style=\"points=[[0,0],[0.25,0],[0.5,0],[0.75,0],[1,0],[1,0.25],[1,0.5],[1,0.75],[1,1],[0.75,1],[0.5,1],[0.25,1],[0,1],[0,0.75],[0,0.5],[0,0.25]];outlineConnect=0;gradientColor=none;html=1;whiteSpace=wrap;fontSize=12;fontStyle=0;shape=mxgraph.aws4.group;grIcon=mxgraph.aws4.group_aws_cloud_alt;strokeColor=#232F3E;fillColor=none;verticalAlign=top;align=left;spacingLeft=30;fontColor=#232F3E;dashed=0;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"219\" y=\"-349.5\" width=\"220\" height=\"310\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Log Archive Account\" link=\"children/log-archive-account/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-185\"><mxCell style=\"points=[[0,0],[0.25,0],[0.5,0],[0.75,0],[1,0],[1,0.25],[1,0.5],[1,0.75],[1,1],[0.75,1],[0.5,1],[0.25,1],[0,1],[0,0.75],[0,0.5],[0,0.25]];outlineConnect=0;gradientColor=none;html=1;whiteSpace=wrap;fontSize=12;fontStyle=0;shape=mxgraph.aws4.group;grIcon=mxgraph.aws4.group_aws_cloud_alt;strokeColor=#232F3E;fillColor=none;verticalAlign=top;align=left;spacingLeft=30;fontColor=#232F3E;dashed=0;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"464\" y=\"-349.5\" width=\"230\" height=\"310\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Security Account\" link=\"children/security-account/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-186\"><mxCell style=\"points=[[0,0],[0.25,0],[0.5,0],[0.75,0],[1,0],[1,0.25],[1,0.5],[1,0.75],[1,1],[0.75,1],[0.5,1],[0.25,1],[0,1],[0,0.75],[0,0.5],[0,0.25]];outlineConnect=0;gradientColor=none;html=1;whiteSpace=wrap;fontSize=12;fontStyle=0;shape=mxgraph.aws4.group;grIcon=mxgraph.aws4.group_aws_cloud_alt;strokeColor=#232F3E;fillColor=none;verticalAlign=top;align=left;spacingLeft=30;fontColor=#232F3E;dashed=0;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"720\" y=\"-349.5\" width=\"230\" height=\"310\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"AWS Cloud\" link=\"children/cloud/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-175\"><mxCell style=\"points=[[0,0],[0.25,0],[0.5,0],[0.75,0],[1,0],[1,0.25],[1,0.5],[1,0.75],[1,1],[0.75,1],[0.5,1],[0.25,1],[0,1],[0,0.75],[0,0.5],[0,0.25]];outlineConnect=0;gradientColor=none;html=1;whiteSpace=wrap;fontSize=12;fontStyle=0;shape=mxgraph.aws4.group;grIcon=mxgraph.aws4.group_aws_cloud_alt;strokeColor=#232F3E;fillColor=none;verticalAlign=top;align=left;spacingLeft=30;fontColor=#232F3E;dashed=0;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"219\" y=\"-789.5\" width=\"730\" height=\"410\" as=\"geometry\"/></mxCell></UserObject><mxCell id=\"UEzPUAAOIrF-is8g5C7q-195\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-177\" target=\"UEzPUAAOIrF-is8g5C7q-178\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell><mxCell id=\"UEzPUAAOIrF-is8g5C7q-196\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-178\" target=\"UEzPUAAOIrF-is8g5C7q-179\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell><mxCell id=\"UEzPUAAOIrF-is8g5C7q-198\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-178\" target=\"UEzPUAAOIrF-is8g5C7q-181\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"><Array as=\"points\"><mxPoint x=\"355\" y=\"-689.5\"/></Array></mxGeometry></mxCell><mxCell id=\"UEzPUAAOIrF-is8g5C7q-199\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-178\" target=\"UEzPUAAOIrF-is8g5C7q-182\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell><mxCell id=\"UEzPUAAOIrF-is8g5C7q-200\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-178\" target=\"UEzPUAAOIrF-is8g5C7q-183\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"><Array as=\"points\"><mxPoint x=\"576\" y=\"-689.5\"/></Array></mxGeometry></mxCell><mxCell id=\"UEzPUAAOIrF-is8g5C7q-197\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-179\" target=\"UEzPUAAOIrF-is8g5C7q-180\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell><mxCell id=\"UEzPUAAOIrF-is8g5C7q-201\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-179\" target=\"UEzPUAAOIrF-is8g5C7q-184\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"/></mxCell><mxCell id=\"UEzPUAAOIrF-is8g5C7q-202\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-184\" target=\"UEzPUAAOIrF-is8g5C7q-176\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"><Array as=\"points\"><mxPoint x=\"739\" y=\"-369.5\"/><mxPoint x=\"329\" y=\"-369.5\"/></Array></mxGeometry></mxCell><mxCell id=\"UEzPUAAOIrF-is8g5C7q-203\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-184\" target=\"UEzPUAAOIrF-is8g5C7q-185\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"><Array as=\"points\"><mxPoint x=\"739\" y=\"-369.5\"/><mxPoint x=\"674\" y=\"-369.5\"/></Array></mxGeometry></mxCell><mxCell id=\"UEzPUAAOIrF-is8g5C7q-204\" style=\"edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;entryX=0.5;entryY=0;entryDx=0;entryDy=0;startArrow=none;startFill=0;endArrow=open;endFill=0;strokeColor=#808080;strokeWidth=2;\" parent=\"1\" source=\"UEzPUAAOIrF-is8g5C7q-184\" target=\"UEzPUAAOIrF-is8g5C7q-186\" edge=\"1\"><mxGeometry relative=\"1\" as=\"geometry\"><Array as=\"points\"><mxPoint x=\"739\" y=\"-369.5\"/><mxPoint x=\"835\" y=\"-369.5\"/></Array></mxGeometry></mxCell><object label=\"Amazon S3&lt;br&gt;Bucket&lt;br&gt;\" semanticTarget=\"xyz\" link=\"children/cloud/children/s3-bucket/index.html\" my-property=\"purum\" id=\"UEzPUAAOIrF-is8g5C7q-177\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=none;fillColor=#277116;strokeColor=none;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;pointerEvents=1;shape=mxgraph.aws4.bucket;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"249\" y=\"-749.5\" width=\"75\" height=\"78\" as=\"geometry\"/></mxCell></object><UserObject label=\"AWS&lt;br&gt;CodePipeline&lt;br&gt;\" link=\"children/cloud/children/code-pipeline/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-178\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#4D72F3;gradientDirection=north;fillColor=#3334B9;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.codepipeline;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"429\" y=\"-749.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"AWS&lt;br&gt;Organizations&lt;br&gt;\" link=\"children/cloud/children/organizations/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-179\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#F54749;gradientDirection=north;fillColor=#C7131F;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.organizations;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"700\" y=\"-749.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"AWS Single&lt;br&gt;Sign-on&lt;br&gt;\" link=\"children/cloud/children/sso/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-180\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#F34482;gradientDirection=north;fillColor=#BC1356;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.cloudwatch;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"856\" y=\"-749.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Account&lt;br&gt;Baseline&lt;br&gt;\" link=\"children/cloud/children/account-baseline/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-181\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#F34482;gradientDirection=north;fillColor=#BC1356;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.cloudformation;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"316\" y=\"-569.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"AWS Service&lt;br&gt;Catalog&lt;br&gt;\" link=\"children/cloud/children/service-catalog/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-182\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#F34482;gradientDirection=north;fillColor=#BC1356;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.service_catalog;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"429\" y=\"-569.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"AWS&lt;br&gt;Parameter&lt;br&gt;Store&lt;br&gt;\" link=\"children/cloud/children/parameter-store/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-183\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=none;fillColor=#BC1356;strokeColor=none;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;pointerEvents=1;shape=mxgraph.aws4.parameter_store;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"539\" y=\"-569.5\" width=\"75\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Core OU\" link=\"children/cloud/children/core-ou/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-184\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=none;fillColor=#BC1356;strokeColor=none;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;pointerEvents=1;shape=mxgraph.aws4.resources;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"705\" y=\"-559.5\" width=\"68\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Account&lt;br&gt;Baseline&lt;br&gt;\" link=\"children/shared-services-account/children/account-baseline/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-187\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#F34482;gradientDirection=north;fillColor=#BC1356;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.cloudformation;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"239\" y=\"-297.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Amazon VPC\" link=\"children/shared-services-account/children/vpc/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-188\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#945DF2;gradientDirection=north;fillColor=#5A30B5;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.vpc;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"340\" y=\"-298.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Account&lt;br&gt;Baseline&lt;br&gt;\" link=\"children/log-archive-account/children/account-baseline/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-189\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#F34482;gradientDirection=north;fillColor=#BC1356;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.cloudformation;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"479\" y=\"-298.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Aggregate&lt;br&gt;CloudTrail&lt;br&gt;and Config Logs&lt;br&gt;\" link=\"children/log-archive-account/children/logs/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-190\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=none;fillColor=#5A30B5;strokeColor=none;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;pointerEvents=1;shape=mxgraph.aws4.flow_logs;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"581\" y=\"-299.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Account&lt;br&gt;Baseline&lt;br&gt;\" link=\"children/security-account/children/account-baseline/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-191\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#F34482;gradientDirection=north;fillColor=#BC1356;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.cloudformation;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"740\" y=\"-299.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Security&lt;br&gt;Cross-Account&lt;br&gt;Roles&lt;br&gt;\" link=\"children/security-account/children/roles/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-192\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=none;fillColor=#C7131F;strokeColor=none;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;pointerEvents=1;shape=mxgraph.aws4.addon;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"849\" y=\"-280.5\" width=\"78\" height=\"40\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Amazon&lt;br&gt;GuardDuty&lt;br&gt;\" link=\"children/security-account/children/guard-duty/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-193\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#F54749;gradientDirection=north;fillColor=#C7131F;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.guardduty;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"740\" y=\"-169.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject><UserObject label=\"Amazon SNS\" link=\"children/security-account/children/sns/index.html\" id=\"UEzPUAAOIrF-is8g5C7q-194\"><mxCell style=\"outlineConnect=0;fontColor=#232F3E;gradientColor=#F34482;gradientDirection=north;fillColor=#BC1356;strokeColor=#ffffff;dashed=0;verticalLabelPosition=bottom;verticalAlign=top;align=center;html=1;fontSize=12;fontStyle=0;aspect=fixed;shape=mxgraph.aws4.resourceIcon;resIcon=mxgraph.aws4.sns;labelBackgroundColor=#ffffff;\" parent=\"1\" vertex=\"1\"><mxGeometry x=\"849\" y=\"-169.5\" width=\"78\" height=\"78\" as=\"geometry\"/></mxCell></UserObject></root></mxGraphModel>";
	
	@Test
	public void testDeflateInflate() throws Exception {
		byte[] input = new byte[20000];
		new Random().nextBytes(input);
		byte[] deflated = deflate(input);
		byte[] inflated = inflate(deflated);
		int isEqual = Arrays.compare(input, inflated);
		assertTrue(isEqual == 0);
	}
	
	@Test
	public void testEncodeDiagram() throws Exception {
		System.out.println(encodeDiagram(AWS_DIAGRAM_DECODED));
	}

	@Test
	public void testWriteDiagram() throws Exception {
		String awsFile = DefaultConverter.INSTANCE.stringContent(getClass().getResource("aws.drawio"));
		Files.writeString(new File("target/aws.drawio").toPath(), awsFile);
		String awsDiagram = decodeDiagram(awsFile);
		
		Document diagramDoc = mxXmlUtils.parseXml(awsDiagram);
		mxCodec codec = new mxCodec(diagramDoc);
		mxIGraphModel graphModel = (mxIGraphModel) codec.decode(diagramDoc.getDocumentElement());
		mxGraph graph = new mxGraph(graphModel);
		
		
		System.out.println(diagramDoc.hashCode());
		mxCell root = (mxCell) graphModel.getRoot();
		dump(root, 0);

		codec = new mxCodec();

		Node encodedNode = codec.encode(graph.getModel());
		
		String encoded = encodeDiagram(encodedNode);
		File encodedFile = new File("target/aws-encoded-uncompressed.drawio");
		Files.writeString(encodedFile.toPath(), encoded);
	}
	
	@Test
	public void testNewDiagram() throws Exception {
//		String templateFile = DefaultConverter.INSTANCE.stringContent(getClass().getResource("template.drawio"));
//		Document xmlDocument = mxXmlUtils.parseXml(templateFile);
//        Element diagram = (Element) xmlDocument.getElementsByTagName("diagram").item(0);
//        Node model = diagram.getElementsByTagName("mxGraphModel").item(0);
//		mxCodec codec = new mxCodec(xmlDocument);
//		mxIGraphModel graphModel = (mxIGraphModel) codec.decode(model);
		mxGraph graph = new mxGraph(/* graphModel */);
		
		mxIGraphModel graphModel = graph.getModel();
		graphModel.beginUpdate();
		Object parent = graph.getDefaultParent();
		try	{
			Document doc = mxDomUtils.createDocument();
			Element v1uo = doc.createElement("UserObject");
			v1uo.setAttribute("label", "Hren");
			v1uo.setAttribute("link", "https://nasdanika.org");
			v1uo.setAttribute("uri", "nasdanika://hmmm");
			mxCell v1 = (mxCell) graph.insertVertex(parent, null, v1uo, 40, 40, 160, 60);
			v1.setStyle("verticalAlign=top");
			Object v11 = graph.insertVertex(v1, null, "Purum", 20, 20, 80, 30);
			Object v2 = graph.insertVertex(parent, null, "World!", 280, 250, 160, 60);
			Object v21 = graph.insertVertex(parent, null, "Govno", 20, 20, 80, 30);
			graph.insertEdge(parent, null, "Edge", v1, v2);
			graph.insertEdge(parent, null, "Cross Edge", v11, v21);
			
			mxIGraphLayout layout = new mxCircleLayout(graph);
			layout.execute(parent);
		} finally {
			graphModel.endUpdate();
		}		
		
		// User object
		// layout
		
		mxCell root = (mxCell) graphModel.getRoot();
		dump(root, 0);

		mxCodec codec = new mxCodec();

		Node encodedNode = codec.encode(graphModel);
		
		String encoded = encodeDiagram(encodedNode);
		File encodedFile = new File("target/new.drawio");
		Files.writeString(encodedFile.toPath(), encoded);
	}
	
	
	@Test
	public void testDecodeEncodeDiagram() throws Exception {
		String awsFile = DefaultConverter.INSTANCE.stringContent(getClass().getResource("aws.drawio"));
		Files.writeString(new File("target/aws.drawio").toPath(), awsFile);
		String awsDiagram = decodeDiagram(awsFile);
		String encoded = encodeDiagram(mxXmlUtils.parseXml(awsDiagram).getDocumentElement());
		String decoded = decodeDiagram(encoded);
		assertEquals(awsDiagram, decoded);
		File encodedFile = new File("target/aws-encoded.drawio");
		Files.writeString(encodedFile.toPath(), encoded);
		
		String awsEncodedFile = DefaultConverter.INSTANCE.stringContent(encodedFile.toURI().toURL());
		String awsDecodedDiagram = decodeDiagram(awsEncodedFile);
		assertEquals(awsDiagram, awsDecodedDiagram);		
	}
		
//	@Test
//	public void processDrawio() throws Exception {
//		String diagramSource = decodeDiagram(DefaultConverter.INSTANCE.stringContent(getClass().getResource("aws.drawio")));
//        System.out.println(diagramSource);
//        
////        Document diagramDoc = mxXmlUtils.parseXml(textContent);
////        mxCodec codec = new mxCodec(diagramDoc);
////        mxIGraphModel graphModel = (mxIGraphModel) codec.decode(diagramDoc.getDocumentElement());
////        mxGraph graph = new mxGraph(graphModel);
////        
////		mxCell root = (mxCell) graphModel.getRoot();
////        dump(root, 0);
////        
////        codec = new mxCodec();
////        
////        Node encoded = codec.encode(graph.getModel());
//        String encodedStr = textContent; // mxXmlUtils.getXml(encoded);
//        System.out.println(encodedStr);
//        
//       
//        String urlEncoded = URLEncoder.encode(encodedStr, Charset.defaultCharset());
//		byte[] compressed = deflate(urlEncoded.getBytes(Charset.defaultCharset()));
//        String compressedEncoded = Base64.getEncoder().encodeToString(compressed);
//        System.out.println(compressedEncoded);
//		diagram.setTextContent(compressedEncoded);
//        
//	}
	
	/**
	 * Decodes text content of the diagram element.
	 * @param diagramSource
	 * @return
	 * @throws Exception
	 */
	private String decodeDiagram(String diagramSource) throws Exception {
		Document xmlDocument = mxXmlUtils.parseXml(diagramSource);
        Node diagram = xmlDocument.getElementsByTagName("diagram").item(0);
        String textContent = diagram.getTextContent();

        if (textContent.startsWith("<")) {
        	return textContent;
        }

        //content is compressed, following steps need to take place

        byte[] compressed = Base64.getDecoder().decode(textContent);
        byte[] decompressed = inflate(compressed);
        String decompressedStr = new String(decompressed, charset);
        return URLDecoder.decode(decompressedStr, charset);
	}
	
	private String encodeDiagram(Node diagramNode) throws Exception {
		String templateSource = DefaultConverter.INSTANCE.stringContent(getClass().getResource("template.drawio"));
		Document xmlDocument = mxXmlUtils.parseXml(templateSource);
        Node diagram = xmlDocument.getElementsByTagName("diagram").item(0);
        diagram.appendChild(xmlDocument.importNode(diagramNode, true));
        return mxXmlUtils.getXml(xmlDocument);
	}
	
	private String encodeDiagram(String diagramSource) throws Exception {
		String urlEncoded = URLEncoder.encode(diagramSource, charset);
		byte[] compressed = deflate(urlEncoded.getBytes(charset));
		String compressedEncoded = Base64.getEncoder().encodeToString(compressed);		
		
		String templateSource = DefaultConverter.INSTANCE.stringContent(getClass().getResource("template.drawio"));
		Document xmlDocument = mxXmlUtils.parseXml(templateSource);
        Node diagram = xmlDocument.getElementsByTagName("diagram").item(0);
        diagram.setTextContent(compressedEncoded);
        return mxXmlUtils.getXml(xmlDocument);
	}

	private void dump(mxICell cell, int indent) {
		for (int i = 0; i < indent; ++i) {
			System.out.print("  ");
		}
		Object cellValue = cell.getValue();
		System.out.println(cell + " " + cellValue);
		for (int i = 0; i < cell.getChildCount(); ++i) {
        	mxICell child = cell.getChildAt(i);
			dump(child, indent + 1);
        }
	}	
		
    private byte[] inflate(byte[] content) throws Exception {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	try (ByteArrayInputStream source = new ByteArrayInputStream(content); OutputStream target = new InflaterOutputStream(baos, new Inflater(true))) {
            byte[] buf = new byte[8192];
            int length;
            while ((length = source.read(buf)) > 0) {
                target.write(buf, 0, length);
            }
    	}
    	
    	return baos.toByteArray();
    }
    
    private byte[] deflate(byte[] content) throws Exception {
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	Deflater deflater = new Deflater(Deflater.DEFAULT_COMPRESSION, true);
		try (ByteArrayInputStream source = new ByteArrayInputStream(content); OutputStream target = new DeflaterOutputStream(baos, deflater)) {
            byte[] buf = new byte[8192];
            int length;
            while ((length = source.read(buf)) > 0) {
                target.write(buf, 0, length);
            }
    	}
    	
    	return baos.toByteArray();
    }
    
}
