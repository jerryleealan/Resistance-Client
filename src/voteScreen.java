import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class voteScreen {
	int numberOfTeamMembers = 4;

	//methods
		public void initialize(){
			JFrame frame = new JFrame("Vote");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setPreferredSize(new Dimension(840, 525));
			frame.setLocation(300 , 200);
			
			Box allBox = Box.createVerticalBox();
			Box missionMembersBox = Box.createHorizontalBox();
			Box buttonBox = Box.createHorizontalBox();
			
			int[] missionPlayerNumbers = new int[numberOfTeamMembers];
			missionPlayerNumbers[0] = 1;
			missionPlayerNumbers[1] = 2;
			missionPlayerNumbers[2] = 3;
			missionPlayerNumbers[3] = 4;
			
			String missionPlayerNums = "";
			for(int i = 0; i < numberOfTeamMembers; i++)
			{
				missionPlayerNums += missionPlayerNumbers[i] + ", ";
			}
			JLabel missionMembers = new JLabel("Players going on mission: " + missionPlayerNums);
			missionMembersBox.add(missionMembers);
		
			JButton voteUp = new JButton("   Vote UP   ");
			voteUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//save vote results
					frame.dispose();
					//check for mission pass => open up missionScreen for teamMembers
				}
			});
			buttonBox.add(voteUp);
			
			JButton voteDown = new JButton("Vote DOWN");
			voteDown.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//save vote results
					frame.dispose();
					//check for mission pass => open up missionScreen for teamMembers
				}
			});
			buttonBox.add(voteDown);
			
			
			allBox.add(Box.createVerticalStrut(120));
			allBox.add(missionMembersBox);
			allBox.add(Box.createVerticalStrut(30));

			allBox.add(buttonBox);
			
			frame.add(allBox);
			
			
			
			frame.pack();
			frame.setVisible(true);
		}
		
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new voteScreen().initialize();
				}
			});
		}
}
