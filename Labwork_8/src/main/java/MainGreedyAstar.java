import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MainGreedyAstar {
    public static void main(String[] args) {
        City[] cities = Functions.createCities();
        double[][] distanceMatrix = Functions.calculateDistanceMatrix(cities);

        try (BufferedWriter greedyWriter = new BufferedWriter(new FileWriter("greedy.txt"));
             BufferedWriter astarWriter = new BufferedWriter(new FileWriter("astar.txt"))) {
            for (int i = 0; i < cities.length; i++) {
                for (int j = 0; j < cities.length; j++) {
                    if (i != j) {
                        List<City> shortestPathGreedy = Functions.greedySearch(distanceMatrix, cities, i, j);
                        double shortestDistanceGreedy = Functions.calculatePathDistance(shortestPathGreedy, distanceMatrix, cities);

                        greedyWriter.write(cities[i].getName() + "-" + cities[j].getName() + " (Жадібний пошук) Відстань: " + String.format("%.2f", shortestDistanceGreedy) + " км Маршрут: " + Functions.formatPath(shortestPathGreedy));
                        greedyWriter.newLine();
                        System.out.println(cities[i].getName() + "-" + cities[j].getName() + " (Жадібний пошук) Відстань: " + String.format("%.2f", shortestDistanceGreedy) + " км Маршрут: " + Functions.formatPath(shortestPathGreedy));                        List<City> shortestPathAStar = Functions.aStarSearch(distanceMatrix, cities, i, j);
                        double shortestDistanceAStar = Functions.calculatePathDistance(shortestPathAStar, distanceMatrix, cities);

                        astarWriter.write(cities[i].getName() + "-" + cities[j].getName() + " (Пошук A*) Відстань: " + String.format("%.2f", shortestDistanceAStar) + " км Маршрут: " + Functions.formatPath(shortestPathAStar));
                        astarWriter.newLine();
                        System.out.println(cities[i].getName() + "-" + cities[j].getName() + " (Пошук A*) Відстань: " + String.format("%.2f", shortestDistanceAStar) + " км Маршрут: " + Functions.formatPath(shortestPathAStar));

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
