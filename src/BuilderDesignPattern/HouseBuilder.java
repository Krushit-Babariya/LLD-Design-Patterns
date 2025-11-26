package BuilderDesignPattern;

public interface HouseBuilder {
    HouseBuilder start(String style);

    HouseBuilder floors(int floors);

    HouseBuilder addRoom(String room);

    HouseBuilder addAmenity(String amenity);

    House build();
}

