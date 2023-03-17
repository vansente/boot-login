package com.example.login.unitl;

public class getUserUrl {

    public static String geturl(String userID,String sso,String hospital_code,String key){
        String url = "";
        String token="";
        long time=System.currentTimeMillis();
        token = md5unitl.MD5min("userName="+userID+"&hospitalCode="+hospital_code+"&t="+time+"&secretKey="+key);
        url=sso+userID+"&hospitalCode="+hospital_code+"&t="+time+"&token="+token;
        return url;
    }


}
