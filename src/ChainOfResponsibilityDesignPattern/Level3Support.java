package ChainOfResponsibilityDesignPattern;

public class Level3Support extends SupportHandler {
    public Level3Support() {
        super("Level 3 Support (Expert Team)");
    }

    @Override
    protected boolean canHandle(SupportRequest request) {
        return request.getPriority() <= 5;
    }

    @Override
    protected void processRequest(SupportRequest request) {
        System.out.println(handlerName + " is handling: " + request);
        System.out.println("Solution: Deep system analysis and code-level investigation.");
        System.out.println("Status: RESOLVED by " + handlerName + "\n");
    }
}

