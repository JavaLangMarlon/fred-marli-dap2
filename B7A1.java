import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

class B7A1 {
    /**********************************************************/
    /**** Kurzaufgabe 7.1: ****/

    // Ersetzen Sie diese Kommentarzeile durch Ihren Code!
    private LinkedList<Pair>[] hash;

    /**********************************************************/

    // Constructor
    public B7A1(int m) {
        /**********************************************************/
        /**** Kurzaufgabe 7.1: ****/

        // Ersetzen Sie diese Kommentarzeile durch Ihren Code!
        hash = new LinkedList[m];
        for(int i = 0; i < m; i++){
            hash[i] = new LinkedList<>();
        }
        /**********************************************************/
    }

    // Private inner class to represent key-value pairs
    private static class Pair {
        /**********************************************************/
        /**** Kurzaufgabe 7.1: ****/

        Integer key;
        Integer value;

        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }

        /**********************************************************/
    }

    // Private helper method to calculate the index of the linked list based on key
    private int addressOfList(Integer key) {
        /**********************************************************/
        /**** Kurzaufgabe 7.1: ****/

        /**********************************************************/
        return Math.floorMod(key, hash.length);
    }

    // Public method to insert a key-value pair into the hash table
    public void insert(Integer key, Integer value) {
        /**********************************************************/
        /**** Kurzaufgabe 7.1: ****/

        ListIterator<Pair> listIterator = hash[addressOfList(key)].listIterator();

        while (listIterator.hasNext()){
            Pair pair = listIterator.next();
            if(pair.key.equals(key)){
                pair.value = value;
                return;
            }
        }

        hash[addressOfList(key)].offerFirst(new Pair(key, value));

        /**********************************************************/
    }

    // Public method to retrieve the value associated with a key
    public Integer get(Integer key) {
        /**********************************************************/
        /**** Kurzaufgabe 7.1: ****/

        ListIterator<Pair> listIterator = hash[addressOfList(key)].listIterator();

        // Ersetzen Sie diese Kommentarzeile durch Ihren Code!
        while (listIterator.hasNext()){
            Pair pair = listIterator.next();
            if(pair.key.equals(key)){
                return pair.value;
            }
        }

        /**********************************************************/
        return null;
    }

    // Public method to remove a key-value pair from the hash table
    public boolean remove(Integer key) {
        /**********************************************************/
        /**** Kurzaufgabe 7.1: ****/

        ListIterator<Pair> listIterator = hash[addressOfList(key)].listIterator();

        // Ersetzen Sie diese Kommentarzeile durch Ihren Code!
        while (listIterator.hasNext()){
            Pair pair = listIterator.next();
            if(pair.key.equals(key)){
                return hash[addressOfList(key)].remove(pair);
            }
        }

        /**********************************************************/
        return false; // Key not found
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";

        B7A1 table = new B7A1(n);
        
        String command;
        while (true) {
            System.out.print("Enter command (i to insert, g to get, r to remove, q to quit): ");
            command = input.next();
            if (command.equals("i")) {
                int key = input.nextInt();
                int value = input.nextInt();
                table.insert(key, value);
                System.out.println("Inserted (" + key + ", " + value + ")");
            } else if (command.equals("g")) {
                int key = input.nextInt();
                Integer result = table.get(key);
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