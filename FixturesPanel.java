import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class FixturesPanel extends JPanel
	{
		
		private String[] teamList;
		private RoundPanel[] rounds;
		private JComboBox<String> cardCombo = new JComboBox<String>(); 
		private Fixture[][] allFixtures;
		private CardLayout cardLayout = new CardLayout();
		private JPanel roundsContainer = new JPanel(cardLayout);
		private JScrollPane scrollPane = new JScrollPane(roundsContainer);
		private GroupPanel parent;

/**
		 * Create the panel.
		 */
		public FixturesPanel(ArrayList<String> teams, NumFixtures roundType, GroupPanel newParent) {
//			System.out.println(teams.toString());
			setLayout(new MigLayout("", "[][][][][grow][][][][]", "[][grow]"));
			add(scrollPane, "cell 0 1 9 1,grow");
			
			parent = newParent;
			int numTeams = teams.size();
			
			if(numTeams % 2 == 1) { //If num of teams is odd, add a fake "Bye" team to the list and increase the number of teams by one
				numTeams += 1;
				teamList = new String[numTeams];
				for(int i = 0; i < numTeams - 1; i++) {teamList[i] = teams.get(i);} //Moves the teams into an array so I can edit freely
				teamList[numTeams - 1] = "Bye";
				} 
			else {
				teamList = new String[numTeams];
				for(int i = 0; i < numTeams; i++) {teamList[i] = teams.get(i);} //Moves the teams into an array so I can edit freely
			}
			createRounds(numTeams, roundType);
			
			cardCombo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String item = cardCombo.getSelectedItem().toString();
					cardLayout.show(roundsContainer, item);
				}
			});
			
//			Fixture testFixture = new Fixture(0, 1);
//			Fixture[] tests = {testFixture, new Fixture(1, 0)};
//			roundsContainer.add(new RoundPanel(tests));
			
			add(cardCombo, "cell 4 0,growx");
			
			int roundNum = 1;
			for(RoundPanel round : rounds) {
				roundsContainer.add(round, "Round " + roundNum);
				roundNum++;
			}
			
			cardLayout.show(roundsContainer, "Round 1");
			//roundsContainer.add(rounds[0], "Round 1");
		}
		
		private void createRounds(int numTeams, NumFixtures roundType) {
			int numFixtures = (numTeams / 2);
			int numUniqueRounds = numTeams - 1; //If it's a double round robin, half of the rounds are the same as the other half
			
			if(roundType == NumFixtures.DOUBLE_ROUND_ROBIN) {
				rounds = new RoundPanel[numUniqueRounds * 2];
				allFixtures = new Fixture[rounds.length * 2][numFixtures];
			}
			else {
				rounds = new RoundPanel[numUniqueRounds];
				allFixtures = new Fixture[rounds.length][numFixtures]; //Create an array with a slot for each round, which itself is an array for each fixture to be played each round
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
			for(int round = 2; round <= numUniqueRounds; round++) { //Iterates up to numTeams - 1 as 
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
				if(round % 2 == 0) { //If the round is even, puts the last team from the last round second
					team1Index = numTeams - 1; team2Index = teamsTemp.get(teamsLeft - 1);
					} else { 		//Else if the round is odd, puts the last team from the last round first
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
				rounds[round - 1] = new RoundPanel(allFixtures[round - 1]); //Since round the is not the index, I use round - 1
			}
			
			if(roundType == NumFixtures.DOUBLE_ROUND_ROBIN) {
				for(int i = 0; i < numUniqueRounds; i++) {
					int roundToCreate = i + numUniqueRounds;
					int fixIndex = 0;
					for(Fixture fixture : allFixtures[i]) {
						allFixtures[roundToCreate][fixIndex] = new Fixture(fixture.team2, fixture.team1); //This just reverses the fixture so the home team is now away and vice versa
						fixIndex ++;
					}
					rounds[roundToCreate] = new RoundPanel(allFixtures[i]);
				}
				
			}
		}
		
		class Fixture { //Create
			public final int team1;
			public final int team2;
			public Fixture(int team1, int team2) {
				this.team1 = team1;
				this.team2 = team2;
			}
			
		}

		class RoundPanel extends JPanel
		{
		private ScorePanel[] scorePanels;

			/**
			 * Create the panel.
			 */
			public RoundPanel(Fixture[] fixtures)
				{
					String rows = "[][][]"; //First one is the header
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
//						add(new JTextField(3), "cell 4 " + currentRow);
//						add(new JLabel("-"), "cell 5 " + currentRow + ",alignx center");
//						add(new JTextField(3), "cell 6 " + currentRow);
//						add(new JButton("Submit"), "cell 8 " + currentRow); //I don't know how to make the event handlers, could just have one button at the bottom 
					}
					
					//add(new JSeparator(SwingConstants.VERTICAL))
					
					//Below this must be generated for each fixtures
					int borderLength = 3 + (fixtures.length * 2); //Times two since every fixture adds a border as well
					
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
					
					
					
//					JSeparator verticalBorderAdjust3 = new JSeparator();
//					verticalBorderAdjust3.setOrientation(SwingConstants.VERTICAL);
//					verticalBorderAdjust3.setForeground(Color.BLACK);
//					verticalBorderAdjust3.setBackground(Color.BLACK);
//					add(verticalBorderAdjust3, "cell 7 0 1 " + borderLength + ",grow");

//					JLabel lblTeam1 = new JLabel("Team 1");
//					add(lblTeam1, "cell 0 3,alignx center");
//					
//					JLabel lblTeam2 = new JLabel("Team 2");
//					add(lblTeam2, "cell 2 3,alignx center");
//					
//					homeScoreField = new JTextField();
//					add(homeScoreField, "cell 4 3");
//					homeScoreField.setColumns(3);
//					
//					JLabel label = new JLabel("-");
//					add(label, "cell 5 3,alignx trailing");
//					
//					awayScoreField = new JTextField();
//					add(awayScoreField, "cell 6 3");
//					awayScoreField.setColumns(3);
					
//					JButton btnSubmit = new JButton("Submit");
//					add(btnSubmit, "cell 8 3");
//					
//					JSeparator fixtureBorder = new JSeparator();
//					fixtureBorder.setForeground(Color.BLACK);
//					fixtureBorder.setBackground(Color.BLACK);
//					add(fixtureBorder, "cell 0 4 9 1,grow");
					
				
				}

		}
		
		class ScorePanel extends JPanel
		{
		private JTextField homeScoreField;
		private JTextField awayScoreField;
		@SuppressWarnings("unused")
		private boolean dataAccepted = false;
		private int homeScore;
		private int awayScore;
		private JCheckBox chckbxSubmit;
		//private int[] result = {homeTeam, awayTeam, homeScore, awayScore};

			/**
			 * Create the panel.
			 */
			public ScorePanel(int homeTeamIndex, int awayTeamIndex)
				{
//				homeTeam = homeTeamIndex;
//				awayTeam = awayTeamIndex;
				
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
								parent.tablePanel.addResult(result);
								homeScoreField.setEnabled(false);
								awayScoreField.setEnabled(false);
								btnSubmit.setEnabled(false);
							}

						} catch(Exception exception) { //Should make this a specific exception
//							dataAccepted = false;
//							chckbxSubmit.setSelected(false);
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
			
//			public boolean checkAccepted() {return dataAccepted;}
//			public int getHomeScore() {return homeScore;}
//			public int getAwayScore() {return awayScore;}

		}


	}
