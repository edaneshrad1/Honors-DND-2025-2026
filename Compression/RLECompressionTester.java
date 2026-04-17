public class RLECompressionTester {
    public static void main(String[] args) {

        //testing encode
        try {
            RLECompression.encode("text.txt");
        } catch (Exception e) {
            System.out.println("bad");
        }

        //testing decode
        try {
            RLECompression.decode("text.txt");
        } catch (Exception e) {
            System.out.println("bad");
        }

        //testing bwTransform
        try {
            RLECompression.bwTransform("text.txt");
        } catch (Exception e) {
            System.out.println("bad");
        }

        //testing invertBWTransform
        try {
            RLECompression.invertBWTransform("text.txt");
        } catch (Exception e) {
            System.out.println("bad");
        }
    }
}
