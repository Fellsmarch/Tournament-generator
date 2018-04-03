/**
 * This class creates a row with the following columns:
 * Postion | Team Name | Wins | Draws | Losses | Goal Difference | Goals Scored | Goals Conceded
 * The toString method is mainly used for testing, as the final product will be directly put into a GUI
 * @author Harrison Cook
 * @version 1.0 03/04/2018
 */

public class Row {
	
	//Need to properly add docstrings for these
	private int wins; 				//The number of wins a team has
	private int draws;				//The number of draws a team has
	private int losses;				//The number of losses a team has
	private String teamName;		//The team name
	private int tablePosition;		//The current position the team holds in a/the table
	private int goalsScored;		//The number of goals the team has scored
	private int goalsConceded;		//The number of goals the team has conceded
	private int goalDifference;		//The difference between the number of goals scored and number of goals conceded
	
	//Constructor
	public Row(String name, int position) {
		teamName = name;
		tablePosition = position;
	}
	
	//Getter methods
	public int getWins() {return wins;}
	public int getDraws() {return draws;}
	public int getLosses() {return losses;}
	public String getTeam() {return teamName;}
	public int getScored() {return goalsScored;}
	public int getConceded() {return goalsConceded;}
	public int getPosition() {return tablePosition;}
	public int getDifference() {return goalDifference;}
	//Setter methods
	public void addWin() {wins += 1;}
	public void addDraw() {draws += 1;}
	public void addLoss() {losses += 1;}
	public void changeTeamName(String newName) {teamName = newName;}
	public void changePosition(int newPosition) {tablePosition = newPosition;}
	public void addScored(int scored) {
		goalsScored += scored;
		goalDifference = goalsScored - goalsConceded;
	}
	public void addConceded(int conceded) {
		goalsConceded += conceded;
		goalDifference = goalsScored - goalsConceded;
	}
	
	public String toString() { //Not used when using the GUI, mainly used for testing purposes
		String borderLine = "--------------------------------------------------------";
		String toReturn = borderLine + "\n";
		toReturn += "|" + tablePosition + "|" + teamName + "|" + wins + "|" + draws + "|" + losses + "|" + goalDifference + "|" + goalsScored + "|" + goalsConceded + "|";
		toReturn += "\n" + borderLine;
		return toReturn;
	}
	
	

	

//	public static void main(String[] args) {
//		// For testing
//		Row row = new Row("Testing", 1);
//		System.out.println(row);
//
//	}

}
