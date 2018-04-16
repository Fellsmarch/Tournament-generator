/**
 * A panel with two panes, the current group table and then a fixtures tab which lists the fixtures for the groups and give the user
 * a place to submit scores for those fixtures.
 */


import javax.swing.JPanel;
import javax.swing.JTabbedPane;

@SuppressWarnings("serial")
public class GroupPanel extends JPanel
	{

		/**
		 * Create the panel.
		 */
		public GroupPanel(GUITablePanel tablePanel, FixturesPanel fixturesPanel)
			{
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			add(tabbedPane);
			
			tabbedPane.addTab("Table", tablePanel);
			tabbedPane.addTab("Fixtures", fixturesPanel);

			}

	}
