import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class B1A2 {

    public static int printPermutations(int[] data, int d) {
        /**********************************************************/
        /**** Kurzaufgabe 1.2: ****/
        if (d == data.length - 1) {
            System.out.println(Arrays.toString(data));
            return 1;
        }

        int realValue = data[d];
        int permutations = 0;
        for (int i = d; i < data.length; i++) {
            data[d] = data[i];
            data[i] = realValue;

            permutations += printPermutations(data, d + 1);

            data[i] = data[d];
            data[d] = realValue;
        }

        /**********************************************************/
        return permutations;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /**********************************************************/
        /**** Kurzaufgabe 1.2: ****/

        int[] L;
        try {
            int n = input.nextInt();

            if (n < 0) {
                System.err.println("n needs to be positive!");
                return;
            }

            L = new int[n];

            for (int i = 0; i < n; i++) {
                L[i] = input.nextInt();
            }
        } catch (InputMismatchException e) {
            System.err.println("arguments need to be integers");
            return;
        } catch (NoSuchElementException e) {
            System.err.println("the amount of list elements needs to match the first parameter and there must be at least one parameter");
            return;
        }

        System.out.printf("Es gibt %d Permutationen der Eingabe.", printPermutations(L, 0));

        /**********************************************************/
        input.close();
    }
}
