package mbta.client;

import java.net.http.*;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URI;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;

/**
 * The MbtaClient class initializes the query to the MBTA API and returns a JsonNode tree via Jackson.
 *
 * If there were additional queries to be added to the application, the client could be refactored a little
 * to make it a bit smoother looking and more adaptable.
 */


public class MbtaClient {

    public JsonNode makeQuery() throws JsonProcessingException{
        HttpClient client = HttpClient.newHttpClient();
        String baseUri = "https://api-v3.mbta.com/routes?";
        String optionsUri = "fields[route]=long_name&filter[type]=0,1&fields[trip]=id" +
                "&include=route_patterns.representative_trip.stops";
        String apiKey = System.getenv("API_KEY");
        String apiString = apiKey != null ? "&api_key=" + apiKey : "";
        HttpRequest request = HttpRequest.newBuilder(URI.create(baseUri + optionsUri + apiString)).build();
        ObjectMapper mapper = new ObjectMapper();
        return client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(response -> {
                    if (response.statusCode() == 200) {
                        try {
                            return mapper.readTree(response.body());
                        } catch (JsonProcessingException e) {
                            System.err.println(e);
                            return mapper.createObjectNode();
                        }
                    } else {
                        System.err.println(response.body());
                        return mapper.createObjectNode();
                    }
                }).join();
    }
}
