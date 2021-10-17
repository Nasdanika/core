Creates a zip archive stream from contained [resources](../resources/index.html) such as [files](../resources/File.html) and [containers](../resources/Container.html).

### Example

#### YAML specification

```yaml
container: 
   name: zip-archive-test
   contents:
      file:
         name: my-archive.zip
         contents:
            zip-archive:
               - container:
                  name: test-container
                  contents:
                     - file:
                        name: test-file
                        contents: Hello, world!
               - file:
                  name: hello.txt
                  contents: Hello World!!!
```

This specification creates a container/folder ``zip-archive-test`` and a file ``my-archive.zip`` in it. 
The archive contains two files - ``test-container/test-file`` and ``hello.txt``.

#### Java code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(specURL, monitor);
		
File outDir = new File("target" + File.separator + "test-output");
outDir.mkdirs();
Context context = Context.EMPTY_CONTEXT;		
FileSystemContainer out = new FileSystemContainer(outDir);
callConsumer(context, monitor, container, out);
```
