
### Examples

* ``drawio test-data/jira/diagram.drawio html-app -r test-data/jira/root-action.yml --add-to-root site -r=-1 -F test-data/jira/page-template.yml test-data/jira/docs``
    * Loads ``test-data/drawio-http/diagram.drawio`` diagram resource
    * Executes [html-app](html-app/index.html) sub-command and its [site](html-app/site/index.html) sub-command to generate a documentation site
* ``drawio test-data/drawio-http/diagram.drawio http-server --http-port=8080 processor route``
    * Loads ``test-data/drawio-http/diagram.drawio`` diagram resource
    * Executes [http-server](http-server/index.html) sub-command which serves diagram element routes at port ``8080``
* ``drawio -p my-property="My property" test-data/invocable.drawio invoke 33 66``
    * Sets property ``my-property`` to ``My property``
    * Loads ``test-data/invocable.drawio`` diagram document
    * Executes [invoke](invoke/index.html) sub-command with ``33`` and ``66`` arguments 
   
### URI Handlers

The command loads [URI Handlers](https://javadoc.io/doc/org.eclipse.emf/org.eclipse.emf.ecore/latest/org/eclipse/emf/ecore/resource/URIHandler.html) using the [capability framework](https://docs.nasdanika.org/core/capability/index.html). 
You can load resources from Maven with the [Maven URI Handler](https://docs.nasdanika.org/core/maven/index.html#uri-handler).
You can also create and register a custom URI handler, for example [GitLabURIHandler](https://javadoc.io/doc/org.nasdanika.models.gitlab/model/latest/org.nasdanika.models.gitlab/org/nasdanika/models/gitlab/util/GitLabURIHandler.html) to load diagram resources from GitLab.
Below is an example of a capability factory:

```java

import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.nasdanika.capability.emf.URIConverterContributorCapabilityFactory;
import org.nasdanika.common.ProgressMonitor;

/**
 * Contributes {@link URIHandler} loading from Maven repositories
 */
public class MavenURIHandlerCapabilityFactory extends URIConverterContributorCapabilityFactory {

    @Override
    protected void contribute(
            URIConverter uriConverter, 
            Loader loader,
            ProgressMonitor progressMonitor) {  
        uriConverter.getURIHandlers().add(0, new MavenURIHandler(loader.getCapabilityLoader(), progressMonitor));
        
    }
    
}
```

### Contributing sub-commands

To contribute a sub-command:

* Add ``@ParentCommands(Document.Supplier.class)`` annotation to your command class
* Declare a field of type ``Document.Supplier`` and annotate it with ``@ParentCommand``. You may declare a setter method instead.
* Use the field value to obtain an instance of ``Document``. 

as shown in the code snippet below:

```java
import org.nasdanika.cli.ParentCommands;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.Document;

import picocli.CommandLine.Command;
import picocli.CommandLine.ParentCommand;

@Command(...)
@ParentCommands(Document.Supplier.class)
public class MyCommand {
    
    @ParentCommand
    private Document.Supplier documentSupplier;

    public void myMethod(ProgressMonitor progressMonitor) {
        Document document = documentSupplier.getDocument(progressMonitor); 
        ...
    }       

}
```

Below is a factory:

```java
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import org.nasdanika.common.ProgressMonitor;

import picocli.CommandLine;

public class MyCommanddFactory extends SubCommandCapabilityFactory<MyCommand> {

    @Override
    protected Class<MyCommand> getCommandType() {
        return MyCommand.class;
    }
    
    @Override
    protected CompletionStage<MyCommand> doCreateCommand(
            List<CommandLine> parentPath,
            Loader loader,
            ProgressMonitor progressMonitor) {
        return CompletableFuture.completedStage(new MyCommand(loader.getCapabilityLoader()));
    }

}
```

The factory shall be registered as a [CapabilityFactory](https://javadoc.io/doc/org.nasdanika.core/capability/latest/org.nasdanika.capability/org/nasdanika/capability/CapabilityFactory.html) provider in ``module-info.java``:

```java
import org.nasdanika.capability.CapabilityFactory;

module <module name> {

    ...    
    
    opens <package with commands>; // For reflection and documentation resource loading
    
    provides CapabilityFactory with MyCommandFactory;
    
}
```

#### Executable diagrams sub-commands

You can create sub-commands which execute diagrams by extending [AbstractElementInvocableCommand](https://javadoc.io/doc/org.nasdanika.core/cli/latest/org.nasdanika.cli/org/nasdanika/cli/AbstractElementInvocableCommand.html).
[invoke](invoke/index.html) is an example of such a command. 
[General purpose executable graphs and diagrams](https://medium.com/nasdanika/general-purpose-executable-graphs-and-diagrams-8663deae5248) Medium story explains how to create diagram element processors.






