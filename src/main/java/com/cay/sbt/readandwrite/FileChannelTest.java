package com.cay.sbt.readandwrite;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileChannelTest {
    @Test
    public void fileChannelTest1()throws Exception{
        FileInputStream fio = new FileInputStream("D:\\downloads\\aaa.txt");
        FileOutputStream fos = new FileOutputStream("D:\\downloads\\bbb.txt");

        FileChannel inFileChannel = fio.getChannel();
        FileChannel outFileChannel = fos.getChannel();

        ByteBuffer bb = ByteBuffer.allocate(1024);

        while((inFileChannel.read(bb))!=-1){
            bb.flip();
            outFileChannel.write(bb);
            bb.clear();
        }
        fio.close();
        fos.close();
        inFileChannel.close();
        outFileChannel.close();
    }
}
