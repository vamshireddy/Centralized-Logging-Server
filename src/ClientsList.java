import java.util.HashMap;

/*
 * This will store the online client objects in a hash table
 */
public class ClientsList {	
	
	public static HashMap<Long, Client> clientshash;
	static
	{
		clientshash = new HashMap<Long, Client>();
	}
	
	public static ClientPortItem createClient(long ip, int port, int seqNo)
	{
		ClientPortItem portitem = null;
		Client c = clientshash.get(ip);
		if( c == null )
		{
			c = new Client();
			clientshash.put(ip, c);
			portitem = c.addPort(port, seqNo);
		}
		else
		{
			portitem = c.getPort(port);
			if( portitem != null)
			{
				portitem.seqNo = seqNo;
			}
			else
			{
				portitem = c.addPort(port, seqNo);
			}
		}
		return portitem;
	}
	
	public static ClientPortItem getClient(long ip, int port)
	{
		Client c = clientshash.get(ip);
		if( c == null )
		{
			return null;
		}
		ClientPortItem portItem = c.getPort(port);
		return portItem;
	}
	
	public static boolean removeClient(long ip, int port)
	{
		Client c = clientshash.get(ip);
		if( c == null )
		{
			return false;
		}
		else
		{
			if( c.removePort(port) == true )
			{
				if( c.getCount() == 0 )
				{
					clientshash.remove(c);
				}
			}
			else
			{
				return false;
			}
		}
		return true;
	}
	public static void showClients()
	{
		/*
		 * TODO write the display function
		 */
	}
}
