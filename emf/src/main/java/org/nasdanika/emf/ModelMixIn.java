package org.nasdanika.emf;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.capability.CapabilityProvider;
import org.nasdanika.capability.ServiceCapabilityFactory;
import org.nasdanika.capability.emf.ResourceSetRequirement;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.drawio.emf.SemanticDrawioFactory.PropertySource;
import org.nasdanika.drawio.emf.SemanticDrawioFactory.RepresentationElementFilter;
import org.nasdanika.emf.persistence.EObjectCapabilityFactory;
import org.nasdanika.emf.persistence.EObjectCapabilityFactory.EObjectRequirement;
import org.nasdanika.ncore.util.NcoreResourceSet;

import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParameterException;
import picocli.CommandLine.Spec;

/**
 * Mix-in for loading a model from a resource. Registers {@link DocLoadingDrawioResourceFactory} for .drawio and .png
 * @deprecated Use org.nasdanika.cli.ModelCommand and chain
 */
@Deprecated 
public class ModelMixIn {
		
	@Option(
		names = {"-f", "--file"},
		description = "Model parameter is a file path")
	private boolean isFile;
	
	@Spec CommandSpec spec;
			
	public EObject getEObject(String model, ProgressMonitor progressMonitor) throws FileNotFoundException {
		File currentDir = new File(".");
		URI modelURI;
		if (isFile) {
			File modelFile = new File(model);
			modelURI = URI.createFileURI(modelFile.getAbsolutePath()).appendFragment("/");				
		} else {
			URI baseURI = URI.createFileURI(currentDir.getAbsolutePath()).appendSegment("");
			modelURI = URI.createURI(model).resolve(baseURI);
		}
		
		NcoreResourceSet resourceSet = new NcoreResourceSet();
		SpecLoadingDrawioResourceFactory resourceFactory = new SpecLoadingDrawioResourceFactory(uri -> resourceSet.getEObject(uri, true)) {
			
			@Override
			protected void filterRepresentationElement(
					org.nasdanika.drawio.ModelElement representationElement, 
					EObject semanticElement,
					Map<EObject, EObject> registry,
					ProgressMonitor progressMonitor) {
				
				for (CommandSpec mixIn: spec.mixins().values()) {
					Object userObject = mixIn.userObject();
					if (userObject instanceof RepresentationElementFilter) {
						((RepresentationElementFilter) userObject).filterRepresentationElement(representationElement, semanticElement, registry, progressMonitor);						
					}
				}
			}
			
			@Override
			protected String getProperty(SpecLoadingDrawioResource drawioResource, String name) {
				for (CommandSpec mixIn: spec.mixins().values()) {
					Object userObject = mixIn.userObject();
					if (userObject instanceof PropertySource) {
						Optional<String> pOpt = ((PropertySource) userObject).getProperty(name, drawioResource.getURI());						
						if (pOpt != null) {
							return pOpt.orElse(null);
						}
					}
				}
				
				return super.getProperty(drawioResource, name);
			}
			
		};
		
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("drawio", resourceFactory);
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("png", resourceFactory);
		
		ResourceSetRequirement resourceSetRequirement = new ResourceSetRequirement(resourceSet, null, null);
		EObjectRequirement requirement = EObjectCapabilityFactory.createRequirement(modelURI, resourceSetRequirement);
		CapabilityLoader capabilityLoader = new CapabilityLoader();
		Iterable<CapabilityProvider<Object>> providers = capabilityLoader.load(ServiceCapabilityFactory.createRequirement(EObject.class, null, requirement), progressMonitor);
		Collection<EObject> results = Collections.synchronizedCollection(new ArrayList<>());
		for (CapabilityProvider<Object> provider: providers) {
			provider.getPublisher().subscribe(r -> results.add((EObject) r), error -> error.printStackTrace());
		}
		for (EObject result: results) {
			return result;
		}
		throw new ParameterException(spec.commandLine(), "Model not found for URI: " + modelURI);
	}

}
