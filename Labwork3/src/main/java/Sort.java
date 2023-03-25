public abstract class Sort {
        public int comparisons = 0;
        public abstract void sort(int[] arrayToSort);
        static void swap(int[] array, int a, int b) {
            int temp = array[a];
            array[a] = array[b];
            array[b] = temp;
        }
        public int partition(int[] array, int lowIndex, int highIndex, int pivot) {
            int leftPointer = lowIndex;
            int rightPointer = highIndex - 1;
            while (leftPointer < rightPointer) {
                while (array[leftPointer] <= pivot && leftPointer < rightPointer) {
                    //comparisons++;
                    leftPointer++;
                }
                while (array[rightPointer] >= pivot && leftPointer < rightPointer) {
                    //comparisons++;
                    rightPointer--;
                }
                swap(array, leftPointer, rightPointer);
            }
            if (array[leftPointer] > array[highIndex]) {
                swap(array, leftPointer, highIndex);
            } else {
                leftPointer = highIndex;
            }
            //comparisons++;
            return leftPointer;
        }
        public int getComparisons() {
            return comparisons;
        }

}
