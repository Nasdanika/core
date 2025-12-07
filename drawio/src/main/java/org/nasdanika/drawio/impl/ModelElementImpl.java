package org.nasdanika.drawio.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.jsoup.Jsoup;
import org.nasdanika.common.AbstractSplitJoinSet;
import org.nasdanika.common.DelimitedStringMap;
import org.nasdanika.common.Realm;
import org.nasdanika.common.Util;
import org.nasdanika.drawio.ConnectionBase;
import org.nasdanika.drawio.Element;
import org.nasdanika.drawio.LinkTarget;
import org.nasdanika.drawio.Model;
import org.nasdanika.drawio.ModelElement;
import org.nasdanika.drawio.Page;
import org.nasdanika.drawio.SpelContext;
import org.nasdanika.drawio.Tag;
import org.nasdanika.drawio.Document.Context;
import org.nasdanika.persistence.ConfigurationException;
import org.nasdanika.persistence.Marker;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.SAXException;

class ModelElementImpl<M extends ModelElement<M>> extends ElementImpl<M> implements ModelElement<M> {
	
	private static final String SPEL_PREFIX = "$spel:";
	private static final String STYLE_PREFIX = "$style:";
	private static final String GEOMETRY_ROLE = "geometry";
	static final String AS_ATTRIBUTE = "as";
	static final String MX_GEOMETRY_ATTRIBUTE = "mxGeometry";

	private static final String MX_CELL_TAG_NAME = "mxCell";
	
	private static final String ID_PREFIX = "id,";
	private static final String NAME_PREFIX = "name,";	

	private static final String PAGE_LINK_ID_PREFIX = "data:page/" + ID_PREFIX;
	private static final String PAGE_LINK_NAME_PREFIX = "data:page/" + NAME_PREFIX;
			
	private static final String ELEMENT_LINK_ID_PREFIX = "data:element/" + ID_PREFIX;
	private static final String ELEMENT_LINK_NAME_PREFIX = "data:element/" + NAME_PREFIX;
	
	private static final String SPEL_LINK_PREFIX = "data:spel,";	
	
	private static final String ATTRIBUTE_TAGS = "tags";
	static final String ATTRIBUTE_VALUE = "value";
	static final String ATTRIBUTE_PARENT = "parent";
	static final String ATTRIBUTE_LABEL = "label";
	static final String ATTRIBUTE_LINK = "link";
	static final String ATTRIBUTE_TOOLTIP = "tooltip";
	static final String ATTRIBUTE_TARGET = "target";
	static final String ATTRIBUTE_SOURCE = "source";
	static final String ATTRIBUTE_STYLE = "style";
	static final String ATTRIBUTE_VISIBLE = "visible";	

	private static final String ENUMERATE_STYLE_KEY = "enumerate";
	private static final String ENUMERATE_VALUE_STYLE_KEY = "enumerateValue";
	
	/**
	 * For element resolution.
	 */
	protected ModelImpl model;
	private int position;
	private Context context;

	/**
	 * @param element XML element for this page element
	 * @param rootElement root element containing all model elements to use for lookup of parent and children.
	 */
	ModelElementImpl(
			org.w3c.dom.Element element, 
			ModelImpl model, 
			int position,
			Context context) {
		this.element = element;
		this.model = model;
		this.position = position;
		this.context = context;
	}

	@Override
	public ModelElement<?> getParent() {
		if (getCellElement().hasAttribute(ATTRIBUTE_PARENT)) {	
			return model.find(getCellElement().getAttribute(ATTRIBUTE_PARENT));
		}
		return null;
	}
	
	@Override
	public List<? extends Element<?>> getChildren() {
		if (element.hasAttribute(ATTRIBUTE_ID)) {
			return model.collect(e -> ModelElementImpl.getCellElement(e).hasAttribute(ATTRIBUTE_PARENT) && ModelElementImpl.getCellElement(e).getAttribute(ATTRIBUTE_PARENT).equals(element.getAttribute(ATTRIBUTE_ID)));
		}
		return Collections.emptyList();
	}
	
	protected org.w3c.dom.Element getCellElement() {
		return getCellElement(element);
	}	
	
	@Override
	public String getId() {
		return getElement().hasAttribute(ATTRIBUTE_ID) ? getElement().getAttribute(ATTRIBUTE_ID) : null;
	}
	
	@Override
	public void setId(String id) {
		getElement().setAttribute(ATTRIBUTE_ID, id);		
	}		
	
	static org.w3c.dom.Element getCellElement(org.w3c.dom.Element element) {
		return MX_CELL_TAG_NAME.equals(element.getTagName()) ? element : DocumentImpl.getChildrenElements(element, MX_CELL_TAG_NAME).get(0);
	}
	
	/**
	 * Wraps mxCell into object element if not already
	 * @return
	 */
	protected org.w3c.dom.Element asObjectElement() {
		if (isMxCell()) {
			org.w3c.dom.Element objectElement = element.getOwnerDocument().createElement("object");
			element.getParentNode().replaceChild(objectElement, element);
			org.w3c.dom.Element cellElement = element;
			element = objectElement;
			objectElement.appendChild(cellElement);
			if (cellElement.hasAttribute(ATTRIBUTE_VALUE)) {
				element.setAttribute(ATTRIBUTE_LABEL, cellElement.getAttribute(ATTRIBUTE_VALUE));
				cellElement.removeAttribute(ATTRIBUTE_VALUE);
			}
			if (cellElement.hasAttribute(ATTRIBUTE_ID)) {
				element.setAttribute(ATTRIBUTE_ID, cellElement.getAttribute(ATTRIBUTE_ID));
				cellElement.removeAttribute(ATTRIBUTE_ID);
			}
		}
		return element;
	}

	@Override
	public void setLabel(String label) {
		element.setAttribute(isMxCell() ? ATTRIBUTE_VALUE : ATTRIBUTE_LABEL, label);
	}

	@Override
	public String getLink() {
		return getProperty(ATTRIBUTE_LINK);
	}

	@Override
	public void setLink(String link) {
		setProperty(ATTRIBUTE_LINK, link);
	}

	@Override
	public String getTooltip() {
		return getProperty(ATTRIBUTE_TOOLTIP);
	}

	@Override
	public void setTooltip(String tooltip) {
		setProperty(ATTRIBUTE_TOOLTIP, tooltip);
	}

	@Override
	public Map<String, String> getStyle() {
		return new DelimitedStringMap(";", "=") {

			@Override
			protected String getState() {
				return getCellElement().getAttribute(ATTRIBUTE_STYLE);
			}

			@Override
			protected void setState(String state) {
				getCellElement().setAttribute(ATTRIBUTE_STYLE, state);
			}
			
		};
	}
	
	@Override
	public String getLabel() {
		return interpolate(isMxCell() ? element.getAttribute(ATTRIBUTE_VALUE) : element.getAttribute(ATTRIBUTE_LABEL), new HashSet<>());
	}

	/**
	 * Unexpanded property.
	 * @param name
	 * @return
	 */
	private String getRawProperty(String name) {
		return isMxCell() ? null : getElement().getAttribute(name);
	}

	protected boolean isMxCell() {
		return MX_CELL_TAG_NAME.equals(element.getTagName());
	}
	
	private String interpolate(String str, Set<String> expanded) {		
		if (!Util.isBlank(str) && "1".equals(getRawProperty("placeholders"))) {
			StringBuilder retBuilder = new StringBuilder();
			int i = 0;
			int start;
			while (i < str.length() &&  (start = str.indexOf("%", i)) != -1) {
				int end = str.indexOf("%", start + 1);
				if (end == -1) {
					break; 
				}

				String pValue;
				String pName = str.substring(start + 1, end);
				if (pName.startsWith(STYLE_PREFIX)) {
					String styleKey = pName.substring(STYLE_PREFIX.length());
					pValue = Util.isBlank(styleKey) ? null : getStyle().get(styleKey);
				} else if (pName.startsWith(SPEL_PREFIX)) {
					String expr = pName.substring(SPEL_PREFIX.length());
					if (Util.isBlank(expr)) {
						pValue = null;
					} else {
						try {
							if (context instanceof SpelContext) {
								pValue = ((SpelContext) context).evaluate(this, expr, String.class);
							} else {
								pValue = evaluate(expr, String.class);
							}
						} catch (Exception ex) {
							pValue = "Error evaluating '" + expr + "': " + ex;
						}
					}
				} else {				
					pValue = getRawProperty(pName);
				}
				if (expanded.add(pName)) {
					pValue = interpolate(pValue, expanded);
				}
				if (Util.isBlank(pValue)) {
					pValue = getInheritedProperty(getParent(), pName);
				}
				
				if (Util.isBlank(pValue)) {
					retBuilder.append(str.substring(i, end));
					i = end;
				} else {
					retBuilder.append(str.substring(i, start)).append(pValue);
					i = end + 1;
				}
			}
			retBuilder.append(str.substring(i));
			return retBuilder.toString();
		}

		return str;
	}
	
	private String getInheritedProperty(ModelElement<?> modelElement, String name) {
		if (modelElement == null) {
			Function<String, String> propertySource = ((DocumentImpl) getModel().getPage().getDocument())::getProperty;
			return propertySource == null ? null : propertySource.apply(name);
		}
		String val = modelElement.getProperty(name);
		if (!Util.isBlank(val)) {
			return val;
		}
		return getInheritedProperty(modelElement.getParent(), name);
	}

	@Override
	public String getProperty(String name) {
		String rawProperty = context.filterProperty(this, name, getRawProperty(name));
		return interpolate(rawProperty, new HashSet<>());
	}
	
	@Override
	public void setProperty(String name, String value) {
		if (Util.isBlank(value)) {
			asObjectElement().removeAttribute(name);			
		} else {
			asObjectElement().setAttribute(name, value);
		}
	}

	@Override
	public Set<Tag> getTags() {
		return new AbstractSplitJoinSet<String,String,Tag>() {

			@Override
			protected String getState() {
				return getProperty(ATTRIBUTE_TAGS);
			}

			@Override
			protected void setState(String state) {
				setProperty(ATTRIBUTE_TAGS, state);
			}

			@Override
			protected List<String> split(String state) {
				if (Util.isBlank(state)) {
					return Collections.emptyList();
				}
				return Arrays.asList(state.split(" "));
			}

			@Override			
			protected String join(List<String> chunks) {
				if (chunks == null || chunks.isEmpty()) {
					return null;
				}
				return chunks.stream().filter(e -> !Util.isBlank(e)).collect(Collectors.joining(" "));
			}

			@Override
			protected Tag load(String chunk) {
				return new TagImpl(chunk, getModel().getPage());
			}

			@Override
			protected String store(Tag element) {
				return element.getName();
			}
			
		};
	}

	@Override
	public boolean isVisible() {
		org.w3c.dom.Element cellElement = getCellElement(getElement());
		return !cellElement.hasAttribute(ATTRIBUTE_VISIBLE) || "1".equals(cellElement.getAttribute(ATTRIBUTE_VISIBLE));
	}

	@Override
	public void setVisible(boolean visible) {
		org.w3c.dom.Element cellElement = getCellElement(getElement());
		if (visible) {
			cellElement.removeAttribute(ATTRIBUTE_VISIBLE);
		} else {
			cellElement.setAttribute(ATTRIBUTE_VISIBLE, "0");
		}		
	}
	
	public <T> T resolve(T base, BiFunction<? super ModelElement<?>,T,T> resolver, ConnectionBase connectionBase) {
		ModelElement<?> logicalParent = getLogicalParent(connectionBase);
		return resolver.apply(this, logicalParent == null ? base : logicalParent.resolve(base, resolver, connectionBase)); 
	}
	
	protected ModelElement<?> getLogicalParent(ConnectionBase connectionBase) {
		return getParent();
	}

	@Override
	public Model getModel() {
		return model;
	}
	
	@Override
	public boolean isTargetLink() {
		String link = getLink();
		return !Util.isBlank(link) && (
				link.startsWith(PAGE_LINK_ID_PREFIX)
				|| link.startsWith(PAGE_LINK_NAME_PREFIX)
				|| link.startsWith(ELEMENT_LINK_ID_PREFIX)
				|| link.startsWith(ELEMENT_LINK_NAME_PREFIX)
				|| link.startsWith(SPEL_LINK_PREFIX)
				);
	}
	
	/**
	 * Finds a page
	 * @param pageSelector Page id or name possibly prefixed by document URI and #
	 * @param qualified if selector is qualified, i.e. starts with id, or name,
	 * @param name selector is a name
	 * @return
	 */
	protected Page findPage(String pageSelector, boolean qualified, boolean name) {
		if (Util.isBlank(pageSelector)) {
			return null;
		}
		
		DocumentImpl document = (DocumentImpl) getModel().getPage().getDocument();
		
		int hashIdx = pageSelector.indexOf("#");
		if (hashIdx != -1) {
			String docLocation = pageSelector.substring(0, hashIdx);
			try {
				document = (DocumentImpl) document.getDocument(URI.createURI(docLocation));
			} catch (IOException | ParserConfigurationException | SAXException | URISyntaxException e) {
				throw new ConfigurationException("Error loading document from " + docLocation + "': " + e, e, this);
			}
			pageSelector = pageSelector.substring(hashIdx + 1);
		}
		
		if (qualified) {
			if (pageSelector.startsWith(ID_PREFIX)) {
				name = false;
				pageSelector = pageSelector.substring(ID_PREFIX.length());
			} else if (pageSelector.startsWith(NAME_PREFIX)) {
				name = true;				
				pageSelector = pageSelector.substring(NAME_PREFIX.length());
			} else {			
				throw new ConfigurationException("Invalid qualified page selector: '" + pageSelector, this);
			}
		}
		
		if (name) {			
			String pageName = URLDecoder.decode(pageSelector, StandardCharsets.UTF_8);
			for (Page page: document.getPages()) {
				if (pageName.equals(page.getName())) {
					return page;
				}
			}
			throw new ConfigurationException("Page with name '" + pageName + "' not found in " + document.getURI(), this);			
		}
		
		for (Page page: document.getPages()) {
			if (pageSelector.equals(page.getId())) {
				return page;
			}
		}
		throw new ConfigurationException("Page with id '" + pageSelector + "' not found in " + document.getURI(), this);
	}

	@Override
	public LinkTarget<?> getLinkTarget() {
		String link = getLink();
		if (Util.isBlank(link)) {
			return null;
		}
		
		DocumentImpl document = (DocumentImpl) getModel().getPage().getDocument();
		
		if (link.startsWith(PAGE_LINK_ID_PREFIX)) {
			String pageId = link.substring(PAGE_LINK_ID_PREFIX.length());
			return findPage(pageId, false, false);			
		} 
		
		if (link.startsWith(PAGE_LINK_NAME_PREFIX)) {
			String pageName = link.substring(PAGE_LINK_NAME_PREFIX.length());
			return findPage(pageName, false, true);			
		} 
		
		if (link.startsWith(ELEMENT_LINK_ID_PREFIX)) {
			String elementSelector = link.substring(ELEMENT_LINK_ID_PREFIX.length());
			Page page = getModel().getPage();
			int slashIdx = elementSelector.indexOf("/");
			if (slashIdx != -1) {
				page = findPage(elementSelector.substring(0, slashIdx), true, false);
				elementSelector =  elementSelector.substring(slashIdx + 1);
			}
			
			String id = elementSelector;
			@SuppressWarnings("rawtypes")
			Optional<ModelElement> target = page
				.stream()
				.filter(ModelElement.class::isInstance)
				.map(ModelElement.class::cast)
				.filter(me -> id.equals(me.getId()))
				.findFirst();
				
			if (target.isEmpty()) {
				throw new ConfigurationException("Element with id '" + id + "' not found on page '" + page.getName() + "' in " + document.getURI(), this);
			}
			return target.get();
		}
		
		if (link.startsWith(ELEMENT_LINK_NAME_PREFIX)) {
			String elementSelector = link.substring(ELEMENT_LINK_NAME_PREFIX.length());
			Page page = getModel().getPage();
			int slashIdx = elementSelector.indexOf("/");
			if (slashIdx != -1) {
				page = findPage(elementSelector.substring(0, slashIdx), true, false);
				elementSelector =  elementSelector.substring(slashIdx + 1);
			}
			
			String name = URLDecoder.decode(elementSelector, StandardCharsets.UTF_8);
			@SuppressWarnings("rawtypes")
			Optional<ModelElement> target = page
					.stream()
					.filter(ModelElement.class::isInstance)
					.map(ModelElement.class::cast)
					.filter(me -> !Util.isBlank(me.getLabel()) && name.equals(Jsoup.parse(me.getLabel()).text()))
					.findFirst();
					
			if (target.isEmpty()) {
				throw new ConfigurationException("Element with name '" + name + "' not found on page '" + page.getName() + "' in " + document.getURI(), this);
			}
			return target.get();
		}
		
		if (link.startsWith(SPEL_LINK_PREFIX)) {
			String expr = link.substring(SPEL_LINK_PREFIX.length());
			return evaluate(expr, LinkTarget.class);
		}

		return null;
	}

	@Override
	public URI getURI() {
		URI parentURI = getParent().getURI();
		return parentURI == null ? URI.createURI(URLEncoder.encode(getId(), StandardCharsets.UTF_8)) : parentURI.appendFragment(parentURI.fragment() + "/" + URLEncoder.encode(getId(), StandardCharsets.UTF_8));
	}
	
	@Override
	public String toString() {
		return super.toString() + " [" + getId() + "] " + getLabel();
	}
	
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj) && obj instanceof ModelElement && getModel().equals(((ModelElement<?>) obj).getModel());
	}

	@Override
	protected String getMarkerPosition() {
		StringBuilder positionBuilder = new StringBuilder();
		Page page = getModel().getPage();
		positionBuilder.append("page-name: " + page.getName() + ", page-id: " + page.getId());
		String label = getLabel();
		if (!Util.isBlank(label)) {
			String labelText = Jsoup.parse(label).text();
			positionBuilder.append(", label: "+ labelText);
		}
		String id = getId();
		if (!Util.isBlank(id)) {
			positionBuilder.append(", id:" + id);
		}
		return positionBuilder.toString();
	}
	
	@Override	
	protected String getMarkerLocation() {
		return ((ElementImpl<?>) getModel().getPage()).getMarkerLocation();
	}
	
	public Set<String> getPropertyNames() {
		if (isMxCell()) {
			return Collections.emptySet();
		}
		
		Set<String> pNames = new HashSet<>();
		NamedNodeMap attrs = element.getAttributes();
		for (int i = 0; i < attrs.getLength(); ++i) {
			String aName = ((Attr) attrs.item(i)).getName();
			switch (aName) {
			case "style":
			case "tags":
			case "link":
			case "label":
			case "tooltip":
			case "visible":
				continue;
			default:
				pNames.add(aName);
			}			
		}
		return pNames;
	}
	
	protected <T extends org.nasdanika.drawio.model.ModelElement> T toModelElement(
			T mElement, 
			Function<org.nasdanika.persistence.Marker, org.nasdanika.ncore.Marker> markerFactory,
			Function<Element<?>, CompletableFuture<EObject>> modelElementProvider,
			Function<Tag, org.nasdanika.drawio.model.Tag> tagProvider) {
		modelElementProvider.apply(this).complete(mElement);
		mElement.setId(getId());
		mElement.setLabel(getLabel());
		mElement.setLink(getLink());
		LinkTarget<?> linkTarget = getLinkTarget();
		if (linkTarget != null) {
			modelElementProvider.apply(linkTarget).thenAccept(mlp -> mElement.setLinkTarget((org.nasdanika.drawio.model.LinkTarget) mlp));
		}
		for (String pName: getPropertyNames()) {
			mElement.getProperties().put(pName, getProperty(pName));
		}		
		for (Entry<String, String> se: getStyle().entrySet()) {
			mElement.getStyle().put(se.getKey(), se.getValue());			
		}
		
		for (Tag tag: getTags()) {
			mElement.getTags().add(tagProvider.apply(tag));
			
		}
		mElement.setTooltip(getTooltip());
		mElement.setVisible(isVisible());
		
		for (Marker marker: getMarkers()) {
			mElement.getMarkers().add(markerFactory.apply(marker));
		}
		
		// TODO - geometry for nodes and connections. Refactor Rectangle to Geometry?
		
		return mElement;
	}
	
	protected void onRemove() {
		
	}
	
	protected org.w3c.dom.Element getGeometryElement(boolean create) {
		org.w3c.dom.Element cellElement = getCellElement();
		List<org.w3c.dom.Element> geometryElements = DocumentImpl.getChildrenElements(cellElement, MX_GEOMETRY_ATTRIBUTE);
		if (geometryElements.isEmpty()) {
			if (create) {
				org.w3c.dom.Element ret = element.getOwnerDocument().createElement(MX_GEOMETRY_ATTRIBUTE);
				ret.setAttribute(AS_ATTRIBUTE, GEOMETRY_ROLE);
				cellElement.appendChild(ret);
				return ret;
			}
		} 
		
		if (geometryElements.size() == 1) {
			return geometryElements.get(0);
		} 
		
		throw new IllegalArgumentException("Expected one geometry element, got " + geometryElements.size());
	}
	
	@Override
	public int getPosition() {
		return position;
	}
	
	@Override
	public String getPath() {
		ModelElement<?> parent = getParent();
		return parent == null ? getId() : parent.getId() + "/" + getId();
	}
	
	@Override
	public Object getEnumarateValue() {
		Map<String, String> style = getStyle();
		if ("1".equals(style.get(ENUMERATE_STYLE_KEY))) {
			String value = style.get(ENUMERATE_VALUE_STYLE_KEY);
			if (!Util.isBlank(value)) {
				return value;
			}
			return getModel()
				.getPage()
				.stream()
				.filter(ModelElement.class::isInstance)
				.map(ModelElement.class::cast)
				.filter(me -> {
					@SuppressWarnings("unchecked")
					Map<String, String> eStyle = me.getStyle();
					return "1".equals(eStyle.get(ENUMERATE_STYLE_KEY)) && Util.isBlank(eStyle.get(ENUMERATE_VALUE_STYLE_KEY));
				})
				.takeWhile(me -> me != this)
				.count() + 1;
		}
		return null;
	}
	
	@Override
	public void remove() {
		org.w3c.dom.Element toRemove = getElement();
		toRemove.getParentNode().removeChild(toRemove);
		onRemove();
	}

	@Override
	protected Realm getRealm() {
		return getModel().getPage().getDocument().getRealmElement().getRealm();
	}
	
}

