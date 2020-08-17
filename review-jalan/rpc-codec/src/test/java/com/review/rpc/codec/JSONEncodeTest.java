package com.review.rpc.codec;

import org.junit.Test;

public class JSONEncodeTest {

	
	@Test
	public void encode() {
		Encoder encode=new JSONEncoder();
		TestBean bean=new TestBean();
		
		byte[] bytes=encode.encode(bean);
		
		
		Decoder decode=new JSONDecoder();
		TestBean obj=decode.decoder(bytes, TestBean.class);
	}
}
