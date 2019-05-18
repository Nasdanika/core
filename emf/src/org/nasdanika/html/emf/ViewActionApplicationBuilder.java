package org.nasdanika.html.emf;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Adaptable;
import org.nasdanika.html.app.ViewPart;
import org.nasdanika.html.app.impl.AbstractActionApplicationBuilder;

/**
 * Generates application by adapting the target to {@link ViewAction}
 * and then delegating to view part adapters and falling back to super behavior if there are no registered adapters.
 * @author Pavel
 *
 */
public class ViewActionApplicationBuilder<T extends EObject> extends AbstractActionApplicationBuilder implements Adaptable {
				
	protected T target;
	
	public ViewActionApplicationBuilder(T target) {
		this.target = target;
	}
	
	@Override
	protected Action getActiveAction() {
		return adaptTo(ViewAction.class);
	}	
		
	@Override
	protected ViewPart getHeaderViewPart() {
		ViewPart headerViewPart = adaptTo(HeaderViewPart.class);
		return headerViewPart != null ? headerViewPart : super.getHeaderViewPart();
	}
	
	@Override
	protected ViewPart getNavigationBarViewPart() {
		ViewPart navigationBarViewPart = adaptTo(NavigationBarViewPart.class);
		return navigationBarViewPart != null ? navigationBarViewPart : super.getNavigationBarViewPart();
	}
	
	@Override
	protected ViewPart getNavigationPanelViewPart() {
		ViewPart navigationPanelViewPart = adaptTo(NavigationPanelViewPart.class);
		return navigationPanelViewPart != null ? navigationPanelViewPart : super.getNavigationPanelViewPart();
	}
	
	@Override
	protected ViewPart getContentPanelViewPart() {
		ViewPart contentPanelViewPart = adaptTo(ContentPanelViewPart.class);
		return contentPanelViewPart != null ? contentPanelViewPart : super.getContentPanelViewPart();
	}
	
	@Override
	protected ViewPart getFooterViewPart() {
		ViewPart footerViewPart = adaptTo(FooterViewPart.class);
		return footerViewPart != null ? footerViewPart : super.getFooterViewPart();
	}
	
	@Override
	public <A> A adaptTo(Class<A> type) {
		return EObjectAdaptable.adaptTo(target, type);
	}
		
};
