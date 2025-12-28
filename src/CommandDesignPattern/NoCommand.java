package CommandDesignPattern;

// Null Object Pattern - represents a command that does nothing
// Used to avoid null checks in the RemoteControl
public class NoCommand implements Command {
    @Override
    public void execute() {
        // Do nothing
    }

    @Override
    public void undo() {
        // Do nothing
    }
}

