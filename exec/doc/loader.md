Loader supports the following types:

* General - control flow and context manipulation components. Can be adapted to both supplier and consumer factories and as such can be used both as resource and as content generators:
    * ``configure`` - [Configurator](configurator.html) - Adds or overrides context properties.
    * ``for-each`` - [Iterator](iterator.html) - execute child components zero or more times with different contexts.
    * ``(map)`` - [Map cast](map-cast.html) - Forces content to be treated as a map even if there is a single entry, which by default is treated as an object definition.
    * ``map`` - [Mapper](mapper.html) - Replaces the current context with a new context loaded from a map and intepolated.
    * ``reference`` - [Reference](reference.html) - Loads object(s) from a YAML resource identified by the reference.
* [Resources](../resources/index.html) - components which implement ${javadoc/org.nasdanika.common.ConsumerFactory}<${javadoc/org.nasdanika.common.resources.BinaryEntityContainer}> and can contribute elements to a container:
    * ``container`` - [Container](../resources/container.html) - Generates a container (folder).
    * ``file`` - [File](../resources/file.html) - Generates a file with contents provided by the content components (see below).
    * ``zip-resource-collection`` - [ZipResourceCollection](../resources/zip-resource-collection.html) - Contributes resources loaded from a Zip archive.
* [Content](../content/index.html) - components which implement ${javadoc/org.nasdanika.common.SupplierFactory}<${javadoc/java.io.InputStream}> and can contribute contents to a file:
    * ``base64`` - [Base 64 Encoder](../content/base64.html) - Encodes binary stream with [Base64](https://en.wikipedia.org/wiki/Base64) encoding.
    * ``form`` - [URL Form Encoder](../content/form.html) - Encodes a map with URL encoded form.
    * ``free-marker`` - [FreeMarker template processor](../content/free-marker.html) - Evaluates FreeMarker template with context or specified model as input.
    * ``http`` - [HTTP Call](../content/http-call.html) - Performs an HTTP request and returns a response.
    * ``interpolator`` - [Interpolator](../content/interpolator.html) - Interpolates content with context properties.
    * ``json`` - [JSON encoder](../content/json.html) - Encodes map or array as JSON.
    * ``mustache`` - [Mustache template processor](../content/mustache.html) - Processes Mustache template with context properties as input.
    * ``resource`` - [Resource](../content/resource.html) - Loads content from a resource identified by URL.
    * ``yaml`` - [YAML encoder](../content/yaml.html) - Encodes value as YAML.
    * ``zip-archive`` - [Zip archive](../content/resource.html) - Produces zip stream from contained resources.
* [Java](../java/index.html) - Specializations of resource and content components/generators for generating Java sources:
    * ``source-folder`` - [Source folder](../java/source-folder.html) - Container specialization designating the root folder containing package folders and compilation units.
    * ``package`` - [Package](../java/package.html) - Container specialization for a Java package.
    * ``compilation-unit`` - [Compilation Unit](../java/compilation-unit.html) - File specialization providing support for Java imports management, auto-injection of package declaration, code formatting, and auto-merging of generated and hand-crafted code.
    * Members - elements that can be contained in types:
        * Types - elements that can be contained in compilation units and other types. Can contain members and other content components: 
            * ``annotation`` - [Annotation](../java/annotation.html) - Generates a Java annotation.
            * ``class`` - [Class](../java/class.html) - Generates a Java class.
            * ``enum`` - [Enum](../java/enum.html) - Generates a Java enum.
            * ``interface`` - [Interface](../java/interface.html) - Generates a Java interface.
        * Operations - executable members which may take parameters and throw exceptions.    
            * ``method`` - [Method](../java/method.html) - Generates a Java method.
            * ``constructor`` - [Constructor](../java/constructor.html) - Generates a Java class constructor.
        * ``field`` - [Field](../java/field.html) - Generates a Java type field.
    