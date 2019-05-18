package org.nasdanika.html.tests.adapters.customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import org.nasdanika.bank.Customer;
import org.nasdanika.bank.CustomerAccount;
import org.nasdanika.bank.Transaction;
import org.nasdanika.html.app.Action;
import org.nasdanika.html.app.Label;
import org.nasdanika.html.app.ViewGenerator;
import org.nasdanika.html.bootstrap.RowContainer.Row;
import org.nasdanika.html.bootstrap.Table;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.ViewAction;

/**
 * Returns context customer view action as its parent.
 * @author Pavel
 *
 */
public class CustomerAccountViewAction extends EObjectViewAction<CustomerAccount> {

	private Supplier<Customer> customerSupplier;

	public CustomerAccountViewAction(CustomerAccount value, Supplier<Customer> customerSupplier) {
		super(value);
		this.customerSupplier = customerSupplier;
	}
		
	@Override
	public String getIcon() {
		// TODO - replace with something like FontAwesome.Literals.CreditCard.regular() once the fontawesome literals generator is available
		return "far fa-credit-card"; 
	}

	@Override
	public Action getParent() {
		if (customerSupplier == null) {
			return null;
		}
		Customer customer = customerSupplier.get();		
		return customer == null ? null : EObjectAdaptable.adaptTo(customer, ViewAction.class);
	}
	
	@Override
	public String getText() {
		return target.getProduct().getName() + " " + target.getNumber();
	}
	
	/**
	 * No need in showing account category because it is the only one.
	 */
	@Override
	public Label getCategory() {
		return null;
	}
	
	@Override
	public List<? extends Action> getChildren() {
		// No navigation children, add context and section children as needed.
		return Collections.emptyList();
	}
	
	@Override
	public Object generate(ViewGenerator viewGenerator) {		
		// Current transactions table ordered reverse chronological.
		List<Transaction<?>> currentTransactions = new ArrayList<>();
		target.getStatements().stream().filter(s -> s.getClosingDate() == null).forEach(s -> {
			currentTransactions.addAll(s.getDebits());
			currentTransactions.addAll(s.getCredits());
		});
		
		// Reverse chronological order.
		currentTransactions.sort((t1, t2) -> t2.getDate().compareTo(t1.getDate()));

		Table currentTransactionsTable = viewGenerator.getBootstrapFactory().table().bordered();
		currentTransactionsTable.headerRow("Date", "Comment", "Debit", "Credit");
		
		for (Transaction<?> transaction: currentTransactions) {
			Row transactionRow = currentTransactionsTable.row();
			transactionRow.cell(transaction.getDate());
			transactionRow.cell(transaction.getComment());
			if (transaction.getDebit().eContainer() == target) {
				transactionRow.cell(transaction.getAmount());
				transactionRow.cell("");
			} else {
				transactionRow.cell("");				
				transactionRow.cell(transaction.getAmount());
			}
		}

		return viewGenerator.getHTMLFactory().fragment("Balance: ", target.getBalance(), "<h4>Current transactions</h4>", currentTransactionsTable);
	}
	
}
