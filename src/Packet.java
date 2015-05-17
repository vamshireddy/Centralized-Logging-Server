import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Arrays;
/*
 * Static class with some utility functions related to the networking and packet manipulation.
 */
public class Packet {
	
	/*
	 * Three type of packets are sent by the client.
	 * REGISTER - sent when the client want to register with the logging server. Server will maintain the state for the client end point/
	 * LOG - Type of packet, sent when the client instructs the server to log something. Its followed by seqno and data
	 * DEREGISTER - remove the client state at the server.
	 */
	public static byte REGISTER = 1;
	public static byte LOG = 2;
	public static byte DEREGISTER = 3;
	
	public static int getSeqNo(byte[] arr)
	{
		byte[] seqarr = Arrays.copyOfRange(arr, 1, 5);
		ByteBuffer buff = ByteBuffer.wrap(seqarr);
		return buff.getInt();
	}
	
	public static long getIP(InetAddress addr)
	{
		byte[] iparr = addr.getAddress();
		final long IPAddress =
			      (((iparr [0] & 0xFF) << (3*8)) + 
			      ((iparr [1] & 0xFF) << (2*8)) +
			      ((iparr [2] & 0xFF) << (1*8)) +
			      (iparr [3] &  0xFF)) & 0xffffffffl;
		return IPAddress;
	}
}
