package CommandDesignPattern;

public class RemoteControl {
    private final Command[] onCommands;
    private final Command[] offCommands;
    private Command lastCommand;

    public RemoteControl(int slots) {
        onCommands = new Command[slots];
        offCommands = new Command[slots];

        Command noCommand = new NoCommand();
        for (int i = 0; i < slots; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        lastCommand = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void pressOnButton(int slot) {
        if (slot >= 0 && slot < onCommands.length) {
            onCommands[slot].execute();
            lastCommand = onCommands[slot];
        }
    }

    public void pressOffButton(int slot) {
        if (slot >= 0 && slot < offCommands.length) {
            offCommands[slot].execute();
            lastCommand = offCommands[slot];
        }
    }

    public void pressUndoButton() {
        System.out.println("--- Undo pressed ---");
        lastCommand.undo();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------ Remote Control ------\n");
        for (int i = 0; i < onCommands.length; i++) {
            sb.append("[slot ").append(i).append("] ")
              .append(onCommands[i].getClass().getSimpleName())
              .append("    ")
              .append(offCommands[i].getClass().getSimpleName())
              .append("\n");
        }
        return sb.toString();
    }
}

