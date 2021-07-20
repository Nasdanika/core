Generates Java interface.

Interfaces have dual nature - they can be used as resources (compilation units) and as contents. 
As a resource/compilation unit they can be added to packages. When used as a resource an interface is wrapped into a compilation unit with reconcile action ``MERGE`` and format set to ``true``.
As contents they can be added to compilation units any other components accepting contents, e.g. method body. 

Configuration keys:

* ``imports`` - fully qualified names of classes to add to the imports section of the containing compilation unit. A string or a list of strings. Optional. An exception is thrown if any of imports cannot be added to the imports section due to a name clash.
* ``name`` - required, interpolated.
* ``modifiers`` - a string or a list of strings. Optional.
* ``annotations`` - a string or a list strings. Optional.
* ``type-parameters`` - a string or a list strings. Optional.
* ``comment`` - a string, content component or a list of thereof. Optional.
* ``body`` - a string, content component or a list of thereof. Optional.
* ``super-types`` - a string or a list strings specifying interfaces which this one extends. Optional. 	
