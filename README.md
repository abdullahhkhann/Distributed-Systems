# Distributed-Systems
This repo contains all my implementation for the course Distributed Systems  

## Distributed Systems 3.1

Created a Client-Server distributed system, where they both communicate with each other. The client sends the hash of a password to the server, and the server cracks it using brute force and sends it back to the client.

![Distributed Systems 3.1](https://github.com/abdullahhkhann/Distributed-Systems/blob/e9c38e91d5c72e65cc6f447284444f4b5e364764/Distributed%20Systems%203.1/image.png)  

## Distributed Systems 3.2

Extended the Client-Server from 3.1.
<u><i>Client</u></i>: - Added multiple clients sending multiple requests to the server.  
                      - Password is now randomly generated for each request, rather than having a fixed password.  
<u><i>Server</u></i>: - Server now calculates amount of time each request takes, in milliseconds, and sends it back to the client along with the cracked password.  
                      - Server now calculates average time taken for all the requests (passwords to be cracked).  
<u><i>BruteForce</u></i>: Added the function to generate a random password.  

In conclusion, made a graph to compare the avearge time it takes as we increase the number of requests and clients running on 1 server. All passwords are 3 characters.  

