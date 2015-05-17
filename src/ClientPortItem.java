import java.sql.Timestamp;
/*
 * This is an object for a Client end point. There may be many end points from the same client(IP)
 */
class ClientPortItem
{
	int port;
	long seqNo;
	Timestamp lastTime;
	
	public ClientPortItem(int port, long seqNo)
	{
		this.port = port;
		this.seqNo = seqNo;
	}
}