Forces (casts/coerces) the configuration to be treated as a map even if the map has a single entry, which by default is treated as object definition ``<type>: <configuration>``. 

The below specification will not load because ``json`` map has a single entry ``name: icon.gif``. Because the map has a single entry it is treated as an object definition with ``name`` type and ``icon.gif`` configuration. 

```yaml
json:
    name: icon.gif                
```

This specification addresses the problem when a map with a single entry is required. It uses ``(map)`` type which treats its configuration as a map even if it has a single entry.

```yaml
json:
    (map):
        name: icon.gif                
```