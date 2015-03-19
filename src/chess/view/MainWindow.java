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

import chess.controller.ApplicationController;
import chess.controller.GameController;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public MainWindow() {
		super("Szachy");
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
		JMenu menu = new JMenu("Gra");
		JMenuItem item = new JMenuItem("Rozpocznij now¹ grê...");
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
		final JDialog dialog = new JDialog(this, "Rozpocznij now¹ grê", true);
		dialog.setLayout(new BorderLayout());
		JPanel dialogPanel = new JPanel();
		dialog.add(dialogPanel, BorderLayout.CENTER); 
		dialogPanel.setLayout(new GridLayout(1, 2));
		final PlayerControllerSelectionPanel whiteSelectionPanel =
				new PlayerControllerSelectionPanel("Bia³e");
		final PlayerControllerSelectionPanel blackSelectionPanel =
				new PlayerControllerSelectionPanel("Czarne");
		dialogPanel.add(whiteSelectionPanel);
		dialogPanel.add(blackSelectionPanel);
		JButton startButton = new JButton("Rozpocznij");
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
		ApplicationController.getInstance().refreshView();
	}
}
