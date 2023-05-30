import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Functions {
    public static City[] createCities(){
        City[] cities = new City[15];

        cities[0] = new City("Джакарта", 106.8456, -6.2088, 0);
        cities[1] = new City("Бандунг", 107.6191, -6.9175, 1);
        cities[2] = new City("Сурабая", 112.7521, -7.2575, 2);
        cities[3] = new City("Медан", 98.6722, 3.5952, 3);
        cities[4] = new City("Макасар", 119.4327, -5.1477, 4);
        cities[5] = new City("Палембанг", 102.2763, -3.7573, 5);
        cities[6] = new City("Йогякарта", 110.3695, -7.7956, 6);
        cities[7] = new City("Семаранг", 110.4710, -7.0249, 7);
        cities[8] = new City("Балікпапан", 116.8312, -1.2650, 8);
        cities[9] = new City("Паданг", 100.3638, -0.9245, 9);
        cities[10] = new City("Бандар-Лампунг", 105.2560, -5.4254, 10);
        cities[11] = new City("Денпасар", 115.2196, -8.6526, 11);
        cities[12] = new City("Манадо", 124.8418, 1.4937, 12);
        cities[13] = new City("Палембанг",  104.744371, -2.963397, 13);
        cities[14] = new City("Депок", 106.803116, -6.394666, 14);

        return cities;
    }

    public static double[][] calculateDistanceMatrix(City[] cities){
        double[][] distanceMatrix = new double[cities.length][cities.length];

        for (int i = 0; i < cities.length; i++) {
            for (int j = 0; j < cities.length; j++) {
                if (i == j) {
                    distanceMatrix[i][j] = 0;
                } else {
                    // Обчислюємо відстань між містами за формулою Гаверсінуса
                    double lon1 = Math.toRadians(cities[i].getLongitude());
                    double lat1 = Math.toRadians(cities[i].getLatitude());
                    double lon2 = Math.toRadians(cities[j].getLongitude());
                    double lat2 = Math.toRadians(cities[j].getLatitude());

                    double dlon = lon2 - lon1;
                    double dlat = lat2 - lat1;

                    double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
                    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

                    double radius = 6371;//r km earth

                    double distance = radius * c;

                    distanceMatrix[i][j] = distance;
                }
            }
        }

        return distanceMatrix;
    }

    public static List<City> greedySearch(double[][] distanceMatrix, City[] cities, int startCityIndex, int endCityIndex) {
        List<City> path = new ArrayList<>();
        boolean[] visited = new boolean[cities.length];

        City startCity = cities[startCityIndex];
        City endCity = cities[endCityIndex];

        path.add(startCity);
        visited[startCityIndex] = true;

        while (path.get(path.size() - 1) != endCity) {
            double minDistance = Double.MAX_VALUE;
            int nextCityIndex = -1;

            for (int i = 0; i < cities.length; i++) {
                if (!visited[i]) {
                    double currentDistance = distanceMatrix[getIndex(path.get(path.size() - 1), cities)][i];
                    if (currentDistance < minDistance) {
                        minDistance = currentDistance;
                        nextCityIndex = i;
                    }
                }
            }

            if (nextCityIndex != -1) {
                City nextCity = cities[nextCityIndex];
                path.add(nextCity);
                visited[nextCityIndex] = true;
            } else {
                break;
            }
        }

        return path;
    }

    public static double calculatePathDistance(List<City> path, double[][] distanceMatrix, City[] cities) {
        double distance = 0;

        for (int i = 0; i < path.size() - 1; i++) {
            int cityIndex1 = getIndex(path.get(i), cities);
            int cityIndex2 = getIndex(path.get(i + 1), cities);
            distance += distanceMatrix[cityIndex1][cityIndex2];
        }

        return distance;
    }

    public static int getIndex(City city, City[] cities) {
        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equals(city)) {
                return i;
            }
        }
        return -1;
    }

    public static String formatPath(List<City> path) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < path.size(); i++) {
            sb.append(path.get(i).getName());
            if (i < path.size() - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }


    public static List<City> aStarSearch(double[][] distanceMatrix, City[] cities, int startCityIndex, int endCityIndex) {
        City startCity = cities[startCityIndex];
        City endCity = cities[endCityIndex];

        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();

        Node startNode = new Node(startCity, null, 0, calculateHeuristic(startCity, endCity));
        openList.add(startNode);

        while (!openList.isEmpty()) {
            //choose node with the less value
            InsertionSort.insertionSort(openList);
            Node currentNode = openList.remove(0);

            //Checking if it is the end place
            if (currentNode.getCity() == endCity) {
                // Побудова маршруту від кінцевого до початкового міста
                List<City> path = new ArrayList<>();
                while (currentNode != null) {
                    path.add(0, currentNode.getCity());
                    currentNode = currentNode.getParent();
                }
                return path;
            }

            // Додавання потомків до відкритого списку
            for (int i = 0; i < cities.length; i++) {
                City successorCity = cities[i];
                if (successorCity != currentNode.getCity() && !isCityInList(successorCity, closedList)) {
                    double successorCost = currentNode.getCost() + distanceMatrix[currentNode.getCity().getIndex()][i];
                    double successorHeuristic = calculateHeuristic(successorCity, endCity);
                    Node successorNode = new Node(successorCity, currentNode, successorCost, successorHeuristic);

                    if (isCityInList(successorCity, openList)) {
                        Node existingNode = getNodeByCity(successorCity, openList);
                        if (successorNode.getTotalCost() < existingNode.getTotalCost()) {
                            existingNode.setCost(successorCost);
                            existingNode.setParent(currentNode);
                        }
                    } else {
                        openList.add(successorNode);
                    }
                }
            }

            // Додавання поточного вузла до закритого списку
            closedList.add(currentNode);
        }

        // Якщо не вдається знайти шлях, повертаємо порожній список
        return new ArrayList<>();
    }

    public static double calculateHeuristic(City currentCity, City endCity) {
        double dx = endCity.getLongitude() - currentCity.getLongitude();
        double dy = endCity.getLatitude() - currentCity.getLatitude();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static boolean isCityInList(City city, List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node.getCity() == city) {
                return true;
            }
        }
        return false;
    }

    public static Node getNodeByCity(City city, List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node.getCity() == city) {
                return node;
            }
        }
        return null;
    }
}
