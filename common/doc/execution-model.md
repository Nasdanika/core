This page explains the Nasdanika execution model, which is used by a number of Nasdanika products and modules such as Vinci, Codegen, and Exec.

The execution model contains the following steps:

* Create a participant ${javadoc/org.nasdanika.commmon.ExecutionParticipantFactory factory}. Factories can be composed as explained in the subsequent sections.
* Create an instance of ${javadoc/org.nasdanika.commmon.Context}.
* Pass the context to the factory ``create()`` method to create a participant.
* Create an instance of ${javadoc/org.nasdanika.commmon.ProgressMonitor}.
* Walk the participant through the lifecycle methods. All lifecycle methods except ``execute()`` have the same signature for all paricipant types. 
    * ``diagnose()`` - performs diagnostic of the participant configuration and returns ${javadoc/org.nasdanika.common.Diagnostic}. If the diagnostic status is ``ERROR`` further execution is aborted. Diagnose may prepare the participant for further execution by initializing internal structures, e.g. resolving URL's. However, this method shall not perform any modifications in resources which might have to be rolled back, e.g. it shall not create or delete files or database records. The default implementation returns status ``SUCCESS``.
    * ``execute()`` - Executes participant's logic. May modify resources, e.g. write to files, create database records. Different participant types have different signatures of this method. There is no default implementation for this method.
    * ``commit()`` - Commits changes performed by ``execute()`` if all execution participants were executed successfully, i.e. without throwing an exception. The default implementation does nothing.
    * ``rollback()`` - Rolls back changes performed by ``execute()`` if some other participant executed after this one has thrown an exception. Returns boolean value indicating whether rollback was successful or not. The default implementation does nothing and returns ``true``.
    * ``close()`` - Releases any resources used during execution. E.g. closes database connections, shuts down executor pulls. Overrides ``close()`` in ${javadoc/java.lang.AutoCloseable} so participants can be used in [try-with-resources](https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html). The default implementation does nothing.  
    
### Execution participant types

* ${javadoc/org.nasdanika.common.Supplier}<T> - ``T execute(ProgressMonitor)`` - returns value.      
* ${javadoc/org.nasdanika.common.Function}<T,R> - ``R execute(T,ProgressMonitor)`` - takes an argument and returns value.     
* ${javadoc/org.nasdanika.common.Consumer}<T> - ``void execute(T,ProgressMonitor)`` - takes an argument, does not return value.      
* ${javadoc/org.nasdanika.common.Command} -  - ``void execute(ProgressMonitor)``      

### Composition

Execution participants can be composed using subclasses of ${javadoc/org.nasdanika.common.CompoundExecutionParticipant}.
Result producing participants - suppliers and functions - can be composed as lists or maps.
Non-result producing participants - consumers and commands - are always composed as lists. 

### Chaining

Result producing participants (supplier and function) and their factories have ``then`` method which takes value-consuming participant (function or consumer) and returns a participant (or a factory for such a participant) which executes the first participant, takes the returned value and passes it to the second participant. 

The participants can be chained as follows:

* Supplier<T>.then(Function<? super T,R>) -> Supplier<R>
* Supplier<T>.then(Consumer<? super T>) -> Command
* Function<T,R>.then(Function<? super R,V>) -> Function<T,V>
* Function<T,R>.then(Consumer<? super R>) -> Consumer<T>

#### asFunction

Suppler and Consumer have ``asFunction`` methods and their factories have ``asFunctionFactory`` methods to allow chaining of supplier and consumer execution.

For suppliers ``asFunction`` method returns ${javadoc/org.nasdanika.common.BiSupplier} with the function argument available via ``getFirst()`` method and the supplier result via ``getSecond()``.
For consumers ``asFunction`` creates a pass-through function which executes the consumer and then returns its argument.

### Contextification

Contextification is a form of chaining of execution participant factories where the first factory is a SupplierFactory<Context>. Context returned by the first factory's supplier is passed to the execution participant created by the second factory. 
Contextification is done using ``contextify()`` method of execution participant factories.

### Bridging to java.util.function

Execution participants have static ``fromXXX`` methods for constructing from objects implementing ``java.util.function`` interfaces, e.g. ${javadoc/java.util.function.Function}.  

### Client code

Client code shall walk the top-level execution participant through the lifecycle methods. Composition and chaining classes methods take care of invoking lifecycle methods of their constituents.

The below code snippet shows how to call Supplier<InputStream>: 

```java
Context context = ...;		
SupplierFactory<InputStream> supplierFactory = ...;
ProgressMonitor monitor = ...;

try (Supplier<InputStream> supplier = supplierFactory.create(context); ProgressMonitor progressMonitor = monitor.split("Calling " + supplier.name(), 3 * supplier.size())) {
	Diagnostic diagnostic = supplier.splitAndDiagnose(progressMonitor);
	if (diagnostic.getStatus() == Status.ERROR) {
		// Handle diagnostic error - dump to console or log, throw an exception or return error code or status.
		diagnostic.dump(System.err, 4);
		// Calling JUnit.fail() here
		fail("Supplier diagnostic failed: " + diagnostic.getMessage());
	}
			
	try {
		InputStream result = supplier.splitAndExecute(progressMonitor);
		// process result here
		...
		supplier.splitAndCommit(progressMonitor);						
	} catch (Exception e) {
		// Rollback and handle exception. 
		// In some cases diagnostic exception may be thrown during execution.
		if (e instanceof DiagnosticException) {
			((DiagnosticException) e).getDiagnostic().dump(System.err, 4);
		}
		if (supplier.splitAndRollback(progressMonitor)) {
			fail("Exception " + e + ", rollback successful");
		} else {
			fail("Exception " + e + ", rollback failed");						
		}
	}
}

``` 