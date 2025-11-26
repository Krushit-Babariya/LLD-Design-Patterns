package SingletonDesignPattern;

public class SingletonPatternDemo {
    public static void main(String[] args) {
        ConfigurationManager designerLaptop = ConfigurationManager.getInstance();
        designerLaptop.update("theme", "dark");
        designerLaptop.update("autosave", "15min");

        ConfigurationManager coworkingStation = ConfigurationManager.getInstance();
        System.out.println("Settings at coworking station: " + coworkingStation.snapshot());

        coworkingStation.update("region", "eu-central");
        System.out.println("Final shared settings: " + designerLaptop.snapshot());
    }
}

