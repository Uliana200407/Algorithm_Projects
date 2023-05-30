import java.util.List;

public class InsertionSort {
    public static void insertionSort(List<Node> nodeList) {
        int n = nodeList.size();

        for (int i = 1; i < n; i++) {
            Node key = nodeList.get(i);
            int j = i - 1;

            while (j >= 0 && nodeList.get(j).getTotalCost() > key.getTotalCost()) {
                nodeList.set(j + 1, nodeList.get(j));
                j--;
            }

            nodeList.set(j + 1, key);
        }
    }
}