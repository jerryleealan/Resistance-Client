import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ResistanceMainScreen {
	
	
	//methods
	public void initialize(){
		JFrame frame = new JFrame("Resistance");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1680, 1050));
		frame.setLocation(0, 0);
		
		Box allBox = Box.createVerticalBox();
		Box voteBox = Box.createHorizontalBox();
		Box OPBox = Box.createHorizontalBox();
		
		
		allBox.add(OPBox);
		allBox.add(Box.createVerticalStrut(15));
		allBox.add(voteBox);
		frame.add(allBox);
		
		
		int numOfPlayers = 7;
		Box[] OPBoxes = new Box[10];
		JLabel[] OPNumbersLabels = new JLabel[10];
		JLabel[] OPIconsLabels = new JLabel[10];
		JButton[] suspectButtons = new JButton[10];
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
			
			suspectButtons[i] = new JButton("Suspect");
			suspectButtons[i].setName("B" + i);
			
			OPIconsLabels[i] = new JLabel();
			ImageIcon qMark = new ImageIcon("/Users/alan/questionmark.png");
			OPIconsLabels[i].setIcon(qMark);
			
			suspectButtons[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					char c = ((JButton) (e.getSource())).getName().charAt(1);
					if(buttonValues[(int) (c) - 48] % 2 == 0){
						ImageIcon spyCard = new ImageIcon("/Users/alan/spy.png");
						OPIconsLabels[(int) (c) - 48].setIcon(spyCard);
					}
					else{
						OPIconsLabels[(int) (c) - 48].setIcon(qMark);
					}
					buttonValues[(int) (c) - 48]++;
				}
			});
			
			voteDisplays[i] = new JLabel("    Last vote: ");
			
			
			
			OPBoxes[i].add(OPIconsLabels[i]);
			OPBoxes[i].add(Box.createVerticalStrut(5));
			OPBoxes[i].add(OPNumbersLabels[i]);
			OPBoxes[i].add(Box.createVerticalStrut(5));
			OPBoxes[i].add(suspectButtons[i]);
			OPBoxes[i].add(Box.createVerticalStrut(5));
			OPBoxes[i].add(voteDisplays[i]);
			
			OPBox.add(Box.createVerticalStrut(50));
			OPBox.add(OPBoxes[i]);
			
			
		}
		
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new ResistanceMainScreen().initialize();
			}
		});
	}
		
}
