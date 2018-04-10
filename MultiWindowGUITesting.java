import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
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
	private CardLayout cardLayout = new CardLayout();		//I don't exactly know why I use this
	private JPanel cardContainer = new JPanel(cardLayout);	//Creates the panel with the CardLayout that every other panel goes into
	private String tournamentName;
	private Tournament tournamentType;
	private JTextField textFieldTournamentName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MultiWindowGUITesting window = new MultiWindowGUITesting();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MultiWindowGUITesting() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//frame = new JFrame();
		frame.setBounds(100, 100, 675, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.getContentPane().setLayout(new CardLayout(0, 0)); //sets the frame to CardLayout
		frame.getContentPane().add(cardContainer);
		
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
		cardCombo.addItem("Group Tournament"); cardCombo.addItem("Knockout Tournament"); cardCombo.addItem("Multi-stage Tournament");
		cardCombo.addItem("League Tournament");
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
		
		
		
		
		//JPanel mainScreen = new JPanel(SpringLayout);
		
		JPanel group = new IndividualPanelTest();
		cardContainer.add(group, "Group Tournament");
		
		//fram
		//JPanel mainScreen = new JPanel();
		//frame.getContentPane().add(mainScreen, "Main Screen"); 	//Adds a panel to the frame with the variable name MainScreen and the name "Main Screen"
	}
}
