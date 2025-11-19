package FactoryMethodDesignPattern;

public class FactoryMethodDemo {
    public static void main(String[] args) {
        Logistics road = new RoadLogistics();
        road.planDelivery("Laptops");

        Logistics sea = new SeaLogistics();
        sea.planDelivery("Cars");
    }
}

