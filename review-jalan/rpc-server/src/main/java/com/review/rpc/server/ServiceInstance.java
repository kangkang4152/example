package com.review.rpc.server;

import java.lang.reflect.Method;

/**
 * 
 * @author Administrator
 *表示一个具体服务
 *
 */
public class ServiceInstance {
	
	private Object target;
	private Method method;
	
	
	public ServiceInstance(Object target, Method method) {
		super();
		this.target = target;
		this.method = method;
	}
	public Object getTarget() {
		return target;
	}
	public void setTarget(Object target) {
		this.target = target;
	}
	public Method getMethod() {
		return method;
	}
	public void setMethod(Method method) {
		this.method = method;
	}
	
	
}
