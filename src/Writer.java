import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Writer implements Runnable{
	private static long logCount = 0;
	private String fileName;
	private File file;
	FileWriter fileWriter;
    boolean running;
    
	public Writer()
	{
		this("clientslog.txt");
	}
	
	public Writer(String f)
	{
		fileName = f;
		file = new File(fileName);
		if(!file.exists()){
			try {
				file.delete();
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
		try {
			fileWriter = new FileWriter(file.getName(),true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(0);
		}
		running = true;
	}
	
	public void cleanup()
	{
		try {
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		running = false;
	}
	
	public void run()
	{
		while( running == true )
		{
			while( Queue.length() <= 0 )
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			String data = Queue.Dequeue();
			if( data != null && !data.equals("") )
			{
				writeToFile(data);
				try {
					fileWriter.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Writer.logCount++;
			}
		}
	}
	
	public static long getLogCount()
	{
		return logCount;
	}
	
	public void writeToFile(String str)
	{
		 try {
			fileWriter.write(str);
			fileWriter.write(System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Can't write to the file\n");
			System.exit(0);
		}
	}
}
