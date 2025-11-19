package FactoryMethodDesignPattern;

public class Truck implements Transport {
    @Override
    public void deliver(String cargo) {
        System.out.println("Deliver " + cargo + "  over land by TRUCK.");
    }
}

