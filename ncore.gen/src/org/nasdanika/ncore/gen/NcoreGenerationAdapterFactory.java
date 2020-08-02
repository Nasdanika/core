package org.nasdanika.ncore.gen;

import org.nasdanika.common.SupplierFactory;
import org.nasdanika.emf.ComposedAdapterFactory;
import org.nasdanika.emf.FunctionAdapterFactory;
import org.nasdanika.ncore.Array;
import org.nasdanika.ncore.Function;
import org.nasdanika.ncore.Html;
import org.nasdanika.ncore.HttpCall;
import org.nasdanika.ncore.List;
import org.nasdanika.ncore.NcorePackage;
import org.nasdanika.ncore.Null;
import org.nasdanika.ncore.Operation;
import org.nasdanika.ncore.Reference;
import org.nasdanika.ncore.Resource;
import org.nasdanika.ncore.SupplierEntry;
import org.nasdanika.ncore.TypedElement;
import org.nasdanika.ncore.TypedEntry;
import org.nasdanika.ncore.Value;

/**
 * Generation adapter factory for Vinci components.
 * @author Pavel
 *
 */
public class NcoreGenerationAdapterFactory extends ComposedAdapterFactory {
	
	@SuppressWarnings("rawtypes")
	public NcoreGenerationAdapterFactory() {
		// Registering adapter factories.
		registerAdapterFactory(
			new FunctionAdapterFactory<SupplierFactory, Array>(
				NcorePackage.Literals.ARRAY, 
				SupplierFactory.class, 
				this.getClass().getClassLoader(),
				ArraySupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, HttpCall>(
					NcorePackage.Literals.HTTP_CALL, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					HttpCallSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, TypedElement>(
					NcorePackage.Literals.TYPED_ELEMENT, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					TypedElementSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, org.nasdanika.ncore.Supplier>(
					NcorePackage.Literals.SUPPLIER, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					SupplierSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, Null>(
					NcorePackage.Literals.NULL, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					NullSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, Resource>(
					NcorePackage.Literals.RESOURCE, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					ResourceSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, Html>(
					NcorePackage.Literals.HTML, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					HtmlSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, Operation>(
					NcorePackage.Literals.OPERATION, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					OperationSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, Reference>(
					NcorePackage.Literals.REFERENCE, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					ReferenceSupplierFactory::new));

//		registerAdapterFactory(
//				new FunctionAdapterFactory<SupplierFactory, RestFunction>(
//					NcorePackage.Literals.REST_FUNCTION, 
//					SupplierFactory.class, 
//					this.getClass().getClassLoader(),
//					RestOperationSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, Value>(
					NcorePackage.Literals.VALUE, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					ValueSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, org.nasdanika.ncore.Map>(
					NcorePackage.Literals.MAP, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					MapSupplierFactory::new));

//		registerAdapterFactory(
//				new FunctionAdapterFactory<SupplierFactory, Property>(
//					NcorePackage.Literals.PROPERTY, 
//					SupplierFactory.class, 
//					this.getClass().getClassLoader(),
//					ValueSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, List>(
					NcorePackage.Literals.LIST, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					ArraySupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, Function>(
					NcorePackage.Literals.FUNCTION, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					OperationSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, SupplierEntry>(
					NcorePackage.Literals.SUPPLIER_ENTRY, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					SupplierSupplierFactory::new));

		registerAdapterFactory(
				new FunctionAdapterFactory<SupplierFactory, TypedEntry>(
					NcorePackage.Literals.TYPED_ENTRY, 
					SupplierFactory.class, 
					this.getClass().getClassLoader(),
					TypedElementSupplierFactory::new));

//		registerAdapterFactory(
//				new FunctionAdapterFactory<SupplierFactory, org.nasdanika.ncore.Object>(
//					NcorePackage.Literals.OBJECT, 
//					SupplierFactory.class, 
//					this.getClass().getClassLoader(),
//					MapSupplierFactory::new));
		
	}

}
