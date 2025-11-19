package AbstractFactoryDesignPattern;

public class WinCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Render Windows-styled checkbox");
    }
}

