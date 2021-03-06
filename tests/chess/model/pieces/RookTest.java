package chess.model.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Field;

public class RookTest {

    @Test
    public void cannotMoveAtTheBeginning() {
        Board board = new Board();
        board.setup();
        Rook rook = (Rook) board.getPiece(7, 7);
        List<Field> allMoves = rook.getAllLegalMoves(board, 7, 7);
        assertTrue(allMoves.isEmpty());
    }

    @Test
    public void canMoveInStraightLinesUntilObstacleMet() {
        Board board = new Board();
        board.setup();
        Rook rook = (Rook) board.getPiece(7, 7);
        board.movePiece(7, 6, 7, 4);
        board.movePiece(7, 7, 7, 5);
        board.movePiece(7, 5, 5, 5);
        board.movePiece(5, 5, 5, 4);
        List<Field> allMoves = rook.getAllLegalMoves(board, 5, 4);
        assertEquals(10, allMoves.size());
        assertTrue(allMoves.contains(Field.get(6, 4)));
        assertTrue(allMoves.contains(Field.get(5, 5)));
        assertTrue(allMoves.contains(Field.get(4, 4)));
        assertTrue(allMoves.contains(Field.get(3, 4)));
        assertTrue(allMoves.contains(Field.get(2, 4)));
        assertTrue(allMoves.contains(Field.get(1, 4)));
        assertTrue(allMoves.contains(Field.get(0, 4)));
        assertTrue(allMoves.contains(Field.get(5, 3)));
        assertTrue(allMoves.contains(Field.get(5, 2)));
        assertTrue(allMoves.contains(Field.get(5, 1)));
    }

}
