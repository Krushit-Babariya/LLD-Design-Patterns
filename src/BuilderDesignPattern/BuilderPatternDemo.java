package BuilderDesignPattern;

public class BuilderPatternDemo {
    public static void main(String[] args) {
        HouseBuilder builder = new VacationCabinBuilder();
        HouseDirector director = new HouseDirector(builder);

        House familyCabin = director.createFamilyCabin();
        System.out.println("Weekend escape ready: " + familyCabin);

        House writersRetreat = director.createWritersRetreat();
        System.out.println("Quiet retreat ready: " + writersRetreat);
    }
}

