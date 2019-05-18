package org.nasdanika.html.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.junit.Before;
import org.junit.Test;
import org.nasdanika.bank.Bank;
import org.nasdanika.bank.BankPackage;
import org.nasdanika.bank.Customer;
import org.nasdanika.html.app.Application;
import org.nasdanika.html.app.ApplicationBuilder;
import org.nasdanika.html.app.ApplicationFactory;
import org.nasdanika.html.app.Identity;
import org.nasdanika.html.app.NavigationActionActivator;
import org.nasdanika.html.app.PropertyDescriptor;
import org.nasdanika.html.app.PropertySource;
import org.nasdanika.html.app.impl.BootstrapContainerApplication;
import org.nasdanika.html.emf.ComposedAdapterFactory;
import org.nasdanika.html.emf.EClassPropertySource;
import org.nasdanika.html.emf.ENamedElementLabel;
import org.nasdanika.html.emf.EObjectAdaptable;
import org.nasdanika.html.emf.EObjectViewAction;
import org.nasdanika.html.emf.EStructuralFeatureLabel;
import org.nasdanika.html.emf.FunctionAdapterFactory;
import org.nasdanika.html.emf.SupplierAdapterFactory;
import org.nasdanika.html.emf.ViewAction;
import org.nasdanika.html.emf.ViewActionActivator;
import org.nasdanika.html.emf.ViewActionApplicationBuilder;
import org.nasdanika.html.fontawesome.FontAwesomeFactory;
import org.nasdanika.html.jstree.JsTreeFactory;
import org.nasdanika.html.tests.adapters.customer.CustomerViewAdapterFactory;


public class TestEmf extends HTMLTestBase {
	
	protected Bank bank;
	private ComposedAdapterFactory composedAdapterFactory;
	
	@Before
	public void loadModels() throws Exception {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMIResourceFactoryImpl());
		
		resourceSet.getPackageRegistry().put(BankPackage.eNS_URI, BankPackage.eINSTANCE);
		URI bankUri = URI.createPlatformPluginURI("org.nasdanika.bank/Nasdanika.bank", false);
		Resource bankResource = resourceSet.getResource(bankUri, true);
		bank = (Bank) bankResource.getContents().iterator().next();
		
		composedAdapterFactory = new ComposedAdapterFactory();
		class BootstrapContainerApplicationFactory implements ApplicationFactory {

			@Override
			public Application createApplication() {
				Application app =  new BootstrapContainerApplication();
				JsTreeFactory.INSTANCE.cdn(app.getHTMLPage());
				FontAwesomeFactory.INSTANCE.cdn(app.getHTMLPage());				
				return app;
			}
			
		}
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
		
		// Identity
		Map<EObject, String> idMap = new HashMap<>();
		Function<EObject, Identity> identityManager = new Function<EObject, Identity>() {

			@Override
			public Identity apply(EObject eObject) {
				return () -> idMap.computeIfAbsent(eObject, eObj -> eObj.eClass().getName()+"-"+idMap.size()) ;
			}
			
		};
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
		
		resourceSet.getAdapterFactories().add(composedAdapterFactory);						
	}
	
	@Test
	public void testENamedElementLabel() {
		ENamedElementLabel<EAttribute> label = new EStructuralFeatureLabel<EAttribute>(BankPackage.Literals.ACCOUNT__PERIOD_START);
		assertEquals("Period start", label.getText());
		System.out.println("Account period start label id: "+label.getId());
	}
	
	@Test
	public void testEClassPropertySource() {
		System.out.println("--- EClass property descriptors ---");
		for (EClassifier ec: BankPackage.eINSTANCE.getEClassifiers()) {			
			if (ec instanceof EClass) {
				System.out.println("\t"+ec.getName());
				PropertySource ps = new EClassPropertySource((EClass) ec, null);
				for (PropertyDescriptor pd: ps.getPropertyDescriptors()) {
					System.out.println("\t\t"+pd.getText());
				}				
			}
		}
		System.out.println("---");
	}
		
	@Test
	public void testBankApplication() throws Exception {
		TreeIterator<EObject> tit = bank.eResource().getAllContents();
		while (tit.hasNext()) {
			EObject next = tit.next();
			Application application = EObjectAdaptable.adaptTo(next, ApplicationFactory.class).createApplication();
			assertNotNull(application);
			
			ApplicationBuilder applicationBuilder = EObjectAdaptable.adaptTo(next, ApplicationBuilder.class);
			assertNotNull(applicationBuilder);
			applicationBuilder.build(application);
			
			NavigationActionActivator activator = (NavigationActionActivator) EObjectAdaptable.adaptTo(next, ViewActionActivator.class);
			writeFile("emf/bank/"+activator.getUrl(), application.toString());
		}
	}
	
	/**
	 * Generates a customer view of the bank.
	 * @throws Exception
	 */
	@Test
	public void testCustomerApplication() throws Exception {
		// Registering customer-view specific adapters.
		Customer[] contextCustomer = {null};
		composedAdapterFactory.registerAdapterFactory(new CustomerViewAdapterFactory(() -> contextCustomer[0]));

		// Registering test-specific customer identity adapter.
		Function<EObject, Identity> identityManager = eObj -> () -> "index";
		composedAdapterFactory.registerAdapterFactory(
				new FunctionAdapterFactory<Identity, EObject>(
						BankPackage.Literals.CUSTOMER, 
						Identity.class, 
						this.getClass().getClassLoader(), 
						identityManager));
		
		for (Customer customer: bank.getCustomers()) {
			contextCustomer[0] = customer;
			
			TreeIterator<EObject> tit = bank.eResource().getAllContents();
			while (tit.hasNext()) {
				EObject next = tit.next();
				
				// All customers output index.html, process only the context customer.
				if (next instanceof Customer && next != customer) {
					continue;
				}
				
				Application application = EObjectAdaptable.adaptTo(next, ApplicationFactory.class).createApplication();
				assertNotNull(application);
				
				ApplicationBuilder applicationBuilder = EObjectAdaptable.adaptTo(next, ApplicationBuilder.class);
				assertNotNull(applicationBuilder);
				applicationBuilder.build(application);
				
				NavigationActionActivator activator = (NavigationActionActivator) EObjectAdaptable.adaptTo(next, ViewActionActivator.class);
				writeFile("emf/customer/"+customer.getName().toLowerCase().replace(' ', '-')+"/"+activator.getUrl(), application.toString());
			}
		}
	}
	
}
