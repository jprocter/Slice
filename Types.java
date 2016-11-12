import java.util.*;

public class Types {
    private Map<String, Integer> types = new HashMap<>();

    public Types() {}

    public void addType(String str, int a) {
        types.put(str, a);
    }

    public Set<String> getKeySet() {
        return types.keySet();
    }

    public int getValue(String key) {
        return types.get(key);
    }
}