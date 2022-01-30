A fragment of package definition:

```yaml
flow-package:
  uri: nasdanika://togaf/adm
  documentation:
    content-markdown:
      style: true
      source:
        content-text: "[TOGAF 9.2](https://pubs.opengroup.org/architecture/togaf92-doc/arch/index.html) [Architecture Development Method](https://pubs.opengroup.org/architecture/togaf92-doc/arch/toc-pt2.html) model."
  name: TOGAF ADM
  modifiers: abstract 
  activities:
    adm:
      flow-flow:
        name: ADM
        representations:
          main:
            type: "drawio:adm.drawio"
        elements:
          preliminary: phases/preliminary/preliminary.yml
          architecture-vision:
             flow-flow:
               name: A. Architecture Vision
               outputs:
                 business-architecture:
                   target: business-architecture
                 requirements-management:
                   target: requirements-management
```