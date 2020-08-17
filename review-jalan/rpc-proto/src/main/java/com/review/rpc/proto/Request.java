package com.review.rpc.proto;
/**
 * 表示一个请求   请求的服务是什么   请求的服务传递的参数
 * @author Administrator
 *
 */
public class Request {

	private ServiceDescriptor sevice;
	private Object[] parameters;
	
	
	public ServiceDescriptor getSevice() {
		return sevice;
	}
	public void setSevice(ServiceDescriptor sevice) {
		this.sevice = sevice;
	}
	public Object[] getParameters() {
		return parameters;
	}
	public void setParameters(Object[] parameters) {
		this.parameters = parameters;
	}
	
	
	
}
