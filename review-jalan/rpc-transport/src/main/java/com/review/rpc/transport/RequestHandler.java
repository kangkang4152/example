package com.review.rpc.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @author Administrator
 *处理网络请求
 *
 */
public interface RequestHandler {

	void onRequest(InputStream recive,OutputStream toResp);
}
