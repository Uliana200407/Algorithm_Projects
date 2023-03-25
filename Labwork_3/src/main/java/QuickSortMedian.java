import java.util.ArrayList;
import java.io.*;


public class QuickSortMedian {
    static int comparisonCount = 0;


    static void swap(ArrayList<Integer> arr, int i, int j)
    {
        int temp = arr.get ( i );
        arr.set ( i, arr.get ( j ) );
        arr.set ( j, temp );
    }

    static int median(ArrayList<Integer> arr, int a, int b, int c){
        if(arr.get ( a ) > arr.get ( b )){
            if(arr.get ( b ) > arr.get ( c ))
                return b;
            else if(arr.get ( a ) > arr.get ( c ))
                return c;
            else
                return a;
        }
        else{
            if(arr.get ( b ) < arr.get ( c ))
                return b;
            else if(arr.get ( a ) < arr.get ( c ))
                return c;
            else
                return a;
        }
    }

    static int partition(ArrayList<Integer> arr, int low, int high)
    {

        int medianIndex = median(arr, low, (low + high) / 2, high);
        int pivot = arr.get ( medianIndex );
        swap(arr, medianIndex, high);

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            comparisonCount++;
            if (arr.get ( j ) < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }
    public static int quickSort(ArrayList<Integer> arr, int low, int high)
    {
        if (low < high) {
            if (high - low > 2) { // compare only if size > 3
                int pi = partition(arr, low, high);
                quickSort(arr, low, pi - 1);
                quickSort(arr, pi + 1, high);
            } else {
// sorting without partitioning
                for (int i = low; i < high; i++) {
                    for (int j = i + 1; j <= high; j++) {
                        comparisonCount++;
                        if (arr.get ( i ) > arr.get ( j )) {
                            swap(arr, i, j);
                        }
                    }
                }
            }
        }
        return comparisonCount;
    }
}
