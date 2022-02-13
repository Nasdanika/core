FlowElement is an abstract base class for [Flow](Flow.html) elements.
It is a source and target of [transitions](Transition.html) and [calls](Call.html).
It can specify [participants](Participants.html), input and output [artifacts](Artifact.html), and responsibility assignments for the flow element and artifacts.
Flow elements can override or suppress elements with the same key defined in one of flows the containing flow extends. 
