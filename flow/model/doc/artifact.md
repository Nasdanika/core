Input to a [flow element](FlowElement.html), e.g. [activity](Activity.html), or an output from a flow element.
Artifacts can be stored in [resources](Resource.html).

For example, Development activity takes a design document as input and outputs source code.
Source code is stored in a version control system, e.g. [Git](https://git-scm.com/).
Build activity takes source code as input and produces a binary, e.g. a jar file. 
That binary can be stored in/published to a binary repository, e.g. [Maven Central](https://mvnrepository.maven.org/).
