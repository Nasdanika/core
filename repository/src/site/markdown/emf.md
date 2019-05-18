# EMF

## Adaptation

### Adapter factories

``EcoreUtil.getRegisteredAdapter()`` finds the first registered adapter factory for a target type, i.e. a factory which returns ``true`` from its ``isFactoryForType()``, without taking the source EObject into consideration. 
[ComposedAdapterFactory](apidocs/org.nasdanika.emf/apidocs/index.html?org/nasdanika/emf/ComposedAdapterFactory.html) allows to register child factories for specific EObject types and adapter types. 
The factory takes EObject inheritance into account - a child factory for a supertype is used if there no registered factory for a type.
A child adapter factory registered for EObject EClass will be used for all EObjects without a more specialized factory registration.

In order to simplify adapter registration and to reduce the number of classes and amount of code to write ``org.nasdanika.emf`` bundle provides several factories which create [dynamic proxies](https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html) implementing both ``Adapter`` and adapter type interfaces:

* [DelegatingAdapterfactory](apidocs/org.nasdanika.emf/apidocs/index.html?org/nasdanika/emf/DelegatingAdapterFactory.html) - base abstract class for the following factories. Adapters created by its ``doCreateAdapter()`` method do not have to implement ``Adapter`` interface. If they do, they are returned as-is, if they don't, they are wrapped into a dynamic proxy implementing both ``Adapter`` and the adapter type interfaces.
* [FunctionAdapterfactory](apidocs/org.nasdanika.emf/apidocs/index.html?org/nasdanika/emf/FunctionAdapterFactory.html) - applies a function to create an adapter. Such a function may be a single-argument constructor which takes EObject being adapted as an argument.
* [InstanceAdapterfactory](apidocs/org.nasdanika.emf/apidocs/index.html?org/nasdanika/emf/InstanceAdapterFactory.html) - Uses a single adapter instance for all EObject's. This factory may be used for contextual adapters. For example, if a resource set is created and used in the context of HTTP request processing then ``HttpServletRequest`` instance can be registered with this adapter factory to be available to all EObject's via ``adaptTo()`` method.
* [SupplierAdapterfactory](apidocs/org.nasdanika.emf/apidocs/index.html?org/nasdanika/emf/SupplierAdapterFactory.html) - retrieves adapter from a supplier. Such a supplier may be a parameterless constructor.
      
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

TODO - access controller, base for EObjects with built-in access control.

