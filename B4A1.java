import java.util.Arrays;
import java.util.Scanner;

class B4A1 {
    // Hilfsfunktion zum Vertauschen zweier Werte
    public static void swap(int[] data, int i, int j) {
        int h = data[i];
        data[i] = data[j];
        data[j] = h;
    }

    public static void maxHeapifyUp(int[] data, int i) {
        /**********************************************************/
        /****                Langaufgabe 4.1:                  ****/

        // we use the formula from the heaps deck, slide 7, however, we need to adjust it a bit because the array
        // here starts with 0
        // (i+1)/2 - 1 = (i-1)/2
        int parentIndex = (i-1)/2;

        // check if parentIndex is still in array and if its value is smaller
        if (parentIndex >= 0 && data[parentIndex] < data[i]) {
            swap(data, parentIndex, i);

            // we need to heapify one level up, because it could be that A[i] was bigger than its grand parent
            maxHeapifyUp(data, parentIndex);
        }

        // as we climb up the tree and we only have one recursive call in the algorithm and no loop, it is obvious
        // that the runtime must be O(tree height) = O(log n)

        /**********************************************************/
    }

    public static void maxHeapifyDown(int[] data, int n) {
        /**********************************************************/
        /****                Langaufgabe 4.1:                  ****/
        int i = 0;
        // we do the same as in the heaps deck, slide 18, but the other way around
        while (true) {
            // analogous to lecture but with index adjustments
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n - 1 && data[left] > data[i]) {
                swap(data, i, left);
                i = left;
            } else if (right < n - 1 && data[right] > data[i]) {
                swap(data, i, right);
                i = right;
            } else {
                break;
            }
        }
        /**********************************************************/
    }

    public static void buildMaxHeap(int[] data) {
        /**********************************************************/
        /****                Langaufgabe 4.1:                  ****/

        // analogous to the lecture, however, we need to iterate in a reversed order, because the maxHeapifyUp algorithm
        // assumes that the right of the index was already heapified, not the left
        for (int i = 1; i < data.length; i++) {
            maxHeapifyUp(data, i);
        }

        // as maxHeapifyUp has a runtime of O(log n) (see above), this algorithm has a runtime of O(n log n)


        /**********************************************************/
    }

    public static int extractMax(int[] data, int n) {
        /**********************************************************/
        /****                Langaufgabe 4.1:                  ****/

        int i = 0;
        swap(data, i, n - 1);

        // utilize the heapifyDown function which is essential for this endeavor
        maxHeapifyDown(data, n);

        return data[n - 1];

        /**********************************************************/
    }

    public static int heapSelect(int[] data, int k) {
        /**********************************************************/
        /****                Langaufgabe 4.1:                  ****/
        // first build a maxheap, otherwise extracting will not work
        buildMaxHeap(data);

        // extract all maximum values before and our wanted value
        for (int i = 0; i < k; i++) {
            extractMax(data, data.length - i);
        }
        // we know where the maximum values will be stored, so we can output this
        return data[data.length - k];

        /**********************************************************/
    }

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();
        System.out.print("Input Array: ");
        System.out.println(Arrays.toString(arr));

        int v = heapSelect(arr, k);
        System.out.println("The " + k + "-biggest element is " + v);

    }
}