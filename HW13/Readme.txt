HW 13: MBTA Schedular (Make your own)

- Race Condition that can happen in the train scheduling is
a) When multiple threads are running/sending different information about upcoming train such as train name, arriving platform, arrival time.
b) Such inconsistent information can confuse the passenger and worse can cause delays/accidents of the train.

MBTASchedule.java class not a thread-safe class and is displaying inconsistent information:
                                                Red Line:
                                                Arriving in: 3min , on platform: JFK/UMass - 01
                                                Arriving in: 5min , on platform: JFK/UMass - 01

- To avoid such race conditions, we revised the MBTASchedule class to be thread-safe with 2 - step thread termination process.
a) displaySchedule and trainScheduler methods are revised to be thread-safe using Reentrant lock surrounding with try-finally blocks;
b) implemented 2-step thread termination using volatile boolean flag done and join().

This makes the MBTAScheduleSafe.java to be thread safe and preventing the race conditions as explained/shown above,
with which we get only a consistent output/information:
                                                Red Line Arriving in:
                                                Arriving in: 5min , on platform: JFK/UMass - 01