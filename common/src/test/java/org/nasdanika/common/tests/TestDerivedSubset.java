package org.nasdanika.common.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.junit.jupiter.api.Test;
import org.nasdanika.common.DerivedSubsetEList;


public class TestDerivedSubset {

	static int checks = 0;

	static void check(boolean condition, String message) {
		++checks;
		if (!condition) {
			throw new AssertionError(message);
		}
	}
	
	@Test
	public void testDerivedSubset() {
		main(null);
	}

	public static void main(String[] args) {
		EcoreFactory f = EcoreFactory.eINSTANCE;
		EcorePackage ep = EcorePackage.eINSTANCE;

		// --- Dynamic Family metamodel ---
		EPackage pkg = f.createEPackage();
		pkg.setName("family");
		pkg.setNsURI("test://family");
		pkg.setNsPrefix("family");

		EClass person = f.createEClass();
		person.setName("Person");
		EAttribute name = f.createEAttribute();
		name.setName("name");
		name.setEType(ep.getEString());
		EAttribute female = f.createEAttribute();
		female.setName("female");
		female.setEType(ep.getEBoolean());
		person.getEStructuralFeatures().add(name);
		person.getEStructuralFeatures().add(female);

		EClass family = f.createEClass();
		family.setName("Family");
		EReference children = f.createEReference();
		children.setName("children");
		children.setEType(person);
		children.setContainment(true);
		children.setUpperBound(-1);
		EReference daughters = f.createEReference();
		daughters.setName("daughters");
		daughters.setEType(person);
		daughters.setUpperBound(-1);
		daughters.setDerived(true);
		daughters.setTransient(true);
		daughters.setVolatile(true);
		family.getEStructuralFeatures().add(children);
		family.getEStructuralFeatures().add(daughters);

		pkg.getEClassifiers().add(person);
		pkg.getEClassifiers().add(family);

		// --- Instance data ---
		EObject fam = EcoreUtil.create(family);
		String[] names = { "Anna", "Boris", "Clara", "Dmitri", "Elena" };
		boolean[] fem = { true, false, true, false, true };
		@SuppressWarnings("unchecked")
		List<EObject> kids = (List<EObject>) fam.eGet(children);
		for (int i = 0; i < names.length; i++) {
			EObject p = EcoreUtil.create(person);
			p.eSet(name, names[i]);
			p.eSet(female, fem[i]);
			kids.add(p);
		}

		int daughtersID = family.getFeatureID(daughters);
		int childrenID = family.getFeatureID(children);

		EList<EObject> view = new DerivedSubsetEList<>(
			EObject.class,
			(InternalEObject) fam,
			daughtersID,
			childrenID,
			(Object o) -> Boolean.TRUE.equals(((EObject) o).eGet(female))
				? Optional.of((EObject) o) : Optional.empty(),
			(EObject d) -> {
				if (!Boolean.TRUE.equals(d.eGet(female))) {
					throw new IllegalArgumentException("Not a daughter: " + d.eGet(name));
				}
				return d;
			});

		// --- Notifications capture ---
		List<Notification> notifications = new ArrayList<>();
		fam.eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(Notification n) {
				notifications.add(n);
			}
		});

		// --- Read path ---
		check(view.size() == 3, "size == 3, was " + view.size());
		check("Anna".equals(view.get(0).eGet(name)), "get(0) == Anna");
		check("Clara".equals(view.get(1).eGet(name)), "get(1) == Clara");
		check("Elena".equals(view.get(2).eGet(name)), "get(2) == Elena");
		check(view.indexOf(kids.get(2)) == 1, "indexOf(Clara) == 1");
		check(!view.contains(kids.get(1)), "!contains(Boris)");

		// --- add(E): appended at end of backing ---
		EObject fiona = EcoreUtil.create(person);
		fiona.eSet(name, "Fiona");
		fiona.eSet(female, true);
		view.add(fiona);
		check(view.size() == 4, "size == 4 after add");
		check(kids.size() == 6 && kids.get(5) == fiona, "Fiona appended to children");
		check(fiona.eContainer() == fam, "Fiona contained by family via write-through");

		// --- add(int, E): positional insert maps into backing ---
		EObject galina = EcoreUtil.create(person);
		galina.eSet(name, "Galina");
		galina.eSet(female, true);
		view.add(1, galina); // between Anna and Clara in the view
		check(view.get(1) == galina, "view.get(1) == Galina");
		check(kids.indexOf(galina) == 1, "Galina inserted right after Anna in children");

		// --- remove(Object): removes only from view+backing, others untouched ---
		EObject clara = kids.get(3); // after insert: Anna, Galina, Boris?? verify by name
		// find Clara robustly
		clara = kids.stream().filter(k -> "Clara".equals(k.eGet(name))).findFirst().get();
		check(view.remove(clara), "remove(Clara)");
		check(!kids.contains(clara), "Clara removed from children");
		check(kids.stream().anyMatch(k -> "Boris".equals(k.eGet(name))), "Boris untouched");
		check(view.size() == 4, "size == 4 after remove");

		// --- set(int, E) ---
		EObject helga = EcoreUtil.create(person);
		helga.eSet(name, "Helga");
		helga.eSet(female, true);
		EObject replaced = view.set(0, helga);
		check("Anna".equals(replaced.eGet(name)), "set returned Anna");
		check(kids.get(0) == helga, "children[0] == Helga");

		// --- precondition enforcement on add ---
		EObject boris2 = EcoreUtil.create(person);
		boris2.eSet(name, "Boris2");
		boris2.eSet(female, false);
		boolean threw = false;
		try {
			view.add(boris2);
		} catch (IllegalArgumentException e) {
			threw = true;
		}
		check(threw, "adding a non-daughter throws");

		// --- bidirectional iteration + iterator add after previous ---
		ListIterator<EObject> it = view.listIterator(view.size());
		List<String> reversed = new ArrayList<>();
		while (it.hasPrevious()) {
			reversed.add((String) it.previous().eGet(name));
		}
		check(reversed.equals(List.of("Fiona", "Elena", "Galina", "Helga")),
			"reverse iteration order, was " + reversed);
		// cursor now at view index 0; add here -> becomes first element
		EObject inga = EcoreUtil.create(person);
		inga.eSet(name, "Inga");
		inga.eSet(female, true);
		it.add(inga);
		check(view.get(0) == inga, "iterator add at front");
		check(kids.get(0) == inga, "Inga first in children");

		// --- notifications: derived feature with view indices ---
		List<Notification> derivedNotifications = notifications.stream()
			.filter(n -> n.getFeatureID(null) == daughtersID).toList();
		List<Notification> backingNotifications = notifications.stream()
			.filter(n -> n.getFeatureID(null) == childrenID).toList();
		check(!backingNotifications.isEmpty(), "backing feature notified via write-through");
		// events: ADD(Fiona@3), ADD(Galina@1), REMOVE(Clara@2), SET(Helga@0), ADD(Inga@0)
		check(derivedNotifications.size() == 5, "5 derived notifications, was " + derivedNotifications.size());
		Notification addFiona = derivedNotifications.get(0);
		check(addFiona.getEventType() == Notification.ADD && addFiona.getPosition() == 3
			&& addFiona.getNewValue() == fiona, "ADD Fiona at view index 3");
		Notification addGalina = derivedNotifications.get(1);
		check(addGalina.getEventType() == Notification.ADD && addGalina.getPosition() == 1,
			"ADD Galina at view index 1");
		Notification removeClara = derivedNotifications.get(2);
		check(removeClara.getEventType() == Notification.REMOVE && removeClara.getPosition() == 2
			&& removeClara.getOldValue() == clara, "REMOVE Clara at view index 2");
		Notification setHelga = derivedNotifications.get(3);
		check(setHelga.getEventType() == Notification.SET && setHelga.getPosition() == 0
			&& "Anna".equals(((EObject) setHelga.getOldValue()).eGet(name)),
			"SET Helga at view index 0, old Anna");
		Notification addInga = derivedNotifications.get(4);
		check(addInga.getEventType() == Notification.ADD && addInga.getPosition() == 0,
			"ADD Inga at view index 0");

		// --- clear(): removes only subset members from backing ---
		view.clear();
		check(view.isEmpty(), "view empty after clear");
		List<String> remaining = kids.stream().map(k -> (String) k.eGet(name)).toList();
		check(remaining.equals(List.of("Boris", "Dmitri")), "only sons remain, was " + remaining);

		// --- Setting / reflective-style access ---
		DerivedSubsetEList<EObject> setting = (DerivedSubsetEList<EObject>) view;
		check(setting.getEStructuralFeature() == daughters, "Setting.getEStructuralFeature");
		check(!setting.isSet(), "isSet == false when empty");

		System.out.println("ALL " + checks + " CHECKS PASSED");
		System.out.println("children: " + remaining);
	}
}
