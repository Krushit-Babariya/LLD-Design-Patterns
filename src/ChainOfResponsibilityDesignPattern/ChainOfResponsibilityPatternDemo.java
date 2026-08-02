package ChainOfResponsibilityDesignPattern;

public class ChainOfResponsibilityPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Chain of Responsibility Design Pattern Demo ===");
        System.out.println("Real-World Analogy: IT Support Ticket System\n");

        SupportHandler level1 = new Level1Support();
        SupportHandler level2 = new Level2Support();
        SupportHandler level3 = new Level3Support();
        SupportHandler manager = new ManagerSupport();

        level1.setNextHandler(level2);
        level2.setNextHandler(level3);
        level3.setNextHandler(manager);

        System.out.println("Support Chain: Level1 -> Level2 -> Level3 -> Manager\n");
        System.out.println("Priority Levels:");
        System.out.println("1 = Basic (Password reset, simple questions)");
        System.out.println("2-3 = Technical (Software issues, configuration)");
        System.out.println("4-5 = Complex (System failures, critical bugs)");
        System.out.println("6+ = Critical (Business impact, security issues)\n");
        System.out.println("=".repeat(50) + "\n");

        SupportRequest request1 = new SupportRequest("Forgot password", 1, "John Doe");
        System.out.println("Request 1:");
        level1.handleRequest(request1);

        SupportRequest request2 = new SupportRequest("Application not starting", 2, "Jane Smith");
        System.out.println("Request 2:");
        level1.handleRequest(request2);

        SupportRequest request3 = new SupportRequest("Database connection timeout", 4, "Tech Corp");
        System.out.println("Request 3:");
        level1.handleRequest(request3);

        SupportRequest request4 = new SupportRequest("Security breach detected", 7, "Enterprise Client");
        System.out.println("Request 4:");
        level1.handleRequest(request4);

        System.out.println("=== Demo Complete ===");
    }
}

