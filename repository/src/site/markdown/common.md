# Common

[org.nasdanika.common](apidocs/org.nasdanika.emf/apidocs/index.html?org/nasdanika/common/package-summary.html) provides classes leveraged by many other bundles/products. In particular:

* [Context](apidocs/org.nasdanika.emf/apidocs/index.html?org/nasdanika/common/Context.html) and its flavors to pass contextual information along the execution path.
* [Converter](apidocs/org.nasdanika.emf/apidocs/index.html?org/nasdanika/common/Converter.html) and its implementations to conveniently perform conversions between different types.
* [Resource](apidocs/org.nasdanika.emf/apidocs/index.html?org/nasdanika/common/resources/Resource.html) and its flavors to simplify working with a hierarchy of resources by abstracting lower-level API's, e.g. ``java.io.File``. Support of ``ZipInputStream`` and ``ZipFile`` containers will be added in upcoming releases.

## Use in Maven projects

Add repository and dependency as shown below:

```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	...	
	<repositories>
		...
		<repository>
			<id>nasdanika-core-snapshots</id>
			<name>nasdanika-core-snapshots</name>
			<url>https://www.nasdanika.org/home/products/core/maven-repository</url>
			<snapshots>
				<enabled>true</enabled>
			</snapshots>
			<layout>default</layout>
		</repository>
		...
	</repositories>	
	...		
	<dependencies>
		...		
		<dependency>
			<groupId>org.nasdanika.core</groupId>
			<artifactId>org.nasdanika.common</artifactId>
			<version>1.0.0-SNAPSHOT</version>
		</dependency>
		...
	</dependencies>
	...
</project>
```