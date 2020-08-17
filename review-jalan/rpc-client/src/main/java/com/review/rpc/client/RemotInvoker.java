package com.review.rpc.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.review.rpc.codec.Decoder;
import com.review.rpc.codec.Encoder;
import com.review.rpc.proto.Request;
import com.review.rpc.proto.Response;
import com.review.rpc.proto.ServiceDescriptor;
import com.review.rpc.transport.TransportClient;

/**
 * 调用远程服务的代理类
 * @author Administrator
 *
 */

public class RemotInvoker implements InvocationHandler {

	private Class clazz;
	private Encoder encoder;
	private Decoder decoder;
	private TransportSelectot select;
	
	private static final Logger logger = LoggerFactory.getLogger(RemotInvoker.class);
	
	public RemotInvoker(Class clazz,Encoder encoder,Decoder decoder,TransportSelectot select) {
		this.clazz=clazz;
		this.encoder=encoder;
		this.decoder=decoder;
		this.select=select;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Request request = new Request();
		request.setSevice(ServiceDescriptor.from(clazz, method));
		request.setParameters(args);
		
		Response response=invokerRemote(request);
		if(response == null || response.getCode() != 0) {
			throw new IllegalStateException("fial to invoke remote:"+response);
		}
		return response.getData();
	}

	private Response invokerRemote(Request request) {
		TransportClient client = null;
		Response resp = null;
		try {
			client=select.select();
			
			byte[] outBytes=encoder.encode(request);
			InputStream receive=client.writer(new ByteArrayInputStream(outBytes));
			
			byte[] inBytes=IOUtils.readFully(receive, receive.available());
			
			logger.debug(new String(inBytes));
			
			resp=decoder.decoder(inBytes, Response.class);
			
		}catch(IOException e) {
			logger.error(e.getMessage(),e);
			resp=new Response();
			resp.setCode(1);
			resp.setMessage("error ："+e.getClass()+":"+e.getMessage());
		}finally {
			if(client != null) {
				select.release(client);
			}
		}
		return resp;
	}

}
