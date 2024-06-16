import java.util.Comparator;
import java.util.Scanner;
import java.util.Arrays;

class B10A2 {

    public static int zooManager(Integer[] C, Integer[] F) {
        int cost = 0;

        /**********************************************************/
        /**** Langaufgabe 10.2: ****/

        // sort the arrays, O(n log n)
        Arrays.sort(C);
        Arrays.sort(F);

        // O(n)
        for (int i = 0; i < C.length; i++) {
            cost += C[i] * F[F.length - 1 - i] * 1000;
        }

        /**********************************************************/

        return cost;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";
        Integer[] C = new Integer[n];
        Integer[] F = new Integer[n];

        for (int i = 0; i < n; i++) {
            C[i] = input.nextInt();
        }
        for (int i = 0; i < n; i++) {
            F[i] = input.nextInt();
        }

        int cost = zooManager(C, F);
        System.out.println("Minimale Kosten: " + cost + " Euro");
        input.close();
    }
}