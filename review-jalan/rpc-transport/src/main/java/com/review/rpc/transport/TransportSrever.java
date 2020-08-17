package com.review.rpc.transport;

/**
 * 
 * @author Administrator
 * 1.启动，监听
 * 2.接受请求
 * 3.关闭监听
 *
 */
public interface TransportSrever {
	void init(int port,RequestHandler handler);
	void start();
	
	void stop();

}
