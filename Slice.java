import java.util.*;

public class Slice {
    ArrayList<Person> people = new ArrayList<Person>();

    public static void main(String args[]) {
        Random rand = new Random();
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
                p.addSlicePref(sl, l);
            }
            people.add(p);
        }
        System.out.println();
        for (Person ph : people) {
            System.out.println(ph);
        }



        /* SLICE ALGORITHM
        /
        /  Made by Roy and Raines.
        /
        /
        */

        //Compute the maximum of pizza slices wanted by any individual.
        Arrays.sort(peepsNoSlices);
        int max = peepsNoSlices[peepsNoSlices.length - 1];

        int k, leftForType, val, u;
        //That's how many rounds there will be
        for (i = 1; i <= max; i++) {
            for (String j : types.getKeySet()) { //for however many types of pizza there are
                leftForType = types.getValue(j);

                //sort people by prefs numbers of this type in descending order
                Collections.sort(people, (Person a, Person b) -> {
                    if (a.getPref(j) > b.getPref(j)) {
                        return -1;
                    } else if (a.getPref(j) < b.getPref(j)) {
                        return 1;
                    } else {
                        return 0;
                    }
                });

                cont = true;
                k = 0;
                while(cont) { //for however many people there are
                    if (k >= people.size()) {
                        break;
                    }
                    val = people.get(k).getPref(j);
                    if (val > 0 && leftForType - val >= 0) {
                        people.get(k).putOnDoorstep(j, true);
                        leftForType = leftForType - val;
                    } else if (val > 0) {
                        people.get(k).putOnDoorstep(j, true);
                        leftForType = 0;
                    } else {

                    }
                    if (leftForType <= 0 || k >= people.size()) {
                        cont = false;
                    }
                    k++;
                }
                if (leftForType != 0) { //if not all of this type are assigned to doorsteps
                    cont = true;
                    k = people.size() - 1;
                    while(cont) {
                        if (k < 0) {
                            break;
                        }
                        people.get(k).putOnDoorstep(j, true);
                        leftForType = leftForType - 1;
                        if (leftForType <= 0 || k < 0) {
                            cont = false;
                        }
                        k--;
                    }
                }
            }

            int d;
            for (u = 0; u < people.size(); u++) {
                if (people.get(u).getNumSlices() > 0) {
                    cont = true;
                    d = 1;
                    while(cont) {
                        if (d > types.getKeySet().toArray().length) {
                            cont = false;
                        } else if (people.get(u).isOnDoorstep(people.get(u).getSlicePref(d))) {
                            people.get(u).addPizza(people.get(u).getSlicePref(d), 1 + people.get(u).getPizza(people.get(u).getSlicePref(d)));
                            types.addType(people.get(u).getSlicePref(d), types.getValue(people.get(u).getSlicePref(d)) - 1);
                            cont = false;
                            people.get(u).decPref(people.get(u).getSlicePref(d));
                        }
                        d++;
                    }
                    people.get(u).decNumSlices();
                }
                people.get(u).sweepDoorstep();
            }

        }

        System.out.println();
        for (Person ph : people) {
            System.out.println(ph);
        }
        System.out.println();
        for (String gh : types.getKeySet()) {
            System.out.println(gh + " : " + types.getValue(gh));
        }
    }
}