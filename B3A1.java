import java.util.Arrays;
import java.util.Scanner;

class B3A1 {
    private static int subsetCounter;

    public static int removeDuplicates(int[] data) {
        /**********************************************************/
        /**** Kurzaufgabe 3.1: ****/
        //the integer i (n´ <= n) counts the number of distinct array elements.
        int i=0;
        //sort the array as instructed in the task
        Arrays.sort(data);
        for(int j=1;j<data.length;j++){
            //if the current element is different from the previous distinct element then
            //
            if(data[i]!=data[j]){
                i++;
                data[i]=data[j];
            }
        }
        return i+1;
        // Ersetzen Sie diese Kommentarzeile durch Ihren Code!
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
        System.out.print("Before removing duplicates: ");
        System.out.println(Arrays.toString(arr));
        int distinct = removeDuplicates(arr);
        System.out.print("After removing duplicates: ");
        System.out.println(Arrays.toString(arr));
        
        /**********************************************************/
        /**** Kurzaufgabe 3.1: ****/
        if(distinct < k){
            k = distinct;
        }
        getSubsets(arr, distinct, k);
        System.out.println("Number of subsets: "+ subsetCounter);
        /**********************************************************/

    }
    private static void getSubsets(int[] supSet,int n, int k, int idx, int[] current) {
        // 
        if (k == 0) {
            subsetCounter++;
            System.out.println(Arrays.toString(current));
            return;
        }
        //The index from the superset reaches the end of the array
        if (idx == n) return;

        current[current.length - k] = supSet[idx];
        getSubsets(supSet,n, k - 1, idx + 1, current);
        getSubsets(supSet,n,k, idx + 1, current);
    }

    public static void getSubsets(int[] supSet, int n, int k) {
        getSubsets(supSet,n, k, 0, new int[k]);

    }





}
