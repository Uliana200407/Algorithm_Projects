import java.io.*;
import java.util.*;

public class BinarySearchTreeConverter {
    public static void convertToBST(Node root) {
        List<Integer> values = new ArrayList<>();
        inOrderTraversal(root, values);
        quickSort(values, 0, values.size() - 1);

        Iterator<Integer> iterator = values.iterator();
        replaceNodeValues(root, iterator);
    }

    private static void quickSort(List<Integer> values, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(values, low, high);
            quickSort(values, low, pivotIndex - 1);
            quickSort(values, pivotIndex + 1, high);
        }
    }

    private static int partition(List<Integer> values, int low, int high) {
        int pivot = values.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (values.get(j) < pivot) {
                i++;
                swap(values, i, j);
            }
        }

        swap(values, i + 1, high);
        return i + 1;
    }

    private static void swap(List<Integer> values, int i, int j) {
        int temp = values.get(i);
        values.set(i, values.get(j));
        values.set(j, temp);
    }

    private static void inOrderTraversal(Node root, List<Integer> values) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left, values);
        values.add(root.value);
        inOrderTraversal(root.right, values);
    }

    private static void replaceNodeValues(Node root, Iterator<Integer> iterator) {
        if (root == null) {
            return;
        }

        replaceNodeValues(root.left, iterator);
        root.value = iterator.next();
        replaceNodeValues(root.right, iterator);
    }

    public static void printTree(Node root) {
        printTreeRecursive(root, 0);
    }

    private static void printTreeRecursive(Node current, int level) {
        if (current != null) {
            printTreeRecursive(current.right, level + 1);

            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }

            System.out.println(current.value);

            printTreeRecursive(current.left, level + 1);
        }
    }

    public static List<List<Integer>> findPaths(Node root, int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathsRecursive(root, targetSum, currentPath, paths);
        return paths;
    }

    private static void findPathsRecursive(Node node, int targetSum, List<Integer> currentPath, List<List<Integer>> paths) {
        if (node == null)
            return;

        currentPath.add(node.value);

        int currentSum = 0;
        for (int i = currentPath.size() - 1; i >= 0; i--) {
            currentSum += currentPath.get(i);
            if (currentSum == targetSum)
                paths.add(new ArrayList<>(currentPath.subList(i, currentPath.size())));
        }

        findPathsRecursive(node.left, targetSum, currentPath, paths);
        findPathsRecursive(node.right, targetSum, currentPath, paths);

        currentPath.remove(currentPath.size() - 1);
    }
}

class Main{
    static String readFile(String fileName){
        String fileContent = "";

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                fileContent = scanner.nextLine();
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return fileContent;
    }

    static int[] convertToInts(String fileContent){
        String[] numbersString = fileContent.split(" ");
        int[] numbers = new int[numbersString.length];
        for (int i = 0; i < numbersString.length; i++) {
            numbers[i] = Integer.parseInt(numbersString[i]);
        }

        return numbers;
    }

    static void writePathsToFile(List<List<Integer>> paths){
        String fileName = "output.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (List<Integer> row : paths) {
                for (int i = 0; i < row.size(); i++) {
                    writer.write(Integer.toString(row.get(i)));
                    if (i != row.size() - 1) {
                        writer.write(" ");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = args[0];
        int targetSum = Integer.parseInt(args[1]);

        String fileContent = readFile(fileName);
        System.out.println("Input.txt: " + fileContent);

        int[] numbers = convertToInts(fileContent);

        Node root = BinaryTreeConverter.convertToBinaryTree (numbers);

        System.out.println("Binary tree from an array:");
        BinarySearchTreeConverter.printTree(root);

        BinarySearchTreeConverter.convertToBST(root);

        System.out.println("\nConverted binary search tree:");
        BinarySearchTreeConverter.printTree(root);

        List<List<Integer>> paths = BinarySearchTreeConverter.findPaths(root, targetSum);

        System.out.println("Paths with sum " + targetSum + ":");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }

        writePathsToFile(paths);
    }
}