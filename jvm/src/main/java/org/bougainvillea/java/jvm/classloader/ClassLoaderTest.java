package org.bougainvillea.java.jvm.classloader;

/**
 * 类加载器
 * 用户自定义类 默认使用AppClassLoader应用类加载器（系统类加载器）
 * java核心类库 使用 c和C++实现的BootstrapClassLoader引导类加载器
 *
 * @author renqiankun
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        ClassLoader systemClassLoader=ClassLoader.getSystemClassLoader();
        //sun.misc.Launcher$AppClassLoader 系统类加载器java实现
        System.out.println(systemClassLoader);

        ClassLoader extClassLoader = systemClassLoader.getParent();
        //sun.misc.Launcher$ExtClassLoader 扩展类加载器java实现
        System.out.println(extClassLoader);

        ClassLoader bootstrapClassloader = extClassLoader.getParent();
        //null 引导类加载器（由c和c++实现）
        System.out.println(bootstrapClassloader);


        //用户自定义类型的类加载器默认式系统类加载器-AppClassLoader
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        //系统类加载器只有一个
        System.out.println(classLoader==systemClassLoader);

        //java核心类库都是使用 BootstrapClassloader引导类加载器进行加载
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);

    }
}
