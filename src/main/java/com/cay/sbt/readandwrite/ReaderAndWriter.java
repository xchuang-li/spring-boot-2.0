package com.cay.sbt.readandwrite;

import java.io.*;

public class ReaderAndWriter {
    public static void main(String[] args) {
        BufferedReader br=null;
        BufferedWriter bw=null;
        try {
            br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("D:\\downloads\\aaa.txt"),"gbk"));
            bw = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("D:\\downloads\\aaa2.txt"),"gbk"));
            String line;
            while((line=br.readLine())!= null){
                bw.write(line);
            }
            bw.flush();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(br!=null){
                    br.close();
                }
                if(bw!=null){
                    bw.close();
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }

    }
}
