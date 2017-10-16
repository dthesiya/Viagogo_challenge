# Viagogo_challenge
A java application containing two versions v1 and v2 to implement grid application having events at different coordinates.

## How to run
* go to src directory for base version

for base version,
* compile using 'javac v1/Driver.java'
* run using 'java v1.Driver'

for enhanced version,
* compile using 'javac v2/Driver.java'
* run using 'java v2.Driver'

### Scenario
* Program randomly generates seed data.
* Program operates in a 2D world.
* Each event has a unique numeric identifier (e.g. 1, 2, 3).
* Each event has zero or more tickets.
* Each ticket has a non-zero price, expressed in US Dollars.
* The distance between two points is computed as the Manhattan distance.

## V1 (having base requirements)
* Program operates in a world that ranges from <b>-10 to +10 (Y axis), and -10 to +10 (X axis)</b>.
* Program assumes that each co-ordinate can hold a <b>maximum of one event</b>.

## V2 (having enhancements, to answer the last two questions in instruction section in problem statement)
* Program operates in a world that ranges from <b>minX to maxX (X axis), and minY to maxY (Y axis)</b> where minX, maxX, minY and maxY are defined by grid manager.
* Program assures that each co-ordinate can hold <b>more than one event</b>.

## Assumptions
* User has a machine with java8 installed.
* User provides input correctly(e.g. <b>4,2</b> and not like 4,a2 or out of grid range coordinates)
* Event ids are generated like <b>int('xy')</b> in v1 and <b>int('xyi')</b> in v2 where i is index of event at same xy. So assumption made here is that no two randomly generated events can collide their coordinates in such a way that it may conflict with event ids(for example, 3720 can mean x=3, y=7 and i=20. It can also mean x=37, y=2 and i=0).
* In v1, it has 20 randomly generated events. One event has three classes of tickets and max ticket amout is $50.(all numeric parameters can be changed.)
* In v2, the world now ranges from -20 to 20 on both axis. It has total 25 randomly generated events considering that one place can have multiple events. It also has three classes of tickets for each event and max ticket amount is $50.(all numeric parameters can be changed.)
