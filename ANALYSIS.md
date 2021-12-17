### Considerations

#### Strategy

Given the static nature of the data in this project, and the relatively small size, my thoughts were to load all the JSON
data I needed into memory as objects and then manipulate them rather than having to repeatedly query the MBTA API.

#### Querying the Data

I spent a bunch of time trying to figure out the optimal query/ies to perform to get the data necessary for this challenge.
The proposal was to start with one of the following two queries:

* https://api-v3.mbta.com/routes
* https://api-v3.mbta.com/routes?filter[type]=0,1

Only the second query filters to only light/heavy rail as specified, so I wasn't sure whether the first one was supposed
to be something else, or whether it was implying that I, as the receiver, should filter it. Thus, I started with the second
query and worked from there. This query returns information on what the MBTA API considers routes. It separates the various
Green Line branches, but keeps the Red Line branches to Ashmont/Braintree as a single entity (Mattapan is also its own route).

From there, I had to figure out what information I needed to get to put together the information as requested. There were
some things I knew I didn't need - direction, schedules, or any unusual changes in the routes. I just needed to know a typical
path from each line as it looks on the T map. The API contains many options, and I had to narrow things down to what I needed.
Ultimately, I figured out that the chain I needed was from `routes` to `route_patterns` to `trips` to `stops`.

First I tried writing a second query for route patterns based on the route IDs pulled from the original query. With route patterns,
I could filter by direction and then manually filter for the right typicality (though could sort by it), and get the trip IDs.
From there, I wrote a third query for trips and included the stops. So I reached the bottom level. I ran into a weird issue where
a trip was coming up that was actually a shuttle bus route - but it was somehow getting triggered by the query since it is
a temporary replacement while the Green Line is under construction. Then I thought about including the route pattern on the
first query, but even though I could filter on `direction_id`, that only applies to the `route` object, and doesn't seem
to apply to the `included` items.

After all of this, with a bit more understanding, I read a bit more on the developer site and realized I could add nested
includes. So I was able to collapse everything back into one query, even though I still had the issue with the `direction_id`, 
but at least I could then filter the route patterns and then the trips myself in order to set up my objects.

#### JSON Parsing

The MBTA API uses the JSON API and thus uses the `data`, `attributes`, `included`, etc., standards. The intention of this
exercise was to avoid a library that would just do everything for me (including linking includes), so I wanted to pick just
a standard JSON parser and perform the mapping somewhat manually. I wanted to be able to pinpoint the data I got from the parser
as I really didn't need most of it, and also wanted to be able to consolidate parts of the data (as explained below with
the stop data in particular). A direct auto-mapping didn't seem to make sense. So I went with Jackson, and while more heavyweight
than what I had intended originally, it allowed me to read the tree easily and get exactly what I needed. I didn't have to
use all the functionality. If I were using this in a more practical sense, I definitely would have seen if the JSON API libraries
worked better - but really, for what I was trying to do, and keep everything immutable, it was reasonably concise. I tried
GSON at first, but being able to pass in a `JsonPointer` to Jackson was much more efficient.

#### Project Organization and Class Structure

My first thought here was to have an overarching object that contained the set of routes and set of stops, and then have
the stops link back to the routes and the routes hold the stops in a list to keep the order. I realized quickly that the Red
Line would not allow for this, and added the path object to keep the order of the branches, and to allow an accurate count
of the length of a route. However, because I really only needed each path once, and direction didn't matter, I was able to
skip over the other intermediary object, `route_pattern`, that the MBTA API provides. I only cared about those as far as
eliminating unusual routes that may be abbreviated and thus duplicated, and just to pick typical ones that contained every
stop on each route, but for the purposes of this exercise, it was overkill to keep any additional information about them.
However, I did run into an issue with my hope for bidirectional references, due to my hope of keeping things as immutable
as possible (explained below), but I came up with a solution that I was relatively happy with, though may have issues if
this exercise's requirements were extended.

Other than the four models to hold the objects created from JSON, I created a client which connects to the API, a mapper
which parses the data, and then the main class which provides the console interface to the user and presents the requested
information.

I thought about keeping a flat structure for the class files since it isn't a large project, but ultimately broke it up
a bit. In such a small project, the "by feature" organization didn't really make much sense, as all of it basically is
one feature. However, after splitting it into the client, mapper, and models ended up being functional and not really simply
by layer, as the models all work together to provide the business logic.

In terms of class structure, I also thought about the return values from some methods in the `Mbta` class and whether I
needed to return the objects or just Strings, but ultimately I think it makes sense to keep the return values as objects.
Sure, my application consumed and then presented them as Strings, but if I was able to reuse the method later, it would make
sense to keep the object rather than deconstructing it. I've also had some thoughts about eliminating the IDs from the
`MbtaRoute` and `MbtaStop` objects, as it seems like maybe it is needlessly complicating them when I don't really need the
IDs ultimately for the purposes of this exercise. That being said, the IDs are useful if I had to make additional queries
to the API, so maybe it makes sense to keep them on the object in case further information was going to be retrieved. I tried
to draw a fine line between eliminating information I don't need, but keeping enough around in case I decided to write more
features myself. That also goes along with holding the route ID rather than the route name within the `MbtaStop` object - it
may not make much of a difference here since it's not like it's a query on a database with an index. I could have gone either way
most likely.

I thought about making the main App class do very little and make even one more class for the console UI. However, given the scope of the
project, I thought maybe that was overkill. I also considered breaking out some of the logic from the `Mbta` object itself, and having 
it just contain the objects, rather than some of the methods performed - like the longest and shortest route calculations. Is that a job 
for the `Mbta` object itself, or is it up to the consumer to care and figure it out based on the object? Maybe if I had broken out the console 
interface into another class, it would have possibly made sense to have it do that calculation as it could be considered presentation of
the loaded data, but it didn't seem like it was the job of the main class to do that. Also, this keeps it more in line with OOP to calculate 
these items within the object.

#### Immutability

Trying to keep with the general principle of creating classes that are as immutable as possible, I worked to achieve that
within this program. The various objects created from the query have no reason to change anyway, and the subsequent requirements
of the program required only reading from and analyzing them. The builder pattern worked great for the routes and paths, as
I added paths and stops, respectively, to each, but left a standard constructor for the stops. I also then refactored to allow
all stops to be added in a list to the path to work better with the stream paradigm, rather than relying on side effects.
However, the whole thing created a challenge as I had intended to be able to have bidirectional links to be able to get back 
to paths/routes from the stops easily to be able to find how two stops connect. After all, I saw what a pain it was to calculate 
this from the top level when querying from the JSON - so why recreate the problem, especially if calling the path calculation method
multiple times in a row. However, I considered what information I actually needed to make this calculation if I started with the stops. 
I realized that I just needed the route names, rather than the objects, and thus attached a set of Strings which hold that information. 
That allowed me to immutably create each stop and then build the paths, routes, and then the single Mbta object itself. I also could have 
considered recalculating the stop to route information within the creation of the `Mbta` object, but I had already just done the same thing
while parsing that it would have seemed redundant. I could consider passing in a map of the information to the `Mbta` object or something though, 
allowing the `MbtaStop` information to be free of the link, but not sure that is the way to go either. I didn't love that I had to store several additional 
objects in memory during the process since there was no other way to associate stops otherwise, especially when I was also trying to only create a 
single stop object for those that occur on multiple routes (making Park Street, for instance, have 5 at least different JSON objects returned from 
the query - Red, Green B, Green C, Green D, and Green E). Since it was easy enough to put together the route names in these cases before the creation
of the route object, I then created thestop object with just that information, even though it required holding onto that information in memory in a 
different map until I knew I had gone through each route to check for duplicates. It also unfortunately forced me to cycle through the trips
twice, once to find the routes for each stop, and then a second to create the `MbtaStop` object itself. However, that is still a `O(n)` operation 
as it is still a fixed number of loops.

#### Search Algorithm for Finding the Path

I wanted to find a path that makes some degree of sense, especially since many paths between two stops only require a single
transfer. All the main routes connect to each other except Red and Blue, and then Mattapan. So the longest route would be
a Blue Line stop to the Mattapan line, which is only 3 transfers. That being said, I didn't want to assume anything about
the length of the path and wanted to keep it generalized despite knowing exactly what the subway map looks like.

So I went with a modified breadth-first-search, to make the trip take the fewest transfers. A depth-first-search did not
make sense here, especially given the fact that the Green Line was broken up, unless I was able to group them. I was hesitant
to do this though, as some Green Line trains do not cover all stops (more thoughts on this below). It would be silly to think
that it would return a path that switched to each branch of the Green Line when only one was necessary. I thought a lot about
the piece about being able to backtrack in the search, but especially with a small set of data, modifying the queue to hold
the current paths worked well. I originally had a much more convoluted strategy to accomplish this, but I wasn't saving
anything by doing that - I was holding even more objects in memory for something that I doubt was faster either. I thought
about holding the parent in memory for backtracking, but it also seemed like overkill. This is especially relevant because
I was only using the routes and not the stops themselves - so I essentially only had edges of a graph and not vertices.
That, along with the fact I had created the transfer map one time upon initializing the object, leads to the search algorithm
performing very well (`O(|E|)`) given that there are only a handful of routes.

#### Testing

I thought hard about the best way to test the different components. Admittedly my test writing skills are still developing,
but I wanted to make sure I tested at least the main functionality of the `Mbta` and `MbtaMapper` classes. Since the JSON
coming in from the API is far more complicated than what I am using, I decided to query a subset of the data and put it into a
file to test the mapper, and then I programmatically created most of the test objects to compare against, and then reused
those objects to also test the `Mbta` class itself. I thought it would otherwise be very difficult and time-consuming to create
the best JSON to test on, since I also wanted to test the filtering the mapper does, and thus didn't want to just hand it
only data it would eventually turn into the POJOs. It maybe was not the most ideal way to test this out, but worked out alright.

#### Other Thoughts

At first, I wondered why the Green Line's various branches were considered separate routes, but that the standard Ashmont/Braintree
lines of the Red Line were considered the same route (but not Mattapan). Then it occurred to me that the main distinction
is that transfers are required between some Green Lines to get to the end, and likewise with Mattapan. Only certain Green
Line trains go north past Government Center, so others would still require a change to get there. With the Red Line, that
is not required except between branches, which also needs a change in direction. I suspect that if all Green Line trains
went to the end of the line and only differed in the branch part, it too would be seen as one route. The MBTA API has a `line`
object which represents this, and groups the Green Line together this way. I thought about including this information when
finding the path between stops, but since it would require a transfer between Green Line routes I kept it as MBTA represents
it. However, when I did the path finding, I left the Red Line calculation as is, and it does not show that it requires a change
if switching between branches.

After all of this, I've started to wonder if I needed the path objects at all, even though I thought I did. I did need to keep
separate paths for the Red Line branches, otherwise I would have no idea what the actual longest path is - the longest path
on the Red Line does not constitute of all the stops contained within. However, maybe I could have just represented it as a set
of lists as I don't hold other information about them at this time. I didn't want to lose the extensibility of the application though,
and then have to recreate an object. There's a reason the API has even more layers than I have created, as there are even more moving
parts than I have used in this project.

Thank you for reading.
