package org.nasdanika.html.emf;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ActionActivator;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;

/**
 * Delegates to value ViewAction.
 * @author Pavel
 *
 */
public class EReferenceSingleValuePropertySourceViewAction<T extends EObject> extends EReferenceSingleValuePropertySource<T> implements Action {

	private String featureRole;
	private Action parent;

	public EReferenceSingleValuePropertySourceViewAction(T eObject, EReference feature, String featureRole, Action parent) {
		super(eObject, feature);
		this.featureRole = featureRole;
		this.parent = parent;
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator) {
		EObject value = (EObject) getValue();
		if (value == null) {
			// TODO - some action to create an object for containment references.
			return null;
		}
		
		Action viewAction = EObjectAdaptable.adaptTo(value, ViewAction.class);
		if (viewAction != null) {
			return viewAction.generate(viewGenerator);
		}
		return null;
	}

	@Override
	public Label getCategory() {
		return null;
	}

	@Override
	public boolean isDisabled() {
		return false;
	}

	@Override
	public String getConfirmation() {
		return null;
	}

	@Override
	public boolean isFloatRight() {
		return false;
	}

	@Override
	public List<? extends Action> getChildren() {
		return Collections.emptyList();
	}

	@Override
	public boolean isInRole(String role) {
		return featureRole != null && featureRole.equals(role);
	}

	@Override
	public Action getParent() {
		return parent;
	}

	@Override
	public ActionActivator getActivator() {
		return null;
	}
	
	@Override
	public Object getId() {
		return getParent().getId()+"-"+feature.getName();
	}

}
