package BuilderDesignPattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class House {
    private final String style;
    private final int floors;
    private final List<String> rooms;
    private final List<String> amenities;

    House(String style, int floors, List<String> rooms, List<String> amenities) {
        this.style = style;
        this.floors = floors;
        this.rooms = new ArrayList<>(rooms);
        this.amenities = new ArrayList<>(amenities);
    }

    public String style() {
        return style;
    }

    public int floors() {
        return floors;
    }

    public List<String> rooms() {
        return Collections.unmodifiableList(rooms);
    }

    public List<String> amenities() {
        return Collections.unmodifiableList(amenities);
    }

    @Override
    public String toString() {
        return "House{" +
                "style='" + style + '\'' +
                ", floors=" + floors +
                ", rooms=" + rooms +
                ", amenities=" + amenities +
                '}';
    }
}

