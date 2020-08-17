package org.rpc.example;

import com.review.rpc.server.RpcServer;
import com.review.rpc.server.RpcServerConfig;

public class Server {

	
	public static void main(String[] args) {
		RpcServer server = new RpcServer(new RpcServerConfig());
		
		server.register(CalcService.class, new CalcServiceimpl());
		server.start();
	}
}
