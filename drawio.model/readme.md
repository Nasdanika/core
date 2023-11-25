
### Feature Mapping

Properties:

* ``page-element`` - for wiring and for linking of pages to semantic elements
* ``top-level-page`` - overrides the default behavior
* ``semantic-id``
* ``type``
* ``ref-id``
* ``base-uri``
* ``spec``
* ``spec-ref``
* ``doc-format`` - defaults to markdown
* ``documentation``
* ``doc-ref``
* ``feature-map``
    * ``container``
        * ``self``
        * ``argument``
    * ``contents``
        * ``self``
        * ``argument``    
    * ``source`` 
    * ``target`` 
    * ``start`` 
    * ``end`` 
* ``feature-map-ref``

Self feature setters take precedence

Feature mapping properties:

* ``path`` - number of list - true or expressions
* ``condition`` 
* ``expression``
* ``position`` 
* ``type`` - type of the object into which the value is injected. Shall use the same type resolution as the factory, make factory methods public and add setFactory() to content mapper or something like this.
* ``argument-type`` - type of value
* ``greedy`` - for containment references - true, false, or ``no-children`` (default)


Example:

"Cluster" contains "Server", "Server" has a connection to "Network"


|                   | ``self`` | ``argument`` |
| ----------------- | ---------- | ---------------- |
| ``container`` | Defined at the container level (Cluster). Container (Cluster) features to inject the contents (Server) to, e.g. ``members`` | Defined at the contents level (Server). Container argument (Cluster) features to inject the context object (Server) to |
| ``contents``  | Defined at the contents level (Server). Contents (Server) features to inject the container (Cluster) to | Defined at the container level (Cluster). Contents argument (Server) features to inject the context object (Cluster) to |


Factory is created from containment mapper and non-containment mapper

Transform / wire, content mapper, reflective content mapper, code snippets 