package org.nasdanika.ncore.util;

import java.util.UUID;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.nasdanika.emf.Palette;
import org.nasdanika.ncore.Entity;

/**
 * Creates a copy of the template entity and then assigns new unique ID's to the entity and its 
 * entity components. 
 * @author Pavel
 *
 */
public class EntityContributor implements Palette.Contributor {

	private EObject template;
	private String id;

	public EntityContributor(EObject template, String id) {
		this.template = template;
		this.id = id;
	}

	@Override
	public EObject get() {
		EObject ret = EcoreUtil.copy(template);
		if (ret instanceof Entity) {
			((Entity) ret).setId(UUID.randomUUID().toString());		
		}
		ret.eAllContents().forEachRemaining(e -> {
			if (e instanceof Entity) {
				((Entity) e).setId(UUID.randomUUID().toString());		
			}
		});
		return ret;
	}

	@Override
	public String getId() {
		return id;
	}

}
