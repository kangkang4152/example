package com.review.rpc.server;

import static org.junit.Assert.assertNotNull;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import com.review.rpc.common.ReflectionUtils;
import com.review.rpc.proto.Request;
import com.review.rpc.proto.ServiceDescriptor;

public class ServiceManagerTest {
	
	
	ServiceManager sm;
	
	@Before
	public void init() {
		sm=new ServiceManager();
		
		TestInterface bean=new TestClass();
		sm.register(TestInterface.class, bean);
		
	}
	
//	@Test
	public void register() {
		 TestInterface bean=new TestClass();
		 sm.register(TestInterface.class, bean);
		
	}
	
	@Test
	public void lookup() {
		Method method =ReflectionUtils.getPublickMethod(TestInterface.class)[0];
		ServiceDescriptor sdp=ServiceDescriptor.from(TestInterface.class, method);
				
		Request request =new Request();
		request.setSevice(sdp);
		
		ServiceInstance sis=sm.lookup(request);
		assertNotNull(sis);
	}

}
