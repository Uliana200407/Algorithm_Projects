import java.io.FileNotFoundException;

import static java.lang.Integer.parseInt;

public class ip22_andreieva_01 {
    public static void main(String[] args) throws FileNotFoundException {
        String inputFileName = args[0];
        int personNumber = Integer.parseInt ( args[1] ) - 1; //inputs user

        String matrixString = Functions.returnMatrixString (inputFileName);
        int rows  = matrixString.split ( "\n" ).length;;
        int columns = matrixString.split ( "\n" )[0].split ( " " ).length;

        int[][]matrix = new int[rows][columns-1];

        Functions.createMatrix ( matrixString, rows, columns-1, matrix );
        Functions.SortMatrix ( personNumber, rows, matrix );

        int inversions[] = new int[rows];

        int users[] = new int[rows];
        for (int i = 0; i < rows; i++) {
            users[i] = i;
        }

        Functions.FindInversions ( personNumber, rows, columns, matrix,inversions);

        Functions.WriteToFile ( personNumber, inversions, users );

    }
}
