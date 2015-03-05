package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final Color BACKGROUND_COLOR = new Color(255, 255, 255);
	private final Color BLACK_FIELD_COLOR = new Color(200, 139, 71);
	private final Color WHITE_FIELD_COLOR = new Color(255, 206, 158);
	private final Color GRID_COLOR = new Color(0, 0, 0);
	private final int MARGIN_SIZE = 10;
	private final int BOARD_SIZE = 8;
	private int effectivePaintSize;

	public BoardPanel() {
		setPreferredSize(new Dimension(600, 600));
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		effectivePaintSize = Math.min(getWidth(), getHeight());
		paintBackground(g);
		paintBoard(g);
		paintGrid(g);
		g.dispose();
	}

	private void paintGrid(Graphics g) {
		g.setColor(GRID_COLOR);
		for(int i = 0; i <= BOARD_SIZE; i++) {
			int offset = calculateOffset(i);
			int start = MARGIN_SIZE;
			int end = effectivePaintSize - MARGIN_SIZE;
			g.drawLine(offset, start, offset, end);
			g.drawLine(start, offset, end, offset);
		}
	}

	private void paintBoard(Graphics g) {
		g.setColor(BLACK_FIELD_COLOR);
		int start = calculateOffset(0);
		int end = calculateOffset(BOARD_SIZE);
		g.fillRect(start, start, end - start, end - start);
		g.setColor(WHITE_FIELD_COLOR);
		for(int i = 0; i < BOARD_SIZE; i++) {
			int y1 = calculateOffset(i);
			int y2 = calculateOffset(i + 1);
			for(int j = i % 2; j < BOARD_SIZE; j += 2) {
				int x1 = calculateOffset(j);
				int x2 = calculateOffset(j + 1);
				g.fillRect(x1, y1, x2 - x1, y2 - y1);
			}
		}
	}

	private void paintBackground(Graphics g) {
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, effectivePaintSize, effectivePaintSize);
	}
	
	private int calculateOffset(int index) {
		return MARGIN_SIZE + (effectivePaintSize - 2 * MARGIN_SIZE)* index
				/ BOARD_SIZE;
	}
}
