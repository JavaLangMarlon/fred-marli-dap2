import java.util.*;


class B10A1 {

    public static List<Activity> activitySelection(Activity[] arr) {
        LinkedList<Activity> list = new LinkedList<Activity>();

        /**********************************************************/
        /**** Langaufgabe 10.1: ****/

        // O(n log n), see docs
        Arrays.sort(arr, Comparator.comparingInt(o -> o.e));

        // we already asserted n > 1
        list.add(arr[0]);
        // O(n)
        for (int i = 1; i < arr.length; i++) {
            // intervals are half open
            if (list.getLast().e <= arr[i].s) {
                list.add(arr[i]);
            }
        }


        /**********************************************************/

        return list;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Initialize the scanner and read the amount of expected integers
        int n = input.nextInt();
        assert n > 0 : "Error: length of the Input Array < 1";
        Activity[] arr = new Activity[n];

        for (int i = 0; i < n; i++) {
            arr[i] = new Activity(input.nextInt(), input.nextInt());
        }

        List<Activity> list = activitySelection(arr);

        System.out.println("Maximale Anzahl: " + list.size());
        System.out.println("Zeitplan: ");
        for (Activity act : list) {
            System.out.print(act + " ");
        }
        System.out.println("");
        input.close();
    }
}

class Activity {
    int s;
    int e;

    public Activity(int s, int e) {
        this.s = s;
        this.e = e;
    }

    @Override
    public String toString() {
        return "(" + s + ", " + e + ")";
    }
}