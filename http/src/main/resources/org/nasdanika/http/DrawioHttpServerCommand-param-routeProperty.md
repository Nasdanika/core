Route property value is a YAML String or Map. 

#### String

If the value is a String, then the value is the route path and the route method is ``GET``.

Example: ``/person``

#### Map

If the value is a String, then the map shall contain two keys:

* ``method`` - one of [HTTPMethod](https://javadoc.io/doc/org.nasdanika.core/http/latest/org.nasdanika.http/org/nasdanika/http/HttpMethod.html) literals.
* ``path`` - route path

Example:

```yaml
method: GET
path: /person
```