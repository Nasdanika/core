package org.nasdanika.emf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.nasdanika.mapping.AbstractMappingFactory.Contributor;
import org.nasdanika.mapping.ContentProvider;
import org.nasdanika.mapping.MappingAdapter;
import org.nasdanika.persistence.Marker;
import org.xml.sax.SAXException;

/**
 * Loads Drawio model using {@link DrawioResource} and then transforms it to a model.
 */
public class ConfigurationLoadingDrawioResource extends ResourceImpl {
		
	public static final String MAPPING_PROPERTY = "mapping";
	public static final String MAPPING_REF_PROPERTY = "mapping-ref";
	
	protected ConnectionBase connectionBase = ConnectionBase.SOURCE;	
	
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
	
	protected Document document;
	protected ChangeRecorder changeRecorder = new ChangeRecorder(this);

	@SuppressWarnings("unchecked")
	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		try {
			document = Document.load(inputStream, getURI());		
			eAdapters().add(new MappingAdapter(document));
			ConfigurationLoadingDrawioFactory<EObject> drawioFactory = new ConfigurationLoadingDrawioFactory<EObject>(createContentProvider(document), capabilityLoader, getResourceSet(), getProgressMonitor()) {
				
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
				
				@Override
				protected org.nasdanika.ncore.Marker createTargetMarker(
						Marker sourceMarker,
						ProgressMonitor progressMonitor) {
					return ConfigurationLoadingDrawioResource.this.createTargetMarker(sourceMarker, progressMonitor);
				}
				
			};
			
			ResourceSet rs = getResourceSet();
			if (rs != null) {
				for (Adapter adapter: rs.eAdapters()) {
					if (adapter instanceof Contributor) {
						drawioFactory.getContributors().add((Contributor<Element, EObject>) adapter);
					}
				}
			}
			
			Transformer<Element,EObject> modelFactory = new Transformer<>(drawioFactory);
			List<Element> documentElements = new ArrayList<>();
			Consumer<Element> visitor = documentElements::add;
			@SuppressWarnings("rawtypes")
			Consumer<org.nasdanika.graph.Element> traverser = (Consumer) org.nasdanika.drawio.Util.traverser(visitor, connectionBase);
			document.accept(traverser);
			
			Map<Element, EObject> modelElements = modelFactory.transform(documentElements, false, getProgressMonitor());
			
			EList<EObject> cnt = getContents();
			modelElements.values()
				.stream()
				.distinct()
				.filter(modelElement -> modelElement != null && modelElement.eContainer() == null)
				.forEach(cnt::add);			
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
		return new DrawioContentProvider(
				document, 
				Context.BASE_URI_PROPERTY, 
				MAPPING_PROPERTY, 
				MAPPING_REF_PROPERTY, 
				connectionBase);
	}
				
	protected org.nasdanika.ncore.Marker createTargetMarker(
			org.nasdanika.persistence.Marker sourceMarker, 
			ProgressMonitor progressMonitor) {
		
		if (sourceMarker == null) {
			return null;
		}
		org.nasdanika.ncore.Marker ret =  org.nasdanika.ncore.NcoreFactory.eINSTANCE.createMarker();
		ret.setDate(new Date());
		ret.setLocation(sourceMarker.getLocation());
		ret.setPosition(sourceMarker.getPosition());		
		return ret;
	}	
	
	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		if (document == null) {
			try {
				document = Document.create(true, getURI());
			} catch (ParserConfigurationException e) {
				throw new IOException("Could not create document", e);
			}
		}
		
		ChangeDescription changeDescription = changeRecorder.endRecording();
		if (changeDescription != null) {
			ChangeDescriptionApplier changeDescriptionApplier = options == null ? null : (ChangeDescriptionApplier) options.get(ChangeDescriptionApplier.class);
			if (changeDescriptionApplier == null) {
				changeDescriptionApplier = (ChangeDescriptionApplier) EcoreUtil.getRegisteredAdapter(this, ChangeDescriptionApplier.class);
			}
			if (changeDescriptionApplier == null) {
				throw new IOException("Unable to apply change description to the source document - there is no adapter for " + ChangeDescriptionApplier.class);
			}			
			changeDescriptionApplier.apply(changeDescription, document);
		}
		
		try (Writer writer = new OutputStreamWriter(outputStream)) {
			String docStr = document.save(null);
			writer.write(docStr);
		} catch (TransformerException | IOException e) {
			throw new IOException("Could not save document", e);
		}
		
		changeRecorder.dispose();
		changeRecorder = new ChangeRecorder(this);
	}

}
