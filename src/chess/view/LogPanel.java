package chess.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import chess.controller.GameController;
import chess.model.board.GameState;

public class LogPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private JLabel gameStateLabel;

    private MovesLogPanel movesLogPanel;

    public LogPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        addGameStatePanel();
        addMovesLogPanel();
        addUndoButton();
    }

    private void addUndoButton() {
        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                GameController.getInstance().undoMove();
            }
        });
        add(undoButton, BorderLayout.SOUTH);
    }

    private void addMovesLogPanel() {
        movesLogPanel = new MovesLogPanel();
        add(movesLogPanel, BorderLayout.CENTER);
    }

    private void addGameStatePanel() {
        JPanel gameStatePanel = new JPanel();
        gameStatePanel.setBackground(Color.WHITE);
        gameStatePanel.setBorder(BorderFactory.createEtchedBorder());
        add(gameStatePanel, BorderLayout.NORTH);
        gameStateLabel = new JLabel();
        gameStateLabel.setFont(getFont().deriveFont(18f));
        gameStatePanel.add(gameStateLabel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        updateStateLabel();
        super.paintComponent(g);
    }

    private void updateStateLabel() {
        GameState state = GameController.getInstance().getGameState();
        chess.model.board.Color currentColor = GameController.getInstance().getCurrentPlayerColor();
        Color labelColor = determineLabelColor(currentColor);
        String labelText = determineLabelText(currentColor, state);
        gameStateLabel.setForeground(labelColor);
        gameStateLabel.setText(labelText);
    }

    private Color determineLabelColor(chess.model.board.Color currentColor) {
        return currentColor == chess.model.board.Color.WHITE ? BoardPanel.WHITE_PIECES_COLOR
                : BoardPanel.BLACK_PIECES_COLOR;
    }

    private String determineLabelText(chess.model.board.Color currentColor, GameState state) {
        String labelText = currentColor == chess.model.board.Color.WHITE ? "White's turn" : "Black's turn";
        if (state == GameState.NONE) {
            return "-";
        } else if (state == GameState.CHECK) {
            labelText += " [check!]";
        } else if (state == GameState.STALEMATE) {
            labelText += " [stalemate!]";
        } else if (state == GameState.MATE) {
            labelText += " [mate!]";
        }
        return labelText;
    }

    public int getMinimumWidth() {
        return 240;
    }

    public void refreshLogs() {
        movesLogPanel.refreshLogs();
    }
}
