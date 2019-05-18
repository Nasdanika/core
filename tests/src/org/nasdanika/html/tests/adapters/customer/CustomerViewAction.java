package org.nasdanika.html.tests.adapters.customer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.nasdanika.bank.Customer;
import org.nasdanika.bank.CustomerAccount;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.bootstrap.Text.Alignment;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.ViewAction;

/**
 * This class customizes the default view by showing accounts as navigation actions. 
 * In the banking model accounts belong to (contained by) the bank, but from the customer point of view
 * accounts belong to them.
 * 
 * This class customizes the content view by displaying a table of customer accounts with balances.
 * 
 * It also adds context actions such as bill pay and transfer funds. 
 * @author Pavel
 *
 */
public class CustomerViewAction extends EObjectViewAction<Customer> {

	public CustomerViewAction(Customer value) {
		super(value);
	}
		
	@Override
	public String getIcon() {
		// TODO - replace with something like FontAwesome.Literals.UserCircle.solid() once the fontawesome literals generator is available
		return "fas fa-user-circle"; 
	}
	
	@Override
	public List<? extends Action> getChildren() {
		List<Action> ret = new ArrayList<>();
		target.getAccounts().stream()
			.map(account -> EObjectAdaptable.adaptTo(account, ViewAction.class))
			.filter(a -> a != null)
			.forEach(ret::add);
		
		// Loading context actions from the application model for the demo purposes.
		URI appUri = URI.createPlatformPluginURI("org.nasdanika.html.app.model/NasdanikaBank.app", false);
		Resource appResource = target.eResource().getResourceSet().getResource(appUri, true);
		ret.addAll(((Action) appResource.getContents().iterator().next()).getChildren().get(0).getContextChildren());				
		
		return ret;
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator) {
		Table accountsTable = viewGenerator.getBootstrapFactory().table().bordered();
		accountsTable.headerRow("Account", "Balance");
		for (CustomerAccount account: target.getAccounts()) {
			Action accountViewAction = EObjectAdaptable.adaptTo(account, ViewAction.class);
			if (accountViewAction != null) {
				Row ar = accountsTable.row();
				ar.cell(viewGenerator.link(accountViewAction));
				ar.cell(account.getBalance()).text().alignment(Alignment.RIGHT);
			}
		}
		return accountsTable;
	}

}
