/**
 * This class creates a table out of Rows (a table is an ArrayList of type Row)
 * The toString method is mainly used for testing, as the final product will be directly put into a GUI
 * 
 * Includes the getter method getTable() which returns the table itself
 * 
 * @author Harrison Cook
 * @version 1.0 03/04/2018
 */

import java.util.ArrayList;

public class Table {
	
	private int numTeams;									//The number of teams to be in the table
	private ArrayList<String> teams;						//The names of the teams to be in the table
	private ArrayList<Row> table = new ArrayList<Row>();	//Initialises the table itself
	
	//Constructor
	public Table(ArrayList<String> teamNames) {
		teams = teamNames;
		numTeams = teamNames.size();
		createTable();
	}
	
	//Builds the empty table out of the team names
	private void createTable() {
		for (int teamIndex = 0; teamIndex < numTeams; teamIndex++) { 	//Iterates while the table does not have the required number of teams in it
			String teamName = teams.get(teamIndex);						//Gets the next team name to add
			Row newRow = new Row(teamName, teamIndex + 1); 				//Creates a new row to add to the table
			table.add(newRow);											//Adds the new row to the table
		}
	}
	
	/**
	 * Getter method
	 * @return The table itself
	 */
	public ArrayList<Row> getTable() {
		return table;
	}
	
	
	public String toString() { //Not used when using the GUI, mainly used for testing purposes
		String toPrint = "";
		for(Row row : table) {
			String toAdd = "\n" + row.toString();
			toPrint += toAdd;
		}
		return toPrint;
	}
//
//	public static void main(String[] args) {
//		// For testing
//		ArrayList<String> names = new ArrayList<String>();
//		names.add("Team 1"); names.add("Team 2"); names.add("Team 3");
//		
//		Table testTable = new Table(names);
//		//testTable.createTable();
//		System.out.println(testTable);
//		
//				
//
//	}

}
