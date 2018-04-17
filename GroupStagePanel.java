import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class GroupStagePanel extends JPanel
	{
		private JComboBox<String> cardCombo = new JComboBox<String>();
		private CardLayout cardLayout = new CardLayout();
		private JPanel groupsContainer = new JPanel(cardLayout);
		private JScrollPane scrollPane = new JScrollPane(groupsContainer);
		/**
		 * Create the panel.
		 */
		public GroupStagePanel(ArrayList<String> teams, int numOfGroups, int numOfTeams, int PPWin, int PPDraw, int PPLoss, NumFixtures numFixtures)
			{
				setLayout(new MigLayout("", "[][][][][grow][][][][]", "[][grow]"));
				add(scrollPane, "cell 0 1 9 1,grow");
			
				Random rand = new Random();
				for (int groupNum = 1; groupNum <= numOfGroups; groupNum++) {
					ArrayList<String> groupTeamNames = new ArrayList<String>();
					for (int teamNum = numOfTeams; teamNum > 0; teamNum--) {
						int numOfNamesLeft = teams.size();
						int nameToAddIndex = rand.nextInt(numOfNamesLeft);
						String nameToAdd = teams.get(nameToAddIndex);
						teams.remove(nameToAddIndex);
						groupTeamNames.add(nameToAdd);
					}
					cardCombo.addItem("Group " + groupNum);
					groupsContainer.add(new GroupPanel(groupTeamNames, PPWin, PPDraw, PPLoss, numFixtures));
					
				}
				cardCombo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String item = cardCombo.getSelectedItem().toString();
						cardLayout.show(groupsContainer, item);
					}
				});
				add(cardCombo, "cell 4 0,growx");
			}

	}
