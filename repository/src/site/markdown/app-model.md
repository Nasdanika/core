# Application Model

EMF implementation of the Application API. It can be used for application prototyping and as a transformation step in the application rendering process.

* [Model documentation](app-model-doc)
* [Java API documentation](apidocs/org.nasdanika.html.app.model/apidocs/index.html)
* [Sample model](NasdanikaBank.app)

Install the application model editor from the p2 repository and use New > Nasdanika > Application model to create a new model.

## Roadmap

### Markdown and plain text

* Flexmark to convert markdown to HTML.
* PRE styles for plain text.

### Templating

The application model can also be used for templating. Currently there is no template engine implementation. Future releases might provide template engine.
One possible implementation of such an engine is to use token source to interpolate ``{{...}}`` tokens. 
Token source would also be used to retrieve ``iterator`` value which should be a collection of token sources. 
Each such token source will be used for template instantiation. 
A more advanced approach is to have an iterator class/function taking a token source and returning a collection of token sources. 
``class:`` prefix can be used to specify that the iterator expression is not a token name but a class name, ``::`` at the end of the class name would specify that iterator is a method/function, e.g. ``class:org.myorg.Util::accounts``. 

### Bootstrap model elements

* Add model elements representing Bootstrap elements such as cards, navs, containers, tables, ...
* Base Content interface - whatever can be added to "content".
* Fragment or raw content - multi-line.
* Text content - html, markdown, plain text - refactor content actions.
* Content reference, url/location, content type. Have a base type with content type enumeration and subclasses with multi-line text and reference. 
* Provide detailed model documentation with links to the bootstrap site and examples. Ideally model screenshots and resulting UI elements next to each other.
* Attributes/references to represent important API's, e.g. content, color, style, ...
* Icons for model elements and possibly attributes.
* Tree editor to start with.
* Consider a Sirius-based diagram editor - sketching, not WYSIWYG.
* Templating support as explained above, e.g. a table row may have ``iterator`` expression/class/function reading data from some data source - JSON file, database table, ... 
* Have a separate model for bootstrap and reference it from the application model? Perhaps an overkill, but provides separation of concepts.
* This would also require a model for HTML - HTMLElement, tables, inputs, buttons, ...

This would allow people familiar with neither Bootstrap nor Java programming to create:
* Static sites.
* Web UI prototypes which could be templatized to become either web applications or generators of static sites.

### User/actor model elements

Inheritance. E.g. logged user has access to profile, all users/actors inherit from a logged user and as such have "view profile" action. 
With support of user/actor model elements in the application model the story model can be retired.

Several ways to model different user roles:

* Actions contained by users - follows app/principal/action model.
* Actions are granted to users - roles many reference.

How to link entity (ecore) and application models?

### Property and data source model elements

Property source contains properties and data source.
Data source - use adapter if not set. REST data source - YAML, JSON, XML. Available operations. Headers. Use new HTTP Builder API's?      

### Multi-resource models

* Action reference/proxy - references another action possibly defined in a different resource. Delegates all calls to that action. For this a base class "AbstractAction" would be needed implementing ``org.nasdanika.html.app.Action`` with the existing ``Action`` model subclass having own structural features and ``ActionProxy`` subclass having ``target`` non-containment reference.
* Content reference (URL) - also support markdown and plain text. Resolve references relative to the model. This approach would be similar to Maven sites.    

### Static site generation

* Application model class - output directory, parts configuration - background, text, borders, ...
* Class hierarchy:
    * Abstract application, 
    * Generic application or just application - content references for view parts, 
    * Action application - contains actions, generates pages for actions with navigation activator. View parts configuration - use defaults, class names of view parts to customize, buttons to select or create as in codegen. Classes shall have constructors with standard list of parameters, use parameter type matching, or use some kind of annotations, e.g. ``@Parameter("name")``.
* Containment hierarchy - HTML page (also a model element), application in the body (content), then application parts, depending on app type - parts or actions.    
* Generate HTML context menu on the HTMLPage class.
* HTMLPage checkboxes for CDN's.
* Maybe "file system" or "workspace" is just one of deployment targets - see below.

### Publishing

* Deployment targets - (S)FTP, HTTP, ...
* Context menu actions on the page "Deploy to all targets" and on individual targets.

### Live preview

Starts HTTP server (Jetty?) on a random port or fixed port, opens a web browser. Similar to how the help system works.

### Validations

* Duplicate ID's - error.
* Empty actions - warning.

### Web UI

It is possible to build a web application for editing application/action models with functionality similar to the application editor, but in a web browser.

