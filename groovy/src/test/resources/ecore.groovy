ePackage {

	name "test"

	// 'global' is a real builder method, so 'global "<uri>"' always registers this element
	// as a global object via DslContext.global(Object, EObject) - it wins over feature/type
	// dispatch. If an EClass declared a 'global' feature, that feature would stay settable via
	// the assignment form 'global = <value>' (which routes through propertyMissing, not the method).
	eClass {

		name "Person"

		global "urn:test/Person"
	}

	// Registers the ePackage itself as a global object.
	global "urn:test"

}
