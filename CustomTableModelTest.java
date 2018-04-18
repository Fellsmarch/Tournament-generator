import java.util.Arrays;
import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class CustomTableModelTest extends AbstractTableModel {
		private String[] columnNames = {"Position", "Team Name", "Wins", "Draws", "Losses", 
				"Goal Difference", "Goals For", "Goals Against", "Points"};
		
		private Object[][] tableData;
		private int numTeams;
		private String[] teamNames;
		private int pointsPerWin;
		private int pointsPerDraw;
		private int pointsPerLoss;
		
		public CustomTableModelTest(int numberOfTeams, String[] teamNameList, int PPWin, int PPDraw, int PPLoss) {
			teamNames = teamNameList;
			//numTeams = numberOfTeams;
			numTeams = teamNameList.length;
			pointsPerWin = PPWin; pointsPerDraw = PPDraw; pointsPerLoss = PPLoss;
			
			tableData = new Object[numTeams][9];
			
			for(int i = 0; i < numTeams; i++) {
				String name = teamNames[i];
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
		
		
		public int getRowCount() {
			return numTeams;
		}
		
		public int getColumnCount() {
			return columnNames.length;
		}
		
		public String getColumnName(int column) {
			return columnNames[column];
		}
		
		public Object getValueAt(int row, int column) {
			return tableData[row][column];
		}
		
		//I could either do this so that the user must input results for each team or have them give both teams and the scores for home team
		//Current implementation is the latter
		public void addResult(String homeTeam, String awayTeam, int goalsForHome, int goalsAgainstHome) {
			int homeTeamIndex = -1; int awayTeamIndex = -1;
			for(int i = 0; i < numTeams; i++) {
				if(tableData[i][1] == homeTeam) {homeTeamIndex = i;}
				if(tableData[i][1] == awayTeam) {awayTeamIndex = i;}
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
		
		class TeamComparator implements Comparator<Object[]>{ //Tells the sorting method how to sort (does it in ascending order, so the return statements are reversed)
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