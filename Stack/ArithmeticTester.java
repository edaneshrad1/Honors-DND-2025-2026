public class ArithmeticTester {
    public static void main(String[] args) {
        // System.out.println(Arithmetic.convertClassicToStout("3 * 4 + 7"));
        // System.out.println(Arithmetic.convertStoutToList("33 4 * 7 +"));

        String equation = Arithmetic.convertClassicToStout("3 * ( 4 - 3 + ( 5 - 1 )");
        // 3 4 3 - 5 1 - + *
        System.out.println(equation);

        System.out.println(Arithmetic.evaluateStout(equation));
    }
}
