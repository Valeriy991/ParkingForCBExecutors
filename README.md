# ParkingForCBExecutors
Test task in the development department of the RCR "Kazan"

Note:
Create an application that emulates car parking. The parking lot can accommodate a finite number of cars. The area of passages between the rows, as well as the distance between places, is not taken into account. We assume that the entire parking area is occupied by places between which cars move by teleportation. Cars can be of two types: cars (occupy one place) and trucks (occupy 2 places).

Exercise:
Parking Workflow: When the application starts, the user enters the following parameters:
- number of parking spaces
- the maximum length of the queue of cars waiting to enter the parking lot
- interval of generation of incoming cars in seconds
- interval of generation of outgoing cars in seconds

After entering the parameters, the application starts to generate input cars and put them in the queue. The type of car is chosen arbitrarily, but taking into account the fact that there should be fewer trucks. In other words, trucks are generated less often. The generation frequency uses a user-defined interval. That is, if we have an interval T1, then at some random time between this interval, a car is generated in the input queue. Similarly, the removal of cars from parking spaces. That is, if we have an interval for removing a car from the parking lot T2, then at an arbitrary moment between this interval, an arbitrary car is selected from the parking lot and removed.
Each car has a unique id. It can be of any type, the main thing is uniqueness.
Adding a car to the input queue and leaving the parking lot must occur independently of each other.
Every 5 seconds, parking reports its statistics in the form:
- Free places: X
- Places occupied: Y (of which there are so many cars and so many trucks)
- Cars waiting in line: Z

Event information:
When adding a car to the queue, the parking lot reports "A car/truck with id = <car ID> has queued for entry."
When moving a car to a parking lot, the parking lot reports "A car/truck with id = <car ID> has parked."
When a car leaves the parking lot, it informs about this "Car / truck with id = <car ID> left the parking lot."

How parking behaves in different situations:
- If there are no seats left, then the generation into the input queue does not stop.
- If the input queue has reached the specified maximum, then carmageddon occurs and the parking exits with some kind of "alarm" message. Exiting the application.

The application must have a console interface, no GUI is required. The use of subd and external files is not required, all data is stored in memory. Data structures and streaming tools of your choice. Java version 8 is expected to be used for writing code. The task must be submitted as a project for Intellij Idea.
