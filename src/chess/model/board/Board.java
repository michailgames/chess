package chess.model.board;

/**
 * Projekt: Szachy
 * Reprezentacja planszy
 * Micha� Rapacz
 * 2015-03-26
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import chess.model.pieces.Bishop;
import chess.model.pieces.King;
import chess.model.pieces.Knight;
import chess.model.pieces.NeverMovedKing;
import chess.model.pieces.NeverMovedRook;
import chess.model.pieces.Pawn;
import chess.model.pieces.Piece;
import chess.model.pieces.Queen;
import chess.model.utils.MoveUtils;

public class Board {

	public static int BOARD_SIZE = 8;
	
	private Piece[] fields;
	private Field whiteKingField;
	private Field blackKingField;
	private Field pawnThatJustMovedTwoSquaresField;
	
	public Board() {
		fields = new Piece[BOARD_SIZE * BOARD_SIZE];
	}
	
	public Board(Board original) {
		fields = Arrays.copyOf(original.fields, BOARD_SIZE * BOARD_SIZE);
		whiteKingField = original.whiteKingField;
		blackKingField = original.blackKingField;
		pawnThatJustMovedTwoSquaresField =
				original.pawnThatJustMovedTwoSquaresField;
	}
	
	public Board(Board previousBoard, Field pieceField, Field targetField) {
		this(previousBoard);
		movePiece(pieceField, targetField.getX(), targetField.getY());
	}

	public void setup() {
		fields = new Piece[BOARD_SIZE * BOARD_SIZE];
		for(int x = 0; x < BOARD_SIZE; x++) {
			setupFigures(0, Color.BLACK);
			fields[fieldIndex(x, 1)] = Pawn.getInstance(Color.BLACK);
			fields[fieldIndex(x, BOARD_SIZE - 2)] = Pawn.getInstance(Color.WHITE);
			setupFigures(BOARD_SIZE - 1, Color.WHITE);
		}
		whiteKingField = new Field(4, BOARD_SIZE - 1);
		blackKingField = new Field(4, 0);
	}
	
	private void setupFigures(int y, Color color) {
		fields[fieldIndex(0, y)] = NeverMovedRook.getInstance(color);
		fields[fieldIndex(1, y)] = Knight.getInstance(color);
		fields[fieldIndex(2, y)] = Bishop.getInstance(color);
		fields[fieldIndex(3, y)] = Queen.getInstance(color);
		fields[fieldIndex(4, y)] = NeverMovedKing.getInstance(color);
		fields[fieldIndex(5, y)] = Bishop.getInstance(color);
		fields[fieldIndex(6, y)] = Knight.getInstance(color);
		fields[fieldIndex(7, y)] = NeverMovedRook.getInstance(color);
	}
	
	public Piece getPiece(int x, int y) {
		return fields[fieldIndex(x, y)];
	}
	
	public Piece getPiece(Field field) {
		return getPiece(field.getX(), field.getY());
	}

	public void movePiece(Field sourceField, int x, int y) {
		movePiece(sourceField.getX(), sourceField.getY(), x, y);
	}
	
	public void movePiece(Field sourceField, Field field) {
		movePiece(sourceField.getX(), sourceField.getY(),
				field.getX(), field.getY());
	}
	
	public void movePiece(int x1, int y1, int x2, int y2) {
		Piece piece = getPiece(x1, y1);
		pawnThatJustMovedTwoSquaresField = null;
		if(piece instanceof Pawn && Math.abs(y2 - y1) == 2) {
			pawnThatJustMovedTwoSquaresField = new Field(x2, y2);
		} else if(piece instanceof King) {
			if(piece.getColor() == Color.WHITE) {
				whiteKingField = new Field(x2, y2);
			} else {
				blackKingField = new Field(x2, y2);
			}
		}
		fields[fieldIndex(x1, y1)] = null;
		fields[fieldIndex(x2, y2)] = piece.move(this, x1, y1, x2, y2);
	}
	
	public void clearField(int x, int y) {
		fields[fieldIndex(x, y)] = null;
	}
	
	// for tests only
	void insertPiece(int x, int y, Piece piece) {
		fields[fieldIndex(x, y)] = piece;
		if(piece instanceof King) {
			if(piece.getColor() == Color.WHITE) {
				whiteKingField = new Field(x, y);
			} else {
				blackKingField = new Field(x, y);
			}
		}
	}

	public GameState getGameState(Color nextPlayerColor) {
		if(whiteKingField == null) {
			return GameState.NONE;
		}
		Field kingfField = getKingField(nextPlayerColor);
		King king = (King) getPiece(kingfField);
		boolean checked = king.isUnderAttack(this,
				kingfField.getX(), kingfField.getY());
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
				Piece piece = fields[fieldIndex(x, y)];
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
	
	private final int fieldIndex(int x, int y) {
		return y * BOARD_SIZE + x;
	}
}
