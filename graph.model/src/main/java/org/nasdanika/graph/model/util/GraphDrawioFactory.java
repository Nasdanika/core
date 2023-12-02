package org.nasdanika.graph.model.util;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.model.Document;
import org.nasdanika.drawio.model.ModelElement;
import org.nasdanika.drawio.model.util.AbstractDrawioFactory;
import org.nasdanika.emf.persistence.EObjectLoader;
import org.nasdanika.exec.content.ContentFactory;
import org.nasdanika.exec.content.Interpolator;
import org.nasdanika.exec.content.Markdown;
import org.nasdanika.exec.content.Resource;
import org.nasdanika.exec.content.Text;
import org.nasdanika.graph.model.DocumentedNamedGraph;
import org.nasdanika.graph.model.Graph;
import org.nasdanika.graph.model.GraphElement;
import org.nasdanika.graph.model.ModelFactory;
import org.nasdanika.graph.model.ModelPackage;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.ObjectLoader;

/**
 * Factory for mapping drawio model to graph model
 * @param <G>
 * @param <E>
 */
public class GraphDrawioFactory<G extends Graph<?>, E extends EObject> extends AbstractDrawioFactory<G, E> {

	/**
	 * Returns a map with graph and ncore entries.
	 */
	@Override
	protected Map<String, EPackage> getEPackages() {
		Map<String, EPackage> ret = new LinkedHashMap<>();
		ret.put("graph", ModelPackage.eINSTANCE);
		ret.put("ncore", NcorePackage.eINSTANCE);
		return ret;
	}

	/**
	 * Creates an instance of {@link DocumentedNamedGraph}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected G createDocumentElement(
			Document document,
			BiConsumer<EObject, BiConsumer<EObject, ProgressMonitor>> elementProvider,
			Consumer<BiConsumer<Map<EObject, EObject>, ProgressMonitor>> registry, 
			ProgressMonitor progressMonitor) {
		return (G) ModelFactory.eINSTANCE.createDocumentedNamedGraph();
	}

	@Override
	protected E getByRefId(String refId, int pass, Map<EObject, EObject> registry) {
		String[] segments = refId.split("/");
		if (segments.length > pass) {
			return null; // Might not have been resolved yet
		}
		Optional<EObject> deo = registry
			.entrySet()
			.stream()
			.filter(re -> re.getKey() instanceof Document)
			.map(re -> re.getValue())
			.findFirst();
		
		if (deo.isPresent()) {
			@SuppressWarnings("unchecked")
			G graph = (G) deo.get();
			return find(graph, segments, 0);
		}
			
		throw new IllegalArgumentException("Document graph not found in the registry");
	}
	
	@SuppressWarnings("unchecked")
	private E find(Graph<?> graph, String[] path, int offset) {
		for (GraphElement graphElement: graph.getElements()) {
			if (path[offset].equals(graphElement.getId())) {
				if (offset == path.length - 1) {
					return (E) graphElement;
				}
				if (graphElement instanceof Graph) {
					return find((Graph<?>) graphElement, path, offset + 1);
				}
				throw new IllegalArgumentException("Element not found at path " + Arrays.toString(path) + " " + graphElement + " is not an instance of Graph at offset " + offset);				
			}
		}
		throw new IllegalArgumentException("Element not found at path " + Arrays.toString(path));
	}

	@Override
	protected EObject createHtmlDoc(String doc, URI baseUri, ProgressMonitor progressMonitor) {
		Text text = ContentFactory.eINSTANCE.createText(); // Interpolate with element properties?
		text.setContent(doc);
		return text;
	}

	@Override
	protected EObject createTextDoc(String doc, URI baseUri,  ProgressMonitor progressMonitor) {
		return createHtmlDoc("<PRE>" + System.lineSeparator() + doc + System.lineSeparator() + "</PRE>", baseUri, progressMonitor);
	}

	@Override
	protected EObject createMarkdownDoc(String doc, URI baseUri, ProgressMonitor progressMonitor) {
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		Text text = ContentFactory.eINSTANCE.createText(); // Interpolate with element properties?
		text.setContent(doc);
		interpolator.setSource(text);
		ret.setSource(interpolator);
		ret.setStyle(true);
		
		// Creating a marker with EObject resource location for resource resolution in Markdown
//		if (location != null) {
//			org.nasdanika.ncore.Marker marker = context.get(MarkerFactory.class, MarkerFactory.INSTANCE).createMarker(location.toString(), progressMonitor);
//			ret.getMarkers().add(marker); 
//		}
		
		return ret;
	}

	@Override
	protected EObject createHtmlDoc(URI docRef,  ProgressMonitor progressMonitor) {
		Resource ret = ContentFactory.eINSTANCE.createResource();
		ret.setLocation(docRef.toString());
		return ret;
	}

	@Override
	protected EObject createTextDoc(URI docRef,  ProgressMonitor progressMonitor) {		
		throw new UnsupportedOperationException();
	}

	@Override
	protected EObject createMarkdownDoc(URI docRef,  ProgressMonitor progressMonitor) {
		Markdown ret = ContentFactory.eINSTANCE.createMarkdown();
		Interpolator interpolator = ContentFactory.eINSTANCE.createInterpolator();
		Resource resource = ContentFactory.eINSTANCE.createResource();
		resource.setLocation(docRef.toString());
		interpolator.setSource(resource);
		ret.setSource(interpolator);
		ret.setStyle(true);
		return ret;
	}
	
	protected String getSpecPropertyName() {
		return getPropertyNamespace() + "spec";
	}
	
	protected String getSpecRefPropertyName() {
		return getPropertyNamespace() + "spec-ref";
	}
	
	@Override
	protected E configureSemanticElement(
			ModelElement modelElement, 
			E semanticElement,
			BiConsumer<EObject, BiConsumer<EObject, ProgressMonitor>> elementProvider,
			Consumer<BiConsumer<Map<EObject, EObject>, ProgressMonitor>> registry, 
			ProgressMonitor progressMonitor) {
		
		EObjectLoader eObjectLoader = new EObjectLoader((ObjectLoader) null) {
		
			@Override
			public ResolutionResult resolveEClass(String type) {
				EClass eClass = (EClass) GraphDrawioFactory.this.getType(type, modelElement);
				return new ResolutionResult(eClass, null);
			}
			
		};
		
		URI baseURI = getBaseURI(modelElement);		
		String spn = getSpecPropertyName();
		if (!Util.isBlank(spn)) {
			String specStr = modelElement.getProperties().get(spn);
			if (!Util.isBlank(specStr)) {
				eObjectLoader.loadYaml(
						specStr, 
						semanticElement, 
						baseURI, 
						null, 
						modelElement.getMarkers(), 
						progressMonitor);
			}			
		}
		
		String srpn = getSpecRefPropertyName();
		if (!Util.isBlank(srpn)) {
			String ref = modelElement.getProperties().get(srpn);
			if (!Util.isBlank(ref)) {
				URI refURI = URI.createURI(ref);
				if (baseURI != null && !baseURI.isRelative()) {
					refURI = refURI.resolve(baseURI);
				}				
				try {
					eObjectLoader.loadYaml(
							new URL(refURI.toString()),
							semanticElement, 
							null, 
							progressMonitor);
				} catch (Exception e) {
					throw new ConfigurationException("Error loading spec from " + refURI, e, modelElement);
				}
			}
		}
		
		return super.configureSemanticElement(
				modelElement,
				semanticElement,
				elementProvider,
				registry,
				progressMonitor);
	}
	
}
