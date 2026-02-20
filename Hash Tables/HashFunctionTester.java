public class HashFunctionTester {
    public static void main(String[] args) {

        String[] names = {"Mateo Atluri", "Asher Butan", "Xander Cheuk", "Taj Clement",
                "Camille Condren", "Evan Daneshrad", "Felicia Duan", "Jake Effress",
                "Zachary Figlin", "James Graczyk", "David Hadi", "Quinn Harris", "Jackson Hubbard",
                "Siona Kirschner", "Dylan Martin", "Morgan Maynard", "Yari Milakin",
                "Waller Morton", "Andrew Stout", "Mattin Tasbihgoo", "Andrew Theiss",
                "Carter Tsao"};
       
        String[] output = new String[500];
        for (int i = 0; i < names.length; i++) {
            int nextIndex = HashFunction.hashFunction(names[i]);
            if (output[nextIndex] != null) {
                System.out.println("duplicate Found for name: " + names[i] + " at index: " + nextIndex);
            }
        }
        // output[nextIndex] = names[i];
    }
}
