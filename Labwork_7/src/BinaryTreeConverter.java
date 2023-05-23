
public class BinaryTreeConverter {
    private static int index;

    public static Node convertToBinaryTree(int[] arr) {
        index = 0;
        return buildBinaryTree (arr);
    }

    private static Node buildBinaryTree(int[] arr) {
        if (index >= arr.length || arr[index] == 0) {
            index++;
            return null;
        }

        Node node = new Node(arr[index]);
        index++;

        node.left = buildBinaryTree (arr);
        node.right = buildBinaryTree (arr);

        return node;
    }

}


