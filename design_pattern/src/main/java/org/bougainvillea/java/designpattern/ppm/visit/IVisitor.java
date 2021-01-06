package org.bougainvillea.java.designpattern.ppm.visit;

import org.bougainvillea.java.designpattern.ppm.visit.element.*;

/**
 * 访问者
 * @author renqiankun
 */
public interface IVisitor {

    /**
     * 物资初始化
     */
    void init(MaterialInit materialInit);

    /**
     * 物资分配
     */
    void distribution(MaterialDistribution materialDistribution);

    /**
     * 物资消耗
     */
    void consume(MaterialConsume materialConsume);

    /**
     * 物资清算
     */
    void clearing(MaterialClearing materialClearing);

    /**
     * 物资冻结
     */
    void freeze(MaterialFreeze materialFreeze);

    /**
     * 退库
     */
    void withdrawal(MaterialWithdrawal materialWithdrawal);

    /**
     * 调拨
     */
    void allocation(MaterialAllocation materialAllocation);

}
