package com.wn.pojo;

public class Single {

    private Single(){}                                 //1.私有构造方法
    private static Admin admin = null;            //2.创建本类对象的属性并赋值为null
    public static Admin getSingleton() {              //3.对外提供公共的访问方法
        if(admin == null)                             //在类中判断本类对象是否为空
            admin = new Admin();                  //如果为空则创建对象并赋值
        return admin;                                 //返回本类对象
    }


}
