This package contains classes for defining "how to do things" in a non-linear visual form using [journeys](Journey.html).

A journey is a directed graph of [journey elements](JourneyElement.html) - [activities](Activity.html), nested journeys, [services](Service.html),
and [pseudo-states](PseudoState.html).

Journey elements can [transition](Transition.html) control from one another and also [call](Call.html) one another.

Because journey elements inherit from [ModelElement](../ModelElement.html), they can have documentation sections to benefit from a combination of 
non-linear (journeys) and linear (text, [sections](../Document.html)) knowledge representation.

One important feature of journeys is journey inheritance, which is similar to inheritance in languages like Java - 
it is possible to define a base journey and then extend it and customize (override) journey elements.

Journey inheritance can be useful in hierarchies such as [organization](../Organization.html) hierarchies and technology hierarchies.

In an organization hierarchy higher levels can define generic processes and specify which elements can be customized at lower levels
and which cannot - final elements. Lower levels of the organization would customize what they need to their specifics, e.g. local regulations.

Similar to that, in the world of technology one can define, say, a generic journey explaining how to develop and deploy a microservice.
Then that journey can be customized to different ways to deploy, e.g. different cloud providers - private and public, and their offerings.

Journey elements can be defined as abstract. For example, an abstract journey for developing a cloud application with concrete elements which are common for different cloud providers
and abstract elements which are provider or technology specific. E.g. create a cloud account or develop a _Java_ microservice.