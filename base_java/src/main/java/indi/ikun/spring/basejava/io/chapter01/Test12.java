package indi.ikun.spring.basejava.io.chapter01;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;

@Slf4j
public class Test12 {

    public static void main(String[] args) {
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(100);
        log.info("position={},limit={}",allocateDirect.position(),allocateDirect.limit());
        ByteBuffer allocate = ByteBuffer.allocate(100);
        log.info("position={},limit={}",allocate.position(),allocate.limit());
    }
}
