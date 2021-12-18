package mbta.model;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The Mbta class holds the entire subway system.
 *
 * It contains a set of MbtaRoute objects, a set of MbtaStop objects,
 * and a map that holds transfer information between routes.
 *
 * I ultimately had the transfer map in memory initialized during construction - at first, I had the pathfinder
 * method calculate it. Since I wanted to allow it to be reused, I kept it as a private object instead.
 *
 * It is also where the main business operations are performed from the requirements of the challenge.
 */

public class Mbta {

    private final Set<MbtaRoute> routes;
    private final Set<MbtaStop> stops;
    private final Map<String, Set<String>> transferMap;

    public Mbta(Set<MbtaRoute> routes, Set<MbtaStop> stops) {
        this.routes = routes;
        this.stops = stops;
        this.transferMap = createTransferMap();
    }

    public Set<MbtaRoute> getRoutes() {
        return routes;
    }

    public MbtaRoute findLongestRoute() {
        return routes.stream().max(Comparator.comparing(route -> route.getLongestPath().size())).get();
    }

    public MbtaRoute findShortestRoute() {
        return routes.stream().min(Comparator.comparing(route -> route.getShortestPath().size())).get();
    }

    public Set<MbtaStop> findIntersections() {
        return stops.stream().filter(stop -> stop.getRouteNames().size() > 1).collect(Collectors.toSet());
    }

    public MbtaStop findStop(String name) {
        return stops.stream().filter(stop -> stop.getStopName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public MbtaRoute findRoute(String id) {
        return routes.stream().filter(route -> route.getRouteId().equalsIgnoreCase(id)).findFirst().orElse(null);
    }

    private Map<String, Set<String>> createTransferMap() {
        Set<String> routeNames = routes.stream().map(MbtaRoute::getRouteId).collect(Collectors.toSet());
        Set<Set<String>> intersectionNames = findIntersections().stream().map(MbtaStop::getRouteNames).collect(Collectors.toSet());

        return routeNames.stream().collect(Collectors.toMap(r -> r,
                r -> intersectionNames.stream().filter(s -> s.contains(r))
                        .flatMap(Set::stream).filter(s -> !s.equals(r)).collect(Collectors.toSet())));
    }

    private List<String> modifiedBFS(Set<String> firstRouteNames, Set<String> secondRouteNames) {
        List<String> routeNameList = new ArrayList<>();
        Queue<String> routesToCheck = new ArrayDeque<>(firstRouteNames);
        Map<String, List<String>> paths = firstRouteNames.stream()
                .collect(Collectors.toMap(Object::toString, route -> new ArrayList<>(Arrays.asList(route))));

        List<String> checked = new ArrayList<>();
        while (!routesToCheck.isEmpty()) {
            String routeId = routesToCheck.poll();
            List<String> curPath = paths.get(routeId);
            checked.add(routeId);
            if (!secondRouteNames.contains(routeId)) {
                List<String> transfers = new ArrayList<>(transferMap.get(routeId));
                for (String transfer : transfers) {
                    if (!routesToCheck.contains(transfer) && !checked.contains(transfer)) {
                        checked.add(transfer);
                        routesToCheck.add(transfer);
                        List<String> newPath = new ArrayList<>(curPath);
                        newPath.add(transfer);
                        paths.put(transfer, newPath);
                    }
                }
            } else {
                return curPath;
            }
            routesToCheck.remove(routeId);
        }
        return routeNameList;
    }

    public List<MbtaRoute> findRouteBetweenStops(MbtaStop start, MbtaStop destination) {
        Set<String> firstRoutes = start.getRouteNames();
        Set<String> secondRoutes  = destination.getRouteNames();

        List<String> routeNameList = modifiedBFS(firstRoutes, secondRoutes);

        List<MbtaRoute> path = routeNameList.stream().map(route -> findRoute(route)).collect(Collectors.toList());
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mbta mbta = (Mbta) o;
        return routes.equals(mbta.routes) && stops.equals(mbta.stops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routes, stops);
    }
}