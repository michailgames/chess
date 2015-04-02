package chess.utils;

import java.util.ArrayList;
import java.util.List;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Move;
import chess.model.Piece;

public class MoveUtils {

	private MoveUtils() { }
	
	public static List<Move> allLegalMoves(Board board, Color color) {
		List<Move> result = new ArrayList<Move>();
		for(Piece piece : board.getAllPieces(color)) {
			for(Field targetField : piece.getAllLegalMoves(board)) {
				result.add(new Move(piece.getField(), targetField));
			}
		}
		return result;
	}
	
	public static boolean hasAnyLegalMove(Board board, Color color) {
		for(Piece piece : board.getAllPieces(color)) {
			if(piece.getAllLegalMoves(board).size() > 0) {
				return true;
			}
		}
		return false;
	}

}
