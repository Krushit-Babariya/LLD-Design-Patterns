package FactoryMethodDesignPattern;

public abstract class Logistics {

    public void planDelivery(String cargo) {
        Transport transport = createTransport();
        System.out.println("Preparing cargo...");
        transport.deliver(cargo);
    }

    protected abstract Transport createTransport();
}

