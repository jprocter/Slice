public class Person {
    private int numSlices;
    private int[] prefs;
    private Map<int, int> slicePref = new Map<>();

    public Person(int n, int[] p, int[] perSlicePref) {
        numSlices = n;
        prefs = new int[p.length];
        for (int i = 0; i < p.length; i++) {
            prefs[i] = p[i];
        }
        for (i = 0; i < perSlicePref.length; i++) {
            slicePref.put(i, perSlicePref[i]);
        }
    }

    public int getNumSlices() {
        return numSlices;
    }

    public int getSlicePref(int key) {
        return slicePref.get(key);
    }

    public void decSlicePref(int key) {
        slicePref.put(key, slicePref.get(key) - 1);
    }
}