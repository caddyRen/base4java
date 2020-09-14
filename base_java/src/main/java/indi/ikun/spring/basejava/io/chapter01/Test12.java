package indi.ikun.spring.basejava.io.chapter01;

import java.nio.ByteBuffer;

public class Test12 {

    public static void main(String[] args) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(100);
        System.err.println("position={"+allocateDirect.position()+"},limit={"+allocateDirect.limit()+"}");
        ByteBuffer allocate = ByteBuffer.allocate(100);
        System.err.println("position={"+allocate.position()+"},limit={"+allocate.limit()+"}");
    }
}
