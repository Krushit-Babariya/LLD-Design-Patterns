package ChainOfResponsibilityDesignPattern;

public class Level1Support extends SupportHandler {
    public Level1Support() {
        super("Level 1 Support (Basic Issues)");
    }

    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.getPriority() <= 1;
    }

    @Override
    protected void processRequest(SupportRequest request) {
        System.out.println(handlerName + " is handling: " + request);
        System.out.println("Solution: Check if device is plugged in and powered on.");
        System.out.println("Status: RESOLVED by " + handlerName + "\n");
    }
}

