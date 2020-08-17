package com.review.rpc.proto;
/**
 * 表示请求后返回的结果
 * @author Administrator
 *
 */
public class Response {

	/**
	 * 服务返回    0 成功    非0  失败
	 */
	private int code=0;
	/**
	 * 返回具体错误信息
	 */
	private String message ="ok";
	/**
	 * 返回的数据
	 */
	private Object data;
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setDate(Object data) {
		this.data = data;
	}
	
	
	
}
