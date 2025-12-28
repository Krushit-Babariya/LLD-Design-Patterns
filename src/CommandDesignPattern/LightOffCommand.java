package CommandDesignPattern;

public class LightOffCommand implements Command {
    private final Light light;
    private boolean previousState;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        previousState = light.isOn();
        light.turnOff();
    }

    @Override
    public void undo() {
        if (previousState) {
            light.turnOn();
        }
    }
}

