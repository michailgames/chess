package chess.model;

import chess.model.pieces.Bishop;
import chess.model.pieces.King;
import chess.model.pieces.Knight;
import chess.model.pieces.Pawn;
import chess.model.pieces.Queen;
import chess.model.pieces.Rook;

public class Board {

	public static int BOARD_SIZE = 8;
	
	private Piece[][] fields = new Piece[BOARD_SIZE][BOARD_SIZE];
	
	public Board() {
		
	}
	
	public void setup() {
		for(int x = 0; x < BOARD_SIZE; x++) {
			setupFigures(0, Color.BLACK);
			fields[x][1] = new Pawn(Color.BLACK, x, 1);
			fields[x][BOARD_SIZE - 2] = new Pawn(Color.WHITE, x, BOARD_SIZE-2);
			setupFigures(BOARD_SIZE - 1, Color.WHITE);
		}
	}
	
	private void setupFigures(int y, Color color) {
		fields[0][y] = new Rook(color, 0, y);
		fields[1][y] = new Knight(color, 1, y);
		fields[2][y] = new Bishop(color, 2, y);
		fields[3][y] = new Queen(color, 3, y);
		fields[4][y] = new King(color, 4, y);
		fields[5][y] = new Bishop(color, 5, y);
		fields[6][y] = new Knight(color, 6, y);
		fields[7][y] = new Rook(color, 7, y);
	}
	
	public Piece getPiece(int x, int y) {
		return fields[x][y];
	}

	public void movePiece(Piece piece, int x, int y) {
		fields[piece.getX()][piece.getY()] = null;
		fields[x][y] = piece.move(x, y);
	}
}
