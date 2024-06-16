import java.util.Scanner;
import java.util.Arrays;

class B10A3 {

    public static int Kreuzfahrtschiffes(int x, Integer[] G) {
        int fahrten = 0;

        /**********************************************************/
        /**** Langaufgabe 10.3: ****/

        // O(n log n)
        Arrays.sort(G);

        // O(n)
        for (int i = 0, j = G.length - 1; i <= j; j--, fahrten++) {
            if (G[i] > x) {
                continue; // the behavior for this case was not defined, in this case we let the person drown on the boat
                // I am sorry :(
            }
            if (i == j) {
                fahrten++; // here, we need to increase the counter again because break will avoid the usual addition
                break;
            }

            int smallestRemaining = G[i];
            int biggestRemaining = G[j];

            if (smallestRemaining + biggestRemaining <= x) {
                i++;
            }
        }

        /**********************************************************/

        return fahrten;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        int x = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";
		Integer[] G = new Integer[n];

        for (int i = 0; i < n; i++) {
            G[i] = input.nextInt();
        }

        int fahrten = Kreuzfahrtschiffes(x, G);
        System.out.println("Mindestanzahl Fahrten: " + fahrten);
        input.close();
    }
}