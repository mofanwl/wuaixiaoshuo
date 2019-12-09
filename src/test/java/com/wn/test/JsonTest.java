package com.wn.test;

import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Administrator on 2019/12/4.
 */
public class JsonTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new FileReader("G:/test_data.json"));
        char[] c=new char[10000000];
        int n;
        String ss=null;

        while((n=br.read(c))!=-1){
            ss=new String(c,0,n);
        }

        br.close();
        System.out.println(ss);

        String jsonResult = "{\"errNum\":-1,\"retMsg\":\"\u8eab\u4efd\u8bc1\u53f7\u7801\u4e0d\u5408\u6cd5!\",\"retData\":[]}";
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        System.out.println(jsonObject.toJSONString());

    }
    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
            }
        return string.toString();
    }

    public static String revert(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }
}
