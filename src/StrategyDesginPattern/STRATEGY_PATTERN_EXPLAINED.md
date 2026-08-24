# Strategy Design Pattern - Complete Explanation

## 📚 Overview

The **Strategy Pattern** is a behavioral design pattern that defines a family of algorithms, encapsulates each one, and makes them interchangeable. It lets the algorithm vary independently from clients that use it.

### Pattern Name & Category
- **Pattern Name:** Strategy
- **Category:** Behavioral Pattern
- **Also Known As:** Policy Pattern

---

## 🎯 Problem Statement

Consider a payment processing system where users can pay using:
- Credit Card
- PayPal
- Bitcoin
- Apple Pay
- Google Pay
- And many more...

**Without Strategy Pattern:** The ShoppingCart class would need to know about all payment methods and contain lots of if-else/switch statements.

```java
// ❌ BAD APPROACH - Without Strategy Pattern
public class ShoppingCart {
    public void checkout(String paymentMethod) {
        if (paymentMethod.equals("CREDIT_CARD")) {
            // Validate card, process payment
        } else if (paymentMethod.equals("PAYPAL")) {
            // Authenticate PayPal, process payment
        } else if (paymentMethod.equals("BITCOIN")) {
            // Validate wallet, mine transaction
        }
        // More payment methods...
    }
}
```

**Problems with this approach:**
1. **Code Explosion:** Class grows with each new payment method
2. **Tight Coupling:** ShoppingCart is tightly coupled to payment implementations
3. **Hard to Maintain:** Adding/removing payment methods requires modifying ShoppingCart
4. **Violates Open/Closed Principle:** Code is open for modification but not closed
5. **Violates Single Responsibility:** ShoppingCart has too many reasons to change

---

## ✅ Solution: Strategy Pattern

**Encapsulate each algorithm in a separate class and make them interchangeable!**

```java
// ✅ GOOD APPROACH - With Strategy Pattern

public interface PaymentStrategy {
    boolean pay(double amount);
    String getStrategyName();
}

public class CreditCardPayment implements PaymentStrategy {
    // Credit card specific logic
}

public class PayPalPayment implements PaymentStrategy {
    // PayPal specific logic
}

public class BitcoinPayment implements PaymentStrategy {
    // Bitcoin specific logic
}

public class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public boolean checkout() {
        return paymentStrategy.pay(totalAmount);
    }
}
```

---

## 🏗️ Component Structure

### 1. **Strategy Interface**
- Defines the contract for all concrete strategies
- Ensures all strategies implement the required methods

```java
public interface PaymentStrategy {
    boolean pay(double amount);
    String getStrategyName();
}
```

### 2. **Concrete Strategies**
- Implement the Strategy interface
- Each encapsulates a specific algorithm

```java
public class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cvv;
    
    @Override
    public boolean pay(double amount) {
        // Credit card payment logic
        return true;
    }
    
    @Override
    public String getStrategyName() {
        return "Credit Card";
    }
}
```

### 3. **Context**
- Maintains a reference to a Strategy object
- Uses the strategy through its interface
- Can switch strategies at runtime

```java
public class ShoppingCart {
    private PaymentStrategy paymentStrategy;
    
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }
    
    public boolean checkout() {
        return paymentStrategy.pay(totalAmount);
    }
}
```

---

## 📊 UML Class Diagram

```
┌──────────────────────────────┐
│      <<interface>>           │
│    PaymentStrategy           │
├──────────────────────────────┤
│ + pay(amount: double)        │
│ + getStrategyName(): String  │
└────────────────┬─────────────┘
                 │
        ┌────────┼────────┬────────────┐
        │        │        │            │
        ▼        ▼        ▼            ▼
   ┌─────────┐ ┌──────┐ ┌──────┐  ┌──────────┐
   │ Credit  │ │PayPal│ │Bitcoin  │ Future
   │  Card   │ │      │ │Payment  │
   └─────────┘ └──────┘ └──────┘  └──────────┘
   
        ▲ (implements)
        │
        │ (uses)
        │
   ┌─────────────────┐
   │  ShoppingCart   │
   ├─────────────────┤
   │- strategy       │
   │- items[]        │
   │- totalAmount    │
   ├─────────────────┤
   │+ setPayment()   │
   │+ checkout()     │
   │+ addItem()      │
   └─────────────────┘
```

---

## 🔄 Sequence Diagram - Payment Processing

```
User         ShoppingCart       PaymentStrategy      CreditCardPayment
 │                │                    │                     │
 │──checkout()──>  │                    │                     │
 │                 │                    │                     │
 │                 │──setPaymentStrategy(creditCard)──┐       │
 │                 │                    │              │       │
 │                 │                    │<─────────────┘       │
 │                 │                    │                     │
 │                 │  pay(totalAmount) │                     │
 │                 │──────────────────>│                      │
 │                 │                    │  validateCard()    │
 │                 │                    │─────────────────>  │
 │                 │                    │  <true>            │
 │                 │                    │<─────────────────  │
 │                 │                    │  processPayment()  │
 │                 │                    │─────────────────>  │
 │                 │                    │  <true>            │
 │                 │                    │<─────────────────  │
 │                 │           true     │                     │
 │                 │<──────────────────│                      │
 │     success     │                    │                     │
 │<──────────────  │                    │                     │
 │                 │                    │                     │
```

---

## 💡 Key Principles

### 1. **Open/Closed Principle (OCP)**
- Open for extension: Add new payment strategies without modifying existing code
- Closed for modification: No need to change ShoppingCart when adding payments

### 2. **Single Responsibility Principle (SRP)**
- Each strategy handles one payment method
- ShoppingCart only manages cart operations

### 3. **Dependency Inversion Principle (DIP)**
- ShoppingCart depends on PaymentStrategy interface, not concrete classes
- Strategies are injected at runtime

---

## 🎓 When to Use Strategy Pattern

✅ **Use Strategy Pattern when:**

1. **Multiple algorithms for a task** - Different payment methods, sorting algorithms, compression types
2. **Avoid if-else/switch chains** - Replace conditional logic with polymorphism
3. **Runtime algorithm selection** - Choose algorithm at runtime based on user input
4. **Algorithms might vary** - Different strategies might be used in different contexts
5. **Easy to add new algorithms** - Without modifying existing client code
6. **Want to isolate algorithm logic** - Keep different implementations separate

❌ **Don't use Strategy Pattern when:**

1. **Only one algorithm** - Simple if-else might be cleaner
2. **Algorithms rarely change** - Pattern adds unnecessary complexity
3. **Very few strategies** - Overkill for 2-3 simple alternatives

---

## 🌍 Real-World Examples

### 1. **Payment Processing** (This Demo)
```
Payment Methods: Credit Card, PayPal, Bitcoin, Apple Pay, etc.
```

### 2. **Sorting Algorithms**
```
Strategies: QuickSort, MergeSort, BubbleSort, HeapSort
Client: Array to be sorted
```

### 3. **Route Navigation**
```
Strategies: Fastest Route, Shortest Route, Scenic Route
Client: Navigation App
```

### 4. **Compression**
```
Strategies: ZIP, RAR, 7-ZIP, GZIP
Client: File Compression Tool
```

### 5. **Authentication**
```
Strategies: OAuth2, JWT, Basic Auth, SAML
Client: Web Application
```

### 6. **Data Caching**
```
Strategies: LRU, FIFO, LFU, TTL
Client: Cache Manager
```

### 7. **Image Processing**
```
Strategies: GaussianBlur, EdgeDetect, Grayscale, Sepia
Client: Image Editor
```

---

## 📝 Implementation Tips

### 1. **Keep Strategies Simple**
- Each strategy should focus on one algorithm
- Avoid mixing business logic

### 2. **Use Factory Pattern**
```java
public class PaymentStrategyFactory {
    public static PaymentStrategy createStrategy(String type) {
        switch(type) {
            case "CREDIT_CARD": return new CreditCardPayment(...);
            case "PAYPAL": return new PayPalPayment(...);
            case "BITCOIN": return new BitcoinPayment(...);
        }
    }
}
```

### 3. **Immutable Strategies**
- Make strategies immutable when possible
- Reduces side effects

### 4. **Strategy Context**
- Keep context simple
- Don't let it know about specific strategies

### 5. **Default Strategy**
```java
public class ShoppingCart {
    private PaymentStrategy paymentStrategy = new DefaultPaymentStrategy();
    // ...
}
```

---

## ⚖️ Pros and Cons

### ✅ Advantages

1. **Flexibility:** Switch algorithms at runtime
2. **Code Reusability:** Strategies can be reused in different contexts
3. **Easier Testing:** Each strategy can be tested independently
4. **Better Maintainability:** Changes to one strategy don't affect others
5. **Follows SOLID Principles:** Especially OCP and SRP
6. **Eliminates Conditional Logic:** No if-else/switch statements
7. **Loose Coupling:** Client depends on interface, not implementations

### ❌ Disadvantages

1. **Increased Classes:** More classes than simple if-else approach
2. **Memory Overhead:** Each strategy is a separate object
3. **Complexity:** Overkill for simple scenarios
4. **Communication Overhead:** More method calls
5. **Learning Curve:** Developers need to understand the pattern

---

## 🔀 Comparison with Similar Patterns

### Strategy vs State Pattern

| Aspect | Strategy | State |
|--------|----------|-------|
| Purpose | Encapsulate interchangeable algorithms | Represent object's internal states |
| Selection | Client chooses strategy | Object changes state internally |
| Change | Deliberate change at runtime | Automatic based on conditions |
| Context | Same across strategies | Different behavior per state |
| Use Case | Payment methods | Object lifecycle (e.g., Order: Pending→Processing→Shipped) |

### Strategy vs Template Method

| Aspect | Strategy | Template Method |
|--------|----------|-----------------|
| Implementation | Composition | Inheritance |
| Flexibility | More flexible | Less flexible |
| Subclassing | No | Yes (required) |
| Algorithm Change | Runtime | Compile time |
| Code Reuse | Limited | Good (via inheritance) |

---

## 🎯 Summary

The **Strategy Pattern** is a powerful design pattern that:

1. **Encapsulates algorithms** in separate classes
2. **Makes them interchangeable** through a common interface
3. **Eliminates conditional logic** and if-else chains
4. **Allows runtime selection** of algorithms
5. **Follows SOLID principles** for better code quality
6. **Improves maintainability** by isolating changes

Use it when you have multiple ways to do something and want to avoid cluttering your code with conditional statements!

---

## 📚 References

- Gang of Four (GoF) Design Patterns
- Head First Design Patterns
- Refactoring Guru - Strategy Pattern
- Oracle Java Documentation

---

**Created:** 2024 | **Updated:** 2026 | **Language:** Java

