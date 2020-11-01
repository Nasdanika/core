A collection of components with input specification. Input specification is used for validating context entries and can also be used to generate UI for collecting user input.

Supported keys:

* ``input`` - Optional input specification, see details below. 
* ``elements`` - Required component or a list of components to execute.

### Example

A group of content components with input specification.

```yaml
group:
   input: 
      properties:
          name:
              type: text
              default-value: World          
   elements: Hello ${name}      
```

### Input specification

Input is a specification of a set of context properties and services. 
Properties can be organized into a hierarchy using property sets. 
During execution input is used to validate presence of required properties in the context and may also supply default values.
Input can also be used to generate UI for collecting user input. E.g. PicoCLI commands or Web UI. 

Input specification is interpolated. For example a list or map of choices for a particular property maybe provided as a context property.

Group's ``input`` element is an implicit property set.

#### Property set

Supported keys:

* ``conditions`` - a single value or a list of JavaScript expressions which should all evaluate to true for this property set to be displayed, validated, and injected into the context. Script bindings:
    * ``context`` - ${javadoc/org.nasdanika.common.Context} constructed from the group context and user input collected so far.
    * This property set properties.
* ``description`` - optional property set description.
* ``icon`` - optional icon. Treated as a URL if contains a slash (``/``) or as a CSS class otherwise. E.g. ``fa fa-cog``.
* ``include`` - single value or a list of relative URL's of property set specifications to include into this specification.
* ``label`` - optional property set label to display to the user.
* ``name`` - optional property set name. If it is present it is used as a prefix for the names of contained properties and property sets.         
* ``properties`` - a list or a map.
    * List - contains maps of properties and property set specifications. If an element map contains ``properties`` key it is treated as a property set.
    * Map - mapping of a property or proerty set name to a specification. If an element map contains ``properties`` key it is treated as a property set. This form does not support property sets without names. Also in this form spec maps shall not contain ``name`` keys.
* ``services`` - fully qualified name(s) of services which have to be present in the context for successful group execution. String or list.
* ``validations`` - a map or a list of maps containing cross-property validations. Map keys:
    * ``condition`` - JavaScript condition which must evaluate to true for the validation to pass. Has the same bindings as ``conditions``.
    * ``severity`` - ``WARNING`` or ``ERROR``.
    * ``message`` - diagnostic message. 

#### Property

* ``arity`` - Specifies exact number of property values or a range with a minimum an maximum values. Defaults to ``0..1`` which means an optional property. Examples:
    * ``1`` - one property value - the same as a required single-value property.
    * ``0..*`` or ``*`` - any number of values.
    * ``2..*`` - at least two values.
    * ``3..5`` - between three and five values.    
* ``choices`` - property value choices. List or map - see details below.    
* ``conditions`` - a single value or a list of JavaScript expressions which should all evaluate to true for this property to be displayed, validated, and injected into the context. Script bindings:
    * ``context`` - ${javadoc/org.nasdanika.common.Context} constructed from the group context and user input collected so far.
* ``control`` - an optional hint to the UI generator specifying which UI control to use to show and collect property value. Supported string values:
    * ``date`` - date control.
    * ``time`` - time control.
    * ``drop-down`` - for fixed arity properties with choices.
    * ``checkbox`` - for boolean properties and for multi-value properties with choices.
    * ``file`` - file attachment.
    * ``number`` - for numeric properties.
    * ``password`` - single-line masked text.
    * ``radio`` - for fixed arity properties with choices.
    * ``text`` - single-line for fixed arity properties without choices. 
    * ``text-area`` - multi-line for fixed arity properties without choices. 
* ``default-value`` - property default value - a single value or a list.
* ``description`` - optional property description.
* ``editable`` - if ``false`` property control shall be rendered as read-only. Default is ``true``.      
* ``icon`` - optional icon. Treated as a URL if contains a slash (``/``) or as a CSS class otherwise. E.g. ``fa fa-cog``.
* ``label`` - optional property label to display to the user.
* ``name`` - property name. Required if property is defined in a list.   
* ``type`` - Fully qualified name of property class. Defaults to ``java.lang.String``.
* ``validations`` - a map or a list of maps containing cross-property validations. Map keys:
    * ``condition`` - JavaScript condition which must evaluate to ``true`` for the validation to pass. Has the same bindings as ``conditions`` plus ``value`` binding containing property value.
    * ``severity`` - ``WARNING`` or ``ERROR``.
    * ``message`` - diagnostic message. 
    
#### Choices

Choices may be specified as a list or a map.

##### List

Choices list may contain scalars or maps. 
If a list element is a scalar then its value is used both as choice value and a choice label.
If a list element is a map, then the following keys are supported in the map:

* ``choices`` - If this key is present then the choice is actually a choice group. Mutually exclusive with ``value``.
* ``description`` - optional choice description.
* ``icon`` - optional icon. Treated as a URL if contains a slash (``/``) or as a CSS class otherwise. E.g. ``fa fa-cog``.
* ``label`` - optional choice label to display to the user.
* ``value`` - choice value. Mutually exclusive with nested ``choices``.

##### Map

Choices map uses choice values as keys and choice labels (if a scalar) or specifications (if a map) as values.
Choice specification maps support the following keys:

* ``description`` - optional choice description.
* ``icon`` - optional icon. Treated as a URL if contains a slash (``/``) or as a CSS class otherwise. E.g. ``fa fa-cog``.
* ``label`` - optional choice label to display to the user.


