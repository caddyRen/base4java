package org.bougainvillea.java.designpattern.ppm.visit;

import org.bougainvillea.java.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorClearing extends ParentVisitorContractor {


    @Override
    public void clearing(MaterialClearing materialClearing) {
        System.err.println("VisitorClearing clearing");
    }

}
