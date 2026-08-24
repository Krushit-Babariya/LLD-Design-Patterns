package StrategyDesginPattern;

/**
 * Strategy Design Pattern Demo
 * 
 * WHAT IS STRATEGY PATTERN?
 * ========================
 * The Strategy Pattern is a behavioral design pattern that:
 * - Defines a family of algorithms, encapsulates each one, and makes them interchangeable
 * - Lets the algorithm vary independently from clients that use it
 * - Promotes Open/Closed Principle - open for extension, closed for modification
 * 
 * KEY COMPONENTS:
 * ===============
 * 1. Strategy Interface (PaymentStrategy): Defines the common interface for all strategies
 * 2. Concrete Strategies (CreditCardPayment, PayPalPayment, BitcoinPayment): Implement the strategy
 * 3. Context (ShoppingCart): Uses the strategy without knowing implementation details
 * 
 * BENEFITS:
 * =========
 * - Eliminates conditional statements (if-else, switch)
 * - Makes code more flexible and maintainable
 * - Easy to add new strategies without changing existing code
 * - Each strategy is encapsulated in its own class
 * - Runtime strategy selection
 * 
 * REAL-WORLD EXAMPLE:
 * ===================
 * This demo shows a payment processing system where users can choose different payment methods
 * (Credit Card, PayPal, Bitcoin) at checkout time. The ShoppingCart doesn't need to know
 * the implementation details of each payment method - it just calls the pay() method.
 */
public class StrategyPatternDemo {
    
    public static void main(String[] args) {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║         STRATEGY DESIGN PATTERN - PAYMENT SYSTEM DEMO          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");
        
        // Create a shopping cart
        ShoppingCart cart = new ShoppingCart();
        
        // Add items to cart
        System.out.println("📦 Adding items to cart...\n");
        cart.addItem("Laptop", 999.99);
        cart.addItem("Mouse", 29.99);
        cart.addItem("Keyboard", 79.99);
        cart.addItem("Monitor", 299.99);
        
        System.out.println("════════════════════════════════════════════════════════════════\n");
        
        // STRATEGY 1: Payment using Credit Card
        System.out.println("💳 SCENARIO 1: PAYMENT WITH CREDIT CARD");
        System.out.println("════════════════════════════════════════════════════════════════\n");
        
        PaymentStrategy creditCard = new CreditCardPayment(
            "John Doe",
            "1234567890123456",
            "123",
            "12/25"
        );
        cart.setPaymentStrategy(creditCard);
        cart.checkout();
        
        cart.clearCart();
        
        // Re-add items for next payment method
        cart.addItem("Laptop", 999.99);
        cart.addItem("Mouse", 29.99);
        cart.addItem("Keyboard", 79.99);
        cart.addItem("Monitor", 299.99);
        
        System.out.println("════════════════════════════════════════════════════════════════\n");
        
        // STRATEGY 2: Payment using PayPal
        System.out.println("🌐 SCENARIO 2: PAYMENT WITH PAYPAL");
        System.out.println("════════════════════════════════════════════════════════════════\n");
        
        PaymentStrategy paypal = new PayPalPayment(
            "user@example.com",
            "securePassword123"
        );
        cart.setPaymentStrategy(paypal);
        cart.checkout();
        
        cart.clearCart();
        
        // Re-add items for next payment method
        cart.addItem("Laptop", 999.99);
        cart.addItem("Mouse", 29.99);
        cart.addItem("Keyboard", 79.99);
        cart.addItem("Monitor", 299.99);
        
        System.out.println("════════════════════════════════════════════════════════════════\n");
        
        // STRATEGY 3: Payment using Bitcoin
        System.out.println("₿ SCENARIO 3: PAYMENT WITH BITCOIN");
        System.out.println("════════════════════════════════════════════════════════════════\n");
        
        PaymentStrategy bitcoin = new BitcoinPayment(
            "1A1z7agoat4WFhUF3QCrCMbLmcuTnU17Hp",
            "5K1aSy6edpvBq3qYESc2bkheT7YhPiBgnBYZK123456789"
        );
        cart.setPaymentStrategy(bitcoin);
        cart.checkout();
        
        System.out.println("════════════════════════════════════════════════════════════════\n");
        System.out.println("✅ Strategy Pattern Demo Completed Successfully!");
        System.out.println("════════════════════════════════════════════════════════════════\n");
    }
}

