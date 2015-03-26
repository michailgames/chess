package chess.view;

/**
 * Projekt: Szachy
 * Panel odpowiadający za widok panelu zawierającego przebieg partii
 * Michał Rapacz
 * 2015-03-26
 */

import java.awt.Dimension;

import javax.swing.JPanel;

public class LogPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	public LogPanel() {
		setPreferredSize(new Dimension(200, 600));
	}

}
