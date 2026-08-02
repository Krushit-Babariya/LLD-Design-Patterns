package ChainOfResponsibilityDesignPattern;

public abstract class SupportHandler {
    protected SupportHandler nextHandler;
    protected String handlerName;

    public SupportHandler(String handlerName) {
        this.handlerName = handlerName;
    }

    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public final void handleRequest(SupportRequest request) {
        if (canHandle(request)) {
            processRequest(request);
        } else if (nextHandler != null) {
            System.out.println(handlerName + " cannot handle. Passing to next level...");
            nextHandler.handleRequest(request);
        } else {
            System.out.println("No handler available for: " + request);
        }
    }

    protected abstract boolean canHandle(SupportRequest request);
    protected abstract void processRequest(SupportRequest request);
}

