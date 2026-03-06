public class ChocolateHashMapTester {
    public static void main(String[] args) {
        ChocolateHashMap<String, ChocolateBatch> map = new ChocolateHashMap<>();

        ChocolateBatch batch1 = new ChocolateBatch("Chocolate Bar", 22, "Iran", 15);
        ChocolateBatch batch2 = new ChocolateBatch("Chocolate Bar", 35, "Iraq", 33);
        ChocolateBatch batch3 = new ChocolateBatch("Chocolate Bar", 12, "USA", 2);
        ChocolateBatch batch4 = new ChocolateBatch("Chocolate Bar", 12, "USA", 2);
        ChocolateBatch batch5 = new ChocolateBatch("Chocolate Bar", 12, "USA", 2);
        ChocolateBatch batch6 = new ChocolateBatch("Chocolate Bar", 12, "USA", 2);
        ChocolateBatch batch7 = new ChocolateBatch("Chocolate Bar", 12, "USA", 2);
        ChocolateBatch batch8 = new ChocolateBatch("Chocolate Bar", 12, "USA", 2);

        map.put("IR-1", batch1);
        map.put("IR-2", batch2);
        map.put("IR-3", batch3);
        map.put("IR-4", batch4);
        map.put("IR-5", batch5);
        map.put("IR-6", batch6);
        map.put("IR-7", batch7);
        map.put("IR-8", batch8);

        System.out.println("Testing toString");
        System.out.println(map.toString());
        
        System.out.println();

        System.out.println("Testing currentLoadFactor");
        System.out.println(map.currentLoadFactor());

        System.out.println();

        System.out.println("Testing containsKey");
        System.out.println(map.containsKey("IR-1"));

        System.out.println();

        System.out.println("Testing get");
        System.out.println(map.get("IR-1"));

        System.out.println();

        System.out.println("Testing containsValue");
        System.out.println(map.containsValue(batch1));

        System.out.println();

        System.out.println("Testing remove");
        System.out.println(map.remove("IR-2"));

        System.out.println();

        System.out.println("Testing rehash");
        map.rehash(20);
    }
}
