Specialization of File. 
Automatically inserts the package declaration at the top of the file.

Adds an import manager to the context so ``${{import/<fully qualified type name>}}`` tokens are replaced with short names where applicable and corresponding import declarations are added to the imports section below the package declaration.

``.java`` extension is optional for compilation units and is added automatically if not present.

Compilation units provide a native merger which overwrites Java constructs which have a clean/empty ``@generated`` tag in their javadoc comments and preserve Java constructs which don't have such a tag or which is
not empty/dirty, e.g. ``@generated NOT``.

In the code snippet below ``getPropertyDescriptors()`` method would be overwritten during generation because it has a "clean" ``@generated`` Javadoc tag.
``addFormatPropertyDescriptor()`` will not be overwritten because it has a "dirty" ``@generated NOT`` tag.

```java

/**
 * This returns the property descriptors for the adapted class.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
@Override
public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
	...
}

/**
 * This adds a property descriptor for the Format feature.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated NOT
 */
protected void addFormatPropertyDescriptor(Object object) {
	...
}

``` 

Configuration keys:

* ``format`` - boolean. If true (default) the compilation unit is formatted.