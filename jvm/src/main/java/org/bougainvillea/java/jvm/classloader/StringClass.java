package org.bougainvillea.java.jvm.classloader;

/**
 * 双亲委派机制
 *
 * String类由BootstrapClassLoader加载
 * 自定义的java.lang.String不会加载
 *
 */
public class StringClass {
    public static void main(String[] args) {
        java.lang.String str=new java.lang.String();
        System.out.println("hi 双亲委派机制");


        MyBougainvillea myBougainvillea = new MyBougainvillea();
        System.out.println(myBougainvillea.getClass().getClassLoader());
    }
}
