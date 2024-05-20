import java.util.Scanner;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;

class B5A1 {

    public static int compareCount = 0;

    static void swap(int[] data, int i, int j) {
        // see previous exercises
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    // test if the data[l,r] is partitioned with pivotelement p at position m.
    // data[i] >=p for l<=i < m
    // data[i] <= p for m < i <=r
    public static boolean isPartitioned(int[] data, int l, int r, int m, int p) {
        /**********************************************************/
        /**** Kurzaufgabe 5.1: ****/

        if (data[m] != p) {
            return false;  // first invariant violated
        }

        for (int i = l; i <= m; i++) {
            if (data[i] < p) {
                return false;  // second invariant violated
            }
        }

        for (int i = m; i <= r; i++) {
            if (data[i] > p) {
                return false;  // third invariant violated
            }
        }

        /**********************************************************/
        return true;
    }

    // l,r inclusive -> partition data[l,r]
    public static int partition(int[] data, int l, int r) {

        /**********************************************************/
        /**** Kurzaufgabe 5.1: ****/

        // implement the partition algorithm from the lecture but descending

        int pivot = data[r];
        int i = l - 1;
        for (int j = l; j <= r - 1; j++) {
            if (data[j] <= pivot) {
                i++;
                swap(data, i, j);
            }
            compareCount++;
        }
        swap(data, ++i, r);
        assert isPartitioned(data, l, r, i, pivot);
        return i;
        /**********************************************************/
    }

    // l,r inclusive -> sort data[l,r]
    public static void qsort(int[] data, int l, int r) {

        /**********************************************************/
        /**** Kurzaufgabe 5.1: ****/

        // implement qsort according to the lecture
        if (l < r) {
            int m = partition(data, l, r);
            qsort(data, l, m - 1);
            qsort(data, m + 1, r);
        }

        /**********************************************************/
    }

    // check if the array sorted discreading.
    public static boolean isSorted(int[] data) {
        /**********************************************************/
        /**** Kurzaufgabe 5.3: ****/

        // here, we iterate over the array and check if any elements violate the order
        for (int i = 1; i < data.length; i++) {
            // here, we check for the violation, if it exists, we can return false
            if (data[i] > data[i - 1]) {
                return false;
            }
        }
        // we need to loop over the whole array to make sure the array is sorted, eventually we can return true
        return true;
        /**********************************************************/
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();

        // Drucken, wenn die Länge for dem Sortieren weniger als 20 beträgt
        if (arr.length < 20) {
            System.out.println("Input Array:");
            System.out.println(Arrays.toString(arr));
        }
        /**********************************************************/
        /**** Kurzaufgabe 5.1, 5.3 & 5.4: ****/

        Instant start = Instant.now();
        // sort array
        qsort(arr, 0, arr.length - 1);
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();
        System.out.println("Time: " + time);
        System.out.println("Compare Count: " + compareCount);
        assert isSorted(arr);
        if (arr.length < 20) {
            System.out.printf("After sorting:\n%s\n", Arrays.toString(arr));
        }

        /**********************************************************/
    }
}
