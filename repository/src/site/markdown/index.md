# HTML

Nasdanika HTML features several libraries providing [fluent API](https://en.wikipedia.org/wiki/Fluent_interface) for building HTML user interfaces. 
The goal of the libraries is to make Java developers more productive by:

* Working in Java instead of switching between Java for the server side development and HTML, JavaScript and CSS for the client side development.
* Apply the power of Java such as inheritance, polymorphism, patterns, e.g. a strategy pattern, etc. to building HTML user interfaces.
* Operate on a higher level of abstraction thinking in terms of, say, actions and view parts instead of individual HTML elements.
* Think about the Web User Interface as a Java interface - a collection of methods/actions which the user can invoke.
* Think about users as asynchronous method invocations - a user is invoked by passing them a callback (user) interface.

Nasdanika HTML libraries, except the application model and EMF ones, are provided as both Maven jar's and Equinox/OSGi bundles. It allows to use them in both Maven/plain Java and OSGi applications. 
The application model and EMF libraries have dependencies on Eclipse/Equinox bundles and are provided only as an OSGi bundle.

Nasdanika HTML libraries can be used to build both static Web sites, e.g. documentation, and dynamic web applications.        

## Libraries/Bundles

* [HTML](html.html) - Low-level API's to build HTML 5 code. 
* [Bootstrap](bootstrap.html) - [Bootstrap 4.x](https://getbootstrap.com/) Java bindings, including [Bootswatch](https://bootswatch.com/) themes. 
* [Font Awesome](fontawesome.html) - [Font Awesome 5.x](https://fontawesome.com/) Java bindings. 
* [KnockoutJS](knockout.html) - [Knockout JS 3.4.x](https://knockoutjs.com/) Java bindings. 
* [jsTree](jstree.html) - [jsTree 3.3.7](https://www.jstree.com/) Java bindings,
* [Application](app.html) - Java API's for building HTML applications using higher level abstractions of actions and property sources.
* [Application Model](app-model.html) - EMF model implementing the action API and a model editor.
    * [Model documentation](app-model-doc).
* [EMF](emf.html) - Uses [Ecore](https://www.eclipse.org/modeling/emf/) metadata and annotations to build actions and property sources backed by [EObject](http://download.eclipse.org/modeling/emf/emf/javadoc/2.9.0/index.html?org/eclipse/emf/ecore/EObject.html)'s.
* [Web applications](web-applications.html) - bundles for building web applications backed by a [CDO](https://wiki.eclipse.org/CDO) server.

## Resources

* Overview presentation
    * [SlideShare](https://www.slideshare.net/PavelVlasov2/nasdanika-html-fluent-java-api-for-building-web-ui).
    * [PDF](Nasdanika-HTML.pdf).
* P2 Repository
    * ``https://www.nasdanika.org/products/html/2.0.0-SNAPSHOT/repository``.
    * [Archived](https://www.nasdanika.org/products/html/2.0.0-SNAPSHOT/org.nasdanika.html.repository-2.0.0-SNAPSHOT.zip).
* Maven Repository - ``https://www.nasdanika.org/products/html/2.0.0-SNAPSHOT/maven-repository``.    
* [Sources](html.zip).
* [NasdanikaBank.app](NasdanikaBank.app) - a sample model with several actions for demo and testing purposes. It is also available as a resource in ``org.nasdanika.app.model`` bundle.
* [Spring Boot demo](https://github.com/Nasdanika/html-spring-boot-demo) - A Spring Boot application which uses Nasdanika HTML libraries to build Web UI.    
* [Test coverage](test-coverage/index.html).
 
## How to contribute

As an open source project we use the [Fork and Pull Model](https://help.github.com/articles/about-collaborative-development-models/).
You can find more information about collaborative development at GitHub in this article - [Collaborating with issues and pull requests](https://help.github.com/categories/collaborating-with-issues-and-pull-requests).

When you contribute code, please make sure that the changes are clearly identifiable. In particular, avoid making non-functional changes in the code which you do not touch, 
e.g. auto-formatting of an entire compilation unit. 

