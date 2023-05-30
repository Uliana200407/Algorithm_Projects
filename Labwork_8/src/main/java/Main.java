import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws IOException {

        City[] cities = Functions.createCities ();
        double[][] distanceMatrix = new double[cities.length][cities.length];

        try {
            for (int i = 0; i < cities.length; i++) {
                for (int j = 0; j < cities.length; j++) {
                    if (i == j) {
                        distanceMatrix[i][j] = 0;
                    } else {
                        // Create a URL object with the endpoint URL
                        URL url = new URL("https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + cities[i].getName ()+ "&destinations="+ cities[j].getName ()+"&units=metric&key=AIzaSyDFf9YGUYEmseBaUZNnMrAotHe2lWW29D4");
                        // Open a connection to the URL
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection ();

                        // Set the request method (GET, POST, etc.)
                        connection.setRequestMethod ( "GET" );

                        // Set the request headers, if needed
                        connection.setRequestProperty ( "Content-Type", "application/json" );

                        // Get the response code
                        int responseCode = connection.getResponseCode ();
                        System.out.println ( "Response Code: " + responseCode );

                        // Read the response content
                        BufferedReader reader = new BufferedReader ( new InputStreamReader ( connection.getInputStream () ) );
                        String line;
                        StringBuilder response = new StringBuilder ();

                        while ((line = reader.readLine ()) != null) {
                            response.append ( line );
                        }
                        reader.close ();


                        connection.disconnect ();

                        try {
                            // Parse the JSON string
                            JSONObject json = new JSONObject ( response.toString () );

                            // Get the "rows" array
                            JSONArray rows = json.getJSONArray ( "rows" );

                            // Get the first element of the "rows" array
                            JSONObject row = rows.getJSONObject ( 0 );

                            // Get the "elements" array
                            JSONArray elements = row.getJSONArray ( "elements" );

                            // Get the first element of the "elements" array
                            JSONObject element = elements.getJSONObject ( 0 );

                            // Get the "distance" object
                            JSONObject distance = element.getJSONObject ( "distance" );

                            // Get the "text" value from the "distance" object
                            String distanceText = distance.getString ( "text" );

                            System.out.println ( cities[i].getName () + " to " + cities[j].getName () + " Distance: " + distanceText + " Path: " + cities[i].getName () + " to " + cities[j].getName () );
                            var splittedString = distanceText.split ( " " );
                            System.out.println ( splittedString[0] );
                            float distanceFloat = Float.parseFloat ( splittedString[0].replace ( ',', '.' ) );

                            distanceMatrix[i][j] = distanceFloat;

                            saveResponseToFile(cities[i], cities[j], distanceText);
                        } catch (JSONException e) {
                            e.printStackTrace ();
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    private static void saveResponseToFile(City origin, City destination, String distance) {
        String fileName = "API.distances.txt";
        try (BufferedWriter writer = new BufferedWriter ( new FileWriter ( fileName, true ) )) {
            writer.write ( "Origin: " + origin.getName () );
            writer.newLine ();
            writer.write ( "Destination: " + destination.getName () );
            writer.newLine ();
            writer.write ( "Distance: " + distance );
            writer.newLine ();
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
