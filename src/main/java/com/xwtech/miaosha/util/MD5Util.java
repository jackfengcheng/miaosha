package com.xwtech.miaosha.util;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by admini on 2019/1/21.
 */
public class MD5Util {
    public static String md5(String value){
        return DigestUtils.md5Hex(value);
    }

    private static final String salt ="1a2b3c4d";

    public static String inputPassToFormPass(String ipuPassWord){
        String str = salt.charAt(0)+salt.charAt(1)+ipuPassWord+salt.charAt(6)+salt.charAt(7);
        return md5(str);
    }
    public static String formPassToDbPass(String formPassWord,String salt){
        String str = salt.charAt(0)+salt.charAt(1)+formPassWord+salt.charAt(6)+salt.charAt(7);
        return md5(str);
    }

    public static String inputPassToDbPass(String input,String salt){
        String formPass = inputPassToFormPass(input);
        String dbPass = formPassToDbPass(formPass, salt);
        return dbPass;
    }
    public static void main(String[] args) {
//        String s = inputPassToFormPass("123456");
//        System.out.println(s);
        String s1 = formPassToDbPass("c8210e78361a27157ac2dc309e6cabf8", salt);
        System.out.println(s1);
    }
}
