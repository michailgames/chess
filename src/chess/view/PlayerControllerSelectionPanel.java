package chess.view;

/**
 * Projekt: Szachy
 * Panel wyboru gracza kontroluj¹cego stronê
 * Micha³ Rapacz
 * 2015-03-26
 */

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import chess.controller.PlayerController;

public class PlayerControllerSelectionPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JList<String> selection;
	
	public PlayerControllerSelectionPanel(String sideName) {
		setBorder(new BevelBorder(BevelBorder.RAISED));
		setPreferredSize(new Dimension(160, 120));
		setLayout(new GridLayout(2, 1));
		add(new JLabel(sideName));
		selection = new JList<String>(getAvailableSelections());
		selection.setSelectedIndex(0);
		add(selection);
	}
	
	public String getSelection() {
		return selection.getSelectedValue();
	}
	
	private String[] getAvailableSelections() {
		List<String> selectionsList = PlayerController.getInstance()
				.getAvailablePlayers();
		String[] availableSelections = new String[selectionsList.size()];
		availableSelections = selectionsList.toArray(availableSelections);
		return availableSelections;
	}

}
