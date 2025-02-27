### Example

``drawio -p my-property="My property" invocable.drawio invoke 33 66``

* The parent [drawio](../index.html) command loads ``invocable.drawio`` diagram with ``my-property`` property set to ``My property``. This property is used for placeholder expansion. 
* This command:
    * Uses default values for ``--processor-property`` and ``--bind-property`` options to create diagram element processors and bind them to dynamic proxy methods - in this case a single [``invoke()``](https://javadoc.io/static/org.nasdanika.core/common/2025.1.0/org.nasdanika.common/org/nasdanika/common/Invocable.html#invoke(java.lang.Object...)) method.
    * Calls the method with command line arguments - ``33`` and ``66``