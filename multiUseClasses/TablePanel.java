/**
 * A JPanel that uses a custom table model to display and update a group
 * or league tournament table
 * 
 * @author Harrison Cook
 */

package multiUseClasses;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class TablePanel extends JPanel implements Serializable
{
		/**
		 * The table model for to use in the table
		 */
		private CustomTableModel tableModel;
		/**
		 * The table to put into the JPanel
		 */
		private JTable table;
		
		/**
		 * Constructs the JPanel by creating the table model and then the table with that model
		 * 
		 * @param teamNameList the names of the teams in the tournament
		 * @param PPWin the number of points awarded to a team per win
		 * @param PPDraw the number of points awarded to a team per draw
		 * @param PPLoss the number of points awarded to a team per loss
		 */
		public TablePanel(ArrayList<String> teamNameList, int PPWin, int PPDraw, int PPLoss)
			{
				setLayout(new MigLayout("", "[grow]", "[grow]"));

				//Create the table
				tableModel = new CustomTableModel(teamNameList, PPWin, PPDraw, PPLoss);
				table = new JTable(tableModel);
				add(new JScrollPane(table), "grow");
			}
		
		/**
		 * Calls the addResult method of CustomTableModel
		 * 
		 * @param result the integer array that is the result which is formatted as [homeTeamIndex, awayTeamindex, homeTeamScore, awayTeamScore]
		 * {@link FixturesPanel.ScorePanel}
		 */
		public void addResult(int[] result) {
			tableModel.addResult(result);
		}
		
		/**
		 * A custom table model so that the table is formatted and displayed correctly
		 * and has the relevant information to the tournament
		 * 
		 * @author Harrison Cook
		 */
		class CustomTableModel extends AbstractTableModel  
		{
			/**
			 * The column names for the table
			 */
			private String[] columnNames = {"Position", "Team Name", "Wins", "Draws", "Losses", 
					"Goal Difference", "Goals For", "Goals Against", "Points"};
			/**
			 * The data for the table
			 */
			private Object[][] tableData;
			/**
			 * The number of teams in the table (also equates to the number of rows)
			 */
			private int numTeams;
			/**
			 * The list of team names to put into the table
			 */
			private ArrayList<String> teamNames;
			/**
			 * The number of points awarded to a team per win
			 */
			private int pointsPerWin;
			/**
			 * The number of points awarded to a team per Draw
			 */
			private int pointsPerDraw;
			/**
			 * The number of points awarded to a team per Loss
			 */
			private int pointsPerLoss;
			
			/**
			 * Constructs the custom table model
			 * 
			 * @param @param teamNameList the names of the teams in the tournament
			 * @param PPWin the number of points awarded to a team per win
			 * @param PPDraw the number of points awarded to a team per draw
			 * @param PPLoss the number of points awarded to a team per loss
			 */
			public CustomTableModel(ArrayList<String> teamNameList, int PPWin, int PPDraw, int PPLoss) {
				teamNames = teamNameList;
				numTeams = teamNameList.size();
				pointsPerWin = PPWin; pointsPerDraw = PPDraw; pointsPerLoss = PPLoss;
				
				tableData = new Object[numTeams][9];
				
				for(int i = 0; i < numTeams; i++) {
					String name = teamNames.get(i);
					tableData[i][0] = i + 1;//Position
					tableData[i][1] = name;	//Team name
					tableData[i][2] = 0;	//Wins
					tableData[i][3] = 0;	//Draws
					tableData[i][4] = 0;	//Losses
					tableData[i][5] = 0;	//Goal Difference
					tableData[i][6] = 0;	//Goals For
					tableData[i][7] = 0;	//Goals Against
					tableData[i][8] = 0;	//Points
				}
			}
			
			/**
			 * Returns the number of rows in the table (which equates to the number of teams)
			 * 
			 * @return the number of rows in the table
			 */
			public int getRowCount() {
				return numTeams;
			}
			
			/**
			 * Returns the number of columns in the table
			 * 
			 * @return the number of columns
			 */
			public int getColumnCount() {
				return columnNames.length;
			}
			
			/**
			 * Returns the name of a column
			 * 
			 * @param column the column number that we want the name of
			 * @return the columnName requested
			 */
			public String getColumnName(int column) {
				return columnNames[column];
			}
			
			/**
			 * Returns the current value of a cell in the table
			 * 
			 * @param row the row the cell is in
			 * @param column the column the cell is in
			 * @return the value of the requested cell
			 */
			public Object getValueAt(int row, int column) {
				return tableData[row][column];
			}
			
			/**
			 * Adds a result of a game to the table and re-sorts the table so that the team with
			 * the most points is at the top of the table
			 * 
			 * @param result the integer array that is the result which is formatted as [homeTeamIndex, awayTeamindex, homeTeamScore, awayTeamScore] {@link TablePanel.addResult}
			 */
			public void addResult(int[] result) {
				int homeTeamIndex = -1; int awayTeamIndex = -1;
				String homeTeam = teamNames.get(result[0]); String awayTeam = teamNames.get(result[1]); //Since result will return the original index, but the table reorders them
				int goalsForHome = result[2]; int goalsAgainstHome = result[3];
				for(int i = 0; i < numTeams; i++) {
					if(tableData[i][1] == homeTeam) {homeTeamIndex = i;}
					else if(tableData[i][1] == awayTeam) {awayTeamIndex = i;}
				}
				if(goalsForHome > goalsAgainstHome) { //Home team wins
					//Home team
					tableData[homeTeamIndex][2] = (Integer) tableData[homeTeamIndex][2] + 1; //Since it is an array of array type Object, we have to cast to int //Add win
					tableData[homeTeamIndex][8] = (Integer) tableData[homeTeamIndex][8] + pointsPerWin;
					//Away team
					tableData[awayTeamIndex][4] = (Integer) tableData[awayTeamIndex][4] + 1; //Not sure if this will work, will have to see when it runs		//Add loss
					tableData[awayTeamIndex][8] = (Integer) tableData[awayTeamIndex][8] + pointsPerLoss;
				}else if (goalsForHome == goalsAgainstHome) { //Game draw
					//Home team
					tableData[homeTeamIndex][3] = (Integer) tableData[homeTeamIndex][3] + 1; //Add draw
					tableData[homeTeamIndex][8] = (Integer) tableData[homeTeamIndex][8] + pointsPerDraw;
					//Away team
					tableData[awayTeamIndex][3] = (Integer) tableData[awayTeamIndex][3] + 1; //Add draw
					tableData[awayTeamIndex][8] = (Integer) tableData[awayTeamIndex][8] + pointsPerDraw;
				}else if (goalsForHome < goalsAgainstHome) { //Home team loss
					//Home team
					tableData[homeTeamIndex][4] = (Integer) tableData[homeTeamIndex][4] + 1; //Add loss
					tableData[homeTeamIndex][8] = (Integer) tableData[homeTeamIndex][8] + pointsPerLoss;
					//Away team
					tableData[awayTeamIndex][2] = (Integer) tableData[awayTeamIndex][2] + 1; //Add win
					tableData[awayTeamIndex][8] = (Integer) tableData[awayTeamIndex][8] + pointsPerWin;
				}
				//Add goal data for home team
				tableData[homeTeamIndex][6] = (Integer) tableData[homeTeamIndex][6] + goalsForHome;
				tableData[homeTeamIndex][7] = (Integer) tableData[homeTeamIndex][7] + goalsAgainstHome;
				//Add goal data for away team
				tableData[awayTeamIndex][6] = (Integer) tableData[awayTeamIndex][6] + goalsAgainstHome;
				tableData[awayTeamIndex][7] = (Integer) tableData[awayTeamIndex][7] + goalsForHome;
				
				//Update position & goal difference (could be done in a method instead, but I don't think it will be used outside this method)
				tableData[homeTeamIndex][5] = (Integer) tableData[homeTeamIndex][6] - (Integer) tableData[homeTeamIndex][7]; //Home team goal difference
				tableData[awayTeamIndex][5] = (Integer) tableData[awayTeamIndex][6] - (Integer) tableData[awayTeamIndex][7]; //Away team goal difference
				
				Arrays.sort(tableData, new TeamComparator()); 	//Sorts the table into the correct order
				int pos = 1;
				for(Object[] row : tableData) { 				//Since the sort does not change the position number,
					row[0] = pos;								//I change it here
					pos++;
				}
				fireTableDataChanged(); //Tells the table that all data may have been updated, could update each cell, but 6 out of 9 cells will always need to be updated
			}
			
			/**
			 * A comparator for a collection which sorts a table in ascending order according
			 * to the number of points a team has, then the team's goal difference and then 
			 * the team's number of goals scored
			 * 
			 * @author Harrison Cook
			 */
			class TeamComparator implements Comparator<Object[]>{ //Tells the sorting method how to sort (does it in ascending order, so the return statements are reversed)
				
				/**
				 * Does the comparison between two teams in the table
				 * 
				 * @param team1 the first team object
				 * @param team2 the second team object (to compare against the first)
				 * 
				 * @return -1 if the first team should be below the second team
				 * @return 0 if the first team should be equal to the second team
				 * @return 1 if the first team should be above the second team
				 */
				public int compare(Object[] team1, Object[] team2) {
					int team1Pts = (Integer) team1[8]; int team2Pts = (Integer) team2[8];
					if(team1Pts < team2Pts) {return 1;}
					else if(team1Pts > team2Pts) {return -1;}
					else {
						int team1GD = (Integer) team1[5]; int team2GD = (Integer) team2[5];
						if(team1GD < team2GD) {return 1;}
						else if(team1GD > team2GD) {return -1;}
						else {
							int team1GF = (Integer) team1[6]; int team2GF = (Integer) team2[6];
							if(team1GF < team2GF) {return 1;}
							else if(team1GF > team2GF) {return -1;}
							else {return 0;}
						}
					}
				}
			}
		}

	}
