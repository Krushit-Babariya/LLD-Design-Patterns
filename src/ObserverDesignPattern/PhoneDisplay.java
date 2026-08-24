package ObserverDesignPattern;

public class PhoneDisplay implements WeatherSubscriber {
    private final String ownerName;

    public PhoneDisplay(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public void onTemperatureChanged(int temperature) {
        System.out.println("Phone of " + ownerName + ": temperature is now " + temperature + "°C");
    }
}
