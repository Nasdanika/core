package org.nasdanika.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface Invocable {
	
	Object invoke(Object... args);
	
	default Invocable bind(Object... bindings) {
		if (bindings.length == 0) {
			return this;
		}		
		
		return new Invocable() {

			@Override
			public Object invoke(Object... args) {
				if (args.length == 0) {
					return Invocable.this.invoke();
				}
				
				Object[] finalArgs = new Object[bindings.length + args.length];
				System.arraycopy(bindings, 0, finalArgs, 0, bindings.length);
				System.arraycopy(args, 0, finalArgs, bindings.length, args.length);
				return Invocable.this.invoke(finalArgs);
			}
			
		};
	}
	
	static Invocable of(Object target, Method method) {
		return new Invocable() {
			
			@Override
			public Object invoke(Object... args) {
				try {
					return method.invoke(target, args);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					throw new ExecutionException("Exception invoking " + method + " of " + target + ": " + e, e);
				}
			}
			
		};
	}	

}
