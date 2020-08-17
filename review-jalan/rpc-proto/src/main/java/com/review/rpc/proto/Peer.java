package com.review.rpc.proto;


/**
 * 表示网络传输的一个端点
 * @author Administrator
 * @param <String>
 *
 */
public class Peer {
	
    private String host;
	private int port;
    
    public Peer(String host, int port) {
		super();
		this.host = host;
		this.port = port;
	}
     
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}

}

