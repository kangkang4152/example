package org.kan.plugin;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class CountTimesPlugin implements MethodBeforeAdvice {

	private int count;
	
	protected void count(Method m) {
		count ++ ;
	}
	
	public void before(Method method, Object[] args, Object target) throws Throwable {

		count(method);
		System.out.println(String.format(" the method %s invoked times %s",method.getName(),count));
	}

}
