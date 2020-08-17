package com.review.rpc.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPTransportSrever implements TransportSrever{

	private static final Logger logger = LoggerFactory.getLogger(HTTPTransportSrever.class);
	
	private RequestHandler handler;
	private Server server;
	
	@Override
	public void init(int port, RequestHandler handler) {
		this.handler=handler;
		this.server=new Server(port);
		
		ServletContextHandler cxt=new ServletContextHandler();
		server.setHandler(cxt);
		
		ServletHolder holder=new ServletHolder(new RequestServlet());
		cxt.addServlet(holder, "/*");
		
	}

	@Override
	public void start() {
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
	class RequestServlet extends HttpServlet{

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				InputStream in=req.getInputStream();
				OutputStream out=resp.getOutputStream();
				if(handler != null) {
					handler.onRequest(in, out);
				}
				out.flush();
		}
		
	}

}
