package ObserverDesignPattern;

interface WeatherStation {
    void addSubscriber(WeatherSubscriber subscriber);

    void removeSubscriber(WeatherSubscriber subscriber);

    void updateTemperature(int newTemperature);
}



