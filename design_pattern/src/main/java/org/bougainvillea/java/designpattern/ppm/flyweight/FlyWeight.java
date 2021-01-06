package org.bougainvillea.java.designpattern.ppm.flyweight;

import org.bougainvillea.java.designpattern.ppm.bo.MaterialState;

/**
 * @author renqiankun
 */
public interface FlyWeight {
    /**
     * 保存物资状态
     * @param materialState
     */
    void use(MaterialState materialState);
}
