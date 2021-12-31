With Nasdanika Flow you can model organizational processes as [flows](Flow.html) - directed graphs of [activities](Activity.html) connected by [transitions](Transition.html)
and [calls](Call.html) and performed by [participants](Participant.html) using [resources](Resource.html) and consuming and producing [artifacts](Artifact.html).
Flow extends activity and as such flows can be nested. Artifacts can also be nested, which allows to model composite artifacts such as modular and distributed systems, e.g. a cloud application.

Flow models are defined in a set of cross-referencing [YAML](https://en.wikipedia.org/wiki/YAML) files with supporting [Markdown](../exec/modules/model/content/Markdown.html) documentation either embedded in YAML or stored in files. 
Markdown documentation supports embedding of diagrams and token interpolation/substitution. Textual definitions of flows in multiple files allow to work with them in the same way as with source code, say, Java - branch, create a pull request, merge.
In a way, Nasdanika Flow allows to write "programs" to be executed by an organization similar to how Java programs are executed by a JVM. 
Textual format also means that flow definitions can be edited and viewed using a wide variety of tools, including viewing and editing in a web browser using native facilities of source control systems such as GitHub.

A static web site is generated from flow models. Generated site includes generated [visualizations](features/visualizations/index.html) - [PlantUML State diagrams](https://plantuml.com/state-diagram) or [Draw.io](https://app.diagrams.net/) diagrams.
Draw.io diagrams can be manually edited after generation. Dynamic behavior can be added to generated pages using Single Page Applications, e.g. applications built with [Vue.js](https://vuejs.org/), [VueRouter](https://router.vuejs.org/), and [BootstrapVue](https://bootstrap-vue.org/).
It allows to inject fine-grained productivity tools into activities where they are used. Such tools may use more generic tools behind the scenes and "bind" some contextual parameters to reduce participant's mental load and probability of mistakes.

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
* [Pseudo-states](PseudoState.html) - used combine and direct transitions. 

## Examples

* [TOGAF ADM](../../../togaf/modules/adm/index.html) 
    * [Sources](https://github.com/Nasdanika/togaf/tree/main/adm)
    * [Generated documentation](https://docs.nasdanika.org/togaf/adm/activities/adm/index.html)
* Tests
    * [Sources](https://github.com/Nasdanika/html/tree/master/flow/src/test/java/org/nasdanika/html/flow/tests)  - JUnit tests to generate HTML from models.
    * [Resources](https://github.com/Nasdanika/html/tree/master/flow/src/test/resources/org/nasdanika/html/flow/tests) - flow definitions.
  
## Process overview   

TODO:

* Load, validate
* Create instance, process, e.g. GitMarkers or data from external systems - metrics etc, save
* Generate action model
* Generate resource model
* Generate site.
  