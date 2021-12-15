package mbta.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * The MbtaStop object represents a single stop on the subway, but doesn't correspond exactly to the MBTA API.
 * It merges some properties across several stop objects as retrieved from the API.
 *
 * It holds a stopId which comes from what the API considers the "parent station" and stopName which is the same across
 * the parent station and child stops. The parent station is the link to linking a stop across routes, as each stop is
 * otherwise considered its own JSON object. Lastly it holds a set of route names which correspond to IDs of MbtaRoute objects.
 *
 * This avoids a direct bidirectional link which was not possible to set up immutably, but helps link when calculating
 * paths between stops. A more sophisticated method may be required if functionality were to be extended.
 *
 */

public class MbtaStop {
    private final String stopId;
    private final Set<String> routeNames;
    private final String stopName;

    public MbtaStop(String stopId, String stopName, Set<String> routeNames) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.routeNames = routeNames;
    }

    public String getStopName() {
        return stopName;
    }

    public Set<String> getRouteNames() {
        return new HashSet<>(routeNames);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MbtaStop mbtaStop = (MbtaStop) o;
        return stopId.equals(mbtaStop.stopId) && routeNames.equals(mbtaStop.routeNames) && stopName.equals(mbtaStop.stopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopId, routeNames, stopName);
    }
}
