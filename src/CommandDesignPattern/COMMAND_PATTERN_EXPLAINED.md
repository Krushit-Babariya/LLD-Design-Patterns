# Command Design Pattern - Detailed Explanation

## What is Command Pattern?

The **Command Design Pattern** is a behavioral design pattern that **encapsulates a request as an object**, allowing you to parameterize clients with different requests, queue operations, log requests, and support undo operations.

### Simple Definition
Instead of directly calling a method on an object, you create a **command object** that contains all the information needed to perform the action. This command object can be stored, passed around, executed later, or even undone.

---

## Real-World Analogy

### 🏠 Home Automation Remote Control

Imagine you have a **universal remote control** for your smart home:

- **Remote Control** = Invoker (the thing that triggers commands)
- **Buttons on Remote** = Commands (encapsulated actions)
- **Devices (Lights, TV, etc.)** = Receivers (the actual things that do the work)

**How it works:**
1. You press a button on the remote (e.g., "Turn on Living Room Light")
2. The remote doesn't directly control the light
3. Instead, it sends a **command object** to the light
4. The command object knows how to turn the light on
5. You can also press "Undo" to reverse the last action

**Why this is powerful:**
- You can program any button to do anything
- You can create "Party Mode" that combines multiple commands
- You can undo actions
- You can schedule commands to run later
- You can log all commands for debugging

---

## Problem It Solves

### Without Command Pattern ❌

```java
// Direct method calls - tightly coupled
Light light = new Light("Living Room");
light.turnOn();  // Direct call - can't undo, can't queue, can't log
```

**Problems:**
- ❌ No way to undo operations
- ❌ Can't queue or schedule operations
- ❌ Can't log operations
- ❌ Can't combine multiple operations
- ❌ Tight coupling between caller and receiver

### With Command Pattern ✅

```java
// Command object - loosely coupled
Command lightOn = new LightOnCommand(light);
remote.pressButton(lightOn);  // Can undo, queue, log, combine
```

**Solutions:**
- ✅ Undo/Redo functionality
- ✅ Queue operations
- ✅ Log operations
- ✅ Combine multiple commands (macros)
- ✅ Loose coupling

---

## Structure & Components

The Command Pattern consists of **5 main components**:

```
┌─────────────┐
│   Client    │  Creates command objects
└──────┬──────┘
       │
       ▼
┌─────────────┐      ┌──────────────┐
│  Invoker    │─────▶│   Command    │  Interface
│ (Remote)    │      │  (Interface) │
└─────────────┘      └──────┬───────┘
                            │
                            │ implements
                            ▼
                   ┌─────────────────┐
                   │ ConcreteCommand │  LightOnCommand, TVOffCommand, etc.
                   └────────┬────────┘
                            │
                            │ uses
                            ▼
                   ┌─────────────┐
                   │  Receiver   │  Light, TV, etc.
                   └─────────────┘
```

### 1. **Command Interface**
Defines the contract for all commands.

```java
public interface Command {
    void execute();  // Execute the command
    void undo();     // Undo the command
}
```

### 2. **Concrete Command**
Implements the Command interface. Each command knows:
- Which receiver to use
- What action to perform
- How to undo the action

```java
public class LightOnCommand implements Command {
    private final Light light;  // The receiver
    
    public void execute() {
        light.turnOn();  // Perform the action
    }
    
    public void undo() {
        light.turnOff();  // Reverse the action
    }
}
```

### 3. **Receiver**
The actual object that performs the work.

```java
public class Light {
    public void turnOn() { /* ... */ }
    public void turnOff() { /* ... */ }
}
```

### 4. **Invoker**
Holds and executes commands. Doesn't know what the command does, just executes it.

```java
public class RemoteControl {
    private Command command;
    
    public void setCommand(Command cmd) {
        this.command = cmd;
    }
    
    public void pressButton() {
        command.execute();  // Just execute, doesn't know what it does
    }
}
```

### 5. **Client**
Creates command objects and sets them on the invoker.

```java
Light light = new Light("Living Room");
Command lightOn = new LightOnCommand(light);
remote.setCommand(lightOn);
```

---

## Code Walkthrough

Let's walk through our home automation example step by step:

### Step 1: Create Receivers (Devices)

```java
// These are the actual devices in your home
Light livingRoomLight = new Light("Living Room");
Light kitchenLight = new Light("Kitchen");
TV livingRoomTV = new TV("Living Room");
```

**What happens:**
- We create physical devices that can perform actions
- `Light` can `turnOn()` and `turnOff()`
- `TV` can `turnOn()`, `turnOff()`, `setVolume()`, etc.

### Step 2: Create Commands

```java
// Wrap each action in a command object
Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
Command kitchenLightOn = new LightOnCommand(kitchenLight);
Command tvOn = new TVOnCommand(livingRoomTV);
```

**What happens:**
- Each command object knows:
  - Which device to control (receiver)
  - What action to perform
  - How to undo the action

**Inside LightOnCommand:**
```java
public class LightOnCommand implements Command {
    private final Light light;  // Knows which light
    
    public void execute() {
        light.turnOn();  // Knows what to do
    }
    
    public void undo() {
        light.turnOff();  // Knows how to undo
    }
}
```

### Step 3: Create Invoker (Remote Control)

```java
RemoteControl remote = new RemoteControl(3);  // 3 button slots
```

**What happens:**
- Remote control is created with 3 slots
- Each slot can hold an "ON" command and an "OFF" command

### Step 4: Assign Commands to Remote

```java
remote.setCommand(0, livingRoomLightOn, livingRoomLightOff);
remote.setCommand(1, kitchenLightOn, kitchenLightOff);
remote.setCommand(2, tvOn, tvOff);
```

**What happens:**
- Slot 0: Controls living room light
- Slot 1: Controls kitchen light
- Slot 2: Controls TV

**Visual representation:**
```
Remote Control:
┌─────────────────────────────────┐
│ [Slot 0] ON  │  OFF             │  → Living Room Light
│ [Slot 1] ON  │  OFF             │  → Kitchen Light
│ [Slot 2] ON  │  OFF             │  → TV
│              [UNDO]             │
└─────────────────────────────────┘
```

### Step 5: Execute Commands

```java
remote.pressOnButton(0);  // Press ON button in slot 0
```

**What happens:**
1. Remote calls `onCommands[0].execute()`
2. `LightOnCommand.execute()` is called
3. `light.turnOn()` is executed
4. Living room light turns on!

**Flow:**
```
User presses button
    ↓
RemoteControl.pressOnButton(0)
    ↓
LightOnCommand.execute()
    ↓
Light.turnOn()
    ↓
Light is ON! ✅
```

### Step 6: Undo Functionality

```java
remote.pressUndoButton();
```

**What happens:**
1. Remote remembers the last command executed
2. Calls `lastCommand.undo()`
3. Command reverses its action
4. Light turns off!

**Flow:**
```
User presses UNDO
    ↓
RemoteControl.pressUndoButton()
    ↓
LightOnCommand.undo()
    ↓
Light.turnOff()
    ↓
Light is OFF! ✅
```

### Step 7: Macro Commands (Combining Multiple Commands)

```java
// Create a "Party Mode" that does multiple things at once
Command partyModeOn = new MacroCommand(Arrays.asList(
    livingRoomLightOn,
    kitchenLightOn,
    tvOn
));
```

**What happens:**
- `MacroCommand` contains a list of commands
- When executed, it runs all commands in sequence
- When undone, it undoes all commands in reverse order

**Inside MacroCommand:**
```java
public void execute() {
    for (Command command : commands) {
        command.execute();  // Execute each command
    }
}

public void undo() {
    // Undo in reverse order
    for (int i = commands.size() - 1; i >= 0; i--) {
        commands.get(i).undo();
    }
}
```

**Example:**
```java
partyRemote.pressOnButton(0);
// Output:
// Executing macro command...
// Living Room light is now ON
// Kitchen light is now ON
// Living Room TV is now ON
```

---

## Key Benefits

### 1. **Decoupling** 🔗
- Invoker (remote) doesn't know about receivers (devices)
- Commands act as a bridge between them

### 2. **Undo/Redo** ↩️
- Commands can store state to reverse operations
- Easy to implement undo functionality

### 3. **Queuing** 📋
- Commands can be stored in queues
- Can execute commands later or in batches

### 4. **Logging** 📝
- Can log all commands
- Useful for debugging and auditing

### 5. **Macro Commands** 🎯
- Combine multiple commands into one
- Execute complex operations with a single button

### 6. **Flexibility** 🔄
- Easy to add new commands
- Don't need to modify existing code

---

## Common Use Cases

### 1. **Text Editor (Undo/Redo)**
```java
// Every action (typing, deleting, formatting) is a command
Command typeCommand = new TypeCommand("Hello");
Command deleteCommand = new DeleteCommand();
editor.execute(typeCommand);
editor.undo();  // Removes "Hello"
```

### 2. **GUI Buttons/Menus**
```java
// Each button click is a command
Button saveButton = new Button(new SaveCommand());
Button printButton = new Button(new PrintCommand());
```

### 3. **Transaction Systems**
```java
// Database operations as commands
Command insertCommand = new InsertCommand(data);
Command updateCommand = new UpdateCommand(id, data);
transaction.execute(insertCommand);
transaction.rollback();  // Undo all commands
```

### 4. **Job Queues**
```java
// Queue commands to execute later
Queue<Command> jobQueue = new Queue<>();
jobQueue.add(new ProcessImageCommand(image));
jobQueue.add(new SendEmailCommand(email));
// Execute later
```

### 5. **Remote Procedure Calls (RPC)**
```java
// Network requests as commands
Command remoteCommand = new RemoteCommand("server.com", "method", params);
remoteCommand.execute();  // Executes on remote server
```

### 6. **Game Development**
```java
// Player actions as commands
Command moveCommand = new MoveCommand(player, x, y);
Command attackCommand = new AttackCommand(player, enemy);
// Can replay, undo, or save game state
```

---

## Summary

### Key Takeaways

1. **Command Pattern = Encapsulate requests as objects**
   - Instead of calling methods directly, create command objects

2. **5 Main Components:**
   - **Command Interface**: Defines `execute()` and `undo()`
   - **Concrete Commands**: Implement the interface
   - **Receiver**: The actual object that does the work
   - **Invoker**: Executes commands (doesn't know what they do)
   - **Client**: Creates and configures commands

3. **Real-World Analogy:**
   - Remote control (Invoker) → Buttons (Commands) → Devices (Receivers)

4. **Benefits:**
   - ✅ Undo/Redo
   - ✅ Queuing
   - ✅ Logging
   - ✅ Macro commands
   - Decoupling

5. **When to Use:**
   - Need undo/redo functionality
   - Need to queue operations
   - Need to log operations
   - Need to combine operations
   - Want to decouple invoker and receiver

### Quick Reference

```java
// 1. Create receiver
Light light = new Light("Living Room");

// 2. Create command
Command lightOn = new LightOnCommand(light);

// 3. Create invoker
RemoteControl remote = new RemoteControl(1);

// 4. Set command
remote.setCommand(0, lightOn, lightOff);

// 5. Execute
remote.pressOnButton(0);

// 6. Undo
remote.pressUndoButton();
```

---

## 🎯 Practice Exercise

Try implementing:
1. A `VolumeUpCommand` and `VolumeDownCommand` for the TV
2. A `DimLightCommand` that sets light brightness
3. A "Movie Mode" macro that:
   - Turns off all lights
   - Turns on TV
   - Sets TV volume to 50%

---

**Happy Coding! 🚀**

For questions or improvements, feel free to explore the code examples in this folder!

