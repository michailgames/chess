package chess.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import chess.controller.PlayerController;

public class PlayerControllerSelectionPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JList<String> selection;

    public PlayerControllerSelectionPanel(String sideName) {
        setBorder(new BevelBorder(BevelBorder.RAISED));
        setMinimumSize(new Dimension(160, 80));
        setLayout(new BorderLayout());
        add(makeSideLabel(sideName), BorderLayout.NORTH);
        selection = new JList<String>(getAvailableSelections());
        selection.setSelectedIndex(0);
        selection.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(selection, BorderLayout.CENTER);
    }

    JLabel makeSideLabel(String sideName) {
        JLabel label = new JLabel(sideName);
        return label;
    }

    public String getSelection() {
        return selection.getSelectedValue();
    }

    private String[] getAvailableSelections() {
        List<String> selectionsList = PlayerController.getInstance().getAvailablePlayers();
        String[] availableSelections = new String[selectionsList.size()];
        availableSelections = selectionsList.toArray(availableSelections);
        return availableSelections;
    }

}
