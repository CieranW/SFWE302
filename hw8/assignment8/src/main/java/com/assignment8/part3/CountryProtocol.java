package hw8.assignment8.src.main.java.com.assignment8.part3;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class CountryProtocol {
    private static final int WAITING = 0;
    private static final int SENTCOUNTRY = 1;
    private static final int ANOTHER = 2;

    private int state = WAITING;


    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING) {
            theOutput = "What country would you like to know about?";
            state = SENTCOUNTRY;
        } else if (state == SENTCOUNTRY) {
            if (theInput.equalsIgnoreCase("United States")) {
                theOutput = getCountryInfo(theInput);
                state = ANOTHER;
            } else if (theInput.equalsIgnoreCase("Germany")) {
                theOutput =getCountryInfo(theInput);
                state = ANOTHER;
            } else if (theInput.equalsIgnoreCase("Czech Republic")) {
                theOutput = getCountryInfo(theInput);
                state = ANOTHER;
            } else if (theInput.equalsIgnoreCase("China")) {
                theOutput = getCountryInfo(theInput);
                state = ANOTHER;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("yes")) {
                theOutput = "What country would you like to know about?";
                state = SENTCOUNTRY;
            } else {
                theOutput = "Bye";
            }
        }

        return theOutput;
    }

    public String getCountryInfo(String country) {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://universities.hipolabs.com/search?country=" + country.replace(" ", "%20")))
                .header("content-type", "application/json")
                .GET()
                .build();

            HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

            JSONArray universities = new JSONArray(response.body());
            int count = universities.length();

            if (count == 0) {
                return "No universities found in " + country + ".";
            }

            StringBuilder result = new StringBuilder();
            result.append("Found ").append(count).append(" universities in ").append(country).append(":\n");

            for (int i = 0; i < count; i++) {
                JSONObject uni = universities.getJSONObject(i);
                result.append("- ").append(uni.getString("name")).append("\n");
            }

            return result.toString();
        } catch (Exception e) {
            return "Error fetching university data for " + country + ".";
        }
    }
}
