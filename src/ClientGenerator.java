import java.io.IOException;

public class ClientGenerator {
	public static void main(String[]args)
	{
		int numuserstogenerate=5;
		String hostname="84F9622";
		for (int i=0;i<numuserstogenerate;i++)
		{
			Thread t=new Thread(new Runnable(){
				public void run()
				{
					try{
						Client c=new Client();
					}
					catch (Exception e)
					{
						System.out.println(e);
						return;
					}
				}
			});
			t.start();
		}
	}
}
