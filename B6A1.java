import java.util.Arrays;
import java.util.Scanner;

public class B6A1 {
    public static int getMin(int[] data) {
        /**********************************************************/
        /****                Langaufgabe 6.1:                  ****/
        int min = data[0];

        for(int i = 1; i < data.length; i++){
            if(data[i] < min){
                min = data[i];
            }
        }

        /**********************************************************/
        return min;
    }

    public static int getMax(int[] data) {
        /**********************************************************/
        /****                Langaufgabe 6.1:                  ****/
        int max = data[0];

        for(int i = 1; i < data.length; i++){
            if(data[i] > max){
                max = data[i];
            }
        }

        /**********************************************************/
        return max;
    }

    public static int[] count(int[] data, int min, int max) {

        int C[] = new int[max - min + 1];

        /**********************************************************/
        /****                Langaufgabe 6.1:                  ****/

        for(int i = 0; i < data.length; i++){
            C[data[i]-min] = C[data[i]-min]+1;
        }

        /**********************************************************/
        return C;
    }

    public static int[] countingSort(int[] data) {
        int B[] = new int[data.length];
        /**********************************************************/
        /****                Langaufgabe 6.1:                  ****/

        int count = 0;
        int min = getMin(data);
        int max = getMax(data);
        int[] frequencies = count(data, min, max);

        for(int i = frequencies.length-2; i >= 0; i--){
            frequencies[i] = frequencies[i+1] + frequencies[i];
            count+=1;
        }

        System.out.println(Arrays.toString(frequencies));

        for(int i = data.length-1; i >= 0; i--){
            B[frequencies[data[i]-min]-1] = data[i];
            frequencies[data[i]-min] = frequencies[data[i]-min]-1;
            count+=1;
        }

        System.out.println("Original: "+(data.length+max-min));
        System.out.println("Actual: "+count);
        /**********************************************************/
        return B;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = input.nextInt();
        }
        input.close();
        System.out.print("Input Array: ");
        System.out.println(Arrays.toString(arr));
        int min = getMin(arr);
        System.out.printf("The minimum value: %d\n", min);
        int max = getMax(arr);
        System.out.printf("The minimum value: %d\n", max);
        int[] count = count(arr, min, max);
        System.out.print("Frequencies: ");
        System.out.println(Arrays.toString(count));
        int[] output = countingSort(arr);
        System.out.print("After sorting: ");
        System.out.println(Arrays.toString(output));
    }
}
