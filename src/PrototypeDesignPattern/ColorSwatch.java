package PrototypeDesignPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ColorSwatch implements Cloneable {
    private String name;
    private int red;
    private int green;
    private int blue;
    private List<String> tags = new ArrayList<>();

    public ColorSwatch(String name, int red, int green, int blue) {
        this.name = name;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    private ColorSwatch(ColorSwatch swatch) {
        this.name = swatch.name;
        this.red = swatch.red;
        this.green = swatch.green;
        this.blue = swatch.blue;
        this.tags = new ArrayList<>(swatch.tags);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void rename(String newName) {
        this.name = newName;
    }

    public List<String> tags() {
        return tags;
    }

    @Override
    public ColorSwatch clone() {
        return new ColorSwatch(this);
    }

    @Override
    public String toString() {
        return "ColorSwatch{" +
                "name='" + name + '\'' +
                ", rgb=(" + red + "," + green + "," + blue + ")" +
                ", tags=" + tags +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorSwatch that = (ColorSwatch) o;
        return red == that.red && green == that.green && blue == that.blue && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, red, green, blue);
    }
}

