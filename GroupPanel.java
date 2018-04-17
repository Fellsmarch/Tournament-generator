/**
 * A panel with two panes, the current group table and then a fixtures tab which lists the fixtures for the groups and give the user
 * a place to submit scores for those fixtures.
 */


import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class GroupPanel extends JPanel
	{
		TablePanel tablePanel;
		private FixturesPanel fixturesPanel;
		/**
		 * Create the panel.
		 */
		public GroupPanel(ArrayList<String> groupTeams, int PPWin, int PPDraw, int PPLoss, NumFixtures numFixtures)//TablePanel newTablePanel, FixturesPanel newFixturesPanel)
			{
			tablePanel = new TablePanel(groupTeams, PPWin, PPDraw, PPLoss);
			fixturesPanel = new FixturesPanel(groupTeams, numFixtures, this);
			
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			//JScrollPane scrollPane = new JScrollPane(tabbedPane);
			//add(scrollPane);
			add(tabbedPane);
			
			tabbedPane.addTab("Table", tablePanel);
			tabbedPane.addTab("Fixtures", fixturesPanel);

			}

	}
