package com.review.rpc.client;

import java.lang.reflect.Proxy;

import com.review.rpc.codec.Decoder;
import com.review.rpc.codec.Encoder;
import com.review.rpc.common.ReflectionUtils;

public class RpcClient {
	private RpcClientConfig config;
	private Encoder encoder;
	private Decoder decoder;
	private TransportSelectot select;
	
	
	public RpcClient() {
		this(new RpcClientConfig());
	}


	public RpcClient(RpcClientConfig rpcClientConfig) {
		// TODO Auto-generated constructor stub
		this.config=rpcClientConfig;
		
		this.encoder=ReflectionUtils.newInstance(config.getEncodeClass());
		this.decoder=ReflectionUtils.newInstance(config.getDecodeClass());
		this.select=ReflectionUtils.newInstance(config.getSelectotClass());
		
		this.select.init(this.config.getServices(), this.config.getConnectCount(), this.config.getTransportClass());
	}
	
	public <T> T getProxy(Class<T> clazz) {
		return (T)Proxy.newProxyInstance(getClass().getClassLoader(), new Class[]{clazz}, new RemotInvoker(
				clazz,encoder,decoder,select));
	}

}
