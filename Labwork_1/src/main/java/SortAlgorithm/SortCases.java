package SortAlgorithm;

public class SortCases {
    public static void main(String[] args) {
        int[] list = new int[10];

//        GenerateRandomList ( list );
//        GenerateAscendingList ( list );
        GenerateDescendingList(list);
        System.out.print ( "Before sorting:" );
        OutputList ( list );
//        System.out.print ( "\nBubble sort" );
//        list = bubbleSort ( list );
//        System.out.print ( "\nBubble sort modified");
//        System.out.print ( "\nInsertion sort");
        list =  insertionSort( list );
//        System.out.print ( "\nBubble sort modified" );
//        list = bubbleSortModified ( list );

        System.out.print ( "After sorting:" );
        OutputList ( list );
    }
    
    private static void GenerateDescendingList(int[]list){
        for(int i = list.length;i > 0;i--){
            list[list.length-i] = i;
        }
    }
    private static void GenerateAscendingList(int[] list) {
        for (int i = 0; i< list.length; i++){
            list[i] = i+1;
        }
    }


    private static void GenerateRandomList(int[] list) {
        for (int i = 0; i <list.length; i++) {
            list[i] = (int) (Math.random () * 100) + 1;
        }
    }

    private static void OutputList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            if (i != list.length - 1) {
                System.out.print ( list[i] + ", " );
            } else {
                System.out.print ( list[i] );
            }
        }

    }

    private static int[] bubbleSort(int[] list) {
        long comparisonsCounter = 0;
        long swapCounter = 0;
        int arraySize = list.length;
        for (int i = 0; i < arraySize - 1; i++){
            for (int j = 0; j < arraySize - i - 1; j++) {
                comparisonsCounter += 1;
                if (list[j] > list[j + 1]) {
                    int temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapCounter += 1;
                }
            }
        }
        System.out.print ("\nSwap counter: "+ swapCounter + "\nComparisons counter: " + comparisonsCounter +"\n" );
        return list;
    }
    private static int[] insertionSort(int[] list) {
        long comparisonsCounter = 0;
        long swapCounter = 0;
        int size = list.length;
        for (int step = 1; step < size; step++) {
            int key = list[step];
            int j = step - 1;
            while (j >= 0 && key < list[j]) {
                list[j + 1] = list[j];
                comparisonsCounter+=1;
                swapCounter += 1;
                --j;
            }
            list[j + 1] = key;
            comparisonsCounter+=1;

        }
        System.out.print ("\nSwap counter: "+ swapCounter + "\nComparisons counter: " + comparisonsCounter +"\n" );
        return list;
    }
    
    static int[] bubbleSortModified(int[] list) {
        boolean isSorted = true;
        long comparisonsCounter = 0;
        long swapCounter = 0;
        int j=0;
        while (isSorted && j < list.length - 1) {
            isSorted = false;
            for (int i = 0; i < list.length - j-1; i++) {
                comparisonsCounter+=1;
                if (list[i] > list[i + 1]) {
                    int temp = list[i];
                    list[i] = list[i + 1];
                    list[i + 1] = temp;
                    isSorted = true;
                    swapCounter += 1;
                }
            }
            j+=1;
        }
        System.out.print ("\nSwap counter: "+ swapCounter + "\nComparisons counter: " + comparisonsCounter +"\n" );
        return list;
    }
}


