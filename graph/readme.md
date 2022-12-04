Nasdanika Graph module provides classes for visiting and processing [graphs](https://en.wikipedia.org/wiki/Graph_(discrete_mathematics)) with two types of relationships between graph elements:

* Containment - one element is contained by another
* Connection - one element (node) connecting to another node via a connection.

On the diagram below containment relationships are shown in bold black and connections in blue

```drawio-resource
./overview.drawio
```

Examples of such graphs:

* A file systems with directories containing files and other directories. Connections may take multiple forms such as symbolic links or files, e.g. HTML files, referencing other files.
* Organizational structure with a hierarchy of organizational units and connections between them. For example, one unit may pass work product to another unit, or a unit may provide services to other units.
* Country, state, county, city, street, house, people living in that house; family relationships between people and ownership relationships between people and houses.
* Diagrams, such as [Drawio](https://www.diagrams.net/) diagrams with a diagram file (resource) containing a ${javadoc/org.nasdanika.drawio.Document document} which contains ${javadoc/org.nasdanika.drawio.Page pages}, pages containing ${javadoc/org.nasdanika.drawio.Layer layers}, and layers containing ${javadoc/org.nasdanika.drawio.Node nodes} and ${javadoc/org.nasdanika.drawio.Connection connections}. Nodes may be nested. [Nasdanika Drawio](../drawio/index.html) is a module for working with Drawio diagrams. It is built on top of this module.
* Processes/(work)flows - processes consist of activities and nested processes. Activities are connected by transitions. [Nasdanika Flow](../flow/index.html) is an example of such process/flow.
* Distributed systems, such as cloud solutions - availability zones, data centers, clusters, nodes, pods, containers, processes inside containers. All of them communicating to each other via network connections.
* Work hierarchy and dependencies - in issue trackers issues may be organized into a hierarchy (e.g. Initiative, Epic, Story, Sub-Task in Jira) and have different types of dependencies.
* In Java a jar contains packages containing sub-packages and classes. Classes contain fields and methods. Fields reference their types, methods call methods of other classes, ...
* [EMF Ecore](https://www.eclipse.org/modeling/emf/) models contain packages. Packages contain sub-packages and classifiers including classes. Classes contain references to other classes. References may be configured as containment (composition) or non-containment.

---

[TOC levels=6]

## Graph API

The graph API has 3 interfaces:

* ${javadoc/org.nasdanika.graph.Element Element} - super-interface for Connection and Node below. Elements may contain other elements. Containment is implemented with ``<T> T accept(BiFunction<? super Element, Map<? extends Element, T>, T> visitor)``, which can be thought of as a hierarchical bottom-up [reduce](https://docs.oracle.com/javase/tutorial/collections/streams/reduction.html) - the visitor function is invoked with an element being visited as its first argument and a map of element's children to results returned by the visitor as the second argument. For leaf elements the second argument may be either an empty map or null. Depending on the map type used by implementations they may also need to implement ``equals()`` and ``hashCode()``.
* ${javadoc/org.nasdanika.graph.Node Node} extends Element and may have incoming and outgoing connections.
* ${javadoc/org.nasdanika.graph.Connection Connection} extends Element and has source and target nodes.


## Processing

Graph processing means associating some behavior with graph elements. 
That behavior (code execution) may modify the graph or perform other actions.

Examples of graph processing:

* Generate code from a diagram. [Nasdanika Application Model Drawio](../../../html/modules/models/modules/app/modules/drawio/index.html) module generates HTML sites from Drawio diagrams. Demos:
    * [Actions](demos/modules/app-drawio/modules/actions/index.html)
    * [Flow](demos/modules/app-drawio/modules/flow-actions/index.html)
    * [Mind Map](demos/modules/app-drawio/modules/map/index.html)
* Update a diagram with information from external source. For example, there might be a diagram of a (software) system. Diagram elements can be updated as follows:
    * During development - colors may reflect completion status. Say, in progress elements in blue, completed elements in green, elements with issues in red or amber.
    * In production - color elements based on their monitoring status. Offline - grey, good - green, overloaded - amber, broken - red.
    
The above two examples may be combined - a documentation site might be generated from a system diagram.
The diagram may be updated with statuses as part of the generation process and embedded to the home page.
A click on a diagram element would navigate to an element documentation page, which may contain detailed status information pulled from tracking/monitoring systems during generation.          
    
### Dispatching

One form of graph processing is dispatching of graph elements to Java methods annotated with ${javadoc/org.nasdanika.graph.Handler Handler} annotation.
The annotation takes a [Spring boolean expression](https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions). 
Graph elements are passed to methods for which the expression is blank or evaluates to true.

Below is a code snippet from [AliceBobHandlers](https://github.com/Nasdanika/core/blob/master/drawio/src/test/java/org/nasdanika/drawio/tests/AliceBobHandlers.java) class:

```java
@Handler("getProperty('my-property') == 'xyz'")
public String bob(Node bob) {
	System.out.println(bob.getLabel());
	return bob.getLabel();
}
```	

Below is a test method from [TestDrawio.testDispatch()](https://github.com/Nasdanika/core/blob/master/drawio/src/test/java/org/nasdanika/drawio/tests/TestDrawio.java#L630) test method which dispatches to the above handler method:

```java
Document document = Document.load(getClass().getResource("alice-bob.drawio"));
		
AliceBobHandlers aliceBobHandlers = new AliceBobHandlers();		
Object result = document.dispatch(aliceBobHandlers);
System.out.println(result);
```

Dispatching is suitable for processing where processing logic for different graph elements does not need to access processing logic of other elements.
An example of such logic would be updating diagram elements based on statuses retrieved from tracking/monitoring systems - each element is updated individually.

### Processors and processor factories

${javadoc/org.nasdanika.graph.processor} package provides means for creating graph element processors and wiring them together so they can interact.

One area of where such functionality would be needed is executable diagrams. For example, a [flow](https://docs.nasdanika.org/demo-drawio-flow-actions/) processor/simulator.
Activity processors would need to pass control to connected activities via connection processors.
Activity processors may also need to access facilities of their parent processors.

The below diagram shows interaction of two nodes via a connection.
Connections are bi-directional - source processor may interact with the target processor and vice versa.

```drawio-resource
./processing.drawio
```

Some connections may be "pass-through" - just passing interactions without doing any processing. 
A pass-through connection is depicted below.


```drawio-resource
./pass-through-processing.drawio
```

Graph element processors are wired together with handlers and endpoints:

* A handler is a java object created by the client code and receiving invocations from other processors or client code via endpoints.
* An endpoint is a java object provided to the client code for interacting with other processors.

An endpoint may be of the same type as a handler or a handler may be used as an endpoint. 
This might be the case if processing is performed sequentially in a single JVM.

Alternatively, an endpoint may be of different type than the handler it passes invocations to. 
For example:

* Endpoint methods may return ${javadoc/java.util.concurrent.Future Futures} or ${javadoc/java.util.concurrent.CompletableFuture Completable Futures} of counterpart handler methods - when an endpoint method is invoked it would invoke handler's method asynchronously. 
* Endpoint methods may take different parameters. E.g. an endpoint method can take ${javadoc/java.io.InputStream InputStream}, save it to some storage and pass a URL to the handler method.

Processors can also interact by looking up other processors in the processor registry as explained below.

Processors, handlers, and endpoints are created and wired by implementations of ${javadoc/org.nasdanika.graph.processor.ProcessorFactory ProcessorFactory} which should implement the following methods:

* ``createEndpoint()`` - creates an endpoint for a given connection, handler and handler type. ${javadoc/org.nasdanika.graph.processor.NopEndpointProcessorFactory NopEndpointProcessorFactory} provides a default implementation of this method which simply returns the handler.
* ``createProcessor()`` method. This method has a default implementation which does nothing - it simply returns ${javadoc/org.nasdanika.graph.processor.ProcessorInfo ProcessorInfo} with ``null`` processor. The purpose of this default implementation is to provide access to graph element's ${javadoc/org.nasdanika.graph.processor.ProcessorConfig ProcessorConfig} (or its subtypes ${javadoc/org.nasdanika.graph.processor.ConnectionProcessorConfig ConnectionProcessorConfig} or ${javadoc/org.nasdanika.graph.processor.NodeProcessorConfig NodeProcessorConfig} depending on the element type) to the client code. The client code can use the config to wire handlers and to call endpoints. It is similar to a [printed circuit board](https://en.wikipedia.org/wiki/Printed_circuit_board) with a [CPU socket](https://en.wikipedia.org/wiki/CPU_socket) - the board provides wiring and the user inserts a CPU into the socket. ``parentProcessorInfoCallbackConsumer`` parameters provides a mechanism to get notified when element's parent processor is created. Processors are created bottom-up and child processors are created before parent processors. ``registryCallbackConsumer`` provides a mechanism to get notified when all processors have been created.
* ``isPassThrough()`` returns ``true`` by default meaning that connections do not perform any processing - they just connect nodes. 


Client code creates processors by calling one of ``createProcessors`` methods. 
These methods return a registry - ``Map<Element,ProcessorInfo<P>>``. 
The registry allows the client code to interact with the handler/endpoint/processor wiring created from the graph.    

[TestDrawio.testProcessor()](https://github.com/Nasdanika/core/blob/master/drawio/src/test/java/org/nasdanika/drawio/tests/TestDrawio.java#L380) method provides an example of using an anonymous implementation of ``NopEndpointProcessorFactory`` for graph processing.

#### Reflective 

A good deal of graph processing is matching graph elements to code to be invoked for processing of that elements. 
It may be quite tedious for large graphs.

${javadoc/org.nasdanika.graph.processor.ReflectiveProcessorFactory ReflectiveProcessorFactory} uses annotations with [Spring expressions](https://docs.spring.io/spring-framework/docs/5.3.22/reference/html/core.html#expressions) to create processors and handlers and inject endpoints as explained below.

${javadoc/org.nasdanika.graph.processor.NopEndpointReflectiveProcessorFactory NopEndpointReflectiveProcessorFactory} extends  ``ReflectiveProcessorFactory`` and implements ``NopEndpointProcessorFactory`` providing default implementations for ``createEndpoint()`` method.

``ReflectiveProcessorFactory`` constructor takes an vararg array of targets - objects with methods and fields annotated with:

* ${javadoc/org.nasdanika.graph.processor.Processor Processor} - annotation for a method creating an instance of processor which is then introspected to create handlers and inject/wire endpoints, parent, and registry.
* ${javadoc/org.nasdanika.graph.processor.Factory Factory} - field, method, or type annotation. Allows to cascade/group targets
* ${javadoc/org.nasdanika.graph.processor.Factories Factories} - field or method annotation which also allows to cascade/group targets

Below is an example of a method annotated with ``Processor`` annotation:

```java
@Processor("label == 'Bob'")
public BobProcessor createBobProcessor(NodeProcessorConfig<Object, Function<String,String>, Function<String,String>> config) {
	return new BobProcessor();
}
```
 
Objects returned from methods annotated with ``Processor`` are introspected for the following annotations:

* All processors:
    * ${javadoc/org.nasdanika.graph.processor.ChildProcessor ChildProcessor} - field a method to inject processor or config of element's child matching the selector expression.
    * ${javadoc/org.nasdanika.graph.processor.ChildProcessors ChildProcessors} - field or method to inject a map of children elements to their processor info.
    * ${javadoc/org.nasdanika.graph.processor.ParentProcessor ParentProcessor} - field or method to inject processor or config of element's parent.
    * ${javadoc/org.nasdanika.graph.processor.ProcessorElement ProcessorElement} - field or method to inject the graph element.
    * ${javadoc/org.nasdanika.graph.processor.Registry Registry} - field or method to inject the registry - a map of graph elements to their info.
    * ${javadoc/org.nasdanika.graph.processor.RegistryEntry RegistryEntry} - field or method to inject a matching registry entry.
* Node processors:
    * ${javadoc/org.nasdanika.graph.processor.IncomingEndpoint IncomingEndpoint} - field or method to inject a matching incoming endpoint.
    * ${javadoc/org.nasdanika.graph.processor.IncomingEndpoints IncomingEndpoints} - field or method to inject a map of incoming connections to their endpoints completion stages.
    * ${javadoc/org.nasdanika.graph.processor.IncomingHandler IncomingHandler} - field or method to obtain a handler for an incoming connection.
    * ${javadoc/org.nasdanika.graph.processor.IncomingHandlerConsumers IncomingHandlerConsumers} - field or method to inject a map of incoming connections to ${javadoc/java.util.function.Consumer consumers} of handlers. 
    * ${javadoc/org.nasdanika.graph.processor.OutgoingEndpoint OutgoingEndpoint} - field or method to inject a matching outgoing endpoint.
    * ${javadoc/org.nasdanika.graph.processor.OutgoingEndpoints OutgoingEndpoints} - field or method to inject a map of outgoing connections to their endpoints completion stages.
    * ${javadoc/org.nasdanika.graph.processor.OutgoingHandler OutgoingHandler} - field or method to obtain a handler for an outgoing connection.
    * ${javadoc/org.nasdanika.graph.processor.OutgoingHandlerConsumers OutgoingHandlerConsumers} - field or method to inject a map of outgoing connections to consumers of handlers.     
* Connection processors:
    * ${javadoc/org.nasdanika.graph.processor.SourceEndpoint SourceEndpoint} - field or method into which a connection source endpoint is injected.  Source endpoint allows the connection processor to interact with the connection source handler.
    * ${javadoc/org.nasdanika.graph.processor.SourceHandler SourceHandler} - field or method from which the connection source handler is obtained.
    * ${javadoc/org.nasdanika.graph.processor.TargetEndpoint TargetEndpoint} - field or method into which a connection target endpoint is injected. Target endpoint allows the connection processor to interact with the connection target handler.
    * ${javadoc/org.nasdanika.graph.processor.TargetHandler TargetHandler} - Field or method from which the connection target handler is obtained.

Below is an example of a node processor:

```java
public class AliceProcessor extends BobHouseProcessor {
	
	@ProcessorElement
	private Node aliceNode;
	
	@OutgoingHandler("target.label == 'Bob'")
	private Function<String,String> replyToBob = request -> {
		return request + System.lineSeparator() + "[" + aliceNode.getLabel() + "] My name is " + aliceNode.getLabel() + ".";
	};
	
	@OutgoingEndpoint("target.label == 'Bob'")
	private Function<String,String> bobEndpoint;
	
	public String talkToBob(String str) {
		return bobEndpoint.apply("[" + aliceNode.getLabel() + "] Hello!");
	}	

}
```       

Below is an example of a connection processor:

```java
public class AliceBobConnectionProcessor {
	
	@SourceEndpoint
	Function<String,String> sourceEndpoint;
	
	@TargetEndpoint
	Function<String,String> targetEndpoint;
	
	@SourceHandler
	Function<String,String> sourceHandler = request -> ">> " + targetEndpoint.apply(request);
	
	@TargetHandler
	Function<String,String> targetHandler = response -> "<< " + sourceEndpoint.apply(response);	
	
}
```

## Semantic mapping

${javadoc/org.nasdanika.graph.processor.GraphProcessorResource GraphProcessorResource} is a base class for mapping graph elements to 
[EMF](https://www.eclipse.org/modeling/emf/) Ecore model elements. 
Nasdanika Application Model Drawio is an example of such semantic mapping - it maps elements of Drawio diagrams to actions of [Nasdanika Application Model](../../../html/modules/models/modules/app/modules/model/index.html) which allows to generate HTML sites from diagrams.

${javadoc/org.nasdanika.graph.processor.emf.AbstractEObjectFactory} is a base class for mapping of graph elements to ${javadoc/org.eclipse.emf.ecore.EObject}'s.
Concrete implementations of this class can be used in combination with concrete implementations of ``GraphProcessorResource``. 

${javadoc/org.nasdanika.drawio.DrawioEObjectFactory} is a specialization of ``AbstractEObjectFactory`` for Drawio diagrams, see [Drawio](../drawio/index.html) for more details.
${javadoc/org.nasdanika.emf.persistence.ObjectLoaderDrawioEObjectFactory} is a further specialization of ``DrawioEObjectFactory``. 
${javadoc/org.nasdanika.emf.persistence.ObjectLoaderDrawioResourceFactory} leverages ``ObjectLoaderLoadingDrawioEObjectFactory`` for loading models from Drawio diagrams with YAML or JSON specifications, see [EMF](../emf/index.html) for more details.

There might be multiple processors and semantic models for the same graph, e.g. a diagram. TODO - many to many ...
It can be thought of as "semantic inversion" - in [UML](https://en.wikipedia.org/wiki/Unified_Modeling_Language) and tools like [Sirius](https://www.eclipse.org/sirius/) there is a model and multiple representations/views of the model. 
Visual (view) elements are mapped to model elements. 

In the case of graph processing and semantic mapping it is the opposite - semantic elements are mapped to visual elements and there might be multiple semantic elements in different models mapping to the same visual element.

An example of such mapping might be a map of United States with a a hierarchy of states and counties.
Map elements can be mapped to different semantic models - weather, population, election results.

Another example is a diagram of a software system where diagram elements can be mapped to:

* Action model (see above) to generate documentation.
* Issues in an issue tracker like Jira to visually depict progress in constructing the system.
* Diagram elements can be mapped to code generators so parts of the system can be generated. This can be used in software product lines where multiple similar solutions are created following the same pattern. The pattern can be captured and documented using diagrams.
* Once the system is build diagram elements can be mapped to build/deployment processes - execution of the diagram would result in deploying a solution. The diagram may be updated with deployment details, e.g. [ARN](https://docs.aws.amazon.com/general/latest/gr/aws-arns-and-namespaces.html)'s for [AWS](https://aws.amazon.com/) solutions.
* Once the system is built diagram elements can be mapped to monitoring models to show how the system operates. At this step deployment details injected into the diagram can be used to pull runtime information.

With semantic mapping a diagram does not have to comply to a specific notation as it is the case with UML or Sirius diagrams. 
Meaning is assigned to diagram elements by semantic mapping, i.e. the notation may be created after the diagram.
It can be beneficial when there is no notation for the problem domain at hand, the notation is too complex or people authoring diagrams are not familiar with the notation, but they know how to express what they know or need as a diagram.

Semantic mapping approach can be used to elicit and codify organizational tribal knowledge - the "secret sauce" of an organization.
An organization may start with pre-existing diagrams and map them actions to generate documentation sites.
Diagrams similar to [this one](https://docs.nasdanika.org/demo-drawio-actions/) can be used to document software systems.
[Flow diagrams](https://docs.nasdanika.org/demo-drawio-flow-actions/) can be used to document processes. 
The diagrams can be interrelated. For example, documentation of some software component may contain flow diagrams instructing how to perform operations on the component, e.g. deployment.   

The organization may also map diagrams to different models. E.g. to the [Nasdanika Flow](../flow/index.html) model for processes. 
Or the organization may create an Ecore model of the organization and map diagrams and other data sources to the model.
Such a model can be documented using [Nasdanika HTML Ecore](../../../html/modules/ecore/index.html).
The documentation may include instructions how to map diagram elements to model elements.


