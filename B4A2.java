import java.util.Random;

class B4A2 {
    // Hilfsfunktion zum Vertauschen zweier Werte
    public static void swap(int[] data, int i, int j) {
        int h = data[i];
        data[i] = data[j];
        data[j] = h;
    }

    // Hilfsfunktion zum Berechnen von n!
    public static int fact(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }

    public static void updatePermutation(int[] numbers, int[] counters) {
        /**********************************************************/
        /****                Langaufgabe 4.2:                  ****/

        for (int i = 0; i < numbers.length; i++) {
            if (counters[i] < i) {
                // do as described
                if (i % 2 == 0) {
                    swap(numbers, 0, i);
                } else {
                    swap(numbers, i, counters[i]);
                }
                counters[i]++;

                break; // permutation was calculated, break out
            } else {
                // repeat loop
                counters[i] = 0;
            }
        }

        /**********************************************************/
    }

    public static void shufflePermutation(int[] numbers) {
        /**********************************************************/
        /****                Langaufgabe 4.2:                  ****/

        Random randGen = new Random();

        for (int i = 0; i < numbers.length - 1; i++) {
            swap(numbers, i, randGen.nextInt(i, numbers.length));  // returns a value between i and numbers.length - 1
        }

        /**********************************************************/
    }

    public static int insertionSort(int[] numbers) {
        /**********************************************************/
        /****                Langaufgabe 4.2:                  ****/

        // we use a modified version of our code from Blatt 2

        int shiftAmount = 0;

        // here, we implemented the insertion-sort-algorithm analogous to how it was introduced in the lecture
        for (int i = 1; i < numbers.length; i++) {
            int key = numbers[i];
            int j;
            // we can use a for loop here to make the code a bit shorter
            for (j = i - 1; j > -1 && numbers[j] > key; j--) {
                shiftAmount++;
                numbers[j + 1] = numbers[j];
            }
            numbers[j + 1] = key;
        }

        /**********************************************************/
        return shiftAmount;
    }


    public static void main(String[] args) {
        System.out.println(insertionSort(new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}));

        int n = Integer.parseInt(args[0]);

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = i + 1;
        }
        
        /**********************************************************/
        /****                Langaufgabe 4.2:                  ****/

        if (0 < n && n <= 10) {
            // permutate the array n! - 1 times
            double shiftAmountMean = insertionSort(numbers.clone());
            int maxShiftAmount = 0;
            int[] counters = new int[n];

            for (int i = 1; i < fact(n) - 1; i++) {
                // clone the array to guarantee the correctness of Heap's algorithm
                int shifts = insertionSort(numbers.clone());
                if (shifts > maxShiftAmount) {
                    maxShiftAmount = shifts;
                }
                shiftAmountMean = (shiftAmountMean * (i - 1) + shifts)/i;

                updatePermutation(numbers, counters);
            }
            System.out.printf("""
                    Durchschnittliche Anzahl von Verschiebungen: %f
                    Schlimmste Anzahl von Verschiebungen: %d
                    """, shiftAmountMean, maxShiftAmount);
        } else {
            int k = Integer.parseInt(args[1]);

            double shiftAmountMean = insertionSort(numbers.clone());

            for (int i = 1; i < k; i++) {
                shufflePermutation(numbers);
                shiftAmountMean = (shiftAmountMean * (i - 1) + insertionSort(numbers.clone()))/i;
            }

            System.out.println(shiftAmountMean);
            System.out.printf("Durchschnittliche Anzahl von Verschiebungen: %f\n", shiftAmountMean);
        }

        /**********************************************************/
    }
}
