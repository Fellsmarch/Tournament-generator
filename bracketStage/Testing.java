package bracketStage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Testing
	{

		private JFrame frame;

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
										Testing window = new Testing();
										window.frame.setVisible(true);
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
		public Testing()
			{
				initialize();
			}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize()
			{
				frame = new JFrame();
				frame.setBounds(100, 100, 1200, 800);
				frame.setResizable(false);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.getContentPane().setLayout(new MigLayout("", "[448px,grow]", "[249px,grow]"));
				
				GraphicsTest graphicsPanel = new GraphicsTest(16);
				JScrollPane scrollPane = new JScrollPane(graphicsPanel);
				frame.getContentPane().add(scrollPane, "flowx,cell 0 0");
				//frame.add(new GraphicsTest(10));
			}

	}
