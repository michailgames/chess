package chess.model.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Field;

public class QueenTest {

    @Test
    public void cannotMoveAtTheBeginning() {
        Board board = new Board();
        board.setup();
        Queen queen = (Queen) board.getPiece(3, 7);
        List<Field> allMoves = queen.getAllLegalMoves(board, 3, 7);
        assertTrue(allMoves.isEmpty());
    }

    @Test
    public void canMoveInStraightLinesAndDiagonallyUntilObstacleMet() {
        Board board = new Board();
        board.setup();
        Queen queen = (Queen) board.getPiece(3, 7);
        board.movePiece(4, 6, 4, 5);
        board.movePiece(3, 7, 6, 4);
        List<Field> allMoves = queen.getAllLegalMoves(board, 6, 4);
        assertEquals(19, allMoves.size());
        assertTrue(allMoves.contains(Field.get(5, 3)));
        assertTrue(allMoves.contains(Field.get(4, 2)));
        assertTrue(allMoves.contains(Field.get(3, 1)));
        assertTrue(allMoves.contains(Field.get(6, 3)));
        assertTrue(allMoves.contains(Field.get(6, 2)));
        assertTrue(allMoves.contains(Field.get(6, 1)));
        assertTrue(allMoves.contains(Field.get(6, 1)));
        assertTrue(allMoves.contains(Field.get(7, 3)));
        assertTrue(allMoves.contains(Field.get(7, 4)));
        assertTrue(allMoves.contains(Field.get(7, 5)));
        assertTrue(allMoves.contains(Field.get(6, 5)));
        assertTrue(allMoves.contains(Field.get(5, 5)));
        assertTrue(allMoves.contains(Field.get(4, 6)));
        assertTrue(allMoves.contains(Field.get(3, 7)));
        assertTrue(allMoves.contains(Field.get(5, 4)));
        assertTrue(allMoves.contains(Field.get(4, 4)));
        assertTrue(allMoves.contains(Field.get(3, 4)));
        assertTrue(allMoves.contains(Field.get(2, 4)));
        assertTrue(allMoves.contains(Field.get(1, 4)));
        assertTrue(allMoves.contains(Field.get(0, 4)));
    }
}
