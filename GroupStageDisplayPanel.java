import java.util.ArrayList;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class GroupStageDisplayPanel extends JPanel
	{
	private JTable table;
	private JTable table_1;

		/**
		 * Create the panel.
		 */
		public GroupStageDisplayPanel(GroupStage groupStage)
			{ 
			ArrayList<Table> groups = groupStage.getGroups();
			setLayout(new MigLayout("", "[grow]", "[grow]"));
			
			JScrollPane scrollPane = new JScrollPane();
			add(scrollPane, "cell 0 0,grow");
			
			table_1 = new JTable();
			scrollPane.setViewportView(table_1);
				
			

				
			
			for (Table group : groups) {
				for (Team team : group.getTable()) {
					
				}
			}
			}

	}
