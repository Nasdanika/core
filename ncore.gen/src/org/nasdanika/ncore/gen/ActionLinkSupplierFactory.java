package org.nasdanika.ncore.gen;

import org.nasdanika.common.Context;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.Supplier;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;

/**
 * Adapts {@link ActionLink} to {@link SupplierFactory} in order to generate HTML anchor.
 * @author Pavel
 *
 */
//public class ActionLinkSupplierFactory implements SupplierFactory<ViewPart> {
//	
//	private ActionLink actionLink;
//
//	public ActionLinkSupplierFactory(ActionLink actionLink) {
//		this.actionLink = actionLink;
//	}
//	
//	static ActionBase unwrap(AbstractAction target) {
//		if (target instanceof ActionBase) {
//			return (ActionBase) target;
//		}
//		
//		if (target instanceof ActionReference) {
//			return unwrap(((ActionReference) target).getAction());
//		}
//		
//		throw new UnsupportedOperationException("Unwrapping of " + target + " is not supported (yet)");
//	}		
//	
//	@Override
//	public Supplier<ViewPart> create(Context context) throws Exception {
//		String targetId = unwrap(actionLink.getTarget()).getId();
//		
//		if (Util.isBlank(targetId)) {
//			throw new IllegalStateException("Action has no ID. Linked actions must have unique id's");
//		}
//
//		Supplier<ViewPart> ret = Supplier.fromCallable(() -> {
//			return new ViewPart() {
//				
//				@Override
//				public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {
//					ActionRegistry registry = viewGenerator.get(ActionRegistry.class);
//					if (registry == null) {
//						throw new IllegalStateException("ActionRegistry service is not present in the view generator- cannot find actions by their ids's");
//					}
//					Action action = registry.get(targetId);
//					if (action == null) {
//						throw new IllegalStateException("Action with ID '" + targetId + "' was not found in the action registry");						
//					}
//					
//					ViewGenerator alvg = viewGenerator.fork();
//					alvg.put(Decorator.SELECTOR_KEY, "action-link");
//					return alvg.link(new ActionFilter<Action>(action) {
//						
//						public String getText() {
//							String linkText = actionLink.getText();
//							return Util.isBlank(linkText) ? super.getText() : linkText;
//						}
//						
//					});
//				}
//				
//				@Override
//				public String toString() {
//					return "Action link view part "+super.toString();
//				}
//				
//			};
//		}, actionLink.getTitle(), 1);
//				
//		Appearance appearance = actionLink.getAppearance();
//		if (appearance == null) {
//			return ret;
//		}
//		
//		return ret.then(appearance.create(context).asFunction()).then(bs -> new ViewPart() {
//
//			@Override
//			public Object generate(ViewGenerator viewGenerator, ProgressMonitor progressMonitor) {				
//				return bs.getFirst().then(bs.getSecond());
//			}
//			
//		});
//		
//	}
//	
//}
