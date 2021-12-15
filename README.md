## MBTA Code Challenge

### Environment

This code was written and compiled with Java 11. I used Jackson for JSON parsing, and JUnit for testing. Dependencies are
managed with Maven.

#### Install and Run

The full package with dependencies bundled can be used via `maven package`. The `jar` can then be run from the command line
with `java -cp target/Mbta-1.0-SNAPSHOT-jar-with-dependencies.jar mbta.App`. Tests can be run on their own with `mvn test`.

#### API Key

I did get an API key for the MBTA API, but this code will run without one defined. An environment variable `API_KEY` can 
be set if desired. Since there is only one call to the API from the app, rate limiting is not an issue.

#### Challenge Requirements

The three components of this project asked to query the MBTA API for light/heavy rail and then show various information.
1) The `long_name` of each route
2) The longest and shortest routes, as well as their respective lengths, and then also all stops that are on more than one route
3) Finding the routes between stops input by the user, names only (e.g. between Downtown Crossing and Wonderland, it could
show Orange Line, Blue Line)

Note that for reasons outlined in ANALYSIS.md, the Green Line branches are all considered separate routes by this application. 
This means that those stops that are on multiple branches of the Green Line and nothing else will show up under the #2 requirement.
I began to think about how to eliminate them by keeping track of the related name of the `line` object from the MBTA API, 
which is how the API keeps track of this grouping, however that feature is not currently built.

#### Additional Notes

This implementation also assumes the integrity of the data it gets from the API is good and does not check for malformed data.
Further error handling could be improved upon and tested. It minimally makes sure the response code is 200 before reading
the JSON tree.

Refactoring is always a work in progress, and I am still working to improve the code.

-----

Further analysis/considerations of the construction of the code can be found in ANALYSIS.md.
