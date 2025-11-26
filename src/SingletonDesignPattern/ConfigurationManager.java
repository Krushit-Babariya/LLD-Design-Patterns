package SingletonDesignPattern;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ConfigurationManager {
    private static volatile ConfigurationManager instance;
    private final Map<String, String> settings = new HashMap<>();

    private ConfigurationManager() {
        settings.put("theme", "light");
        settings.put("region", "us-east");
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            synchronized (ConfigurationManager.class) {
                if (instance == null) {
                    instance = new ConfigurationManager();
                }
            }
        }
        return instance;
    }

    public void update(String key, String value) {
        settings.put(key, value);
    }

    public String get(String key) {
        return settings.get(key);
    }

    public Map<String, String> snapshot() {
        return Collections.unmodifiableMap(new HashMap<>(settings));
    }
}

