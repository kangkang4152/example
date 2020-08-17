package com.review.rpc.codec;

import com.alibaba.fastjson.JSON;
/**
 * 序列化
 * @author Administrator
 *
 */
public class JSONEncoder implements Encoder {

	@Override
	public byte[] encode(Object obj) {
		return JSON.toJSONBytes(obj);
	}

}
