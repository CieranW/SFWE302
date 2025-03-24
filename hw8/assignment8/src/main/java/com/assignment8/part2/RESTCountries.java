package hw8.assignment8.src.main.java.com.assignment8.part2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class RESTCountries {
    public static void main(String[] args) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://restcountries.com/v3.1/all"))
            .header("content-type", "application/json")
            .method("GET", HttpRequest.BodyPublishers.noBody())
            .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
            .send(request, HttpResponse.BodyHandlers.ofString());
        String result = response.body();

        JSONArray countries = new JSONArray(result);
        for (int i = 0; i < countries.length(); i++) {
            JSONObject country = countries.getJSONObject(i);
            System.out.println(country.getJSONObject("name"));
            System.out.println(country.getJSONArray("capital").get(0));
            System.out.println(country.getJSONObject("languages"));

            for (String key : country.getJSONObject("languages").keySet()) {
                System.out.println(key);
            }
        }
    }
}
