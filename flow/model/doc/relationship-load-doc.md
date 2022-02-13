An example of relationship YAML definitions:

```yaml
s3-bucket:
  name: Amazon S3 Bucket
  relationships:
    pipeline: code-pipeline
code-pipeline:
  name: AWS CodePipeline
  relationships:
    organizations: organizations
    account-baseline: account-baseline
    service-catalog: service-catalog
    parameter-store: parameter-store
```