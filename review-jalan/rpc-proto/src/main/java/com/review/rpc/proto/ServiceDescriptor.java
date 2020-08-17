package com.review.rpc.proto;

import java.lang.reflect.Method;

/**
 * 表示服务
 * @author Administrator
 * 1，类名。2.方法名。3.返回类型    4，参数类型
 */
public class ServiceDescriptor {
	
	private String clazz;
	private String method;
	private String returnType;
	private String[]  parameterTypes;
	
	public ServiceDescriptor() {
		super();
	}

	public ServiceDescriptor(String clazz, String method, String returnType, String[] parameterTypes) {
		super();
		this.clazz = clazz;
		this.method = method;
		this.returnType = returnType;
		this.parameterTypes = parameterTypes;
	}
	
	
	public static ServiceDescriptor from(Class clazz,Method method) {
		ServiceDescriptor sdp=new ServiceDescriptor();
		sdp.setClazz(clazz.getName());
		sdp.setMethod(method.getName());
		sdp.setReturnType(method.getReturnType().getName());
		
		Class[] parameterClasses = method.getParameterTypes();
		
		String[] parameterTypes=new String[parameterClasses.length];
		for(int i=0;i<parameterClasses.length;i++) {
			parameterTypes[i]=parameterClasses[i].getName();
		}
		sdp.setParameterTypes(parameterTypes);
		
		return sdp;
	}
	
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getReturnType() {
		return returnType;
	}
	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}
	public String[] getParameterTypes() {
		return parameterTypes;
	}
	public void setParameterTypes(String[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ServiceDescriptor other = (ServiceDescriptor) obj;
		return this.toString().equals(other.toString());
	}
	
	@Override
	public String toString() {
		return "clazz="+ clazz 
				+ ",method="+ method
				+",returnType="+ returnType
				+"parameterTypes="+parameterTypes;
	}
	
}
