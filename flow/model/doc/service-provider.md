Base class for package elements which an provide services - activities and flows which can be referenced from other activities and flows.

For example, an infrastructure team participant may provide a service "Create a cloud environment". 
This service can be either called or referenced using [Service](Service.html) from application development flows. E.g. "Create a DEV cloud environment", "Create a PROD cloud environment".
The service itself can be a flow and reference services provided by other service providers. E.g. "Create a cloud environment" service may call "Financial approval" service provided by a Finance participant.

A programming analogy would be service provider being a class and services being methods called by other classes.
A distributed computing analogy would be service provider being a microservice and services being endpoints called by other microservices.

