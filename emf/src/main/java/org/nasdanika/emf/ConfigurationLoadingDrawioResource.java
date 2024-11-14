package org.nasdanika.emf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.nasdanika.capability.CapabilityLoader;
import org.nasdanika.common.Context;
import org.nasdanika.common.NullProgressMonitor;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Transformer;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Document;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.emf.AbstractDrawioFactory;
import org.nasdanika.drawio.emf.DrawioContentProvider;
import org.nasdanika.drawio.emf.DrawioResource;
import org.nasdanika.drawio.model.ModelFactory;
import org.nasdanika.mapping.ContentProvider;
import org.nasdanika.persistence.Marker;
import org.xml.sax.SAXException;

/**
 * Loads Drawio model using {@link DrawioResource} and then transforms it to a model.
 */
public class ConfigurationLoadingDrawioResource extends ResourceImpl {
		
	public static final String MAPPING_PROPERTY = "mapping";
	public static final String MAPPING_REF_PROPERTY = "mapping-ref";
	
	protected Function<URI,EObject> uriResolver;
	private CapabilityLoader capabilityLoader;
	
	public ConfigurationLoadingDrawioResource(
			CapabilityLoader capabilityLoader,
			Function<URI,EObject> uriResolver) {
		super();
		this.uriResolver = uriResolver;
		this.capabilityLoader = capabilityLoader;
	}

	public ConfigurationLoadingDrawioResource(
			URI uri,
			CapabilityLoader capabilityLoader,
			Function<URI,EObject> uriResolver) {
		super(uri);
		this.uriResolver = uriResolver;
		this.capabilityLoader = capabilityLoader;
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			Document document = Document.load(inputStream, getURI());			
			ConfigurationLoadingDrawioFactory<EObject> drawioFactory = new ConfigurationLoadingDrawioFactory<EObject>(createContentProvider(document), capabilityLoader, getResourceSet()) {
				
				@Override
				protected EObject getByRefId(Element obj, String refId, int pass, Map<Element, EObject> registry) {
					return ConfigurationLoadingDrawioResource.this.getByRefId(obj, getContentProvider().getBaseURI(obj),  refId, pass, registry);
				}
				
				@Override
				protected boolean isRefIdProxyURI() {
					return true;
				}
				
				@Override
				protected ClassLoader getClassLoader(Element context) {
					Supplier<ClassLoader> lpcs = () -> {
						Element ancestor = getContentProvider().getParent(context);
						if (ancestor == null) {
							return Thread.currentThread().getContextClassLoader();
						}
						
						return getClassLoader(ancestor);
					};
					return ConfigurationLoadingDrawioResource.this.getClassLoader(
							context,
							getContentProvider().getBaseURI(context),
							lpcs);
				}
				
				@Override
				protected URI getAppBase() {
					return ConfigurationLoadingDrawioResource.this.getAppBase();
				}
				
				@Override
				protected void filterRepresentationElement(
						Element element,
						Map<Element, EObject> registry, 
						ProgressMonitor progressMonitor) {
					super.filterRepresentationElement(element, registry, progressMonitor);
					ConfigurationLoadingDrawioResource.this.filterRepresentationElement(element, registry, progressMonitor);
				}
				
				@Override
				protected Map<String, Object> getVariables(Element context) {
					return ConfigurationLoadingDrawioResource.this.getVariables(context);
				}
				
			};
			
			Transformer<Element,EObject> modelFactory = new Transformer<>(drawioFactory);
			List<Element> documentElements = document.stream().filter(Element.class::isInstance).map(Element.class::cast).toList();
			Map<Element, EObject> modelElements = modelFactory.transform(documentElements, false, getProgressMonitor());
			EList<EObject> cnt = getContents();
			for (EObject modelElement: modelElements.values()) {
				if (modelElement != null && modelElement.eContainer() == null) {
					cnt.add(modelElement);
				}
			}
		} catch (IOException | ParserConfigurationException | SAXException e) {
			throw new IOException("Error loading Drawio document from " + getURI() + ": " + e, e);
		}
	}
	
	protected void filterRepresentationElement(
			Element element, 
			Map<Element, EObject> registry,
			ProgressMonitor progressMonitor) {
		
	}

	protected ProgressMonitor getProgressMonitor() {
		return new NullProgressMonitor();
	}

	protected Function<Marker, org.nasdanika.ncore.Marker> getMarkerFactory() {
		return null;
	}

	protected ModelFactory getDrawioFactory() {
		return org.nasdanika.drawio.model.ModelFactory.eINSTANCE;
	}	
	
	protected EObject getByRefId(Element element, URI baseURI, String refId, int pass, Map<Element, EObject> registry) {
		if (uriResolver == null) {
			return null;
		}
		
		URI refURI = URI.createURI(refId);
		if(!getURI().isRelative()) {
			if (baseURI == null) {
				refURI = refURI.resolve(getURI());
			} else {
				refURI = refURI.resolve(baseURI);
			}
		}
		return uriResolver.apply(refURI);
	}
	
	protected ClassLoader getClassLoader(Element context, URI baseURI, Supplier<ClassLoader> ancestorClassLoaderSupplier) {
		return ancestorClassLoaderSupplier == null ? Thread.currentThread().getContextClassLoader() : ancestorClassLoaderSupplier.get();
	}	

	protected URI getAppBase() {
		return AbstractDrawioFactory.DEFAULT_APP_BASE;
	}
	
	protected Map<String, Object> getVariables(Element context) {
		return Collections.emptyMap();
	}
	
	protected ContentProvider<Element> createContentProvider(Document document) {
		return new DrawioContentProvider(document, Context.BASE_URI_PROPERTY, MAPPING_PROPERTY, MAPPING_REF_PROPERTY, ConnectionBase.SOURCE);
	}

}
