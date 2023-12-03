/**
 */
package org.nasdanika.drawio.model.impl;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.nasdanika.drawio.model.ModelPackage;
import org.nasdanika.drawio.model.SemanticMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Semantic Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.drawio.model.impl.SemanticMappingImpl#getDocumentURI <em>Document URI</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.SemanticMappingImpl#getPageID <em>Page ID</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.SemanticMappingImpl#getModelElementID <em>Model Element ID</em>}</li>
 *   <li>{@link org.nasdanika.drawio.model.impl.SemanticMappingImpl#isPageElement <em>Page Element</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SemanticMappingImpl extends MinimalEObjectImpl.Container implements SemanticMapping {
	/**
	 * The default value of the '{@link #getDocumentURI() <em>Document URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentURI()
	 * @generated
	 * @ordered
	 */
	protected static final String DOCUMENT_URI_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getPageID() <em>Page ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageID()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #getModelElementID() <em>Model Element ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModelElementID()
	 * @generated
	 * @ordered
	 */
	protected static final String MODEL_ELEMENT_ID_EDEFAULT = null;

	/**
	 * The default value of the '{@link #isPageElement() <em>Page Element</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isPageElement()
	 * @generated
	 * @ordered
	 */
	protected static final boolean PAGE_ELEMENT_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SemanticMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SEMANTIC_MAPPING;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected int eStaticFeatureCount() {
		return 0;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDocumentURI() {
		return (String)eDynamicGet(ModelPackage.SEMANTIC_MAPPING__DOCUMENT_URI, ModelPackage.Literals.SEMANTIC_MAPPING__DOCUMENT_URI, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDocumentURI(String newDocumentURI) {
		eDynamicSet(ModelPackage.SEMANTIC_MAPPING__DOCUMENT_URI, ModelPackage.Literals.SEMANTIC_MAPPING__DOCUMENT_URI, newDocumentURI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPageID() {
		return (String)eDynamicGet(ModelPackage.SEMANTIC_MAPPING__PAGE_ID, ModelPackage.Literals.SEMANTIC_MAPPING__PAGE_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPageID(String newPageID) {
		eDynamicSet(ModelPackage.SEMANTIC_MAPPING__PAGE_ID, ModelPackage.Literals.SEMANTIC_MAPPING__PAGE_ID, newPageID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getModelElementID() {
		return (String)eDynamicGet(ModelPackage.SEMANTIC_MAPPING__MODEL_ELEMENT_ID, ModelPackage.Literals.SEMANTIC_MAPPING__MODEL_ELEMENT_ID, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModelElementID(String newModelElementID) {
		eDynamicSet(ModelPackage.SEMANTIC_MAPPING__MODEL_ELEMENT_ID, ModelPackage.Literals.SEMANTIC_MAPPING__MODEL_ELEMENT_ID, newModelElementID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isPageElement() {
		return (Boolean)eDynamicGet(ModelPackage.SEMANTIC_MAPPING__PAGE_ELEMENT, ModelPackage.Literals.SEMANTIC_MAPPING__PAGE_ELEMENT, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPageElement(boolean newPageElement) {
		eDynamicSet(ModelPackage.SEMANTIC_MAPPING__PAGE_ELEMENT, ModelPackage.Literals.SEMANTIC_MAPPING__PAGE_ELEMENT, newPageElement);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.SEMANTIC_MAPPING__DOCUMENT_URI:
				return getDocumentURI();
			case ModelPackage.SEMANTIC_MAPPING__PAGE_ID:
				return getPageID();
			case ModelPackage.SEMANTIC_MAPPING__MODEL_ELEMENT_ID:
				return getModelElementID();
			case ModelPackage.SEMANTIC_MAPPING__PAGE_ELEMENT:
				return isPageElement();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.SEMANTIC_MAPPING__DOCUMENT_URI:
				setDocumentURI((String)newValue);
				return;
			case ModelPackage.SEMANTIC_MAPPING__PAGE_ID:
				setPageID((String)newValue);
				return;
			case ModelPackage.SEMANTIC_MAPPING__MODEL_ELEMENT_ID:
				setModelElementID((String)newValue);
				return;
			case ModelPackage.SEMANTIC_MAPPING__PAGE_ELEMENT:
				setPageElement((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.SEMANTIC_MAPPING__DOCUMENT_URI:
				setDocumentURI(DOCUMENT_URI_EDEFAULT);
				return;
			case ModelPackage.SEMANTIC_MAPPING__PAGE_ID:
				setPageID(PAGE_ID_EDEFAULT);
				return;
			case ModelPackage.SEMANTIC_MAPPING__MODEL_ELEMENT_ID:
				setModelElementID(MODEL_ELEMENT_ID_EDEFAULT);
				return;
			case ModelPackage.SEMANTIC_MAPPING__PAGE_ELEMENT:
				setPageElement(PAGE_ELEMENT_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.SEMANTIC_MAPPING__DOCUMENT_URI:
				return DOCUMENT_URI_EDEFAULT == null ? getDocumentURI() != null : !DOCUMENT_URI_EDEFAULT.equals(getDocumentURI());
			case ModelPackage.SEMANTIC_MAPPING__PAGE_ID:
				return PAGE_ID_EDEFAULT == null ? getPageID() != null : !PAGE_ID_EDEFAULT.equals(getPageID());
			case ModelPackage.SEMANTIC_MAPPING__MODEL_ELEMENT_ID:
				return MODEL_ELEMENT_ID_EDEFAULT == null ? getModelElementID() != null : !MODEL_ELEMENT_ID_EDEFAULT.equals(getModelElementID());
			case ModelPackage.SEMANTIC_MAPPING__PAGE_ELEMENT:
				return isPageElement() != PAGE_ELEMENT_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

} //SemanticMappingImpl
