package org.nasdanika.html.tests.adapters.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.nasdanika.bank.Bank;
import org.nasdanika.bank.Customer;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.app.impl.ActionImpl;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.ViewAction;

/**
 * This class customizes bank view by providing context actions to be displayed in the footer and 
 * one navigation action for the context customer. 
 * @author Pavel
 *
 */
public class BankViewAction extends EObjectViewAction<Bank> {

	private Supplier<Customer> customerSupplier;

	public BankViewAction(Bank bank, Supplier<Customer> customerSupplier) {
		super(bank);
		this.customerSupplier = customerSupplier;
	}
	
	@Override
	public List<? extends Action> getChildren() {
		List<Action> ret = new ArrayList<>();
		Customer customer = customerSupplier.get();
		if (customer != null) {
			Action customerViewAction = EObjectAdaptable.adaptTo((EObject) customer, ViewAction.class);
			if (customerViewAction != null) {
				ret.add(customerViewAction); // Shall have navigation role.
			}
		}
		
		// Option - use a template for loading context actions.
		ActionImpl contactUs = new ActionImpl();
		contactUs.setText("Contact Us");
		contactUs.setActivator(new NavigationActionActivator() {
			
			@Override
			public String getUrl() {
				return "#";
			}
			
		});
		contactUs.setIcon("far fa-envelope");
		contactUs.getRoles().add(Action.Role.CONTEXT);
		ret.add(contactUs);
		
		return ret;
	}
	
	/**
	 * Displaying static content which may be interpolated. This implementation uses empty token source for interpolation, i.e. does nothing.
	 * Other options include instantiating app model template (once this functionality is available).
	 */
	@Override
	public Object generate(ViewGenerator viewGenerator) {
		return viewGenerator.getHTMLFactory().mutableTokenSource().interpolate(getClass().getResource("BankHomePage.html"));
	}
}
