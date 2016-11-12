import java.util.ArrayList;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
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
            s = keyboard.nextLine();
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
        System.out.println(types.getValue("BBQ"));
    }
}