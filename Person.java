import java.util.Map;
import java.util.HashMap;
import java.util.*;

public class Person {
    private int numSlices;
    private Map<String, Integer> prefs = new HashMap<>();
    private Map<String, Integer> slicePrefs = new HashMap<>();
    private Map<String, Integer> pizza = new HashMap<>();

    public Person(int n, Set<String> keySet) {
        numSlices = n;
        for (String s : keySet) {
            prefs.put(s, 0);
            slicePrefs.put(s, 0);
            pizza.put(s, 0);
        }
    }

    public int getNumSlices() {
        return numSlices;
    }

    public int getSlicePref(String key) {
        return slicePrefs.get(key);
    }

    public void decSlicePref(String key) {
        slicePrefs.put(key, slicePrefs.get(key) - 1);
    }

    public int getPref(String key) {
        return prefs.get(key);
    }

    public void addPref(String key, Integer a) {
        prefs.put(key, a);
    }

    public void addSlicePref(String key, Integer a) {
        slicePrefs.put(key, a);
    }

    public void addPizza(String key, Integer a) {
        pizza.put(key, a);
    }

    public String toString() {
        String s = "This person wants " + numSlices + " slices of pizza.\n";
        for (String l : prefs.keySet()) {
            s = s + prefs.get(l) + " " + l + "\n";
        }
        for (String l : slicePrefs.keySet()) {
            s = s + l + " is their priority " + slicePrefs.get(l) + "\n";
        }
        return s;
    }

    public boolean doesContainSlicePref(Integer value) {
        return slicePrefs.containsValue(value);
    }


}