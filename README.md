# LoggingServer
Message Logging Server which runs on UDP protocol with reliability.

* It uses three threads, one thread for reading the UDP packets, one for writing the logs of the recevied packets to a file, and the last one for taking the user input.
* Common queue between reader and writer threads is implemented to share data.
