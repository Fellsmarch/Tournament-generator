/**
 * This class creates a list of groups (tables) for a group stage of a tournament
 * The toString method is mainly used for testing, as the final product will be directly put into a GUI
 * The term group is a specific version of a table (they are used interchangeably in this instance), in
 * the group stage, a table is just the visual output and statistics of a group 
 * 
 * I need to add getter methods for the groupTables and groups so that other classes can retrieve them
 * 
 * @author Harrison Cook
 * @version 1.0 03/04/2018
 */


import java.util.ArrayList;
import java.util.Random;

public class GroupStage {//implements Tournament{
	
	private ArrayList<Table> groupTables; 												//This ArrayList will hold all the groups, so this list is essentially the group stage
	private ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>(); 	//Creates an ArrayList that will hold the textual representation of a group
																						//that is stored for later use 
	
	public GroupStage(int numOfGroups, int numOfTeams, ArrayList<String> teamNames) {
		Random randomiser = new Random();
		groupTables = new ArrayList<Table>();
		for (int groupNum = numOfGroups; groupNum > 0; groupNum--) { 		//Iterate up to the number of groups (tables) required
			ArrayList<String> tableTeamNames = new ArrayList<String>();		//A list of team names to add to a single group (table)
			for (int teamNum = numOfTeams; teamNum > 0; teamNum--) { 		//Iterate up to the number of teams in a group (table)
				int numOfNamesLeft = teamNames.size();
				int nameToAddIndex = randomiser.nextInt(numOfNamesLeft); 	//Find a random index that is still in teamNames
				String nameToAdd = teamNames.get(nameToAddIndex); 			//Gets the team name at that random index
				teamNames.remove(nameToAddIndex);							//Removes the team name from the list so it is not selected again
				tableTeamNames.add(nameToAdd);								//Adds the team name to the list of team names to add to a single group (table)
			}
			groups.add(tableTeamNames); 									//Adds the list of names to groups that is used to construct a table
			Table newTable = new Table(tableTeamNames); 					//Creates a new group (table)
			groupTables.add(newTable); 										//Adds the newly created table to the list of Tables (ie. to the group stage)
		}
	}
	
	public ArrayList<Table> getGroups() {return groupTables;}
	public ArrayList<ArrayList<String>> getTextGroups() {return groups;}
	
	
	public String toString() { 												//Not used when using the GUI, mainly used for testing purposes
		String toPrint = "";
		int tableNum = 1;
		for (Table table : groupTables) {
			toPrint += "Table #" + tableNum + "\n";
			String toAdd = table.toString();
			toPrint += toAdd + "\n";
			tableNum ++;
		}
		return toPrint;
	}

//	public static void main(String[] args) {
//		// For testing
//		int numOfGroups = 4;
//		int numOfTeams = 5;
//		ArrayList<String> names = new ArrayList<String>();
//		names.add("Team 1"); names.add("Team 2"); names.add("Team 3"); names.add("Team 4"); names.add("Team 5"); names.add("Team 6"); names.add("Team 7"); 
//		names.add("Team 8"); names.add("Team 9"); names.add("Team 10"); names.add("Team 11"); names.add("Team 12"); names.add("Team 13"); names.add("Team 14"); 
//		names.add("Team 15"); names.add("Team 16"); names.add("Team 17"); names.add("Team 18"); names.add("Team 19"); names.add("Team 20");
//		GroupStage test = new GroupStage(numOfGroups, numOfTeams, names);
//		System.out.println(test.toString());
//	}

}
