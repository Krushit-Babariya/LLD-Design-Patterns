package ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class SimpleWeatherStation implements WeatherStation {
    private final List<WeatherSubscriber> subscribers = new ArrayList<>();
    private int temperature;

    @Override
    public void addSubscriber(WeatherSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(WeatherSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void updateTemperature(int newTemperature) {
        this.temperature = newTemperature;
        notifySubscribers();
    }

    private void notifySubscribers() {
        for (WeatherSubscriber subscriber : subscribers) {
            subscriber.onTemperatureChanged(temperature);
        }
    }
}
