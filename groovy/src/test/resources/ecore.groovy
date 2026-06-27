ePackage {

	name "test"

	// EClass has no 'global' feature, so the 'global' keyword registers this element
	// under the given URI via DslContext.global(String, EObject).
	eClass {

		name "Person"

		global "urn:test/Person"
	}

	// 'registerGlobal' is the always-available alias; it registers even on EClasses
	// that declare a 'global' feature (here it registers the ePackage itself).
	registerGlobal "urn:test"

}
