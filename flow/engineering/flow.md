With Nasdanika Flow you can model organizational processes as [flows](Flow.html) - directed graphs of [activities](Activity.html) connected by [transitions](Transition.html)
and [calls](Call.html) and performed by [participants](Participant.html) using [resources](Resource.html) and consuming and producing [artifacts](Artifact.html).
Flow extends activity and as such flows can be nested. Artifacts can also be nested, which allows to model composite artifacts such as modular and distributed systems, e.g. a cloud application.

Flow models can be defined in a set of cross-referencing [YAML](https://en.wikipedia.org/wiki/YAML) files with supporting [Markdown](../exec/modules/model/content/Markdown.html) documentation either embedded in YAML or stored in files. 
Markdown documentation supports embedding of diagrams and token interpolation/substitution. Textual definitions of flows in multiple files allow to work with them in the same way as with source code, say, Java - branch, create a pull request, merge.
In a way, Nasdanika Flow allows to write "programs" to be executed by an organization similar to how Java programs are executed by a JVM or how distributed systems, such as cloud applications, operate. 
Textual format also means that flow definitions can be edited and viewed using a wide variety of tools, including viewing and editing in a web browser using native facilities of source control systems such as GitHub.

A static web site is generated from flow models. Generated site includes generated [visualizations](features/visualizations/index.html) - [PlantUML State diagrams](https://plantuml.com/state-diagram) or [Diagrams.net](https://diagrams.net/) diagrams.
Diagrams.net diagrams can be manually edited after generation. 
Dynamic behavior can be added to generated pages using Single Page Applications, e.g. applications built with [Vue.js](https://vuejs.org/), [VueRouter](https://router.vuejs.org/), and [BootstrapVue](https://bootstrap-vue.org/).
It allows to inject fine-grained productivity tools into activities where they are used.
Such tools may use more generic tools behind the scenes and "bind" some contextual parameters to reduce participant's mental load and probability of mistakes.

Flows can [extend](features/inheritance/index.html) other flows forming an inheritance hierarchy similar to inheritance in languages such as Java or Docker images specifying base images.
This allows to define base process/flow packages and extend/tailor them to specific needs.

Nasdanika Flow also [features](features.html):

* [Responsibility Assignment Matrices](features/raci/index.html), 
* [Validation](features/validations/index.html), 
* [Data Provenance](features/data-provenance/index.html).

## Core concepts

* [Package](Package.html) - Root element of the flow model containing other elements including sub-packages. 
* [Flow](Flow.html) - A composite activity containing flow elements - *activities*, *flows*, *services*, and *pseudo-states*.
* [Activity](Activity.html) - a flow element performed by a *participant*. Has output *transitions* and may *call* other flow elements. Activities can specify input and output *artifacts*, responsibility assignments, and artifact responsibility assignments.
* [Transition](Transition.html) - when an activity completes it passes control and artifacts to subsequent activities via transitions. Fire-and-forget. Transitions may specify payload - *artifacts* which they carry from source to target.
* [Call](Call.html) - a flow element may call other flow elements as part of its execution. Calling activity blocks waiting for the called activity to complete. Request-reply. In addition to payload calls may specify response - *artifacts* returned to the source from the target, e.g. a signed certificate or connectivity parameters for a new database.
* [Participant](Participant.html) - performs activities. May contain (offer) shared activities (including flows) as services which can be referenced from flows. E.g., a technology team may offer a service "Create a cloud Environment" referenced from multiple flows. A service offered by one participant may call services offered by other participants.
* [Resource](Resource.html) - something used to complete activities. E.g. IDE or Cl/CD pipeline. Resources can be repositories of artifacts and can provide shared activities - services, which can be referenced from flows. 
* [Artifact](Artifact.html) - inputs and outputs of activities passed between activities via transitions and calls and stored in repository resources. E.g., a design document or source code. Artifacts can form a containment hierarchy and can reference template artifacts.
* [Service](Service.html) - a flow element referencing an activity provided by a participant or resource.
* [Pseudo-states](PseudoState.html) - used to combine and direct transitions. 

## Knowledge continuum

One way to think about a flow model is as of a "knowledge continuum" where knowledge of how to do things is organized in progression from generic to specific with bi-directional links between more generic and more specific pieces.

Activity documentation would be the most specific unit of knowledge, or, to be more precise, a mix of bits and pieces of knowledge from multiple domains/disciplines. 
For example, to perform a microservice development activity a developer need to know how to use IDE, source control system, an issue tracking system, etc. 
However, they don't need to know all of that at the expert level. E.g. they just need to know how to commit their code to the source control system and how to update an issue. 
They don't necessarily need to know how to do branching, merging, rebasing. 
Activity documentation may contain links to specific parts of reference manuals for resources and artifacts used by the activity, e.g. a specific chapter in a source control reference manual.
Bi-directional references between activities and participants, resources, and artifacts used in activity are automatically generated.

Resource, Artifact, and Participant documentation is more generic, but still specific to the flow package.
E.g. documentation for a build tool resource may provide a description how the tool is used in the flow and contain references to external resources - manuals, book, video courses.
Bi-directional links between participants and tools/artifacts they use in all activities are automatically generated. 
A person who is new to playing a role of a particular participant may study participant documentation including resources the participant uses and activities it participates in.

And finally, resources referenced from the flow documentation are most generic and flow-independent. 
These resources can also be categorized, if needed, from specific to generic. 
E.g. there might be documentation on how to use a particular tool within the organization and documentation on how to use the tool in general.

## Examples

* [TOGAF ADM](../../../togaf/modules/adm/index.html) 
    * [Sources](https://github.com/Nasdanika/togaf/tree/main/adm)
    * [Generated documentation](https://docs.nasdanika.org/togaf/adm/activities/adm/index.html)
* Tests
    * [Sources](https://github.com/Nasdanika/html/tree/master/flow/src/test/java/org/nasdanika/html/flow/tests)  - JUnit tests to generate HTML from models.
    * [Resources](https://github.com/Nasdanika/html/tree/master/flow/src/test/resources/org/nasdanika/html/flow/tests) - flow definitions.
  
## Process overview   

An example of generating a [web site](https://docs.nasdanika.org/togaf/adm/activities/adm/index.html) from a flow model can be found here - [TestTogafAdmGen.java](https://github.com/Nasdanika/togaf/blob/main/adm/src/test/java/org/nasdanika/togaf/adm/tests/TestTogafAdmGen.java).

Generation steps:

* Load the model from YAML or other resource. Optionally validate. Abstract and mix-in models may contain validation errors such as unresolved proxies "by design" and therefore shall not be validated upon load.
* Create an instance model. At this step you may save the instance model to XMI to logically separate processing steps. You may also enrich the model by loading data from external systems.
* Generate an action model from the instance model.
* Generate a resource model from the action model. At this step you may combine multiple action models.
* Generate a site (container) from the resource model.
  
## Maven dependency

To use the flow model add the following dependency to ``pom.xml``:

```xml
<dependency>
  <groupId>org.nasdanika.core</groupId>
  <artifactId>flow</artifactId>
  <version>2022.1.2</version>
</dependency>
```

A list of versions can be found [here](https://search.maven.org/search?q=g:org.nasdanika.core%20AND%20a:flow).