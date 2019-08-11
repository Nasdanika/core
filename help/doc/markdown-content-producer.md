# Markdown content producer

Markdown conent producer processes requests for help resources ending with ``.md.html`` (e.g. ``overview.md.html``) by finding a corresponding markdown resources without ``.html``
suffix (e.g. ``overview.md``) and converting them to HTML. 

The producer shall be registered with the help system as shown below:

```xml
<extension
      id="... ID here ..."
      name="Markdown Content Producer"
      point="org.eclipse.help.contentProducer">
   <contentProducer
         producer="org.nasdanika.help.markdown.MarkdownContentProducer">
   </contentProducer>
</extension>
``` 

The producer is already registered by ``org.nasdanika.help`` bundle and can be referenced from another bundle as shown below:

```xml
<extension
      point="org.eclipse.help.contentProducer">
   <binding
         producerId="org.nasdanika.help.MarkdownContentProducer">
   </binding>
</extension>
``` 

## Markdown pre-processor extension point

The content producer supports a markdown pre-processor extension point. 
Extensions contributing to this point shall implement ``org.nasdanika.help.markdown.MarkdownPreProcessor`` interface.

Pre-processors are ignored in [fenced code blocks](https://help.github.com/en/articles/creating-and-highlighting-code-blocks). 

### PlantUML pre-processor

``org.nasdanika.help`` bundle provides [PlantUML](http://plantuml.com) pre-processor which replaces diagram definitions between ``@startuml`` and ``@enduml`` tokens with diagram images. The opening tag shall be preceded by at least on blank line and the closing tag shall be followed by at least one blank line. 

This pre-processor requires [Graphviz](https://www.graphviz.org/) installation.

#### Example

##### Definition
```
@startuml
	hide footbox
	Alice -> Bob
@enduml
```	 

##### Diagram

@startuml
	hide footbox
	Alice -> Bob
@enduml

