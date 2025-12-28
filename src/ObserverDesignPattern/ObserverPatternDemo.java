package ObserverDesignPattern;

public class ObserverPatternDemo {
    public static void main(String[] args) {
        SimpleWeatherStation station = new SimpleWeatherStation();

        WeatherSubscriber alicePhone = new PhoneDisplay("Alice");
        WeatherSubscriber bobPhone = new PhoneDisplay("Bob");
        WeatherSubscriber sprinkler = new GardenSprinkler();

        station.addSubscriber(alicePhone);
        station.addSubscriber(bobPhone);
        station.addSubscriber(sprinkler);

        System.out.println("Morning update:");
        station.updateTemperature(22);

        System.out.println("---");
        System.out.println("Afternoon update:");
        station.updateTemperature(31);

        System.out.println("---");
        System.out.println("Bob leaves the station:");
        station.removeSubscriber(bobPhone);
        station.updateTemperature(28);
    }
}


