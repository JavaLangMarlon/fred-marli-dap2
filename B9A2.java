import java.util.Scanner;
import java.util.Arrays;

class B9A2 {

	// Initialisierung fuer einen leeren Baum
	
	/**********************************************************/
	/**** Kurzaufgabe 9.2: ****/

	// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

	/**********************************************************/

	private static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	public B9A2() {
	}

	public B9A2(int[] array) {
		/**********************************************************/
		/**** Kurzaufgabe 9.2: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
	}

	private boolean isEmpty() {
		/**********************************************************/
		/**** Kurzaufgabe 9.2: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
		return false;
	}

	public void add(int l, int r) {
		/**********************************************************/
		/**** Kurzaufgabe 9.2: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
	}

	// Links-Rotation von this
	private void rotateLeft() {
		/**********************************************************/
		/**** Kurzaufgabe 9.2: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
	}

	// Rechts-Rotation von this
	private void rotateRight() {
		/**********************************************************/
		/**** Kurzaufgabe 9.2: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
	}

	// Ueberpruefung ob AVL-Baum Eigenschaft erfuellt wird
	private boolean isBalanced() {
		/**********************************************************/
		/**** Kurzaufgabe 9.2: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
		return false;
	}

	// In-Order Traversierung
	// linkes Kind -> Wurzel -> rechtes Kind
	public void inOrder() {
		/**********************************************************/
		/**** Kurzaufgabe 9.2: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
	}

	private boolean search(int l, int r) {
		/**********************************************************/
		/**** Kurzaufgabe 9.2: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
		return false;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		// Initialize the scanner and read the amount of expected integers
		int n = input.nextInt();
		assert n > 0 : "Error: length of the Input Array < 1";
		int[] arr = new int[2 * n];

		for (int i = 0; i < 2*n; i++) {
			arr[i] = input.nextInt();
		}
		System.out.print("Input Array: ");
		System.out.println(Arrays.toString(arr));
		B9A2 bst = new B9A2(arr);
		System.out.println("In-Order Traversierung:");
		bst.inOrder();
		System.out.println();

		String command;
        while (true) {
            System.out.print("Enter command (s to search, q to quit): ");
            command = input.next();
            if (command.equals("s")) {
                int l = input.nextInt();
                int r = input.nextInt();
				if (! bst.search(l, r))
					System.out.println("No interval overlaps!");
            } else if (command.equals("q")) {
                break;
            } else {
                System.out.println("Invalid command. Please try again.");
            }
        }
        input.close();
	}
}
