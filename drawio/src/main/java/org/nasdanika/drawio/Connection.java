package org.nasdanika.drawio;

/**
 * Connection between two nodes. Both source and target can be null.
 * @author Pavel
 *
 */
public interface Connection extends LayerElement<Connection>, org.nasdanika.graph.Connection {
	
	@Override
	Node getSource();
	
	void setSource(Node node);
	
	Point setSourcePoint(double x, double y);
	
	Point getSourcePoint();
	
	@Override
	Node getTarget();
	
	void setTarget(Node node);
		
	Point setTargetPoint(double x, double y);
	
	Point getTargetPoint();
	
	PointList getPoints();

	void addPoint(double x, double y);
}

//<mxGraphModel dx="3346" dy="1071" grid="1" gridSize="10" guides="1" tooltips="1" connect="1" arrows="1" fold="1" page="1" pageScale="1" pageWidth="1100" pageHeight="850" math="0" shadow="0">
//<root>
//  <mxCell id="0" />
//  <mxCell id="1" parent="0" />
//  <object label="c3" id="c3">
//    <mxCell style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;" edge="1" parent="1" source="n1" target="n2">
//      <mxGeometry relative="1" as="geometry" />
//    </mxCell>
//  </object>
//  <object label="c2" id="c2">
//    <mxCell style="edgeStyle=orthogonalEdgeStyle;rounded=0;orthogonalLoop=1;jettySize=auto;html=1;" edge="1" parent="1" source="n1">
//      <mxGeometry relative="1" as="geometry">
//        <mxPoint x="880" y="1080" as="targetPoint" />
//      </mxGeometry>
//    </mxCell>
//  </object>
//  <object label="N1" id="n1">
//    <mxCell style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
//      <mxGeometry x="490" y="940" width="120" height="60" as="geometry" />
//    </mxCell>
//  </object>
//  <object label="N2" id="n2">
//    <mxCell style="rounded=0;whiteSpace=wrap;html=1;" vertex="1" parent="1">
//      <mxGeometry x="390" y="1215" width="120" height="60" as="geometry" />
//    </mxCell>
//  </object>
//  <object label="c1" id="c1">
//    <mxCell style="endArrow=classic;html=1;rounded=0;entryX=0;entryY=0.25;entryDx=0;entryDy=0;" edge="1" parent="1" target="n1">
//      <mxGeometry width="50" height="50" relative="1" as="geometry">
//        <mxPoint x="230" y="990" as="sourcePoint" />
//        <mxPoint x="580" y="950" as="targetPoint" />
//        <Array as="points">
//          <mxPoint x="360" y="1080" />
//          <mxPoint x="360" y="960" />
//        </Array>
//      </mxGeometry>
//    </mxCell>
//  </object>
//</root>
//</mxGraphModel>
