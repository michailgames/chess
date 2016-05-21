package chess.model.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Field;

public class BishopTest {

    @Test
    public void cannotMoveAtTheBeginning() {
        Board board = new Board();
        board.setup();
        Bishop bishop = (Bishop) board.getPiece(5, 7);
        List<Field> allMoves = bishop.getAllLegalMoves(board, 5, 7);
        assertTrue(allMoves.isEmpty());
    }

    @Test
    public void canMoveDiagonallyUntilObstacleMet() {
        Board board = new Board();
        board.setup();
        Bishop bishop = (Bishop) board.getPiece(5, 7);
        board.movePiece(4, 6, 4, 5);
        board.movePiece(5, 7, 2, 4);
        List<Field> allMoves = bishop.getAllLegalMoves(board, 2, 4);
        assertEquals(9, allMoves.size());
        assertTrue(allMoves.contains(new Field(1, 3)));
        assertTrue(allMoves.contains(new Field(0, 2)));
        assertTrue(allMoves.contains(new Field(1, 5)));
        assertTrue(allMoves.contains(new Field(3, 3)));
        assertTrue(allMoves.contains(new Field(4, 2)));
        assertTrue(allMoves.contains(new Field(5, 1)));
        assertTrue(allMoves.contains(new Field(3, 5)));
        assertTrue(allMoves.contains(new Field(4, 6)));
        assertTrue(allMoves.contains(new Field(5, 7)));
    }

}
