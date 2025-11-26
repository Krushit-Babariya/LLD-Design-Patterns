package BuilderDesignPattern;

public class HouseDirector {
    private final HouseBuilder builder;

    public HouseDirector(HouseBuilder builder) {
        this.builder = builder;
    }

    public House createFamilyCabin() {
        return builder.start("Family Cabin")
                .floors(2)
                .addRoom("Living Room")
                .addRoom("Kitchen")
                .addRoom("Bunk Room")
                .addAmenity("Fireplace")
                .addAmenity("Wraparound Porch")
                .build();
    }

    public House createWritersRetreat() {
        return builder.start("Writer's Retreat")
                .floors(1)
                .addRoom("Studio")
                .addRoom("Library")
                .addAmenity("Skylight")
                .addAmenity("Wood Stove")
                .build();
    }
}

