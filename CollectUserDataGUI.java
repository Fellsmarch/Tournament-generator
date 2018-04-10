/**
 * This class creates a GUI that gathers data from the user for use in the rest of the tournament generator
 * program when creating a group stage. It gathers the number of groups to be in the group stage, the
 * number of teams in each group and gathers all the teams (their names) to be in the tournament
 * 
 * @author Harrison Cook
 * @version 0.1, March 2018
 */

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.Font;


public class CollectUserDataGUI
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
		
		//GUI Objects (instantiated here so I can call them in my methods below)
		private JLabel lblHowManyGroups, lblHowManyTeams;
		private JLabel checkboxX, checkboxX2, lblPleaseEnterTeam;
		private JCheckBox groupsStatus, teamsStatus;
		private JTextField numOfGroups, numOfTeams, fieldTeamName;
		private JButton enterNumOfGroups, enterNumOfTeams, buttonEnterTeamName;
		private JTextArea namesListOutput;
		JFrame frmTournamentGenerator;
		private JLabel lblErrorReport;
		//private JTextField textField;
		private JButton buttonReset;
		private JButton buttonRemoveLast;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args)
			{
				EventQueue.invokeLater(new Runnable()
					{
						public void run()
							{
								try
									{
										CollectUserDataGUI window = new CollectUserDataGUI();
										window.frmTournamentGenerator.setVisible(true);
									} catch (Exception e)
									{
										e.printStackTrace();
									}
							}
					});
			}

		/**
		 * Create the application.
		 */
		public CollectUserDataGUI()
			{
				initialize();
			}

		
		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize()
			{
				frmTournamentGenerator = new JFrame();
				frmTournamentGenerator.setTitle("Tournament Generator");
				frmTournamentGenerator.setBounds(100, 100, 450, 300);
				frmTournamentGenerator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frmTournamentGenerator.getContentPane().setLayout(null);
				
				lblHowManyGroups = new JLabel("How many groups do you want?");
				lblHowManyGroups.setBounds(12, 12, 241, 15);
				frmTournamentGenerator.getContentPane().add(lblHowManyGroups);
				
				checkboxX = new JLabel("x"); //Makes the checkbox look like it has an 'x' in it, it is above the checkbox so the x is drawn on top
				checkboxX.setBounds(394, 11, 26, 15);
				frmTournamentGenerator.getContentPane().add(checkboxX);
				
				checkboxX2 = new JLabel("x"); //Makes the checkbox look like it has an 'x' in it, it is above the checkbox so the x is drawn on top
				checkboxX2.setBounds(394, 36, 26, 15);
				frmTournamentGenerator.getContentPane().add(checkboxX2);
				
				groupsStatus = new JCheckBox(""); 
				groupsStatus.setEnabled(false);
				//groupsStatus.setSelected(true);
				groupsStatus.setBounds(387, 8, 21, 23);
				frmTournamentGenerator.getContentPane().add(groupsStatus);
				
				teamsStatus = new JCheckBox("");
				teamsStatus.setEnabled(false);
				//teamsStatus.setSelected(true);
				teamsStatus.setBounds(387, 33, 21, 23);
				frmTournamentGenerator.getContentPane().add(teamsStatus);
				
				numOfGroups = new JTextField(); //The input area for the number of groups the user wants
				numOfGroups.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						groupsButton(); //Activates when enter is pressed
					}
				});

				numOfGroups.setToolTipText("Enter a number of groups you want in the group stage");
				numOfGroups.setBounds(248, 12, 36, 19);
				//numOfGroups.getRootPane().setDefaultButton(enterNumOfGroups);
				frmTournamentGenerator.getContentPane().add(numOfGroups);
				
				enterNumOfGroups = new JButton("Submit"); //Submit button for the number of groups input
				enterNumOfGroups.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						groupsButton();
					}
				});
				enterNumOfGroups.setBounds(296, 12, 83, 17);
				frmTournamentGenerator.getContentPane().add(enterNumOfGroups);
				
				lblHowManyTeams = new JLabel("How many teams in each group?");
				lblHowManyTeams.setBounds(12, 37, 229, 15);
				frmTournamentGenerator.getContentPane().add(lblHowManyTeams);
				
				numOfTeams = new JTextField(); //The input area for the number of teams the user wants per group
				numOfTeams.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						teamsButton(); //Activates when enter is pressed
					}
				});
				numOfTeams.setToolTipText("Enter a number of teams you want in each group");
				numOfTeams.setBounds(248, 35, 36, 19);
				frmTournamentGenerator.getContentPane().add(numOfTeams);
				
				enterNumOfTeams = new JButton("Submit"); //Submit button for the number of teams input
				enterNumOfTeams.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						teamsButton();
					}
				});
				enterNumOfTeams.setBounds(296, 35, 83, 17);
				frmTournamentGenerator.getContentPane().add(enterNumOfTeams);
				
				lblErrorReport = new JLabel(""); //The label that shows any errors
				lblErrorReport.setForeground(Color.RED);
				lblErrorReport.setBounds(12, 255, 436, 15);
				frmTournamentGenerator.getContentPane().add(lblErrorReport);
				
				lblPleaseEnterTeam = new JLabel("Please enter team names one by one:");
				lblPleaseEnterTeam.setBounds(12, 64, 311, 15);
				frmTournamentGenerator.getContentPane().add(lblPleaseEnterTeam);
				
				fieldTeamName = new JTextField(); //Where the user inputs the team names one by one
				fieldTeamName.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						teamNameButton(); //Activates when enter is pressed
					}
				});
				fieldTeamName.setBounds(12, 90, 272, 19);
				frmTournamentGenerator.getContentPane().add(fieldTeamName);
				fieldTeamName.setColumns(10);
				fieldTeamName.setEnabled(false);
				
				buttonEnterTeamName = new JButton("Submit"); //Submit button for the team name input field
				buttonEnterTeamName.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						teamNameButton();
					}
				});
				buttonEnterTeamName.setBounds(296, 91, 83, 17);
				frmTournamentGenerator.getContentPane().add(buttonEnterTeamName);
				buttonEnterTeamName.setEnabled(false);
				
				namesListOutput = new JTextArea(); //An output area to show the user the team names they have input
				namesListOutput.setBounds(12, 114, 272, 129);
				frmTournamentGenerator.getContentPane().add(namesListOutput);
				namesListOutput.setEditable(false);
				
				buttonReset = new JButton("Reset"); //Button to reset the program to its initial state, used if the user wants to change the number of teams or groups after inputting a name
				buttonReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						reset();
					}
				});
				buttonReset.setBounds(296, 148, 83, 17);
				frmTournamentGenerator.getContentPane().add(buttonReset);

				
				buttonRemoveLast = new JButton("Remove"); //Button to remove the last team name the user input
				buttonRemoveLast.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						removeLastTeam();
					}
				});
				buttonRemoveLast.setFont(new Font("Dialog", Font.BOLD, 10));
				buttonRemoveLast.setToolTipText("Removes the last added team to the list");
				buttonRemoveLast.setEnabled(false);
				buttonRemoveLast.setBounds(296, 120, 83, 17);
				frmTournamentGenerator.getContentPane().add(buttonRemoveLast);
				

			}

		private void groupsButton() { //Checks to see whether the user input for the number of groups is valid and returns an error if is it not
			
			try {
				numGroups = Integer.parseInt(numOfGroups.getText());
				if (numGroups < 1) {
					throw (new Exception());
				}
				groupsStatus.setSelected(true);
				checkboxX.setText("");
				numOfTeams.requestFocus();
				lblErrorReport.setText("");
				groupsAccepted = true;
				unlockTeamNamesInput();
			} catch(Exception exception) {
				numOfGroups.setText("");
				groupsStatus.setSelected(false);
				checkboxX.setText("x");
				numOfGroups.requestFocus();
				groupsAccepted = false;
				unlockTeamNamesInput();
				if (numGroups < 1) { //If number is negative this seems to return false and so the error thrown is "Please only input an integer"
					lblErrorReport.setText("ERROR: Number of groups must be greater than 0");
				} else {
					lblErrorReport.setText("ERROR: Please only input an integer");
				}
				
				
			}
		}
		
		private void teamsButton() { //Checks to see whether the user input for the number of teams is valid and returns an error if is it not
			try {
				numTeams = Integer.parseInt(numOfTeams.getText());
				if (numTeams < 1) {
					throw (new Exception());
				}
				teamsStatus.setSelected(true);
				checkboxX2.setText("");
				fieldTeamName.requestFocus();
				lblErrorReport.setText("");
				teamsAccepted = true;
				unlockTeamNamesInput();
			} catch(Exception exception) {
				numOfTeams.setText("");
				teamsStatus.setSelected(false);
				checkboxX2.setText("x");
				numOfTeams.requestFocus();
				teamsAccepted = false;
				unlockTeamNamesInput();
				if (numGroups < 1) { //If number is negative this seems to return false and so the error thrown is "Please only input an integer"
					lblErrorReport.setText("ERROR: Number of groups must be greater than 0");
				} else {
					lblErrorReport.setText("ERROR: Please only input an integer");
				}
			}
		}
		
		private void teamNameButton() { //Adds the team name input by the user to the list of teams and calls the output list to be updated
			if (groupsAccepted && teamsAccepted) { //If both the number of groups and number of teams inputs are valid, stops the user editing them after inputing team names to prevent errors
				numOfGroups.setEditable(false);
				enterNumOfGroups.setEnabled(false);
				numOfTeams.setEditable(false);
				enterNumOfTeams.setEnabled(false);
			}
			String teamName = fieldTeamName.getText();
			if (teamName.length() < 1) {
				lblErrorReport.setText("ERROR: Team name must contain at least one character");
			} else {
				teams.add(teamName);
				updateOutputList();
				fieldTeamName.setText("");
				buttonRemoveLast.setEnabled(true);
				lblErrorReport.setText("");
			}
			
			
		}
		
		private void updateOutputList() { //Updates the output list that shows the user what team names they have input
			String toAdd = "";
			for (int index = teams.size() - 1; index >= 0; index--) { //Iterates backwards through the ArrayList so that the newest values are shown at the top of the text field
				toAdd += teams.get(index) + "\n";
			}
			namesListOutput.setText(toAdd);
		}
		
		private void unlockTeamNamesInput() { //Checks if the number of groups and number of teams inputs are valid, if they are it lets the user to input team names
			if (groupsAccepted && teamsAccepted) {
				buttonEnterTeamName.setEnabled(true);
				fieldTeamName.setEnabled(true);
			}
			else {
				buttonEnterTeamName.setEnabled(false);
				fieldTeamName.setEnabled(false);
				//lblErrorReport.setText("ERROR: Please complete previous fields first");
			}
		}
		
		private void removeLastTeam() { //Removes the last team the user added to the list and calls the output list to be updated
			int length = teams.size() - 1;
			teams.remove(length);
			updateOutputList();
			if (teams.size() < 1) {
				buttonRemoveLast.setEnabled(false);
			}
		}
		
		private void reset() { //Resets all values so that the user can re-enter group size, team size and team names
			numGroups = 0;
			numTeams = 0;
			teams = new ArrayList<String>();
			updateOutputList();
			numOfGroups.setEditable(true);
			enterNumOfGroups.setEnabled(true);
			numOfTeams.setEditable(true);
			enterNumOfTeams.setEnabled(true);
			checkboxX.setText("x");
			checkboxX2.setText("x");
			numOfGroups.setText("");
			numOfTeams.setText("");
			groupsAccepted = false;
			teamsAccepted = false;
			unlockTeamNamesInput();
			lblErrorReport.setText("");
			buttonRemoveLast.setEnabled(false);
			numOfGroups.requestFocus();
			groupsStatus.setSelected(false);
			teamsStatus.setSelected(false);

		}
		
		public int getNumGroups() {return numGroups;}
		public int getNumTeams() {return numTeams;}
		public ArrayList<String> getTeams() {return teams;}
		
		
//		public userData getUserData(){
//			return numGroups, numTeams, teams;
//		}
	}
