package indi.ikun.spring.designpattern.ppm.visit;

import indi.ikun.spring.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorConsume extends ParentVisitorContractor {


    @Override
    public void consume(MaterialConsume materialConsume) {
        System.err.println("VisitorConsume consume");
    }


}
