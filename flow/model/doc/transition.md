Transition connects two [flow elements](FlowElement.html), e.g. [activities](Activity.html) and is navigated when execution of the source element is completed
and execution of the target element shall begin. 

Transition may define payload [artifacts](Artifact.html) - what the source element shall pass along the transition to the target element. 
For example, a transition from "Business Analysis" to "Design" may have "Requirements Document" as its payload.