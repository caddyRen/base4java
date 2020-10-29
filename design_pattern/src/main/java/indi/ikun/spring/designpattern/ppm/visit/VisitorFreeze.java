package indi.ikun.spring.designpattern.ppm.visit;

import indi.ikun.spring.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorFreeze extends ParentVisitorPartA {

    @Override
    public void freeze(MaterialFreeze materialFreeze) {
        System.err.println("VisitorFreeze freeze");
    }

}
