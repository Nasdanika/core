package org.nasdanika.html.tests.adapters.customer;

import java.util.function.Supplier;

import org.nasdanika.bank.Bank;
import org.nasdanika.bank.BankPackage;
import org.nasdanika.bank.Customer;
import org.nasdanika.bank.CustomerAccount;
import org.nasdanika.html.emf.ComposedAdapterFactory;
import org.nasdanika.html.emf.FunctionAdapterFactory;
import org.nasdanika.html.emf.InstanceAdapterFactory;
import org.nasdanika.html.emf.NavigationPanelViewPart;
import org.nasdanika.html.emf.ViewAction;

/**
 * Provides adapters for the customer view of the bank.
 * @author Pavel
 *
 */
public class CustomerViewAdapterFactory extends ComposedAdapterFactory {
	
	public CustomerViewAdapterFactory(Supplier<Customer> contextCustomerSupplier) {
		// Registering customer-view specific adapters.
		registerAdapterFactory(
				new FunctionAdapterFactory<ViewAction, Customer>(
						BankPackage.Literals.CUSTOMER, 
						ViewAction.class, 
						this.getClass().getClassLoader(), 
						CustomerViewAction::new));
		
		// Bank view adapter factory is aware of the context customer
		registerAdapterFactory(
				new FunctionAdapterFactory<ViewAction, Bank>(
						BankPackage.Literals.BANK,
						ViewAction.class, 
						this.getClass().getClassLoader(), 
						bank -> new BankViewAction(bank, contextCustomerSupplier)));
		
		// Bank Navigation panel view part adapter - rendering nothing.
		registerAdapterFactory(
				new InstanceAdapterFactory<NavigationPanelViewPart>(
						BankPackage.Literals.BANK,
						NavigationPanelViewPart.class, 
						this.getClass().getClassLoader(), 
						viewGenerator -> ""));
		
		// Customer account view adapter factory is aware of the context customer
		registerAdapterFactory(
				new FunctionAdapterFactory<ViewAction, CustomerAccount>(
						BankPackage.Literals.CUSTOMER_ACCOUNT,
						ViewAction.class, 
						this.getClass().getClassLoader(), 
						customerAccount -> new CustomerAccountViewAction(customerAccount, contextCustomerSupplier)));
		
	}

}
