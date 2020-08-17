package org.rpc.example;

import com.review.rpc.client.RpcClient;

public class Client {
	
	public static void main(String[] args) {
		RpcClient client=new RpcClient();
		
		CalcService service=client.getProxy(CalcService.class);
		
		int r1=service.add(2, 3);
		int r2=service.minus(6, 4);
		
		System.out.println(r1);
		System.out.println(r2);
		
	}

	
	
}
