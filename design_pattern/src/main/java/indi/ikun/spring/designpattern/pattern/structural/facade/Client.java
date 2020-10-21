package indi.ikun.spring.designpattern.pattern.structural.facade;

public class Client {

    public static void main(String[] args) {
        Facade facade=new FacadeImpl();
        facade.start();
        facade.end();
    }
}
