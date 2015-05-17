import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * This is the command line thread, which can exit the program by taking console input.
 */
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
			System.out.println("Welcome to Logging Server\n1.Show logs count\n2.Show current clients\n3.Show duplicate message count\n4.Exit");
			BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
			try {
				int input = Integer.parseInt(buff.readLine().trim());
				switch(input)
				{
				case 1:	long lc = Writer.getLogCount();
						System.out.println("\n\n");
						System.out.println("Total Log Count: "+lc);
						break;
				case 2: ClientsList.showClients();
						break;
				case 3:	System.out.println("Duplicates: "+reader.dupCount) ;
						break;
				case 4: System.exit(0);
						
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("Please enter your choice in 1-4 numbers");
				continue;
			}
		}
	}
}
