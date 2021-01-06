package org.bougainvillea.java.designpattern.ppm.visit;

import org.bougainvillea.java.designpattern.ppm.visit.element.*;

/**
 * @author renqiankun
 */
public class VisitorConsume extends ParentVisitorContractor {


    @Override
    public void consume(MaterialConsume materialConsume) {
        System.err.println("VisitorConsume consume");
    }


}
