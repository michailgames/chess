package chess.view;

/**
 * Projekt: Szachy
 * G³ówne okno programu
 * Micha³ Rapacz
 * 2015-03-26
 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import chess.controller.ApplicationController;
import chess.controller.GameController;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    private LogPanel logPanel;

    public MainWindow() throws IOException {
        super("michailChess");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(ImageIO.read(getClass().getResource("/logo.png")));
        initUI();
        setSize(900, 700);
        setVisible(true);
    }

    private void initUI() {
        initMenuBar();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);
        logPanel = new LogPanel();
        panel.add(new BoardPanel(logPanel.getMinimumWidth()), BorderLayout.WEST);
        panel.add(logPanel, BorderLayout.CENTER);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu menu = new JMenu("Game");
        JMenuItem item = new JMenuItem("Start new game...");
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showNewGameDialog();
            }
        });
        menu.add(item);
        item = new JMenuItem("Quit");
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(item);
        menuBar.add(menu);

        menu = new JMenu("About");
        item = new JMenuItem("About michailChess...");
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                showAboutDialog();
            }
        });
        menu.add(item);
        menuBar.add(menu);

        setJMenuBar(menuBar);
    }

    private void showNewGameDialog() {
        final JDialog dialog = new JDialog(this, "Start new game", true);
        dialog.setLayout(new BorderLayout());
        JPanel dialogPanel = new JPanel();
        dialog.add(dialogPanel, BorderLayout.CENTER);
        dialogPanel.setLayout(new GridLayout(1, 2));
        final PlayerControllerSelectionPanel whiteSelectionPanel = new PlayerControllerSelectionPanel("White");
        final PlayerControllerSelectionPanel blackSelectionPanel = new PlayerControllerSelectionPanel("Black");
        dialogPanel.add(whiteSelectionPanel);
        dialogPanel.add(blackSelectionPanel);
        JButton startButton = new JButton("Begin");
        startButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGame(whiteSelectionPanel.getSelection(), blackSelectionPanel.getSelection());
                dialog.dispose();
            }
        });
        dialog.add(startButton, BorderLayout.SOUTH);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void showAboutDialog() {
        JDialog dialog = new JDialog(this, "About michailChess", true);
        JPanel panel = new JPanel();
        dialog.add(panel);
        panel.setLayout(new GridLayout(4, 1));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(new JLabel("michailChess", SwingConstants.CENTER));
        panel.add(new JLabel("Autor: Micha³ Rapacz", SwingConstants.CENTER));
        panel.add(new JLabel("Czerwiec 2015", SwingConstants.CENTER));
        panel.add(new JLabel("Program powsta³ jako licencjacki projekt "
                + "programistyczny podczas studiów na Uniwersytecie Wroc³awskim", SwingConstants.CENTER));
        dialog.pack();
        dialog.setVisible(true);
    }

    private void startNewGame(String whiteChoice, String blackchoice) {
        GameController.getInstance().startNewGame(whiteChoice, blackchoice);
        ApplicationController.getInstance().refreshLogs();
        ApplicationController.getInstance().refreshView();
    }

    public void refreshLogs() {
        logPanel.refreshLogs();
        repaint();
    }
}
