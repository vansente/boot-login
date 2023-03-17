package com.example.login.unitl;



import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
        JSONObject resp = new JSONObject();
        CloseableHttpClient client = null;

        try {
            Map<String, String> heads = new HashMap<>();
            heads.put("Content-Type", "application/json;charset=UTF-8");
            HttpPost request = new HttpPost(url);
            if (headerMap != null) {
                headerMap.forEach(request::setHeader);
            }
            StringEntity entity = new StringEntity(jsonStr, "UTF-8");
            request.setEntity(entity);
            HttpResponse response = client.execute(request);
            HttpEntity httpEntity = response.getEntity();
            if (httpEntity != null) {
                clientResponse = EntityUtils.toString(httpEntity, "UTF-8");
            }
            if (clientResponse == null || "".equals(clientResponse)) {
                resp.put("rx_errmsg", "clientResponse is null or empty");
            } else {
                resp = JSONObject.parseObject(clientResponse);

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