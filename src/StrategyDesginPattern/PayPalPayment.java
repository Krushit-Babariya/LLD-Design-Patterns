package StrategyDesginPattern;

public class PayPalPayment implements PaymentStrategy {
    
    private String email;
    private String password;
    
    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Processing PayPal Payment");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Email: " + email);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        
        if (authenticate()) {
            System.out.println("✓ PayPal account authenticated");
            
            if (processPayPalTransaction(amount)) {
                System.out.println("✓ Payment of $" + String.format("%.2f", amount) + " processed successfully via PayPal");
                System.out.println("✓ Transaction ID: PP-" + System.currentTimeMillis());
                System.out.println("✓ Funds transferred to merchant account");
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                return true;
            }
        } else {
            System.out.println("✗ PayPal authentication failed");
        }
        
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        return false;
    }

    private boolean authenticate() {
        return email.contains("@") && password.length() >= 6;
    }
    
    private boolean processPayPalTransaction(double amount) {
        return amount > 0;
    }
    
    @Override
    public String getStrategyName() {
        return "PayPal";
    }
}

