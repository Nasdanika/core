Properties can be used to customize the documentation generation process, e.g. provide configuration for generation of representation diagram elements.

The below snippet shows how to use properties to customize diagram element shape for draw.io diagrams:

```yml
service-catalog:
  name: AWS Service Catalog
  properties:
    representation:
      drawio:
        width: 150
        height: 200
        style:
          shape: mxgraph.aws3d.cloudfront
```

Properties under the ``representation`` are copied to the representation diagram element. 
Then, properties under the ``drawio`` key are used during generation of draw.io diagrams. 
The following properties are supported by drawio diagram elements:

* ``height`` - integer
* ``width`` - integer
* ``style`` - string or map

Properties with other keys become user object attributes.