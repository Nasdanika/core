## Example

``drawio diagram.drawio http-server --http-port=8080 processor route``

* Parent [drawio](../index.html) command loads ``diagram.drawio`` file
* This command:
    * Loads processor [invocable URIs](https://docs.nasdanika.org/core/capability/index.html#loading-invocables-from-uris) from the ``processor`` property
    * Loads route definitions from the ``route`` property
    * Serves diagram element routes on port ``8080``

## Resources

* [Serving HTTP — Diagrams & Reflection](https://medium.com/nasdanika/serving-http-diagrams-reflection-6944a90a8161) story   
* [Routing HTTP](https://projectreactor.io/docs/netty/1.2.3/reference/http-server.html#routing-http) Reactor Netty Reference Guide