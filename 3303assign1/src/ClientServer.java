import java.io.*;
import java.net.*;
import java.util.Arrays;
public class ClientServer {
	DatagramSocket socketData;
	DatagramPacket sendPacket, receivePacket;
	   DatagramSocket sendReceiveSocket;

	   public ClientServer()
	   {
	      try {
	         // Construct a datagram socket and bind it to any available 
	         // port on the local host machine. This socket will be used to
	         // send and receive UDP Datagram packets.
	         sendReceiveSocket = new DatagramSocket();
	      } catch (SocketException se) {   // Can't create the socket.
	         se.printStackTrace();
	         System.exit(1);
	      }
	   }
public void sendAndReceive(){
	 
	byte msg[] = new byte[100];//as shown in slide 59, one has to create the size of the byte array/msg
	//now we start the for loop, first one will be read then write and last one is invalid as we will change the second byte to 3
	//the if statements will handle the first two bytes,
	//then we will put the filename and then the 0 byte then the mode and finally put a 0 at the end
	for (int i=0;i<10;i++){
		if(i==0){msg[0]=0;
		msg[1]=1;
		}
		if(i==1){msg[0]=0;
		msg[1]=2;}
		if(i==2){msg[0]=0;
		msg[1]=1;}
		if(i==3){msg[0]=0;
		msg[1]=2;}
		if(i==4){msg[0]=0;
		msg[1]=1;}
		if(i==5){msg[0]=0;
		msg[1]=2;}
		if(i==6){msg[0]=0;
		msg[1]=1;}
		if(i==7){msg[0]=0;
		msg[1]=2;}
		if(i==8){msg[0]=0;
		msg[1]=1;}
		if(i==9){msg[0]=0;
		msg[1]=2;}
		if(i==10){msg[0]=0;
		msg[1]=3;}
	String filename="test.txt";
	byte file[]=filename.getBytes();
	//now we are going to use array copy, initially i was going to add the two byte arrays together but then i realized that the byte array msg already has 100 bytes so if we do not enter the filename bytes right after the first two set bytes, our send request will be WRONG!
	System.arraycopy(file, 0, msg, 2, file.length);
	//now we are going to put our third part of the byte in which is 0, to do this first remember that the first two bytes have been already used, so do the length of the filename+2bytes+1byte to have the array element at which we insert our 0!
	msg[file.length+2]=0;
	String mode="netascii";
	byte modee[]=mode.getBytes();
	System.arraycopy(modee, 0, msg, file.length+3, modee.length);
	//finally add our last byte which is 0!
	msg[file.length+4+modee.length]=0;
	//the stuff that is in the packet
	System.out.println(Arrays.toString(msg));
	
	
	
	  try {
	         sendPacket = new DatagramPacket(msg, msg.length,
	                                         InetAddress.getLocalHost(), 68);
	      } catch (UnknownHostException e) {
	         e.printStackTrace();
	         System.exit(1);
	      }
	  

      System.out.println("Client: Sending packet:");
      System.out.println("To host: " + sendPacket.getAddress());
      System.out.println("Destination host port: " + sendPacket.getPort());
      System.out.println("Length: " + sendPacket.getLength());
      System.out.print("Containing: ");
      System.out.println(new String(sendPacket.getData())); // or could print "s"

      // Send the datagram packet to the server via the send/receive socket. 

      try {
         sendReceiveSocket.send(sendPacket);
      } catch (IOException e) {
         e.printStackTrace();
         System.exit(1);
      }System.out.println("Client: Packet sent.\n");
	  
	  
	  
	  
	  
	  byte data[] = new byte[100];
      receivePacket = new DatagramPacket(data, data.length);
      try {
          // Block until a datagram is received via sendReceiveSocket.  
          sendReceiveSocket.receive(receivePacket);
       } catch(IOException e) {
          e.printStackTrace();
          System.exit(1);
       }
      // Process the received datagram.
      System.out.println("Client: Packet received:");
      System.out.println("From host: " + receivePacket.getAddress());
      System.out.println("Host port: " + receivePacket.getPort());
      System.out.println("Length: " + receivePacket.getLength());
      System.out.print("Containing: ");

      // Form a String from the byte array.
      String received = new String(data);   
      System.out.println(received);
      
	}//now we are going to close the socket and we are done!!
	 sendReceiveSocket.close();}
public static void main(String args[])
{
   ClientServer c = new ClientServer();
   c.sendAndReceive();
}
}

