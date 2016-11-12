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
        boolean cont2;
        String s;
        Types types = new Types();
        System.out.println("Enter a number of pizzas:");
        sum = keyboard.nextInt() * 8;
        System.out.println();
        while (cont) {
            System.out.println("Enter next type of pizza(" + sum + " slices remaining):");
            s = keyboard.next();
            keyboard.nextLine();
            cont2 = true;
            while(cont2) {
                System.out.println("How many slices of that type?");
                i = keyboard.nextInt();
                if (sum - i < 0) {
                    System.out.println("Error: Not enough slices left for amount entered. " + sum + " remaining.");
                }
                else if (i == 0) {
                    System.out.println("Error: Can't be zero slices.");
                } else {
                    cont2 = false;
                }
            }
            types.addType(s, i);
            sum = sum - i;
            if (sum == 0) {
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
        int sl = 0;
        for (i = 0; i < numPeeps; i++) {
            p = new Person(peepsNoSlices[i], types.getKeySet());
            System.out.println("\nPerson " + (i+1) + ":");
            cont = true;
            while(cont) {
                sum = peepsNoSlices[i];
                for (String l : types.getKeySet()) {
                    cont2 = true;
                    while(cont2) {
                        System.out.println("How many slices of " + l + " do you want(" + sum + " slices left to select)?");
                        sl = keyboard.nextInt();
                        if (sum - sl < 0) {
                            System.out.println("Error: Not enough slices left.");
                        } else {
                            cont2 = false;
                        }
                    }
                    sum = sum - sl;
                    p.addPref(l, sl);
                }
                if (sum == 0) {
                    cont = false;
                } else {
                    System.out.println("Error: You're selection must match your number of slices chosen. Start Over\n");
                }
            }

            for (String l : types.getKeySet()) {
                cont = true;
                while(cont) {
                    System.out.println("From 1 being the highest, to " + (types.getKeySet().toArray().length)
                    + " being the lowest, how much do you want " + l + "?");
                    sl = keyboard.nextInt();
                    if (!p.doesContainSlicePref(sl)) {
                        cont = false;
                    } else {
                        System.out.println("You have already entered that priority, enter something else.");
                    }
                }
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