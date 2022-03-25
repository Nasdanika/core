Diagram element style for component diagrams. If specified, the style diagram element is used as a template for a diagram element created to represent this artifact on a diagram.

The below code snippet provides several examples of style definition:

```yaml
  artifacts:
    binary:
      name: Docker Image
      modifiers: extension
    kubernetes-cluster:
      name: Cluster
      partition: true
      representations:
        uml: {}
        drawio: 
          type: drawio:./cluster.drawio
      children:
        node-a: 
          name: Node A
          description: Web requests processing node
          partition: true
          style:
            type: node
            dashed: true
            color: lightblue
          children:
            container-1: 
              name: Container A.1
              partition: true
              children:
                httpd:
                  name: Apache Web Server
                  relationships:
                    tomcat:
                      target: ../../container-2/children/tomcat
                      description: Reverse proxy
                      name: Pass request
                      style:
                        type: "#-->>" 
            container-2: 
              name: Container A.2              
              partition: true
              children:
                tomcat:
                  name: Tomcat
                  style:
                    stereotype: Servlet Container
                    properties:
                      drawio:
                        width: 150
                        height: 200
                        style:
                          shape: mxgraph.aws3d.cloudfront
        node-b: 
          name: Node B
          style:
            type: node
          partition: true
          children:
            container-1: 
              name: Container B.1
            container-2: 
              name: Container B.2                        
```

* Node A defines type as ``node`` with dashed border and light blue background.
* A relationship from the Apache Web Server to Tomcat customizes its appearance using ``#-->>`` connection style. For details on styling of PlantUML diagram elements see [PlantUML](https://plantuml.com/) documentation.
* Tomcat defines "Servlet Container" stereotype. It also defines style properties specific to drawio - height, width, and style as an AWS CloudFront icon.
* Node B defines its style type as ``node``.

#### Draw.io style properties

Defining style for Draw.io diagrams makes sense for template artifacts so their instances inherit the style.
You can open the Draw.io/Diagrams.net editor either [in the browser](https://app.diagrams.net/) or [desktop](https://www.diagrams.net/), create and style a diagram element, and then copy the style to YAML. 
You can do it in the editor by clicking Edit > Style and copying the style definition to YAML. You may copy it as a string or convert to YAML.
You can also go to Extras > Edit diagram and, copy the XML definition and convert to YAML.

For example, this XML definition

```xml
    <mxCell
        id="C2D87RZH6g-nyLimbJug-1"
        value="CloudFront"
        style="verticalLabelPosition=bottom;html=1;verticalAlign=top;strokeWidth=1;align=center;outlineConnect=0;dashed=0;outlineConnect=0;shape=mxgraph.aws3d.cloudfront;fillColor=#ECECEC;strokeColor=#5E5E5E;aspect=fixed;"
        parent="1"
        vertex="1">
      <mxGeometry x="370" y="630" width="103.8" height="169.79999999999998" as="geometry" />
    </mxCell>
```

would correspond to this YAML definition:

```yaml
style:
  properties:
    drawio:
      x: 370
      y: 630
      width: 103.8
      height: 169.79999999999998
      style: verticalLabelPosition=bottom;html=1;verticalAlign=top;strokeWidth=1;align=center;outlineConnect=0;dashed=0;outlineConnect=0;shape=mxgraph.aws3d.cloudfront;fillColor=#ECECEC;strokeColor=#5E5E5E;aspect=fixed;             
```

or this one, which is more readable:

```yaml
style:
  properties:
    drawio:
      x: 370
      y: 630
      width: 103.8
      height: 169.79999999999998
      style: 
        verticalLabelPosition: bottom
        html: 1
        verticalAlign: top
        strokeWidth: 1
        align: center
        outlineConnect: 0
        dashed: 0
        outlineConnect: 0
        shape: mxgraph.aws3d.cloudfront
        fillColor: "#ECECEC"
        strokeColor: "#5E5E5E"
        aspect: fixed
```

You don't need to specify element position (x and y) as it will be automatically positioned.
You may also skip some style attributes which are not essential or have default values - experiment to find out which ones to keep and which ones to drop.
