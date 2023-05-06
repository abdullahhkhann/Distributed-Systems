import java.net.*;
import java.io.*;

public class LoadBalancer {
    //arg[0] = number of servers
    //arg[1] = server IP address

    public static void main(String[] args) {
        DatagramSocket loadBalancerSocket = null;
        DatagramSocket serverSocket = null;
        try {

            int numServers = Integer.parseInt(args[0]);

            // an array to store all server sockets 
            /*DatagramSocket[] sockets = new DatagramSocket[numServers];
            for (int i = 0; i < numServers; i++) {
                sockets[i] = new DatagramSocket();
            }*/
            
            serverSocket = new DatagramSocket();
            loadBalancerSocket = new DatagramSocket(50000);
            byte[] buffer = new byte[1000];
            System.out.println("Load balancer is running...");

            int serverIndex = 0;
            
            while (true) {
                // Receive request from client and forward to server
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                loadBalancerSocket.receive(request);
                
                InetAddress serverAddress = InetAddress.getByName(args[1]);
                int serverPort = 20000 + serverIndex;
                //DatagramSocket serverSocket = serverSocket;  //sockets[serverIndex];
                
                DatagramPacket serverRequest = new DatagramPacket(request.getData(), request.getLength(),
                												  serverAddress, serverPort);
                serverSocket.send(serverRequest);

                // Receive response from server and forward to client
                /*byte[] serverBuffer = new byte[1000];
                
                DatagramPacket serverResponse = new DatagramPacket(serverBuffer, serverBuffer.length);
                serverSocket.receive(serverResponse);
                //socket.receive(serverResponse);
                
                DatagramPacket reply = new DatagramPacket(serverResponse.getData(), serverResponse.getLength(), request.getAddress(), request.getPort());
                aSocket.send(reply);*/
                
                serverIndex = (serverIndex + 1) % numServers; // Server index is incremented to distribute workload evenly
            }

        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (loadBalancerSocket != null) loadBalancerSocket.close();
            if (serverSocket != null) serverSocket.close();
        }
    }

}
