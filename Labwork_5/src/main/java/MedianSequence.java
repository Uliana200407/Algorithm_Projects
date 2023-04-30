import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MedianSequence {
    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        try {
            List<Integer> array = readInputFile(inputFile);
            List<Integer> subArray = new ArrayList<>();

            try (FileWriter writer = new FileWriter(outputFile)) {
                for (int i = 0; i < array.size(); i++) {
                    subArray.add(array.get(i));
                    List<Integer> sortedSubArray = insertionSort(new ArrayList<>(subArray));
                    int n = sortedSubArray.size();

                    if (n % 2 == 1) {
                        // Непарна кількість елементів - повертаємо одну медіану
                        writer.write(sortedSubArray.get(n / 2) + "\n");
                    } else {
                        // Парна кількість елементів - повертаємо дві медіани через пробіл
                        writer.write(sortedSubArray.get(n / 2 - 1) + " " + sortedSubArray.get(n / 2) + "\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> insertionSort(List<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            int key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j) > key) {
                list.set(j + 1, list.get(j));
                j = j - 1;
            }

            list.set(j + 1, key);
        }

        return list;
    }

    private static List<Integer> readInputFile(String inputFile) throws IOException {
        List<Integer> array = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int n = Integer.parseInt(reader.readLine());
            for (int i = 0; i < n; i++) {
                array.add(Integer.parseInt(reader.readLine()));
            }
        }

        return array;
    }
}
