package mbta;

import mbta.model.MbtaPath;
import mbta.model.MbtaRoute;

public class TestMbtaRouteFixture {

    public static MbtaRoute BLUE_LINE = new MbtaRoute.RouteBuilder("Blue", "Blue Line")
            .addPath(new MbtaPath.PathBuilder().addStop(TestMbtaStopFixture.WONDERLAND).addStop(TestMbtaStopFixture.REVERE)
                    .addStop(TestMbtaStopFixture.BEACHMONT).addStop(TestMbtaStopFixture.SUFFOLKDOWNS).addStop(TestMbtaStopFixture.ORIENTHEIGHTS)
                    .addStop(TestMbtaStopFixture.WOODISLAND).addStop(TestMbtaStopFixture.AIRPORT).addStop(TestMbtaStopFixture.MAVERICK)
                    .addStop(TestMbtaStopFixture.AQUARIUM).addStop(TestMbtaStopFixture.STATE).addStop(TestMbtaStopFixture.GOVCENTER)
                    .addStop(TestMbtaStopFixture.BOWDOIN).build()).build();

    public static MbtaRoute BLUE_LINE_WITH_ORANGE = new MbtaRoute.RouteBuilder("Blue", "Blue Line")
            .addPath(new MbtaPath.PathBuilder().addStop(TestMbtaStopFixture.WONDERLAND).addStop(TestMbtaStopFixture.REVERE)
                    .addStop(TestMbtaStopFixture.BEACHMONT).addStop(TestMbtaStopFixture.SUFFOLKDOWNS).addStop(TestMbtaStopFixture.ORIENTHEIGHTS)
                    .addStop(TestMbtaStopFixture.WOODISLAND).addStop(TestMbtaStopFixture.AIRPORT).addStop(TestMbtaStopFixture.MAVERICK)
                    .addStop(TestMbtaStopFixture.AQUARIUM).addStop(TestMbtaStopFixture.STATE_WITH_ORANGE).addStop(TestMbtaStopFixture.GOVCENTER)
                    .addStop(TestMbtaStopFixture.BOWDOIN).build()).build();

    public static MbtaRoute ORANGE_LINE = new MbtaRoute.RouteBuilder("Orange", "Orange Line")
            .addPath(new MbtaPath.PathBuilder().addStop(TestMbtaStopFixture.OAKGROVE).addStop(TestMbtaStopFixture.MALDEN)
                    .addStop(TestMbtaStopFixture.WELLINGTON).addStop(TestMbtaStopFixture.ASSEMBLY).addStop(TestMbtaStopFixture.SULLIVAN)
                    .addStop(TestMbtaStopFixture.COMMUNITYCOLL).addStop(TestMbtaStopFixture.NORTHSTATION).addStop(TestMbtaStopFixture.HAYMARKET)
                    .addStop(TestMbtaStopFixture.STATE_WITH_ORANGE).addStop(TestMbtaStopFixture.DOWNTOWNCROSSING_WITH_ORANGE).addStop(TestMbtaStopFixture.CHINATOWN)
                    .addStop(TestMbtaStopFixture.TUFTS).addStop(TestMbtaStopFixture.BACKBAY).addStop(TestMbtaStopFixture.MASSAVE)
                    .addStop(TestMbtaStopFixture.RUGGLES).addStop(TestMbtaStopFixture.ROXBURY).addStop(TestMbtaStopFixture.JACKSON)
                    .addStop(TestMbtaStopFixture.STONYBROOK).addStop(TestMbtaStopFixture.GREENSTREET).addStop(TestMbtaStopFixture.FORESTHILLS).build()).build();

    public static MbtaRoute RED_LINE = new MbtaRoute.RouteBuilder("Red", "Red Line")
            .addPath(new MbtaPath.PathBuilder().addStop(TestMbtaStopFixture.ALEWIFE).addStop(TestMbtaStopFixture.DAVIS)
                    .addStop(TestMbtaStopFixture.PORTER).addStop(TestMbtaStopFixture.HARVARD).addStop(TestMbtaStopFixture.CENTRAL)
                    .addStop(TestMbtaStopFixture.KENDALL).addStop(TestMbtaStopFixture.CHARLES).addStop(TestMbtaStopFixture.PARKSTREET)
                    .addStop(TestMbtaStopFixture.DOWNTOWNCROSSING).addStop(TestMbtaStopFixture.SOUTHSTATION).addStop(TestMbtaStopFixture.BROADWAY)
                    .addStop(TestMbtaStopFixture.ANDREW).addStop(TestMbtaStopFixture.JFK).addStop(TestMbtaStopFixture.SAVINHILL)
                    .addStop(TestMbtaStopFixture.FIELDSCORNER).addStop(TestMbtaStopFixture.SHAWMUT).addStop(TestMbtaStopFixture.ASHMONT).build())
            .addPath(new MbtaPath.PathBuilder().addStop(TestMbtaStopFixture.ALEWIFE).addStop(TestMbtaStopFixture.DAVIS)
                    .addStop(TestMbtaStopFixture.PORTER).addStop(TestMbtaStopFixture.HARVARD).addStop(TestMbtaStopFixture.CENTRAL)
                    .addStop(TestMbtaStopFixture.KENDALL).addStop(TestMbtaStopFixture.CHARLES).addStop(TestMbtaStopFixture.PARKSTREET)
                    .addStop(TestMbtaStopFixture.DOWNTOWNCROSSING).addStop(TestMbtaStopFixture.SOUTHSTATION).addStop(TestMbtaStopFixture.BROADWAY)
                    .addStop(TestMbtaStopFixture.ANDREW).addStop(TestMbtaStopFixture.JFK).addStop(TestMbtaStopFixture.NORTHQUINCY)
                    .addStop(TestMbtaStopFixture.WOLLASTON).addStop(TestMbtaStopFixture.QUINCYCENTER).addStop(TestMbtaStopFixture.QUINCYADAMS)
                    .addStop(TestMbtaStopFixture.BRAINTREE).build()).build();

    public static MbtaRoute RED_LINE_WITH_ORANGE = new MbtaRoute.RouteBuilder("Red", "Red Line")
            .addPath(new MbtaPath.PathBuilder().addStop(TestMbtaStopFixture.ALEWIFE).addStop(TestMbtaStopFixture.DAVIS)
                    .addStop(TestMbtaStopFixture.PORTER).addStop(TestMbtaStopFixture.HARVARD).addStop(TestMbtaStopFixture.CENTRAL)
                    .addStop(TestMbtaStopFixture.KENDALL).addStop(TestMbtaStopFixture.CHARLES).addStop(TestMbtaStopFixture.PARKSTREET)
                    .addStop(TestMbtaStopFixture.DOWNTOWNCROSSING_WITH_ORANGE).addStop(TestMbtaStopFixture.SOUTHSTATION).addStop(TestMbtaStopFixture.BROADWAY)
                    .addStop(TestMbtaStopFixture.ANDREW).addStop(TestMbtaStopFixture.JFK).addStop(TestMbtaStopFixture.SAVINHILL)
                    .addStop(TestMbtaStopFixture.FIELDSCORNER).addStop(TestMbtaStopFixture.SHAWMUT).addStop(TestMbtaStopFixture.ASHMONT).build())
            .addPath(new MbtaPath.PathBuilder().addStop(TestMbtaStopFixture.ALEWIFE).addStop(TestMbtaStopFixture.DAVIS)
                    .addStop(TestMbtaStopFixture.PORTER).addStop(TestMbtaStopFixture.HARVARD).addStop(TestMbtaStopFixture.CENTRAL)
                    .addStop(TestMbtaStopFixture.KENDALL).addStop(TestMbtaStopFixture.CHARLES).addStop(TestMbtaStopFixture.PARKSTREET)
                    .addStop(TestMbtaStopFixture.DOWNTOWNCROSSING_WITH_ORANGE).addStop(TestMbtaStopFixture.SOUTHSTATION).addStop(TestMbtaStopFixture.BROADWAY)
                    .addStop(TestMbtaStopFixture.ANDREW).addStop(TestMbtaStopFixture.JFK).addStop(TestMbtaStopFixture.NORTHQUINCY)
                    .addStop(TestMbtaStopFixture.WOLLASTON).addStop(TestMbtaStopFixture.QUINCYCENTER).addStop(TestMbtaStopFixture.QUINCYADAMS)
                    .addStop(TestMbtaStopFixture.BRAINTREE).build()).build();
}
