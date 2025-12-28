package CommandDesignPattern;

public class TV {
    private final String location;
    private boolean isOn;
    private int volume;
    private int channel;

    public TV(String location) {
        this.location = location;
        this.isOn = false;
        this.volume = 10;
        this.channel = 1;
    }

    public void turnOn() {
        isOn = true;
        System.out.println(location + " TV is now ON (Channel: " + channel + ", Volume: " + volume + ")");
    }

    public void turnOff() {
        isOn = false;
        System.out.println(location + " TV is now OFF");
    }

    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(100, volume));
        if (isOn) {
            System.out.println(location + " TV volume set to " + this.volume);
        }
    }

    public void setChannel(int channel) {
        this.channel = channel;
        if (isOn) {
            System.out.println(location + " TV channel changed to " + this.channel);
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public int getVolume() {
        return volume;
    }

    public int getChannel() {
        return channel;
    }

    public String getLocation() {
        return location;
    }
}

