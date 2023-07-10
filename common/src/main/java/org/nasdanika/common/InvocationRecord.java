package org.nasdanika.common;

import java.lang.reflect.Method;

public record InvocationRecord<T>(
		T target, 
		T proxy, 
		Method method, 
		Object[] args, 
		boolean after,
		Object result, 
		Throwable error, 
		Thread thread, 
		StackTraceElement[] stackTrace,
		long seqNo,
		long nanoTime) {

}
