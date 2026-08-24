package ObserverDesignPattern;

public class GardenSprinkler implements WeatherSubscriber{
    @Override
    public void onTemperatureChanged(int temperature) {
        if (temperature >= 30) {
            System.out.println("Garden sprinkler: It's hot (" + temperature + "°C), turning sprinklers ON.");
        } else {
            System.out.println("Garden sprinkler: Temperature (" + temperature + "°C) is fine, sprinklers OFF.");
        }
    }
}
