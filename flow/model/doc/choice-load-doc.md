An example of a choice YAML definition:

```yaml
is-architecturally-significant:
  flow-choice:
    name: Architecturally significant
    description: Decision whether the story is architecturally significant and requires an architectural review.
    participants: product-owner
    resources: issue-tracker
    input-artifacts: story
    output-artifacts: story
    outputs: 
      review:
        target: architectural-review
        name: "Yes"
        payload: story
        documentation:
          content-markdown:
            source:
              content-text: Architecturally significant stories need to go through an architectural review. 
      develop:
        target: develop/elements/code
        name: "No"
        payload: story                    
        documentation:
          content-markdown:
            source:
              content-text: Stories which are not architecturally significant go straight to development.
```