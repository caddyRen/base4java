package org.bougainvillea.java.designpattern.pattern.creation.factory.method.pizza;

import org.bougainvillea.java.designpattern.pattern.creation.factory.no.pizza.Pizza;

public class BJGreekPizza extends Pizza {
    @Override
    public void prepare() {
        setName("BJGreekPizza");
        System.err.println("prepare BJGreekPizza");
    }
}
