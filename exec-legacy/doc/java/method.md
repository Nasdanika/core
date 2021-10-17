Generates a method.

Configuration keys;

* ``imports`` - fully qualified names of classes to add to the imports section of the containing compilation unit. A string or a list of strings. Optional. An exception is thrown if any of imports cannot be added to the imports section due to a name clash.
* ``name`` - required, interpolated. Must be the same as the name of the containing class.
* ``modifiers`` - a string or a list of strings. Optional.
* ``annotations`` - a string or a list strings. Optional.
* ``type-parameters`` - a string or a list strings. Optional.
* ``comment`` - a string, content component or a list of thereof. Optional.
* ``body`` - A string, content component or a list of thereof. If not present a method is generated without ``{ }``, e.g. an interface method or an abstract class method.
* ``parameters`` - a string or a list strings. Optional.
* ``exceptions`` - a string or a list strings. Optional.
* ``return-type`` - a string, method return type. If not present the method return type is ``void``.
