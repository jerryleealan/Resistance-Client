import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class TeamLeaderScreen {
	
	int numberOfTeamMembers = 4;
	int numberOfChosenTeamMems = 0;
	//methods
		public void initialize(){
			JFrame frame = new JFrame("Team Leader");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setPreferredSize(new Dimension(840, 525));
			frame.setLocation(300 , 200);
			
			Box allBox = Box.createVerticalBox();
			Box OPBox = Box.createHorizontalBox();
			
			JLabel instructions = new JLabel("Choose " + numberOfTeamMembers + " people to go on a mission.                                                                                             "
					+ "               ");
		
			JButton submitButton = new JButton("Submit");
			submitButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//save selections
					frame.dispose();
					new voteScreen().initialize();
				}
			});
		
			
			
			allBox.add(Box.createVerticalStrut(60));
			allBox.add(instructions);
			allBox.add(Box.createVerticalStrut(60));
			allBox.add(OPBox);
			allBox.add(Box.createVerticalStrut(30));

			allBox.add(submitButton);
			
			frame.add(allBox);
			
			
			
			int numOfPlayers = 7;
			Box[] OPBoxes = new Box[10];
			JLabel[] OPNumbersLabels = new JLabel[10];
			JLabel[] OPIconsLabels = new JLabel[10];
			JButton[] chooseButtons = new JButton[10];
			int[] buttonValues = new int[10];
			
			JLabel[] voteDisplays = new JLabel[10];
			
			int b = 0;
			while(b<10){
				buttonValues[b] = 0;
				b++;
			}
			
			//adding to otherPlayersBox
			for (int i = 0; i < numOfPlayers; i++) {
				OPBoxes[i] = Box.createVerticalBox();
				
				OPNumbersLabels[i] = new JLabel("      Player " + (i + 1));
				
				chooseButtons[i] = new JButton("Choose");
				chooseButtons[i].setName("B" + i);
				
				OPIconsLabels[i] = new JLabel();
				ImageIcon qMark = new ImageIcon("/Users/alan/questionmark.png");
				OPIconsLabels[i].setIcon(qMark);
				
				chooseButtons[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						char c = ((JButton) (e.getSource())).getName().charAt(1);
						if(buttonValues[(int) (c) - 48] % 2 == 0 && numberOfChosenTeamMems < numberOfTeamMembers){
							ImageIcon spyCard = new ImageIcon("/Users/alan/missionmember.jpeg");
							OPIconsLabels[(int) (c) - 48].setIcon(spyCard);
							numberOfChosenTeamMems++;
						}
						else if(OPIconsLabels[(int) (c) - 48].getIcon().equals(qMark)){
							OPIconsLabels[(int) (c) - 48].setIcon(qMark);
							
						}
						else{
							OPIconsLabels[(int) (c) - 48].setIcon(qMark);
							numberOfChosenTeamMems--;
						}
						buttonValues[(int) (c) - 48]++;
					}
				});
				
				
				
				OPBoxes[i].add(OPIconsLabels[i]);
				OPBoxes[i].add(Box.createVerticalStrut(5));
				OPBoxes[i].add(OPNumbersLabels[i]);
				OPBoxes[i].add(Box.createVerticalStrut(5));
				OPBoxes[i].add(chooseButtons[i]);
				
				OPBox.add(Box.createVerticalStrut(50));
				OPBox.add(OPBoxes[i]);
				
				
			}
			
			
			frame.pack();
			frame.setVisible(true);
		}
		
		public static void main(String[] args) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new TeamLeaderScreen().initialize();
				}
			});
		}
			
}
