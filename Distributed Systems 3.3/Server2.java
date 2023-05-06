import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server2 {

	public static void main(String[] args) {
		
		DatagramSocket serverSocket = null;
		long startTime;
		long sumExecutionTime = 0;
		int clientPort = 10000;
		
		try {
			serverSocket = new DatagramSocket(20001);
			byte[] buffer = new byte[1000];
			System.out.println("Server2 is up and running...");
			
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				serverSocket.receive(request);
				
				startTime = System.currentTimeMillis();  //Stamp starting time of the server execution
				
				//Receiving the password hash from the client and cracking it
				String passwordHash = new String(request.getData()).trim();
                String password = BruteForce.crackPassword(passwordHash);
                
                //Stamp the end time and calculate execution time of the process
                long endTime = System.currentTimeMillis();
                long executionTime = endTime - startTime;
                
                sumExecutionTime += executionTime; //Calculate sum of all execution times
                
                password += " " + executionTime;
                
				//Send reply to client
				DatagramPacket reply = new DatagramPacket(password.getBytes(), password.length(),
														  request.getAddress(), clientPort);
				
				System.out.println("Cracked password: " + password + " ms");
				System.out.println("--------------------------------------------");
				
				serverSocket.send(reply);
				
				//Calculate average execution time
				long avgExecutionTime = sumExecutionTime / 300;
	            System.out.println("Average execution time: " + avgExecutionTime + " ms");
			}
		}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
	 	}catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
		}finally {
			if(serverSocket != null) serverSocket.close();
		}
		
	}

}
