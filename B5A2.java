
import java.util.Scanner;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;

class B5A2 {
    // test if the data[l,r] is partitioned with pivotelement p1 at position m1, p2
    // at m2.
    // m1 <= m2 and p1 >= p2
    // data[i] >=p1 for l<=i < m1
    // p2<=data[i] <= p1 for m1 < p < m2
    // data[i] <= p2 for m2 < p <=r

    public static boolean isPartitioned(int[] data, int l, int r, int m1, int p1, int m2, int p2) {

        /**********************************************************/
        /**** Kurzaufgabe 5.2: ****/

        int maxP = Math.max(p1, p2);
        int minP = Math.min(p1, p2);

        if (data[m1] != maxP || data[m2] != minP) {
            return false;  // first invariant violated
        }

        for (int i = l; i <= m1; i++) {
            if (data[i] < maxP) {
                System.out.println("hi2");
                return false;  // second invariant violated
            }
        }
        for (int i = m2; i <= r; i++) {
            if (data[i] > minP) {
                System.out.println("hi3");
                return false;  // third invariant violated
            }
        }
        for (int i = m1; i <= m2; i++) {
            if (maxP < data[i] || data[i] < minP) {
                System.out.println("hi4");
                return false;  // fourth invariant violated
            }
        }

        /**********************************************************/
        return true;
    }

    // l,r inclusive -> partition data[l,r]
    public static int[] partition(int[] data, int l, int r) {

        /**********************************************************/
        /**** Kurzaufgabe 5.2: ****/

        // ensure that leftPivot is the bigger one
        if (data[l] < data[r]) {
            B5A1.swap(data, l, r);
        }

        int leftI = l + 1; // index of all elements greater than leftPivot + 1
        int rightI = r - 1; // index of all elements smaller than rightPivot - 1

        int leftPivot = data[l], rightPivot = data[r];

        // iterate through sequence until we hit rightI
        for (int k = l + 1; k <= rightI; k++) {
            if (data[k] > leftPivot) {
                // if elements are greater than the left pivot, we can just put them
                // to the left, increase leftI and continue (with the switched element)
                B5A1.swap(data, k, leftI);
                leftI++;
            } else if (data[k] <= rightPivot) {
                // if elements are smaller than or equal to the right pivot
                while (data[rightI] < rightPivot && k < rightI) {
                    // skip all elements that are already at their right place
                    rightI--;
                }

                // bring the current element to its new position and continue with the
                // switched one
                B5A1.swap(data, k, rightI);
                rightI--;

                // put new k in front if necessary
                if (data[k] > leftPivot) {
                    B5A1.swap(data, k, leftI);
                    leftI++;
                }
            }
        }

        B5A1.swap(data, l, --leftI); // swap the last element that is greater than leftPivot with leftPivot
        B5A1.swap(data, r, ++rightI);

        assert isPartitioned(data, l, r, leftI, leftPivot, rightI, rightPivot);

        // return both pivots
        return new int[] { leftI, rightI };
        /**********************************************************/
    }

    // l,r inclusive -> sort data[l,r]
    public static void qsort(int[] data, int l, int r) {

        /**********************************************************/
        /**** Kurzaufgabe 5.2: ****/

        if (l < r) {
            int[] ms = partition(data, l, r);
            qsort(data, l, ms[0] - 1);
            qsort(data, ms[0] + 1, ms[1] - 1);
            qsort(data, ms[1] + 1, r);
        }

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
        /**** Kurzaufgabe 5.2: ****/

        // sort array
        Instant start = Instant.now();
        qsort(arr, 0, arr.length - 1);
        Instant finish = Instant.now();
        long time = Duration.between(start, finish).toMillis();
        System.out.println("Time: " + time);
        assert B5A1.isSorted(arr);
        if (arr.length < 20) {
            System.out.printf("After sorting:\n%s\n", Arrays.toString(arr));
        }

        /**********************************************************/
    }
}