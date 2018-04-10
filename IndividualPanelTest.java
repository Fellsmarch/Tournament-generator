import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class IndividualPanelTest extends JPanel {

	/**
	 * Create the panel.
	 */
	public IndividualPanelTest() {
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JComboBox<String> cardCombo = new JComboBox<String>();
		springLayout.putConstraint(SpringLayout.EAST, cardCombo, 178, SpringLayout.WEST, this);
		String test = "Test";
		cardCombo.addItem(test);
		
		springLayout.putConstraint(SpringLayout.NORTH, cardCombo, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, cardCombo, 10, SpringLayout.WEST, this);
		add(cardCombo);
		
	}
}
