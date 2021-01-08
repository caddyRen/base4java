package org.bougainvillea.java.jvm.classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 继承URLClassLoader 实现Classloader
 * @author renqiankun
 */
public class MyURLClassLoader extends URLClassLoader {
    public MyURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
}
