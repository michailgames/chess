package chess.model.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Field;

public class KingTest {

    @Test
    public void cannotMoveAtTheBeginning() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertTrue(allMoves.isEmpty());
    }

    @Test
    public void canMoveOneFieldInEachDirection() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        board.movePiece(4, 6, 4, 4);
        board.movePiece(5, 1, 5, 2);
        board.movePiece(4, 0, 5, 1);
        board.movePiece(5, 1, 4, 2);
        board.movePiece(4, 2, 4, 3);
        List<Field> allMoves = king.getAllPotentialMoves(board, 4, 3);
        assertEquals(7, allMoves.size());
        assertTrue(allMoves.contains(new Field(3, 2)));
        assertTrue(allMoves.contains(new Field(4, 2)));
        assertTrue(allMoves.contains(new Field(3, 3)));
        assertTrue(allMoves.contains(new Field(5, 3)));
        assertTrue(allMoves.contains(new Field(3, 4)));
        assertTrue(allMoves.contains(new Field(4, 4)));
        assertTrue(allMoves.contains(new Field(5, 4)));
    }

    @Test
    public void canDoShortCastlingWhenItIsSafeAndThereIsAPlace() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        prepareShortCastlingForBlack(board);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertTrue(allMoves.contains(new Field(6, 0)));
    }

    @Test
    public void cannotDoShortCastlingWhenThereIsAPieceInBetween() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        board.movePiece(6, 0, 7, 2);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(6, 0)));
    }

    @Test
    public void cannotDoShortCastlingIfIsUnderAttack() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        prepareShortCastlingForBlack(board);
        board.movePiece(1, 7, 2, 5);
        board.movePiece(2, 5, 3, 3);
        board.movePiece(3, 3, 2, 1);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(6, 0)));
    }

    @Test
    public void cannotDoShortCastlingIfHasMovedBefore() {
        Board board = new Board();
        board.setup();
        prepareShortCastlingForBlack(board);
        board.movePiece(4, 0, 5, 0);
        board.movePiece(5, 0, 4, 0);
        King king = (King) board.getPiece(4, 0);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(6, 0)));
    }

    @Test
    public void cannotDoShortCastlingIfRookHasMovedBefore() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        prepareShortCastlingForBlack(board);
        board.movePiece(7, 0, 6, 0);
        board.movePiece(6, 0, 7, 0);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(6, 0)));
    }

    @Test
    public void cannotDoShortCastlingIfHasToPassThroughAttackedField() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        prepareShortCastlingForBlack(board);
        board.movePiece(1, 6, 1, 5);
        board.movePiece(2, 7, 1, 6);
        board.movePiece(1, 6, 6, 1);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(6, 0)));
    }

    @Test
    public void shortCastlingMovesTheRook() {
        Board board = new Board();
        board.setup();
        prepareShortCastlingForBlack(board);
        board.movePiece(4, 0, 6, 0);
        assertTrue(board.getPiece(5, 0) instanceof Rook);
    }

    @Test
    public void canDoLongCastlingWhenItIsSafeAndThereIsAPlace() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        prepareLongCastlingForBlack(board);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertTrue(allMoves.contains(new Field(2, 0)));
    }

    @Test
    public void cannotDoLongCastlingWhenThereIsAPieceInBetween() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        prepareLongCastlingForBlack(board);
        board.movePiece(3, 1, 3, 0);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(2, 0)));
    }

    @Test
    public void cannotDoLongCastlingIfIsUnderAttack() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        prepareLongCastlingForBlack(board);
        board.movePiece(1, 7, 2, 5);
        board.movePiece(2, 5, 3, 3);
        board.movePiece(3, 3, 2, 1);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(2, 0)));
    }

    @Test
    public void cannotDoLongCastlingIfHasMovedBefore() {
        Board board = new Board();
        board.setup();
        prepareLongCastlingForBlack(board);
        board.movePiece(4, 0, 3, 0);
        board.movePiece(3, 0, 4, 0);
        King king = (King) board.getPiece(4, 0);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(2, 0)));
    }

    @Test
    public void cannotDoLongCastlingIfRookHasMovedBefore() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        prepareLongCastlingForBlack(board);
        board.movePiece(0, 0, 2, 0);
        board.movePiece(2, 0, 0, 0);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(2, 0)));
    }

    @Test
    public void cannotDoLongCastlingIfHasToPassThroughAttackedField() {
        Board board = new Board();
        board.setup();
        King king = (King) board.getPiece(4, 0);
        prepareLongCastlingForBlack(board);
        board.movePiece(3, 6, 3, 5);
        board.movePiece(2, 7, 5, 4);
        board.movePiece(5, 4, 2, 1);
        List<Field> allMoves = king.getAllLegalMoves(board, 4, 0);
        assertFalse(allMoves.contains(new Field(2, 0)));
    }

    @Test
    public void longCastlingMovesTheRook() {
        Board board = new Board();
        board.setup();
        prepareLongCastlingForBlack(board);
        board.movePiece(4, 0, 2, 0);
        assertTrue(board.getPiece(3, 0) instanceof Rook);
    }

    private void prepareShortCastlingForBlack(Board board) {
        board.movePiece(6, 0, 7, 2);
        board.movePiece(6, 1, 6, 3);
        board.movePiece(5, 0, 6, 1);
    }

    private void prepareLongCastlingForBlack(Board board) {
        board.movePiece(1, 0, 0, 2);
        board.movePiece(1, 1, 1, 3);
        board.movePiece(2, 0, 1, 1);
        board.movePiece(3, 1, 3, 2);
        board.movePiece(3, 0, 3, 1);
    }

}
