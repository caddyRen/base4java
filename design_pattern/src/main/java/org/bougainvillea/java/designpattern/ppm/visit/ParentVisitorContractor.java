package org.bougainvillea.java.designpattern.ppm.visit;

import org.bougainvillea.java.designpattern.ppm.visit.element.*;

/**
 * 访问者
 * @author renqiankun
 */
public class ParentVisitorContractor implements IVisitor{

    /**
     * 物资初始化
     */
    @Override
    public void init(MaterialInit materialInit) {
        throw new UnsupportedOperationException();
    }

    /**
     * 物资分配
     */
    @Override
    public void distribution(MaterialDistribution materialDistribution) {
        throw new UnsupportedOperationException();
    }

    /**
     * 物资消耗
     */
    @Override
    public void consume(MaterialConsume materialConsume) {
        throw new UnsupportedOperationException();
    }

    /**
     * 物资清算
     */
    @Override
    public void clearing(MaterialClearing materialClearing) {
        throw new UnsupportedOperationException();
    }

    /**
     * 物资冻结
     */
    @Override
    public void freeze(MaterialFreeze materialFreeze) {
        throw new UnsupportedOperationException();
    }

    /**
     * 退库
     */
    @Override
    public void withdrawal(MaterialWithdrawal materialWithdrawal) {
        throw new UnsupportedOperationException();
    }

    /**
     * 调拨
     */
    @Override
    public void allocation(MaterialAllocation materialAllocation) {
        throw new UnsupportedOperationException();
    }
}
