package chess.model.utils;

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.board.Move;
import chess.model.pieces.Piece;

public class MoveUtils {

    private MoveUtils() {}

    public static List<Move> allLegalMoves(Board board, Color color) {
        List<Move> result = new ArrayList<Move>();
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            for (int y = 0; y < Board.BOARD_SIZE; y++) {
                Piece piece = board.getPiece(x, y);
                if (piece == null || piece.getColor() != color) {
                    continue;
                }
                for (Field targetField : piece.getAllLegalMoves(board, x, y)) {
                    result.add(new Move(Field.get(x, y), targetField));
                }
            }
        }
        return result;
    }

    public static List<Move> allPotentialMoves(Board board, Color color) {
        List<Move> result = new ArrayList<Move>();
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            for (int y = 0; y < Board.BOARD_SIZE; y++) {
                Piece piece = board.getPiece(x, y);
                if (piece == null || piece.getColor() != color) {
                    continue;
                }
                for (Field targetField : piece.getAllPotentialMoves(board, x, y)) {
                    result.add(new Move(Field.get(x, y), targetField));
                }
            }
        }
        return result;
    }

    public static boolean hasAnyLegalMove(Board board, Color color) {
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            for (int y = 0; y < Board.BOARD_SIZE; y++) {
                Piece piece = board.getPiece(x, y);
                if (piece == null || piece.getColor() != color) {
                    continue;
                }
                if (piece.getAllLegalMoves(board, x, y).size() > 0) {
                    return true;
                }
            }
        }
        return false;
    }

}
