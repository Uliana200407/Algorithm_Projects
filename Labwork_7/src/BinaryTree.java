import java.util.ArrayList;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class BinaryTree {
    Node root;

    public BinaryTree() {
        this.root = null;
    }

    public void insert(int value) {
        this.root = insertRec(this.root, value);
    }

    private Node insertRec(Node current, int value) {
        if (value == 0) {
            return current;
        }
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = insertRec(current.left, value);
        } else if (value > current.value) {
            current.right = insertRec(current.right, value);
        }

        return current;
    }

    public void printInOrder() {
        printInOrderRecursive(this.root);
    }

    private void printInOrderRecursive(Node current) {
        if (current != null) {
            printInOrderRecursive(current.left);
            System.out.print(current.value + " ");
            printInOrderRecursive(current.right);
        }
    }

    private void findPathsRecursive(Node node, int targetSum, List<Integer> currentPath, List<List<Integer>> paths) {
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

    public List<List<Integer>> findPaths(int targetSum) {
        List<List<Integer>> paths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        findPathsRecursive(root, targetSum, currentPath, paths);
        return paths;
    }

    public void printPreOrder() {
        printPreOrderRecursive(this.root);
    }

    private void printPreOrderRecursive(Node current) {
        if (current != null) {
            System.out.print(current.value + " ");
            printPreOrderRecursive(current.left);
            printPreOrderRecursive(current.right);
        }
    }
}

class Main {
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

        BinaryTree tree = new BinaryTree();

        for (int number : numbers) {
            tree.insert(number);
        }

        System.out.println("BinaryTree in Inorder traversal: ");
        tree.printInOrder();
        System.out.println();

        System.out.println("BinaryTree Preorder traversal:");
        tree.printPreOrder();
        System.out.println();

        List<List<Integer>> paths = tree.findPaths(targetSum);

        System.out.println("Paths with sum " + targetSum + ":");
        for (List<Integer> path : paths) {
            System.out.println(path);
        }

        writePathsToFile(paths);
    }
}