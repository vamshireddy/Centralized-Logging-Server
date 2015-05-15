import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.Timestamp;

/*
 * Reader class, which reads from the network and writes the received messages to a queue.
 */

public class Reader implements Runnable {
	
	private DatagramSocket serverSock = null;
	byte[] receiveData = new byte[1024];
	DatagramPacket packet = null;
	boolean running;
	
	public void cleanup()
	{
		serverSock.close();
		running = false;
	}
	
	public void run()
	{
		running = true;
		/*
		 * Create a UDP server socket to accept the incoming messages. 
		 */
		try {
			serverSock = new DatagramSocket(4444);
		} catch (SocketException e) {
			System.out.println("Can't bind to the port 4444");
			System.exit(0);
		}
		/*
		 * Accept the incoming UDP packets
		 */
		System.out.println("Waiting for the packets");
		while( running == true )
		{
			packet = new DatagramPacket(receiveData, receiveData.length);
			try
			{
				serverSock.receive(packet);
			}
			catch( IOException ioe )
			{
				System.out.println("Error in receiving the packets");
				ioe.printStackTrace();
				System.exit(0);
			}
			String sentence = new String(packet.getData());
			String ipPort = packet.getAddress().toString() + " : " + packet.getPort();
			sentence = sentence.trim();
			/*
			 * Write the received data with its time stamp to a queue.
			 */
			Timestamp time = new Timestamp(System.currentTimeMillis());
			Queue.Enqueue(new LogData(sentence + " : " + ipPort, time));
		}
	}
}
