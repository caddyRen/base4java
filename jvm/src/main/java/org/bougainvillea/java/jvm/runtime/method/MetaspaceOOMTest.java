package org.bougainvillea.java.jvm.runtime.method;

import com.sun.xml.internal.ws.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * *************************此处有大坑*********************************************
 * gradle compile时无法加载到rt.jar
 * 所以需要引入相关rt.jar
 * 引入方式参考jvm模块下的build.gradle文件
 *
 * Metaspace OOM
 * JDK7
 * -XX:PermSize=10M -XX:MaxPermSize=10m
 * JDK8
 * -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 */
public class MetaspaceOOMTest extends ClassLoader{

    public static void main(String[] args) {
        int j=0;
        try {
            MetaspaceOOMTest test = new MetaspaceOOMTest();
            for (int i = 0; i < 10000; i++) {
                ClassWriter classWriter=new ClassWriter(0);
                //指明版本号，修饰符，类名，包名，父类，接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,"Class"+i,null,"java/lang/Object",null);
                byte[] code = classWriter.toByteArray();
                //extends CLassLoader 类加载
                test.defineClass("Class"+i,code,0,code.length);
                j++;
            }
        }finally {
            System.out.println(j);
        }
    }
}
