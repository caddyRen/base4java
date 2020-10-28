package indi.ikun.spring.designpattern.ppm.visit;

import indi.ikun.spring.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorAllocation extends Visitor{

    @Override
    public void allocation(MaterialAllocation materialAllocation) {
        System.err.println("VisitorAllocation allocation");
    }
}
