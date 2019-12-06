package com.wn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Administrator on 2019/12/2.
 */
public class Tools {

    static String getRandomStringByLength(int length) {
        String base = "0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public static String getOrderNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Date date = new Date();
        return sdf.format(date) + getRandomStringByLength(4);
    }
    public static Long getTime(){
        return System.currentTimeMillis();
    }

    public static String toVip(Integer day){
        Long TIME_YT=86400000L;
        TIME_YT=TIME_YT * day;
        long times = System.currentTimeMillis()+TIME_YT;
        return String.valueOf(times);
    }
    public static String getVipTime(String time){
        Long timess=Long.valueOf(time);
        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timess));
        return result;
    }

    public static String getVip(String time){
        //1575440745805
        //1575440506355
        Long timea=Long.valueOf(time);
        long times = System.currentTimeMillis();
        String vip="";
        if((times-timea)<0){
            vip="vip";
        }else {
            vip="novip";
        }
        System.out.println("vip:"+vip);
        return vip;
    }
}
