# LoggingServer
Access logging server which runs on UDP

* It uses three threads, one thread for reading the UDP packets, second one for writing the logs of the recevied packeets to a file, and the last one for taking the user input.
* There is a common queue between reader and writer threads and is synchronized.
