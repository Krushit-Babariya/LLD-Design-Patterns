package CommandDesignPattern;

import java.util.Arrays;

public class CommandPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Command Design Pattern Demo ===");
        System.out.println("Real-World Analogy: Home Automation Remote Control\n");

        // Create receivers (the actual devices)
        Light livingRoomLight = new Light("Living Room");
        Light kitchenLight = new Light("Kitchen");
        TV livingRoomTV = new TV("Living Room");

        // Create commands
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        Command kitchenLightOn = new LightOnCommand(kitchenLight);
        Command kitchenLightOff = new LightOffCommand(kitchenLight);
        Command tvOn = new TVOnCommand(livingRoomTV);
        Command tvOff = new TVOffCommand(livingRoomTV);

        // Create remote control with 3 slots
        RemoteControl remote = new RemoteControl(3);

        // Assign commands to remote slots
        remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
        remote.setCommand(1, kitchenLightOn, kitchenLightOff);
        remote.setCommand(2, tvOn, tvOff);

        System.out.println(remote);

        // Demonstrate basic command execution
        System.out.println("--- Scenario 1: Basic Commands ---");
        remote.pressOnButton(0);  // Turn on living room light
        remote.pressOnButton(1);  // Turn on kitchen light
        remote.pressOnButton(2);  // Turn on TV

        System.out.println("\n--- Scenario 2: Undo Functionality ---");
        remote.pressUndoButton(); // Undo last command (TV off)

        System.out.println("\n--- Scenario 3: Turn Off Devices ---");
        remote.pressOffButton(0); // Turn off living room light
        remote.pressOffButton(1); // Turn off kitchen light

        // Demonstrate macro command (Party Mode)
        System.out.println("\n--- Scenario 4: Macro Command (Party Mode) ---");
        Command partyModeOn = new MacroCommand(Arrays.asList(
            livingRoomLightOn,
            kitchenLightOn,
            tvOn
        ));
        Command partyModeOff = new MacroCommand(Arrays.asList(
            livingRoomLightOff,
            kitchenLightOff,
            tvOff
        ));

        RemoteControl partyRemote = new RemoteControl(1);
        partyRemote.setCommand(0, partyModeOn, partyModeOff);
        
        System.out.println("\nActivating Party Mode:");
        partyRemote.pressOnButton(0);
        
        System.out.println("\nDeactivating Party Mode:");
        partyRemote.pressOffButton(0);

        System.out.println("\n=== Demo Complete ===");
    }
}

