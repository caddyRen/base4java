package org.bougainvillea.java.designpattern.ppm.visit;

import org.bougainvillea.java.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorDistribution extends ParentVisitorPartA {

    @Override
    public void distribution(MaterialDistribution materialDistribution) {
        System.err.println("VisitorDistribution distribution");
    }
}
