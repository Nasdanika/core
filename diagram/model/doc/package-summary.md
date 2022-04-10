Nasdanika Diagram Model provides an abstraction level to help with generating diagrams for multiple targets.
See the [generators](../gen/index.html) module for available generators and code examples.
The goal of the diagram model is to provide "cheap" visualizations with the focus or the ease of creation and maintenance (re-generation).

A picture is worth of a thousand words.
Humans are visual creatures - visuals improve learning up to 400%.
However, if a visual is too "costly" to create or maintain it might be not worth it to create it in the first place.    

Below is a quick overview of the model elements:

* [Diagram](Diagram.html) - the root of a diagram model which contains _DiagramElements_ and _Notes_.
* [DiagramElement](DiagramElement.html) - a shape/node/vertex on a diagram. Contains _Connections_ and other diagram elements. Extends _Style_ and _Link_. Has two specializations - [Start](Start.html) and [End](End.html) used for generating state diagrams.
* [Connection](Connection.html) - contained by a source diagram element and references a target diagram element. Extends _Style_.
* [Note](Note.html) - _Diagram_, _DiagramElement_, or _Connection_ note.
* [Style](Style.html) - styling for _DiagramElements_ and _Connections_. Defines generic styling attributes and allows to define generation target specific styling via properties.
* [Link](Link.html) - a link to a URL with text and tooltip. Base class for _DiagramElement_. Can also be used in _DiagramElement_ name, description, and _Note_ content.

See the [package diagram](package-summary-diagram.html) for visual representation and documentation of model elements for detailed descriptions of model classes and their features.

