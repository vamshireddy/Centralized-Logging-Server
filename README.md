# LoggingServer
Message Logging Server which runs on UDP protocol with reliability.
## Features
* It can recieve logs from multiple clients at the same time and can write them to the backing store.
* Logs are not written as soon as they reach the server, instead they are queued on the respective client queues. Later, the daemon will dequeue and write the logs to the disk.
* It uses three threads, one thread for reading the UDP packets, one for writing the logs of the recevied packets to a file, and the last one for taking the user input.
* Common Queue between reader and writer threads is implemented to share data.
