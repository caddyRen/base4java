package org.bougainvillea.java.designpattern.pattern.creation.factory.method.order;

import org.bougainvillea.java.designpattern.pattern.creation.factory.method.pizza.BJCheesePizza;
import org.bougainvillea.java.designpattern.pattern.creation.factory.method.pizza.BJGreekPizza;
import org.bougainvillea.java.designpattern.pattern.creation.factory.no.pizza.Pizza;

public class BJOrderFactory extends OrderFactory{

    @Override
    public void createPizza(String name){
        Pizza pizza=null;
        if ("cheese".equals(name)){
            pizza= new BJCheesePizza();
        }else if("greek".equals(name)) {
            pizza= new BJGreekPizza();
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
