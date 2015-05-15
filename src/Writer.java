import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Writer implements Runnable{

	private String fileName;
	private File file;
	FileWriter fileWriter;
    BufferedWriter bufferWriter;
    
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
			bufferWriter = new BufferedWriter(fileWriter);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void cleanup()
	{
		try {
			bufferWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run()
	{
		try
		{
			while( true )
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
				LogData data = Queue.Dequeue();
				if( data != null && !data.equals("") )
				{
					writeToFile(data.toString());

				}
			}
		}
		finally
		{
			try {
				bufferWriter.close();
				fileWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void writeToFile(String str)
	{
		System.out.println("LOG:"+str);
		 try {
			bufferWriter.write(str);
			bufferWriter.write(System.lineSeparator());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
