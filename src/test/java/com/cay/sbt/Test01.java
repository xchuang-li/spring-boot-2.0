package com.cay.sbt;

import org.junit.Test;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test01 {
    @Test
    public void test001() throws Exception{
        String date = "2019-01-01";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");
        Date date1= sdf1.parse(date);
        String date2 = new SimpleDateFormat("yyyyMMdd").format(date1);
        System.out.println(date2);

        String date3 = date.replaceAll("-","");
        System.out.println(date3);
    }
    @Test
    public void test002() throws Exception{
        String date = "20190425155932";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        Date date1= sdf1.parse(date);
        String date2 = new SimpleDateFormat("yyyyMMddHHmmss").format(date1);
        System.out.println(date1);
    }
    @Test
    public void getEncoding() throws Exception{
        System.out.println("系统默认编码："+System.getProperty("file.encoding"));
        System.out.println("系统默认字符编码："+ Charset.defaultCharset());
        System.out.println("系统默认语言：" + System.getProperty("user.language"));
        String str ="我还是个宝宝啊";
        str = new String(str.getBytes(),"gbk");
        System.out.println(str);
        if(str.equals(new String(str.getBytes(),"utf-8"))){
            System.out.println("utf-8");
        }else if(str.equals(new String(str.getBytes(),"gbk"))){
            System.out.println("gbk");
        }else{
            System.out.println("无法识别的字符集编码");
        }
    }
}
