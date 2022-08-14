package org.nasdanika.exec.gen;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.nasdanika.common.BiSupplier;
import org.nasdanika.common.DefaultConverter;
import org.nasdanika.common.ExecutionException;
import org.nasdanika.common.Function;
import org.nasdanika.common.FunctionFactory;
import org.nasdanika.common.MapCompoundSupplierFactory;
import org.nasdanika.common.ProgressMonitor;
import org.nasdanika.common.SupplierFactory;
import org.nasdanika.common.Util;
import org.nasdanika.emf.EObjectAdaptable;
import org.nasdanika.exec.Eval;

public class EvalSupplierFactoryProviderAdapter extends AdapterImpl implements SupplierFactory.Provider {
	
	private static final String CONTEXT_BINDING = "context";
	private static final String PROGRESS_MONITOR_BINDING = "progressMonitor";
	
	public EvalSupplierFactoryProviderAdapter(Eval eval) {
		setTarget(eval);
	}
	
	@Override
	public boolean isAdapterForType(Object type) {
		return type == SupplierFactory.class;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> SupplierFactory<T> getFactory(Class<T> type) {
		Eval eval = (Eval) getTarget();
		
		MapCompoundSupplierFactory<String, Object> bindingsFactory = new MapCompoundSupplierFactory<String, Object>("Bindings");
		EMap<String, EObject> bindings = eval.getBindings();
		for (Entry<String, EObject> ce: bindings.entrySet()) {
			EObject value = ce.getValue();
			SupplierFactory<?> sf = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(value, Object.class), "Cannot adapt " + value + " to SupplierFactory");				
			bindingsFactory.put(ce.getKey(), sf.then(Util.OBJECT_TO_STRING_FACTORY));
		}
		
		SupplierFactory<InputStream> scriptFactory = Objects.requireNonNull(EObjectAdaptable.adaptToSupplierFactory(eval.getScript(), InputStream.class), "Cannot adapt " + eval.getScript() + " to SupplierFactory");		
		
		
		
		if (type == null || type == Object.class) {
			FunctionFactory<BiSupplier<InputStream, Map<String,Object>>, Object> functionFactory = context -> new Function<BiSupplier<InputStream, Map<String,Object>>, Object>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "eval";
				}

				@Override
				public Object execute(BiSupplier<InputStream, Map<String,Object>> input, ProgressMonitor progressMonitor) {
					Map<String,Object> bindings = new HashMap<>(input.getSecond());
					bindings.put(CONTEXT_BINDING, context);
					bindings.put(PROGRESS_MONITOR_BINDING, progressMonitor);					
					throw new UnsupportedOperationException("TODO - implement with Spring Expression language or drop eval");
//					try {
//						return Util.eval(input.getFirst(), bindings);
//					} catch (IOException e) {
//						throw new ExecutionException(e, this);
//					}
				}
			};
			
			return (SupplierFactory<T>) scriptFactory.then(bindingsFactory.asFunctionFactory()).then(functionFactory);
		}
		
		if (type == Boolean.class) {
			FunctionFactory<Object, Boolean> booleanFactory = context -> new Function<Object,Boolean>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "boolean";
				}

				@Override
				public Boolean execute(Object obj, ProgressMonitor progressMonitor) {
					return Boolean.TRUE.equals(obj);
				}
			};
			
			return (SupplierFactory<T>) getFactory(Object.class).then(booleanFactory);			
		}
		
		if (type == InputStream.class) {
			FunctionFactory<Object, InputStream> booleanFactory = context -> new Function<Object,InputStream>() {

				@Override
				public double size() {
					return 1;
				}

				@Override
				public String name() {
					return "stream";
				}

				@Override
				public InputStream execute(Object obj, ProgressMonitor progressMonitor) {
					InputStream ret = DefaultConverter.INSTANCE.convert(obj, InputStream.class);
					if (obj != null && ret == null) {
						try {
							ret = Util.toStream(context, obj.toString());
						} catch (IOException e) {
							throw new ExecutionException(e, this);
						}
					}
					return ret;
				}
			};
			
			return (SupplierFactory<T>) getFactory(Object.class).then(booleanFactory);			
		}
		
		throw new IllegalArgumentException("Unsupported type: " + type);
	}

}
