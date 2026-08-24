package StrategyDesginPattern;

public interface PaymentStrategy {

    boolean pay(double amount);

    String getStrategyName();
}

