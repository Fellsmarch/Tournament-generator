package groupStage;
/**
 * A panel with two panes, the current group table and then a fixtures tab which lists the fixtures for the groups and give the user
 * a place to submit scores for those fixtures.
 */


import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import multiUseClasses.FixturesPanel;
import multiUseClasses.TablePanel;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class GroupPanel extends JPanel implements Serializable 
	{
		/**
		 * The table panel
		 */
		private TablePanel tablePanel;
		/**
		 * The fixtures panel
		 */
		private FixturesPanel fixturesPanel;
		
		/**
		 * Construct the panel.
		 * 
		 * @param groupTeams the list of teams/team names
		 * @param PPWin the number of points awarded to a team per win
		 * @param PPDraw the number of points awarded to a team per draw
		 * @param PPLoss the number of points awarded to a team per loss
		 * @param The number of fixtures per group
		 */
		public GroupPanel(ArrayList<String> groupTeams, int PPWin, int PPDraw, int PPLoss, NumFixtures numFixtures)
			{
			setLayout(new MigLayout("", "[grow]", "[grow]"));
			tablePanel = new TablePanel(groupTeams, PPWin, PPDraw, PPLoss);
			fixturesPanel = new FixturesPanel(groupTeams, numFixtures, this);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			add(tabbedPane, "grow");
			
			tabbedPane.addTab("Table", tablePanel);
			tabbedPane.addTab("Fixtures", fixturesPanel);
			}
		
		/**
		 * Returns the tablePanel in used by this class
		 * @return tablePanel
		 */
		public TablePanel getTable() {return tablePanel;}

	}
