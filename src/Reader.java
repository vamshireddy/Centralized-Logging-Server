import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.Arrays;
/*
 * Reader class, which reads from the network and writes the received messages to a queue.
 */
public class Reader implements Runnable {
	
	private DatagramSocket serverSock = null;
	// Duplicate count
	public int dupCount = 0;
	// Largest UDP Datagram size
	byte[] receiveData = new byte[65507];
	DatagramPacket packet = null;

	boolean running = true;
	
	public void cleanup()
	{
		serverSock.close();
		running = false;
	}
	

	public void run()
	{
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
			/*
			 * Clean the array
			 */
			Arrays.fill(receiveData, (byte)0);
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
			/*
			 * Get IP, port, packet Type, seqNo, packetLength
			 */
			int packetLength = packet.getLength();
			long ip = Packet.getIP(packet.getAddress());
			int port = packet.getPort();
			byte packType = receiveData[0];
			int packSeqNo = Packet.getSeqNo(receiveData);
			byte[] data = Arrays.copyOfRange(receiveData, 5, packetLength);
			
			/*
			 * Sanity check
			 */
			if( ip <= 0 || ( packType != 1 && packType !=2 && packType!=3) || packSeqNo <=0  )
			{
				System.out.println("Malformed packet");
				continue;
			}
			ClientPortItem clientPort = null;
			if( packType == Packet.REGISTER )
			{
				/*
				 * This will create a client and a port object, if it doesn't exists.
				 * If exists, it returns the object.
				 */
				clientPort = ClientsList.createClient(ip, port, packSeqNo);
				reply(receiveData);
			}
			else if( packType == Packet.LOG )
			{
				clientPort = ClientsList.getClient(ip, port);
				if( clientPort == null )
				{
					System.out.println("Can't log, client port not in list");
					continue;
				}
				if( packSeqNo == (clientPort.seqNo+1))
				{
					/*
					 * Its not a duplicate
					 * Get the current time and make a log string, store it in the queue.
					 */
					Timestamp time = new Timestamp(System.currentTimeMillis());
					Queue.Enqueue(time.toString()+":"+packet.getAddress()+":"+packet.getPort()+":"+new String(data));
					clientPort.seqNo++;
					clientPort.lastTime = time;
					reply(receiveData);
				}
				else
				{
					/*
					 * Its a duplicate. 
					 */
					dupCount++;
				}
			}
			else if( packType == Packet.DEREGISTER)
			{
				/*
				 * Remove the client port from the clients object
				 */
				ClientsList.removeClient(ip, packet.getPort());
				reply(receiveData);
			}
		}
	}
	
	public void reply(byte[] sendData)
	{
		/*
		 * Reply the same message with only type and sequence no (first 5 bytes)
		 */
		DatagramPacket packy = new DatagramPacket(sendData,0,5);
		packy.setAddress(packet.getAddress());
		packy.setPort(packet.getPort());
		try {
			serverSock.send(packy);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
	}
}