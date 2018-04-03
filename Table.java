import java.util.ArrayList;

public class Table {
	
	private int numTeams;
	private ArrayList<String> teams;
	private ArrayList<Row> table = new ArrayList<Row>();
	
	public Table(ArrayList<String> teamNames) {
		teams = teamNames;
		numTeams = teamNames.size();
		createTable();
	}
	
	private void createTable() {
		for(int teamIndex = 0; teamIndex < numTeams; teamIndex++) {
			String teamName = teams.get(teamIndex);
			Row newRow = new Row(teamName, teamIndex + 1);
			table.add(newRow);
		}
	}
	
	public ArrayList<Row> returnTable() {
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
