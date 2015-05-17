import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * Queue data structure is the common place for both reader and writer thread to share data. Reader thread is a producer, which reads the log
 * from the socket and enqueues it to the queue. Writer thread will dequeue and write it to a file.
 */
public class Queue {

	private static BlockingQueue<String> q;
	static
	{
		q = new ArrayBlockingQueue<String>(100000);
	}
	public static void Enqueue(String data)
	{
		try {
			q.put(data);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String Dequeue()
	{
		try {
			return Queue.q.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static int length()
	{
		return q.size();
	}
}
