import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Server_Part1 {

	public static void main(String[] args) {
		
		DatagramSocket serverSocket = null;
		
		try {
			serverSocket = new DatagramSocket(20000);
			byte[] buffer = new byte[1000];
			
			while (true) {
				DatagramPacket request = new DatagramPacket(buffer, buffer.length);
				serverSocket.receive(request);
				
				String passwordHash = new String(request.getData()).trim();
                String password = BruteForce.crackPassword(passwordHash);
				
				DatagramPacket reply = new DatagramPacket(password.getBytes(), password.length(),
														  request.getAddress(), request.getPort());
				
				serverSocket.send(reply);
			}
		}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
	 	}catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
		}finally {
			if(serverSocket != null) serverSocket.close();
		}

	}

}
