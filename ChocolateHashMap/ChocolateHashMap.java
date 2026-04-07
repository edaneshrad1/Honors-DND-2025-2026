/**
 * ChocolateHashMap<K,V>
 *
 * A custom hash map (separate chaining) built for a fictional chocolate factory inventory system.
 * Each bucket is a circular DOUBLY-linked list with a sentinel BatchNode.
 *
 * You are responsible for implementing the methods marked TODO.
 */
public class ChocolateHashMap<K, V> {
    private BatchNode<ChocolateEntry<K, V>>[] buckets;
    private int objectCount;
    private double loadFactorLimit;

    // Constructor: creates a hash map with the given initial bucket size and load factor limit
    @SuppressWarnings("unchecked")
    public ChocolateHashMap(int bucketCount, double loadFactorLimit) {
        this.buckets = (BatchNode<ChocolateEntry<K, V>>[]) new BatchNode[bucketCount];
        fillArrayWithSentinels(buckets);
        this.objectCount = 0;
        this.loadFactorLimit = loadFactorLimit;
    }

    // Constructor: creates an empty hash map with default parameters
    public ChocolateHashMap() {
        this(10, 0.75);
    }

    private static void fillArrayWithSentinels(BatchNode[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new BatchNode();
        }
    }

    // Return a pointer to the bucket array
    public BatchNode<ChocolateEntry<K, V>>[] getBuckets() {
        return this.buckets;
    }

    // Returns true if this map is empty; otherwise returns false.
    public boolean isEmpty() {
        return (objectCount == 0);
    }

    // Returns the number of entries in this map.
    public int size() {
        return objectCount;
    }

    // Return the bucket index for the key
    // Use .hashCode(), but be aware that hashCode can return negative numbers!
    // NOTE: Math.abs(Integer.MIN_VALUE) is still negative. Consider masking the sign bit.
    //O(1)
    private int whichBucket(K key) {

        //hash the key
        int keyIndex = key.hashCode();

        //mod the key by buckets.length
        keyIndex = keyIndex % buckets.length;

        //if the modded key is negative add buckets.length to it
        if (keyIndex < 0) {
            keyIndex += buckets.length;
        }

        return keyIndex;
    }

    // Returns the current load factor (objCount / buckets)
    //O(1)
    public double currentLoadFactor() {
        return (double)(objectCount) / (double)(buckets.length);
    }

    // Return true if the key exists as a key in the map, otherwise false.
    // Use the .equals method to check equality.
    //O(1)
    public boolean containsKey(K key) {

        //call whichBucket on key to find its location in buckets
        int location = whichBucket(key);

        //go to that list in buckets and loop through it
        if (isBucketEmpty(buckets[location])) {
            return false;
        } else {

            //check every node in that list and make sure its key is equal to key
            BatchNode<ChocolateEntry<K, V>> currentNode = buckets[location].getNext();
            while (currentNode != buckets[location]) {
                ChocolateEntry<K, V> entry = currentNode.getEntry();

                //if a node's key equals key immediately return true
                if (entry.getKey().equals(key)) {
                    return true;

                //if currentNode.getKey() is not equal to key continue itterating through the loop
                } else {
                    currentNode = currentNode.getNext();
                    continue;
                }
            }
            
            //if you reach the end of the list and don't find key return false
            return false;
        }
    }

    // Return true if the value exists as a value in the map, otherwise false.
    // Use the .equals method to check equality.
    //O(n)
    public boolean containsValue(V value) {

        //loop through buckets
        for (int i = 0; i< buckets.length; i++) {

            //if the current list is empty continue
            if (isBucketEmpty(buckets[i])) {
                continue;

            //if not check every node in the list and return true if found
            } else {
                BatchNode<ChocolateEntry<K, V>> currentNode = buckets[i].getNext();
                while (currentNode != buckets[i]) {
                    V currentValue = currentNode.getEntry().getValue();

                    //if the current value is null check if value is null too using ==
                    if (currentValue == null) {

                        //if yes return true
                        if (value == null) {
                            return true;

                        //if no continue
                        } else {
                            currentNode = currentNode.getNext();
                            continue;
                        }
                    //if the current value isn't null check if it's equal to value using .equals
                    } else {

                        //if yes return true
                        if (currentValue.equals(value)) {
                            return true;

                        //if no continue
                        } else {
                            currentNode = currentNode.getNext();
                            continue;
                        }
                    }
                }
            }
        }
        
        //if value is not found return false
        return false;
    }

    // Puts a key-value pair into the map.
    // If the key already exists in the map you should *not* add the key-value pair.
    // Return true if the pair was added; false if the key already exists.
    // If a pair should be added, add it to the END of the bucket.
    // After adding the pair, check if the load factor is greater than the limit.
    // - If so, you must call rehash with double the current bucket size.
    //O(1) if you don't need to rehash but O(n) if you need to rehahs
    public boolean put(K key, V value) {

        //if the key already exists do nothing and return false
        if (containsKey(key)) {
            return false;
        }

        //find the bucket you put key into
        int location = whichBucket(key);

        //instantiate the sentinle and its previous at key
        BatchNode<ChocolateEntry<K, V>> sentinle = buckets[location];
        BatchNode<ChocolateEntry<K, V>> sentinlePrev = sentinle.getPrevious();

        //make a new ChocolateEntry with key and value
        ChocolateEntry<K, V> entry = new ChocolateEntry<K, V>(key, value);

        //make a new BatchNode with key where the previous points to sentinlePrev and next to Sentinle
        BatchNode<ChocolateEntry<K, V>> addedNode = new BatchNode<ChocolateEntry<K, V>>(entry, sentinlePrev, sentinle);

        //make the next of the sentinlePrev and the previous of sentinle that new node and 
        sentinlePrev.setNext(addedNode);
        sentinle.setPrevious(addedNode);

        //add to objectCount
        objectCount++;

        //make sure the load factor is chillin
        if (currentLoadFactor() > loadFactorLimit) {
            rehash(buckets.length * 2);
        }

        return true;
    }


    // Returns the value associated with the key in the map.
    // If the key is not in the map, then return null.
    //O(1)
    public V get(K key) {

        //find the location of key
        int location = whichBucket(key);

        //if the list is empty return null
        if (isBucketEmpty(buckets[location])) {
            return null;

        //if not itterate through the list and check the key of every node
        } else {
            BatchNode<ChocolateEntry<K, V>> currentNode = buckets[location].getNext();
            while (currentNode != buckets[location]) {
                ChocolateEntry<K, V> entry = currentNode.getEntry();

                //if the key of currentNode is equal to key yay!
                if (entry.getKey().equals(key)) {
                    return entry.getValue();

                //if not continue
                } else {
                    currentNode = currentNode.getNext();
                    continue;
                }
            }
        }
        
        //if the key is never found return null
        return null;
    }

    // Remove the pair associated with the key.
    // Return true if successful, false if the key did not exist.
    //O(1)
    public boolean remove(K key) {

        //find the location of key
        int location = whichBucket(key);

        //if the list is empty return false
        if (isBucketEmpty(buckets[location])) {
            return false;

        //if not itterate through the list and check the key of every node
        } else {
            BatchNode<ChocolateEntry<K, V>> currentNode = buckets[location].getNext();
            while (currentNode != buckets[location]) {
                ChocolateEntry<K, V> entry = currentNode.getEntry();
                BatchNode<ChocolateEntry<K, V>> prevNode = currentNode.getPrevious();
                BatchNode<ChocolateEntry<K, V>> nextNode = currentNode.getNext();

                //if the key is found make the next of prevNode nextNode and the previous of nextNode prevNode and return true
                if (entry.getKey().equals(key)) {
                    prevNode.setNext(nextNode);
                    nextNode.setPrevious(prevNode);
                    objectCount--;
                    return true;

                //if not continue
                } else {
                    currentNode = currentNode.getNext();
                    continue;
                }
            }
        }
        
        
        //if the key is never found return false
        return false;
    }

    // Rehash the map so that it contains the given number of buckets
    // Loop through all existing buckets, from 0 to length
    // Rehash each object into the new bucket array in the order they appear on the original chain.
    // I.e. if a bucket originally has (sentinel)->J->Z->K, then J will be rehashed first,
    // followed by Z, then K.
    //O(n)
    public void rehash(int newBucketCount) {

        //store buckets as the variable oldBuckets
        BatchNode<ChocolateEntry<K, V>>[] oldBuckets = buckets;

        //make a new array with size newBucketCount
        BatchNode<ChocolateEntry<K, V>>[] newBuckets = new BatchNode[newBucketCount];

        fillArrayWithSentinels(newBuckets);

        //make newBuckets the new array
        this.buckets = newBuckets;

        //reset objectCount to 0
        this.objectCount = 0;

        //loop through the origional array
        for (int i = 0; i < oldBuckets.length; i++) {

            //if buckets[i] isn't empty loop through the list
            if (!isBucketEmpty(oldBuckets[i])) {
                BatchNode<ChocolateEntry<K, V>> currentNode = oldBuckets[i].getNext();
                while (currentNode != oldBuckets[i]) {
                     //put each key, value pair into newBuckets
                     ChocolateEntry<K, V> entry = currentNode.getEntry();
                     put(entry.getKey(), entry.getValue());
                     currentNode = currentNode.getNext();
                }
            //if buckets[i] is empty continue
            } else {
                continue;
            }
        }
    }

    // The output should be in the following format:
    // [ n, k | { b#: k1,v1 k2,v2 k3,v3 } { b#: k1,v1 k2,v2 } ]
    // n is the objCount
    // k is the number of buckets
    // For each bucket that contains objects, create a substring that indicates the bucket index
    // And list all of the items in the bucket (in the order they appear)
    // Example (using chocolate-themed data):
    // [ 3, 10 | { b3: LOT-70,DARK LOT-12,MILK } { b7: LOT-99,WHITE } ]
    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder();
        returnString.append("[ " + objectCount + ", " + buckets.length + " | ");

        //loop through buckets
        for (int i = 0; i < buckets.length; i++) {

            //stop at each bucket that's not empty
            if (isBucketEmpty(buckets[i])) {
                continue;
            } else {

                //append brackets and shit
                returnString.append("{ b" + i + ": ");

                //loop through the list of nodes and append their key and value separated by a comma
                BatchNode<ChocolateEntry<K, V>> currentNode = buckets[i].getNext();
                while (currentNode != buckets[i]) {
                    ChocolateEntry<K, V> entry = currentNode.getEntry();
                    returnString.append(entry.getKey() + "," + entry.getValue() + " ");
                    currentNode = currentNode.getNext();
                }

                //append brackets and shit
                returnString.append("} ");
            }
        }

        returnString.append("]");

        return returnString.toString();
    }

    //method that checks if a bucket is empty
    public boolean isBucketEmpty(BatchNode<ChocolateEntry<K, V>> bucket) {
        if (bucket.getNext() == bucket) {
            return true;
        } else {
            return false;
        }
    }
}
