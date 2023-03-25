import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class QuickSortThree extends Sort {
    public void sort(int[] arrayToSort) {
        quickSort(arrayToSort, 0, arrayToSort.length - 1);
    }

    private void quickSort(int[] array, int lowIndex, int highIndex) {
        if (lowIndex >= highIndex) {
            return;
        }

        int[] pivots = partition(array, lowIndex, highIndex);
        comparisons+=3; // increment counter by 3 for the comparisons in above line
        if (pivots != null) {
            quickSort(array, lowIndex, pivots[0] - 1);
            quickSort(array, pivots[0] + 1, pivots[1] - 1);
            quickSort(array, pivots[1] + 1, pivots[2] - 1);
            quickSort(array, pivots[2] + 1, highIndex);
        }
    }

    private int[] partition (int[] inArray, int lowIndex, int highIndex) {
        int aPointer = lowIndex + 2, bPointer = lowIndex + 2;
        int cPointer = highIndex - 1, dPointer = highIndex - 1;

        int lowPivot, middlePivot, highPivot;
        if (divisible(inArray, lowIndex, highIndex)) {

            // знаходимо pivot-и
            int[] array = new int[inArray.length];
            List<Integer> differentNumbers = new LinkedList<>();
            List<Integer> differentNumbersIndexes = new LinkedList<>();

            for (int i = lowIndex; i < highIndex; i++) {
               /// comparisons++;


                if (!differentNumbers.contains(inArray[i])) {
                    differentNumbers.add(inArray[i]);
                    differentNumbersIndexes.add(i);
                }

                if (differentNumbers.size() == 3) {
                    int arrayCounter = lowIndex + 2;
                    for (int j = lowIndex + 2; j <= highIndex; j++) {
                        comparisons++;
                        if (!differentNumbersIndexes.contains(j)) {
                            array[arrayCounter] = inArray[j];
                            arrayCounter++;
                        }
                    }
                    break;
                }

            }
            pivotSort(differentNumbers, differentNumbersIndexes, 0, differentNumbers.size() - 1);
            array[lowIndex] = differentNumbers.get(0);
            array[lowIndex + 1] = differentNumbers.get(1);
            array[highIndex] = differentNumbers.get(2);

            for (int i = lowIndex; i < highIndex + 1; i++) {
                //comparisons++;

                inArray[i] = array[i];
            }

            lowPivot = inArray[lowIndex];
            middlePivot = inArray[lowIndex + 1];
            highPivot = inArray[highIndex];

            // partition algorithm
            while (bPointer <= cPointer) {
                //comparisons++;

                while (inArray[bPointer] < middlePivot && bPointer <= cPointer) {
                    //comparisons++;

                    if (inArray[bPointer] < lowPivot) {
                        //comparisons++;
                        swap(inArray, aPointer, bPointer);
                        aPointer++;
                    }
                    bPointer++;
                    // comparisons++;

                }
                while (inArray[cPointer] > middlePivot && bPointer <= cPointer) {


                    if (inArray[cPointer] > highPivot) {
                        swap(inArray, cPointer, dPointer);
                        dPointer--;
                    }
                    cPointer--;

                }

                if (bPointer <= cPointer) {
                    if (inArray[bPointer] > highPivot) {


                        if (inArray[cPointer] < lowPivot) {
                            swap(inArray, bPointer, aPointer);
                            swap(inArray, aPointer, cPointer);
                            aPointer++;

                        } else {

                            swap(inArray, bPointer, cPointer);
                        }
                        swap(inArray, cPointer, dPointer);
                        bPointer++;
                        cPointer--;
                        dPointer--;
                        //comparisons++;
                    } else {
                        if (inArray[cPointer] < lowPivot) {
                            swap(inArray, bPointer, aPointer);
                            swap(inArray, aPointer, cPointer);
                            aPointer++;

                        } else {
                            swap(inArray, bPointer, cPointer);
                        }
                        bPointer++;
                        cPointer--;
                    }
                }
            }
            aPointer--;
            bPointer--;
            dPointer++;

            swap(inArray, lowIndex + 1, aPointer);
            swap(inArray, aPointer, bPointer);
            int newMiddlePivotPosition = bPointer;
            aPointer--;
            swap(inArray, lowIndex, aPointer);
            int newLowPivotPosition = aPointer;
            swap(inArray, highIndex, dPointer);
            int newHighPivotPosition = dPointer;

            return new int[] {newLowPivotPosition, newMiddlePivotPosition, newHighPivotPosition};

        } else {
            insertionSort(inArray, lowIndex, highIndex);
        }
        return null;
    }

    static private void insertionSort(int[] array, int lowIndex, int highIndex) {
        for (int i = lowIndex + 1; i <= highIndex; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= lowIndex && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            array[j + 1] = key;
        }
    }


    static private void pivotSort(List<Integer>pivots, List<Integer> pivotsIndexes, int lowIndex, int highIndex) {
        for (int i = lowIndex; i < highIndex; i++) {
            for (int j = lowIndex; j < highIndex; j++) {
                if (pivots.get(j) > pivots.get(j + 1)) {
                    Collections.swap(pivots, j, j + 1);
                    Collections.swap(pivotsIndexes, j, j + 1);
                }
            }
        }
    }

    // перевірка на наявність в масиві 3 різних чисел
    static boolean divisible(int[] array, int lowIndex, int highIndex) {
        if (highIndex - lowIndex < 3) {
            return false;
        } else {
            List<Integer> differentNumbers= new LinkedList<>();
            for (int i = lowIndex; i < highIndex + 1; i++) {
                if (!differentNumbers.contains(array[i])) {
                    differentNumbers.add(array[i]);
                }
                if (differentNumbers.size() >= 3) {
                    return true;
                }
            }
        }
        return false;
    }
}