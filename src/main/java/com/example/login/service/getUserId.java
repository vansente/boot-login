package com.example.login.service;



import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Component
public class getUserId {


    /**
     * 发送json字符串，通过body进行传递
     *
     * @param url       地址
     * @param jsonStr   json串
     * @param headerMap 请求头参数
     * @return JSONObject
     */

    public static JSONObject sendpost(String url, String jsonStr, Map<String, String> headerMap) {
        String clientResponse = "";
        JSONObject resp = new JSONObject(true);
        CloseableHttpClient client = null;

        try {
            client = HttpClients.createDefault();//创建一个http对象
            HttpPost request = new HttpPost(url);//创建一个post请求，并赋地址

            if (headerMap != null) {
                headerMap.forEach(request::setHeader);
            }
            request.setHeader("Content-Type", "application/json");//消息头
            StringEntity entity = new StringEntity(jsonStr, "UTF-8");//消息体
            request.setEntity(entity);
            HttpResponse response = client.execute(request);  //发送post请求
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                clientResponse = EntityUtils.toString(httpEntity, "UTF-8");
            }
            if (clientResponse == null || "".equals(clientResponse)) {
                resp.put("rx_errmsg", "clientResponse is null or empty");
            } else {
                resp = JSONObject.parseObject(clientResponse);  //回传json

            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.put("rx_errmsg", e.getMessage());
            resp.put("rx_errdatailmsg", "返回的内容为：" + clientResponse + "||" + "ceshi");
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return resp;
    }
}