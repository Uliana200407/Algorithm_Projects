public class Node {
    private City city;
    private Node parent;
    private double cost;
    private double heuristic;

    public Node(City city, Node parent, double cost, double heuristic) {
        this.city = city;
        this.parent = parent;
        this.cost = cost;
        this.heuristic = heuristic;
    }

    public City getCity() {
        return city;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(double heuristic) {
        this.heuristic = heuristic;
    }

    public double getTotalCost() {
        return cost + heuristic;
    }
}