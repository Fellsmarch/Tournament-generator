/**
 * A JPanel which displays the fixtures for a group of teams to play
 * and allows the user to input the result of said fixture(s)
 * 
 * @author Harrison Cook
 */
package multiUseClasses;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import groupStage.GroupPanel;
import groupStage.NumFixtures;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FixturesPanel extends JPanel implements Serializable
	{
		/**
		 * The list of teams/team names to create fixtures for
		 */
		private String[] teamList;
		/**
		 * The array of rounds which hold the fixtures
		 */
		private RoundPanel[] rounds;
		/**
		 * The JComboBox to switch between rounds
		 */
		private JComboBox<String> cardCombo = new JComboBox<String>(); 
		/**
		 * Holds all fixtures for the group
		 */
		private Fixture[][] allFixtures;
		/**
		 * The cardLayout to facilitate put all the round panels in
		 */
		private CardLayout cardLayout = new CardLayout();
		/**
		 * The JPanel which actually contains all the round panels
		 */
		private JPanel roundsContainer = new JPanel(cardLayout);
		/**
		 * The scrollPane the roundsContainer is placed in so that the user can scroll through the fixtures
		 */
		private JScrollPane scrollPane = new JScrollPane(roundsContainer);
		/**
		 * The JPanel that this JPanel is held in {@link groupStage.GroupPanel}
		 */
		private GroupPanel parent;

		
		
		/**
		 * Constructs the panel
		 * 
		 * @param teams the list of teams/team names that are in the group
		 * @param roundType the type of round the rounds will be (Round Robin or Double Round Robin {@link groupStage.NumFixtures})
		 * @param parent the JPanel that this JPanel is held in
		 */
		public FixturesPanel(ArrayList<String> teams, NumFixtures roundType, GroupPanel parent) {
			setLayout(new MigLayout("", "[][][][][grow][][][][]", "[][grow]"));		//Sets the layout of the panel to MigLayout
			add(scrollPane, "cell 0 1 9 1,grow");
			
			this.parent = parent;
			int numTeams = teams.size();
			
			if(numTeams % 2 == 1) { 												//If num of teams is odd, add a fake "Bye" team to the list and increase the number of teams by one
				numTeams += 1;
				teamList = new String[numTeams];
				for(int i = 0; i < numTeams - 1; i++) {teamList[i] = teams.get(i);} //Moves the teams into an array so I can edit freely
				teamList[numTeams - 1] = "Bye";
				} 
			else {
				teamList = new String[numTeams];
				for(int i = 0; i < numTeams; i++) {teamList[i] = teams.get(i);} 	//Moves the teams into an array so I can edit freely
			}
			createRounds(numTeams, roundType);										//Create the rounds
			
			cardCombo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String item = cardCombo.getSelectedItem().toString();
					cardLayout.show(roundsContainer, item);
				}
			});
			add(cardCombo, "cell 4 0,growx");
			int roundNum = 1;
			for(RoundPanel round : rounds) {										//Add the created rounds to the rounds container
				roundsContainer.add(round, "Round " + roundNum);
				roundNum++;
			}
			
			cardLayout.show(roundsContainer, "Round 1");							//Shows the initial round
		}
		
		/**
		 * Creates all the rounds required for the group and generates
		 * and displays the scorePanel for the round
		 * 
		 * @param numTeams the number of teams in the group
		 * @param roundType the type of round the rounds will be (Round Robin or Double Round Robin {@link groupStage.NumFixtures})
		 */
		private void createRounds(int numTeams, NumFixtures roundType) {
			int numFixtures = (numTeams / 2);
			int numUniqueRounds = numTeams - 1; 									//If it's a double round robin, half of the rounds are the same as the other half
			
			if(roundType == NumFixtures.DOUBLE_ROUND_ROBIN) {
				rounds = new RoundPanel[numUniqueRounds * 2];
				allFixtures = new Fixture[rounds.length * 2][numFixtures];
			}
			else {
				rounds = new RoundPanel[numUniqueRounds];
				allFixtures = new Fixture[rounds.length][numFixtures]; 				//Create an array with a slot for each round, which itself is an array for each fixture to be played each round
			}
			
			//Creates the first round
			cardCombo.addItem("Round 1");
			int team1Index = 0; int team2Index = numTeams - 1;
			for(int fixIndex = 0; fixIndex < numFixtures; fixIndex++) {
				allFixtures[0][fixIndex] = new Fixture(team1Index, team2Index);
				team1Index++; team2Index--;
			}
			rounds[0] = new RoundPanel(allFixtures[0]);
		
			//Create the following rounds
			for(int round = 2; round <= numUniqueRounds; round++) { 				//Iterates up to numTeams - 1 as 
				String roundTitle = "Round " + round;
				cardCombo.addItem(roundTitle);
				
				ArrayList<Integer> teamsTemp = new ArrayList<Integer>();
				int prevRound = round - 2;
				for(Fixture fixture : allFixtures[prevRound]) {
					teamsTemp.add(fixture.team1);
					teamsTemp.add(fixture.team2);
				}
				int teamsLeft = teamsTemp.size();
				
				//This deals with the initial two teams
				if(round % 2 == 0) { 												//If the round is even, puts the last team from the last round second
					team1Index = numTeams - 1; team2Index = teamsTemp.get(teamsLeft - 1);
					} else { 														//Else if the round is odd, puts the last team from the last round first
						team1Index = teamsTemp.get(teamsLeft - 1); team2Index = numTeams - 1;
						}
				allFixtures[round - 1][0] = new Fixture(team1Index, team2Index);
				teamsTemp.remove((Integer) team1Index); teamsTemp.remove((Integer) team2Index);
				teamsLeft -= 2;
				
				//This deals with all the remaining teams
				int fixIndex = 1;
				while(teamsLeft > 0) {
					team1Index = teamsTemp.get(teamsLeft - 2); team2Index = teamsTemp.get(teamsLeft - 1);
					allFixtures[round - 1][fixIndex] = new Fixture(team1Index, team2Index);
					teamsTemp.remove((Integer) team1Index); teamsTemp.remove((Integer) team2Index);
					teamsLeft -= 2; fixIndex ++;
				}
				rounds[round - 1] = new RoundPanel(allFixtures[round - 1]); 		//Since round the is not the index, I use round - 1
			}
			
			if(roundType == NumFixtures.DOUBLE_ROUND_ROBIN) {
				for(int i = 0; i < numUniqueRounds; i++) {
					int roundToCreate = i + numUniqueRounds;
					String roundTitle = "Round " + (roundToCreate + 1);
					cardCombo.addItem(roundTitle);
					int fixIndex = 0;
					for(Fixture fixture : allFixtures[i]) {
						allFixtures[roundToCreate][fixIndex] = new Fixture(fixture.team2, fixture.team1); //This just reverses the fixture so the home team is now away and vice versa
						fixIndex ++;
					}
					rounds[roundToCreate] = new RoundPanel(allFixtures[roundToCreate]);
				}
				
			}
		}
		
		/**
		 * This class is effectively a container for a fixture
		 * 
		 * @author Harrison Cook
		 */
		class Fixture
		{
			/**
			 * The first team's index
			 */
			public final int team1;
			/**
			 * The second team's index
			 */
			public final int team2;
			
			/**
			 * Constructs the fixture
			 * 
			 * @param team1 the first team's index
			 * @param team2 the second team's index
			 */
			public Fixture(int team1, int team2) {
				this.team1 = team1;
				this.team2 = team2;
			}
			
		}

		/**
		 * The round panel which formats and holds the fixtures so 
		 * that they display in a grid/table fashion
		 * 
		 * @author Harrison Cook
		 */
		class RoundPanel extends JPanel
		{
			/**
			 * The places to enter the fixture results for each fixture
			 */
			private ScorePanel[] scorePanels;

			/**
			 * Construct the panel.
			 * 
			 * @param fixtures the fixtures to be in this round
			 */
			public RoundPanel(Fixture[] fixtures)
				{
					String rows = "[][][]"; 													//First row is the header
					for(int i = 0; i < fixtures.length; i++) {
						rows = "[]" + rows;
					}
					setLayout(new MigLayout("", "[grow][][grow][][][][][][]", "[][][][][]"));
					
					//This is the static stuff that never changes
					JSeparator topBorder = new JSeparator();
					topBorder.setForeground(Color.BLACK);
					topBorder.setBackground(Color.BLACK);
					add(topBorder, "cell 0 0 9 1,grow");
					
					JLabel lblHomeTeam = new JLabel("Home Team");
					add(lblHomeTeam, "cell 0 1,alignx center");
				
					
					JLabel lblAwayTeam = new JLabel("Away Team");
					add(lblAwayTeam, "cell 2 1,alignx center");
					
					JLabel lblScore = new JLabel("Score");
					add(lblScore, "cell 4 1 3 1,alignx center");
					
					JSeparator headerBorder = new JSeparator();
					headerBorder.setBackground(Color.BLACK);
					headerBorder.setForeground(Color.BLACK);
					add(headerBorder, "cell 0 2 9 1,grow");
					
					int currentRow = 3;
					scorePanels = new ScorePanel[fixtures.length];
					String homeTeam; String awayTeam;
					int scorePanelIndex = 0;
					for(Fixture fixture : fixtures) {
						scorePanels[scorePanelIndex] = new ScorePanel(fixture.team1, fixture.team2);
						homeTeam = teamList[fixture.team1];
						awayTeam = teamList[fixture.team2];
						add(new JLabel(homeTeam), "cell 0 " + currentRow + ",alignx center");
						add(new JLabel(awayTeam), "cell 2 " + currentRow + ",alignx center");
						add(scorePanels[scorePanelIndex], "cell 4 " + currentRow);
						currentRow++;
					}
					
					
					//Below this must be generated for each fixtures
					int borderLength = 3 + (fixtures.length * 2); 									//Times two since every fixture adds a border as well
					
					JSeparator verticalBorderAdjust1 = new JSeparator();
					verticalBorderAdjust1.setOrientation(SwingConstants.VERTICAL);
					verticalBorderAdjust1.setBackground(Color.BLACK);
					verticalBorderAdjust1.setForeground(Color.BLACK);
					add(verticalBorderAdjust1, "cell 1 0 1 " + borderLength + ",grow");
					
					JSeparator verticalBorderAdjust2 = new JSeparator();
					verticalBorderAdjust2.setOrientation(SwingConstants.VERTICAL);
					verticalBorderAdjust2.setForeground(Color.BLACK);
					verticalBorderAdjust2.setBackground(Color.BLACK);
					add(verticalBorderAdjust2, "cell 3 0 1 " + borderLength + ",grow");
				}
		}
		
		/**
		 * The panel where the user enters the scores/results of a 
		 * give fixture
		 * 
		 * @author Harrison Cook
		 */
		class ScorePanel extends JPanel
		{
			/**
			 * The field to enter the home team's score
			 */
			private JTextField homeScoreField;
			/**
			 * The field to enter the away team's score
			 */
			private JTextField awayScoreField;
			/**
			 * Whether or not the user input is valid (a whole, non-negative integer
			 */
			@SuppressWarnings("unused") //It is used
			private boolean dataAccepted = false;
			/**
			 * The entered score for the home team
			 */
			private int homeScore;
			/**
			 * The entered score for the away team
			 */
			private int awayScore;
			/**
			 * The check box to implicitly inform the user if their input was valid
			 */
			private JCheckBox chckbxSubmit;

			/**
			 * Construct the panel
			 * 
			 * @param homeTeamIndex the index of the home team
			 * @param awayTeamIndex the index of the away team
			 */
			public ScorePanel(int homeTeamIndex, int awayTeamIndex)
				{
				setLayout(new MigLayout("", "[][][][][]", "[]"));
				
				homeScoreField = new JTextField();
				add(homeScoreField, "cell 0 0");
				homeScoreField.setColumns(3);
				
				JLabel label = new JLabel("-");
				add(label, "cell 1 0,alignx trailing");
				
				awayScoreField = new JTextField();
				add(awayScoreField, "cell 2 0");
				awayScoreField.setColumns(3);
				
				JButton btnSubmit = new JButton("Submit");
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							homeScore = Integer.parseInt(homeScoreField.getText());
							awayScore = Integer.parseInt(awayScoreField.getText());
							if (homeScore < 0 || awayScore < 0) {
								JOptionPane.showMessageDialog(parent, "Scores must be 0 or more!", "SCORE ERROR", JOptionPane.ERROR_MESSAGE);
							}else {
								dataAccepted = true;
								chckbxSubmit.setSelected(true);
								int[] result = {homeTeamIndex, awayTeamIndex, homeScore, awayScore};
								parent.getTable().addResult(result);
								homeScoreField.setEnabled(false);
								awayScoreField.setEnabled(false);
								btnSubmit.setEnabled(false);
							}

						} catch(Exception exception) { 	//Should make this a specific exception
							homeScoreField.requestFocus();
							JOptionPane.showMessageDialog(parent, "Scores must be integers!", "SCORE ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				add(btnSubmit, "cell 3 0,growx");
				
				chckbxSubmit = new JCheckBox("");
				chckbxSubmit.setEnabled(false);
				add(chckbxSubmit, "cell 4 0");
				}
		}
	}
