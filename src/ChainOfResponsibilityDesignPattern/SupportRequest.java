package ChainOfResponsibilityDesignPattern;

public class SupportRequest {
    private final String issue;
    private final int priority;
    private final String customerName;

    public SupportRequest(String issue, int priority, String customerName) {
        this.issue = issue;
        this.priority = priority;
        this.customerName = customerName;
    }

    public String getIssue() {
        return issue;
    }

    public int getPriority() {
        return priority;
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public String toString() {
        return String.format("[Priority: %d] %s - %s", priority, customerName, issue);
    }
}

