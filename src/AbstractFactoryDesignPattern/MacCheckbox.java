package AbstractFactoryDesignPattern;

public class MacCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Render macOS-styled checkbox");
    }
}

