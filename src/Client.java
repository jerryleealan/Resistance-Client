import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//test again
public class Client {
	public static void main(String[]args)
	{
		new Client();
	}
	public Client(){
		gui();
		network();
	}
	
	private String hostnamestring="84F9622";
	
	private ArrayList<String>otherspys=new ArrayList<String>();
	private boolean spy=false;
	private boolean missionleader=false;
	private ArrayList<String>allusers=new ArrayList<String>();
	
	private JFrame frame=new JFrame();
	private JPanel login=new JPanel();
	private JPanel gamepanel=new JPanel();
	
	private JTextField hostname=new JTextField(hostnamestring);
	private JTextField username=new JTextField("samename");
	
	private JButton submituser=new JButton("Connect!");
	private volatile boolean cansubmit=false;
	private boolean cont=true;
	private OutputStreamWriter osw;
	private InputStreamReader isr;
	private volatile String host="";
	private volatile Socket connection;
	private volatile int port=1999;
	private String user="";
	private JLabel label=new JLabel("Waiting...");
	private volatile String input="";
	
	//gamepanel elements
	private volatile JLabel example=new JLabel("Game Started!");
	//
	
	public void gui(){
		label.setHorizontalAlignment(JLabel.CENTER);
		frame.setSize(400,200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBackground(Color.WHITE);
		username.setBackground(Color.WHITE);
		submituser.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0)
			{
				if(username.getText().length()!=0&&username.getText().length()<=20)
				{
					user=username.getText();
					try
					{
						host=hostname.getText();
						InetAddress address =InetAddress.getByName(host);
						connection=new Socket(address,port);
						System.out.println("Socket created, waiting to submit username.");
						username.setEditable(false);
						frame.remove(login);
						frame.add(label);
						cansubmit=true;
						frame.repaint();
						frame.revalidate();
					}
					catch(Exception e2)
					{
						System.out.println(e2);
						JOptionPane.showMessageDialog(frame,"Invalid Host Name: Please Try Again");
						cansubmit=false;
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame,"Invalid Username: Usernames must contain at least \none character and no more than 20 characters");
					username.setText("");
				}
			}
		});
		
		
		login.setLayout(new GridBagLayout());
		JLabel hnl=new JLabel("Host Name:");
		GridBagConstraints constraints=new GridBagConstraints();
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		constraints.fill=GridBagConstraints.BOTH;
		constraints.weightx=0.25;
		constraints.weighty=0.33;
		login.add(hnl,constraints);
		JLabel unl=new JLabel("Username:");
		constraints.gridy=1;
		login.add(unl,constraints);
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.weightx=0.75;
		login.add(hostname,constraints);
		constraints.gridy=1;
		login.add(username,constraints);
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=2;
		constraints.weightx=1;
		login.add(submituser,constraints);
		frame.add(login);
		//
		gamepanel.add(example);
		//
		frame.setVisible(true);
	}
	public void network(){
		while(!cansubmit){}
		frame.setTitle(user);
		try{
		BufferedOutputStream bos=new BufferedOutputStream(connection.getOutputStream());
		osw=new OutputStreamWriter(bos, "US-ASCII");
		String process=user+(char)13;
		osw.write(process);
		osw.flush();
		System.out.println("Username submitted");
		BufferedInputStream bis=new BufferedInputStream(connection.getInputStream());
		isr=new InputStreamReader(bis,"US-ASCII");
		Thread t=new Thread(new Runnable(){
			public void run()
			{
				try{
					while(true)
					{
						StringBuffer instr=new StringBuffer();
						int c=0;
						while((c=isr.read())!=13)
							instr.append((char) c);
						input=instr.toString();
					}
				}
				catch (IOException e)
				{
					System.out.println(e);
					return;
				}
			}
		});
		t.start();
		while(cont)
		{
			while(input.equals("")){};
			System.out.println("ServerInstruction received");
			if(input.equals("game"))
			{
				input="";
				cont=false;
			}
			else if(input.equals("start"))
			{
				input="";
				System.out.println("Waiting to input something");
				frame.remove(label);
				frame.add(gamepanel);
				frame.repaint();
				frame.revalidate();
			}
			else if(input.substring(0,3).equals("spy"))
			{
				input=input.substring(3);
				otherspys=new ArrayList<String>(Arrays.asList(input.split(" ")));
				otherspys.remove(0);
				input="";
				spy=true;
				frame.remove(label);
				String spytext="";
				if(otherspys.size()==1)
					spytext="You are a spy. There are 2 spies, and the other spy is "+otherspys.get(0);
				else
				{
					spytext="You are a spy. There are "+(otherspys.size()+1)+" spies, and the other spies are ";
					if(otherspys.size()==2)
						spytext+=otherspys.get(0)+" and "+otherspys.get(1);
					else
						spytext+=otherspys.get(0)+", "+otherspys.get(1)+", and "+otherspys.get(2);
				}
				example.setText(spytext);
				frame.remove(label);
				frame.add(gamepanel);
				frame.repaint();
				frame.revalidate();
			}
			else if(input.equals("lead"))
			{
				input="";
				missionleader=true;
			}
		}
		connection.close();
		System.out.println("Reached exit point");
		System.exit(0);
		}
		catch (Exception g)
		{
			System.out.println("Exception: "+ g);
			System.exit(0);
		}
		
	}
}
