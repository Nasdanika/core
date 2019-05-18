# HTML

[Java API](apidocs/org.nasdanika.html/apidocs/index.html) to build HTML.

The entry point to the API is [HTMLFactory](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/HTMLFactory.html).
It can be obtained as ``HTMLFactory factory = HTMLFactory.INSTANCE;``.

## HTMLElement

[HTMLElement](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/HTMLElement.html) is the base interface for other interfaces representing HTML elements. 
It has methods to set attributes, css classes and styles, and event handlers.

## Container  

[Container](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/Container.html) interface is implemented by elements which support content, e.g. 
[Tag](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/Tag.html). 

Content can be added to container by calling ``content()`` method and passing multiple elements or by calling ``accept()`` method inherited from Consumer.

## Tag  

Generic tag element. It can be created by calling one of HTMLFactory ``tag()`` methods and passing either String tag name or [TagName](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/TagName.html) as the first argument and zero or more content objects.

## Fragment  

[Fragment](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/Fragment.html) is used to group several elements together without creating
an HTML element. For example, it may be used return a group of HTML elements from a method.

## Non-empty tags and divs  

HTMLFactory ``nonEmptyTag()`` and ``nonEmptyDiv()`` methods create tags and divs which produce output only if they have content.

## Example - create an HTML Page

```
// HTML page
HTMLPage page = factory().page();
page.head(factory.getHTMLFactory().tag(TagName.meta).attribute("charset", "utf-8"));
page.head(factory.getHTMLFactory().tag(TagName.meta).attribute("name", "viewport").attribute("content", "width=device-width, initial-scale=1, shrink-to-fit=no"));
		
page.title("Demo");
page.stylesheet("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css");
page.script("https://code.jquery.com/jquery-3.3.1.slim.min.js");
page.script("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js");
page.script("https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js");
		
page.body("Hello");
```

## Stringification

[HTMLElementImpl](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/impl/HTMLElementImpl.html) has two ``stringify()`` methods to easily convert Readers, InputStreams and URL's to strings. 

Strings are returned as is, nulls are converted to an empty string. 
If an object is an instance of [Producer](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/Producer.html) then its ``produce()`` method is used for stringification.
For all other objects ``toString()`` method is used to stringify.

The below example shows how to load classloader resource:

```
System.out.println(HTMLElementImpl.stringify(getClass().getResource("test-resource.txt")));
```

HTML API uses strigification on attributes, CSS classes, and content. It allows to write compact code, e.g. to produce a script tag with script code loaded from classloader resource:

```
HTMLFactory.INSTANCE.tag(TagName.script, getClass().getResource("my-script.js"));
``` 

## Simple templating (interpolation)

HTMLFactory has several ``interpolate`` methods which replace ``{{token name}}`` entries in the input with token values obtained from [TokenSource](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/TokenSource.html), Map<String, Object> or a single key/value pair.


There is also [MutableTokenSource](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/MutableTokenSource.html] interface which can be used to accumulate tokens without having to create a map. 
MutableTokensource can be created with ``mutableTokenSource()`` factory methods.

Interpolation input is first stringified (see above) and then tokens get replaced.
The code snippet below shows how to interpolate classloader resource with a single token:

```
System.out.println(HTMLFactory.INSTANCE.interpolate(getClass().getResource("test-resource.txt"), "addressee", "world"));		
```   

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
			<id>nasdanika-html-snapshots</id>
			<name>nasdanika-html-snapshots</name>
			<url>https://www.nasdanika.org/products/html/2.0.0-SNAPSHOT/master/maven-repository</url>
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
			<groupId>org.nasdanika.html</groupId>
			<artifactId>org.nasdanika.html</artifactId>
			<version>2.0.0-SNAPSHOT</version>
		</dependency>
		...
	</dependencies>
	...
</project>
```