import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Monitor implements Runnable{
	Reader reader;
	Writer writer;
	public Monitor(Reader r, Writer w)
	{
		reader = r;
		writer = w;
	}
	
	public void run()
	{
		while( true )
		{
			System.out.println("Press 1 to stop\n");
			BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
			try {
				if( buff.readLine().trim().equals("1") )
				{
					System.exit(0);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
