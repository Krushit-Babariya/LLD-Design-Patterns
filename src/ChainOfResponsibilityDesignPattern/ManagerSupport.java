package ChainOfResponsibilityDesignPattern;

public class ManagerSupport extends SupportHandler {
    public ManagerSupport() {
        super("Manager Support (Critical Issues)");
    }

    @Override
    protected boolean canHandle(SupportRequest request) {
        return true;
    }

    @Override
    protected void processRequest(SupportRequest request) {
        System.out.println(handlerName + " is handling: " + request);
        System.out.println("Solution: Escalating to executive team and allocating resources.");
        System.out.println("Status: ESCALATED by " + handlerName + "\n");
    }
}

