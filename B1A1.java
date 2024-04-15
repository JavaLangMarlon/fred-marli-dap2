import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

class B1A1 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        /**********************************************************/
        /****                Kurzaufgabe 1.1b:                 ****/
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

        /**********************************************************/
        input.close();


        /**********************************************************/
        /****                Kurzaufgabe 1.1c:                 ****/

        if (args.length != 1) {
            System.err.println("Exactly one command argument is needed;");
            return;
        }
        int k;
        try {
            k = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("k must be an integer.");
            return;
        }
        if (k >= L.length) {
            System.err.println("k must be < n");
        }
        Arrays.sort(L);
        System.out.printf("The %d-smallest value of L is %d\n", k, L[k - 1]);

        /**********************************************************/
    }
}
