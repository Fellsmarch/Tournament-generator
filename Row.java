
public class Row {
	
	
	private int wins;
	private int draws;
	private int losses;
	private String teamName;
	private int tablePosition;
	private int goalsScored;
	private int goalsConceded;
	private int goalDifference;
	
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
