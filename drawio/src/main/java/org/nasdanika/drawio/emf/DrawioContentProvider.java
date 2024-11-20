package org.nasdanika.drawio.emf;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

import org.eclipse.emf.common.util.URI;
import org.jsoup.Jsoup;
import org.nasdanika.common.Context;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.PropertySource;
import org.nasdanika.drawio.Connection;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Node;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.Root;
import org.nasdanika.drawio.Util;
import org.nasdanika.mapping.ContentProvider;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marked;
import org.yaml.snakeyaml.Yaml;

public class DrawioContentProvider implements ContentProvider<Element> {
	
	private static final String CONFIG_PROPERTY = "config";

	private static final String CONFIG_REF_PROPERTY = "config-ref";

	/**
	 * To resolve links
	 */
	private Collection<Element> elements;
	
	private Map<LinkTarget, Collection<ModelElement>> linkSoureMap = new ConcurrentHashMap<>();
	
	private Map<Element, PropertySource<String,Object>> propertySources = new ConcurrentHashMap<>();	

	private String baseURIProperty;
	private String configProperty;
	private String configRefProperty;
//	private ConnectionBase connectionBase;

	public DrawioContentProvider(Element root) {
		this(root, Context.BASE_URI_PROPERTY, CONFIG_PROPERTY, CONFIG_REF_PROPERTY, ConnectionBase.SOURCE);
	}

	/**
	 * Traverses the root content to build a list of elements for link resolution.
	 * @param root
	 */
	public DrawioContentProvider(
			Element root, 
			String baseURIProperty,
			String configProperty,
			String configRefProperty,
			ConnectionBase connectionBase) {
		elements = new ArrayList<>();
		if (root != null) {
			Consumer<Element> visitor = element -> {
				elements.add(element);
				if (element instanceof ModelElement) {
					ModelElement linkSource = (ModelElement) element;
					LinkTarget linkTarget = linkSource.getLinkTarget();
					if (linkTarget != null) {
						linkSoureMap.computeIfAbsent(linkTarget, lt -> new ArrayList<ModelElement>()).add(linkSource);
					}
				}
			};
			root.accept(Util.withLinkTargets(visitor, connectionBase), connectionBase);
		}
		this.baseURIProperty = baseURIProperty;
		this.configProperty = configProperty;
		this.configRefProperty = configRefProperty;
//		this.connectionBase = connectionBase;
	}
	
	public DrawioContentProvider(Collection<Element> elements) {
		this(elements, Context.BASE_URI_PROPERTY, CONFIG_PROPERTY, CONFIG_REF_PROPERTY, ConnectionBase.SOURCE);
	}

	/**
	 * 
	 * @param elements To resolve links
	 */
	public DrawioContentProvider(
			Collection<Element> elements, 
			String baseURIProperty,
			String configProperty,
			String configRefProperty,
			ConnectionBase connectionBase) {
		this.elements = elements;
		this.baseURIProperty = baseURIProperty;
		this.configProperty = configProperty;
		this.configRefProperty = configRefProperty;
//		this.connectionBase = connectionBase;		
		for (Element element: elements) {
			if (element instanceof ModelElement) {
				ModelElement linkSource = (ModelElement) element;
				LinkTarget linkTarget = linkSource.getLinkTarget();
				if (linkTarget != null) {
					linkSoureMap.computeIfAbsent(linkTarget, lt -> new ArrayList<ModelElement>()).add(linkSource);
				}
			}
		}		
	}
	
	@Override
	public Element getParent(Element element) {
		return getParent(element, new HashSet<Element>()::add);
	}

	private Element getParent(Element element, Predicate<Element> linkTracker) {
		// First linker it the logical parent
		if (element instanceof LinkTarget) {
			Collection<ModelElement> sources = linkSoureMap.computeIfAbsent((LinkTarget) element, e -> new ArrayList<ModelElement>());
			if (!sources.isEmpty()) {
				ModelElement result = sources.iterator().next();
				return linkTracker.test(result) ? result : null; // Avoiding infinite loops.
			}
		}
		 // Logically merging Root, Model, and Page
		if (element instanceof Root) {
			return getParent(((Root) element).getModel().getPage(), linkTracker);
		}
		if (element instanceof Model) {
			return getParent(((Model) element).getPage(), linkTracker);			
		}
		if (element instanceof Page) {
			return ((Page) element).getDocument();
		}
//		if (element instanceof Connection) {
//			if (connectionBase == ConnectionBase.SOURCE) {
//				return ((Connection) element).getSource();
//			} 
//			
//			if (connectionBase == ConnectionBase.TARGET) {
//				return ((Connection) element).getTarget();
//			}
//		}
		
		if (element instanceof ModelElement) {
			return ((ModelElement) element).getParent();
		}
		return null;
	}

	@Override
	public Collection<? extends Element> getChildren(Element element) {
		if (element instanceof ModelElement) {
			ModelElement modelElement = (ModelElement) element;
			List<Element> children = new ArrayList<>(modelElement.getChildren());
			LinkTarget linkTarget = modelElement.getLinkTarget();
			if (linkTarget != null) {
				children.add(linkTarget);
			}
//			if (modelElement instanceof Node) {
//				if (connectionBase == ConnectionBase.SOURCE) {
//					children.addAll(((Node) modelElement).getOutgoingConnections());
//				} else if (connectionBase == ConnectionBase.TARGET) {
//					children.addAll(((Node) modelElement).getIncomingConnections());
//				}
//			}
			return children;
		}
		if (element instanceof Page) {
			return getChildren(((Page) element).getModel().getRoot());
		}
		if (element instanceof Model) {
			return getChildren(((Model) element).getRoot());
		}
		
		// Document
		return element == null ? Collections.emptyList() : element.getChildren();
	}

	@Override
	public URI getBaseURI(Element element) {
		if (element == null) {
			return null;
		}
		
		if (element instanceof ModelElement && !org.nasdanika.common.Util.isBlank(baseURIProperty)) {
			String baseURIStr = ((ModelElement) element).getProperty(baseURIProperty);
			if (!org.nasdanika.common.Util.isBlank(baseURIStr)) {
				URI baseURI = URI.createURI(baseURIStr);
				if (baseURI.isRelative()) {
					URI resolveBase = element.getURI();
					Element parent = getParent(element);
					if (parent != null) {
						URI parentBaseURI = getBaseURI(parent);
						if (parentBaseURI != null && !parentBaseURI.isRelative()) {
							resolveBase = parentBaseURI;
						}
					}
					if (resolveBase == null || resolveBase.isRelative()) {
						return baseURI;
					}
					return baseURI.resolve(resolveBase);
				}
			}
		}
		
		return element.getURI();
	}

	@Override
	public Object getProperty(Element element, String property) {
		if (element instanceof Page) {
			return getProperty(((Page) element).getModel().getRoot(), property);
		}
		if (element instanceof Model) {
			return getProperty(((Model) element).getRoot(), property);
		}
						
		return propertySources.computeIfAbsent(element, this::createElementPropertySource).getProperty(property);
	}

	@SuppressWarnings("unchecked")
	private PropertySource<String,Object> createElementPropertySource(Element element) {
		if (element instanceof ModelElement) {
			ModelElement modelElement = (ModelElement) element;
			if (org.nasdanika.common.Util.isBlank(configProperty) && org.nasdanika.common.Util.isBlank(configRefProperty)) {				
				// Using element's own properties
				return modelElement::getProperty;

			}
			
			if (!org.nasdanika.common.Util.isBlank(configProperty)) {
				// Loading YAML from config property
				String configStr = modelElement.getProperty(configProperty);
				if (!org.nasdanika.common.Util.isBlank(configStr)) {
					Yaml yaml = new Yaml();
					Object config = yaml.load(configStr);
					if (config instanceof Map) {
						return ((Map<String,Object>) config)::get;
					}
					throw new ConfigurationException("Usupported configuration type: " + configStr, null, asMarked(element));
				}
			}
			
			if (!org.nasdanika.common.Util.isBlank(configRefProperty)) {
				// Loading YAML
				String ref = modelElement.getProperty(configRefProperty);
				if (!org.nasdanika.common.Util.isBlank(ref)) {
					URI refURI = URI.createURI(ref);
					URI baseURI = getBaseURI(element);
					if (baseURI != null && !baseURI.isRelative()) {
						refURI = refURI.resolve(baseURI);
					}
					try {
						DefaultConverter converter = DefaultConverter.INSTANCE;
						Reader reader = converter.toReader(refURI);
						String configStr = converter.toString(reader);
						Object config = new Yaml().load(configStr);
						if (config instanceof Map) {
							return ((Map<String,Object>) config)::get;
						}
						throw new ConfigurationException("Usupported configuration type: " + configStr, null, asMarked(element));								
					} catch (IOException e) {
						throw new ConfigurationException("Error loading source from " + refURI, e, asMarked(element));
					}
				}
			}
		}
		
		// Empty property source
		return property -> null;		
	}		

	@Override
	public Marked asMarked(Element element) {
		return element;
	}

	@Override
	public Element getConnectionSource(Element element) {
		return element instanceof Connection ? ((Connection) element).getSource() : null;
	}

	@Override
	public Element getConnectionTarget(Element element) {
		return element instanceof Connection ? ((Connection) element).getTarget() : null;
	}

	@Override
	public String getName(Element element) {
		if (element instanceof Page) {
			return ((Page) element).getName();
		}
		if (element instanceof Model) {
			return ((Model) element).getPage().getName();
		}
		if (element instanceof Root) {
			return ((Root) element).getModel().getPage().getName();
		}
		if (element instanceof ModelElement) {
			String label = ((ModelElement) element).getLabel();
			return org.nasdanika.common.Util.isBlank(label) ? null : Jsoup.parse(label).text();
		}

		return null;
	}

	@Override
	public String getDescription(Element element) {
		if (element instanceof Page) {
			return ((Page) element).getModel().getRoot().getTooltip();
		}
		if (element instanceof Model) {
			return ((Model) element).getRoot().getTooltip();
		}
		return element instanceof ModelElement ? ((ModelElement) element).getTooltip() : null;
	}

	@Override
	public Object getIdentity(Element element) {
		if (element instanceof Page) {
			return ((Page) element).getId();
		}
		if (element instanceof Model) {
			return ((Model) element).getPage().getId();
		}
		if (element instanceof Root) {
			return ((Root) element).getModel().getPage().getId();
		}
		return element instanceof ModelElement ? ((ModelElement) element).getId() : null;
	}

}
