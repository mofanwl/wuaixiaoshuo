package com.wn.test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/12/4.
 */
public class TimeTest {
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }

    public static void main(String[] args) {

        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1575958547768L));
       // long times = System.currentTimeMillis();
       // System.out.println(getTime(toVip(5)));
        System.out.println(result);
       // getVip(1575440506355L);
    }
    public static Long toVip(Integer day){
        Long TIME_YT=86400000L;
        TIME_YT=TIME_YT * day;
        long times = System.currentTimeMillis()+TIME_YT;
        return times;
    }
    public static Long toVip2(Integer day){
        Long TIME_YT=86400000L;
        TIME_YT=TIME_YT * day;
        long times = TIME_YT;
        return times;
    }
    public static String getTime(long time){
        System.out.println(time);
        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time));
        return result;
    }

    public static void getVip(Long time){
        //1575440745805
        //1575440506355
        long times = System.currentTimeMillis();
        String vip="";
        if((times-time)<0){
            vip="yes";

        }else {
            vip="no";
        }
        System.out.println("vip:"+vip);
    }



}
