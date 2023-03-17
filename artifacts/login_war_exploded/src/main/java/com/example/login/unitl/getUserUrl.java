package com.example.login.unitl;

public class getUserUrl {

    public static String geturl(String userID,String sso,String hospital_code,String key){
        String url = "";
        String token="";
        long time=System.currentTimeMillis();
        token = unitl.md5unitl.MD5min("userID="+userID+"&hospitalCode="+hospital_code+"&t="+time+"secretKey="+key);
        url=sso +userID+"&hospitalCode="+hospital_code+"&t="+time+"&token="+token+"&destination=L3N5c2NlbnRlcg==";
        return url;
    }


}
