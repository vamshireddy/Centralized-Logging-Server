# LoggingServer
Message Logging Server which runs on UDP protocol with reliability.
## Features
* It can recieve logs from multiple clients at the same time and can write them to the backing store.
* Logs are not written as soon as they reach the server, instead they are queued on the respective client queues. Later, the daemon will dequeue and write the logs to the disk.
* It uses three threads, one thread for reading the UDP packets, one for writing the logs of the recevied packets to a file, and the last one for taking the user input.
* Common Queue between reader and writer threads is implemented to share data.
* It orders all the logs according to the sequence numbers present in the messages and stores the timestamp.

## How to run
#### Using Eclipse
1. Add this project repository as a project in Eclipse. 
2. Then compile it and run.

#### Without Eclipse
1. Compile all of the files in the repo together using the command `javac <file_names>`
2. Run the Master class by this commmand `java Master`

## Protocol
* REGISTER message - it is sent by the client along with the sequence number to set up the state on the server. Server creates a data stucture with this sequence number and expects the logs. 
* DEREGISTER message - it is sent by the client to remove its state on the server. This is usually sent when the client shutsdown.
* LOG message - it is sent by the client along with the message to be logged.

## Log format
TIMESTAMP : IPADDRESS : PORT : MSG
