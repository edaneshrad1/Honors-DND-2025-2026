public class HashFunction {

    //takes in a name and returns a unique int between 0 and 500
    public static int hashFunction(String name) {
        String str = name.toLowerCase();
        int sum = 0;
        //add up the values of every letter in name
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                continue;
            } else {
                sum += str.charAt(i)*Math.pow(22, str.length() - i - 1);
            }
        }
        return sum % 500;
    }
}