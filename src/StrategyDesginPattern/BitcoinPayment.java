package StrategyDesginPattern;

public class BitcoinPayment implements PaymentStrategy {
    private String walletAddress;
    private String privateKey;
    
    public BitcoinPayment(String walletAddress, String privateKey) {
        this.walletAddress = walletAddress;
        this.privateKey = privateKey;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Processing Bitcoin Payment");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Wallet Address: " + maskWalletAddress(walletAddress));
        System.out.println("Amount: $" + String.format("%.2f", amount));
        
        double bitcoinAmount = amount / 50000.0;
        System.out.println("Bitcoin Amount: " + String.format("%.6f", bitcoinAmount) + " BTC");

        if (validateWalletAndKey()) {
            System.out.println("✓ Bitcoin wallet validated");
            if (mineTransaction()) {
                System.out.println("✓ Payment of $" + String.format("%.2f", amount) + " processed successfully via Bitcoin");
                System.out.println("✓ Transaction Hash: " + generateTransactionHash());
                System.out.println("✓ Block confirmations: 6");
                System.out.println("✓ Network fee: $0.50");
                System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
                return true;
            }
        } else {
            System.out.println("✗ Bitcoin wallet validation failed");
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
        return false;
    }

    private boolean validateWalletAndKey() {
        return walletAddress.length() >= 26 && privateKey.length() >= 51;
    }
    
    private boolean mineTransaction() {
        try {
            System.out.println("⏳ Waiting for blockchain confirmation...");
            Thread.sleep(1000);
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    private String generateTransactionHash() {
        return "0x" + Long.toHexString(System.currentTimeMillis());
    }

    private String maskWalletAddress(String address) {
        if (address.length() > 8) {
            return address.substring(0, 4) + "..." + address.substring(address.length() - 4);
        }
        return address;
    }
    
    @Override
    public String getStrategyName() {
        return "Bitcoin";
    }
}

