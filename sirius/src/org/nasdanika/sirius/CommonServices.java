package org.nasdanika.sirius;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.properties.EditSupport;
import org.nasdanika.common.Util;
import org.nasdanika.ncore.NcorePackage;

/**
 * Base class for Sirius services with common methods.
 */
public class CommonServices {
	
	/**
	 * Allows to hide features in property views by returning only features which have corresponding property descriptors.
	 * @param self
	 * @param features
	 * @param session
	 * @return Features which have corresponding property descriptors. 
	 */
	public List<EStructuralFeature> getProperties(EObject self, List<EStructuralFeature> features, Object session) {
		if (session instanceof Session) {
			TransactionalEditingDomain domain = ((Session) session).getTransactionalEditingDomain();
			if (domain instanceof AdapterFactoryEditingDomain) {
				AdapterFactory adapterFactory = ((AdapterFactoryEditingDomain) domain).getAdapterFactory();
				Adapter propertySource = adapterFactory.adapt(self, IItemPropertySource.class);
				if (propertySource instanceof IItemPropertySource) {
					IItemPropertySource ps = (IItemPropertySource) propertySource;
					List<Object> propertyFeatures = ps.getPropertyDescriptors(self).stream().map(pd -> pd.getFeature(self)).collect(Collectors.toList());
					return features.stream().filter(propertyFeatures::contains).collect(Collectors.toList());
				}
			}
		}
		
		return features;		
	}
    
    public boolean isSelect(EObject self, EditSupport editSupport, EStructuralFeature feature) {
    	try {
    		return editSupport.getChoiceOfValues(feature) != null;
    	} catch (NullPointerException e) {
    		// Edit support throws an exception if choices are null.
    		return false;
    	}
    }
    
    public boolean isHtml(EObject self, EStructuralFeature feature) {
    	EAnnotation nann = feature.getEAnnotation("urn:org.nasdanika");
    	if (nann == null) {
    		return false;    	
    	}
    	String contentType = nann.getDetails().get("content-type");
    	return !Util.isBlank(contentType) && "text/html".equalsIgnoreCase(contentType.trim());
    }
    
    public boolean isImage(EObject self, EStructuralFeature feature) {
    	EAnnotation nann = feature.getEAnnotation("urn:org.nasdanika");
    	if (nann == null) {
    		return false;    	
    	}
    	String contentType = nann.getDetails().get("content-type");
    	return !Util.isBlank(contentType) && contentType.trim().startsWith("image/");
    }
        
    public boolean isMarkdown(EObject self, EStructuralFeature feature) {
    	EAnnotation nann = feature.getEAnnotation("urn:org.nasdanika");
    	if (nann == null) {
    		return false;    	
    	}
    	String contentType = nann.getDetails().get("content-type");
    	return !Util.isBlank(contentType) && "text/markdown".equalsIgnoreCase(contentType.trim());
    }    
    
	public boolean isCode(EObject self, EStructuralFeature feature) {
		EAnnotation nann = feature.getEAnnotation("urn:org.nasdanika");
		if (nann == null) {
			return false;    	
		}
		String contentType = nann.getDetails().get("content-type");
		return !Util.isBlank(contentType) && "text/code".equalsIgnoreCase(contentType.trim());
	}    
    
    public boolean isDescription(EObject self, EStructuralFeature feature) {
    	return feature == NcorePackage.Literals.MODEL_ELEMENT__DESCRIPTION;
    }
    
    public boolean hasNasdanikaRule(EObject self, EStructuralFeature feature) {
    	return isCode(self, feature) 
    			|| isDescription(self, feature) 
    			|| isHtml(self, feature) 
    			|| isMarkdown(self, feature);
    }
        
    public boolean hasHtml(EObject self) {
    	for (EStructuralFeature f: self.eClass().getEAllStructuralFeatures()) {
    		if (isHtml(self,f)) {
    			return true;
    		}
    	}
    	return false;
    }
    
	public boolean hasImage(EObject self) {
		for (EStructuralFeature f: self.eClass().getEAllStructuralFeatures()) {
			if (isImage(self,f)) {
				return true;
			}
		}
		return false;
	}
        
    public boolean hasMarkdown(EObject self) {
    	for (EStructuralFeature f: self.eClass().getEAllStructuralFeatures()) {
    		if (isMarkdown(self,f)) {
    			return true;
    		}
    	}
    	return false;
    }    
    
    public boolean hasCode(EObject self) {
    	for (EStructuralFeature f: self.eClass().getEAllStructuralFeatures()) {
    		if (isCode(self,f)) {
    			return true;
    		}
    	}
    	return false;
    }    
    
    public boolean hasDescription(EObject self) {
    	for (EStructuralFeature f: self.eClass().getEAllStructuralFeatures()) {
    		if (isDescription(self,f)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public boolean isResourceExtension(EObject self, String extension) {
    	Resource resource = self.eResource();
    	return resource != null && resource.getURI().toString().endsWith("."+extension); 
    }

}
