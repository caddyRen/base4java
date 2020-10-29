package indi.ikun.spring.designpattern.ppm.visit;

import indi.ikun.spring.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorClearing extends ParentVisitorContractor {


    @Override
    public void clearing(MaterialClearing materialClearing) {
        System.err.println("VisitorClearing clearing");
    }

}
