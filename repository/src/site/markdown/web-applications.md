# Web applications

This page explains how to build web applications backed by a [CDO] server using Nasdanika HTML bundles.

In addition to the bundles which can be used to build both static and dynamic content there are three CDO bundles for creating web applications:

* [org.nasdanika.cdo](apidocs/org.nasdanika.cdo/apidocs/index.html) - CDO repository and server OGSi components.      
* [org.nasdanika.cdo.h2](apidocs/org.nasdanika.cdo.h2/apidocs/index.html) - [H2](http://www.h2database.com/html/main.html) CDO repository OSGi component.       
* [org.nasdanika.cdo.http](apidocs/org.nasdanika.cdo.http/apidocs/index.html) - servlet, routers and annotations for dispatching HTTP requests to Java method invocations in a context of a CDO transaction and a CDO object (entity).

This picture shows interaction between different web application components:

![web application](web-application.png)

## Servlet

Application servlet extends [CDOTransactionServlet](apidocs/org.nasdanika.cdo.http/apidocs/index.html?org/nasdanika/cdo/http/CDOTransactionServlet.html) and overrides its protected methods to customize for the application needs as will be explained below.

In its ``init()`` method CDOTransactionServlet creates a service tracker for services exposing ``org.eclipse.emf.cdo.session.CDOSessionProvider`` interface. 
If there are several service components providing this service in the application, then a filter can be used to select a specific service component. 
The tracker is closed in ``destroy()``.






## Session Provider

## Session Initializer



## Server

## Repository

## Deployment options

### Behind Apache with NTLM authentication

http://us.mirrors.quenda.co/apache//httpd/binaries/win32/, Bitnami, apache haus - https://www.apachehaus.com/cgi-bin/download.plx, example of configuration - forwarded user, ...

  

TODO - 3 bundles, runtime features, product configuration. Security - forwarded users, basic auth, tokens, session subject

routing annotations, routing, conversion, locking

development process
* create projects
* model
* web - servlet, adapter factories.

## Summary

build both rest and web ui apps
solid foundation and default behavior with fine grained polymorphic customizations