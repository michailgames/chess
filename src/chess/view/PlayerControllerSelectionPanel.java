package chess.view;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PlayerControllerSelectionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private static String[] availableSelections = {"Human", "Cpu"};
	
	private JList<String> selection;
	
	public PlayerControllerSelectionPanel(String sideName) {
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setLayout(new GridLayout(2, 1));
		add(new JLabel(sideName));
		selection = new JList<String>(availableSelections);
		selection.setSelectedIndex(0);
		add(selection);
	}
	
	public String getSelection() {
		return selection.getSelectedValue();
	}

}
