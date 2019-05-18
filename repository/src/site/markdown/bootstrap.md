# Bootstrap

[Java API](apidocs/org.nasdanika.html.bootstrap/apidocs/index.html) to build [Bootstrap](https://getbootstrap.com/) HTML.

The entry point to the API is [BootstrapFactory](apidocs/org.nasdanika.html.bootstrap/apidocs/index.html?org/nasdanika/html/bootstrap/BootstrapFactory.html).
It can be obtained as ``BootstrapFactory factory = BootstrapFactory.INSTANCE;``.  

[BootstrapElement](apidocs/org.nasdanika.html.bootstrap/apidocs/index.html?org/nasdanika/html/bootstrap/BootstrapElement.html) is the base interface for other elements.
It has ``toHTMLElement()`` method providing access to the [HTMLElement](apidocs/org.nasdanika.html/apidocs/index.html?org/nasdanika/html/HTMLElement.html) this BootstrapElement wraps. 
In most cases the wrapped HTML element is the outer element containing all other elements, but there are a few documented special cases, e.g. an action group with content.
BootstrapElement delegates its ``toString()`` and ``produce()`` to the underlying HTML element.

``org.nasdanika.html.bootstrap`` bundle has ``resources`` folder containing Bootstrap CSS and JavaScript, [Bootswatch](https://bootswatch.com/) themes, and [jQuery](https://jquery.com/). 
These resources can be used to generate self-sufficient Web UI without dependency on CDN's. 

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
			<artifactId>org.nasdanika.html.bootstrap</artifactId>
			<version>2.0.0-SNAPSHOT</version>
		</dependency>
		...
	</dependencies>
	...
</project>
```

## Examples

The below sections provide examples of how to use Bootstrap API's.

### Action Group

```
ActionGroup actionGroup = BootstrapFactory.INSTANCE.actionGroup(false);
		
actionGroup.action(false, false, Color.DEFAULT, "#", "One");
actionGroup.action(true, false, Color.DEFAULT, "#", "Two - active");
actionGroup.action(false, true, Color.DEFAULT, "#", "Three - disabled");
actionGroup.action(false, false, Color.WARNING, "#", "Four - warning");
```
<iframe src="test-dumps/bootstrap/action-group.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = this.contentWindow.document.body.scrollHeight + 'px'"></iframe>

#### With content

```		
ActionGroup actionGroup = BootstrapFactory.INSTANCE.actionGroup(false);
		
actionGroup.contentAction("One", false, false, Color.DEFAULT, null, "First content");
actionGroup.contentAction("Two - active", true, false, Color.DEFAULT, "action-xyz", "Active content");
actionGroup.contentAction("Three - disabled", false, true, Color.DEFAULT, null, "Disabled content");
actionGroup.contentAction("Four - warning", false, false, Color.WARNING, null, BootstrapFactory.INSTANCE.alert(Color.WARNING, "Be careful!"));
```						

<iframe src="test-dumps/bootstrap/action-group-content.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = this.contentWindow.document.body.scrollHeight + 'px'"></iframe>

#### Adapt to navs 

```
ActionGroup actionGroup = BootstrapFactory.INSTANCE.actionGroup(false);
navsItems(actionGroup.asNavs()); // See "Navs" section for navsItems method code.
```

<iframe src="test-dumps/bootstrap/action-group-navs-adapter.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = this.contentWindow.document.body.scrollHeight + 'px'"></iframe>

### Alert

```
BootstrapFactory factory = BootstrapFactory.INSTANCE;
Tag alert = factory.alert(Color.INFO, "Alert");
```

<iframe src="test-dumps/bootstrap/alert.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = this.contentWindow.document.body.scrollHeight + 'px'"></iframe>

### Badge 

```
BootstrapFactory factory = BootstrapFactory.INSTANCE;
Tag badge = factory.badge(true, Color.INFO, "Badge");
Tag badgeLink = factory.badgeLink("#", false, Color.WARNING, "Badge link");
```

<iframe src="test-dumps/bootstrap/badge.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

### Breadcrumbs

```		
Breadcrumbs breadcrumbs = BootstrapFactory.INSTANCE.breadcrums();
breadcrumbs.item(false, breadcrumbs.getFactory().getHTMLFactory().link("#", "First"));
breadcrumbs.item(true, "Last");
```

<iframe src="test-dumps/bootstrap/breadcrumbs.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

### Bootstrap CDN HTML Page

```
HTMLPage bootstrapPage = factory.bootstrapCdnHTMLPage(Theme.Cerulean);
bootstrapPage.title("Bootstrap demo");
bootstrapPage.body(container);		
```

### Button

```
HTMLFactory htmlFactory = factory.getHTMLFactory();		
org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
```

<iframe src="test-dumps/bootstrap/button.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

### Button group

```		
ButtonGroup buttonGroup = factory.buttonGroup(false);
buttonGroup.add(button);
buttonGroup.add(button);
```

<iframe src="test-dumps/bootstrap/button-group.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

### Button toolbar

```		
ButtonToolbar toolbar = factory.buttonToolbar();
toolbar.add(buttonGroup);
```

<iframe src="test-dumps/bootstrap/button-toolbar.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>
	
### Card

```
Card card = BootstrapFactory.INSTANCE.card();		
card.getTitle().toHTMLElement().content("Header");
card.getBody().toHTMLElement().content("Body");
card.getFooter().toHTMLElement().content("Footer");		
```

<iframe src="test-dumps/bootstrap/card.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>
	
### Dropdown

```		
BootstrapFactory factory = BootstrapFactory.INSTANCE;
HTMLFactory htmlFactory = factory.getHTMLFactory();
org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
		
Dropdown dropdown = factory.dropdown(button, true, Direction.DOWN);
dropdown.item(htmlFactory.link("#", "Item 1"), false, false);
dropdown.header("Header");
dropdown.item(htmlFactory.link("#", "Item 2"), true, false);
dropdown.divider();
dropdown.item(htmlFactory.link("#", "Item 3"), false, true);
```

<iframe src="test-dumps/bootstrap/dropdown.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 150) + 'px'"></iframe>

### Input group
		
```		
InputGroup inputGroup = factory.inputGroup();
Input input = htmlFactory.input(InputType.text);
inputGroup.input(input);
inputGroup.prepend("@");
inputGroup.append("Something").large();
```

<iframe src="test-dumps/bootstrap/input-group.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

### Form		

```		
Form form = htmlFactory.form();
		
form.content(factory.formGroup("Email address", htmlFactory.input(InputType.email).value("email@example.com"), "We'll never share").large().plainText());
form.content(factory.formGroup("Password", htmlFactory.input(InputType.password).disabled(), null).small());
form.content(factory.formGroup("Check me out", htmlFactory.input(InputType.checkbox), null).invalid("Oh, no"));

form.content(factory.formGroup("1", htmlFactory.input(InputType.radio), null).inline());
form.content(factory.formGroup("2", htmlFactory.input(InputType.radio), null).inline());
form.content(factory.formGroup("3", htmlFactory.input(InputType.radio), null).inline());

form.content(factory.formGroup("City", htmlFactory.input(InputType.text), "City").valid());
form.content(factory.formGroup("State", htmlFactory.input(InputType.text), "State").invalid("No such state"));
```

<iframe src="test-dumps/bootstrap/form.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

### Grid, background and text style

```
Container container = factory.container();
Row row = container.row();
row.col("Col 1").border(Color.DARK).background(Color.WARNING).text().color(Color.PRIMARY);
row.col("Col 2").border(Color.PRIMARY).text().weight(Weight.BOLD).alignment(Alignment.CENTER);
row.col("Col 3").border(Color.WARNING, Placement.RIGHT).background(Color.SECONDARY).text().monospace();		
```

<iframe src="test-dumps/bootstrap/misc.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

### List Group

```		
ListGroup listGroup = BootstrapFactory.INSTANCE.listGroup(false);
		
listGroup.item(false, false, Color.DEFAULT, "One");
listGroup.item(true, false, Color.DEFAULT, "Two - active");
listGroup.item(false, true, Color.DEFAULT, "Three - disabled");
listGroup.item(false, false, Color.WARNING, "Four - warning");
```
	
<iframe src="test-dumps/bootstrap/list-group.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>
		
#### Flush

```		
ListGroup listGroup = BootstrapFactory.INSTANCE.listGroup(true);
		
listGroup.item(false, false, Color.DEFAULT, "One");
listGroup.item(true, false, Color.DEFAULT, "Two - active");
listGroup.item(false, true, Color.DEFAULT, "Three - disabled");
listGroup.item(false, false, Color.WARNING, "Four - warning");
```

<iframe src="test-dumps/bootstrap/list-group-flush.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>


### Navs

#### Simple tabs

Using ``NamedItemsContainer`` interfact methods:

```
Navs simpleTabs = BootstrapFactory.INSTANCE.tabs();
simpleTabs.item("First", "First content");
simpleTabs.item("Second", "Second content");
simpleTabs.item("Third", "Third content");
simpleTabs.item("Fourth", "Fourth content");
```

<iframe src="test-dumps/bootstrap/simple-tabs.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

#### Active and disabled tabs

Helper method:

```
private Navs navsItems(Navs navs) {
	navs.item("First", false, false, null, "First content");
	navs.item("Second", true, false, null, "Second content");
	navs.item("Third", false, true, null, "Third content");
	navs.item("Fourth", "Fourth content");
	return navs;
}
```

Tabs:

```
navsItems(BootstrapFactory.INSTANCE.tabs());
```

<iframe src="test-dumps/bootstrap/tabs.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

#### Pills

```
navsItems(BootstrapFactory.INSTANCE.pills());
```

<iframe src="test-dumps/bootstrap/pills.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

#### Vertical pills

* Use ``toHTMLElement()`` method to get access to the backing HTML element and style it.
* Output nav and content div separately.

```
Navs verticalPills = navsItems(BootstrapFactory.INSTANCE.pills());
verticalPills.toHTMLElement().addClass("flex-column");		
Container container = BootstrapFactory.INSTANCE.container();
Row row = container.row();
row.col(verticalPills.toHTMLElement()).widthAuto();
row.col(verticalPills.getContentDiv());
```

<iframe src="test-dumps/bootstrap/vertical-pills.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>


### Navbar

```
Tag brand = HTMLFactory.INSTANCE.link("#", "Nasdanika");
Navbar navbar = BootstrapFactory.INSTANCE.navbar(DeviceSize.LARGE, false, Color.LIGHT, brand);
navbar.item("#", true, false, "Item 1");
navbar.item("#", false, false, "Item 2");
navbar.item("#", false, true, "Item 3");
		
Dropdown dropdown = navbar.dropdown(false, "Dropdown 4");
dropdown.item(HTMLFactory.INSTANCE.link("#", "Item 1"), false, false);
dropdown.header("Header");
dropdown.item(HTMLFactory.INSTANCE.link("#", "Item 2"), true, false);
dropdown.divider();
dropdown.item(HTMLFactory.INSTANCE.link("#", "Item 3"), false, true);
		
navbar.navbarText("Some text");
```

<iframe src="test-dumps/bootstrap/navbar.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 150) + 'px'"></iframe>
	
### Table

``` 		
Table table = factory.table().striped();
table.toHTMLElement().caption("My table");
TableHeader header = table.header();
header.headerRow("A", "B", "C");
TableBody body = table.body();
body.row("One", "Two", "Three");		
```

<iframe src="test-dumps/bootstrap/table.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

### Tooltip

```
org.nasdanika.html.Button hButton = htmlFactory.button("Button");	
Button<org.nasdanika.html.Button> button = factory.button(hButton, Color.PRIMARY, false);
factory.tooltip(button, "I am a <I>tooltip</I>." , true, Placement.BOTTOM);
Tag initScript = factory.initTooltipScript();
```

<iframe src="test-dumps/bootstrap/tooltip.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>
