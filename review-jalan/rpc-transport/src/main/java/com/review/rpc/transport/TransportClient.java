package com.review.rpc.transport;

import java.io.InputStream;

import com.review.rpc.proto.Peer;

/**
 * 
 * @author Administrator
 * 1.创建连接
 * 2.发送数据，并且等待响应
 * 3。关闭连接
 *
 */
public interface TransportClient {

	void connect(Peer peer);
	
	InputStream writer(InputStream data);
	
	void close();
}
