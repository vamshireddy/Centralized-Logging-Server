import java.util.PriorityQueue;


public class Queue {
	private static java.util.Queue<LogData> q;
	static
	{
		q = new PriorityQueue<LogData>();
	}
	public static void Enqueue(LogData data)
	{
		synchronized (q) {
			Queue.q.add(data);
		}
	}
	public static LogData Dequeue()
	{
		synchronized (q) {
			return q.remove();
		}
	}
	public static int length()
	{
		return q.size();
	}
}
