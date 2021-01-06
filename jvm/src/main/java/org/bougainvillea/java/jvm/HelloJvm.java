package org.bougainvillea.java.jvm;

/**
 * jclasslib
 */
public class HelloJvm {

    private static int a=10;
    static {
        a=20;
        b=30;
        System.out.println(a);
//        System.out.println(b);
    }
    private static int b=11;

    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);
    }
}
