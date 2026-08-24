package ObserverDesignPattern;

public interface WeatherSubscriber {
    void onTemperatureChanged(int temperature);
}
