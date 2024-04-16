import java.time.Duration;
import java.time.Instant;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class B2A1 {
    public static int compare_cnt = 0; // Zähler für die Vergleiche während des sortierens.

    /**
     * * @param args die Parameter aus der Kommandozeile
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /**********************************************************/
        /**** Kurzaufgabe 2.1: ****/

        // we utilize our code from last week
        int[] L;
        try {
            int n = input.nextInt(); // read n

            if (n < 0) { // check if n is a natural number, everything else does not make sense in this context
                System.err.println("n needs to be positive!");
                return;
            }

            L = new int[n]; // create an array with the given size

            for (int i = 0; i < n; i++) {
                L[i] = input.nextInt(); // read the values from stdio
            }
        } catch (InputMismatchException e) {
            // if the user enters the wrong type of arguments, for example, a string
            // a InputMismatchException will be thrown and we stop the execution here
            System.err.println("arguments need to be integers");
            return;
        } catch (NoSuchElementException e) {
            // if too few elements are provided by the user, a NoSuchElementException
            // will be thrown and we stop here.
            System.err.println("the amount of list elements needs to match the first parameter and there must be at least one parameter");
            return;
        }

        // we need this condition twice, so we save it in a variable
        boolean suitableForConsole = L.length > 0 && L.length < 101;

        if (suitableForConsole) {
            // print the array on one line
            System.out.print(L[0]);
            for (int i = 1; i < L.length; i++) {
                System.out.print(" " + L[i]);
            }
            System.out.println();
        }
        // measure time
        Instant start = Instant.now();
        insertionSort(L); // sort the array
        Instant finish = Instant.now();
        System.out.println("Time: " + Duration.between(start, finish).toMillis());
        if (suitableForConsole) {
            // print the array on one line
            System.out.print(L[0]);
            for (int i = 1; i < L.length; i++) {
                System.out.print(" " + L[i]);
            }
            System.out.println();
        }
        System.out.println(isSorted(L) ? "Feld ist sortiert!" : "FEHLER: Feld ist NICHT sortiert!");
        System.out.printf("Das Sortieren des Arrays hat %d Vergleiche benoetigt.\n", compare_cnt);

        /**********************************************************/
        input.close();
    }

    /**
     * Prüft, ob ein Array absteigend sortiert ist.
     *
     * @param numbers Das zu prüfende Array
     * @return true genau dann, wenn das Array sortiert ist
     */
    public static boolean isSorted(int[] numbers) {
        /**********************************************************/
        /**** Kurzaufgabe 2.1: ****/

        // here, we iterate over the array and check if any elements violate the order
        for (int i = 1; i < numbers.length; i++) {
            // here, we check for the violation, if it exists, we can return false
            if (numbers[i] < numbers[i-1]) {
                return false;
            }
        }
        // we need to loop over the whole array to make sure the array is sorted, eventually we can return true

        /**********************************************************/

        return true;
    }

    /**
     * Insertion-Sort-Algorithmus.
     *
     * @param numbers das zu sortierende Array
     */
    public static void insertionSort(int[] numbers) {

        /**********************************************************/
        /**** Kurzaufgabe 2.1: ****/

        compare_cnt = 0; // reset counter to 0 to make sure the number is correct

        // here, we implemented the insertion-sort-algorithm analogous to how it was introduced in the lecture
        for (int i = 1; i < numbers.length; i++) {
            int key = numbers[i];
            int j;
            // we can use a for loop here to make the code a bit shorter
            for (j = i - 1; j > -1 && numbers[j] > key; j--) {
                compare_cnt++; // if we enter the loop here, we know that a comparison between the array elements
                // has been performed
                numbers[j + 1] = numbers[j];
            }
            compare_cnt += j > -1 ? 1 : 0; // if we leave the for loop but the j > -1 condition still holds, then we
            // had a last check numbers[j] > key which returned false, thus then we need to increment the counter by 1
            numbers[j + 1] = key;
        }

        // assert correctness of the algorithm
        assert isSorted(numbers) : "insertion sort did not work correctly";

        /**********************************************************/

    }
}