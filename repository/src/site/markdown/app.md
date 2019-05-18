# Application

``org.nasdanika.html.app`` bundle/library provides a level of abstraction on top of [HTML](html.html), [Bootstrap](bootstrap.html), [JsTree](jstree.html), [Font Awesome](fontawesome.html), and [KnockoutJS](knockout.html) bundles.
This page outlines core concepts and shows usage examples. See [Java API](apidocs/org.nasdanika.html.app/apidocs/index.html) for additional details.

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
			<artifactId>org.nasdanika.html.app</artifactId>
			<version>2.0.0-SNAPSHOT</version>
		</dependency>
		...
	</dependencies>
	...
</project>
```

## Application

[Application](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Application.html) interface is an abstraction of a web application consisting of the following parts:

* Header
* Navigation bar
* Navigation panel
* Content panel
* Footer

There are two implementations of this interface:

* [BootstrapContainerApplication](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/BootstrapContainerApplication.html).
* [HTMLTableApplication](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/HTMLTableApplication.html).

### Bootstrap application

The below code snippet shows how to build a bootstrap container application with Ajax jsTree navigation panel. 
If desired, an application can be customized by overriding configureXXX() methods. 

```
Application app = new BootstrapContainerApplication(Theme.Litera, false);

Tag treeContainer = app.getHTMLPage().getFactory().div();
HTMLFactory htmlFactory = HTMLFactory.INSTANCE;
app
	.header("Header")
	.navigationBar("Navigation bar")
	.navigationPanel(treeContainer)
	.contentPanel(/* htmlFactory.overlay("Content overlay"), */ "Content")
	.footer("Footer");

JsTreeFactory jsTreeFactory = JsTreeFactory.INSTANCE;
jsTreeFactory.cdn(app.getHTMLPage());

FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());

app.getHTMLPage().body(jsTreeFactory.bind(treeContainer, jsTreeFactory.buildAjaxJsTree("node.id == '#' ? 'jstree.json' : 'jstree-' + node.id + '.json'", "'context-menu-' + node.id + '.json'")));		

writeFile("app/bootstrap/index.html", app.toString());

// JsTree
		
JsTreeNode rootNode = jsTreeFactory.jsTreeNode();
rootNode.icon("far fa-user");
rootNode.text("User");
rootNode.id(htmlFactory.nextId());
rootNode.hasChildren();
JSONArray jsTreeRootNodes = new JSONArray();
jsTreeRootNodes.put(rootNode.toJSON());
writeFile("app/bootstrap/jstree.json", jsTreeRootNodes.toString());		

JSONArray jsTreeChildNodes = new JSONArray();

JsTreeNode childNode = jsTreeFactory.jsTreeNode();
childNode.icon("far fa-user");
childNode.text("Child");
childNode.id(jsTreeFactory.getHTMLFactory().nextId());
jsTreeChildNodes.put(childNode.toJSON());
writeFile("app/bootstrap/jstree-"+rootNode.getId()+".json", jsTreeChildNodes.toString());

// JsTree context menu - the same menu for both nodes.
JsTreeContextMenuItem item = jsTreeFactory.jsTreeContextMenuItem();
item.label("Do it!");
item.icon("far fa-user");
item.action("window.location.href='http://www.nasdanika.org'; console.log('hey');");

JSONObject menu = new JSONObject();
menu.put("do-it", item.toJSON());
writeFile("app/bootstrap/context-menu-"+rootNode.getId()+".json", menu.toString());
writeFile("app/bootstrap/context-menu-"+childNode.getId()+".json", menu.toString());
```

The application: 

<iframe src="test-dumps/app/bootstrap/index.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 20) + 'px'"></iframe>

## Label

[Label](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Label.html) is something that can be displayed in a variety of ways. It has the following attributes:

* Icon - image URL (if there is slash) or css class, e.g. ``fas fa-user``.
* Text
* Color and Outline - used when a label is displayed as a badge or a button.
* Id - used by sub-interfaces, e.g. actions.
* Tooltip
* Description - more detailed description than a tooltip. It can be displayed in a modal 
* Notification - can be displayed as a badge next the text.

Label is a base interface for [Action](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Action.html) and a number of other interfaces. 

## Action

[Action](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Action.html) is the unit of system/user interaction.

Actions define a vocabulary of human/system interaction. In such a vocabulary actions are "verbs". 
E.g. "View transactions" or "Submit payment". 
If actions are verbs, then property sources are nouns. They are explained below.

Often a struggle in creating a new application is the fact that the users have a vague idea how it may look like. 
With application-language metaphor we can say that "if they can articulate it, we can automate it" - take their descriptions and turn verbs into actions and nouns into entities or value objects.

For example: "Jim uploads a feed from mainframe and then Bob validates it for inconsistencies". From this we may tell that:

* There is a user/actor/role Jim with "upload mainframe feed" action.
* There is a user/actor/role Bob with "verify mainframe feed" action.
* There is a "mainframe feed" entity which gets uploaded and validated. 

Thinking in terms of actions allows to stay focused on functionality and not get distracted by lower-level concerns such as button colors, borders, etc. 
Such concerns are important once the core application functionality is solidified.  
One of my friends once told me a long time ago that "it is much easier to make a functional application fast than a fast application functional".
Thinking in actions helps to deliver functionality faster and take care of non-functional requirements later. 

While the framework allows to refine appearance of UI elements on a case-by-case basis having a consistent UI increases clarity of human-system interactions.  
 
Where there's a vocabulary there's a dictionary. Building an application in terms of actions and property sources allows to create and publish such a user-system dictionary as part of the build process.   

In Java terms an action may be thought of as a method in a callback interface (Web UI) passed to a user. 
The user interacts with the system by "invoking callback methods" - activating actions so they get executed by the system by invoking their ``generate()`` method.

### Action activator

[ActionActivator](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ActionActivator.html)s are used to trigger action execution on the server side. 
There are three types of action activators:

* [NavigationActionActivator](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/NavigationActionActivator.html) - when user clicks on the action UI element it triggers navigation to the specified URL.
* [ScriptActionActivator](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ScriptActionActivator.html) - when user clicks on the action UI element it triggers execution of the specified script.
* [BindingActionActivator](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/BindingActionActivator.html) - the activator is responsible for configure the UI element to activate action execution. For example, such a binder may add KnockoutJS click binding.

In the above text is it assumed that UI elements are activated by a mouse click. However, they may be activated differently, e.g. for a form action activation shall happen on form submit.
In this case the navigation url would go to the action attribute of the form and the script code would go to the onSubmit handler.

### Contained actions

Actions have ``getChildren()`` method returning a collection of contained (sub) actions. 
There are also several default ``getXXXChildrens()`` methods filtering and grouping child actions.

An action may play different roles. Action role defines where it appears in the UI. 
There are several built-in [roles](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Action.Role.html)

#### Navigation
Navigation actions correspond to contained objects and are typically rendered in the navigation panel. For example a bank customer has/owns accounts. 
Actions to view such accounts would be displayed in the navigation panel. 
In entity-relationship terms navigation actions correspond to containment references and in Java terms they correspond to fields. 
You may also think of them as "view (noun)" actions. E.g. "view account".  

#### Context
Context actions get their name from the fact that they are displayed as a JsTree context menu items. 
They are also output as buttons in the content panel.
Context actions correspond to methods/operations/verbs. E.g. "open account" context action of a "view customer" action.

#### Section
Section actions play the same role as navigation actions, but they are displayed in the content panel of the contained action. 
For example, "view transactions" section action of "view statement" action would display a list of transactions below statement details.
Section actions may be executed/generated along with the containing action. In this case they don't need an activator.
Section actions with NavigationActionActivators may be executed by loading section content using AJAX.

#### View

PropertySource or PropertyDescriptor actions in this role are displayed in view mode.

#### Edit  

PropertySource or PropertyDescriptor actions in this role are displayed in edit mode.

### Action categories

Action extends [Categorized](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Categorized.html).
It allows to group actions into categories.
Anonymous categories have id but no text or icon.

Categories are presented in the UI in following ways:

* Navbar - uncategorized actions are at the top level, categorized are grouped into dropdowns. Headers and separators are not supported in category drop-downs. Anonymous categories are not supported.  
* Dropdown - headers for named categories and separators for anonymous.
* Action groups - different action groups for categories. Named categories are represented as action groups in cards with the category icon and text in the header.
* jsTree
    * Nodes - a node for named categories, anonymous categories are not supported - treated as uncategorized.
    * [TODO: Context menu](../../../../mantis/view.php?id=209) - dividers for anonymous categories, and sub-menus for named categories.
* Buttons - button groups, all categories are treated as anonymous.
* [TODO: Sections](../../../../mantis/view.php?id=210) - named categories add an extra level. Anonymous categories are displayed as a horizontal lines (HR) in header sections and as separate action groups, not supported in navs.   

## View generator

[ViewGenerator](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ViewGenerator.html) provides access to lower-level API's factories and methods to build
UI elements from application abstractions such as actions. 

## View part

[ViewPart](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ViewPart.html) generates Web UI leveraging ViewGenerator passed to its ``generate()`` method. 

The library provides several [view part implementations](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/viewparts/package-summary.html) used by the action application builder (see below).
View parts in the ``viewparts`` package may be customized via subclassing.

## Application builder

[ApplicationBuilder](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ApplicationBuilder.html) builds an application passed to its ``build()`` method.

### ViewPartApplicationBuilder

[ViewPartApplicationBuilder](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/ViewPartApplicationBuilder.html) is an abstract implementation of ApplicationBuilder.
It creates a ViewGenerator, view parts for the header, navigation bar, navigation panel, content panel, and footer, and then delegates application building to the view parts.

### AbstractActionApplicationBuilder
  
[AbstractActionApplicationBuilder](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/AbstractActionApplicationBuilder.html) is a subclass of ViewPartApplicationBuilder
which builds application from an action hierarchy.
It uses 3 actions to build the application:

* Active action - action which has been executed and results of its execution are presented to the user in the content panel. This action or its parent in the path are selected in the navigation bar or the navigation panel.
* Principal action - this action's link is displayed as the navigation bar brand. Context actions are displayed in the navigation bar, and child actions are displayed in the navigation panel. The action takes its name from the fact that it would typically represent the security principal. The principal action is computed as the second element in the active action path if the path has two or more elements, or as the active action if the path has 1 element. Null otherwise. This behavior may be customize by overriding ``getPrincipalAction()`` method. 
* Root action - this action's link is displayed in the header and its context actions are displayed in the footer. It is computed as the first element in the active action path, or as the active action if the path is empty. This behavior can be customized by overriding ``getRootAction()`` method.

AbstractActionApplicationBuilder delegates building to the following view parts:

* [NavigationBarViewPart](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/viewparts/NavigationBarViewPart.html) 
* [AdaptiveNavigationPanelViewPart](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/viewparts/AdaptiveNavigationPanelViewPart.html) which delegates to the [ActionGroupNavigationPanelViewPart](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/viewparts/ActionGroupNavigationPanelViewPart.html) if navigation actions do not have children (one-level) or to [JsTreeNavigationPanelViewPart](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/viewparts/JsTreeNavigationPanelViewPart.html) if navigation actions form a multi-level hierarchy. 
* [ContentPanelViewPart](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/viewparts/ContentPanelViewPart.html) which in turn delegates to [SectionViewPart](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/viewparts/SectionViewPart.html) 
* [FooterViewPart](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/viewparts/FooterViewPart.html)

The header view part is so simple that it is implemented as a lambda. 

### ActionApplicationBuilder
  
[ActionApplicationBuilder](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/impl/ActionApplicationBuilder.html) is a concrete subclass of AbstractActionApplicationBuilder
which take active, principal, and root actions in constructors.

In the examples below "view account" is an active action, "John Doe" is the principal action and the "Bank of Nasdanika" is the root action.

#### Action group navigation panel

The code snippet below shows how an application builder is constructed for the default action group navigation panel.
  
```
ApplicationBuilder appBuilder = new ActionApplicationBuilder(appAction, principalAction, principalAction.getChildren(), selected, Collections.emptyMap()) {
	@Override
	protected Object generateHeader(ViewGenerator viewGenerator) {
		return ((Tag) super.generateHeader(viewGenerator)).addClass("text-dark").style().text().decoration().none();
	}
};			
```

Full code is available in the [testActionApplication](https://github.com/Nasdanika/html/blob/master/org.nasdanika.html.tests/src/org/nasdanika/html/tests/TestApp.java) class.

<iframe src="test-dumps/app/action/link-group/credit-card-9012.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

   
#### jsTree navigation panel

```		
ActionApplicationBuilder jsTreeAppBuilder = new ActionApplicationBuilder(appAction, principalAction, principalAction.getChildren(), selected, Collections.emptyMap()) {
	@Override
	protected Object generateHeader(ViewGenerator viewGenerator) {
		return ((Tag) super.generateHeader(viewGenerator)).addClass("text-dark").style().text().decoration().none();
	}
		@Override
	protected ViewPart getNavigationPanelViewPart() {
			return new JsTreeNavigationPanelViewPart(navigationPanelActions, activeAction);
		}
	};		
```
  
<iframe src="test-dumps/app/action/js-tree/credit-card-9012.html" style="border:none;" width="100%" scrolling="no" onload="this.style.height = (this.contentWindow.document.body.scrollHeight + 50) + 'px'"></iframe>

## Data sources and properties

[DataSource](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/DataSource.html) is something which can provide access to some data, 
knows how to retrieve a version/revision of the data object, and also knows how to update that data using a map of property deltas.
There are two flavors of DataSource - [SingleValueDataSource](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/SingleValueDataSource.html) and [MultiValueDataSource](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/MultiValueDataSource.html).

[Property](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Property.html) is something which knows how to deal with properties/attributes of 
objects returned by data sources. E.g. if a data source returns a ``Person`` object then ``FirstName`` property know how retrieve and set the person's first name.  

## Property sources and descriptors

[PropertySource](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/PropertySource.html) extends DataSource, Label, and [ActionProvider](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ActionProvider.html). 
I.e. it is a data source which can be displayed in the UI and have associated actions.

As in the case of the DataSource, there are two flavors of PropertySource:

* [MultiValuePropertySource](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/MultiValuePropertySource.html).
* [SingleValueDataSource](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/SingleValueDataSource.html).

Property source has ``getPropertyDescriptors()`` method returning a list of [PropertyDescriptor](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/PropertyDescriptor.html)s. 
Property descriptor extends Label, Property, and Categorized. 

Wile properties are concerned with value retrieval and update, property descriptors are concerned with displaying and editing property values.   
