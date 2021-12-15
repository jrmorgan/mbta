package mbta;

import mbta.model.MbtaStop;

import java.util.Set;

public class TestMbtaStopFixture {

    public static final MbtaStop AQUARIUM = new MbtaStop("place-aqucl", "Aquarium", Set.of("Blue"));
    public static final MbtaStop SUFFOLKDOWNS = new MbtaStop("place-sdmnl", "Suffolk Downs", Set.of("Blue"));
    public static final MbtaStop GOVCENTER = new MbtaStop("place-gover", "Government Center", Set.of("Blue"));
    public static final MbtaStop ORIENTHEIGHTS = new MbtaStop("place-orhte", "Orient Heights", Set.of("Blue"));
    public static final MbtaStop STATE_WITH_ORANGE = new MbtaStop("place-state", "State", Set.of("Blue", "Orange"));
    public static final MbtaStop STATE = new MbtaStop("place-state", "State", Set.of("Blue"));
    public static final MbtaStop REVERE = new MbtaStop("place-rbmnl", "Revere Beach", Set.of("Blue"));
    public static final MbtaStop WONDERLAND = new MbtaStop("place-wondl", "Wonderland", Set.of("Blue"));
    public static final MbtaStop WOODISLAND = new MbtaStop("place-wimnl", "Wood Island", Set.of("Blue"));
    public static final MbtaStop MAVERICK = new MbtaStop("place-mvbcl", "Maverick", Set.of("Blue"));
    public static final MbtaStop AIRPORT = new MbtaStop("place-aport", "Airport", Set.of("Blue"));
    public static final MbtaStop BEACHMONT = new MbtaStop("place-bmmnl", "Beachmont", Set.of("Blue"));
    public static final MbtaStop BOWDOIN = new MbtaStop("place-bomnl", "Bowdoin", Set.of("Blue"));
    public static final MbtaStop SAVINHILL = new MbtaStop("place-shmnl", "Savin Hill", Set.of("Red"));
    public static final MbtaStop MALDEN = new MbtaStop("place-mlmnl", "Malden Center", Set.of("Orange"));
    public static final MbtaStop HAYMARKET = new MbtaStop("place-haecl", "Haymarket", Set.of("Orange"));
    public static final MbtaStop ANDREW = new MbtaStop("place-andrw", "Andrew", Set.of("Red"));
    public static final MbtaStop PARKSTREET = new MbtaStop("place-pktrm", "Park Street", Set.of("Red"));
    public static final MbtaStop FORESTHILLS = new MbtaStop("place-forhl", "Forest Hills", Set.of("Orange"));
    public static final MbtaStop TUFTS = new MbtaStop("place-tumnl", "Tufts Medical Center", Set.of("Orange"));
    public static final MbtaStop MASSAVE = new MbtaStop("place-masta", "Massachusetts Avenue", Set.of("Orange"));
    public static final MbtaStop ASSEMBLY = new MbtaStop("place-astao", "Assembly", Set.of("Orange"));
    public static final MbtaStop PORTER = new MbtaStop("place-portr", "Porter", Set.of("Red"));
    public static final MbtaStop WELLINGTON = new MbtaStop("place-welln", "Wellington", Set.of("Orange"));
    public static final MbtaStop HARVARD = new MbtaStop("place-harsq", "Harvard", Set.of("Red"));
    public static final MbtaStop SULLIVAN = new MbtaStop("place-sull", "Sullivan Square", Set.of("Orange"));
    public static final MbtaStop QUINCYCENTER = new MbtaStop("place-qnctr", "Quincy Center", Set.of("Red"));
    public static final MbtaStop CENTRAL = new MbtaStop("place-cntsq", "Central", Set.of("Red"));
    public static final MbtaStop BROADWAY = new MbtaStop("place-brdwy", "Broadway", Set.of("Red"));
    public static final MbtaStop JACKSON = new MbtaStop("place-jaksn", "Jackson Square", Set.of("Orange"));
    public static final MbtaStop COMMUNITYCOLL = new MbtaStop("place-ccmnl", "Community College", Set.of("Orange"));;
    public static final MbtaStop BACKBAY = new MbtaStop("place-bbsta", "Back Bay", Set.of("Orange"));
    public static final MbtaStop WOLLASTON = new MbtaStop("place-wlsta", "Wollaston", Set.of("Red"));
    public static final MbtaStop RUGGLES = new MbtaStop("place-rugg", "Ruggles", Set.of("Orange"));
    public static final MbtaStop FIELDSCORNER = new MbtaStop("place-fldcr", "Fields Corner", Set.of("Red"));
    public static final MbtaStop CHINATOWN = new MbtaStop("place-chncl", "Chinatown", Set.of("Orange"));
    public static final MbtaStop ASHMONT = new MbtaStop("place-asmnl", "Ashmont", Set.of("Red"));
    public static final MbtaStop SHAWMUT = new MbtaStop("place-smmnl", "Shawmut", Set.of("Red"));
    public static final MbtaStop NORTHSTATION = new MbtaStop("place-north", "North Station", Set.of("Orange"));
    public static final MbtaStop JFK = new MbtaStop("place-jfk", "JFK/UMass", Set.of("Red"));
    public static final MbtaStop OAKGROVE = new MbtaStop("place-ogmnl", "Oak Grove", Set.of("Orange"));
    public static final MbtaStop CHARLES = new MbtaStop("place-chmnl", "Charles/MGH", Set.of("Red"));
    public static final MbtaStop GREENSTREET = new MbtaStop("place-grnst", "Green Street", Set.of("Orange"));
    public static final MbtaStop NORTHQUINCY = new MbtaStop("place-nqncy", "North Quincy", Set.of("Red"));
    public static final MbtaStop QUINCYADAMS = new MbtaStop("place-qamnl", "Quincy Adams", Set.of("Red"));
    public static final MbtaStop KENDALL = new MbtaStop("place-knncl", "Kendall/MIT", Set.of("Red"));
    public static final MbtaStop DOWNTOWNCROSSING_WITH_ORANGE = new MbtaStop("place-dwnxg", "Downtown Crossing", Set.of("Red", "Orange"));
    public static final MbtaStop DOWNTOWNCROSSING = new MbtaStop("place-dwnxg", "Downtown Crossing", Set.of("Red"));
    public static final MbtaStop DAVIS = new MbtaStop("place-davis", "Davis", Set.of("Red"));
    public static final MbtaStop ROXBURY = new MbtaStop("place-rcmnl", "Roxbury Crossing", Set.of("Orange"));
    public static final MbtaStop ALEWIFE = new MbtaStop("place-alfcl", "Alewife", Set.of("Red"));
    public static final MbtaStop BRAINTREE = new MbtaStop("place-brntn", "Braintree", Set.of("Red"));
    public static final MbtaStop STONYBROOK = new MbtaStop("place-sbmnl", "Stony Brook", Set.of("Orange"));
    public static final MbtaStop SOUTHSTATION = new MbtaStop("place-sstat", "South Station", Set.of("Red"));

    public static final Set<MbtaStop> RED_STOPS = Set.of(ALEWIFE, DAVIS, PORTER, HARVARD, CENTRAL, KENDALL, CHARLES, PARKSTREET,
            DOWNTOWNCROSSING, SOUTHSTATION, BROADWAY, ANDREW, JFK, SAVINHILL, FIELDSCORNER, SHAWMUT, ASHMONT, NORTHQUINCY,
            WOLLASTON, QUINCYCENTER, QUINCYADAMS, BRAINTREE);

    public static final Set<MbtaStop> BLUE_STOPS = Set.of(AQUARIUM, SUFFOLKDOWNS, GOVCENTER, ORIENTHEIGHTS, STATE, REVERE, WONDERLAND,
            WOODISLAND, MAVERICK, AIRPORT, BEACHMONT, BOWDOIN);

    public static final Set<MbtaStop> ORANGE_STOPS = Set.of(OAKGROVE, GREENSTREET, DOWNTOWNCROSSING_WITH_ORANGE, STATE_WITH_ORANGE,
            STONYBROOK, MALDEN, WELLINGTON, ASSEMBLY, SULLIVAN, COMMUNITYCOLL, BACKBAY, MASSAVE, JACKSON, FORESTHILLS, RUGGLES,
            TUFTS, CHINATOWN, HAYMARKET, NORTHSTATION, ROXBURY);

    public static final Set<MbtaStop> RED_STOPS_WITH_ORANGE = Set.of(ALEWIFE, DAVIS, PORTER, HARVARD, CENTRAL, KENDALL,
            CHARLES, PARKSTREET, DOWNTOWNCROSSING_WITH_ORANGE, SOUTHSTATION, BROADWAY, ANDREW, JFK, SAVINHILL, FIELDSCORNER,
            SHAWMUT, ASHMONT, NORTHQUINCY, WOLLASTON, QUINCYCENTER, QUINCYADAMS, BRAINTREE);

    public static final Set<MbtaStop> BLUE_STOPS_WITH_ORANGE = Set.of(AQUARIUM, SUFFOLKDOWNS, GOVCENTER, ORIENTHEIGHTS,
            STATE_WITH_ORANGE, REVERE, WONDERLAND, WOODISLAND, MAVERICK, AIRPORT, BEACHMONT, BOWDOIN);


}
