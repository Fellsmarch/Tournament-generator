/**
 * The main window/controller to create tournaments
 * 
 * @author Harrison Cook
 * 
 * Save/Load not currently working
 */

package controllers;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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

import groupStage.GroupStageCreationPanel;//.GroupStagePanel;
import groupStage.GroupStageCreationPanel.GroupStagePanel;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.awt.event.ActionEvent;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class MainWindow implements Serializable
	{
		/**
		 * The frame to hold all GUI elements in
		 */
		private JFrame mainWindowFrame = new JFrame();
		/**
		 * The card layout to switch between panels
		 */
		private CardLayout cardLayout = new CardLayout();
		/**
		 * The JPanel which holds the panels
		 */
		private JPanel tournamentContainer = new JPanel(cardLayout);
		/**
		 * The tournament name, displayed at the top of the window
		 */
		private String tournamentName;
		/**
		 * The JComboBox to let the user choose what kind of tournament
		 * they want to create
		 */
		private JComboBox<String> tournamentSelector = new JComboBox<String>();
		/**
		 * The field for the user to enter the tournament name
		 */
		private JTextField tournamentNameField;
		
		/**
		 * The groupStage panel
		 */
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
				mainWindowFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
						if (tournamentName.length() <= 26) { //Arbitrary max num
							if (tournamentName.length() > 0) {
								cardLayout.show(tournamentContainer, item);
								mainWindowFrame.setTitle(tournamentName);
								mainWindowFrame.setBounds(100, 100, 450, 370);
							}else {
								JOptionPane.showMessageDialog(mainWindowFrame, "ERROR: Please enter a tournament name!", "ERROR", JOptionPane.ERROR_MESSAGE);
							}
						}else {
							JOptionPane.showMessageDialog(mainWindowFrame, "ERROR: Tournament name must 26 characters or less!", "ERROR", JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				mainWindowPanel.add(btnNewButton, "cell 2 12 3 1,alignx center");
				
				
				JMenuBar menuBar = new JMenuBar();
				mainWindowFrame.getContentPane().add(menuBar, BorderLayout.NORTH);
				
				JMenu mnFile = new JMenu("File");
				menuBar.add(mnFile);
				
				JMenuItem mntmNew = new JMenuItem("New");
				mntmNew.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						MainWindow newWindow = new MainWindow();
//						newWindow.mainWindowFrame.setVisible(true);
						//Exit will exit all of them
					
//						int promptResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?\nUnsaved data will be lost!", "Tournament Generator", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);//, icon, options, initialValue)
//						if (promptResult == JOptionPane.YES_OPTION) {
//							//Make new tournament
					}
				});
				mnFile.add(mntmNew);
				
				JMenuItem mntmSave = new JMenuItem("Save");
				mntmSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser fileChooser = new JFileChooser();
						int userChosenFile = fileChooser.showSaveDialog(null);
						if (userChosenFile == JFileChooser.APPROVE_OPTION) {
							String fileName = fileChooser.getSelectedFile().getName();
							GroupStagePanel groupStage = groupTournament.getGroupStagePanel();
							saveToFile(groupStage, fileName);
						}
					}
				});
				mnFile.add(mntmSave);
				
				JMenuItem mntmLoad = new JMenuItem("Load");
				mntmLoad.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JFileChooser fileChooser = new JFileChooser();
						int userChosenFile = fileChooser.showOpenDialog(null);
						if (userChosenFile == JFileChooser.APPROVE_OPTION) {
							String fileName = fileChooser.getSelectedFile().getName();
							GroupStagePanel groupStage = loadFromFile(fileName);
							tournamentContainer.add(groupStage, "Loaded Group Stage");
							cardLayout.show(tournamentContainer, "Loaded Group Stage");
						}
					}
				});
				mnFile.add(mntmLoad);
				
				JMenuItem mntmInfo = new JMenuItem("Info");
				mnFile.add(mntmInfo);
				
				JMenuItem mntmExit = new JMenuItem("Exit");
				mntmExit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int promptResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?\nUnsaved data will be lost!", "Tournament Generator", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);//, icon, options, initialValue)
						if (promptResult == JOptionPane.YES_OPTION) {
							System.exit(0);
						}
					}
				});
				mnFile.add(mntmExit);
				
				tournamentContainer.setVisible(true);
				
				mainWindowFrame.addWindowListener(new WindowAdapter() {
					@Override
					public void windowClosing(WindowEvent we) {
						//String[] ObjButtons = {
						int promptResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?\nUnsaved data will be lost!", "Tournament Generator", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);//, icon, options, initialValue)
						if (promptResult == JOptionPane.YES_OPTION) {
							System.exit(0);
						}
					}
				});
			}
		
		/**
		 * Saves the tournament to a file
		 * 
		 * @param groupStage the tournament to save (hard coded to group tournament at the moment)
		 * @param fileName the name to give the file
		 */
		public void saveToFile(GroupStagePanel groupStage, String fileName) {
			fileName += ".tournament";
			
			FileOutputStream fileOutput = null;
			ObjectOutputStream objectOutput = null;
			
			try {
				fileOutput = new FileOutputStream(fileName);
				objectOutput = new ObjectOutputStream(fileOutput);
				objectOutput.writeObject(groupStage);
				
				objectOutput.close();
			} catch (Exception ex) {
				System.out.println("Hello");
				//ex.printStackTrace();
			}
		}
		

		/**
		 * Loads a tournament from a file
		 * 
		 * @param fileName the file to load
		 * @return the tournament
		 */
		public GroupStagePanel loadFromFile(String fileName) {
			//fileName += ".ser";
			GroupStagePanel groupStage = null;
			
			FileInputStream fileInput = null;
			ObjectInputStream objectInput = null;
			
			try {
				fileInput = new FileInputStream(fileName);
				objectInput = new ObjectInputStream(fileInput);
				groupStage = (GroupStagePanel) objectInput.readObject();
				
				objectInput.close();
				fileInput.close();
				
				return groupStage;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			return groupStage; //This is just to placate the necessity to return groupStage, should maybe restructure
		}

	}
