package CommandDesignPattern;

public class Light {
    private final String location;
    private boolean isOn;

    public Light(String location) {
        this.location = location;
        this.isOn = false;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(location + " light is now ON");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(location + " light is now OFF");
    }

    public boolean isOn() {
        return isOn;
    }

    public String getLocation() {
        return location;
    }
}

