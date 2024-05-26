import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

class B7A2<K, V> {
    /**********************************************************/
    /**** Kurzaufgabe 7.2: ****/

    private LinkedList<Pair<K, V>>[] hash;

    /**********************************************************/

    // Constructor
    public B7A2(int m) {
        /**********************************************************/
        /**** Kurzaufgabe 7.2: ****/

        hash = new LinkedList[m];
        for(int i = 0; i < m; i++){
            hash[i] = new LinkedList<>();
        }
        /**********************************************************/
    }

    // Private inner class to represent key-value pairs
    private static class Pair<K, V> {
        /**********************************************************/
        /**** Kurzaufgabe 7.2: ****/

        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        /**********************************************************/
    }

    // Private helper method to calculate the index of the linked list based on
    // key's hash code
    private int addressOfList(K key) {
        /**********************************************************/
        /**** Kurzaufgabe 7.2: ****/

        int hashValue = key.hashCode();
        return Math.floorMod(hashValue, this.hash.length);

        /**********************************************************/
    }

    // Public method to insert a key-value pair into the hash table
    public void insert(K key, V value) {
        /**********************************************************/
        /**** Kurzaufgabe 7.2: ****/

        ListIterator<Pair<K, V>> listIterator = hash[addressOfList(key)].listIterator();

        while (listIterator.hasNext()){
            Pair<K, V> pair = listIterator.next();
            if(pair.key.equals(key) && key.hashCode() == pair.key.hashCode()){
                pair.value = value;
                return;
            }
        }

        hash[addressOfList(key)].offerFirst(new Pair<K, V>(key, value));

        /**********************************************************/
    }

    // Public method to retrieve the value associated with a key
    public V get(K key) {
        /**********************************************************/
        /**** Kurzaufgabe 7.2: ****/

        ListIterator<Pair<K, V>> listIterator = hash[addressOfList(key)].listIterator();

        while (listIterator.hasNext()){
            Pair<K, V> pair = listIterator.next();
            if(pair.key.equals(key)){
                return pair.value;
            }
        }

        /**********************************************************/
        return null;
    }

    // Public method to remove a key-value pair from the hash table
    public boolean remove(K key) {
        /**********************************************************/
        /**** Kurzaufgabe 7.2: ****/

        ListIterator<Pair<K, V>> listIterator = hash[addressOfList(key)].listIterator();

        // Ersetzen Sie diese Kommentarzeile durch Ihren Code!
        while (listIterator.hasNext()){
            Pair<K, V> pair = listIterator.next();
            if(pair.key.equals(key)){
                return hash[addressOfList(key)].remove(pair);
            }
        }

        /**********************************************************/
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";

        B7A2<Integer, String> table = new B7A2<>(n);
        
        String command;
        while (true) {
            System.out.print("Enter command (i to insert, g to get, r to remove, q to quit): ");
            command = input.next();
            if (command.equals("i")) {
                int key = input.nextInt();
                String value = input.next();
                table.insert(key, value);
                System.out.println("Inserted (" + key + ", " + value + ")");
            } else if (command.equals("g")) {
                int key = input.nextInt();
                String result = table.get(key);
                if (result != null) {
                    System.out.println("Key: " + key + ", Value: " + result);
                } else {
                    System.out.println("Key not found.");
                }
            } else if (command.equals("r")) {
                int key = input.nextInt();
                boolean removed = table.remove(key);
                System.out.println("Key " + key + " removed: " + removed);
            } else if (command.equals("q")) {
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        input.close();
    }
}
