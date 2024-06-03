import java.util.Scanner;
import java.util.Arrays;

class B8A2 {

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node {
		int value;
		Node left, right, parent;
		boolean color;

		Node(int value) {
			this.value = value;
			this.color = RED; // Neue Knoten sind zunächst rot
		}
	}

	private Node root;

	public B8A2(int[] array) {
		for (int i = 0; i < array.length; i++) {
			this.add(array[i]);
		}
	}

	// Methode zum Hinzufügen eines Werts
	public void add(int value) {
		
		/**********************************************************/
		/**** Longaufgabe 8.2: ****/

		if (root == null) {
			root = new Node(value);
			root.color = BLACK;  // root is black
		} else {
			var newNode = new Node(value);
			insert(root, newNode);

			fixInsert(newNode);
		}

		System.out.printf("Fuege %d in den Rot-Schwarz-Baum ein.\n", value);

		/**********************************************************/
	}

	// Hilfsmethode zum Einfügen eines neuen Knotens
	private void insert(Node root, Node newNode) {
		/**********************************************************/
		/**** Longaufgabe 8.2: ****/

		if (newNode.value < root.value) {
			if (root.left == null) {
				root.left = newNode;
				newNode.parent = root;
			} else {
				insert(root.left, newNode);
			}
		} else if (newNode.value > root.value) {
			if (root.right == null) {
				root.right = newNode;
				newNode.parent = root;
			} else {
				insert(root.right, newNode);
			}
		}
		/**********************************************************/
	}

	// Balancierung nach dem Einfügen
	private void fixInsert(Node node) {
		/**********************************************************/
		/**** Longaufgabe 8.2: ****/

		// analogous to lecture only with necessary nil adjustments
		while (node.parent != null && node.parent.color) {
			if (node.parent == node.parent.parent.left) {
				Node y = node.parent.parent.right;
				if (y != null && y.color) {
					node.parent.color = BLACK;
					node.parent.parent.color = RED;
					y.color = BLACK;
					node = node.parent.parent;
				} else {
					if (node == node.parent.right) {
						node = node.parent;
						rotateLeft(node);
					}
					node.parent.color = BLACK;
					node.parent.parent.color = RED;
					rotateRight(node.parent.parent);
				}
			} else if (node.parent == node.parent.parent.right) {
				Node y = node.parent.parent.left;
				if (y != null && y.color) {
					node.parent.color = BLACK;
					node.parent.parent.color = RED;
					y.color = BLACK;
					node = node.parent.parent;
				} else {
					if (node == node.parent.left) {
						node = node.parent;
						rotateRight(node);
					}
					node.parent.color = BLACK;
					node.parent.parent.color = RED;
					rotateLeft(node.parent.parent);
				}
			}
		}
		root.color = BLACK;

		/**********************************************************/
	}

	// Links-Rotation
	private void rotateLeft(Node node) {
		/**********************************************************/
		/**** Longaufgabe 8.2: ****/

		System.out.printf("Fuehre Links-Rotation durch bei Knoten: %d\n", node.value);

		// left rotation
		if (node.right != null) {
			if (node.parent != null) {
				if (node.parent.left == node) {
					node.parent.left = node.right;
				} else {
					node.parent.right = node.right;
				}
			} else {
				root = node.right;
			}
			node.right.parent = node.parent;

			Node tmp = node.right.left;
			node.right.left = node;
			node.parent = node.right;
			node.right = tmp;
			if (tmp != null) {
				tmp.parent = node;
			}
		}

		/**********************************************************/
	}

	// Rechts-Rotation
	private void rotateRight(Node node) {
		/**********************************************************/
		/**** Longaufgabe 8.2: ****/

		System.out.printf("Fuehre Rechts-Rotation durch bei Knoten: %d\n", node.value);

		// right rotation
		if (node.left != null) {
			if (node.parent != null) {
				if (node.parent.left == node) {
					node.parent.left = node.left;
				} else {
					node.parent.right = node.left;
				}
			} else {
				root = node.left;
			}
			node.left.parent = node.parent;

			Node tmp = node.left.right;
			node.left.right = node;
			node.parent = node.left;
			node.left = tmp;
			if (tmp != null) {
				tmp.parent = node;
			}
		}

		/**********************************************************/
	}

	// In-Order Traversierung mit Ausgabe der Farben
	public void inOrderTraversal(Node node) {
		
		/**********************************************************/
		/**** Longaufgabe 8.2: ****/

		if (node == null) {
			return;
		}
		inOrderTraversal(node.left);
		System.out.printf("(%d, %s) ", node.value, node.color ? "rot" : "schwarz");
		inOrderTraversal(node.right);

		/**********************************************************/
	}

    // Gibt die Höhe des Baums zurück
	public int height(Node node) {
		/**********************************************************/
		/**** Longaufgabe 8.2: ****/

		// see exercise 8.1

		if (node == null) {
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right));

		/**********************************************************/
	}

	// Hauptmethode
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

		B8A2 tree = new B8A2(arr);

		System.out.println("Hoehe: " + tree.height(tree.root));
		System.out.print("In-Order Traversierung: ");
		tree.inOrderTraversal(tree.root);
	}
}
