import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Random;

public class Client1 {
	
	static InetAddress serverAddress;
    static int serverPort;
    static DatagramSocket clientSocket = null;

	public static void main(String[] args) {
		
		try {
			
			clientSocket = new DatagramSocket(10000);
			
			//Send multiple requests
			for(int i = 0; i < 300; i++) {
				
				//Generate random password
				int passwordLength = randomInt();
				String password = BruteForce.generateRandomPassword(passwordLength);
				System.out.println("Set password: " + password);
				
				//Calculate hash
				String hash = BruteForce.hash(password);
				System.out.println("Password hash: " + hash);
				
				
				//Send hash to server
				//clientSocket = new DatagramSocket();
				
				byte[] hashBytes = hash.getBytes();
				serverAddress = InetAddress.getByName(args[0]);
	            //serverPort = 20000;
				serverPort = Integer.parseInt(args[1]);
				
				DatagramPacket request = new DatagramPacket(hashBytes, hashBytes.length, serverAddress, serverPort);
				
				clientSocket.send(request);
				
				//Receive cracked password from server along with server execution time
				byte[] buffer = new byte[1000];
				DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
				
				clientSocket.receive(reply);
				
				String crackedPassword = new String(reply.getData(), 0, reply.getLength());
				
				System.out.println("Cracked password: " + crackedPassword + " ms");
				System.out.println("--------------------------------------------");
			
			}
			
		}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
	    }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
	    }finally { 
	    	if(clientSocket != null) clientSocket.close();
	    	}

	}
	
	//Function to generate random integer between 1 and 3
	private static int randomInt() {
		Random random = new Random();
	    int min = 1;
	    int max = 3;
	    
	    return random.nextInt((max - min) + 1) + min;
	}
	

}


