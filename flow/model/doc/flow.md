Flow is a composite [activity](Activity.html). A flow can extend another flow and inherit its elements, which it can override or suppress.

Flow extension/inheritance can be used to define generic flows at higher levels of an organization and specialize them at lower levels.
Base flows can also be defined by standard bodies and flows can be organized into a "Flow continuum", similar to TOGAF's [Enterprise Continuum](https://pubs.opengroup.org/architecture/togaf9-doc/arch/chap35.html).
Flows can be defined as abstract and contain abstract elements, i.e. placeholder elements which specify _what_ needs to be done, but not _how_. 
Abstract elements must be overridden or suppressed in concrete flows.

Flows can also specify final elements which cannot be overridden. It can be used to enforce decision authority, 
e.g. if some activity must be done in one particular way for regulatory or legal reasons then that activity would be defined as final at the higher levels of the organization
to prevent overriding at lower levels.

Flows also can be defined as final, i.e. they cannot be extended.
Note that a final abstract journey makes no sense.