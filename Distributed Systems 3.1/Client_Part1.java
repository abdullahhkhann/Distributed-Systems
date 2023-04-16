import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client_Part1 {
	
	static InetAddress serverAddress;
    static int serverPort;
    static DatagramSocket clientSocket = null;

	public static void main(String[] args) {
		
		try {
			
			//Set password
			String password = "aB 3";
			System.out.println("Set password: " + password);
			
			//Calculate hash
			String hash = BruteForce.hash(password);
			System.out.println("Password hash: " + hash);
			
			//Send hash to server
			clientSocket = new DatagramSocket();
			byte[] hashBytes = hash.getBytes();
			serverAddress = InetAddress.getByName(args[0]);
            serverPort = 20000;
			
			DatagramPacket request = new DatagramPacket(hashBytes, hashBytes.length, serverAddress, serverPort);
			
			clientSocket.send(request);
			
			//Receive cracked password from server
			byte[] buffer = new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			
			clientSocket.receive(reply);
			
			String crackedPassword = new String(reply.getData(), 0, reply.getLength());
			System.out.println("Cracked password: " + crackedPassword);
			
		}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
	    }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
	    }finally { 
	    	if(clientSocket != null) clientSocket.close();
	    	}

	}

}
