package indi.ikun.spring.designpattern.ppm.visit;

import indi.ikun.spring.designpattern.ppm.visit.element.*;

/**
 * 访问者
 * @author renqiankun
 */
public class Visitor {
    /**
     * 物资初始化
     */
    public void init(MaterialInit materialInit) {
        throw new UnsupportedOperationException();
    }

    /**
     * 物资分配
     */
    public void distribution(MaterialDistribution materialDistribution) {
        throw new UnsupportedOperationException();
    }

    /**
     * 物资消耗
     */
    public void consume(MaterialConsume materialConsume) {
        throw new UnsupportedOperationException();
    }

    /**
     * 物资清算
     */
    public void clearing(MaterialClearing materialClearing) {
        throw new UnsupportedOperationException();
    }

    /**
     * 物资冻结
     */
    public void freeze(MaterialFreeze materialFreeze) {
        throw new UnsupportedOperationException();
    }

    /**
     * 退库
     */
    public void withdrawal(MaterialWithdrawal materialWithdrawal) {
        throw new UnsupportedOperationException();
    }

    /**
     * 调拨
     */
    public void allocation(MaterialAllocation materialAllocation) {
        throw new UnsupportedOperationException();
    }
}
