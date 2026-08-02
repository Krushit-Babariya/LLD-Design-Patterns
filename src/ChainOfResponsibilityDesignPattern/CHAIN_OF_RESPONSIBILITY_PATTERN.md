# Chain of Responsibility Design Pattern - Detailed Explanation

## What is Chain of Responsibility Pattern?

The **Chain of Responsibility Design Pattern** is a behavioral design pattern that **passes requests along a chain of handlers**. Each handler decides either to process the request or to pass it to the next handler in the chain.

### Simple Definition
Instead of having one object handle all requests, you create a **chain of handler objects**. When a request comes in, it travels through the chain until one handler decides to process it. If no handler can process it, the request might be rejected or handled by a default handler.

---

## Real-World Analogy

### 🎫 IT Support Ticket System

Imagine you submit a support ticket to a company:

1. **Level 1 Support** receives your ticket first
   - Handles simple issues: password resets, basic questions
   - If they can't solve it, they pass it to Level 2

2. **Level 2 Support** receives the ticket
   - Handles technical issues: software problems, configuration
   - If they can't solve it, they pass it to Level 3

3. **Level 3 Support** receives the ticket
   - Handles complex issues: system failures, critical bugs
   - If they can't solve it, they pass it to Management

4. **Manager Support** receives the ticket
   - Handles critical issues: security breaches, business-critical problems
   - This is usually the final level

**Key Points:**
- Each level tries to handle the request
- If they can't, they pass it to the next level
- The request travels through the chain until handled
- You don't need to know which level will handle it

---

## Problem It Solves

### Without Chain of Responsibility ❌

```java
// All logic in one place - tightly coupled
public void handleRequest(Request request) {
    if (request.getPriority() == 1) {
        level1.handle(request);
    } else if (request.getPriority() <= 3) {
        level2.handle(request);
    } else if (request.getPriority() <= 5) {
        level3.handle(request);
    } else {
        manager.handle(request);
    }
}
```

**Problems:**
- ❌ Hard to add or remove handlers
- ❌ Violates Open/Closed Principle
- ❌ All logic in one place
- ❌ Hard to test individual handlers
- ❌ Tight coupling between client and handlers

### With Chain of Responsibility ✅

```java
// Each handler decides if it can handle the request
level1.setNextHandler(level2);
level2.setNextHandler(level3);
level3.setNextHandler(manager);

level1.handleRequest(request);  // Request travels through chain
```

**Solutions:**
- ✅ Easy to add or remove handlers
- ✅ Each handler is independent
- ✅ Follows Single Responsibility Principle
- ✅ Loose coupling
- ✅ Dynamic chain configuration

---

## Structure & Components

The Chain of Responsibility Pattern consists of **4 main components**:

```
┌─────────────┐
│   Client   │  Creates request and starts chain
└──────┬──────┘
       │
       ▼
┌─────────────┐      ┌──────────────┐
│   Handler   │─────▶│   Handler    │  Abstract class/interface
│  (Abstract) │      │  (Abstract)  │
└──────┬──────┘      └──────┬───────┘
       │                    │
       │ extends            │ extends
       ▼                    ▼
┌─────────────┐      ┌──────────────┐
│Concrete     │      │Concrete      │  Level1, Level2, etc.
│Handler 1    │      │Handler 2     │
└─────────────┘      └──────────────┘
```

### 1. **Handler (Abstract Class)**
Defines the interface for handling requests and maintains a reference to the next handler.

```java
public abstract class SupportHandler {
    protected SupportHandler nextHandler;  // Reference to next handler
    
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
    
    public final void handleRequest(SupportRequest request) {
        if (canHandle(request)) {
            processRequest(request);  // Handle it
        } else if (nextHandler != null) {
            nextHandler.handleRequest(request);  // Pass to next
        } else {
            // No handler can process
        }
    }
    
    protected abstract boolean canHandle(SupportRequest request);
    protected abstract void processRequest(SupportRequest request);
}
```

### 2. **Concrete Handlers**
Implement the handler interface. Each decides if it can handle the request.

```java
public class Level1Support extends SupportHandler {
    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.getPriority() <= 1;  // Can handle priority 1
    }
    
    @Override
    protected void processRequest(SupportRequest request) {
        // Process the request
        System.out.println("Level 1 handling: " + request);
    }
}
```

### 3. **Request Object**
The object that travels through the chain.

```java
public class SupportRequest {
    private String issue;
    private int priority;
    // ... getters
}
```

### 4. **Client**
Creates the chain and initiates the request.

```java
SupportHandler level1 = new Level1Support();
SupportHandler level2 = new Level2Support();
level1.setNextHandler(level2);

SupportRequest request = new SupportRequest("Issue", 2);
level1.handleRequest(request);  // Starts the chain
```

---

## Code Walkthrough

Let's walk through our support ticket example step by step:

### Step 1: Create the Chain

```java
SupportHandler level1 = new Level1Support();
SupportHandler level2 = new Level2Support();
SupportHandler level3 = new Level3Support();
SupportHandler manager = new ManagerSupport();

// Build the chain
level1.setNextHandler(level2);
level2.setNextHandler(level3);
level3.setNextHandler(manager);
```

**What happens:**
- Each handler knows about the next handler
- Chain: Level1 → Level2 → Level3 → Manager
- Visual representation:

```
┌─────────┐    ┌─────────┐    ┌─────────┐    ┌─────────┐
│ Level 1 │───▶│ Level 2 │───▶│ Level 3 │───▶│ Manager │
└─────────┘    └─────────┘    └─────────┘    └─────────┘
```

### Step 2: Create a Request

```java
SupportRequest request = new SupportRequest("Forgot password", 1, "John Doe");
```

**What happens:**
- Request has priority 1 (basic issue)
- Contains issue description and customer name

### Step 3: Send Request Through Chain

```java
level1.handleRequest(request);
```

**What happens:**
1. **Level 1** receives the request
   - Checks: `canHandle(request)` → `priority <= 1` → **TRUE**
   - Processes: "Check if device is plugged in"
   - **STOPS HERE** - doesn't pass to next handler

**Flow:**
```
Request (Priority 1)
    ↓
Level1.canHandle() → TRUE
    ↓
Level1.processRequest()
    ↓
RESOLVED ✅
```

### Step 4: Higher Priority Request

```java
SupportRequest request2 = new SupportRequest("Database timeout", 4, "Tech Corp");
level1.handleRequest(request2);
```

**What happens:**
1. **Level 1** receives the request
   - Checks: `canHandle(request)` → `priority <= 1` → **FALSE**
   - Passes to Level 2

2. **Level 2** receives the request
   - Checks: `canHandle(request)` → `priority <= 3` → **FALSE**
   - Passes to Level 3

3. **Level 3** receives the request
   - Checks: `canHandle(request)` → `priority <= 5` → **TRUE**
   - Processes: "Deep system analysis"
   - **STOPS HERE**

**Flow:**
```
Request (Priority 4)
    ↓
Level1.canHandle() → FALSE → Pass to Level2
    ↓
Level2.canHandle() → FALSE → Pass to Level3
    ↓
Level3.canHandle() → TRUE
    ↓
Level3.processRequest()
    ↓
RESOLVED ✅
```

### Step 5: Critical Request

```java
SupportRequest request3 = new SupportRequest("Security breach", 7, "Enterprise");
level1.handleRequest(request3);
```

**What happens:**
- Level 1, 2, 3 all pass it along
- Manager receives it (can handle any priority)
- Manager processes it

**Flow:**
```
Request (Priority 7)
    ↓
Level1 → Level2 → Level3 → Manager
    ↓
Manager.canHandle() → TRUE (always)
    ↓
Manager.processRequest()
    ↓
ESCALATED ✅
```

---

## Key Benefits

### 1. **Decoupling** 🔗
- Client doesn't know which handler will process the request
- Handlers don't know about other handlers (except next one)

### 2. **Flexibility** 🔄
- Easy to add new handlers
- Easy to remove handlers
- Can reorder the chain dynamically

### 3. **Single Responsibility** 🎯
- Each handler has one responsibility
- Easy to test individual handlers

### 4. **Open/Closed Principle** ✅
- Open for extension (add new handlers)
- Closed for modification (don't change existing code)

### 5. **Dynamic Chain** ⚡
- Can build chain at runtime
- Can change chain based on conditions

---

## Common Use Cases

### 1. **Request Processing in Web Frameworks**
```java
// Middleware chain in web frameworks
Handler authentication = new AuthenticationHandler();
Handler authorization = new AuthorizationHandler();
Handler logging = new LoggingHandler();

authentication.setNextHandler(authorization);
authorization.setNextHandler(logging);

authentication.handleRequest(httpRequest);
```

### 2. **Event Handling**
```java
// UI event handling
Handler buttonHandler = new ButtonClickHandler();
Handler menuHandler = new MenuClickHandler();
Handler windowHandler = new WindowHandler();

buttonHandler.setNextHandler(menuHandler);
menuHandler.setNextHandler(windowHandler);
```

### 3. **Validation Chains**
```java
// Form validation
Validator emailValidator = new EmailValidator();
Validator phoneValidator = new PhoneValidator();
Validator ageValidator = new AgeValidator();

emailValidator.setNextValidator(phoneValidator);
phoneValidator.setNextValidator(ageValidator);
```

### 4. **Logging Systems**
```java
// Different log levels
Logger debugLogger = new DebugLogger();
Logger infoLogger = new InfoLogger();
Logger errorLogger = new ErrorLogger();

debugLogger.setNextLogger(infoLogger);
infoLogger.setNextLogger(errorLogger);
```

### 5. **Purchase Approval System**
```java
// Different approval levels based on amount
Approver manager = new ManagerApprover(1000);
Approver director = new DirectorApprover(10000);
Approver ceo = new CEOApprover(100000);

manager.setNextApprover(director);
director.setNextApprover(ceo);
```

### 6. **Exception Handling**
```java
// Different exception handlers
Handler nullHandler = new NullPointerHandler();
Handler ioHandler = new IOExceptionHandler();
Handler genericHandler = new GenericExceptionHandler();

nullHandler.setNextHandler(ioHandler);
ioHandler.setNextHandler(genericHandler);
```

---

## Variations

### 1. **Default Handler**
Always at the end to handle unhandled requests:

```java
public class DefaultHandler extends SupportHandler {
    @Override
    protected boolean canHandle(SupportRequest request) {
        return true;  // Always handles
    }
    
    @Override
    protected void processRequest(SupportRequest request) {
        System.out.println("Request cannot be handled. Escalating...");
    }
}
```

### 2. **Early Termination**
Stop the chain after first handler processes:

```java
public final void handleRequest(SupportRequest request) {
    if (canHandle(request)) {
        processRequest(request);
        return;  // Stop here
    }
    if (nextHandler != null) {
        nextHandler.handleRequest(request);
    }
}
```

### 3. **Multiple Chains**
Different chains for different request types:

```java
Handler technicalChain = buildTechnicalChain();
Handler billingChain = buildBillingChain();

if (request.getType() == "TECHNICAL") {
    technicalChain.handleRequest(request);
} else {
    billingChain.handleRequest(request);
}
```

---

## Summary

### Key Takeaways

1. **Chain of Responsibility = Pass requests through a chain**
   - Each handler decides to process or pass along
   - Request travels until handled or chain ends

2. **4 Main Components:**
   - **Handler (Abstract)**: Defines interface and chain structure
   - **Concrete Handlers**: Implement handling logic
   - **Request**: Object that travels through chain
   - **Client**: Creates chain and initiates request

3. **Real-World Analogy:**
   - Support ticket system with multiple levels
   - Each level tries to handle, passes if can't

4. **Benefits:**
   - ✅ Decoupling
   - ✅ Flexibility
   - ✅ Single Responsibility
   - ✅ Open/Closed Principle
   - ✅ Dynamic chains

5. **When to Use:**
   - Multiple objects can handle a request
   - Don't know which handler will process
   - Want to decouple sender and receiver
   - Need dynamic chain configuration

### Quick Reference

```java
// 1. Create handlers
Handler handler1 = new ConcreteHandler1();
Handler handler2 = new ConcreteHandler2();

// 2. Build chain
handler1.setNextHandler(handler2);

// 3. Create request
Request request = new Request(data);

// 4. Start chain
handler1.handleRequest(request);
```

---

## 🎯 Practice Exercise

Try implementing:
1. A **Purchase Approval System** with handlers for:
   - Manager (up to $1000)
   - Director (up to $10000)
   - CEO (up to $100000)
   - Board (above $100000)

2. A **Logging System** with handlers for:
   - Debug (level 1)
   - Info (level 2)
   - Warning (level 3)
   - Error (level 4)

3. A **Form Validator** chain that validates:
   - Email format
   - Password strength
   - Age requirement
   - Terms acceptance

---

**Happy Coding! 🚀**

For questions or improvements, feel free to explore the code examples in this folder!

