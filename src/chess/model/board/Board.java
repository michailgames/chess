package chess.model.board;

/**
 * Projekt: Szachy
 * Reprezentacja planszy
 * Micha³ Rapacz
 * 2015-03-26
 */

import java.util.ArrayList;
import java.util.List;

import chess.model.pieces.Bishop;
import chess.model.pieces.King;
import chess.model.pieces.Knight;
import chess.model.pieces.Pawn;
import chess.model.pieces.Piece;
import chess.model.pieces.Queen;
import chess.model.pieces.Rook;
import chess.utils.MoveUtils;

public class Board {

	public static int BOARD_SIZE = 8;
	
	private Piece[][] fields = new Piece[BOARD_SIZE][BOARD_SIZE];
	private Field whiteKingField;
	private Field blackKingField;
	private Field pawnThatJustMovedTwoSquaresField = null;
	
	public Board() { }
	
	public Board(Board original) {
		copyFields(original);
		copyState(original);
	}

	private void copyFields(Board original) {
		fields = new Piece[BOARD_SIZE][BOARD_SIZE];
		for(int x = 0; x < BOARD_SIZE; x++) {
			for(int y = 0; y < BOARD_SIZE; y++) {
				Piece oldPiece = original.fields[x][y];
				if(oldPiece == null) {
					continue;
				}
				Piece newPiece = oldPiece.copy();
				fields[x][y] = newPiece;
				if(newPiece instanceof King) {
					if(newPiece.getColor() == Color.WHITE) {
						whiteKingField = new Field(x, y);
					} else {
						blackKingField = new Field(x, y);
					}
				}
			}
		}
	}
	
	private void copyState(Board original) {
		if(original.pawnThatJustMovedTwoSquaresField != null) {
			int x = original.pawnThatJustMovedTwoSquaresField.getX();
			int y = original.pawnThatJustMovedTwoSquaresField.getY();
			pawnThatJustMovedTwoSquaresField = new Field(x, y);
		}
	}
	
	public Board(Board previousBoard, Field pieceField, Field targetField) {
		this(previousBoard);
		movePiece(pieceField, targetField.getX(), targetField.getY());
	}

	public void setup() {
		fields = new Piece[BOARD_SIZE][BOARD_SIZE];
		for(int x = 0; x < BOARD_SIZE; x++) {
			setupFigures(0, Color.BLACK);
			fields[x][1] = new Pawn(Color.BLACK, x, 1);
			fields[x][BOARD_SIZE - 2] = new Pawn(Color.WHITE, x, BOARD_SIZE-2);
			setupFigures(BOARD_SIZE - 1, Color.WHITE);
		}
		whiteKingField = new Field(4, BOARD_SIZE - 1);
		blackKingField = new Field(4, 0);
	}
	
	private void setupFigures(int y, Color color) {
		fields[0][y] = new Rook(color, 0, y).allowedToPerformCastling();
		fields[1][y] = new Knight(color, 1, y);
		fields[2][y] = new Bishop(color, 2, y);
		fields[3][y] = new Queen(color, 3, y);
		fields[4][y] = new King(color, 4, y).allowedToPerformCastling();
		fields[5][y] = new Bishop(color, 5, y);
		fields[6][y] = new Knight(color, 6, y);
		fields[7][y] = new Rook(color, 7, y).allowedToPerformCastling();
	}
	
	public Piece getPiece(int x, int y) {
		return fields[x][y];
	}
	
	public Piece getPiece(Field field) {
		return getPiece(field.getX(), field.getY());
	}

	public void movePiece(Field sourceField, int x, int y) {
		Piece piece = getPiece(sourceField);
		pawnThatJustMovedTwoSquaresField = null;
		if(piece instanceof Pawn && Math.abs(sourceField.getY() - y) == 2) {
			pawnThatJustMovedTwoSquaresField = new Field(x, y);
		} else if(piece instanceof King) {
			if(piece.getColor() == Color.WHITE) {
				whiteKingField = new Field(x, y);
			} else {
				blackKingField = new Field(x, y);
			}
		}
		fields[sourceField.getX()][sourceField.getY()] = null;
		fields[x][y] = piece.move(this, x, y);
	}
	
	public void movePiece(Field sourceField, Field field) {
		movePiece(sourceField, field.getX(), field.getY());
	}
	
	public void movePiece(int x1, int y1, int x2, int y2) {
		movePiece(new Field(x1, y1), x2, y2);
	}
	
	public void clearField(int x, int y) {
		fields[x][y] = null;
	}
	
	void insertPiece(int x, int y, Piece piece) {
		fields[x][y] = piece;
	}

	public GameState getGameState(Color nextPlayerColor) {
		if(whiteKingField == null) {
			return GameState.NONE;
		}
		King king = (King) getPiece(getKingField(nextPlayerColor));
		boolean checked = king.isUnderAttack(this);
		boolean hasMoves = MoveUtils.hasAnyLegalMove(this, nextPlayerColor);
		if(checked && !hasMoves) {
			return GameState.MATE;
		} else if(checked) {
			return GameState.CHECK;
		} else if(!hasMoves) {
			return GameState.STALEMATE;
		}
		return GameState.OPEN;
	}
	
	public Field getKingField(Color color) {
		return (color == Color.WHITE) ? whiteKingField : blackKingField;
	}
	
	public List<Piece> getAllPieces(Color color) {
		List<Piece> foundPieces = new ArrayList<Piece>();
		for(int x = 0; x < BOARD_SIZE; x++) {
			for(int y = 0; y < BOARD_SIZE; y++) {
				Piece piece = fields[x][y];
				if(piece != null && piece.getColor() == color) {
					foundPieces.add(piece);
				}
			}
		}
		return foundPieces;
	}
	
	public boolean canPawnBeTakenEnPassant(Field pawnField) {
		if(pawnThatJustMovedTwoSquaresField == null) {
			return false;
		}
		return pawnField.equals(pawnThatJustMovedTwoSquaresField);
	}
}
