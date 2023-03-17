package com.example.login.unitl;

import java.security.MessageDigest;

public class md5unitl {

    public static String MD5min(String inStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();//转成char数组类型
        System.out.println("charArray.length = " + charArray.length);
        byte[] byteArray = new byte[charArray.length];//根据长度转换成byte

        for (int i = 0; i < charArray.length; i++){
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);
//        System.out.println("md5Bytes.length = " + md5Bytes.length);

        StringBuffer hexValue = new StringBuffer();//获取32位

        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
//            System.out.println("Integer.toHexString(val) = " + Integer.toHexString(val));
        }
        return hexValue.toString(); //.toUpperCase() 去掉字母就会变成小写。加上是大写

    }
}
