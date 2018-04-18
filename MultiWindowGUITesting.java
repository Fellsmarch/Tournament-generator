import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MultiWindowGUITesting {

	private JFrame frame = new JFrame(); 					//Creates the frame to put the CardLayout panel in
	private CardLayout cardLayout = new CardLayout();		
	private JPanel cardContainer = new JPanel(cardLayout);	//Creates the panel with the CardLayout that every other panel goes into
	private String tournamentName;
	@SuppressWarnings("unused")
//	private Tournament tournamentType;
	private JTextField textFieldTournamentName;
	private CustomTableModelTest modelTest;

	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MultiWindowGUITesting window = new MultiWindowGUITesting();
//					window.frame.setVisible(true);
//					
//					
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		MultiWindowGUITesting window = new MultiWindowGUITesting();
		window.frame.setVisible(true);
		Thread.sleep(7000);
		window.modelTest.addResult("Team 1", "Team 3", 2, 6);
	}

	/**
	 * Create the application.
	 * @throws InterruptedException 
	 */
	public MultiWindowGUITesting() throws InterruptedException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws InterruptedException 
	 */
	private void initialize() throws InterruptedException {
		//frame = new JFrame();
		//frame.setBounds(100, 100, 450, 350);
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new CardLayout(0, 0)); //sets the frame to CardLayout
		frame.getContentPane().add(cardContainer);
		frame.setResizable(true);
		
		JPanel mainScreen = new JPanel();
		cardContainer.add(mainScreen, "name_513900034118511");
		SpringLayout sl_mainScreen = new SpringLayout();
		mainScreen.setLayout(sl_mainScreen);
		
		JLabel lblTournamentGenerator = new JLabel("Tournament Generator");
		lblTournamentGenerator.setHorizontalAlignment(SwingConstants.CENTER);
		sl_mainScreen.putConstraint(SpringLayout.NORTH, lblTournamentGenerator, 10, SpringLayout.NORTH, mainScreen);
		sl_mainScreen.putConstraint(SpringLayout.WEST, lblTournamentGenerator, 149, SpringLayout.WEST, mainScreen);
		sl_mainScreen.putConstraint(SpringLayout.EAST, lblTournamentGenerator, -138, SpringLayout.EAST, mainScreen);
		lblTournamentGenerator.setFont(new Font("Tahoma", Font.BOLD, 30));
		mainScreen.add(lblTournamentGenerator);
		
		JLabel lblTournamentName = new JLabel("Tournament name:");
		sl_mainScreen.putConstraint(SpringLayout.NORTH, lblTournamentName, 38, SpringLayout.SOUTH, lblTournamentGenerator);
		sl_mainScreen.putConstraint(SpringLayout.WEST, lblTournamentName, 10, SpringLayout.WEST, mainScreen);
		mainScreen.add(lblTournamentName);
		
		textFieldTournamentName = new JTextField();
		sl_mainScreen.putConstraint(SpringLayout.NORTH, textFieldTournamentName, 38, SpringLayout.SOUTH, lblTournamentGenerator);
		sl_mainScreen.putConstraint(SpringLayout.WEST, textFieldTournamentName, 6, SpringLayout.EAST, lblTournamentName);
		sl_mainScreen.putConstraint(SpringLayout.SOUTH, textFieldTournamentName, 26, SpringLayout.NORTH, lblTournamentName);
		sl_mainScreen.putConstraint(SpringLayout.EAST, textFieldTournamentName, -159, SpringLayout.EAST, mainScreen);
		mainScreen.add(textFieldTournamentName);
		textFieldTournamentName.setColumns(10);
		
		JLabel lblMaxChars = new JLabel("Max. 26 characters");
		sl_mainScreen.putConstraint(SpringLayout.NORTH, lblMaxChars, 6, SpringLayout.NORTH, lblTournamentName);
		sl_mainScreen.putConstraint(SpringLayout.WEST, lblMaxChars, 6, SpringLayout.EAST, textFieldTournamentName);
		lblMaxChars.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mainScreen.add(lblMaxChars);
		
		JLabel lblChooseTournament = new JLabel("Please choose tournament type:");
		sl_mainScreen.putConstraint(SpringLayout.NORTH, lblChooseTournament, 22, SpringLayout.SOUTH, textFieldTournamentName);
		sl_mainScreen.putConstraint(SpringLayout.WEST, lblChooseTournament, 190, SpringLayout.WEST, mainScreen);
		sl_mainScreen.putConstraint(SpringLayout.SOUTH, lblChooseTournament, 48, SpringLayout.SOUTH, lblTournamentName);
		sl_mainScreen.putConstraint(SpringLayout.EAST, lblChooseTournament, 0, SpringLayout.EAST, textFieldTournamentName);
		lblChooseTournament.setHorizontalAlignment(SwingConstants.CENTER);
		mainScreen.add(lblChooseTournament);
		
		JComboBox<String> cardCombo = new JComboBox<String>();
		sl_mainScreen.putConstraint(SpringLayout.NORTH, cardCombo, 6, SpringLayout.SOUTH, lblChooseTournament);
		sl_mainScreen.putConstraint(SpringLayout.WEST, cardCombo, 79, SpringLayout.WEST, lblChooseTournament);
		sl_mainScreen.putConstraint(SpringLayout.EAST, cardCombo, 0, SpringLayout.EAST, textFieldTournamentName);
		cardCombo.addItem("Group Tournament"); cardCombo.addItem("GroupPanel Test");//cardCombo.addItem("Knockout Tournament"); cardCombo.addItem("Multi-stage Tournament");
		//cardCombo.addItem("League Tournament"); cardCombo.addItem("Table Test");
		mainScreen.add(cardCombo);
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		sl_mainScreen.putConstraint(SpringLayout.WEST, lblError, 10, SpringLayout.WEST, mainScreen);
		sl_mainScreen.putConstraint(SpringLayout.SOUTH, lblError, -10, SpringLayout.SOUTH, mainScreen);
		sl_mainScreen.putConstraint(SpringLayout.EAST, lblError, 639, SpringLayout.WEST, mainScreen);
		mainScreen.add(lblError);
		
		JButton btnConfirm = new JButton("Confirm");
		sl_mainScreen.putConstraint(SpringLayout.NORTH, btnConfirm, 25, SpringLayout.SOUTH, cardCombo);
		sl_mainScreen.putConstraint(SpringLayout.WEST, btnConfirm, 0, SpringLayout.WEST, lblTournamentGenerator);
		sl_mainScreen.putConstraint(SpringLayout.SOUTH, btnConfirm, -58, SpringLayout.SOUTH, mainScreen);
		sl_mainScreen.putConstraint(SpringLayout.EAST, btnConfirm, 0, SpringLayout.EAST, textFieldTournamentName);
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String item = cardCombo.getSelectedItem().toString();
				tournamentName = textFieldTournamentName.getText();
				if (tournamentName.length() <= 26 && tournamentName.length() > 0) {
					lblError.setText("");
					cardLayout.show(cardContainer, item);
				}else {
					lblError.setText("ERROR: Tournament name must be between 1 and 26 characters");
				}
				
			}
		});
		mainScreen.add(btnConfirm);
		
		
		
		
		//Group testing
		//Table panel
		JPanel group = new GroupStageCreationPanel();
		cardContainer.add(group, "Group Tournament");
		String[] teams = {"Team 1", "Team 2", "Team 3", "Team 4", "Team 5", "Team 6", "Team 7", 
				"Team 8", "Team 9", "Team 10", "Team 11", "Team 12", "Team 13", "Team 14", "Team 15", 
				"Team 16"};
		modelTest = new CustomTableModelTest(4, teams, 3, 1, 0);
		JTable testTable = new JTable(modelTest);
		JScrollPane testPane = new JScrollPane(testTable);
		JPanel testPanel = new JPanel();
		testPanel.add(testPane);
		//modelTest.addResult("Team 1", "Team 3", 3, 2);
		modelTest.addResult("Team 4", "Team 2", 2, 0);
		//cardContainer.add(testPanel, "Table Test");

		//Fixtures panel
//		String[] tempTeams = {"Team 1", "Team 2", "Team 3", "Team 4", "Vinnie is lameeeeeeeeee"};
//		ArrayList<String> teamsList = new ArrayList<String>(Arrays.asList(tempTeams));
//		FixturesPanel fixturesPanel = new FixturesPanel(teamsList, NumFixtures.SINGLE_ROUND_ROBIN);
//		
//		//GroupPanel
//		GroupPanel groupPanel = new GroupPanel(testPanel, fixturesPanel);
//		cardContainer.add(groupPanel, "GroupPanel Test");
		
		
		
		//tablePanel.table.addResult
		
		//fram
		//JPanel mainScreen = new JPanel();
		//frame.getContentPane().add(mainScreen, "Main Screen"); 	//Adds a panel to the frame with the variable name MainScreen and the name "Main Screen"
	}
}
