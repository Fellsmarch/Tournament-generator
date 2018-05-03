package bracketStage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GraphicsTest extends JPanel
	{
		int longestTeamName;
		int numTeams;
		ArrayList<Point[]> rounds = new ArrayList<Point[]>();		
		
		//Could input an array/arrayList of teams, and an ArrayList the same as the rounds but for team names
		@Override
		public Dimension getPreferredSize() {
			int paddingX = 10;
			int paddingY = 10;
			int sizeX = (((int) (Math.log(numTeams) / Math.log(2)) + 1) * 210 + paddingX);
			int sizeY = (numTeams * 50);
			return new Dimension(sizeX, sizeY);
		}
		
		@Override
		public void paintComponent(Graphics g) { //This gets run twice for some reason
			
			System.out.println("Updated draw");
			super.paintComponent(g);
			g.setColor(Color.BLACK);
			createFirstRound(g, numTeams);
			paintBrackets(g, numTeams, 1);
			addTeamName(0, 0, "Test");
//			g.drawRect(0, 0, 130, 20); //Enough width for 16 characters
//			g.drawString("1234567890123456",(2), (15));
//			g.drawString("Team " + (i + 1), 2, 15 + (i*50));

		}
		
		private void paintBrackets(Graphics g, int numBrackets, int roundNum) {
			System.out.println("Drawing brackets");
			//int timesRan = 0;
			//int timesLooped = 0;
			while (numBrackets > 1) {
				Point[] prevRound = rounds.get(roundNum - 1);
				Point[] currentRound = new Point[numBrackets/2];
//				if (numBrackets % 2 == 1) {
//					numBrackets++;
//				}
				int index = 0;
				for (int i = 1; i < numBrackets; i+=2) {
					Point teamA = prevRound[i-1];
					Point teamB = prevRound[i];
		
					//Lines from brackets
					g.drawLine(teamA.x, teamA.y, teamA.x + 50, teamA.y); 
					g.drawLine(teamB.x, teamB.y, teamB.x + 50, teamB.y);
					
					//Vertical line connecting brackets
					g.drawLine(teamA.x + 50, teamA.y, teamA.x + 50, teamB.y);
					
					//Horizontal line connecting to new bracket
					g.drawLine(teamA.x + 50, (teamB.y + teamA.y) / 2, teamA.x + 100, (teamB.y + teamA.y) / 2);
					
					//Create new brackets
					g.drawRect(teamA.x + 100, ((teamB.y + teamA.y) / 2) - 10, 130, 20);
		
					currentRound[index] = new Point(teamA.x + 230, ((teamB.y + teamA.y) / 2));
					index++;
				}
				rounds.add(currentRound);
				numBrackets /= 2;
				roundNum++;
				//timesLooped++;
				
			} 
			//System.out.println(timesLooped);
			//timesRan++;
			//System.out.println(timesRan);
			
		}
		
		public void addTeamName(int roundNum, int bracket, String teamName) {
			Point teamLoc = rounds.get(roundNum)[bracket];
			Graphics g = this.getGraphics();
			g.drawString(teamName, teamLoc.x - 128, teamLoc.y + 5);
		}
		
		private void createFirstRound(Graphics g, int numBrackets) {
			Point[] round = new Point[numBrackets];
			rounds.add(round);
			for(int i = 0; i < numBrackets; i++) {
				g.drawRect(0, i*50, 130, 20);
				round[i] = new Point(130, i*50+10);
			}
		}
		
		/**
		 * Create the panel.
		 */
		public GraphicsTest(int numTeams)
			{
				setBackground(Color.white);
				this.numTeams = numTeams;
				Toolkit.getDefaultToolkit().setDynamicLayout(false);
			}
		

	}		


