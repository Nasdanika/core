Java components are specializations of resource and content components facilitating generation of Java sources.

### Example

This example demonstrates use of several Java components.

### YAML specification

```yaml
container:
   name: java-project
   contents:
      - source-folder:
         name: src
         contents:
            - package:
               name: org.nasdanika.test
               contents:
                  - compilation-unit:
                     name: MyClass
                     contents:
                        interpolator:
                           resource: java-template.txt
                  - compilation-unit:
                     name: FineGrainedClass
                     contents:
                        - class:
                           name: FineGrainedClass
                           modifiers: public
                           super-types:
                              - java.lang.Object
                              - '${import/org.nasdanika.common.SupplierFactory}<${import/java.io.InputStream}>'
                           body: 
                              - field:
                                 name: myField
                                 modifiers:
                                    - private
                                 comment: My rather important field
                                 type: '${import/java.util.Collection}<${import/java.lang.String}>'
                                 body: 'new ${import/java.util.ArrayList}<>()'
```

### java-template.txt

```
public class MyClass implements ${import/org.nasdanika.common.SupplierFactory}<${import/java.io.InputStream}> {

}
```

### Generation code

```java
ObjectLoader loader = new Loader();
ProgressMonitor monitor = new PrintStreamProgressMonitor(System.out, 0, 4, false);
Object container = loader.loadYaml(TestExec.class.getResource("java-spec.yml"), monitor);
assertEquals(Container.class, container.getClass());
		
File outDir = new File("target" + File.separator + "test-output");
outDir.mkdirs();
Context context = Context.EMPTY_CONTEXT;		
FileSystemContainer out = new FileSystemContainer(outDir);
callConsumer(context, monitor, container, out);		
```

### Generation results

#### MyClass

```java
package org.nasdanika.test;

import java.io.InputStream;
import org.nasdanika.common.SupplierFactory;

public class MyClass implements SupplierFactory<InputStream> {

}
```

#### FineGrainedClass

```java
package org.nasdanika.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import org.nasdanika.common.SupplierFactory;

/**
 * @generated
 */
public class FineGrainedClass implements SupplierFactory<InputStream> {

	/**
	 * My rather important field
	 * @generated
	 */
	private Collection<String> myField = new ArrayList<>();
}
```
