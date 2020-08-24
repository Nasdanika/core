Generates Java class.

Configuration keys:

* ``name`` - required, interpolated.
* ``modifiers`` - a string or a list of strings. Optional.
* ``annotations`` - a string or a list strings. Optional.
* ``type-parameters`` - a string or a list strings. Optional.
* ``comment`` - a string, content component or a list of thereof. Optional.
* ``body`` - a string, content component or a list of thereof. Optional.
* ``super-types`` - a string or a list strings. Optional. The first element forms the ``extends`` clause unless it is ``java.lang.Object``, the rest form the ``implements`` clause.	
