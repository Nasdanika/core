Mapping of representation names to values - [Diagrams](https://docs.nasdanika.org/modules/core/modules/diagram/modules/model/Diagram.html) which serve as templates for generating diagram content from the package element.

The below code snippet defines two representations:

* ``flow`` with ``plantuml`` type (default). 
* ``generated`` with ``drawio`` type. 

```yml
test:
  flow-flow:
    name: Test
    representations:
      flow:
        hide-empty-description: true
        vertical: false
      generated: 
        type: drawio:./test.drawio
        name: Drawio
        description: Generated diagram
```

In the second case a diagram file ``test.drawio`` is generated if it doesn't exist.
This file can be edited after generation using an [online editor](https://app.diagrams.net/) or a desktop application which can be downloaded from https://www.diagrams.net/.

The ``./`` prefix indicates that the diagram file location shall be resolved relative to the marker location, i.e. the location of the YAML file with representation definition.
If there is no prefix, or if there is no marker, i.g. if the definition was loaded from XMI and not from YAML, then diagram file location is resolved relative to the model resource location, 
which may be different from the location of the YAML file. 