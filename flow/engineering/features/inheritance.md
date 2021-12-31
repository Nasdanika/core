Nasdanika Flow supports [package](../../Package.html) multiple inheritance, i.e. a package may extend one or more other packages.

Flow inheritance can be useful in hierarchies such as organizational hierarchies and technology hierarchies.
In an organizational hierarchy higher levels can define generic processes and specify which elements can be customized at lower levels
and which cannot - final elements. Lower levels of the organization would customize what they need to their specifics, e.g. local regulations.

Similar to that, in the world of technology one can define, say, a generic flow explaining how to develop and deploy a SpringBoot microservice.
Then that flow can be customized to different ways to deploy, e.g. different cloud providers - private and public, and their offerings.

For example, a package containing flows for agile software development using a particular technology, say SpringBoot on AWS, may extends a package containing generic agile flows as well as a package containing AWS-specific flows and other element which are agnostic to a particular development methodology.

Extension packages may:
* Add elements. 
* Override elements - replace definitions. 
* Suppress (remove) elements.

This is similar to inheritance in object-oriented languages like lava where subclasses can add and override inherited methods. Method suppression is not available in lava. 
Similar to Java, elements can be defined as ``final`` and ``abstract`` - final elements cannot be overridden, and abstract elements must be overridden.
For example, there might be an abstract flow for developing a cloud application with concrete elements which are common for different cloud providers
and abstract elements which are provider or technology specific. 

Flow inheritance is also similar to basing Docker images on other images using ``FRROM``, but with support of "mix-in" images with multiple inheritance.
Inheritance allows multiple teams to incrementally build a "whole end-to-end picture" for flow participants.

### Example

[AWS Agile Development](https://github.com/Nasdanika/html/blob/master/flow/src/test/resources/org/nasdanika/html/flow/tests/agile/aws.yml#L4) extends [Core Agile Development](https://github.com/Nasdanika/html/blob/master/flow/src/test/resources/org/nasdanika/html/flow/tests/agile/core.yml).

