Route serving processors shall implement ``BiFunction<? super HttpServerRequest,? super HttpServerResponse,? extends Publisher<Void>>`` to serve a single route.

Processors may also implement [HttpServerRouteBuilder](https://javadoc.io/doc/org.nasdanika.core/http/latest/org.nasdanika.telemetry/org/nasdanika/http/HttpServerRouteBuilder.html) 
or be [adaptable](https://javadoc.io/doc/org.nasdanika.core/common/latest/org.nasdanika.common/org/nasdanika/common/Adaptable.html) to it.
In this case the route property is optional and is used as a route prefix.

#### Examples

##### Java

###### Single route

[SystemHttpHandler.java](https://github.com/Nasdanika-Demos/cli/blob/main/src/main/java/org/nasdanika/launcher/demo/drawio/SystemHttpHandler.java)

```java
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import org.nasdanika.capability.CapabilityFactory.Loader;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.reactivestreams.Publisher;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;
import reactor.netty.http.server.HttpServerResponse;

/**
 * Diagram element processor which processes HTTP requests 
 */
public class SystemHttpHandler implements BiFunction<HttpServerRequest, HttpServerResponse, Publisher<Void>> {
    
    private String amount;
    
    @ProcessorElement
    public void setElement(Node element) {
        this.amount = element.getProperty("amount");
    }

    /**
     * This is the constructor signature for graph processor classes which are to be instantiated by URIInvocableCapabilityFactory (org.nasdanika.capability.factories.URIInvocableCapabilityFactory).
     * Config may be of specific types {@link ProcessorConfig} - {@link NodeProcessorConfig} or {@link ConnectionProcessorConfig}.  
     * @param loader
     * @param loaderProgressMonitor
     * @param data
     * @param fragment
     * @param config
     * @param infoProvider
     * @param endpointWiringStageConsumer
     * @param wiringProgressMonitor
     */
    public SystemHttpHandler(
            Loader loader,
            ProgressMonitor loaderProgressMonitor,
            Object data,
            String fragment,
            ProcessorConfig config,
            BiConsumer<Element, BiConsumer<ProcessorInfo<Invocable>, ProgressMonitor>> infoProvider,
            Consumer<CompletionStage<?>> endpointWiringStageConsumer,
            ProgressMonitor wiringProgressMonitor) {

    }

    @Override
    public Publisher<Void> apply(HttpServerRequest request, HttpServerResponse response) {
        return response.sendString(Mono.just("Account: " + request.param("account") + ", Amount: " + amount));
    }

}
```

###### Route builder

[RouteBuilderProcessor](https://github.com/Nasdanika-Demos/cli/blob/main/src/main/java/org/nasdanika/launcher/demo/drawio/RouteBuilderProcessor.java)

```java
import java.util.concurrent.CompletionStage;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.nasdanika.capability.CapabilityFactory.Loader;
import org.nasdanika.common.Invocable;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Node;
import org.nasdanika.graph.Element;
import org.nasdanika.graph.processor.ConnectionProcessorConfig;
import org.nasdanika.graph.processor.NodeProcessorConfig;
import org.nasdanika.graph.processor.ProcessorConfig;
import org.nasdanika.graph.processor.ProcessorElement;
import org.nasdanika.graph.processor.ProcessorInfo;
import org.nasdanika.telemetry.HttpServerRouteBuilder;

import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRoutes;

/**
 * Diagram element processor which builds HTTP routes 
 */
public class RouteBuilderProcessor implements HttpServerRouteBuilder {
    
    private String amount;
    
    @ProcessorElement
    public void setElement(Node element) {
        this.amount = element.getProperty("amount");
    }

    /**
     * This is the constructor signature for graph processor classes which are to be instantiated by URIInvocableCapabilityFactory (org.nasdanika.capability.factories.URIInvocableCapabilityFactory).
     * Config may be of specific types {@link ProcessorConfig} - {@link NodeProcessorConfig} or {@link ConnectionProcessorConfig}.  
     * @param loader
     * @param loaderProgressMonitor
     * @param data
     * @param fragment
     * @param config
     * @param infoProvider
     * @param endpointWiringStageConsumer
     * @param wiringProgressMonitor
     */
    public RouteBuilderProcessor(
            Loader loader,
            ProgressMonitor loaderProgressMonitor,
            Object data,
            String fragment,
            ProcessorConfig config,
            BiConsumer<Element, BiConsumer<ProcessorInfo<Invocable>, ProgressMonitor>> infoProvider,
            Consumer<CompletionStage<?>> endpointWiringStageConsumer,
            ProgressMonitor wiringProgressMonitor) {
        
    }

    @Override
    public void buildRoutes(HttpServerRoutes routes) {
        routes.get("/balance", (request, response) -> response.sendString(Mono.just("Account: " + request.param("account") + ", Amount: " + amount)));      
    }

}
```

##### Groovy

[person.groovy](https://github.com/Nasdanika-Demos/cli/blob/main/test-data/drawio-http/person.groovy)

```groovy
import reactor.core.publisher.Mono 
import org.nasdanika.drawio.Node
import org.nasdanika.graph.processor.ProcessorElement

//drawio test-data/drawio-http/diagram.drawio http-server --http-port=8080 processor route
new java.util.function.BiFunction() {

    @ProcessorElement
    public Node element;
    
    def apply(request, response) {
        response.sendString(Mono.just(element.getLabel()))
    }
    
}
```

