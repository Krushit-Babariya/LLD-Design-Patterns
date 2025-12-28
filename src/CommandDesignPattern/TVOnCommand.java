package CommandDesignPattern;

public class TVOnCommand implements Command {
    private final TV tv;
    private boolean previousState;

    public TVOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        previousState = tv.isOn();
        tv.turnOn();
    }

    @Override
    public void undo() {
        if (!previousState) {
            tv.turnOff();
        }
    }

    public void add() {

    }
}

