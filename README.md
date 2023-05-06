# Distributed-Systems
This repo contains all my implementation for the course Distributed Systems  

## Distributed Systems 3.1

Created a Client-Server distributed system, where they both communicate with each other. The client sends the hash of a password to the server, and the server cracks it using brute force and sends it back to the client.

![Distributed Systems 3.1](https://github.com/abdullahhkhann/Distributed-Systems/blob/e9c38e91d5c72e65cc6f447284444f4b5e364764/Distributed%20Systems%203.1/image.png)  

## Distributed Systems 3.2

Extended the Client-Server from 3.1.

***<u>Client</u>***:  
- Added multiple clients sending multiple requests to the server.  
- Password is now randomly generated for each request, rather than having a fixed password.  
![Distributed Systems 3.2](https://github.com/abdullahhkhann/Distributed-Systems/blob/48df6b66d675df22e3bfafce165a18a4a1063bd5/Distributed%20Systems%203.2/Client.PNG)  

***<u>Server</u>***:  
- Server now calculates amount of time each request takes, in milliseconds, and sends it back to the client along with the cracked password.  
- Server now calculates average time taken for all the requests (passwords to be cracked).  
![Distributed Systems 3.2](https://github.com/abdullahhkhann/Distributed-Systems/blob/48df6b66d675df22e3bfafce165a18a4a1063bd5/Distributed%20Systems%203.2/Server.PNG)  

***<u>BruteForce</u>***:
- Added the function to generate a random password.  

In conclusion, made a graph to compare the average time it takes as we increase the number of requests and clients running on 1 server. All passwords are 3 characters.  
![Distributed Systems 3.2](https://github.com/abdullahhkhann/Distributed-Systems/blob/7208d52aaa6c9d601c9f64058429f629d7745f70/Distributed%20Systems%203.2/image.png)  

## Distributed Systems 3.3

Added a load balancer to distribute the load to 3 servers in a round-robin fashion, where the load balancer receives the requests and distributes them to the servers and the servers then process the request and reply back to the client directly. This makes it a 3-tier client server architecture and makes the system scalable.  

Computed the average execution time for each server.  
***Server 1***:  
![Distributed Systems 3.3](https://github.com/abdullahhkhann/Distributed-Systems/blob/e9c38e91d5c72e65cc6f447284444f4b5e364764/Distributed%20Systems%203.1/image.png)  
***Server 2***:  
![Distributed Systems 3.3](https://github.com/abdullahhkhann/Distributed-Systems/blob/e9c38e91d5c72e65cc6f447284444f4b5e364764/Distributed%20Systems%203.1/image.png)  
***Server 3***:  
![Distributed Systems 3.3](https://github.com/abdullahhkhann/Distributed-Systems/blob/e9c38e91d5c72e65cc6f447284444f4b5e364764/Distributed%20Systems%203.1/image.png)  

In conclusion, we then analysed the different execution time and compared them.  
![Distributed Systems 3.3](https://github.com/abdullahhkhann/Distributed-Systems/blob/e9c38e91d5c72e65cc6f447284444f4b5e364764/Distributed%20Systems%203.1/image.png)  
