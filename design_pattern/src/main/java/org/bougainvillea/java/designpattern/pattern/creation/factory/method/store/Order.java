package org.bougainvillea.java.designpattern.pattern.creation.factory.method.store;

import org.bougainvillea.java.designpattern.pattern.creation.factory.method.order.OrderFactory;
import org.apache.commons.lang3.StringUtils;

import static org.bougainvillea.java.designpattern.pattern.creation.factory.no.order.Order.getName;

public class Order {

    OrderFactory orderFactory;

    public Order(OrderFactory abs) {
        this.orderFactory = abs;
        do{
            System.err.println("输入pizza name");
            String name=getName();
            if(StringUtils.isEmpty(name)){
                break;
            }else {
                orderFactory.createPizza(name);
            }
        }while (true);
    }
}
