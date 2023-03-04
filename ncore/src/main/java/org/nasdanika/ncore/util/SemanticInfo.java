package org.nasdanika.ncore.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.json.JSONObject;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.ListProperty;
import org.nasdanika.ncore.MapProperty;
import org.nasdanika.ncore.ModelElement;
import org.nasdanika.ncore.NamedElement;
import org.nasdanika.ncore.Property;

/**
 * A wrapper interface for semantic-element annotation
 * @author Pavel
 *
 */
public class SemanticInfo extends SemanticIdentityImpl {
	
	public static String KEY = "semantic-element";
	
	public static String TYPE_KEY = "type";
	public static String NS_URI_KEY = "ns-uri";
	public static String CONTAINER_KEY = "container";
	public static String DESCRIPTION_KEY = "description";
	public static String LOCATION_KEY = "location";
	public static String ICON_KEY = "icon";
		
	public interface TypeInfo {
		
		String getNsURI();
		
		String getName(); 
	}
	
	protected ContainerInfo containerInfo;
	protected String typeName;
	protected String typeNsURI;
	protected String name;
	protected String description;
	protected String icon;
	protected URI location;
	
	public SemanticInfo(
			Collection<URI> identifiers, 
			String name,
			String description,
			String icon,
			URI location,
			ContainerInfo containerInfo,
			String typeName,
			String typeNsURI) {
		
		super(identifiers);
		this.name = name;
		this.description = description;
		this.icon = icon;
		this.location = location;
		this.containerInfo = containerInfo;
		this.typeName = typeName;
		this.typeNsURI = typeNsURI;
	}
	
	public SemanticInfo(EObject semanticElement) {
		super(semanticElement);
		if (semanticElement instanceof NamedElement) {
			this.name = ((NamedElement) semanticElement).getName();
		}
		if (semanticElement instanceof ModelElement) {
			this.description = ((ModelElement) semanticElement).getDescription();
		}
		EObject container = semanticElement.eContainer();
		if (container != null) {
			EReference cRef = semanticElement.eContainmentFeature();
			this.containerInfo = new ContainerInfo(container, cRef == null ? null : cRef.getName());
		}
		EClass semanticClass = semanticElement.eClass();
		typeName = semanticClass.getName();
		typeNsURI = semanticClass.getEPackage().getNsURI();
	}

	public SemanticInfo(JSONObject jsonObj, URI base) {
		super(jsonObj);
		if (jsonObj.has(ContainerInfo.NAME_KEY)) {
			name = jsonObj.getString(ContainerInfo.NAME_KEY);
		}
		if (jsonObj.has(DESCRIPTION_KEY)) {
			description = jsonObj.getString(DESCRIPTION_KEY);
		}
		if (jsonObj.has(ICON_KEY)) {
			icon = jsonObj.getString(ICON_KEY);
		}
		if (jsonObj.has(LOCATION_KEY)) {
			location = URI.createURI(jsonObj.getString(LOCATION_KEY));
			if (base != null && !base.isRelative() && location.isRelative()) {
				location = location.resolve(base);
			}
		}
		if (jsonObj.has(CONTAINER_KEY)) {
			containerInfo = new ContainerInfo(jsonObj.getJSONObject(CONTAINER_KEY));
		}
		if (jsonObj.has(TYPE_KEY)) {
			JSONObject typeObj = jsonObj.getJSONObject(TYPE_KEY);
			typeName = typeObj.getString(ContainerInfo.NAME_KEY);
			typeNsURI = typeObj.getString(NS_URI_KEY);
		}
	}

	public URI getLocation() {
		return location;
	}
	
	public void setLocation(URI location) {
		this.location = location;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	/**
	 * @param eObj
	 * @return {@link SemanticInfo} if eObj is a {@link ModelElement} and has semantic-element annotation
	 */
	public static SemanticInfo getAnnotation(EObject eObj) {
		if (eObj instanceof ModelElement) {
			Property annotation = ((ModelElement) eObj).getAnnotation(KEY);
			if (annotation instanceof MapProperty) {
				MapProperty semanticElementAnnotation = (MapProperty) annotation;
				Property identifiersProperty = semanticElementAnnotation.get(IDENTIFIERS_KEY);
				List<URI> identifiers = identifiersProperty == null ? Collections.emptyList() : ((ListProperty) identifiersProperty).getValue()
					.stream()
					.map(org.nasdanika.ncore.String.class::cast)
					.map(org.nasdanika.ncore.String::getValue)
					.map(URI::createURI)
					.collect(Collectors.toList());
				
				Property nameProperty = semanticElementAnnotation.get(ContainerInfo.NAME_KEY);		
				Property descriptionProperty = semanticElementAnnotation.get(DESCRIPTION_KEY);		
				Property iconProperty = semanticElementAnnotation.get(ICON_KEY);		
				Property locationProperty = semanticElementAnnotation.get(LOCATION_KEY);		
				
				ContainerInfo containerInfo;

				Property containerProperty = semanticElementAnnotation.get(CONTAINER_KEY);
				if (containerProperty == null) {
					containerInfo = null;
				} else {
					MapProperty containerMap = containerProperty instanceof MapProperty ? (MapProperty) containerProperty : null;
					Property containerIdentifiersProperty = containerMap == null ? null : containerMap.get(IDENTIFIERS_KEY);
					List<URI> containerIdentifiers = containerIdentifiersProperty == null ? Collections.emptyList() : ((ListProperty) containerIdentifiersProperty).getValue()
						.stream()
						.map(org.nasdanika.ncore.String.class::cast)
						.map(org.nasdanika.ncore.String::getValue)
						.map(URI::createURI)
						.collect(Collectors.toList());
					
					Property referenceProperty = containerMap == null ? null : containerMap.get(ContainerInfo.REFERENCE_KEY);						
					Property containerNameProperty = containerMap == null ? null : containerMap.get(ContainerInfo.NAME_KEY);
					
					containerInfo = new ContainerInfo(
							containerIdentifiers, 
							referenceProperty == null ? null : ((org.nasdanika.ncore.String) referenceProperty).getValue(),
							containerNameProperty == null ? null : ((org.nasdanika.ncore.String) containerNameProperty).getValue()); 
				}
				
				Property typeProperty = semanticElementAnnotation.get(TYPE_KEY);
				MapProperty typeMap = typeProperty instanceof MapProperty ? (MapProperty) typeProperty : null;
				Property typeNsUriProperty = typeMap == null ? null : typeMap.get(NS_URI_KEY);						
				Property typeNameProperty = typeMap == null ? null : typeMap.get(ContainerInfo.NAME_KEY);						
				
				return new SemanticInfo(
						identifiers, 
						nameProperty == null ? null : ((org.nasdanika.ncore.String) nameProperty).getValue(),
						descriptionProperty == null ? null : ((org.nasdanika.ncore.String) descriptionProperty).getValue(),
						iconProperty == null ? null : ((org.nasdanika.ncore.String) iconProperty).getValue(),
						locationProperty == null ? null : URI.createURI(((org.nasdanika.ncore.String) locationProperty).getValue()),
						containerInfo,
						typeNameProperty == null ? null : ((org.nasdanika.ncore.String) typeNameProperty).getValue(),
						typeNsUriProperty == null ? null : ((org.nasdanika.ncore.String) typeNsUriProperty).getValue());
			}
		}
		
		return null;
	}
	
	public void annotate(ModelElement target) {
		// Semantic element annotation
		Map<String,Object> semanticAnnotation = new LinkedHashMap<>();		
		List<String> semanticIdentifiers = new ArrayList<>();
		for (URI uri: getIdentifiers()) {
			semanticIdentifiers.add(uri.toString());
		}	
		semanticAnnotation.put(IDENTIFIERS_KEY, semanticIdentifiers);
		String name = getName();
		if (!Util.isBlank(name)) {
			semanticAnnotation.put(ContainerInfo.NAME_KEY, name);
		}
		String description = getDescription();
		if (!Util.isBlank(description)) {
			semanticAnnotation.put(DESCRIPTION_KEY, description);
		}
		URI location = getLocation();
		if (location != null) {
			semanticAnnotation.put(LOCATION_KEY, location.toString());
		}
		
		ContainerInfo containerInfo = getContainerInfo();
		if (containerInfo != null) {
			Map<String, Object> containerSpec = new LinkedHashMap<>();
			List<String> containerURIs = new ArrayList<>();
			for (URI uri: containerInfo.getIdentifiers()) {
				containerURIs.add(uri.toString());
			}	
			containerSpec.put(IDENTIFIERS_KEY, containerURIs);
			
			String containerName = containerInfo.getName();
			if (!Util.isBlank(containerName)) {
				containerSpec.put(ContainerInfo.NAME_KEY, containerName);
			}			

			String refName = containerInfo.getReference();
			if (!Util.isBlank(refName)) {
				containerSpec.put(ContainerInfo.REFERENCE_KEY, refName);
			}
			
			semanticAnnotation.put(CONTAINER_KEY, containerSpec);
		}
		
		TypeInfo typeInfo = getTypeInfo();
		if (typeInfo != null) {
			Map<String,String> typeSpec = new LinkedHashMap<>();
			typeSpec.put(NS_URI_KEY, typeInfo.getNsURI());
			typeSpec.put(ContainerInfo.NAME_KEY, typeInfo.getName());		
			semanticAnnotation.put(TYPE_KEY, typeSpec);
		}
		
		target.setAnnotation(SemanticInfo.KEY, semanticAnnotation);		
	}

	public ContainerInfo getContainerInfo() {
		return containerInfo;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	};
	
	public TypeInfo getTypeInfo() {
		if (Util.isBlank(typeName) && Util.isBlank(typeNsURI)) {
			return null;
		}
		
		return new TypeInfo() {
			
			@Override
			public String getNsURI() {
				return typeNsURI;
			}
			
			@Override
			public String getName() {
				return typeName;
			}
		};
	}
	
	@Override
	public JSONObject toJSON() {
		JSONObject ret = super.toJSON();
		if (!Util.isBlank(getName())) {
			ret.put(ContainerInfo.NAME_KEY, getName());
		}
		if (!Util.isBlank(getDescription())) {
			ret.put(DESCRIPTION_KEY, getDescription());
		}
		if (!Util.isBlank(getIcon())) {
			ret.put(ICON_KEY, getIcon());
		}
		if (getLocation() != null) {
			ret.put(LOCATION_KEY, getLocation().toString());
		}
		
		ContainerInfo cInfo = getContainerInfo();
		if (cInfo != null) {
			ret.put(CONTAINER_KEY, cInfo.toJSON());
		}
		
		TypeInfo tInfo = getTypeInfo();
		if (tInfo != null) {
			JSONObject jType = new JSONObject();
			jType.put(ContainerInfo.NAME_KEY, tInfo.getName());
			jType.put(NS_URI_KEY, tInfo.getNsURI());
			ret.put(TYPE_KEY, jType);
		}
		
		return ret;
	}
	
}
