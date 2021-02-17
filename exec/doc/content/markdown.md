Generates HTML from [Markdown](https://en.wikipedia.org/wiki/Markdown). Use ``styled-markdown`` to wrap HTML into a DIV with ``markdown-body`` class.

### Example

#### YAML specification

```yaml
markdown: |+2 
  An example of inline markdown.
  
  ### List 
  
  * Bullet 1
  * Bullet 2 
```

The above specification uses in-line markdown. Markdown source can also be loaded from [resources](resource.html) or other sources, e.g. by making an [HTTP call](http-call.html), e.g.:

```yaml
markdown: 
    resource: https://nasdanika.org/hello.md
```

#### Diagrams

[PlantUML](https://plantuml.com/) diagrams can be embedded into markdown using [fenced code blocks](https://www.markdownguide.org/extended-syntax/#fenced-code-blocks) with the following language specifications:

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

##### Examples

###### UML Sequence

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

##### Wireframe

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

```gantt
[Prototype design] lasts 15 days
[Test prototype] lasts 10 days
-- All example --
[Task 1 (1 day)] lasts 1 day
[T2 (5 days)] lasts 5 days
[T3 (1 week)] lasts 1 week
[T4 (1 week and 4 days)] lasts 1 week and 4 days
[T5 (2 weeks)] lasts 2 weeks
```

##### Mind Map

```mindmap
* Debian
** Ubuntu
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

```wbs
* Business Process Modelling WBS
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

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object markdown = loader.loadYaml(specURL, monitor);
		
Context context = Context.EMPTY_CONTEXT;
		
InputStream streamResult = callSupplier(context, monitor, markdown);
String stringResult = Util.toString(context, result);
```                
