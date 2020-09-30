/**
 */
package org.nasdanika.party.impl;

import org.eclipse.emf.ecore.EClass;
import org.nasdanika.party.MarkdownText;
import org.nasdanika.party.PartyPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Markdown Text</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.party.impl.MarkdownTextImpl#getMarkdown <em>Markdown</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MarkdownTextImpl extends MarkdownImpl implements MarkdownText {
	/**
	 * The default value of the '{@link #getMarkdown() <em>Markdown</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMarkdown()
	 * @generated
	 * @ordered
	 */
	protected static final String MARKDOWN_EDEFAULT = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MarkdownTextImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PartyPackage.Literals.MARKDOWN_TEXT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getMarkdown() {
		return (String)eDynamicGet(PartyPackage.MARKDOWN_TEXT__MARKDOWN, PartyPackage.Literals.MARKDOWN_TEXT__MARKDOWN, true, true);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMarkdown(String newMarkdown) {
		eDynamicSet(PartyPackage.MARKDOWN_TEXT__MARKDOWN, PartyPackage.Literals.MARKDOWN_TEXT__MARKDOWN, newMarkdown);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case PartyPackage.MARKDOWN_TEXT__MARKDOWN:
				return getMarkdown();
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
			case PartyPackage.MARKDOWN_TEXT__MARKDOWN:
				setMarkdown((String)newValue);
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
			case PartyPackage.MARKDOWN_TEXT__MARKDOWN:
				setMarkdown(MARKDOWN_EDEFAULT);
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
			case PartyPackage.MARKDOWN_TEXT__MARKDOWN:
				return MARKDOWN_EDEFAULT == null ? getMarkdown() != null : !MARKDOWN_EDEFAULT.equals(getMarkdown());
		}
		return super.eIsSet(featureID);
	}

} //MarkdownTextImpl
