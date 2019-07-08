package com.cay.sbt.readandwrite;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class FileInAndOutputStream {

    public static void main(String[] args) {
        BufferedInputStream bin = null;
        BufferedOutputStream bot =null;
        try {
            bin = new BufferedInputStream(new FileInputStream("D:\\downloads\\aaa.txt"));
            bot = new BufferedOutputStream(new FileOutputStream("D:\\downloads\\aaaBAK.txt"));
            int b =0;
            while((b=bin.read())!=-1){
                System.out.println((char) b);
                bot.write(b);
            }
            bot.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(bin !=null){
                    bin.close();
                }
                if (bot !=null){
                    bot.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
