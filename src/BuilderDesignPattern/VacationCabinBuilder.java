package BuilderDesignPattern;

import java.util.ArrayList;
import java.util.List;

public class VacationCabinBuilder implements HouseBuilder {
    private String style;
    private int floors;
    private final List<String> rooms = new ArrayList<>();
    private final List<String> amenities = new ArrayList<>();

    @Override
    public HouseBuilder start(String style) {
        this.style = style;
        rooms.clear();
        amenities.clear();
        floors = 1;
        return this;
    }

    @Override
    public HouseBuilder floors(int floors) {
        this.floors = floors;
        return this;
    }

    @Override
    public HouseBuilder addRoom(String room) {
        rooms.add(room);
        return this;
    }

    @Override
    public HouseBuilder addAmenity(String amenity) {
        amenities.add(amenity);
        return this;
    }

    @Override
    public House build() {
        return new House(style, floors, rooms, amenities);
    }
}

