package com.kan.webservice.run;

import com.kan.webservice.getmsg.Function;
import com.kan.webservice.getmsg.FunctionService;
import com.kan.webservice.getmsg.Message;
import com.kan.webservice.protogenesis.WebServiceClient;

public class Run {

	public static void main(String[] args) {
	    Function function = new FunctionService().getFunctionPort();
//	    String string  = function.transWords("Let's Get Heck Out Of Here!");
//	    System.out.println(string);
	    
	    Message msg = new Message();
	    msg = function.pingIp("192.168.0.0");
	    System.out.println(msg.getMsg());
	    
	    WebServiceClient.doPost("http://localhost:9001/Service/Function","aaaa");
	}

}
