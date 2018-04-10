
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;



import net.miginfocom.swing.MigLayout;
import javax.swing.JTextPane;
import java.awt.Color;



@SuppressWarnings("serial")
public class GroupStageCreationPanel extends JPanel
	{
		
		//My variables
				/**
				 * The number of groups to be in the group stage
				 */
				private int numGroups;
				/**
				 * The number of teams to be in each group
				 */
				private int numTeams;
				private boolean groupsAccepted = false; //If the input for groups has been accepted
				private boolean teamsAccepted = false;  //If the input for teams has been accepted
				/**
				 * The list of teams (their names) to be put into the groups
				 */
				private ArrayList<String> teams = new ArrayList<String>(); //The list of team names
//				private JTextField numGroupsFieldInput;
//				private JTextField numTeamsFieldInput;
				
				//GUI Objects (instantiated here so I can call them in my methods below)
				private JLabel lblGroups, lblTeams, lblEnterTeamNames;
				private JCheckBox chckbxGroups, chckbxTeams;
				private JTextField numGroupsField, numTeamsField, teamNameField;
				private JButton btnGroups, btnTeams, btnTeamName, btnUndoTeamName, btnReset;
				private JLabel lblError;
				private JTextPane teamNamesOutput;
				private JScrollPane teamNamesContainer;

				



		/**
		 * Create the panel.
		 */
		public GroupStageCreationPanel() {
			setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow][][]", "[][][][][grow][grow][][][][]"));
			
			lblGroups = new JLabel("How many groups do you want?");
			add(lblGroups, "flowx,cell 0 0 6 1,alignx left");
			
			lblTeams = new JLabel("How many teams do you want?");
			add(lblTeams, "flowx,cell 0 1 6 1,alignx left");
			
			lblEnterTeamNames = new JLabel("Please enter team names one by one:");
			add(lblEnterTeamNames, "cell 0 2 6 1,alignx left");
			
			teamNameField = new JTextField();
			teamNameField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					teamNameButton();
				}
				
			});
			
			
			teamNameField.setEnabled(false);
			add(teamNameField, "cell 0 3 6 1,growx");
			teamNameField.setColumns(10);
			
			
			btnGroups = new JButton("Submit");
			btnGroups.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					groupsButton();
				}
			});
			add(btnGroups, "cell 6 0");
			
			chckbxGroups = new JCheckBox("");
			chckbxGroups.setEnabled(false);
			add(chckbxGroups, "cell 7 0");
			
			
			btnTeams = new JButton("Submit");
			btnTeams.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					teamsButton();
				}
			});
			add(btnTeams, "cell 6 1");
			
			chckbxTeams = new JCheckBox("");
			chckbxTeams.setEnabled(false);
			add(chckbxTeams, "cell 7 1");
			
			
			btnTeamName = new JButton("Submit");
			btnTeamName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					teamNameButton();
				}
			});
			btnTeamName.setEnabled(false);
			add(btnTeamName, "cell 6 3");
			
			btnUndoTeamName = new JButton("Undo");
			btnUndoTeamName.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					removeLastTeam();
				}
			});
			btnUndoTeamName.setEnabled(false);
			add(btnUndoTeamName, "cell 6 4,growx,aligny top");
			

			
	
			
			teamNamesOutput = new JTextPane();
			teamNamesOutput.setEditable(false);
			//add(teamNamesOutput, "flowx,cell 0 4 3 5,grow");
			
			teamNamesContainer = new JScrollPane();
			add(teamNamesContainer, "cell 0 4 6 5,grow");
			
			teamNamesContainer.setViewportView(teamNamesOutput);
			
			

			btnReset = new JButton("Reset");
			btnReset.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					reset();
				}
			});
			add(btnReset, "cell 6 8,growx");
			
			lblError = new JLabel(" ");
			lblError.setForeground(Color.RED);
			add(lblError, "cell 0 9 8 1");
			
			numGroupsField = new JTextField();
			numGroupsField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					groupsButton();
				}
			});
			add(numGroupsField, "cell 5 0,growx");
		
			
			
			
			
			numTeamsField = new JTextField();
			numTeamsField.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					teamsButton();
				}
			});
			add(numTeamsField, "cell 5 1,growx");

		}
				
		private void groupsButton() { //Checks to see whether the user input for the number of groups is valid and returns an error if is it not
			
			try {
				numGroups = Integer.parseInt(numGroupsField.getText());
				if (numGroups < 1) {
					throw (new Exception());
				}
				chckbxGroups.setSelected(true);
				//checkboxX.setText("");
				numTeamsField.requestFocus();
				lblError.setText(" ");
				groupsAccepted = true;
				unlockTeamNamesInput();
			} catch(Exception exception) {
				numGroupsField.setText("");
				chckbxGroups.setSelected(false);
				//checkboxX.setText("x");
				numGroupsField.requestFocus();
				groupsAccepted = false;
				unlockTeamNamesInput();
				lblError.setText("ERROR: Number of groups must be an integer greater than 0");
				
				
				
			}
		}
		
		private void teamsButton() { //Checks to see whether the user input for the number of teams is valid and returns an error if is it not
			try {
				numTeams = Integer.parseInt(numTeamsField.getText());
				if (numTeams < 1) {
					throw (new Exception());
				}
				chckbxTeams.setSelected(true);
				//checkboxX2.setText("");
				teamNameField.requestFocus();
				lblError.setText(" ");
				teamsAccepted = true;
				unlockTeamNamesInput();
			} catch(Exception exception) {
				numTeamsField.setText("");
				chckbxTeams.setSelected(false);
				//checkboxX2.setText("x");
				numTeamsField.requestFocus();
				teamsAccepted = false;
				unlockTeamNamesInput();
				lblError.setText("ERROR: Number of groups must be an integer greater than 0");
			}
		}
		
		private void teamNameButton() { //Adds the team name input by the user to the list of teams and calls the output list to be updated
			if (groupsAccepted && teamsAccepted) { //If both the number of groups and number of teams inputs are valid, stops the user editing them after inputing team names to prevent errors
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
			}else {
				teams.add(teamName);
				updateOutputList();
				teamNameField.setText("");
				btnUndoTeamName.setEnabled(true);
				lblError.setText(" ");
				int maxTeams = numGroups * numTeams; 	//The number of teams in the tournament = the number of groups * the number of teams
				if (teams.size() >= maxTeams) { 		//If the number of teams input >= the number of teams that should be in the tournament,
					btnTeamName.setEnabled(false);		//don't let the user input any more teams
				}
			}
			
			
		}
		
		private void updateOutputList() { //Updates the output list that shows the user what team names they have input
			String toAdd = "";
			for (int index = teams.size() - 1; index >= 0; index--) { //Iterates backwards through the ArrayList so that the newest values are shown at the top of the text field
				toAdd += teams.get(index) + "\n";
			}
			teamNamesOutput.setText(toAdd);
			teamNamesOutput.setCaretPosition(0);
		}
		
		private void unlockTeamNamesInput() { //Checks if the number of groups and number of teams inputs are valid, if they are it lets the user to input team names
			if (groupsAccepted && teamsAccepted) {
				btnTeamName.setEnabled(true);
				teamNameField.setEnabled(true);
			}
			else {
				btnTeamName.setEnabled(false);
				teamNameField.setEnabled(false);
				//lblError.setText("ERROR: Please complete previous fields first");
			}
		}
		
		private void removeLastTeam() { //Removes the last team the user added to the list and calls the output list to be updated
			int length = teams.size() - 1;
			teams.remove(length);
			updateOutputList();
			btnTeamName.setEnabled(true); //This is only here for if the max number of teams has been reached and the submit button has been disabled
			if (teams.size() < 1) {
				btnUndoTeamName.setEnabled(false);
			}
		}
		
		private void reset() { //Resets all values so that the user can re-enter group size, team size and team names
			int confirmReset = JOptionPane.showConfirmDialog(this, "Are you sure you want to reset?\nAll data will be lost!", "Confirm reset", JOptionPane.YES_NO_OPTION); //Creates a popup confirming that they user wants to reset
			if (confirmReset == JOptionPane.YES_OPTION) {
				numGroups = 0;
				numTeams = 0;
				teams = new ArrayList<String>();
				updateOutputList();
				numGroupsField.setEditable(true);
				btnGroups.setEnabled(true);
				numTeamsField.setEditable(true);
				btnTeams.setEnabled(true);
				//checkboxX.setText("x");
				//checkboxX2.setText("x");
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
			}
		}
	}
