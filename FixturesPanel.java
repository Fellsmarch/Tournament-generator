import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class FixturesPanel extends JPanel
	{

		/**
		 * Create the panel.
		 */
		public FixturesPanel(ArrayList<String> teams, NumFixtures fixtureType)
			{
			setLayout(new MigLayout("", "[][][][][grow][][][][]", "[][grow]"));
			
			JComboBox cardCombo = new JComboBox();
			add(cardCombo, "cell 4 0,growx");
			
			JPanel roundsPanel = new JPanel();
			add(roundsPanel, "cell 0 1 9 1,grow");
			roundsPanel.setLayout(new CardLayout(0, 0));
			

			
//			int numTeams = teams.size();
//			int numFixtures = numTeams * ((numTeams - 1) / 2);
//			System.out.println(numFixtures); //For testing formula is correct, on 6 it should return 15
//			numFixtures = numTeams - 1; numFixtures /= 2; numFixtures *= numTeams;
//			System.out.println(numFixtures); //For testing formula is correct, on 6 it should return 15
//			if(fixtureType == NumFixtures.DOUBLE_ROUND_ROBIN) {numFixtures *= 2;}
//			
//			String rows = "";
//			for(int i = 0; i < numFixtures; i++) {
//				rows = "[]" + rows;
//			}
//			rows = "[]" + rows; //For the header
//			System.out.println(rows);
//			
//			}
//		
//		public static void main(String[] args) {
//			ArrayList<String> teams = new ArrayList<String>();
//			teams.add("Team 1"); teams.add("Team 2"); teams.add("Team 3"); teams.add("Team 4"); teams.add("Team 6"); teams.add("Team 6");
//			FixturesPanel test = new FixturesPanel(teams, NumFixtures.SINGLE_ROUND_ROBIN);
		}

	}
