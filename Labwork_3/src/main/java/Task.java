import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Task {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
       File myFile = new File ( fileName );
        StringBuilder sb = new StringBuilder();
        try(FileReader reader = new FileReader(myFile)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char)c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String fileContent = sb.toString();
        System.out.println(fileContent);


        ArrayList <String> splittedArray2 = new ArrayList<>( Arrays.asList(fileContent.split("\n")));
        splittedArray2.remove(0);
        ArrayList<Integer> integerArrayList = new ArrayList<> ();
        for (String element: splittedArray2) {
            integerArrayList.add ( Integer.parseInt ( element ) );
        }
        //for quicksort
        ArrayList<Integer> array1 = new ArrayList<Integer> (integerArrayList);
        QuickSort sorter = new QuickSort();
        sorter.quickSort (array1, 0, array1.size () - 1);
        int comparison1 = sorter.comparisons;
        //for medianQuickSort

        ArrayList<Integer> array2 = new ArrayList<Integer> (integerArrayList);
        int comparisons2 = QuickSortMedian.quickSort ( array2,0,array2.size () - 1 );
        //for QuickSortThree
        int [] array3 = integerArrayList.stream ().mapToInt ( i -> i ).toArray ();

        QuickSortThree sorter2 = new QuickSortThree();
        sorter2.sort ( array3 );
        int comparisons3 = sorter2.comparisons;

        FileWriter myFile1 = new FileWriter ("ip22_andreieva_03_output.txt" );
        myFile1.write ( comparison1 + " " + comparisons2 + " " + comparisons3);
        myFile1.close ();
    }
}
