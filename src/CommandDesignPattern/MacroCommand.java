package CommandDesignPattern;

import java.util.ArrayList;
import java.util.List;

// Concrete Command - executes multiple commands as a single command
// Real-world analogy: "Party Mode" button that turns on all lights and TV at once
public class MacroCommand implements Command {
    private final List<Command> commands;

    public MacroCommand(List<Command> commands) {
        this.commands = new ArrayList<>(commands);
    }

    @Override
    public void execute() {
        System.out.println("Executing macro command...");
        for (Command command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        System.out.println("Undoing macro command...");
        // Undo in reverse order
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
    }
}

