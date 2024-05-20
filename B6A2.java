import java.util.Scanner;
import java.util.Arrays;

class B6A2 {


    //this three methods are only here because my compiler is throwing errors when using the methods from B6A1
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

    public static void sortByByte(int[] input, int l, int r, int b) {
        /**********************************************************/
        /****                Langaufgabe 6.2:                  ****/
        int[] help = new int[r-l+1];

        //initializing the divisor
        int pow = (int) Math.pow(256, b);

        //creating a help-array with the calculated modulo values shown in the sheet
        for(int i = 0; i < r-l+1; i++){
            help[i] = ((input[l+i])/pow)%256;
        }

        //creating a frequency array for the modulo values initialized above
        int min = getMin(help);
        int[] frequencies = count(help, min, getMax(help));

        //calculating the positions depending on the frequency like shown in counting sort
        for(int i = frequencies.length-2; i >= 0; i--){
            frequencies[i] = frequencies[i+1] + frequencies[i];
        }

        //setting up the array from behind ;)
        for(int i = r-l; i >= 0 ; i--){
            //the position of the current value input[l+i] is now calculated by searching the frequencyvalue with the modulo value
            int freqPos = ((input[l+i])/pow)%256-min;
            help[frequencies[freqPos]-1] = input[l+i];
            //just like in counting sort we have to decrease the value by 1
            frequencies[freqPos] = frequencies[freqPos]-1;
        }

        //copying the sorted sequence in the original array
        for(int i = 0; i < r-l+1; i++){
            input[l+i] = help[i];
        }
        /**********************************************************/
    }

    public void radix(int[] data) {
        /**********************************************************/
        /****                Langaufgabe 6.2:                  ****/

        //calling sortByByte for every b from 0-3
        for(int i = 0; i < 4; i++){
            sortByByte(data, 0, data.length-1, i);
        }

        /**********************************************************/
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
        System.out.println("Finish: "+Arrays.toString(arr));
        B6A2 Sorter = new B6A2();
        Sorter.radix(arr);
        System.out.println("After Sorting: " + Arrays.toString(arr));
    }

}
