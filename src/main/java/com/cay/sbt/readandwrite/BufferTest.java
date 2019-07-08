package com.cay.sbt.readandwrite;

import org.junit.Test;

import java.nio.ByteBuffer;

public class BufferTest {
    @Test
    public void byteBufferTest(){
        System.out.println("初始化缓冲区");
        ByteBuffer bb = ByteBuffer.allocate(1024);
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());

        System.out.println("写入字符");
        bb.put("hello".getBytes());
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());

        //开启读模式
        System.out.println("开启读模式");
        bb.flip();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());

        System.out.println("读取缓冲区数据");
        byte[] bytes = new byte[bb.limit()];
        bb.get(bytes);
        System.out.println(new String(bytes,0,bytes.length));
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());

        System.out.println("重复读值");
        bb.flip();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        byte[] b = new byte[bb.limit()];
        bb.get(b);
        System.out.println(new String(b,0,b.length));
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());

        System.out.println("清空");
        bb.clear();
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
        byte[] b1 = new byte[bb.limit()];
        bb.get(b1);
        System.out.println(new String(b1,0,b1.length));
        System.out.println(bb.position());
        System.out.println(bb.limit());
        System.out.println(bb.capacity());
    }
}
