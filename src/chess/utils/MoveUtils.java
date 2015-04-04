package chess.utils;

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.board.Move;
import chess.model.pieces.Piece;

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
