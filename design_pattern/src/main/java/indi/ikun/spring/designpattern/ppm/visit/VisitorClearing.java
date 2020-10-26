package indi.ikun.spring.designpattern.ppm.visit;

import indi.ikun.spring.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorClearing implements IVisitor{
    @Override
    public void init(MaterialInit materialInit) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void distribution(MaterialDistribution materialDistribution) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void consume(MaterialConsume materialConsume) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clearing(MaterialClearing materialClearing) {
        System.err.println("VisitorClearing clearing");
    }

    @Override
    public void freeze(MaterialFreeze materialFreeze) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void withdrawal(MaterialWithdrawal materialWithdrawal) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void allocation(MaterialAllocation materialAllocation) {
        throw new UnsupportedOperationException();
    }
}
