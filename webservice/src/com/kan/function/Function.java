package com.kan.function;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.kan.po.Message;

@WebService
public class Function {

	//该方法就是要暴露给其他应用程序调用的方法  
    public String transWords(String words){  
        String res="";  
        for(char ch : words.toCharArray()){  
            res+="\t"+ch+"\t";  
        }  
        return res;  
    }  
    
    
    public Message pingIp(String ip) {
    	Message msg = new Message();
    	msg.setCode(2);
    	msg.setMsg(ip);
    	return msg;
    }
    
    //这里我们使用main方法来发布我们的service  
    public static void  main(String[] args){  
        Endpoint.publish("http://localhost:9001/Service/Function",new Function());  
        System.out.println("Publish Success~");  
    }  
}  
