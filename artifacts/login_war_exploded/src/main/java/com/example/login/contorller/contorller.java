package com.example.login.contorller;


import com.alibaba.fastjson.JSONObject;
import com.example.login.unitl.getUserId;
import com.example.login.unitl.getUserUrl;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * by:小飞
 */

@Log4j2
@RestController
public class contorller {
    @Value("${hlyy.sso}")
    private String hlyy_sso;
    @Value("${hlyy.isd}")
    private  String sso;
    @Value("${hospital.code}")
    private  String hospital_code;
    @Value("${key}")
    private  String key;
    String token;

@GetMapping({"/isdlogin"})
public void Bytoken(@RequestParam("xxl_sso_sessionid") String tokens, HttpServletResponse response) throws IOException {
    String returnMsg;

    if (StringUtils.isBlank(tokens)) {
        returnMsg = "xxl_sso_sessionid为空";
        log.error(returnMsg);
        response.sendRedirect(returnMsg);
    } else {
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        String time=dateFormat.format(date);
        token=tokens;
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("xxl_sso_ssessionid",token);
        String url = hlyy_sso+token;
        JSONObject rsp = getUserId.sendpost(url, jsonObj.toJSONString(), null);
        String userId=rsp.getString("empNo");
        String userName=rsp.getString("name");
        String hlurl= getUserUrl.geturl(userId,sso,hospital_code,key);
        log.info("======程序运行中======");
        log.info(time+"当前的用户名为"+userName);
        log.info(time+"当前的token值="+tokens);
        log.info(time+"获取的员工号为"+userId);
        log.info("===================");
        response.sendRedirect(hlurl);
    }
    }

}