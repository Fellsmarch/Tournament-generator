import java.util.ArrayList;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

public class RoundPanel extends JPanel
	{
	private JTextField homeScoreField;
	private JTextField awayScoreField;
	private Integer[][] results;

		/**
		 * Create the panel.
		 */
		public RoundPanel(ArrayList<String> teams, int seed)
			{
//				String rows = "[]"; //First one is the header
//				for(int i = 0; i < teams.size(); i++) {
//					rows = "[]" + rows;
//				}
//				
//				setLayout(new MigLayout("", "[]", rows));
				setLayout(new MigLayout("", "[grow][][grow][][][][][][]", "[][][][][]"));
				
				//This is the static stuff that never changes
				JSeparator topBorder = new JSeparator();
				topBorder.setForeground(Color.BLACK);
				topBorder.setBackground(Color.BLACK);
				add(topBorder, "cell 0 0 8 1,grow");
				
				JLabel lblHomeTeam = new JLabel("Home Team");
				add(lblHomeTeam, "cell 0 1,alignx center");
			
				
				JLabel lblAwayTeam = new JLabel("Away Team");
				add(lblAwayTeam, "cell 2 1,alignx center");
				
				JLabel lblScore = new JLabel("Score");
				add(lblScore, "cell 4 1 3 1,alignx center");
				
				JSeparator headerBorder = new JSeparator();
				headerBorder.setBackground(Color.BLACK);
				headerBorder.setForeground(Color.BLACK);
				add(headerBorder, "cell 0 2 9 1,grow");
				
				int maxCount = teams.size();
				
				results = new Integer[maxCount / 2][4]; //Creates an array of each fixture holding the [homeTeamIndex, awayTeamIndex, scoreFor, scoreAgainst]
				
//				int count = 0;
				int index = seed;
//				while(count < maxCount) {
//					String homeTeam = teams.get(index); 
//					index += 1; index %= maxCount;
//					String awayTeam = teams.get(index);
//					index += 1; index %= maxCount;
//				}
				for(int i = 0; i < results.length; i++) {
					String homeTeam = teams.get(index); 
					index += 1; index %= maxCount;
					String awayTeam = teams.get(index);
					index += 1; index %= maxCount;
				}
				
				//Below this must be generated for each fixtures
				
				JLabel lblTeam1 = new JLabel("Team 1");
				add(lblTeam1, "cell 0 3,alignx center");
				
				JLabel lblTeam2 = new JLabel("Team 2");
				add(lblTeam2, "cell 2 3,alignx center");
				
				homeScoreField = new JTextField();
				add(homeScoreField, "cell 4 3");
				homeScoreField.setColumns(3);
				
				JLabel label = new JLabel("-");
				add(label, "cell 5 3,alignx trailing");
				
				awayScoreField = new JTextField();
				add(awayScoreField, "cell 6 3");
				awayScoreField.setColumns(3);
				
				JButton btnSubmit = new JButton("Submit");
				add(btnSubmit, "cell 8 3");
				
				JSeparator fixtureBorder = new JSeparator();
				fixtureBorder.setForeground(Color.BLACK);
				fixtureBorder.setBackground(Color.BLACK);
				add(fixtureBorder, "cell 0 4 9 1,grow");
				
				JSeparator verticalBorderAdjust1 = new JSeparator();
				verticalBorderAdjust1.setOrientation(SwingConstants.VERTICAL);
				verticalBorderAdjust1.setBackground(Color.BLACK);
				verticalBorderAdjust1.setForeground(Color.BLACK);
				add(verticalBorderAdjust1, "cell 1 0 1 4,grow");
				
				JSeparator verticalBorderAdjust2 = new JSeparator();
				verticalBorderAdjust2.setOrientation(SwingConstants.VERTICAL);
				verticalBorderAdjust2.setForeground(Color.BLACK);
				verticalBorderAdjust2.setBackground(Color.BLACK);
				add(verticalBorderAdjust2, "cell 3 0 1 4,grow");
				
				JSeparator verticalBorderAdjust3 = new JSeparator();
				verticalBorderAdjust3.setOrientation(SwingConstants.VERTICAL);
				verticalBorderAdjust3.setForeground(Color.BLACK);
				verticalBorderAdjust3.setBackground(Color.BLACK);
				add(verticalBorderAdjust3, "cell 7 0 1 4,grow");
				
			
			}

	}
