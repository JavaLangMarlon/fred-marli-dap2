import java.time.Duration;
import java.time.Instant;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class B2A2 {
    public static int compare_cnt = 0; // Zähler für die Vergleiche während des sortierens.

    /**
     * @param args die Parameter aus der Kommandozeile
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /**********************************************************/
        /****                 Kurzaufgabe 2.2:                 ****/

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
        mergeSort(L); // sort the array
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
        System.out.println(B2A1.isSorted(L) ? "Feld ist sortiert!" : "FEHLER: Feld ist NICHT sortiert!");
        System.out.printf("Das Sortieren des Arrays hat %d Vergleiche benoetigt.\n", compare_cnt);

        /**********************************************************/
        input.close();
    }

    /**
     * Führt den Merge-Sort-Algorithmus aus.
     *
     * @param numbers das zu sortierende Array
     */
    public static void mergeSort(int[] numbers) {
        compare_cnt = 0; // reset counter to ensure correctness
        int[] tmpArray = new int[numbers.length];
        System.arraycopy(numbers, 0, tmpArray, 0, numbers.length);
        mergeSort(numbers, tmpArray, 0, numbers.length);

        // make sure that you have implemented and compiled B2A1 (run: javac B2A1.java)
        assert B2A1.isSorted(numbers);
    }

    /**
     * Hilfsfunktion, welche die rekursiven Aufrufe ausführt.
     *
     * @param target Zielarray für die sortierte Folge
     * @param source ein zu sortierendes Array mit Integer Zahlen
     * @param left   die linke Grenze des zu sortierenden Teilarrays, inklusiv
     * @param right  die rechte Grenze des zu sortierenden Teilarrays, exklusiv
     */
    public static void mergeSort(int[] target, int[] source, int left, int right) {
        /**********************************************************/
        /****                 Kurzaufgabe 2.2:                 ****/
        // implement the algorithm analogous to how it was introduced in the lecture
        // as the right index is exclusive (see comment below why) we need to add + 1 in the if condition
        // and remove + 1 in the second merge sort call
        if (left + 1 < right) {
            int m = (left + right)/2;
            mergeSort(target, source, left, m);
            assert B2A1.isSequenceSorted(target, left, m); // assert merge sort worked correctly
            mergeSort(target, source, m, right);
            assert B2A1.isSequenceSorted(target, m, right); // assert merge sort worked correctly
            merge(target, source, left, m, right);
            assert B2A1.isSequenceSorted(target, left, right); // assert merge worked correctly
        }
        /**********************************************************/
    }

    private static void merge(int[] target, int[] source, int left, int m, int right) {
        int i = 0, j = 0;
        // loop through the interval of the array, similar to how it was done in the lecture
        // there is one significant difference though, here m is the first element of the
        // right interval and the right parameter is exclusive. This is necessary because
        // in the merge-method the length is given as the right parameter
        for (int k = left; k < right; k++) {
            assert B2A1.isSequenceSorted(target, left, k); // loop invariant
            // setting a last parameter to infinity is not possible as we only have limited
            // space in our tmpArray, thus we do something different. We add two if checks
            // here to check if the parameters are exceeding their bound. Then, we can infer
            // that we need to use the other interval.
            if (m + j >= right) {
                target[k] = source[left + i];
                i++;
            } else if (left + i >= m) {
                target[k] = source[m + j];
                j++;
            } else if (source[left + i] <= source[m + j]) {
                // we only increment the counter if we really reach past the bound checks
                compare_cnt++;
                target[k] = source[left + i];
                i++;
            } else {
                // we only increment the counter if we really reach past the bound checks
                compare_cnt++;
                target[k] = source[m + j];
                j++;
            }
        }
        // here, we need to copy our results from our target array into our source
        // array so that we can use our sorting progress in the next merge iterations
        System.arraycopy(target, left, source, left, right - left);
    }


//     A couple words about exercise 3:
//
//     As we can see in the code, the run time of Merge-Sort is not dependent on the inner structure of the input array.
//     Thus, it always has a run time of O(n log n).
//
//     Insertion-Sort on the other hand has a run time which is dependent on the inner structure of the input array.
//     Its worst case happens when it is reversed. Then, it has a run time of O(n^2). This is also the average run time
//     of the algorithm. However, if the array is already sorted, the algorithm has its best case. Then, the run time
//     linear.
//
//     We can see these effects if we increase the size of our inputs. If the arrays are sorted, the insertion sort will
//     eventually perform better than the merge sort algorithm. However, if we randomly choose arrays or even arrays which
//     are sorted in descending order, the merge sort algorithm will perform significantly better.
//     However, we can only be certain that we will see this behavior if we let n grow to a significantly higher number.


}