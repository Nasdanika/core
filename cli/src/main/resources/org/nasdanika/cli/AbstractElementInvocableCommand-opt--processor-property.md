##### Examples

###### Groovy

Property value: ``invocable-person.groovy#my-fragment``

[Processor code](https://github.com/Nasdanika-Demos/cli/blob/main/test-data/invocable-person.groovy):

```groovy
import java.util.concurrent.CompletionStage
import java.util.function.BiConsumer
import java.util.function.Consumer
import java.util.function.Supplier

import org.nasdanika.capability.CapabilityFactory.Loader
import org.nasdanika.common.Invocable
import org.nasdanika.common.ProgressMonitor
import org.nasdanika.drawio.Node
import org.nasdanika.graph.Element
import org.nasdanika.graph.processor.OutgoingEndpoint
import org.nasdanika.graph.processor.ProcessorConfig
import org.nasdanika.graph.processor.ProcessorElement
import org.nasdanika.graph.processor.ProcessorInfo

// Script arguments for reference
Loader loader = args[0];
ProgressMonitor loaderProgressMonitor = args[1];
Object data = args[2]; // From fragment
ProcessorConfig config = args[3];
BiConsumer<Element, BiConsumer<ProcessorInfo<Invocable>, ProgressMonitor>> infoProvider = args[4];
Consumer<CompletionStage<?>> endpointWiringStageConsumer = args[5];
ProgressMonitor wiringProgressMonitor = args[6];

new org.nasdanika.common.Invocable() {
    
    /**
     * Diagram element is injected into this field
     */
    @ProcessorElement
    public Node element;
    
    private amountSupplier
    
    @OutgoingEndpoint
    public void setAmountSupplier(Supplier<String> amountSupplier) {
        System.out.println("Amount supplier " + amountSupplier);
        this.amountSupplier = amountSupplier;
    }   
                
    /**
     * This method is invoked by the dynamic proxy's Invocable.invoke() method. 
     * The first argument is the proxy object - it can be used to resolve state or call other methods of the proxy.
     */
    def invoke(Object... args) {
        System.out.println(args);
        System.out.println(element.getProperty("greeting") + " I have " + amountSupplier.get() + " dollars in my bank account. I'm so happy!!! " + args);
        args.length; // Just to return something  
    }
    
}
```