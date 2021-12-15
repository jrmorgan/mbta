package mbta.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Comparator;

/**
 * The MbtaRoute class represents a single route as considered one by the MBTA API.
 * It contains a routeId, routeName, and a set of paths on the route.
 *
 * It holds multiple paths, so it can hold different branches, such as the Red Line's Ashmont/Braintree branches.
 *
 * It does not represent Green Line trains as a single route from the API, and it remains separate here.
 *
 * The lineName String may be added to represent the connections between the Green Lines.
 *
 * It uses a builder to be able to add MbtaPath objects one at a time.
 */

public class MbtaRoute {
    private final String routeId;
    private final String routeName;
    private final Set<MbtaPath> paths;
    //private final String lineName; //allows some grouping for Green line. May need adjustment

    private MbtaRoute(RouteBuilder builder) {
        this.routeId = builder.routeId;
        this.routeName = builder.routeName;
        this.paths = builder.paths;
    }

    public String getRouteName() {
        return routeName;
    }

    public Set<MbtaPath> getPaths() {
        return new HashSet<>(paths);
    }

    //public String getLineName() {
    //    return lineName;
    //}

    public MbtaPath getLongestPath() {
        return paths.stream().max(Comparator.comparing(MbtaPath::size)).get();
    }

    public MbtaPath getShortestPath() {
        return paths.stream().min(Comparator.comparing(MbtaPath::size)).get();
    }

    public String getRouteId() {
        return routeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MbtaRoute mbtaRoute = (MbtaRoute) o;
        return routeId.equals(mbtaRoute.routeId) && routeName.equals(mbtaRoute.routeName) && paths.equals(mbtaRoute.paths);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, routeName, paths);
    }

    public static class RouteBuilder {
        private String routeId;
        private String routeName;
        private Set<MbtaPath> paths;

        public RouteBuilder(String routeId, String routeName) {
            this.routeId = routeId;
            this.routeName = routeName;
            this.paths = new HashSet<>();
        }

        public RouteBuilder addPath(MbtaPath path) {
            this.paths.add(path);
            return this;
        }

        public MbtaRoute build() {
            return new MbtaRoute(this);
        }
    }
}
