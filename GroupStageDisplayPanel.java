import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.SpringLayout;

@SuppressWarnings("serial")
public class GroupStageDisplayPanel extends JPanel
	{

		/**
		 * Create the panel.
		 */
		public GroupStageDisplayPanel(ArrayList<GroupPanel> groupPanels)
			{
			SpringLayout springLayout = new SpringLayout();
			setLayout(springLayout);
			
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 6, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 6, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, 290, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.EAST, tabbedPane, -10, SpringLayout.EAST, this);
			add(tabbedPane);
			
			int groupNum = 1;
			for(GroupPanel groupPanel : groupPanels) {
				String tabTitle = "Group " + groupNum;
				tabbedPane.addTab(tabTitle, groupPanel);
			}
			}
	}
