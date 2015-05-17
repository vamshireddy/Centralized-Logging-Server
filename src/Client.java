import java.util.HashMap;
import java.util.Set;

/*
 * Clients are represented using this class objects and stored in the clients list.
 */
public class Client {
	
	private HashMap<Integer, ClientPortItem> portsList;
	
	public Client()
	{
		portsList = new HashMap<Integer, ClientPortItem>();
	}
	
	public int getCount()
	{
		return portsList.size();
	}
	
	public ClientPortItem getPort(int port)
	{
		ClientPortItem p = (ClientPortItem)portsList.get(port);
		return p;
	}
	
	public ClientPortItem addPort(int port, int seqNo)
	{
		ClientPortItem portitem = new ClientPortItem(port, seqNo);
		portsList.put(port, portitem);
		return portitem;
	}
	
	public boolean removePort(int port)
	{
		portsList.remove(port);
		return true;
	}
	
	public Set<Integer> listPorts()
	{
		return portsList.keySet();
	}
}
