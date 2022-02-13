An example of a partial YAML definition of a flow:

```yaml
activities:
  development:
    flow-flow:
      name: Agile development
      representations:
        flow:
          hide-empty-description: true
          vertical: false
          name: PlantUML Diagram
          description: Generated diagram
        manual: 
          type: "drawio:./agile-development.drawio"
          description: Generated and then manually edited diagram
      elements:
        create:
          flow-activity:
            name: Create a story
            description: Product Owner creates a new story in the issue tracker.
            participants: product-owner
            resources: issue-tracker
            output-artifacts: story
            modifiers: final
            responsible: product-owner
            accountable: product-owner
            consulted: lead-developer
            informed: [developer, tester]                
            representations:
              flow:
                hide-empty-description: true
                vertical: false
                context: 1
            outputs: 
              groom:
                target: groom
                payload: story
```