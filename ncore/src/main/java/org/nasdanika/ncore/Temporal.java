/**
 */
package org.nasdanika.ncore;

import java.lang.Boolean;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.function.Supplier;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Temporal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.nasdanika.ncore.Temporal#getInstant <em>Instant</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getBase <em>Base</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getOffset <em>Offset</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getDerivatives <em>Derivatives</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getLowerBounds <em>Lower Bounds</em>}</li>
 *   <li>{@link org.nasdanika.ncore.Temporal#getUpperBounds <em>Upper Bounds</em>}</li>
 * </ul>
 *
 * @see org.nasdanika.ncore.NcorePackage#getTemporal()
 * @model annotation="urn:org.nasdanika documentation-reference='doc/temporal.md'"
 *        annotation="http://www.eclipse.org/emf/2002/Ecore constraints='bounds offset'"
 * @generated
 */
public interface Temporal extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Instant</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An absolute point on the time-line. E.g. ``2021/07/04``.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Instant</em>' attribute.
	 * @see #setInstant(Instant)
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_Instant()
	 * @model dataType="org.nasdanika.ncore.Instant"
	 *        annotation="urn:org.nasdanika default-feature='true' exclusive-with='base'"
	 * @generated
	 */
	Instant getInstant();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Temporal#getInstant <em>Instant</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Instant</em>' attribute.
	 * @see #getInstant()
	 * @generated
	 */
	void setInstant(Instant value);

	/**
	 * Returns the value of the '<em><b>Base</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.ncore.Temporal#getDerivatives <em>Derivatives</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Base of this temporal.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base</em>' reference.
	 * @see #setBase(Temporal)
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_Base()
	 * @see org.nasdanika.ncore.Temporal#getDerivatives
	 * @model opposite="derivatives"
	 *        annotation="urn:org.nasdanika exclusive-with='instant'"
	 * @generated
	 */
	Temporal getBase();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Temporal#getBase <em>Base</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base</em>' reference.
	 * @see #getBase()
	 * @generated
	 */
	void setBase(Temporal value);

	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Time offset from the base in [ISO-8601 durations](https://en.wikipedia.org/wiki/ISO_8601#Durations) format. 
	 * 
	 * Examples:
	 * 
	 * * ``P1H`` for one hour later.
	 * * ``-P20D`` or ``P-20D`` for 20 days before. Can be null (zero), e.g. if one [period](Period.html) starts right after another period ends.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(Duration)
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_Offset()
	 * @model dataType="org.nasdanika.ncore.Duration"
	 * @generated
	 */
	Duration getOffset();

	/**
	 * Sets the value of the '{@link org.nasdanika.ncore.Temporal#getOffset <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(Duration value);

	/**
	 * Returns the value of the '<em><b>Derivatives</b></em>' reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Temporal}.
	 * It is bidirectional and its opposite is '{@link org.nasdanika.ncore.Temporal#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Temporals which are based on this temporal.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Derivatives</em>' reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_Derivatives()
	 * @see org.nasdanika.ncore.Temporal#getBase
	 * @model opposite="base" derived="true"
	 * @generated
	 */
	EList<Temporal> getDerivatives();

	/**
	 * Returns the value of the '<em><b>Lower Bounds</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Temporal}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Lower bounds of a temporal. E.g. exact time of some temporal might not be known at a moment, but it might be known that it should not be before than some other temporal - a lower bound. Lower bounds are used in validations and before/after computations. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Lower Bounds</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_LowerBounds()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EList<Temporal> getLowerBounds();

	/**
	 * Returns the value of the '<em><b>Upper Bounds</b></em>' containment reference list.
	 * The list contents are of type {@link org.nasdanika.ncore.Temporal}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Upper bounds of a temporal. E.g. exact time of some temporal might not be known at a moment, but it might be known that it should not be after some other temporal - an upper bound. Upper bounds are used in validations and before/after computations. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Upper Bounds</em>' containment reference list.
	 * @see org.nasdanika.ncore.NcorePackage#getTemporal_UpperBounds()
	 * @model containment="true"
	 *        annotation="urn:org.nasdanika homogenous='true' strict-containment='true'"
	 * @generated
	 */
	EList<Temporal> getUpperBounds();
	
	// Default methods

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tests if this temporal is after the specified temporal. Returns null if unknown, e.g. two unrelated events.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated NOT
	 */
	default Boolean after(Temporal when) {
		if (when == null) {
			return false;
		}
		
		Temporal nThis = normalize();
		Instant thisInstant = nThis.getInstant();
		
		Temporal nWhen = when.normalize();
		Instant whenInstant = nWhen.getInstant();
		
		if (whenInstant != null) {
			if (thisInstant == null) {
				return null;
			}
			return thisInstant.isAfter(whenInstant);
		}
		if (nWhen.getBase() == nThis.getBase()) {
			Duration whenOffset = nWhen.getOffset();
			Duration thisOffset = nThis.getOffset();
			
			if (whenOffset == null) {
				whenOffset = Duration.ZERO;
			}
			if (thisOffset == null) {
				thisOffset = Duration.ZERO;
			}
			return thisOffset.compareTo(whenOffset) > 0;
		}
		
		/*
		 * Bounds based - when upper bounds shall not be after this or its lower bounds
		 * If any of when upper bounds is before this or lower bound then this is after when 
		 * if any of lower bounds are after when then this is after when 
		 */
		for (Temporal whenUpperBound: when.getUpperBounds()) {
			if (this.after(whenUpperBound) == Boolean.TRUE) {
				return true;
			}
			for (Temporal lowerBound: getLowerBounds()) {
				if (lowerBound.after(whenUpperBound) == Boolean.TRUE) {
					return true;
				}
			}
		}
		for (Temporal lowerBound: getLowerBounds()) {
			if (lowerBound.after(when) == Boolean.TRUE) {
				return true;
			}
		}
		
		return null;		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tests if this temporal is before the specified temporal. Returns null if unknown, e.g. two unrelated events.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated NOT
	 */
	default Boolean before(Temporal when) {
		if (when == null) {
			return false;
		}
		return when.after(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Tests if this temporal occurs at the same point on the time-line as the specified temporal. Returns null if unknown, e.g. two unrelated events.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated NOT
	 */
	default Boolean coincides(Temporal when) {
		if (when == null) {
			return false;
		}
		Temporal nWhen = when.normalize();
		Instant whenInstant = nWhen.getInstant();
		
		Temporal nThis = normalize();
		Instant thisInstant = nThis.getInstant();
		if (whenInstant != null) {
			if (thisInstant == null) {
				return null;
			}
			return thisInstant.equals(whenInstant);
		}
		if (nWhen.getBase() == nThis.getBase()) {
			Duration whenOffset = nWhen.getOffset();
			Duration thisOffset = nThis.getOffset();
			
			if (whenOffset == null) {
				whenOffset = Duration.ZERO;
			}
			if (thisOffset == null) {
				thisOffset = Duration.ZERO;
			}
			return thisOffset.compareTo(whenOffset) == 0;
		}
		return null;		
	}
	
	/**
	 * Adds temporal to duration with basing result on the argument if there is no base or instant
	 * or on the argument's base. Different from Temporal.plus which always bases on this temporal.
	 * @param base Temporal to add offset to
	 * @param offset Added to the base instant if base is instant based or to the super-base.
	 */
	static Temporal plus(Temporal base, Duration offset) {
		if (offset == null || Duration.ZERO.equals(offset)) {
			return base;
		}
		
		EClass eClass = base.eClass();
		Temporal ret = (Temporal) eClass.getEPackage().getEFactoryInstance().create(NcorePackage.Literals.TEMPORAL);
		Instant instant = base.getInstant();
		if (instant == null) {
			Temporal superBase = base.getBase();
			if (superBase == null) {
				ret.setBase(base);
				ret.setOffset(offset);
			} else {
				ret.setBase(superBase);
				Duration baseOffset = base.getOffset();
				ret.setOffset(baseOffset == null ? offset : offset.plus(baseOffset));
			}
		} else {
			ret.setInstant(instant.plus(offset));
		}
		for (Temporal lowerBound: base.getLowerBounds()) {
			ret.getLowerBounds().add(plus(lowerBound, offset));
		}
		for (Temporal upperBound: base.getUpperBounds()) {
			ret.getUpperBounds().add(plus(upperBound, offset));
		}
		return ret;					
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns a normalized instance of this temporal not contained in the model. Normalization walks through the temporal chain to the root temporal. If that root temporal is instant/absolute then the normalized instance would be instant/absolute. Otherwise the normalized instance would contain the root temporal as its base and offset would be the sum of all offsets.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated NOT
	 */
	default Temporal normalize() {
		Supplier<Temporal> retSupplier = () -> {
			EClass eClass = eClass();
			Temporal ret = (Temporal) eClass.getEPackage().getEFactoryInstance().create(eClass);
			if (this instanceof NamedElement) {
				((NamedElement) ret).setName(((NamedElement) this).getName());
			}
			return ret;
		};
		
		Temporal base = getBase();
		Duration offset = getOffset();
		Instant instant = getInstant();
		EList<Temporal> lowerBounds = getLowerBounds();
		EList<Temporal> upperBounds = getUpperBounds();

		// No base - adding offset to instant.
		if (base == null) {
			if (instant == null || offset == null || Duration.ZERO.equals(offset)) {
				return this;
			}
			
			Temporal ret = retSupplier.get();
			ret.setInstant(instant.plus(offset));
			for (Temporal lowerBound: lowerBounds) {
				ret.getLowerBounds().add(lowerBound.plus(offset));
			}
			for (Temporal upperBound: upperBounds) {
				ret.getUpperBounds().add(upperBound.plus(offset));
			}
			return ret;
		}
		
		Temporal ret = plus(base.normalize(), offset);
		if (!lowerBounds.isEmpty() || !upperBounds.isEmpty()) {
			ret = ret.copy();
			for (Temporal lowerBound: lowerBounds) {
				ret.getLowerBounds().add(lowerBound.copy());
			}
			for (Temporal upperBound: upperBounds) {
				ret.getUpperBounds().add(upperBound.copy());
			}			
		}
		return ret;		
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns [duration](Duration.html) difference between this temporal and the argument temporal - how much this temporal is after the argument on the time-line, if difference can be computed, e.g. both this temporal and the argument temporal are instant or trace to the same base temporal. Returns null otherwise.
	 * <!-- end-model-doc -->
	 * @model dataType="org.nasdanika.engineering.Duration"
	 * @generated NOT
	 */
	default Duration minus(Temporal when) {
		if (when == null) {
			return null;
		}
		
		// Instant
		Instant whenInstant = when.getInstant();
		Instant thisInstant = getInstant();
		
		if (thisInstant != null && whenInstant != null) {
			return Duration.ofMillis(thisInstant.toEpochMilli() - whenInstant.toEpochMilli());
		}

		// Base
		Temporal base = getBase();
		if (base == when) {
			return getOffset();
		}
		
		Temporal whenBase = when.getBase();		
		if (whenBase == this) {
			Duration whenOffset = when.getOffset();
			return whenOffset == null ? whenOffset : whenOffset.negated();
		}
		
		if (whenBase == base) {
			Duration whenOffset = when.getOffset();
			Duration offset = getOffset();
			if (whenOffset == null) {
				return offset;
			}
			if (offset == null) {
				return whenOffset.negated();
			}
			return offset.minus(whenOffset);
		}
		
		if (whenBase == null && base == null) {
			return null; // No point in normalization.
		}
		
		if (base == null) {
			if (whenBase.getBase() == null) {
				return null; // No point in normalization.
			}
			// There is when super-base - shall normalize.
			return minus(when.normalize());
		}
		
		if (whenBase == null) {
			if (base.getBase() == null) {
				return null; // No point in normalization
			}
			// There is super-base - shall normalize.
			return normalize().minus(when);
		}
		
		// Normalized
		return normalize().minus(when.normalize()); 
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns a temporal based on this one offset by negation of the argument [duration](Duration.html). Null duration is treated as zero and set as-is (not negated).
	 * <!-- end-model-doc -->
	 * @model offsetDataType="org.nasdanika.engineering.Duration"
	 * @generated NOT
	 */
	default Temporal minus(Duration offset) {
		return plus(offset == null ? offset : offset.negated());
	}
		
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns a temporal based on this one offset by the argument [duration](Duration.html). Duration can be null.
	 * <!-- end-model-doc -->
	 * @model offsetDataType="org.nasdanika.engineering.Duration"
	 * @generated NOT
	 */
	default Temporal plus(Duration offset) {
		if (offset == null || Duration.ZERO.equals(offset)) {
			return this;
		}
		
		EClass eClass = eClass();
		Temporal ret = (Temporal) eClass.getEPackage().getEFactoryInstance().create(NcorePackage.Literals.TEMPORAL);
		ret.setBase(this);
		ret.setOffset(offset);
		for (Temporal lowerBound: getLowerBounds()) {
			ret.getLowerBounds().add(lowerBound.plus(offset));
		}
		for (Temporal upperBound: getUpperBounds()) {
			ret.getUpperBounds().add(upperBound.plus(offset));
		}
		return ret;		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Returns a deep copy of self with bounds copied. Other containment references are not set.
	 * <!-- end-model-doc -->
	 * @model
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	default Temporal copy() {
		EClass eClass = eClass();
		Temporal ret = (Temporal) eClass.getEPackage().getEFactoryInstance().create(eClass);
		for (EStructuralFeature sf: eClass.getEAllStructuralFeatures()) {
			if (sf.isChangeable()) {
				if (sf instanceof EAttribute) {
					ret.eSet(sf, eGet(sf));
				} else {
					EReference ref = (EReference) sf;
					if (ref.isContainment()) {
						if (NcorePackage.Literals.TEMPORAL.isSuperTypeOf(ref.getEReferenceType())) {
							if (ref.isMany()) {
								Collection<Temporal> target = (Collection<Temporal>) ret.eGet(sf);
								for (Temporal te: (Collection<Temporal>) eGet(sf)) {
									target.add(te.copy());
								}
							} else {
								ret.eSet(sf, ((Temporal) eGet(sf)).copy());														
							}
						}
					} else {
						ret.eSet(sf, eGet(sf));						
					}
				}
			}
		}
		return ret;		
	}

	static java.lang.String formatDuration(Duration duration) {
		if (duration == null || duration.equals(Duration.ZERO)) {
			return "Zero";
		}
		
		StringBuilder ret = new StringBuilder();
		long days = duration.toDays();
		if (days != 0) {
			ret.append(days).append(days == 1 ? " day" : " days");
		}
		long hours = duration.toHours() % 24;
		if (hours != 0) {
			if (ret.length() > 0) {
				ret.append(" ");
			}
			ret.append(hours).append(hours == 1 ? " hour" : " hours");			
		}		
		long minutes = duration.toMinutes() % 60;
		if (minutes != 0) {
			if (ret.length() > 0) {
				ret.append(" ");
			}	
			ret.append(minutes).append(minutes == 1 ? " minute" : " minutes");
		}
		long seconds = (duration.toMillis() * 1000) % 60;
		if (seconds != 0) {
			if (ret.length() > 0) {
				ret.append(" ");
			}			
			ret.append(seconds).append(seconds == 1 ? " second" : " seconds");
		}
//		long nanos = duration.toNanos();
//		if (nanos != 0) {
//			if (ret.length() > 0) {
//				ret.append(" ");
//			}			
//			ret.append(nanos).append(nanos == 1 ? " nanosecond" : " nanoseconds");
//		}
		
		return ret.toString();
	}		

} // Temporal
