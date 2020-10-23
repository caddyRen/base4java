package indi.ikun.spring.designpattern.ppm.flyweight;

import indi.ikun.spring.designpattern.ppm.bo.MaterialState;

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
