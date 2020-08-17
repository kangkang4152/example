package com.review.rpc.server;

import com.review.rpc.codec.Decoder;
import com.review.rpc.codec.Encoder;
import com.review.rpc.codec.JSONDecoder;
import com.review.rpc.codec.JSONEncoder;
import com.review.rpc.transport.HTTPTransportSrever;
import com.review.rpc.transport.TransportSrever;

public class RpcServerConfig {
	
	private Class<? extends TransportSrever> transportClass=HTTPTransportSrever.class;
	private Class<? extends Encoder> encoderClass=JSONEncoder.class;
	private Class<? extends Decoder> decoderClass=JSONDecoder.class;
	
	private int port=3000;

	public Class<? extends TransportSrever> getTransportClass() {
		return transportClass;
	}

	public void setTransportClass(Class<? extends TransportSrever> transportClass) {
		this.transportClass = transportClass;
	}

	public Class<? extends Encoder> getEncoderClass() {
		return encoderClass;
	}

	public void setEncoderClass(Class<? extends Encoder> encoderClass) {
		this.encoderClass = encoderClass;
	}

	public Class<? extends Decoder> getDecoderClass() {
		return decoderClass;
	}

	public void setDecoderClass(Class<? extends Decoder> decoderClass) {
		this.decoderClass = decoderClass;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
	

}
