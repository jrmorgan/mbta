package mbta;

import mbta.client.MbtaClient;
import mbta.mapper.MbtaMapper;
import mbta.model.Mbta;
import mbta.model.MbtaRoute;
import mbta.model.MbtaStop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * The App class is the client-facing tool which prints information from the Mbta object.
 *
 * It initializes the query to the API with the MbtaClient and then uses the MbtaMapper to create the Mbta object.
 *
 * Then, it uses the Mbta object to print the objectives of the code challenge.
 * 1) Print all route names
 * 2) Print longest and shortest routes, along with their sizes
 * 3) Prompt the user for two stops and find the routes between them
 *
 * It could be considered to put the output console logic in an additional class with this class simply connecting to
 * that one.
 *
 * @author Jaina Morgan
 */

public class App {

    public static void printRouteNames(Mbta mbta) {

        System.out.println("***\nRoute names:");
        System.out.println("---");
        mbta.getRoutes().stream()
                .sorted(Comparator.comparing(MbtaRoute::getRouteName, Comparator.naturalOrder()))
                .forEach(route -> System.out.println(route.getRouteName()));
        System.out.println("***");
        System.out.println();
    }

    public static void printRouteInformation(Mbta mbta) {
        System.out.println("***\nRoute information:");
        System.out.println("---");
        MbtaRoute longest = mbta.findLongestRoute();
        MbtaRoute shortest = mbta.findShortestRoute();
        System.out.println("Longest route: " + longest.getRouteName() +
                "\nNumber of stops: " + longest.getLongestPath().size());
        System.out.println("---");
        System.out.println("Shortest route: " + shortest.getRouteName() +
                "\nNumber of stops: " + shortest.getShortestPath().size());
        System.out.println("---");
        System.out.println("Intersections:");
        mbta.findIntersections().stream().forEach(
                stop -> System.out.println(stop.getStopName() + ": " +
                        stop.getRouteNames().stream()
                                .map(route -> mbta.findRoute(route).getRouteName()).collect(Collectors.toSet())));
        System.out.println("***");
        System.out.println();
    }

    public static void askUserForStops(Mbta mbta) throws IOException {
        boolean finished = false;
        while (!finished) {
            System.out.println("***");
            System.out.println("Please enter first stop: ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String firstStopName = br.readLine();
            MbtaStop firstStop = mbta.findStop(firstStopName);
            while (firstStop == null) {
                System.out.println("Couldn't find stop. Please try again: ");
                firstStopName = br.readLine();
                firstStop = mbta.findStop(firstStopName);
            }
            System.out.println("---");
            System.out.println("Please enter second stop: ");
            String secondStopName = br.readLine();
            MbtaStop secondStop = mbta.findStop(secondStopName);
            while (secondStop == null) {
                System.out.println("Couldn't find stop. Please try again: ");
                secondStopName = br.readLine();
                secondStop = mbta.findStop(secondStopName);
            }
            System.out.println("---");
            System.out.println("Route found:");
            mbta.findRouteBetweenStops(firstStop, secondStop).forEach(route -> System.out.println(route.getRouteName()));
            System.out.println("***");
            System.out.println();
            System.out.println("Type 'Exit' to quit. Anything else will continue.");
            if (br.readLine().equalsIgnoreCase("Exit")) {
                System.out.println("Thank you.");
                finished = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        MbtaClient mbtaClient = new MbtaClient();
        Mbta mbta = new MbtaMapper().createMbta(mbtaClient.makeQuery());

        System.out.println("MBTA Coding Challenge");
        System.out.println();
        printRouteNames(mbta);
        printRouteInformation(mbta);
        askUserForStops(mbta);
    }
}
