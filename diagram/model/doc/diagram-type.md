Diagram type. Supported types:

* ``drawio:<location of drawio diagram resource relative to the model resource>`` - Loads [draw.io](https://www.diagrams.net/) diagram created in a draw.io [web application](https://app.diagrams.net/) or [desktop application](https://github.com/jgraph/drawio-desktop/releases/tag/v17.2.4) (also available in Microsoft Store) and generates HTML for embedding into a web page. Diagram elements and other attributes are ignored.
* ``plantuml:<dialect>`` - Generates a [PlantUML](https://plantuml.com/) diagram for a given dialect. Supported dialects:
    * ``uml`` - sequence, use case, class, activity, component, state, object, deployment, timing, and network diagrams.
    * ``salt`` - [wireframe diagrams](https://plantuml.com/salt)
    * ``gantt`` - [Gantt charts](https://plantuml.com/gantt-diagram)
    * ``mindmap`` - [mind maps](https://plantuml.com/mindmap-diagram)
    * ``wbs`` - [work breakdown structures](https://plantuml.com/wbs-diagram)

Default type is ``plantuml:uml``.