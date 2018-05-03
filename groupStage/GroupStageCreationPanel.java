/**
 * The JPanel which gets the information required to create a group
 * stage/tournament from the user and then creates the group
 * stage/tournament 
 * 
 * @author Harrison Cook
 */
package groupStage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;

//import multiUseClasses.FixturesPanel;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;



@SuppressWarnings("serial")
public class GroupStageCreationPanel extends JPanel implements Serializable
	{
				
				/**
				 * The group stage panel that gets displayed when all required
				 * information has been gathered
				 */
				private GroupStagePanel groupStage;
				/**
				 * The card layout to switch between the information gathering
				 * panel and the group stage panel itself
				 */
				private CardLayout cardLayout = new CardLayout();
				/**
				 * The container which the card layout goes into
				 */
				private JPanel container = new JPanel(cardLayout);
				
		/**
		 * Construct the panel.
		 */
		public GroupStageCreationPanel() {
			setLayout(new MigLayout("", "[grow]", "[grow]"));
			add(container, "grow");
			
			GetGroupStageDataPanel mainWindow = new GetGroupStageDataPanel();
			container.add(mainWindow, "Main Window");
			cardLayout.show(container, "Main Window");
		}
		
		/**
		 * A JPanel which gathers required information from the user to create
		 * a group stage/tournament
		 * 
		 * @author Harrison Cook
		 */
		class GetGroupStageDataPanel extends JPanel
		{
			/**
			 * The number of groups to be in the group stage
			 */
			private int numGroups;
			/**
			 * The number of teams to be in each group
			 */
			private int numTeams;
			/**
			 * Whether or not the user input for numGroups is valid
			 */
			private boolean groupsAccepted = false;
			/**
			 * Whether or not the user input for numTeams is valid
			 */
			private boolean teamsAccepted = false;
			/**
			 * The number of points awarded to a team per win (default 3)
			 */
			private int pointsPerWin = 3;
			/**
			 * The number of points awarded to a team per draw (default 1)
			 */
			private int pointsPerDraw = 1;
			/**
			 * The number of points awarded to a team per loss (default 0)
			 */
			private int pointsPerLoss = 0;
			/**
			 * The number of fixtures per group to play (default round robin)
			 */
			private NumFixtures numFixtures = NumFixtures.SINGLE_ROUND_ROBIN;
			/**
			 * The list of teams (their names) to be put into the groups
			 */
			private ArrayList<String> teams = new ArrayList<String>();
			
			//GUI Objects (instantiated here so I can call them in my methods below)
			private JLabel lblGroups, lblTeams, lblEnterTeamNames, lblPoints, lblError, lblWin, lblDraw, lblLoss;
			private JCheckBox chckbxGroups, chckbxTeams, chckbxTeamNames, chckbxPoints;
			private JTextField numGroupsField, numTeamsField, teamNameField, winField, lossField, drawField;
			private JButton btnGroups, btnTeams, btnTeamName, btnUndoTeamName, btnReset, btnPoints, btnConfirm;
			private JTextPane teamNamesOutput;
			private JScrollPane teamNamesContainer;
			private JRadioButton rdbtnSingleRoundRobin, rdbtnDoubleRoundRobin;
			
			/**
			 * Initalize the contents of the Panel
			 */
			public GetGroupStageDataPanel() {
			setLayout(new MigLayout("", "[grow][grow][grow][grow][]", "[][][][][][][][grow][grow][][][][]"));
			
			lblGroups = new JLabel("How many groups do you want?");
			add(lblGroups, "flowx,cell 0 0 3 1,alignx left");
			
			
			btnGroups = new JButton("Submit");
			btnGroups.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					groupsButton();
				}
			});
			add(btnGroups, "cell 3 0,growx");
			
			chckbxGroups = new JCheckBox("");
			chckbxGroups.setEnabled(false);
			add(chckbxGroups, "cell 4 0");
			
			lblTeams = new JLabel("How many teams do you want?");
			add(lblTeams, "flowx,cell 0 1 3 1,alignx left");
			
			rdbtnSingleRoundRobin = new JRadioButton("Single Round Robin");
			rdbtnSingleRoundRobin.setSelected(true);
			rdbtnSingleRoundRobin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbtnDoubleRoundRobin.setSelected(false);
					numFixtures = NumFixtures.SINGLE_ROUND_ROBIN;
				}
			});
			
			
			btnTeams = new JButton("Submit");
			btnTeams.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					teamsButton();
				}
			});
			add(btnTeams, "cell 3 1,growx");
			
			chckbxTeams = new JCheckBox("");
			chckbxTeams.setEnabled(false);
			add(chckbxTeams, "cell 4 1");
			add(rdbtnSingleRoundRobin, "flowx,cell 0 2 5 1");
			
			lblPoints = new JLabel("Number of points per");
			add(lblPoints, "cell 0 3 2 1");
			
			lblWin = new JLabel("Win:");
			add(lblWin, "flowx,cell 0 4 3 1,alignx left");
			
			btnPoints = new JButton("Submit");
			btnPoints.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pointsButton();
				}
			});
			add(btnPoints, "cell 3 4,growx");
			
			chckbxPoints = new JCheckBox("");
			chckbxPoints.setEnabled(false);
			add(chckbxPoints, "cell 4 4");
			
			
			lblEnterTeamNames = new JLabel("Please enter team names one by one:");
			add(lblEnterTeamNames, "cell 0 5 5 1,alignx left");
			
			teamNameField = new JTextField();
			teamNameField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					teamNameButton();
				}
				
			});
			
			
			teamNameField.setEnabled(false);
			add(teamNameField, "cell 0 6 3 1,growx");
			teamNameField.setColumns(10);
			
			
			btnTeamName = new JButton("Submit");
			btnTeamName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					teamNameButton();
				}
			});
			btnTeamName.setEnabled(false);
			add(btnTeamName, "cell 3 6,growx");
			
			chckbxTeamNames = new JCheckBox("");
			chckbxTeamNames.setEnabled(false);
			add(chckbxTeamNames, "cell 4 6");
			

			
	
			
			teamNamesOutput = new JTextPane();
			teamNamesOutput.setEditable(false);
			//add(teamNamesOutput, "flowx,cell 0 4 3 5,grow");
			
			teamNamesContainer = new JScrollPane();
			add(teamNamesContainer, "cell 0 7 3 5,grow");
			
			teamNamesContainer.setViewportView(teamNamesOutput);
			
			btnUndoTeamName = new JButton("Undo");
			btnUndoTeamName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeLastTeam();
				}
			});
			btnUndoTeamName.setEnabled(false);
			add(btnUndoTeamName, "cell 3 7,growx,aligny top");
			
			btnConfirm = new JButton("Confirm");
			btnConfirm.setEnabled(false);
			btnConfirm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
						if(chckbxPoints.isSelected()) {
						int confirmContinue = JOptionPane.showConfirmDialog(null, "Are you sure you want to continue with these teams?", "Confirm Continue", JOptionPane.YES_NO_CANCEL_OPTION);
						if(confirmContinue == JOptionPane.YES_OPTION) {
							groupStage = new GroupStagePanel(teams, numGroups, numTeams, pointsPerWin, pointsPerDraw, pointsPerLoss, numFixtures);
							container.add(groupStage, "Group Stage");
							cardLayout.show(container, "Group Stage");
						}
					}else {lblError.setText("ERROR: Please submit number of points per win/draw/loss!");}
				}
			});
			add(btnConfirm, "cell 3 10,growx");
			
			

			btnReset = new JButton("Reset");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reset();
				}
			});
			add(btnReset, "cell 3 11,growx");
			
			lblError = new JLabel(" ");
			lblError.setForeground(Color.RED);
			add(lblError, "cell 0 12 5 1");
			
			winField = new JTextField();
			winField.setText("3");
			winField.setColumns(3);
			add(winField, "cell 0 4 3 1,alignx center");
			
			lblDraw = new JLabel(" Draw:");
			add(lblDraw, "cell 0 4 3 1,alignx center");
			
			drawField = new JTextField();
			drawField.setText("1");
			add(drawField, "cell 0 4 3 1");
			drawField.setColumns(3);
			
			lblLoss = new JLabel("Loss");
			add(lblLoss, "cell 0 4 3 1");
			
			lossField = new JTextField();
			lossField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pointsButton();
				}
			});
			lossField.setText("0");
			add(lossField, "cell 0 4 3 1");
			lossField.setColumns(3);
				
				numGroupsField = new JTextField();
				numGroupsField.setColumns(3);
				numGroupsField.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						groupsButton();
					}
				});
				add(numGroupsField, "cell 2 0,alignx right");
				
					numTeamsField = new JTextField();
					numTeamsField.setColumns(3);
					numTeamsField.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							teamsButton();
						}
					});
					add(numTeamsField, "cell 2 1,alignx right");
					
					rdbtnDoubleRoundRobin = new JRadioButton("Double Round Robin");
					rdbtnDoubleRoundRobin.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							rdbtnSingleRoundRobin.setSelected(false);
							numFixtures = NumFixtures.DOUBLE_ROUND_ROBIN;
						}
					});
					add(rdbtnDoubleRoundRobin, "cell 0 2 5 1,alignx right");

		}
		
		/**
		 * Checks to see whether the user input for the number of groups
		 * is valid shows an error if it is not
		 * 
		 */
		private void groupsButton() {
			
			try {
				numGroups = Integer.parseInt(numGroupsField.getText());
				if (numGroups < 1) {
					throw (new Exception()); 		//Should make this a specific exception
				}
				chckbxGroups.setSelected(true);
				numTeamsField.requestFocus();
				lblError.setText(" ");
				groupsAccepted = true;
				unlockTeamNamesInput();
			} catch(Exception exception) { 			//Should make this a specific exception
				numGroupsField.setText("");
				chckbxGroups.setSelected(false);
				numGroupsField.requestFocus();
				groupsAccepted = false;
				unlockTeamNamesInput();
				lblError.setText("ERROR: Number of groups must be an integer greater than 0");
				
				
				
			}
		}
		
		/**
		 * Checks to see whether the user input for the number of teams
		 * is valid and shows an error if it is not
		 */
		private void teamsButton() {
			try {
				numTeams = Integer.parseInt(numTeamsField.getText());
				if (numTeams < 1) {
					throw (new Exception()); 		//Should make this a specific exception
				}
				chckbxTeams.setSelected(true);
				winField.requestFocus();
				lblError.setText(" ");
				teamsAccepted = true;
				unlockTeamNamesInput();
			} catch(Exception exception) { 			//Should make this a specific exception
				numTeamsField.setText("");
				chckbxTeams.setSelected(false);
				numTeamsField.requestFocus();
				teamsAccepted = false;
				unlockTeamNamesInput();
				lblError.setText("ERROR: Number of groups must be an integer greater than 0");
			}
		}
		
		/**
		 * Checks to see if the user inputs for the number of points
		 * is valid and shows an error if it is not
		 */
		private void pointsButton() {
			try {
				pointsPerWin = Integer.parseInt(winField.getText());
				pointsPerDraw = Integer.parseInt(drawField.getText());
				pointsPerLoss = Integer.parseInt(lossField.getText());
				chckbxPoints.setSelected(true);
				lblError.setText(" ");
				if(teamNameField.isEnabled()) {
					teamNameField.requestFocus();
				}else {numGroupsField.requestFocus();}
			} catch(Exception exception) {
				chckbxPoints.setSelected(false);
				winField.requestFocus();
				lblError.setText("ERROR: Number of points must be an integer");
			}
		}
		
		/**
		 * Adds the team name entered by the user to the list of teams
		 * and calls the output display to be updated @see updateOutputList
		 */
		private void teamNameButton() {
			if (groupsAccepted && teamsAccepted) { 		//If both the number of groups and number of teams inputs are valid, stops the user editing them after inputing team names to prevent errors
				numGroupsField.setEditable(false);
				btnGroups.setEnabled(false);
				numTeamsField.setEditable(false);
				btnTeams.setEnabled(false);
			}
			String teamName = teamNameField.getText();
			if (teamName.length() < 1) {
				lblError.setText("ERROR: Team name must contain at least one character");
			} else if (teamName.length() > 37){												//Currently this is just an arbitrary number, when I work out a limitation this can be changed
				lblError.setText("ERROR: Team name must not be longer than 37 characters");	//37 is just the number the scroll pane can create in the default panel size that doesn't need a horizontal scroll
			} else if (teams.contains(teamName)) {
				lblError.setText("ERROR: That team already exists, team names must be unique");
			}else {
				teams.add(teamName);
				updateOutputList();
				teamNameField.setText("");
				btnUndoTeamName.setEnabled(true);
				lblError.setText(" ");
				int maxTeams = numGroups * numTeams; 	//The number of teams in the tournament = the number of groups * the number of teams
				if (teams.size() >= maxTeams) { 		//If the number of teams input >= the number of teams that should be in the tournament,
					btnTeamName.setEnabled(false);		//don't let the user input any more teams
					teamNameField.setEnabled(false);
					btnConfirm.setEnabled(true);
					chckbxTeamNames.setSelected(true);
				}else {teamNameField.requestFocus();}
			}
			
			
		}
		
		/**
		 * Updates the output list which shows the user what team names
		 * they have already submitted
		 */
		private void updateOutputList() {
			String toAdd = "";
			for (int index = teams.size() - 1; index >= 0; index--) { //Iterates backwards through the ArrayList so that the newest values are shown at the top of the text field
				toAdd += teams.get(index) + "\n";
			}
			teamNamesOutput.setText(toAdd);
			teamNamesOutput.setCaretPosition(0);
		}
		
		/**
		 * Checks if the number of groups and number of teams inputs
		 * are valid, if they are it allows the user to input team names
		 */
		private void unlockTeamNamesInput() {
			if (groupsAccepted && teamsAccepted) {
				btnTeamName.setEnabled(true);
				teamNameField.setEnabled(true);
			}
			else {
				btnTeamName.setEnabled(false);
				teamNameField.setEnabled(false);
			}
		}
		
		/**
		 * Removes the last team the user submitted and updates the output
		 * list
		 */
		private void removeLastTeam() {
			int length = teams.size() - 1;
			teams.remove(length);
			updateOutputList();
			btnTeamName.setEnabled(true); 			//This is only here for if the max number of teams has been reached and the submit button has been disabled
			teamNameField.setEnabled(true);
			int maxTeams = numGroups * numTeams;
			if(teams.size() < maxTeams) {
				btnConfirm.setEnabled(false);
				chckbxTeamNames.setSelected(false);
			}
			
			if (teams.size() < 1) {
				btnUndoTeamName.setEnabled(false);
				
			}
		}
		
		/**
		 * Resets all values in the panel so that the user can
		 * re-enter group size, team size and team names if they
		 * have been locked
		 */
		private void reset() {
			int confirmReset = JOptionPane.showConfirmDialog(this, "Are you sure you want to reset?\nAll data will be lost!", "Confirm reset", JOptionPane.YES_NO_CANCEL_OPTION); //Creates a popup confirming that they user wants to reset
			if (confirmReset == JOptionPane.YES_OPTION) {
				numGroups = 0;
				numTeams = 0;
				teams = new ArrayList<String>();
				updateOutputList();
				numGroupsField.setEditable(true);
				btnGroups.setEnabled(true);
				numTeamsField.setEditable(true);
				btnTeams.setEnabled(true);
				numGroupsField.setText("");
				numTeamsField.setText("");
				groupsAccepted = false;
				teamsAccepted = false;
				unlockTeamNamesInput();
				lblError.setText(" ");
				btnUndoTeamName.setEnabled(false);
				numGroupsField.requestFocus();
				chckbxGroups.setSelected(false);
				chckbxTeams.setSelected(false);
				rdbtnSingleRoundRobin.setSelected(true);
				rdbtnDoubleRoundRobin.setSelected(false);
				winField.setText("3"); pointsPerWin = 3;
				drawField.setText("1"); pointsPerDraw = 1;
				lossField.setText("0"); pointsPerLoss = 0;
				chckbxPoints.setSelected(false);
				chckbxTeamNames.setSelected(false);
				btnConfirm.setEnabled(false);
			}
		}
		
		/**
		 * Returns the required information to create a group stage
		 * 
		 * @return and object array containing the teams, points per win, 
		 * points per draw, points per loss and the number of fixtures
		 */
		public Object[] getGroupStageData() {
			Object[] toReturn = {teams, pointsPerWin, pointsPerDraw, pointsPerLoss, numFixtures};
			return toReturn;
		}
		}
		
		
		
		/**
		 * The JPanel used once group stage data has been collected
		 * It contains the table panel for the tournament and the fixtures panel
		 * {@link multiUseClasses.FixturesPanel}
		 * {@link multiUseClasses.TablePanel}
		 * 
		 * @author Harrison Cook
		 */
		public class GroupStagePanel extends JPanel
		{
			/**
			 * The JComboBox for switching between the table panel
			 * and the fixtures panel
			 */
			private JComboBox<String> cardCombo = new JComboBox<String>();
			/**
			 * The card layout to facilitate switching between the 
			 * table panel and the fixtures panel
			 */
			private CardLayout cardLayout = new CardLayout();
			/**
			 * The container which contains the table panel and the
			 * fixtures panel
			 */
			private JPanel groupsContainer = new JPanel(cardLayout);
			/**
			 * The scroll pane to ensure the user can see all the elements
			 */
			private JScrollPane scrollPane = new JScrollPane(groupsContainer);
			
			/**
			 * Constructs the panel.
			 */
			public GroupStagePanel(ArrayList<String> teams, int numOfGroups, int numOfTeams, int PPWin, int PPDraw, int PPLoss, NumFixtures numFixtures)
				{
					setLayout(new MigLayout("", "[][][][][grow][][][][]", "[][grow]"));
					add(scrollPane, "cell 0 1 9 1,grow");
				
					Random rand = new Random();
					for (int groupNum = 1; groupNum <= numOfGroups; groupNum++) {
						ArrayList<String> groupTeamNames = new ArrayList<String>();
						for (int teamNum = numOfTeams; teamNum > 0; teamNum--) {
							int numOfNamesLeft = teams.size();
							int nameToAddIndex = rand.nextInt(numOfNamesLeft);
							String nameToAdd = teams.get(nameToAddIndex);
							teams.remove(nameToAddIndex);
							groupTeamNames.add(nameToAdd);
						}
						cardCombo.addItem("Group " + groupNum);
						groupsContainer.add(new GroupPanel(groupTeamNames, PPWin, PPDraw, PPLoss, numFixtures), "Group " + groupNum);
					}
					
					cardCombo.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String item = cardCombo.getSelectedItem().toString();
							cardLayout.show(groupsContainer, item);
						}
					});
					add(cardCombo, "cell 4 0,growx");
				}

		}
		
		/**
		 * Returns the groupStage
		 * @return groupStage
		 */
		public GroupStagePanel getGroupStagePanel () {return groupStage;}
	}
