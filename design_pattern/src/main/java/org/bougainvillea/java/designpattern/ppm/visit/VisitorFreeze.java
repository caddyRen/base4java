package org.bougainvillea.java.designpattern.ppm.visit;

import org.bougainvillea.java.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorFreeze extends ParentVisitorPartA {

    @Override
    public void freeze(MaterialFreeze materialFreeze) {
        System.err.println("VisitorFreeze freeze");
    }

}
