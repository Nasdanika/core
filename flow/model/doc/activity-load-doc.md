An example of an activity YAML definition:

```yaml
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