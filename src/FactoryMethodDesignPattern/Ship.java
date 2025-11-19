package FactoryMethodDesignPattern;

public class Ship implements Transport {
    @Override
    public void deliver(String cargo) {
        System.out.println("Deliver %s overseas by SHIP " +  cargo);
    }
}

