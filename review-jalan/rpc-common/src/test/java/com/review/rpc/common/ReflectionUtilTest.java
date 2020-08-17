package com.review.rpc.common;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;

import org.junit.Test;


public class ReflectionUtilTest {
	
 
	@Test
	public void newInstance() {
		AppTest t=ReflectionUtils.newInstance(AppTest.class);
		assertNotNull(t);
	}
	
	@Test
	public void getPublicMethods() {
		Method[] methods=ReflectionUtils.getPublickMethod(AppTest.class);
		assertEquals(1, methods.length);
	}
	
	@Test
	public void getPublicMethodsName() {
		Method[] methods=ReflectionUtils.getPublickMethod(AppTest.class);
		for(Method m:methods) {
			System.out.print(m.getName());
		}
	}
	
	@Test
	public void invok() {
		Method[] methods=ReflectionUtils.getPublickMethod(AppTest.class);
		Method b=methods[0];
		
		AppTest a=new AppTest();
		
		Object obj=ReflectionUtils.invoke(a, b);
		System.out.print(obj);
	}

}
