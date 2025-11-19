package AbstractFactoryDesignPattern;

public class AbstractFactoryDemo {
    public static void main(String[] args) {
        runDemo("windows");
        runDemo("mac");
    }

    private static void runDemo(String platform) {
        GUIFactory factory = switch (platform.toLowerCase()) {
            case "windows" -> new WindowsFactory();
            case "mac" -> new MacFactory();
            default -> throw new IllegalArgumentException("Unknown platform " + platform);
        };

        Application app = new Application(factory);
        System.out.println("Rendering UI for " + platform);
        app.renderUI();
        System.out.println("----");
    }
}

