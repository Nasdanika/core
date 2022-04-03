A list of objects.

### Examples

#### Flat

```yaml
exec-list:
  elements:
    - content-text: Hello
    - content-text: World    
```


#### Nested

```yaml
exec-list:
  elements:
    - content-text: Hello
    - exec-list: 
        elements:
          content-text: World    
```
