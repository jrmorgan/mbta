package mbta;

import mbta.model.Mbta;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestMbtaFixture {

    enum Route {
        BLUE,
        RED,
        ALL
    }

    public Mbta getMbta(Route route) {
        switch (route) {
            case BLUE:
                return new Mbta(Set.of(TestMbtaRouteFixture.BLUE_LINE), TestMbtaStopFixture.BLUE_STOPS);
            case RED:
                return new Mbta(Set.of(TestMbtaRouteFixture.RED_LINE), TestMbtaStopFixture.RED_STOPS);
            case ALL:
            default:
                return new Mbta(Set.of(TestMbtaRouteFixture.RED_LINE_WITH_ORANGE, TestMbtaRouteFixture.BLUE_LINE_WITH_ORANGE,
                        TestMbtaRouteFixture.ORANGE_LINE), Stream.of(TestMbtaStopFixture.BLUE_STOPS_WITH_ORANGE, TestMbtaStopFixture.RED_STOPS_WITH_ORANGE,
                        TestMbtaStopFixture.ORANGE_STOPS).flatMap(Set::stream).collect(Collectors.toSet()));
        }
    }

}
