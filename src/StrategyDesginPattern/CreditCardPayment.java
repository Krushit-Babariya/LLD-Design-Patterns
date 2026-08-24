package StrategyDesginPattern;

public class CreditCardPayment implements PaymentStrategy {
    
    private String cardHolderName;
    private String cardNumber;
    private String cvv;
    private String expirationDate;
    
    public CreditCardPayment(String cardHolderName, String cardNumber, String cvv, String expirationDate) {
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expirationDate = expirationDate;
    }
    
    @Override
    public boolean pay(double amount) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Processing Credit Card Payment");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Cardholder Name: " + cardHolderName);
        System.out.println("Card Number: " + maskCardNumber(cardNumber));
        System.out.println("CVV: " + cvv);
        System.out.println("Expiration Date: " + expirationDate);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        
        if (validateCardDetails()) {
            System.out.println("✓ Credit card validated successfully");
            System.out.println("✓ Payment of $" + String.format("%.2f", amount) + " processed successfully");
            System.out.println("✓ Transaction ID: CC-" + System.currentTimeMillis());
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            return true;
        } else {
            System.out.println("✗ Credit card validation failed");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
            return false;
        }
    }
    
    private boolean validateCardDetails() {
        return cardNumber.length() == 16 && cvv.length() == 3;
    }
    
    private String maskCardNumber(String cardNumber) {
        return cardNumber.replaceAll("\\d(?=\\d{4})", "*");
    }
    
    @Override
    public String getStrategyName() {
        return "Credit Card";
    }
}

