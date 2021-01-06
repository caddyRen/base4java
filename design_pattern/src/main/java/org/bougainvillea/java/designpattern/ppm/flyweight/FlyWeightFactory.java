package org.bougainvillea.java.designpattern.ppm.flyweight;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 享元工厂类
 * @author renqiankun
 */
public class FlyWeightFactory {
    /**
     * 存放共享内容的集合
     */
    private ConcurrentMap<String,FlyWeight> map=new ConcurrentHashMap<>();

    public FlyWeight get(String id,FlyWeight flyWeight){
        if(!map.containsKey(id)){
            if(flyWeight==null){
                //查询数据库设置共享数据
                throw new UnsupportedOperationException();
            }
            map.put(id,flyWeight);
        }
        return map.get(id);
    }

    public int count(){
        return map.size();
    }
}
