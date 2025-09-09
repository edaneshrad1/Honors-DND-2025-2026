import java.util.ArrayList;

public class pugTester {
    public static void main(String[] args) {
        ArrayList<Dog> dogs = new ArrayList<Dog>();
        // Dog one = new Dog("fido", "Golden Retriever");
        // Dog two = new Dog("fluffy", "German Shepherd");
        // Dog three = new Dog("Mr. Theiss", "Golden lab");
        // Dog four = new Dog("Cookie", "Shitzu");
        // Dog five = new Dog("Mr. Stout", "Golden doodle");
        // Dog six = new Dog("Xander", new String("Gol" + "den"));
        // dogs.add(one);
        // dogs.add(two);
        // dogs.add(three);
        // dogs.add(four);
        // dogs.add(five);
        // dogs.add(1, six);
        // dogs.add(new Dog("Lady", "Golden"));
        // dogs.add(new Dog("Paco", "Mutt"));
        // dogs.add(new Dog("Fifi", "Mutt"));
        // dogs.add(new Dog("Hector", new String("Gol" + "den")));
        // dogs.add(new Dog("Luna", "Golden"));
        // dogs.add(new Dog("Rosie", "Labardoodle"));
        // dogs.add(new Dog("Liberty", "German Shepherd"));
        // dogs.add(new Dog("Monchy", "Golden"));
        for (int i = 0; i < 1000000; i++) {
            if (i % 2 == 0) {
                dogs.add(new Dog("Tommy", "Golden"));
            } else {
                dogs.add(new Dog("Jerry", "Mutt"));
            }
        }
        // System.out.println("Before");
        // for (int i = 0; i < dogs.size(); i++) {
        // System.out.println(dogs.get(i).toString());
        // }
        // System.out.println();
        PugSaver.rescuePugs(dogs);
        System.out.println("After");
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i).toString());
        }
    }
}
