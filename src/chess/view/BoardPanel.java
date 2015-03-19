package chess.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import chess.controller.ApplicationController;
import chess.controller.GameController;
import chess.model.Board;
import chess.model.Piece;

public class BoardPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private final Color BACKGROUND_COLOR = new Color(255, 255, 255);
	private final Color BLACK_FIELD_COLOR = new Color(192, 192, 192);
	private final Color SELECTION_COLOR = new Color(13, 210, 198, 130);
	private final Color WHITE_FIELD_COLOR = new Color(255, 255, 255);
	private final Color GRID_COLOR = new Color(0, 0, 0);
	private final int MARGIN_SIZE = 10;
	
	private int effectivePaintSize;
	private Board board;

	public BoardPanel() {
		setPreferredSize(new Dimension(600, 600));
		addMouseListener(new BoardClickListener());
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		effectivePaintSize = Math.min(getWidth(), getHeight());
		board = GameController.getInstance().getBoard();
		paintBackground(g);
		paintBoard(g);
		paintSelection(g);
		paintGrid(g);
		paintPieces(g);
		g.dispose();
	}

	private void paintGrid(Graphics g) {
		g.setColor(GRID_COLOR);
		for(int i = 0; i <= Board.BOARD_SIZE; i++) {
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
		int end = calculateOffset(Board.BOARD_SIZE);
		g.fillRect(start, start, end - start, end - start);
		g.setColor(WHITE_FIELD_COLOR);
		for(int i = 0; i < Board.BOARD_SIZE; i++) {
			int y1 = calculateOffset(i);
			int y2 = calculateOffset(i + 1);
			for(int j = i % 2; j < Board.BOARD_SIZE; j += 2) {
				int x1 = calculateOffset(j);
				int x2 = calculateOffset(j + 1);
				g.fillRect(x1, y1, x2 - x1, y2 - y1);
			}
		}
	}
	
	private void paintPieces(Graphics g) {
		for(int i = 0; i < Board.BOARD_SIZE; i++) {
			int x1 = calculateOffset(i);
			int x2 = calculateOffset(i + 1);
			int fieldSize = x2 - x1;
			int margin = (int) (0.1 * fieldSize);
			for(int j = 0; j < Board.BOARD_SIZE; j++) {
				int y2 = calculateOffset(j + 1);
				Piece piece = board.getPiece(i, j); 
				if(piece != null) {
					g.setFont(g.getFont().deriveFont(1f * fieldSize));
					g.drawString(piece.getUnicodeString(), x1, y2 - margin);
				}
			}
		}
	}
	
	private void paintSelection(Graphics g) {
		Piece selectedPiece = GameController.getInstance().getSelectedPiece();
		if(selectedPiece != null) {
			int x1 = calculateOffset(selectedPiece.getX());
			int x2 = calculateOffset(selectedPiece.getX() + 1);
			int y1 = calculateOffset(selectedPiece.getY());
			int y2 = calculateOffset(selectedPiece.getY() + 1);
			g.setColor(SELECTION_COLOR);
			g.fillRect(x1, y1, x2 - x1, y2 - y1);
		}
	}

	private void paintBackground(Graphics g) {
		g.setColor(BACKGROUND_COLOR);
		g.fillRect(0, 0, effectivePaintSize, effectivePaintSize);
	}
	
	private int calculateOffset(int index) {
		return MARGIN_SIZE + (effectivePaintSize - 2 * MARGIN_SIZE)* index
				/ Board.BOARD_SIZE;
	}
	
	private class BoardClickListener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {
			int fieldX = -1;
			int fieldY = -1;
			for(int i = 0; i < Board.BOARD_SIZE; i++) {
				if(calculateOffset(i) <= e.getX() &&
						e.getX() < calculateOffset(i+1)) {
					fieldX = i;
				}
				if(calculateOffset(i) <= e.getY() &&
						e.getY() < calculateOffset(i+1)) {
					fieldY = i;
				}
			}
			if(fieldX != -1 && fieldY != -1) {
				GameController.getInstance().clickField(fieldX, fieldY);
				ApplicationController.getInstance().refreshView();
			}
		}
		
		@Override
		public void mouseClicked(MouseEvent e) { }

		@Override
		public void mouseReleased(MouseEvent e) { }

		@Override
		public void mouseEntered(MouseEvent e) { }

		@Override
		public void mouseExited(MouseEvent e) { }	
	}
}
