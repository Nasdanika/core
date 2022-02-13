An example of artifact participant responsibility YAML definitions, lines 9+:

```yaml
code:
  flow-activity:
    name: Code
    outputs:
      unit-test:
        target: unit-test
      document:
        target: document
    artifact-responsibilities: # Artifact responsibilities
      story:
        responsible: developer
        accountable: scrum-master
        consulted: [lead-developer, architect]
        informed: [product-owner, tester]
        description: |+2
          Developer changes story status to "In progress" and adds comments about the progress or questions requiring clarifications. 
          Other responsible subscribe to the story to receive notifications and provide clarifications.                                   
      source:
        responsible: developer
        accountable: lead-developer
        consulted: [product-owner, architect]
        informed: [scrum-master, tester]
        description: |+2
          Developer writes code and reaches out to the product owner and architect for clarifications. Informs the scrum master and the tester on the progress.    
```