package com.review.rpc.codec;

import com.alibaba.fastjson.JSON;
/**
 * 反序列化
 * @author Administrator
 *
 */
public class JSONDecoder implements Decoder {

	@Override
	public <T> T decoder(byte[] bytes, Class<T> clazz) {
		return JSON.parseObject(bytes, clazz);
	}

}
