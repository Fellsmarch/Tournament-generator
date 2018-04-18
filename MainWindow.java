import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow
	{

		private JFrame mainWindowFrame = new JFrame();
		private CardLayout cardLayout = new CardLayout();
		private JPanel tournamentContainer = new JPanel(cardLayout);
		private String tournamentName;
		private JComboBox<String> tournamentSelector = new JComboBox<String>();
		private JTextField tournamentNameField;
		
		//Tournament panels
		private GroupStageCreationPanel groupTournament = new GroupStageCreationPanel();

		/**
		 * Launch the application.
		 */
		public static void main(String[] args)
			{
				EventQueue.invokeLater(new Runnable()
					{
						public void run()
							{
								try
									{
										MainWindow window = new MainWindow();
										window.mainWindowFrame.setVisible(true);
									} catch (Exception e)
									{
										e.printStackTrace();
									}
							}
					});
			}

		/**
		 * Create the application.
		 */
		public MainWindow()
			{
				initialize();
			}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize()
			{
				mainWindowFrame = new JFrame();
				mainWindowFrame.setTitle("Tournament Generator 1.0");
				mainWindowFrame.setBounds(100, 100, 450, 300);
				mainWindowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainWindowFrame.getContentPane().add(tournamentContainer);
				
				JPanel mainWindowPanel = new JPanel();
				mainWindowPanel.setLayout(new MigLayout("", "[][][grow,center][200px:n,center][grow,center][][]", "[][15px:n][][][][][5px:n][][][center][5px:n][10px:n][25px:n]"));

				//Add panels to container
				tournamentContainer.add(mainWindowPanel, "Main Window");
				tournamentContainer.add(groupTournament, "Group Tournament");
				
				//Add tournament options to combo box
				tournamentSelector.addItem("Group Tournament");
				mainWindowPanel.add(tournamentSelector, "cell 3 9,growx");
				
				//Rest of the default panel
				JLabel lblTournamentGenerator = new JLabel("Tournament Generator");
				lblTournamentGenerator.setFont(new Font("Dialog", Font.BOLD, 25));
				mainWindowPanel.add(lblTournamentGenerator, "cell 2 0 3 1,alignx center");
				
				JSeparator separator = new JSeparator();
				mainWindowPanel.add(separator, "cell 2 1 3 1,grow");
				
				JLabel lblPleaseInputTournament = new JLabel("Please input tournament name:");
				lblPleaseInputTournament.setFont(new Font("Dialog", Font.PLAIN, 12));
				mainWindowPanel.add(lblPleaseInputTournament, "cell 2 2 3 1,alignx center");
				
				tournamentNameField = new JTextField();
				mainWindowPanel.add(tournamentNameField, "cell 2 3 3 1,growx");
				tournamentNameField.setColumns(20);
				
				JSeparator separator_1 = new JSeparator();
				mainWindowPanel.add(separator_1, "cell 2 5 3 1,grow");
				
				JLabel lblChooseTournamentType = new JLabel("Choose tournament type:");
				lblChooseTournamentType.setFont(new Font("Dialog", Font.PLAIN, 12));
				mainWindowPanel.add(lblChooseTournamentType, "flowx,cell 2 7 3 1,alignx center");
				
				JSeparator separator_2 = new JSeparator();
				mainWindowPanel.add(separator_2, "cell 2 11 3 1,grow");
				
				JButton btnNewButton = new JButton("Confirm");
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String item = tournamentSelector.getSelectedItem().toString();
						tournamentName = tournamentNameField.getText();
						if (tournamentName.length() <= 26 && tournamentName.length() > 0) { //Arbitrary max num
							cardLayout.show(tournamentContainer, item);
							mainWindowFrame.setTitle(tournamentName);
							mainWindowFrame.setBounds(100, 100, 450, 370);
						}else {
							JOptionPane.showMessageDialog(mainWindowFrame, "ERROR: Tournament name must 26 characters or less", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				mainWindowPanel.add(btnNewButton, "cell 2 12 3 1,alignx center");
				
				
				JMenuBar menuBar = new JMenuBar();
				mainWindowFrame.getContentPane().add(menuBar, BorderLayout.NORTH);
				
				JMenu mnFile = new JMenu("File");
				menuBar.add(mnFile);
				
				tournamentContainer.setVisible(true);
			}

	}
