package org.bougainvillea.java.designpattern.pattern.creation.factory.abstractdemo.factory;


import org.bougainvillea.java.designpattern.pattern.creation.factory.method.pizza.LDCheesePizza;
import org.bougainvillea.java.designpattern.pattern.creation.factory.method.pizza.LDGreekPizza;
import org.bougainvillea.java.designpattern.pattern.creation.factory.no.pizza.Pizza;

public class LDOrderFactory implements AbsFactory{

    @Override
    public void createPizza(String name){
        Pizza pizza=null;
        if ("cheese".equals(name)){
            pizza= new LDCheesePizza();
        }else if("greek".equals(name)) {
            pizza= new LDGreekPizza();
        }else{
            System.err.println("order failure");
            return;
        }
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.bake();
    }
}
