package com.kan.webservice.protogenesis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * WebServiceClient
 *
 * @author jim
 * @date 2017/12/11
 */
public class WebServiceClient {

    /**
     * ��ֹʵ����
     */
    private WebServiceClient() {
        throw new IllegalStateException("�������ֹʵ����");
    }

    private static final String CHARSET_NAME = "UTF-8";

    /**
     * POST����
     *
     * @param url    �����ַ
     * @param params XML�ַ���
     * @return nullΪ����ʧ��
     */
    public static String doPost(String url, String params) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            
            conn.setRequestProperty("Content-Type", "text/xml;charset=UTF-8");
//            conn.setRequestProperty("SOAPAction", targetNamespace + "toTraditionalChinese");
            
            // ��ʱʱ��30��
            int timeout = 30 * 1000;
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
            conn.connect();
            if (params != null) {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), CHARSET_NAME);
                out.write(params);
                out.flush();
                out.close();
            }
            InputStreamReader r = new InputStreamReader(conn.getInputStream(), CHARSET_NAME);
            BufferedReader reader = new BufferedReader(r);
            String line;
            StringBuffer sb = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            return null;
        }
    }
}
