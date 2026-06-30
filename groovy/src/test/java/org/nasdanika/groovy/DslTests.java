package org.nasdanika.groovy;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EClass;
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
		System.out.println("testEcoreDslResource");
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);
		ResourceSet resourceSet = capabilityLoader.loadOne(requirement, progressMonitor);
		
		File groovyFile = new File("src/test/resources/test-ecore.groovy").getCanonicalFile();
		Resource groovyResource = resourceSet.getResource(URI.createFileURI(groovyFile.getAbsolutePath()), true);
		
		EObject person = resourceSet.getEObject(URI.createURI("urn:test/Person"), true);
		((EClass) person).getEAllStructuralFeatures().forEach(f -> System.out.println("Feature: " + f.getName() + " " + groovyResource.getURIFragment(f)));
		assertNotNull(person, "Person not found");
		System.out.println("Person URI fragment: " + groovyResource.getURIFragment(person));
		
		File ecoreFile = new File("target/test-ecore.groovy.xml").getCanonicalFile();
		Resource ecoreResource = resourceSet.createResource(URI.createFileURI(ecoreFile.getAbsolutePath()));
		ecoreResource.getContents().addAll(EcoreUtil.copyAll(groovyResource.getContents()));
		ecoreResource.save(null);
	}

    @Test
    public void testQualifiedEcoreDslResource() throws IOException {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);
		ResourceSet resourceSet = capabilityLoader.loadOne(requirement, progressMonitor);
		
		File groovyFile = new File("src/test/resources/test.ecore.groovy").getCanonicalFile();
		Resource groovyResource = resourceSet.getResource(URI.createFileURI(groovyFile.getAbsolutePath()), true);
		
		EObject person = resourceSet.getEObject(URI.createURI("urn:test/Person"), true);
		assertNotNull(person, "Person not found");
		System.out.println("Person URI fragment: " + groovyResource.getURIFragment(person));
		
		File ecoreFile = new File("target/test.ecore.groovy.xml").getCanonicalFile();
		Resource ecoreResource = resourceSet.createResource(URI.createFileURI(ecoreFile.getAbsolutePath()));
		ecoreResource.getContents().addAll(EcoreUtil.copyAll(groovyResource.getContents()));
		ecoreResource.save(null);
	}

    @Test
    public void testManyEcoreDslResource() throws IOException {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);
		ResourceSet resourceSet = capabilityLoader.loadOne(requirement, progressMonitor);
		
		File groovyFile = new File("src/test/resources/test-many.ecore.groovy").getCanonicalFile();
		Resource groovyResource = resourceSet.getResource(URI.createFileURI(groovyFile.getAbsolutePath()), true);
		
		EObject person = resourceSet.getEObject(URI.createURI("urn:test/Person"), true);
		assertNotNull(person, "Person not found");
		System.out.println("Person URI fragment: " + groovyResource.getURIFragment(person));
		
		File ecoreFile = new File("target/test-many.ecore.groovy.xml").getCanonicalFile();
		Resource ecoreResource = resourceSet.createResource(URI.createFileURI(ecoreFile.getAbsolutePath()));
		ecoreResource.getContents().addAll(EcoreUtil.copyAll(groovyResource.getContents()));
		ecoreResource.save(null);
	}

    @Test
    public void testFamilyEcoreDslResource() throws IOException {
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		ProgressMonitor progressMonitor = new PrintStreamProgressMonitor();
		Requirement<ResourceSetRequirement, ResourceSet> requirement = ServiceCapabilityFactory.createRequirement(ResourceSet.class);
		ResourceSet resourceSet = capabilityLoader.loadOne(requirement, progressMonitor);
		
		File groovyFile = new File("src/test/resources/family.ecore.groovy").getCanonicalFile();
		Resource groovyResource = resourceSet.getResource(URI.createFileURI(groovyFile.getAbsolutePath()), true);
		
		File ecoreFile = new File("target/family.ecore.groovy.xml").getCanonicalFile();
		Resource ecoreResource = resourceSet.createResource(URI.createFileURI(ecoreFile.getAbsolutePath()));
		ecoreResource.getContents().addAll(EcoreUtil.copyAll(groovyResource.getContents()));
		ecoreResource.save(null);
	}

}
