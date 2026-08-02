package ChainOfResponsibilityDesignPattern;

public class Level2Support extends SupportHandler {
    public Level2Support() {
        super("Level 2 Support (Technical Issues)");
    }

    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.getPriority() <= 3;
    }

    @Override
    protected void processRequest(SupportRequest request) {
        System.out.println(handlerName + " is handling: " + request);
        System.out.println("Solution: Checking system logs and performing diagnostics.");
        System.out.println("Status: RESOLVED by " + handlerName + "\n");
    }
}

