package mbta.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The MbtaPath class holds a single ordered list of MbtaStop objects that represent a specific path on a route.
 *
 * It uses a builder to be able to add one stop at a time.
 *
 * If there were more functionality required, it could hold more specific information, such as direction, to help
 * create a proper trip between stops.
 */

public class MbtaPath {
    private final List<MbtaStop> stops;

    private MbtaPath(PathBuilder builder) {
        this.stops = builder.stops;
    }

    public int size() {
        return stops.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MbtaPath mbtaPath = (MbtaPath) o;
        return stops.equals(mbtaPath.stops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stops);
    }

    public static class PathBuilder {
        private List<MbtaStop> stops;

        public PathBuilder() {
            stops = new ArrayList<>();
        }

        public PathBuilder addStop(MbtaStop stop) {
            this.stops.add(stop);
            return this;
        }

        public PathBuilder addStops(List<MbtaStop> stops) {
            this.stops.addAll(stops);
            return this;
        }

        public MbtaPath build() {
            return new MbtaPath(this);
        }
    }
}
