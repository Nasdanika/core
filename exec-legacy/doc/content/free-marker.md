Generates contents using [Apache FreeMarker](https://freemarker.apache.org/).

Configuration shall be a map which supports the following keys:

* ``base`` - Base URL for resolving templates. The base itself is resolved to the loading base. When loading from a URL the loading base is the URL of the YAML specification file. If not specified, then the loading base is used as templates resolution base. 
* ``template`` - Template to render.
* ``model`` - Context property of model object to use for template rendering. If not provided then the context itself is used as the template model. 


Example:

```yaml
free-marker:
   base: free-marker-templates/
   template: hello.ftl
``` 