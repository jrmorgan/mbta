package mbta;

import mbta.model.Mbta;
import mbta.model.MbtaRoute;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TestMbta {
    private Mbta mbta;

    @BeforeEach
    void setUp() {
        TestMbtaFixture testFixtures = new TestMbtaFixture();
        mbta = testFixtures.getMbta(TestMbtaFixture.Route.ALL);
    }

    @Test
    void findLongestRoute() {
        MbtaRoute actual = mbta.findLongestRoute();
        assertAll(
                () -> assertEquals(TestMbtaRouteFixture.ORANGE_LINE, actual),
                () -> assertEquals(20, actual.getLongestPath().size()));
    }

    @Test
    void findShortestRoute() {
        MbtaRoute actual = mbta.findShortestRoute();
        assertAll(
                () -> assertEquals(TestMbtaRouteFixture.BLUE_LINE_WITH_ORANGE, actual),
                () -> assertEquals(12, actual.getShortestPath().size()));
    }

    @Test
    void findIntersections() {
        assertEquals(mbta.findIntersections(), Set.of(TestMbtaStopFixture.DOWNTOWNCROSSING_WITH_ORANGE, TestMbtaStopFixture.STATE_WITH_ORANGE));
    }

    @Disabled
    void findIntersectionsSharedLineNotRoute() {
        //this would be for testing stops which are on multiple routes that share a line
    }

    @Test
    void findStopWrongName() {
        assertNull(mbta.findStop("Park Ave"));
    }

    @Test
    void findStop() {
        assertEquals( TestMbtaStopFixture.PARKSTREET, mbta.findStop("Park Street"));
    }

    @Test
    void findStopAllCaps() {
        assertEquals( TestMbtaStopFixture.PARKSTREET, mbta.findStop("PARK STREET"));
    }

    @Test
    void findStopNoCaps() {
        assertEquals( TestMbtaStopFixture.PARKSTREET, mbta.findStop("park street"));
    }

    @Test
    void findStopMixedCaps() {
        assertEquals( TestMbtaStopFixture.PARKSTREET, mbta.findStop("Park sTreeT"));
    }

    @Test
    void findRouteWrongName() {
        assertNull(mbta.findRoute("Orang"));
    }

    @Test
    void findRoute() {
        assertEquals(TestMbtaRouteFixture.RED_LINE_WITH_ORANGE, mbta.findRoute("Red"));
    }

    @Test
    void findRouteAllCaps() {
        assertEquals(TestMbtaRouteFixture.RED_LINE_WITH_ORANGE, mbta.findRoute("RED"));
    }

    @Test
    void findRouteNoCaps() {
        assertEquals(TestMbtaRouteFixture.RED_LINE_WITH_ORANGE, mbta.findRoute("red"));
    }

    @Test
    void findRouteMixedCaps() {
        assertEquals(TestMbtaRouteFixture.RED_LINE_WITH_ORANGE, mbta.findRoute("ReD"));
    }

    @Test
    void findRouteBetweenStopsSameRoute() {
        assertEquals(List.of(TestMbtaRouteFixture.BLUE_LINE_WITH_ORANGE),
                mbta.findRouteBetweenStops(TestMbtaStopFixture.AIRPORT, TestMbtaStopFixture.WONDERLAND));
    }

    @Test
    void findRouteBetweenStopsSameWithTransferStop() {
        assertEquals(List.of(TestMbtaRouteFixture.RED_LINE_WITH_ORANGE),
                mbta.findRouteBetweenStops(TestMbtaStopFixture.DOWNTOWNCROSSING_WITH_ORANGE, TestMbtaStopFixture.ALEWIFE));
    }

    @Test
    void findRouteBetweenStopsOneTransfer() {
        assertEquals(List.of(TestMbtaRouteFixture.RED_LINE_WITH_ORANGE, TestMbtaRouteFixture.ORANGE_LINE),
                mbta.findRouteBetweenStops(TestMbtaStopFixture.BROADWAY, TestMbtaStopFixture.WELLINGTON));
    }

    @Test
    void findRouteBetweenStopsTwoTransfers() {
        assertEquals(List.of(TestMbtaRouteFixture.BLUE_LINE_WITH_ORANGE, TestMbtaRouteFixture.ORANGE_LINE, TestMbtaRouteFixture.RED_LINE_WITH_ORANGE),
                mbta.findRouteBetweenStops(TestMbtaStopFixture.WONDERLAND, TestMbtaStopFixture.CENTRAL));
    }
}