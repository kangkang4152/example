package com.review.rpc.server;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.review.rpc.common.ReflectionUtils;
import com.review.rpc.proto.Request;
import com.review.rpc.proto.ServiceDescriptor;

public class ServiceManager {
	
	private static final Logger logger = LoggerFactory.getLogger(ServiceManager.class);
	
	private Map<ServiceDescriptor,ServiceInstance> services;
	
	public ServiceManager() {
		this.services=new ConcurrentHashMap<>();
	}
	
	public <T> void  register(Class<T> interfaceClass,T bean) {
		Method[] methods = ReflectionUtils.getPublickMethod(interfaceClass);
		for(Method m:methods) {
			ServiceInstance sis=new ServiceInstance(bean,m);
			ServiceDescriptor sdp=ServiceDescriptor.from(interfaceClass,m);
			services.put(sdp, sis);
			logger.info(" register service {} {}",sdp.getClass(),sdp.getMethod());
		}
	}
	
	public ServiceInstance lookup(Request request) {
		ServiceDescriptor  sdp=request.getSevice();
		return   services.get(sdp);
	}
}
