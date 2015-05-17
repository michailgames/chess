package chess.view;

/**
 * Projekt: Szachy
 * G��wne okno programu
 * Micha� Rapacz
 * 2015-03-26
 */

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
		setSize(900, 700);
		setVisible(true);
	}
	
	private void initUI() {
		initMenuBar();
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		LogPanel logPanel = new LogPanel();
		panel.add(new BoardPanel(logPanel.getMinimumWidth()), BorderLayout.WEST);
		panel.add(logPanel, BorderLayout.CENTER);
	}
	
	private void initMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Gra");
		JMenuItem item = new JMenuItem("Rozpocznij now� gr�...");
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
		final JDialog dialog = new JDialog(this, "Rozpocznij now� gr�", true);
		dialog.setLayout(new BorderLayout());
		JPanel dialogPanel = new JPanel();
		dialog.add(dialogPanel, BorderLayout.CENTER); 
		dialogPanel.setLayout(new GridLayout(1, 2));
		final PlayerControllerSelectionPanel whiteSelectionPanel =
				new PlayerControllerSelectionPanel("Bia�e");
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
		GameController.getInstance().startNewGame(whiteChoice, blackchoice);
		ApplicationController.getInstance().refreshView();
	}
	
	public void refresh() {
		repaint();
	}
}
