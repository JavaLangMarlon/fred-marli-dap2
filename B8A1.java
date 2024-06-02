
import java.util.Scanner;
import java.util.Arrays;

class B8A1 {

    /**********************************************************/
    /**** Langaufgabe 8.1: ****/
    class Node {
        int key;
        Node left;
        Node right;
        //key equals data
        public Node(int data) {
            key = data;
            left = null;
            right = null;
        }
    }
    Node root;
    int height;
    /**********************************************************/

    public B8A1(int[] array) {
        /**********************************************************/
        /**** Langaufgabe 8.1: ****/
        root = null;
        for (int i = 0; i < array.length; i++) {
            add(array[i]);
        }
        height = getHeight(root);
        /**********************************************************/
    }
    
    private int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        int leftTreeHeight = getHeight(root.left);
        int rightTreeHeight = getHeight(root.right);
        return Math.max(leftTreeHeight, rightTreeHeight) + 1;

    }

    public void add(int v) {
        /**********************************************************/
        /**** Langaufgabe 8.1: ****/
        root = addRecursively(root, v);
        /**********************************************************/
    }
    //Helper function to add newly created Node with integer key recursively into BST
    private Node addRecursively(Node root, int v) {
        if(root == null){
            root = new Node(v);
            return root;
        }
        else if(v < root.key){
            root.left = addRecursively(root.left,  v);
        } else if (v > root.key ){
            root.right = addRecursively(root.right, v);
        }
        return root;
    }
    // In-Order Traversierung
    // linkes Kind -> Wurzel -> rechtes Kind
    public void inOrder() {
        /**********************************************************/
        /**** Longaufgabe 8.1: ****/
        inOrderRecursively(root);
        /**********************************************************/
    }

    private void inOrderRecursively(Node root) {
        if (root != null){
            inOrderRecursively(root.left);
            System.out.print(root.key+ " ");
            inOrderRecursively(root.right);
        }
    }

    // Pre-Order Traversierung
    // Wurzel -> linkes Kind -> rechtes Kind
    public void preOrder() {
        /**********************************************************/
        /**** Longaufgabe 8.1: ****/
        preOrderRecursively(root);
        /**********************************************************/
    }

    private void preOrderRecursively(Node root) {
        if (root != null){
            System.out.print(root.key+ " ");
            preOrderRecursively(root.left);
            preOrderRecursively(root.right);
        }
    }

    // Post-Order Traversierung
    // linkes Kind -> rechtes Kind -> Wurzel
    public void postOrder() {
        /**********************************************************/
        /**** Longaufgabe 8.1: ****/
        postOrderRecursively(root);
        /**********************************************************/
    }

    private void postOrderRecursively(Node root) {
        if (root != null){
            postOrderRecursively(root.left);
            postOrderRecursively(root.right);
            System.out.print(root.key+ " ");
        }
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

        B8A1 bst = new B8A1(arr);

        System.out.println("Hoehe des Baums: " + bst.height);

        System.out.println("In-Order Traversierung:");
        bst.inOrder();
        System.out.println();
        System.out.println("Pre-Order Traversierung:");
        bst.preOrder();
        System.out.println();
        System.out.println("Post-Order Traversierung:");
        bst.postOrder();
        System.out.println();
    }

}
