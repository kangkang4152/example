package com.kan.function;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;

import com.kan.po.Message;

@WebService
public class Function {

	//�÷�������Ҫ��¶������Ӧ�ó�����õķ���  
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
    
    //��������ʹ��main�������������ǵ�service  
    public static void  main(String[] args){  
        Endpoint.publish("http://localhost:9001/Service/Function",new Function());  
        System.out.println("Publish Success~");  
    }  
}  
