package AbstractFactoryDesignPattern;

public class WinButton implements Button {
    @Override
    public void render() {
        System.out.println("Render Windows-styled button");
    }
}

