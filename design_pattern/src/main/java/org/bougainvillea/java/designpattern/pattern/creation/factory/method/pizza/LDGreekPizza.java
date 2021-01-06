package org.bougainvillea.java.designpattern.pattern.creation.factory.method.pizza;

import org.bougainvillea.java.designpattern.pattern.creation.factory.no.pizza.Pizza;

public class LDGreekPizza extends Pizza {

    @Override
    public void prepare() {
        setName("LDGreekPizza");
        System.err.println("prepare LDGreekPizza");
    }
}
