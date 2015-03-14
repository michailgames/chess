package chess.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import chess.controller.GameController;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MainWindow() {
		super("Chess");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		initUI();
		pack();
		setVisible(true);
	}
	
	private void initUI() {
		initMenuBar();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		panel.add(new BoardPanel(), BorderLayout.CENTER);
		panel.add(new LogPanel(), BorderLayout.EAST);
	}
	
	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Game");
		JMenuItem item = new JMenuItem("Start a new game...");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showNewGameDialog();
			}
		});
		menu.add(item);
		menuBar.add(menu);
		setJMenuBar(menuBar);
	}
	
	private void showNewGameDialog() {
		final JDialog dialog = new JDialog(this, "Start a new game", true);
		dialog.setLayout(new BorderLayout());
		JPanel dialogPanel = new JPanel();
		dialog.add(dialogPanel, BorderLayout.CENTER); 
		dialogPanel.setLayout(new GridLayout(1, 2));
		final PlayerControllerSelectionPanel whiteSelectionPanel =
				new PlayerControllerSelectionPanel("White");
		final PlayerControllerSelectionPanel blackSelectionPanel =
				new PlayerControllerSelectionPanel("Black");
		dialogPanel.add(whiteSelectionPanel);
		dialogPanel.add(blackSelectionPanel);
		JButton startButton = new JButton("Start");
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				startNewGame(whiteSelectionPanel.getSelection(),
						blackSelectionPanel.getSelection());
				dialog.dispose();
			}
		});
		dialog.add(startButton, BorderLayout.SOUTH);
		dialog.pack();
		dialog.setVisible(true);
	}
	
	private void startNewGame(String whiteChoice, String blackchoice) {
		GameController.getInstance().startNewGame();
		repaint();
	}
}
