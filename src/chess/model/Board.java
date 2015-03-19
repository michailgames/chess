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
	private King whiteKing;
	private King blackKing;
	
	public Board() { }
	
	public Board(Board previousBoard, Field pieceField, Field targetField) {
		fields = new Piece[BOARD_SIZE][BOARD_SIZE];
		for(int x = 0; x < BOARD_SIZE; x++) {
			for(int y = 0; y < BOARD_SIZE; y++) {
				Piece oldPiece = previousBoard.fields[x][y];
				if(oldPiece != null) {
					Piece newPiece = oldPiece.copy();
					fields[x][y] = newPiece;
					if(newPiece instanceof King) {
						if(newPiece.getColor() == Color.WHITE) {
							whiteKing = (King) newPiece;
						} else {
							blackKing = (King) newPiece;
						}
					}
				}
			}
		}
		movePiece(fields[pieceField.getX()][pieceField.getY()],
				targetField.getX(), targetField.getY());
	}
	
	public void setup() {
		fields = new Piece[BOARD_SIZE][BOARD_SIZE];
		for(int x = 0; x < BOARD_SIZE; x++) {
			setupFigures(0, Color.BLACK);
			fields[x][1] = new Pawn(Color.BLACK, x, 1);
			fields[x][BOARD_SIZE - 2] = new Pawn(Color.WHITE, x, BOARD_SIZE-2);
			setupFigures(BOARD_SIZE - 1, Color.WHITE);
		}
		whiteKing = (King) fields[4][BOARD_SIZE - 1];
		blackKing = (King) fields[4][0];
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
	
	public King getKing(Color color) {
		return (color == Color.WHITE) ? whiteKing : blackKing;
	}
}
