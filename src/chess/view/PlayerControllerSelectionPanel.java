package chess.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PlayerControllerSelectionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;

	private static String[] availableSelections = {"Cz³owiek", "Komputer"};
	
	private JList<String> selection;
	
	public PlayerControllerSelectionPanel(String sideName) {
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setPreferredSize(new Dimension(160, 120));
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
