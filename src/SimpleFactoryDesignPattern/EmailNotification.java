package SimpleFactoryDesignPattern;

public class EmailNotification implements Notification {
    @Override
    public void send(String destination, String message) {
        System.out.printf("Sending EMAIL to %s: %s%n", destination, message);
    }
}

