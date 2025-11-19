package AbstractFactoryDesignPattern;

public class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("Render macOS-styled button");
    }
}

