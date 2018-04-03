import java.util.ArrayList;
import java.util.Random;

public class GroupStage {
	
	private ArrayList<Table> groups;
	
	public GroupStage(int numOfGroups, int numOfTeams, ArrayList<String> teamNames) {
		Random randomiser = new Random();
		groups = new ArrayList<Table>();
		for (int groupNum = numOfGroups; groupNum > 0; groupNum--) {
			ArrayList<String> tableTeamNames = new ArrayList<String>();
			for (int teamNum = numOfTeams; teamNum > 0; teamNum--) {
				int numOfNamesLeft = teamNames.size();
				int nameToAddIndex = randomiser.nextInt(numOfNamesLeft);
				String nameToAdd = teamNames.get(nameToAddIndex);
				teamNames.remove(nameToAddIndex);
				tableTeamNames.add(nameToAdd);
			}
			Table newTable = new Table(tableTeamNames);
			groups.add(newTable);
		}
	}
	
	public String toString() { //Not used when using the GUI, mainly used for testing purposes
		String toPrint = "";
		int tableNum = 1;
		for (Table table : groups) {
			toPrint += "Table #" + tableNum + "\n";
			String toAdd = table.toString();
			//System.out.println(toAdd);
			toPrint += toAdd + "\n";
			tableNum ++;
		}
		//System.out.println(toPrint);
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
