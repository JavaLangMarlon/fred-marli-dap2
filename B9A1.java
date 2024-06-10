import java.util.Scanner;
import java.util.Arrays;

class B9A1 {

	// Initialisierung fuer einen leeren Baum
	/**********************************************************/
	/**** Kurzaufgabe 9.1: ****/

	// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

	/**********************************************************/

	public B9A1() {}

	public B9A1(int[] array) {
		/**********************************************************/
		/**** Kurzaufgabe 9.1: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
	}

	private boolean isEmpty() {
		/**********************************************************/
		/**** Kurzaufgabe 9.1: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
		return false;
	}

    // Add value to the tree
	public void add(int v) {
		
		/**********************************************************/
		/**** Kurzaufgabe 9.1: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/

	}

	// Links-Rotation von this
	private void rotateLeft() {
		/**********************************************************/
		/**** Kurzaufgabe 9.1: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
	}

	// Rechts-Rotation von this
	private void rotateRight() {
		/**********************************************************/
		/**** Kurzaufgabe 9.1: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
	}

	// Ueberpruefung ob AVL-Baum Eigenschaft erfuellt wird
	private boolean isBalanced() {
		/**********************************************************/
		/**** Kurzaufgabe 9.1: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

		/**********************************************************/
		return false;
	}

	// In-Order Traversierung
	// linkes Kind -> Wurzel -> rechtes Kind
	public void inOrder() {
		/**********************************************************/
		/**** Kurzaufgabe 9.1: ****/

		// Ersetzen Sie diese Kommentarzeile durch Ihren Code!

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
		B9A1 bst = new B9A1(arr);
		System.out.println("Hoehe: " + bst.height);
		System.out.println("In-Order Traversierung:");
		bst.inOrder();
		System.out.println();
	}
}

