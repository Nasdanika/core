Context builders can be used customize ${org.nasdanika.common.Context} of command's ``ExecutionParticipant``. Implements org.nasdanika.cli.ContextBuilder
For example, ``JavadocContextBuilder`` loads package lists from Javadoc URL's provided in configurations and replaces fully qualified names of classes with links to Javadoc documentation. 
E.g. ``${{javadoc/org.nasdankia.common.Context}}`` with ${org.nasdanika.common.Context}.
 
Context builders are Java classes which implement ``FunctionFactory<Context,Context>`` and are registered with xxx extension point.

TODO:

* Registration example
* Implementation example - configuration context to factory and command context to function.
* Command line example, config example
* Generate vinci model from registered context builders - doc resource attribute. Mount under Context builders action - generate the action and link from the doc model. ``vinci generate documentation context-builders``. 
Modify other documentation commands to move under documentation - ecore-doc and help to cli

Builders may have parameters in this case they have to implement BiConsumer. Builder parameters are passed to the builder by calling its <code>java.util.function.BiConsumer.accept(String,String)</code> method.
 
Addressing plugin symbolic name/builder id e.g. ``org.nasdanika.vinci.cli/javadoc-context-builder``

context-builder - individual c/b? or context-builders - mapping, config

Config:

- Map - id, config, mounts
- List of maps

# Next steps

* Implement context builder loading in the command - javadoc context builder in vinci.cli bundle.
* Test - javadoc context builder for documentation.

```yaml
mounts:
    javadoc:
        id: org.nasdanika.vinci.cli/javadoc-context-builder
        config:
            # Core
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.cli/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.common/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.eclipse/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.emf/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.emf.edit/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.emf.presentation/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.help/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.ncore/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.ncore.edit/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.sirius.tree/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.sirius.tree.edit/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.texttospeech/apidocs/
            - https://nasdanika.org/builds/${release}/products/core/bundles/org.nasdanika.ui/apidocs/

            # HTML
            - https://nasdanika.org/builds/${release}/products/html/bundles/org.nasdanika.html/apidocs/
```