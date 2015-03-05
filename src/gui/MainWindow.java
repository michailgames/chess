package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		add(panel);
		panel.add(new BoardPanel(), BorderLayout.CENTER);
		panel.add(new LogPanel(), BorderLayout.EAST);
	}
}
