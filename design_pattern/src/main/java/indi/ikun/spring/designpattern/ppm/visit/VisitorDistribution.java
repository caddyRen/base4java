package indi.ikun.spring.designpattern.ppm.visit;

import indi.ikun.spring.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorDistribution extends ParentVisitorPartA {

    @Override
    public void distribution(MaterialDistribution materialDistribution) {
        System.err.println("VisitorDistribution distribution");
    }
}
