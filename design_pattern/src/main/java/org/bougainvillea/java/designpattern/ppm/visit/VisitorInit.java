package org.bougainvillea.java.designpattern.ppm.visit;

import org.bougainvillea.java.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorInit extends ParentVisitorPartA {
    @Override
    public void init(MaterialInit materialInit) {
        System.err.println("VisitorInit init");
    }
}
