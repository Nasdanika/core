


### Examples

#### Class path resource

#### Maven resource

#### GitLab

TODO - factory in demo, snippet here. 


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

#### Executable diagrams sub-commands



