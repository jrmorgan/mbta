package mbta.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import mbta.model.MbtaPath;
import mbta.model.MbtaRoute;
import mbta.model.MbtaStop;
import mbta.model.Mbta;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The MbtaMapper class takes in the JsonNode tree generated from the MbtaClient and parses it.
 *
 * It will create MbtaStop, MbtaPath, and MbtaRoute objects, connect them to each other, and then
 * create the full Mbta object and return it.
 *
 */

public class MbtaMapper {

    Set<JsonNode> stops = new HashSet<>();
    Map<String, Set<JsonNode>> trips = new HashMap<>();
    Map<String, String> parentStations = new HashMap<>();

    private void filterIncluded(JsonNode included) {
        Set<String> routePatternIds = new HashSet<>();
        for (JsonNode node : included) {
            switch (node.get("type").asText()) {
                case "stop":
                    stops.add(node);
                    String parentStationId = node.at("/relationships/parent_station/data/id").asText();
                    String stopId = node.get("id").asText();
                    parentStations.put(stopId, parentStationId);
                    break;
                case "route_pattern":
                    JsonNode attributes = node.get("attributes");
                    //only get typical routes and only need one direction since direction filter does not apply to route_pattern
                    if (attributes.get("typicality").asInt() == 1 && attributes.get("direction_id").asInt() == 0) {
                        routePatternIds.add(node.get("id").asText());
                    }
                    break;
                case "trip":
                    String routeId = node.at("/relationships/route/data/id").asText();
                    Set<JsonNode> relationships = trips.computeIfAbsent(routeId, k -> new HashSet<>());
                    relationships.add(node.at("/relationships"));
                    break;
            }
        }
        trips.values().forEach(nodes -> nodes.removeIf(node -> !routePatternIds.contains(node.at("/route_pattern/data/id").asText())));
    }

    private Set<MbtaRoute> buildPathsAndRoutes(Map<String, String> routes, Map<String, MbtaStop> stops) {
        Set<MbtaRoute> allRoutes = new HashSet<>();
        trips.forEach((route, routeTrips) -> {
            MbtaRoute.RouteBuilder routeBuilder = new MbtaRoute.RouteBuilder(route, routes.get(route));
            routeTrips.forEach(node -> {
                JsonNode tripStops = node.at("/stops/data");
                MbtaPath.PathBuilder pathBuilder = new MbtaPath.PathBuilder();
                List<String> stopList = tripStops.findValuesAsText("id");
                //changed from a `forEach` using `addStop`
                pathBuilder.addStops(
                        stopList.stream().map(stop ->
                                stops.get(parentStations.get(stop))).collect(Collectors.toList()));
                routeBuilder.addPath(pathBuilder.build());
            });
            allRoutes.add(routeBuilder.build());
        });
        return allRoutes;
    }

    public Mbta createMbta(JsonNode mbtaTree) {
        JsonNode routes = mbtaTree.get("data");
        JsonNode included = mbtaTree.get("included");

        Map<String, String> routeMap = new HashMap<>();

        for (JsonNode node : routes) {
            routeMap.put(node.get("id").asText(), node.at("/attributes/long_name").asText());
        }

        filterIncluded(included);
        Map<String, Set<String>> parentStationRoutes = new HashMap<>();

        trips.forEach((route, routeTrips) -> routeTrips.forEach(node -> {
            List<String> stopList = node.at("/stops/data").findValuesAsText("id");
            stopList.forEach(stop -> {
                Set<String> parentRoutes = parentStationRoutes.computeIfAbsent(parentStations.get(stop), k -> new HashSet<>());
                parentRoutes.add(route);
            });
        }));

        Map<String, MbtaStop> allStops = new HashMap<>();
        for (JsonNode node : stops) {
            String stopId = node.get("id").asText();
            String name = node.at("/attributes/name").asText();
            String parentStationId = parentStations.get(stopId);
            Set<String> stopRoutes = parentStationRoutes.get(parentStationId);
            allStops.computeIfAbsent(parentStationId, k -> new MbtaStop(parentStationId, name, stopRoutes));
        }

        Set<MbtaRoute> allRoutes = buildPathsAndRoutes(routeMap, allStops);

        return new Mbta(allRoutes, new HashSet<>(allStops.values()));
    }
}
