package com.example.login.contorller;


import com.alibaba.fastjson.JSONObject;
import com.example.login.service.getUserId;
import com.example.login.unitl.getUserUrl;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@ResponseBody
public class contorller {
    @Value("${hlyy.sso}")
    private String hlyy_sso;
    @Value("${hlyy.isd}")
    private  String sso;
    @Value("${hospital.code}")
    private  String hospital_code;
    @Value("${key}")
    private  String key;


    @GetMapping({"/idsLogin"})
public void Bytoken(@RequestParam("xxl_sso_sessionid") String tokens, HttpServletResponse response) throws IOException {
        Logger logger= LoggerFactory.getLogger(contorller.class);   //写日志，定位contorller类
        String returnMsg; //定义空字符串，用于存信息
    if (StringUtils.isBlank(tokens)) {
        returnMsg = "xxl_sso_sessionid为空";
        logger.error(returnMsg);
        response.sendRedirect(returnMsg);
    } else {
        Date date = new Date();  //日志显示时间
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");  //定义时间格式
        String time=dateFormat.format(date);  //定义好时间格式存入变量time
        JSONObject jsonObj = new JSONObject();  //定义请求入参json
        jsonObj.put("xxl_sso_sessionid",tokens);  //存好入参进json

        String url = hlyy_sso+tokens;
        logger.info("请求工号地址："+url);//展示URL
        JSONObject rsp = getUserId.sendpost(url, jsonObj.toJSONString(), null);  //发送post请求
        logger.info("请求的回参为"+rsp);
        String useJson=rsp.getString("message");
        logger.info("获取工号集合为"+useJson);
        JSONObject userlist=JSONObject.parseObject(useJson);
        String userId=userlist.getString("empNo");
        String userName=userlist.getString("name");
        String hlurl= getUserUrl.geturl(userId,sso,hospital_code,key);
        logger.info("======程序运行中======");
        logger.info(time+"当前的用户名为"+userName);
        logger.info(time+"获取的员工号为"+userId);
        logger.info(time+"当前的his回传的token值="+tokens);
        logger.info("单点地址为："+hlurl);
        logger.info("成功登陆系统");
        logger.info("===================");
        response.sendRedirect(hlurl);
    }
    }

}