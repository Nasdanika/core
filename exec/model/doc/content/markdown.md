Markdown is a filter which renders [Markdown](https://en.wikipedia.org/wiki/Markdown) to HTML. 
It optionally styles it by enclosing into a ``<div class="markdown-body">`` recognized by [github-markdown-css](https://github.com/sindresorhus/github-markdown-css).

The filter also processes markdown which allows to embed diagrams using [fenced code blocks](https://www.markdownguide.org/extended-syntax/#fenced-code-blocks).

Markdown source can be defined in the YAML definition or loaded from an external resource.

[TOC levels=6]

## Inline Markdown

### Single line

```yaml
content-markdown:
  style: true
  source:
    content-text: Hello, *World*!
```

### Multi-line

```yaml
content-markdown:
  style: true
  source:
    content-text: |+2
      Hello, ``Universe``!
```

## Markdown loaded from resource

```yaml
content-markdown:
  style: true
  source:
    content-resource: features/execution-model.md  
```

## Embedded images

Markdown filter allows to embed PNG and JPEG using fenced blocks.

### PNG resource

	```png-resource
	my.png
	```

Resource location is resolved relative to the YAML resource containing filter definition.

### JPEG resource

	```jpeg-resource
	my.jpeg
	```

### PNG

	```png
	Base 64 encoded png 
	```
### JPEG

	```jpeg
	Base 64 encoded jpeg
	```

## Embedded diagrams

Markdown filter allows to embed [PlantUML](https://plantuml.com/) and [Draw.io](https://app.diagrams.net/) diagrams using fenced blocks.

### Draw.io

	```drawio-resource
	  aws.drawio
	```

Resource location is resolved relative to the YAML resource containing filter definition.

### PlantUML

PlantUML diagrams can be defined inline or loaded from resources.

#### Loading from a resource

	```uml-resource
	sequence.plantuml
	```

In the above snippet ``uml`` is a dialect supported by PlantUML (see below) and ``sequence.plantuml`` is a resource location resolved relative to the YAML resource containing markdown filter definition.

#### Inline 

The following language specifications (dialects) are supported:

* ``uml`` - for the following diagram types:
    * [Sequence](https://plantuml.com/sequence-diagram), 
    * [Use Case](https://plantuml.com/use-case-diagram), 
    * [Class](https://plantuml.com/class-diagram), 
    * [Activity](https://plantuml.com/activity-diagram-beta), 
    * [Component](https://plantuml.com/component-diagram), 
    * [State](https://plantuml.com/state-diagram), 
    * [Object](https://plantuml.com/object-diagram), 
    * [Deployment](https://plantuml.com/deployment-diagram), 
    * [Timing](https://plantuml.com/timing-diagram), 
    * [Network](https://plantuml.com/nwdiag).
* ``wireframe`` - for [Wireframe diagrams](https://plantuml.com/salt)
* ``gantt`` - for [Gantt diagrams](https://plantuml.com/gantt-diagram)
* ``mindmap`` - for [Mind Maps](https://plantuml.com/mindmap-diagram)
* ``wbs`` - for [Work Breakdown Structures](https://plantuml.com/wbs-diagram)

##### UML
###### Sequence

Fenced block:

	```uml
	Alice -> Bob: Authentication Request
	Bob --> Alice: Authentication Response
	```
	
Diagram:

```uml
Alice -> Bob: Authentication Request
Bob --> Alice: Authentication Response
```

###### Component

Component diagram with links to component pages.


Fenced block:

	```uml
    package Core {
       component Common [[https://docs.nasdanika.org/modules/core/modules/common/index.html]]
    }
    
    package HTML {
       component HTML as html [[https://docs.nasdanika.org/modules/html/modules/html/index.html]]
       [html] ..> [Common]
    }
	```
	
Diagram:

```uml
package Core {
   component Common [[https://docs.nasdanika.org/modules/core/modules/common/index.html]]
}

package HTML {
   component HTML as html [[https://docs.nasdanika.org/modules/html/modules/html/index.html]]
   [html] ..> [Common]
}
```

##### Wireframe

Fenced block:

	```wireframe
	{
	  Just plain text
	  [This is my button]
	  ()  Unchecked radio
	  (X) Checked radio
	  []  Unchecked box
	  [X] Checked box
	  "Enter text here   "
	  ^This is a droplist^
	}
	```


Diagram:

```wireframe
{
  Just plain text
  [This is my button]
  ()  Unchecked radio
  (X) Checked radio
  []  Unchecked box
  [X] Checked box
  "Enter text here   "
  ^This is a droplist^
}
```

##### Gantt

Fenced block:

	```gantt
	[Prototype design] lasts 15 days and links to [[https://docs.nasdanika.org/index.html]]
	[Test prototype] lasts 10 days
	-- All example --
	[Task 1 (1 day)] lasts 1 day
	[T2 (5 days)] lasts 5 days
	[T3 (1 week)] lasts 1 week
	[T4 (1 week and 4 days)] lasts 1 week and 4 days
	[T5 (2 weeks)] lasts 2 weeks
	```

Diagram:

```gantt
[Prototype design] lasts 15 days and links to [[https://docs.nasdanika.org/index.html]]
[Test prototype] lasts 10 days
-- All example --
[Task 1 (1 day)] lasts 1 day
[T2 (5 days)] lasts 5 days
[T3 (1 week)] lasts 1 week
[T4 (1 week and 4 days)] lasts 1 week and 4 days
[T5 (2 weeks)] lasts 2 weeks
```

##### Mind Map

Fenced block:

	```mindmap
	* Debian
	** [[https://ubuntu.com/ Ubuntu]]
	*** Linux Mint
	*** Kubuntu
	*** Lubuntu
	*** KDE Neon
	** LMDE
	** SolydXK
	** SteamOS
	** Raspbian with a very long name
	*** <s>Raspmbc</s> => OSMC
	*** <s>Raspyfi</s> => Volumio
	```

Diagram:

```mindmap
* Debian
** [[https://ubuntu.com/ Ubuntu]]
*** Linux Mint
*** Kubuntu
*** Lubuntu
*** KDE Neon
** LMDE
** SolydXK
** SteamOS
** Raspbian with a very long name
*** <s>Raspmbc</s> => OSMC
*** <s>Raspyfi</s> => Volumio
```

##### WBS

WBS elements can have links. This type of diagram can also be used to display organization structure.

	```wbs
	* [[https://docs.nasdanika.org/index.html Business Process Modelling WBS]]
	** Launch the project
	*** Complete Stakeholder Research
	*** Initial Implementation Plan
	** Design phase
	*** Model of AsIs Processes Completed
	**** Model of AsIs Processes Completed1
	**** Model of AsIs Processes Completed2
	*** Measure AsIs performance metrics
	*** Identify Quick Wins
	** Complete innovate phase
	```

Fenced block:


Diagram:

```wbs
* [[https://docs.nasdanika.org/index.html Business Process Modelling WBS]]
** Launch the project
*** Complete Stakeholder Research
*** Initial Implementation Plan
** Design phase
*** Model of AsIs Processes Completed
**** Model of AsIs Processes Completed1
**** Model of AsIs Processes Completed2
*** Measure AsIs performance metrics
*** Identify Quick Wins
** Complete innovate phase
```

## Extensions

* [Table of contents](https://github.com/vsch/flexmark-java/wiki/Table-of-Contents-Extension) - add ``[TOC]`` to the document as explained in the documentation. This extension will create a table of contents from markdown headers. 
* [Footnotes](https://github.com/vsch/flexmark-java/wiki/Footnotes-Extension)
* Strikethrough: ``~~strikethrough~~``-> ~~strikethrough~~ 
* Subscript: ``H~2~O`` -> H~2~0
* Superscript: ``2^5^ = 32`` -> 2^5^ = 32
