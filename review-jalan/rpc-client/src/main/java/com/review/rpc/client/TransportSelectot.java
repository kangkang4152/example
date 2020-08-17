package com.review.rpc.client;

import java.util.List;

import com.review.rpc.proto.Peer;
import com.review.rpc.transport.TransportClient;

public interface TransportSelectot {

	void  init(List<Peer> peers,
			int count,
			Class<? extends TransportClient> clazz);
 	
	TransportClient select();
	
	void release(TransportClient client);
	
	void close();
}
