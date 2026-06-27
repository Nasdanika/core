package org.nasdanika.groovy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Test;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.ServiceCapabilityFactory.Requirement;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.PrintStreamProgressMonitor;
import org.nasdanika.common.ProgressMonitor;

/**
 * Exercises the full source -> transform pipeline in isolation: the .groovy source handler compiles
 * the script to a CompiledSource, then the same steps the .pm transform handler performs (build the
 * DSL, install entry points, eval, resolve deferred references) are run here, validating both
 * reference tiers. This lives in the groovy-dsl module so the Groovy runtime is on the classpath.
 */
public class DslTests {

    @Test
    public void testEcoreDslResource() throws IOException {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);
		ResourceSet resourceSet = capabilityLoader.loadOne(requirement, progressMonitor);
		
		File markdownFile = new File("src/test/resources/ecore.groovy").getCanonicalFile();
		Resource markdownResource = resourceSet.getResource(URI.createFileURI(markdownFile.getAbsolutePath()), true);
		
		EObject person = resourceSet.getEObject(URI.createURI("urn:test/Person"), true);
		assertNotNull(person, "Person not found");
		
		File ecoreFile = new File("target/ecore-groovy.xml").getCanonicalFile();
		Resource ecoreResource = resourceSet.createResource(URI.createFileURI(ecoreFile.getAbsolutePath()));
		ecoreResource.getContents().addAll(EcoreUtil.copyAll(markdownResource.getContents()));
		ecoreResource.save(null);
	}


}
