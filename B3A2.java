import java.util.Arrays;
import java.util.Scanner;

class B3A2 {
    /**********************************************************/
    /**** Kurzaufgabe 3.2: ****/

    public static int[] find(int[] arr, int k){
        if(arr.length == 1 && k == 1){
            return arr;
        }

        int nMinusOneFak = arr.length-1;
        for(int j = arr.length-2; j > 1; j--){
            nMinusOneFak *= j;
        }
        int i = (k-1)/nMinusOneFak;
        int newK = k-i*nMinusOneFak;

        int[] arr_new = new int[arr.length-1];
        for(int j = 0, m = 0; j < arr.length; j++){
            if(j != i){
                arr_new[m]=arr[j];
                m++;
            }
        }


        int[] tmp = new int[]{arr[i]};
        int[] nextArr = find(arr_new, newK);


        int[] result = Arrays.copyOf(tmp, 1 + nextArr.length);
        System.arraycopy(nextArr, 0, result, 1, nextArr.length);

        return result;
    }

    /**********************************************************/

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();

        Arrays.sort(arr);
        System.err.println("Sorted input:");
        System.out.println(Arrays.toString(arr));
        System.err.println("The " + k + "-smallest permutation is:");
        /**********************************************************/
        /**** Kurzaufgabe 3.2: ****/
        int[] smallestPermutation = find(arr, k);

        System.out.println(Arrays.toString(smallestPermutation));

        /**********************************************************/
    }
}
