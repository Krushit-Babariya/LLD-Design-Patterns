package ObserverDesignPattern;

import java.util.ArrayList;
import java.util.List;

interface WeatherStation {
    void addSubscriber(WeatherSubscriber subscriber);

    void removeSubscriber(WeatherSubscriber subscriber);

    void updateTemperature(int newTemperature);
}

interface WeatherSubscriber {
    void onTemperatureChanged(int temperature);
}

class SimpleWeatherStation implements WeatherStation {
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

class PhoneDisplay implements WeatherSubscriber {
    private final String ownerName;

    public PhoneDisplay(String ownerName) {
        this.ownerName = ownerName;
    }

    @Override
    public void onTemperatureChanged(int temperature) {
        System.out.println("Phone of " + ownerName + ": temperature is now " + temperature + "°C");
    }
}

class GardenSprinkler implements WeatherSubscriber {
    @Override
    public void onTemperatureChanged(int temperature) {
        if (temperature >= 30) {
            System.out.println("Garden sprinkler: It's hot (" + temperature + "°C), turning sprinklers ON.");
        } else {
            System.out.println("Garden sprinkler: Temperature (" + temperature + "°C) is fine, sprinklers OFF.");
        }
    }
}


