package SimpleFactoryDesignPattern;

public class PushNotification implements Notification {
    @Override
    public void send(String destination, String message) {
        System.out.printf("Sending PUSH notification to %s: %s%n", destination, message);
    }
}

