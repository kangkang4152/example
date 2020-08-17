package com.review.rpc.transport;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;

import com.review.rpc.proto.Peer;

public class HTTPTransportClient implements TransportClient {

	private String url;
	
	@Override
	public void connect(Peer peer) {
		this.url="http://"+peer.getHost()+":"+peer.getPort();
	}

	@Override
	public InputStream writer(InputStream data) {
		try {
			HttpURLConnection con=(HttpURLConnection) new URL(url).openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(true);
			con.setRequestMethod("POST");
			
			con.connect();
			IOUtils.copy(data, con.getOutputStream());
			
			int resultCode=con.getResponseCode();
					
			if(resultCode == HttpURLConnection.HTTP_OK) {
				return con.getInputStream();
			}else {
				return con.getErrorStream();
			}
		} catch (IOException e) {
			throw new IllegalStateException(e);
		} 
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
