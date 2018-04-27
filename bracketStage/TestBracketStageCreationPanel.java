package bracketStage;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;

@SuppressWarnings("serial")
public class TestBracketStageCreationPanel extends JPanel
	{
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

		/**
		 * Create the panel.
		 */
		public TestBracketStageCreationPanel()
			{
			setLayout(new MigLayout("", "[][grow][]", "[][][][][][][][][grow][][][0px]"));
			
			JLabel lblNumberOfParticipants = new JLabel("Number of participants");
			add(lblNumberOfParticipants, "cell 0 0");
			
			JComboBox<String> comboBox = new JComboBox<String>();
			comboBox.addItem("Use number of participants entered below"); 
			comboBox.addItem("Choose number of blank brackets to create");
			comboBox.setSelectedIndex(0);
			add(comboBox, "cell 1 0 2 1,growx");
			
			//Only appears if 'choose number of blank brackets to create' is selected
			JLabel lblNumberOfBrackets = new JLabel("Number of brackets");
			add(lblNumberOfBrackets, "cell 0 1");
			textField_1 = new JTextField();
			add(textField_1, "cell 1 1,growx");
			textField_1.setColumns(3);
			
			JButton btnSubmit_1 = new JButton("Submit");
			add(btnSubmit_1, "cell 2 1,growx");
			
			JRadioButton rdbtnSingleElimination = new JRadioButton("Single Elimination");
			rdbtnSingleElimination.setSelected(true);
			add(rdbtnSingleElimination, "cell 0 2");
			
			JRadioButton rdbtnDoubleElimination = new JRadioButton("Double Elimination");
			rdbtnDoubleElimination.setToolTipText("Losers will go to a loser's bracket");
			add(rdbtnDoubleElimination, "cell 1 2 2 1");
			
			JLabel lblGamesPerFixture = new JLabel("Games per fixture");
			add(lblGamesPerFixture, "flowx,cell 0 3");
			
			textField = new JTextField();
			textField.setText("1");
			add(textField, "cell 1 3,growx");
			textField.setColumns(3);
			
			JButton btnSubmit = new JButton("Submit");
			add(btnSubmit, "cell 2 3,growx");
			
			
			//Only appears if an even 'best of' is selected
			JLabel lblTiebreaker = new JLabel("Tie-breaker solution");
			add(lblTiebreaker, "cell 0 4");
			JComboBox<String> comboBox_1 = new JComboBox<String>();
			comboBox_1.addItem("Goal difference"); comboBox_1.addItem("Penalities");//Would need generic term for penalties
			comboBox_1.addItem("Custom (User manually selects winner)");
			add(comboBox_1, "cell 1 4 2 1,growx");
			
			JRadioButton rdbtnRandomlySeeded = new JRadioButton("Un-seeded (random)");
			rdbtnRandomlySeeded.setToolTipText("Also known as a blind draw");
			rdbtnRandomlySeeded.setSelected(true);
			add(rdbtnRandomlySeeded, "cell 0 5");
			
			JRadioButton rdbtnSeeded = new JRadioButton("Seeded ");
			add(rdbtnSeeded, "cell 1 5");
			
			JLabel lblPleaseEnterParticipants = new JLabel("Please enter participants one by one");
			//lblPleaseEnterParticipants.setText("Please enter participants one by one, best first");
			add(lblPleaseEnterParticipants, "cell 0 6 3 1");
			
			textField_2 = new JTextField();
			textField_2.setToolTipText("Enter participant name here");
			add(textField_2, "cell 0 7 2 1,growx");
			textField_2.setColumns(10);
			
			JButton btnNewButton = new JButton("Submit");
			add(btnNewButton, "cell 2 7,growx");
			
			JTextPane textPane = new JTextPane();
			add(textPane, "cell 0 8 2 3,grow");
			
			JLabel lblbestOf = new JLabel("(Best of)");
			add(lblbestOf, "cell 0 3,alignx right");
			
			JButton btnUndo = new JButton("Undo");
			add(btnUndo, "cell 2 8,growx,aligny top");
			
			JButton btnConfirm = new JButton("Confirm");
			add(btnConfirm, "cell 2 9,growx");
			
			JButton btnReset = new JButton("Reset");
			add(btnReset, "cell 2 10,growx");
			
			JLabel lblErrorLabel = new JLabel("");
			lblErrorLabel.setForeground(Color.RED);
			add(lblErrorLabel, "cell 0 11");

			}

	}
