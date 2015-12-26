import java.io.*;
import java.net.*;


public class ImmediateHost {
 DatagramPacket sendPacket, receivePacket;
	      DatagramSocket sendSocket, receiveSocket;
	public ImmediateHost(){
		
		      try { 
		         // Construct a datagram socket and bind it to any available 
		         // port on the local host machine. This socket will be used to
		         // send UDP Datagram packets.
		         sendSocket = new DatagramSocket(68);

		         // Construct a datagram socket and bind it to port 5000 
		         // on the local host machine. This socket will be used to
		         // receive UDP Datagram packets.
		         receiveSocket = new DatagramSocket();
		         
		         // to test socket timeout (2 seconds)
		         //receiveSocket.setSoTimeout(2000);
		      } catch (SocketException se) {
		         se.printStackTrace();
		         System.exit(1);
		      } 
		   }
	public void SendRecieve(){
		for (;;){
			 byte data[] = new byte[100];
		      receivePacket = new DatagramPacket(data, data.length);
		      System.out.println("Server: Waiting for Packet.\n");

		      // Block until a datagram packet is received from receiveSocket.
		      try {        
		         System.out.println("Waiting..."); // so we know we're waiting
		         receiveSocket.receive(receivePacket);
		      } catch (IOException e) {
		         System.out.print("IO Exception: likely:");
		         //System.out.println("Receive Socket Timed Out.\n" + e);
		         e.printStackTrace();
		         System.exit(1);
		      }
		      String received = new String(data);   //print recieved
		      System.out.println(received);
		      
		      

		         sendPacket = new DatagramPacket(data, receivePacket.getLength(),
		                                        receivePacket.getAddress(), 69);
		        
		         System.out.println("Simulator: sendingg.");
		         System.out.println("To host: " + sendPacket.getAddress());
		         System.out.println("Destination host port: " + sendPacket.getPort());
		         System.out.println("Length: " + sendPacket.getLength());
		         System.out.println("Containing: ");
		         String sending = new String(data);   //print recieved
			      System.out.println(sending);
			      
			      
			      byte dataagain[] = new byte[100];
			      receivePacket = new DatagramPacket(dataagain, dataagain.length);
			      System.out.println("Server: Waiting for Packet.\n");

			      // Block until a datagram packet is received from receiveSocket.
			      try {        
			         System.out.println("Waiting..."); // so we know we're waiting
			         receiveSocket.receive(receivePacket);
			      } catch (IOException e) {
			         System.out.print("IO Exception: likely:");
			         System.out.println("Receive Socket Timed Out.\n" + e);
			         e.printStackTrace();
			         System.exit(1);
			      }

			      // Process the received datagram.
			      System.out.println("Server: Packet received:");
			      System.out.println("From host: " + receivePacket.getAddress());
			      System.out.println("Host port: " + receivePacket.getPort());
			      System.out.println("Length: " + receivePacket.getLength());
			      System.out.print("Containing: " );

			      // Form a String from the byte array.
			      String recieved = new String(data);   
			      System.out.println(recieved + "\n");  
			      
			      

			      sendPacket = new DatagramPacket(data, receivePacket.getLength(),
			                               receivePacket.getAddress(), receivePacket.getPort());

			      System.out.println( "Server: Sending packet:");
			      System.out.println("To host: " + sendPacket.getAddress());
			      System.out.println("Destination host port: " + sendPacket.getPort());
			      System.out.println("Length: " + sendPacket.getLength());
			      System.out.print("Containing: ");
			      System.out.println(received); // as we are sending back the same thing
			      // could also say:
			      // System.out.println(new String(sendPacket.getData()));
			      
			      // Send the datagram packet to the client via the send socket. 
			      try {
			         sendSocket.send(sendPacket);
			      } catch (IOException e) {
			         e.printStackTrace();
			         System.exit(1);
			      }

			      System.out.println("Server: packet sent");

			      // We're finished, so close the sockets.
			      sendSocket.close();
			      receiveSocket.close();
			   }
			      
			      
			      
		      
		}
	
	 public static void main(String args[])
	   {
	      ImmediateHost a = new ImmediateHost();
	      a.SendRecieve();
	   }
		
	}
	
	

