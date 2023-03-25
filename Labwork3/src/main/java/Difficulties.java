import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class Difficulties {
    public static void GenerateDescendingList(ArrayList < Integer > list) {
        for (int i = list.size (); i > 0; i--) {
            list.set ( list.size () - i, i );
        }
    }

    private static void GenerateAscendingList(ArrayList < Integer > list) {
        for (int i = 0; i < list.size (); i++) {
            list.set ( i, i );
        }
    }

    private static void GenerateRandomList(ArrayList < Integer > list) {
//        for (int i = 0; i < list.size (); i++) {
//            list.set ( i, (int) (Math.random () * 1000) + 1 );
        for (int i=1; i<list.size (); i++){
            list.set ( i, i );
        };
        Collections.shuffle(list);
    }


    public static void main(String[] args) {
        int listSize = 100;
        ArrayList < Integer > list = new ArrayList <Integer> ( Collections.nCopies ( listSize,0 ) );
        System.out.println ("-----RANDOM ARRAY-----");
        GenerateRandomList(list);
        ArrayList<Integer> list2 = new ArrayList<> (list);
        int [] listInt = list.stream ().mapToInt ( i -> i ).toArray ();
        QuickSort sorter = new QuickSort();

        sorter.quickSort (list, 0, list.size () - 1);
        int comparison1 = sorter.comparisons;

        int comparisons2 = QuickSortMedian.quickSort ( list2,0,list.size () - 1 );
        QuickSortThree sorter2 = new QuickSortThree();
        sorter2.sort ( listInt );
        int comparisons3 = sorter2.comparisons;



        System.out.print ("Comparisons counter of simple Quick sort: " + comparison1 +"\n" );
        System.out.print ("Comparisons counter of median Quick Sort: " + comparisons2 +"\n" );
        System.out.print ("Comparisons counter of 3Pivot Quick Sort: " + comparisons3 +"\n" );

        ArrayList < Integer > listAsc = new ArrayList <Integer> ( Collections.nCopies ( listSize,0 ) );
        System.out.println ("-----ASCENDING ARRAY-----");
        GenerateAscendingList ( listAsc );
        ArrayList<Integer> list2Asc = new ArrayList<> (listAsc);
        int [] listIntAsc = listAsc.stream ().mapToInt ( i -> i ).toArray ();
        QuickSort sorterAsc = new QuickSort();

        sorterAsc.quickSort (listAsc, 0, listAsc.size () - 1);
        int comparison1Asc = sorterAsc.comparisons;

        int comparisons2Asc = QuickSortMedian.quickSort ( list2Asc,0,listAsc.size () - 1 );
        QuickSortThree sorter2Asc = new QuickSortThree();
        sorter2Asc.sort ( listIntAsc );
        int comparisons3Asc = sorter2Asc.comparisons;

        System.out.print ("Comparisons counter of simple Quick sort: " + comparison1Asc +"\n" );
        System.out.print ("Comparisons counter of median Quick Sort: " + comparisons2Asc +"\n" );
        System.out.print ("Comparisons counter of 3Pivot Quick Sort: " + comparisons3Asc +"\n" );

        ArrayList < Integer > listDsc = new ArrayList <Integer> ( Collections.nCopies ( listSize,0 ) );
        System.out.println ("-----DESCENDING ARRAY-----");
        GenerateDescendingList ( listDsc );
        ArrayList<Integer> list2Dsc = new ArrayList<> (listDsc);
        int [] listIntDsc = listDsc.stream ().mapToInt ( i -> i ).toArray ();
        QuickSort sorterDsc = new QuickSort();

        sorterDsc.quickSort (listDsc, 0, listDsc.size () - 1);
        int comparison1Dsc = sorterDsc.comparisons;

        int comparisons2Dsc = QuickSortMedian.quickSort ( list2Dsc,0,listDsc.size () - 1 );
        QuickSortThree sorter2Dsc = new QuickSortThree();
        sorter2Dsc.sort ( listIntDsc );
        int comparisons3Dsc = sorter2Dsc.comparisons;

        System.out.print ("Comparisons counter of simple Quick sort: " + comparison1Dsc +"\n" );
        System.out.print ("Comparisons counter of median Quick Sort: " + comparisons2Dsc +"\n" );
        System.out.print ("Comparisons counter of 3Pivot Quick Sort: " + comparisons3Dsc +"\n" );

    }



}
