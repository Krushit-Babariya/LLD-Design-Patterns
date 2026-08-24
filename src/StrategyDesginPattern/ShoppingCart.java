package StrategyDesginPattern;

public class ShoppingCart {
    
    private double totalAmount = 0;
    private PaymentStrategy paymentStrategy;
    private java.util.List<String> items = new java.util.ArrayList<>();
    
    public void addItem(String itemName, double price) {
        items.add(itemName + " ($" + String.format("%.2f", price) + ")");
        totalAmount += price;
    }
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
        System.out.println("Payment method changed to: " + strategy.getStrategyName() + "\n");
    }
    
    public void displayCart() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║          SHOPPING CART CONTENTS            ║");
        System.out.println("╠════════════════════════════════════════════╣");
        for (String item : items) {
            System.out.println("║ " + item.substring(0, Math.min(item.length(), 40)) + 
                             (item.length() > 40 ? "..." : " ".repeat(Math.max(0, 40 - item.length() + 3))) + "║");
        }
        System.out.println("╠════════════════════════════════════════════╣");
        System.out.println("║ Total Amount: $" + String.format("%-27.2f", totalAmount) + "║");
        System.out.println("╚════════════════════════════════════════════╝\n");
    }

    public boolean checkout() {
        if (paymentStrategy == null) {
            System.out.println("❌ ERROR: No payment strategy selected!");
            return false;
        }
        
        if (items.isEmpty()) {
            System.out.println("❌ ERROR: Shopping cart is empty!");
            return false;
        }
        
        displayCart();
        System.out.println("Selected Payment Method: " + paymentStrategy.getStrategyName() + "\n");

        return paymentStrategy.pay(totalAmount);
    }

    public double getTotal() {
        return totalAmount;
    }
    
    public void clearCart() {
        items.clear();
        totalAmount = 0;
        System.out.println("Shopping cart cleared.\n");
    }
}

