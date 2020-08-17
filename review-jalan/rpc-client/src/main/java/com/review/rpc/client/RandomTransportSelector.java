package com.review.rpc.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.review.rpc.common.ReflectionUtils;
import com.review.rpc.proto.Peer;
import com.review.rpc.transport.TransportClient;

public class RandomTransportSelector implements TransportSelectot {

	private List<TransportClient> clients;
	
	private static final Logger logger = LoggerFactory.getLogger(RandomTransportSelector.class);
	
	
	
	public RandomTransportSelector() {
		clients= new ArrayList<>();
	}

	@Override
	public synchronized void   init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
		count = Math.max(count, 1);
		
		for(Peer peer :peers) {
			for(int i=0;i<count;i++) {
				TransportClient client=ReflectionUtils.newInstance(clazz);
				client.connect(peer);
				clients.add(client);
			}
			logger.info("connect server :",peer);
		}
	}

	@Override
	public synchronized TransportClient select() {
		int i= new Random().nextInt(clients.size());
		return clients.remove(i);
	}

	@Override
	public synchronized void release(TransportClient client) {
		clients.add(client);
		
	}

	@Override
	public synchronized void close() {
		for(TransportClient client : clients) {
			client.close();
		}
		clients.clear();
	}

	

}
