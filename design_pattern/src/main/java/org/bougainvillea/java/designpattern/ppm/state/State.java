package org.bougainvillea.java.designpattern.ppm.state;


import org.bougainvillea.java.designpattern.ppm.bo.MaterialState;

/**
 * 抽象状态
 */
public interface State {

    /**
     * 初始化物资池
     */
    MaterialState init(MaterialState materialState);

    /**
     * 分派物资
     */
    MaterialState distribution(MaterialState materialState);

    /**
     * 清算物资
     */
    MaterialState clearingMaterial(MaterialState materialState);

    /**
     * 冻结物资
     */
    MaterialState freezeMaterial(MaterialState materialState);

    /**
     * 退库或者调拨
     */
    MaterialState withdrawalMovement(MaterialState materialState);
}