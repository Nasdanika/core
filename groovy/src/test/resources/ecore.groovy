eObject('http://www.eclipse.org/emf/2002/Ecore#EPackage') {

	name "test"

	// 'global' is a real builder method, so 'global "<uri>"' always registers this element
	// as a global object via DslContext.global(Object, EObject) - it wins over feature/type
	// dispatch. If an EClass declared a 'global' feature, that feature would stay settable via
	// the assignment form 'global = <value>' (which routes through propertyMissing, not the method).
	// 'eObject' with a URI-qualified name resolves the EClass even when its simple name would
	// collide across packages in a resource set; the simple 'eClass { }' form still works too.
	def person = eObject('http://www.eclipse.org/emf/2002/Ecore#EClass') {

		name "Person"

		global "urn:test/Person"
	}

	// Registers the ePackage itself as a global object.
	global "urn:test"
	
	eClass {

        name "Address"

        global "urn:test/Address"

		// String selector: an EMF path anchored at the resource root ('/'). Each step is a URI-fragment
		// segment, so a classifier is matched by an attribute predicate, eClassifiers[name='Person'].
		eReference {

            name "residents"
            eType "/eClassifiers[name='Person']"
            upperBound -1

		}

		// Closure selector for a non-containment reference: instead of a relative path, navigate the
		// model directly. The closure is evaluated deferred (after the whole script is built) with the
		// reference element as its delegate, so eContainer() walks up to the EPackage. It may return an
		// EObject (as here) or a path String. See DslContext.resolveRelative(EObject, Object).
		eReference {

			name "primaryResident"
			eType { eContainer().eContainer().getEClassifier("Person") }

		}

		eReference {

			name "secondaryResident"
			eType person  // the variable defined above, which is an EObject

		}

    }
	
	eClassifiers('EClass') {
        name "Company"
        global "urn:test/Company"
    }

}
