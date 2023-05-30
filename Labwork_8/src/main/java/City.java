public class City {
    City(String name, double longitude, double latitude, int index){
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.index = index;
    }

    private String name;
    private double longitude;
    private double latitude;
    private int index;

    public int getIndex() {
        return index;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getFullInfo(){
        return String.format("City name: %s, coordinated(latitude, longitude): %f,%f", this.name, this.latitude, this.longitude);
    }
}
