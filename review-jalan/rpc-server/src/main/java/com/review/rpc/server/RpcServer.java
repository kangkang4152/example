package com.review.rpc.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.review.rpc.codec.Decoder;
import com.review.rpc.codec.Encoder;
import com.review.rpc.common.ReflectionUtils;
import com.review.rpc.proto.Request;
import com.review.rpc.proto.Response;
import com.review.rpc.transport.RequestHandler;
import com.review.rpc.transport.TransportSrever;

public class RpcServer {

	private RpcServerConfig config;
	private TransportSrever net;
	private Encoder encoder;
	private Decoder decoder;
	private ServiceManager serviceManager;
	private ServiceInvoker serviceInvoker;
	
	private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);
	
	public RpcServer(RpcServerConfig config) {
		this.config = config;
		
		this.net=ReflectionUtils.newInstance(config.getTransportClass());
	    this.net.init(config.getPort(), this.handler);
		
		this.encoder=ReflectionUtils.newInstance(config.getEncoderClass());
		
		this.decoder=ReflectionUtils.newInstance(config.getDecoderClass());
		
		this.serviceManager=new ServiceManager();
		
		this.serviceInvoker=new ServiceInvoker();
		
	}
	
	public <T> void register(Class<T> interfaceClass,T bean) {
		serviceManager.register(interfaceClass, bean);
	}
	
	public void start() {
		this.net.start();
	}
	
	
	private RequestHandler handler=new RequestHandler(){
		@Override
		public void onRequest(InputStream recive,OutputStream toResp) {
			Response resp =new Response();
			try {
				byte[] inBytes;
				inBytes = IOUtils.readFully(recive,recive.available());
				Request request = decoder.decoder(inBytes, Request.class);
				//logger.info(" get request {} {}",request);
				
				ServiceInstance sis=serviceManager.lookup(request);
				
				logger.info("信息是否为空:");
				
				Object ret=serviceInvoker.invoke(sis, request);
				resp.setDate(ret);
				
			} catch (IOException e) {
				logger.warn(e.getMessage(),e);
				
				resp.setCode(1);
				resp.setMessage("RpcService got error :"+e.getClass().getName());
			}finally {
				try {
					byte[] outBytes = encoder.encode(resp);
					toResp.write(outBytes);
					logger.info("client");
				} catch (IOException e) {
					logger.warn(e.getMessage(),e);
				}
			}
		}
	};
	
	public RpcServerConfig getConfig() {
		return config;
	}
	public void setConfig(RpcServerConfig config) {
		this.config = config;
	}
	public TransportSrever getNet() {
		return net;
	}
	public void setNet(TransportSrever net) {
		this.net = net;
	}
	public Encoder getEncoder() {
		return encoder;
	}
	public void setEncoder(Encoder encoder) {
		this.encoder = encoder;
	}
	public Decoder getDecoder() {
		return decoder;
	}
	public void setDecoder(Decoder decoder) {
		this.decoder = decoder;
	}
	public ServiceManager getServiceManager() {
		return serviceManager;
	}
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
	public ServiceInvoker getServiceInvoker() {
		return serviceInvoker;
	}
	public void setServiceInvoker(ServiceInvoker serviceInvoker) {
		this.serviceInvoker = serviceInvoker;
	}
	
	
}
