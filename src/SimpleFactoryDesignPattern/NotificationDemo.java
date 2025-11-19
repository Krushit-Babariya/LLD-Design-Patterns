package SimpleFactoryDesignPattern;
import java.util.List;

public class NotificationDemo {
    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();

        List<NotificationType> types = List.of(
                NotificationType.EMAIL,
                NotificationType.SMS,
                NotificationType.PUSH
        );

        for (NotificationType type : types) {
            Notification notification = factory.create(type);
            notification.send("krushit@gmail.com", "Factory created " + type);
        }
    }
}

