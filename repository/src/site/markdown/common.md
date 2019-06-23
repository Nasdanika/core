# Common

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