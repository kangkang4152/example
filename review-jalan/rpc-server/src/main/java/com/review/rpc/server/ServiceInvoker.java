package com.review.rpc.server;

import com.review.rpc.common.ReflectionUtils;
import com.review.rpc.proto.Request;

public class ServiceInvoker {
	public Object invoke(ServiceInstance service,Request request) {
		return ReflectionUtils.invoke(service.getTarget(), service.getMethod(), request.getParameters());
	}

}
