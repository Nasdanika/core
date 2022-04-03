A collection of [properties](Property.html) with keys and values.

### Examples

#### Flat

```yaml
exec-map:
  entries:
    greeting: 
      content-text: Hello
    addressee: 
      content-text: World    
```


#### Nested

```yaml
exec-map:
  entries:
    greeting: 
      content-text: And here's to you
    addressee:
      exec-map:
        entries:
          salutation: 
            content-text: Mrs.
          name:
            content-text: Robinson  
```
