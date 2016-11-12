import java.util.Map;
import java.util.HashMap;
import java.util.*;

public class Person {
    private int numSlices;
    private Map<String, Integer> prefs = new HashMap<>();
    private Map<Integer, String> slicePrefs = new HashMap<>();
    private Map<String, Integer> pizza = new HashMap<>();
    private Map<String, Boolean> doorstep = new HashMap<>();

    public Person(int n, Set<String> keySet) {
        numSlices = n;
        for (String s : keySet) {
            prefs.put(s, 0);
            pizza.put(s, 0);
        }
    }

    public int getNumSlices() {
        return numSlices;
    }

    public void decNumSlices() {
        numSlices--;
    }

    public void putOnDoorstep(String key, Boolean b) {
        doorstep.put(key, b);
    }

    public boolean isOnDoorstep(String key) {
        return doorstep.containsKey(key);
    }

    public void sweepDoorstep() {
        doorstep.clear();
    }

    public String getSlicePref(Integer key) {
        return slicePrefs.get(key);
    }

    public void decPref(String key) {
        prefs.put(key, prefs.get(key) - 1);
    }

    public int getPref(String key) {
        return prefs.get(key);
    }

    public void addPref(String key, Integer a) {
        prefs.put(key, a);
    }

    public void addSlicePref(Integer key, String a) {
        slicePrefs.put(key, a);
    }

    public void addPizza(String key, Integer a) {
        pizza.put(key, a);
    }

    public int getPizza(String key) {
        return pizza.get(key);
    }

    public String toString() {
        String s = "This person wants " + numSlices + " slices of pizza.\n";
        for (String l : prefs.keySet()) {
            s = s + prefs.get(l) + " " + l + "\n";
        }
        for (Integer l : slicePrefs.keySet()) {
            s = s + slicePrefs.get(l) + " is their priority " + l + ".\n";
        }
        for (String l : pizza.keySet()) {
            s = s + pizza.get(l) + " " + l + "\n";
        }
        return s;
    }

    public boolean doesContainSlicePref(Integer value) {
        return slicePrefs.containsKey(value);
    }


}