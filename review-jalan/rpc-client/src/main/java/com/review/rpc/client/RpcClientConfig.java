package com.review.rpc.client;

import java.util.Arrays;
import java.util.List;

import com.review.rpc.codec.Decoder;
import com.review.rpc.codec.Encoder;
import com.review.rpc.codec.JSONDecoder;
import com.review.rpc.codec.JSONEncoder;
import com.review.rpc.proto.Peer;
import com.review.rpc.transport.HTTPTransportClient;
import com.review.rpc.transport.TransportClient;

public class RpcClientConfig {
	private  Class<? extends TransportClient> transportClass=HTTPTransportClient.class;
	private  Class<? extends Encoder> encodeClass =  JSONEncoder.class;
	private  Class<? extends Decoder> decodeClass =  JSONDecoder.class;
	private  Class<? extends TransportSelectot> selectotClass =  RandomTransportSelector.class;
	private int connectCount=1;
	private List<Peer>  services = Arrays.asList(new Peer("127.0.0.1",3000));
	public Class<? extends TransportClient> getTransportClass() {
		return transportClass;
	}
	public void setTransportClass(Class<? extends TransportClient> transportClass) {
		this.transportClass = transportClass;
	}
	public Class<? extends Encoder> getEncodeClass() {
		return encodeClass;
	}
	public void setEncodeClass(Class<? extends Encoder> encodeClass) {
		this.encodeClass = encodeClass;
	}
	public Class<? extends Decoder> getDecodeClass() {
		return decodeClass;
	}
	public void setDecodeClass(Class<? extends Decoder> decodeClass) {
		this.decodeClass = decodeClass;
	}
	public Class<? extends TransportSelectot> getSelectotClass() {
		return selectotClass;
	}
	public void setSelectotClass(Class<? extends TransportSelectot> selectotClass) {
		this.selectotClass = selectotClass;
	}
	public int getConnectCount() {
		return connectCount;
	}
	public void setConnectCount(int connectCount) {
		this.connectCount = connectCount;
	}
	public List<Peer> getServices() {
		return services;
	}
	public void setServices(List<Peer> services) {
		this.services = services;
	} 
	
	
}
