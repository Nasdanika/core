# EMF

[Java API](apidocs/org.nasdanika.html.emf/apidocs/index.html) for generating Web UI from EObject's data and metadata.

The philosophy of this bundle is generation of a Web UI for EObjects by adapting them to different UI generation participants as outlined below:

* Target EObject is adapted to [Application](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/Application.html). Customization of this adapter will change a general appearance of the application. 
* Target EObject is adapted to [ApplicationBuilder](apidocs/org.nasdanika.html.app/apidocs/index.html?org/nasdanika/html/app/ApplicationBuilder.html), e.g. [ViewActionApplicationBuilder](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ViewActionApplicationBuilder.html).
* ViewActionApplicationBuilder adapts the target EObject to [ViewAction](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ViewAction.html), which is implemented by [EObjectViewAction](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/EObjectViewAction.html), which can be subclassed and customized as needed.
* It also adapts the target EObject to different view parts:
    * [HeaderViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/HeaderViewPart.html)
    * [NavigationBarViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/NavigationBarViewPart.html)
    * [NavigationPanelViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/NavigationPanelViewPart.html)
    * [ContentPanelViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ContentPanelViewPart.html)
    * [FooterViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/FooterViewPart.html)

Access to contextual information can be done by adapting EObjects to context-providing interfaces. E.g. in the case of dynamic web applications access to servlet API's such as request/response may also be done by adapting EObject to HTTPServletRequest and HTTPServletResponse.

``EObjectViewAction`` extends [EObjectSingleValueDataSource](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/EObjectSingleValueDataSource.html). 
It functions in the following way:

* ``getChildren()`` method creates children from EReferences. Child role depends on EReference containment and cardinality.
* ``execute()`` method creates [ViewSingleValuePropertySourceViewPart](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/app/viewparts/ViewSingleValuePropertySourceViewPart.html) from self. Property source properties are created from EObject's EClass structural features which have read permission and are single value and non-containment (for references).

An example of default UI generation behavior for a bank [customer](https://www.nasdanika.org/products/bank/1.0.0-SNAPSHOT/modeldoc/index.html#router/doc-content/75726e3a6f72672e6e617364616e696b612e62616e6b/Customer.html) can be found [here](test-dumps/emf/bank/Customer-36.html). Customization of the default generation is be explained in "Customizing UI Generation" section.

## Adaptation

EObjects can be adapted to other types with [EObjectAdaptable](apidocs/parent/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/EObjectAdaptable.html).adaptTo() method.
This method delegates to ``EcoreUtil.getRegisteredAdapter()``. 

It has a special handling of [AccessController](apidocs/parent/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/AccessController.html) - if there is no AccessController adapter for a given EObject, and that EObject is contained by another EObject, then the container object is adapted to AccessController and, if  the adapter is not null, the method returns AccessController which adds the containment as qualifier suffix. In other words, permission checks "bubble-up" by adding feature path as a qualifier. 

For example, ``Customer`` object has ``accounts`` reference containing ``Account`` objects. When one of such account objects is adapted to ``AccessController`` and it doesn't have its own adapter defined it would receive an AccessController adapter delegating to Customer's access controller and adding ``accounts`` as a qualifier. E.g. in case of calling ``canRead("balance")`` on the account object it will call ``hasPermission("read", "accounts/balance")`` on Customer's access controller, which in turn may delegate to its container. 

### Adapter factories

``EcoreUtil.getRegisteredAdapter()`` finds the first registered adapter factory for a target type, i.e. a factory which returns ``true`` from its ``isFactoryForType()``, without taking the source EObject into consideration. 
[ComposedAdapterFactory](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ComposedAdapterFactory.html) allows to register child factories for specific EObject types and adapter types. 
The factory takes EObject inheritance into account - a child factory for a supertype is used if there no registered factory for a type.
A child adapter factory registered for EObject EClass will be used for all EObjects without a more specialized factory registration.

In order to simplify adapter registration and to reduce the number of classes and amount of code to write ``org.nasdanika.html.emf`` bundle provides several factories which create [dynamic proxies](https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html) implementing both ``Adapter`` and adapter type interfaces:

* [DelegatingAdapterfactory](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/DelegatingAdapterFactory.html) - base abstract class for the following factories. Adapters created by its ``doCreateAdapter()`` method do not have to implement ``Adapter`` interface. If they do, they are returned as-is, if they don't, they are wrapped into a dynamic proxy implementing both ``Adapter`` and the adapter type interfaces.
* [FunctionAdapterfactory](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/FunctionAdapterFactory.html) - applies a function to create an adapter. Such a function may be a single-argument constructor which takes EObject being adapted as an argument.
* [InstanceAdapterfactory](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/InstanceAdapterFactory.html) - Uses a single adapter instance for all EObject's. This factory may be used for contextual adapters. For example, if a resource set is created and used in the context of HTTP request processing then ``HttpServletRequest`` instance can be registered with this adapter factory to be available to all EObject's via ``adaptTo()`` method.
* [SupplierAdapterfactory](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/SupplierAdapterFactory.html) - retrieves adapter from a supplier. Such a supplier may be a parameterless constructor.
      
### Registering adapter factories

To register adapter factories with a resource set:
* Create a composed adapter factory.
* Register adapter factories with the composed adapter factory.
* Register the composed adapter factory with the resource set.

Example:

```
ComposedAdapterFactory composedAdapterFactory = new ComposedAdapterFactory();

composedAdapterFactory.registerAdapterFactory(
		new SupplierAdapterFactory<ApplicationFactory>(
				ApplicationFactory.class, 
				this.getClass().getClassLoader(), 
				BootstrapContainerApplicationFactory::new));
		
composedAdapterFactory.registerAdapterFactory(
		new FunctionAdapterFactory<ApplicationBuilder, EObject>(
				ApplicationBuilder.class, 
				this.getClass().getClassLoader(), 
				ViewActionApplicationBuilder::new));
		
composedAdapterFactory.registerAdapterFactory(
		new FunctionAdapterFactory<ViewAction, EObject>(
				ViewAction.class, 
				this.getClass().getClassLoader(), 
				EObjectViewAction::new));
		
resourceSet.getAdapterFactories().add(composedAdapterFactory);						
```

Use ComposedAdapterFactory to group several adapter factories. Example:

```
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
```

Then you can register just the composed adapter factory with a resource set or another composed adapter factory.

## Access control

``EObjectViewAction`` and other classes such as [EClassPropertySource](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/EClassPropertySource.html) adapt their target object to ``AccessController`` to customize UI generation.
For example, ``EClassPropertySource`` checks for ``read`` permission for EStructuralFeature in order to include it as a property into the property set and
``EObjectViewAction`` does the same for child features.

## Object identity and action activators

``EObjectViewAction.getActionActivator()`` method computes action activator by adapting the target EObject to [ViewActionActivator](apidocs/org.nasdanika.html.emf/apidocs/index.html?org/nasdanika/html/emf/ViewActionActivator.html).

The code snippet below shows how to implement view action activator using object identity.  

```
// Identity manager maps EObject to Identity. 
// Implementation-specific, e.g. for CDO objects identity may be derived from CDOID.
Function<EObject, Identity> identityManager = ... 

composedAdapterFactory.registerAdapterFactory(
		new FunctionAdapterFactory<Identity, EObject>(
				Identity.class, 
				this.getClass().getClassLoader(), 
				identityManager));
				
// View action activator
class IdentityNavigationViewActionActivatorAdapter implements ViewActionActivator, NavigationActionActivator {
	
	private EObject target;

	public IdentityNavigationViewActionActivatorAdapter(EObject target) {
		this.target = target;
	}

	@Override
	public String getUrl() {
		Identity identity = EObjectAdaptable.adaptTo(target, Identity.class);
		return identity == null ? null : identity.getId()+".html";
	}
	
}
composedAdapterFactory.registerAdapterFactory(
		new FunctionAdapterFactory<ViewActionActivator,EObject>(
				ViewActionActivator.class, 
				this.getClass().getClassLoader(), 
				IdentityNavigationViewActionActivatorAdapter::new));
```

## Customizing UI Generation  

In some situations the default UI generation needs to be customized.
For example, in the [bank](https://www.nasdanika.org/products/bank/1.0.0-SNAPSHOT/modeldoc/index.html#router/doc-content/75726e3a6f72672e6e617364616e696b612e62616e6b/package-summary.html) model accounts "belong" (are contained by) the bank and transactions also belong to the bank. 
However, from the customer point of view accounts belong to the customer and transactions belong to statements. 
Also, representation of transaction and other objects from the customer point of view is different from the default representation. 
The default representation may possibly be used by a a bank clerk, but for a customer it would be hard to understand. 
Also the default representation reveals internal bank information which shall not be visible by the bank customers.

UI customization is achieved via model annotations and custom adapters. Such adapters are typically created by sub-classing existing classes.
This section describes an example of such customization:
  * [Sources](https://github.com/Nasdanika/html/tree/master/tests/src/org/nasdanika/html/tests/adapters/customer)
  * [API documentation](apidocs/org.nasdanika.html.tests/apidocs/index.html?org/nasdanika/html/tests/adapters/customer/package-summary.html)
  * [Results](test-dumps/emf/customer/john-doe/index.html).

### Model annotations

Element icon, color and outline flag may be customized by adding an annotation to the corresponding EMF model element with source ``org.nasdanika.html`` and the following detail keys:

* ``icon`` - the value will be returned from getIcon() method. Value example: ``fas fa-university``.
* ``color`` - [bootstrap color](apidocs/org.nasdanika.html.bootstrap/apidocs/index.html?org/nasdanika/html/bootstrap/Color.html) name, e.g. ``INFO``.
* ``outline`` - ``true`` for outline, any other value or no other value otherwise. 

### BankViewAction

From the customer point of view bank has only one child object - the customer and as such the bank view action shall have one navigation child action for the "context" customer.
In addition to the child action it shall have context actions to be displayed in the footer.
In this class such context actions are defined in the ``getChildren()`` method. 

```
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
```	

The bank icon is loaded from the model annotation using the inherited implementation of the ``getIcon()`` method.
Bank "home page" is implemented as static content loaded from ``BankHomePage.html`` classloader resource.

```	
	/**
	 * Displaying static content which may be interpolated. This implementation uses empty token source for interpolation, i.e. does nothing.
	 * Other options include instantiating app model template (once this functionality is available).
	 */
	@Override
	public Object execute(ViewGenerator viewGenerator) {
		return viewGenerator.getHTMLFactory().mutableTokenSource().interpolate(getClass().getResource("BankHomePage.html"));
	}
}

```

### CustomerViewAction

Although from the bank's point of view, which is reflected in the model, customer accounts are owned by the bank because there might be several account owners, from the customer point of view customer's accounts are owned by the customer. 
This class customizes the default behavior by listing customer account view actions as navigation children, so they appear in the navigation panel.
This class also customizes the icon, because there is no icon model annotation for the Customer EClass, and ``execute()`` method to show a table of accounts with their balances.  

```
public class CustomerViewAction extends EObjectViewAction<Customer> {

	public CustomerViewAction(Customer value) {
		super(value);
	}
		
	@Override
	public String getIcon() {
		// TODO - replace with something like FontAwesome.Literals.UserCircle.solid() once the font awesome literals generator is available
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
	public Object execute(ViewGenerator viewGenerator) {
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
```

### CustomerAccountViewAction

This class customizes:

* Account icon.
* Account text by including product name.
* ``execute()`` method by showing the current balance and a table of current (unbilled) transactions. 
* Clears the default categorization.
* Suppresses default account children (statements) so they do not appear in the navigation panel.

```
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
	public Object execute(ViewGenerator viewGenerator) {		
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
```

### CustomerViewAdapterFactory

This class groups registrations of the adapter factories for the above classes so they can be added to the resource set or parent composed adapter factory together.

```
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
```

The source code of this class was already shown in "Registering adapter factories" section.   
