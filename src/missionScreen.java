import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class missionScreen {
	public void initialize(){
		JFrame frame = new JFrame("Mission");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(250, 100));
		frame.setLocation(300 , 200);
		
		Box allBox = Box.createVerticalBox();
		Box buttonBox = Box.createHorizontalBox();
		
		
	
		JButton voteUp = new JButton("PASS Mission");
		voteUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save results
				frame.dispose();
				//check for mission pass => open up MainScreen
			}
		});
		buttonBox.add(voteUp);
		
		JButton voteDown = new JButton("FAIL Mission");
		voteDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save results
				frame.dispose();
				//check for mission pass => open up MainScreen
			}
		});
		buttonBox.add(voteDown);
		
		
		allBox.add(Box.createVerticalStrut(10));

		allBox.add(Box.createVerticalStrut(10));

		allBox.add(buttonBox);
		
		frame.add(allBox);
		
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new missionScreen().initialize();
			}
		});
	}
}
