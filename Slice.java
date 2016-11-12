import java.util.*;

public class Slice {
    ArrayList<Person> people = new ArrayList<Person>();

    public static void main(String args[]) {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("How many slices are on a pizza?");
        int numSlices = keyboard.nextInt();
        System.out.println("How many people are there?");
        int numPeeps = keyboard.nextInt();
        int[] peepsNoSlices = new int[numPeeps];
        int i;
        for (i = 0; i < numPeeps; i++) {
            System.out.println("How many slices does Person " + (i + 1) + " want?");
            peepsNoSlices[i] = keyboard.nextInt();
        }
        int sum = 0;
        for (int s : peepsNoSlices) {
            sum += s;
        }
        System.out.println("Total number of slices: " + sum + ". Recommended number of pizzas: " + (int) Math.ceil(((double) sum) / numSlices));
        boolean cont = true;
        String s;
        Types types = new Types();
        sum = (int) Math.ceil(((double) sum) / numSlices) * 8;
        while (cont) {
            System.out.println("Enter next type of pizza: ");
            s = keyboard.next();
            keyboard.nextLine();
            System.out.println("How many slices of that type?");
            i = keyboard.nextInt();
            types.addType(s, i);
            sum = sum - i;
            if (sum <= 0) {
                cont = false;
            }
        }
        System.out.println("Final Order is:");
        for (String h : types.getKeySet()) {
            System.out.println(types.getValue(h) + " slices of " + h);
        }
        System.out.println();

        ArrayList<Person> people = new ArrayList<Person>();
        Person p;
        int sl;
        for (i = 0; i < numPeeps; i++) {
            p = new Person(peepsNoSlices[i], types.getKeySet());
            System.out.println("Person " + (i+1) + ":");
            for (String l : types.getKeySet()) {
                System.out.println("How many slices of " + l + " do you want?");
                sl = keyboard.nextInt();
                p.addPref(l, sl);
            }
            for (String l : types.getKeySet()) {
                System.out.println("From 1 being the highest, to " + (types.getKeySet().toArray().length)
                    + " being the lowest, how much do you want " + l + "?");
                sl = keyboard.nextInt();
                p.addSlicePref(l, sl);
            }
            people.add(p);
        }
        System.out.println();
        for (Person ph : people) {
            System.out.println(ph);
        }
    }
}