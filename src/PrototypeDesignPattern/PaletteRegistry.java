package PrototypeDesignPattern;

import java.util.HashMap;
import java.util.Map;

public class PaletteRegistry {
    private final Map<String, ColorSwatch> registry = new HashMap<>();

    public void register(String key, ColorSwatch prototype) {
        registry.put(key, prototype);
    }

    public ColorSwatch create(String key) {
        ColorSwatch prototype = registry.get(key);
        if (prototype == null) {
            throw new IllegalArgumentException("No swatch registered for key " + key);
        }
        return prototype.clone();
    }
}

