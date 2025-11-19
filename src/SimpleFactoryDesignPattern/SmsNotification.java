package SimpleFactoryDesignPattern;

public class SmsNotification implements Notification {
    @Override
    public void send(String destination, String message) {
        System.out.printf("Sending SMS to %s: %s%n", destination, message);
    }
}

