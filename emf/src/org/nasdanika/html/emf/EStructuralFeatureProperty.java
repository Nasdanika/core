package org.nasdanika.html.emf;

import java.util.List;

import org.apache.commons.text.StringEscapeUtils;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Choice;
import org.nasdanika.html.app.Diagnostic;
import org.nasdanika.html.app.Property;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.ViewPart;

public class EStructuralFeatureProperty implements Property {

	private EStructuralFeature feature;

	public EStructuralFeatureProperty(EStructuralFeature feature) {
		this.feature = feature;
	}

	@Override
	public Object getDisplayValue(Object obj) {
		Object value = getValue(obj);
		// TODO - type-specific formatting.
		if (value == null) {
			return "";
		}
		
		// Render a link
		if (value instanceof EObject) {
			Action viewAction = EObjectAdaptable.adaptTo((EObject) value, ViewAction.class);
			if (viewAction != null) {
				// Returning ViewPart to avoid dealing with obtaining of ViewGenerator
				return new ViewPart() {

					@Override
					public Object generate(ViewGenerator viewGenerator) {
						return viewGenerator.link(viewAction);
					}
					
				};
			} 
		}
		
		return StringEscapeUtils.escapeHtml4(value.toString());
	}
	
	protected Object getValue(Object obj) {
		return obj == null ? null : ((EObject) obj).eGet(feature);
	}

	@Override
	public Object getEditValue(Object obj) {
		// TODO - type-specific formatting
		return getValue(obj);
	}

	@Override
	public List<Choice> getChoices(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEditable(Object obj) {
		if (obj instanceof EObject) {
			AccessController ap = EObjectAdaptable.adaptTo((EObject) obj, AccessController.class);
			if (ap != null && !ap.canUpdate(feature.getName())) {
				return false;
			}
		}
		return feature.isChangeable(); 
	}

	@Override
	public Diagnostic update(Object obj, Object originalValue, Object newValue) {
		// check update access, return error diagnostic if no access.
		// TODO Auto-generated method stub
		return null;
	}

}
