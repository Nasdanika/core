Generates a type field. 

Configuration keys:

* ``imports`` - fully qualified names of classes to add to the imports section of the containing compilation unit. A string or a list of strings. Optional. An exception is thrown if any of imports cannot be added to the imports section due to a name clash.
* ``name`` - required, interpolated.
* ``modifiers`` - a string or a list of strings. Optional.
* ``annotations`` - a string or a list strings. Optional.
* ``type-parameters`` - a string or a list strings. Optional.
* ``comment`` - a string, content component or a list of thereof. Optional.
* ``body`` - field initializer. A string, content component or a list of thereof. Optional.
* ``type`` - field type.
