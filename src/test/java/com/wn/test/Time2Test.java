package com.wn.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/12/4.
 */
public class Time2Test {
    public static void maina(String[] args) throws Exception{
        Long time=86400000L;
        System.out.println(stringStamp2Date("1575785310121"));//string时间戳转Date
        System.out.println(long2String(1554025583));//long时间戳转string日期
        System.out.println(dateToStamp("2019-03-31 17:46:23"));//日期格式转时间戳
        System.out.println(stampToDate("1554025583000"));////string时间戳转日期
        System.out.println(string2Date("2019-03-31 17:46:23"));//string时间转日期
        System.out.println(string2Date("2019-03-31 17:46:23").getTime());//Date转long时间戳13位
    }

    public static void main(String[] args) {
        String a1="5600.56";
        String a2="52200.56";
        Float f1=Float.valueOf(a1);
        Float f2=Float.valueOf(a2);
        if((f2-f1)>=0){
            System.out.println("ok");
        }else {
            System.out.println("no");
        }
      //  System.out.println(f2-f1);
       // System.out.println(Float.valueOf(a1)+Float.valueOf(a2));
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    /**
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    /**
     * 取得当前时间戳（精确到秒）
     * @return
     */
    public static String timeStamp(){
        long time = System.currentTimeMillis();
        String t = String.valueOf(time/1000);
        return t;
    }
    /**
     *
     **/
    public static Date string2Date(String time){
        SimpleDateFormat format =   new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    /**
     *
     **/
    public static String long2String(long time){
        String result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(time * 1000));
        return result;
    }

    public static Date stringStamp2Date(String time){
        time=time+"000";
        Long longTime= Long.valueOf(time);
        Date date = new Date(longTime);
        return date;
    }
}
