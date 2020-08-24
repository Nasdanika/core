Coming soon. ${javadoc/org.nasdanika.common.SupplierFactory}


```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object resource = loader.loadYaml(TestExec.class.getResource("resource-spec.yml"), monitor);
		
Context context = Context.EMPTY_CONTEXT;		
try (Supplier<InputStream> supplier = Loader.asSupplierFactory(resource).create(context); ProgressMonitor progressMonitor = monitor.setWorkRemaining(3).split("Loading resource", 3)) {
	Diagnostic diagnostic = supplier.splitAndDiagnose(progressMonitor);
	if (diagnostic.getStatus() == Status.ERROR) {
		diagnostic.dump(System.err, 4);
		fail("Supplier diagnostic failed: " + diagnostic.getMessage());
	}
			
	try {
		InputStream result = supplier.splitAndExecute(progressMonitor);
		supplier.splitAndCommit(progressMonitor);				
		assertEquals("Hello, ${name}!", Util.toString(context, result));
	} catch (Exception e) {
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