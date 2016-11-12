import java.util.*;

public class People {
    private ArrayList<Person> people = new ArrayList<Person>();

    public People() {}

    public void addPerson(int n, Set<String> keySet) {
        Person p = new Person(n, keySet);
        people.add(p);
    }
}