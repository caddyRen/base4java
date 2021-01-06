package org.bougainvillea.java.designpattern.ppm.visit;

import org.bougainvillea.java.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorAllocation extends ParentVisitorPartA {

    @Override
    public void allocation(MaterialAllocation materialAllocation) {
        System.err.println("VisitorAllocation allocation");
    }
}
