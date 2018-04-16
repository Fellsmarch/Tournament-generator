import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import java.awt.CardLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class GUITableTesting {

	private JFrame frmGroupStage;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITableTesting window = new GUITableTesting();
					window.frmGroupStage.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUITableTesting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGroupStage = new JFrame();
		frmGroupStage.setTitle("Group Stage");
		frmGroupStage.setBounds(100, 100, 665, 505);
		frmGroupStage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frmGroupStage.getContentPane().setLayout(springLayout);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		springLayout.putConstraint(SpringLayout.WEST, table, 10, SpringLayout.WEST, frmGroupStage.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, table, 629, SpringLayout.WEST, frmGroupStage.getContentPane());
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null, null},
				{1, null, null, null, null, null, null, null, null, null},
				{2, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"Pos", "Team", "P", "W", "D", "L", "F", "A", "GD", "PTS"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, Integer.class, Object.class, Object.class, Object.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		
		});
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); 	//Creates a new Default cell renderer 
		centerRenderer.setHorizontalAlignment(JLabel.CENTER); 						//Sets the horizontal alignment of the new renderer to CENTER
		table.setDefaultRenderer(Integer.class, centerRenderer);					//Sets all the Integer cells in table to the same format as centerRenderer
		
		table.getColumnModel().getColumn(0).setPreferredWidth(43);
		table.getColumnModel().getColumn(0).setMinWidth(43);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setMinWidth(180);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setMinWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(30);
		table.getColumnModel().getColumn(4).setMinWidth(30);
		table.getColumnModel().getColumn(5).setPreferredWidth(30);
		table.getColumnModel().getColumn(5).setMinWidth(30);
		table.getColumnModel().getColumn(6).setPreferredWidth(30);
		table.getColumnModel().getColumn(6).setMinWidth(30);
		table.getColumnModel().getColumn(7).setPreferredWidth(30);
		table.getColumnModel().getColumn(7).setMinWidth(30);
		table.getColumnModel().getColumn(8).setPreferredWidth(50);
		table.getColumnModel().getColumn(8).setMinWidth(50);
		table.getColumnModel().getColumn(9).setPreferredWidth(50);
		table.getColumnModel().getColumn(9).setMinWidth(50);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		springLayout.putConstraint(SpringLayout.NORTH, table, 10, SpringLayout.NORTH, frmGroupStage.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, table, 424, SpringLayout.NORTH, frmGroupStage.getContentPane());
		frmGroupStage.getContentPane().add(table);
		
		String test = "Tab 3";
	}
}
	