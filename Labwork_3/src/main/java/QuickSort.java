
import java.util.ArrayList;

public class QuickSort {
    public int comparisons = 0;

    static void swap(ArrayList<Integer> arr, int a, int b) {
        int temp = arr.get ( a );
        arr.set ( a, arr.get ( b ) );
        arr.set ( b, temp );
    }

    int partition(ArrayList<Integer> arr, int low, int high) {
        int pivot = arr.get ( high );
        int i = low - 1;

        for (int j = low; j <= high - 1; j++) {
            this.comparisons++;
            if (arr.get ( j ) <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    public void quickSort(ArrayList<Integer> arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}
