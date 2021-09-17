Journey is a composite activity. A journey can [extend](Journey.html#EReference-extends) another journey and inherit its elements which it can override or suppress.

Journey extension/inheritance can be used to define generic journeys at higher levels of an [organization](Organization.html) and specialize them at lower levels.
Generic journeys may contain abstract elements, i.e. placeholder elements which specify _what_ needs to be done, but not _how_. 
Abstract elements must be overridden or suppressed in concrete journeys.

Journeys can also specify final elements which cannot be overridden. It can be used to enforce decision authority, 
e.g. if some activity must be done in one particular way for regulatory or legal reasons then that activity would be defined as final at the higher levels of the organization
to catch accidental overriding at lower levels.

Journeys also can be defined as final, i.e. they cannot be extended. Note that a final abstract journey makes no sense.