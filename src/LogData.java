import java.sql.Timestamp;

/*
 * This is the Data which is to be logged into a File
 */

public class LogData implements Comparable<Object>{

	private String data;
	private Timestamp time;
	public LogData(String data, Timestamp time)
	{
		this.data = data;
		this.time = time;
	}
	public String toString()
	{
		return time.toString()+" : "+data;
	}
	public int compareTo(Object o) {
		LogData d = (LogData)o;
		if( this.time.before(d.time) )
		{
			return 0;
		}
		else if( this.time.equals(d.time))
		{
			return 1;
		}
		else
		{
			return 1;
		}
	}
}
