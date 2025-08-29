

public class pugTester {
    public static void main(String[] args) {
        MyArrayList<Dog> dogs = new MyArrayList<Dog>();
        Dog one = new Dog("fido", "Golden Retriever");
        Dog two = new Dog("fluffy", "German Shepherd");
        Dog three = new Dog("Mr. Theiss", "Golden lab");
        Dog four = new Dog("Cookie", "Shitzu");
        dogs.add(one);
        dogs.add(two);
        dogs.add(three);
        dogs.add(four);
        System.out.println("Before");
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i).toString());
        }
        System.out.println();
        PugSaver.rescuePugs(dogs);
        System.out.println("After");
        for (int i = 0; i < dogs.size(); i++) {
            System.out.println(dogs.get(i).toString());
        }
    }
}
