package CommandDesignPattern;

public class TVOffCommand implements Command {
    private final TV tv;
    private boolean previousState;

    public TVOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        previousState = tv.isOn();
        tv.turnOff();
    }

    @Override
    public void undo() {
        if (previousState) {
            tv.turnOn();
        }
    }
}

