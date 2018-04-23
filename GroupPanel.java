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
		private TablePanel tablePanel;
		private FixturesPanel fixturesPanel;
		/**
		 * Create the panel.
		 */
		public GroupPanel(ArrayList<String> groupTeams, int PPWin, int PPDraw, int PPLoss, NumFixtures numFixtures)//TablePanel newTablePanel, FixturesPanel newFixturesPanel)
			{
			setLayout(new MigLayout("", "[grow]", "[grow]"));
//			System.out.println("Creating a new groupPanel");
			tablePanel = new TablePanel(groupTeams, PPWin, PPDraw, PPLoss);
			fixturesPanel = new FixturesPanel(groupTeams, numFixtures, this);
			
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			//JScrollPane scrollPane = new JScrollPane(tabbedPane);
			//add(scrollPane);
			add(tabbedPane, "grow");
			
			tabbedPane.addTab("Table", tablePanel);
			tabbedPane.addTab("Fixtures", fixturesPanel);
//			System.out.println("Group panel created");
			}
		public TablePanel getTable() {return tablePanel;}

	}
